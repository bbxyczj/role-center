package com.caihua.roleCenter.service;

import com.caihua.roleCenter.domain.mapper.ActionMapper;
import com.caihua.roleCenter.model.dto.Action;
import com.caihua.roleCenter.model.dto.RoleAction;
import com.caihua.roleCenter.common.exception.RcException;
import com.caihua.roleCenter.model.request.ActionUserRequest;
import com.caihua.roleCenter.model.vo.TreeActionVo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * created by zhengliu on 2018/6/29
 */
@Service
public class ActionServiceImpl implements ActionService {

    @Resource
    private ActionMapper actionMapper;

    @Resource
    private RoleActionService roleActionService;

    @Override
    public List<Action> select(Action model) {
        return actionMapper.select(model);
    }

    @Override
    public Action getById(Integer id) {
        if (id == null) {
            return null;
        }
        return actionMapper.getById(id);
    }

    @Override
    public boolean doAddOrUpdate(Action action) {
        boolean flag;
        action.setUpdated(System.currentTimeMillis());
        if (action.getId() != null && action.getId() > 0) {
            flag = actionMapper.update(action) > 0;
        } else {
            action.setCreated(System.currentTimeMillis());
            flag = actionMapper.insert(action) > 0;
        }
        return flag;
    }

    @Override
    public boolean delete(Integer id) {
        //删除关联角色的action
        Action action = new Action();
        action.setPid(id);
        List<Action> select = actionMapper.select(action);
        if (!CollectionUtils.isEmpty(select)) {
            throw new RcException("该权限下有子权限未删除");
        }
        RoleAction roleAction = new RoleAction();
        roleAction.setActionId(id);
        roleActionService.deleteRelationAction(roleAction);
        return actionMapper.delete(id) > 0;
    }

    @Override
    public List<Action> findByRoleId(Integer roleId) {
        return actionMapper.findByRoleId(roleId);
    }

    @Override
    public List<Action> getActionByItemId(TreeActionVo treeActionVo) {
        if (treeActionVo == null || treeActionVo.getItemId() == null) {
            throw new RcException("请选择项目");
        }
        if (treeActionVo.getIsIndex() == null) {
            treeActionVo.setIsIndex(0);
        }
        if (treeActionVo.getPid() == null) {
            treeActionVo.setPid(0);
        }
        return getActionVo(treeActionVo);
    }

    @Override
    public Action getAction(ActionUserRequest request) {
        return actionMapper.getAction(request);
    }

    /**
     * 插入项目权限
     *
     * @param list
     * @param itemId
     * @return
     */
    @Override
    public int setItemActionJson(List<Action> list, Integer itemId, int pid) {
        Integer result = 0;
        if (itemId == null) {
            throw new RcException("请生成项目，并提交项目id");
        }
        for (Action action : list) {
            action.setCreated(System.currentTimeMillis());
            action.setUpdated(System.currentTimeMillis());
            action.setItemId(itemId);
            action.setPid(pid);
            result += actionMapper.insert(action);
            if (!CollectionUtils.isEmpty(action.getActionList())) {
                result+=setItemActionJson(action.getActionList(), itemId, action.getId());
            }
        }
        return result;
    }


    private List<Action> getActionVo(TreeActionVo treeActionVo) {
        List<Action> actionVos = actionMapper.getActionVo(treeActionVo);
        if (CollectionUtils.isEmpty(actionVos)) {
            return null;
        }
        actionVos.forEach(actionVo -> {
            TreeActionVo tav = new TreeActionVo();
            tav.setIsIndex(treeActionVo.getIsIndex());
            tav.setItemId(treeActionVo.getItemId());
            tav.setPid(actionVo.getId());
            tav.setActionIds(treeActionVo.getActionIds());
            actionVo.setActionList(getActionVo(tav));
        });
        return actionVos;
    }
}
