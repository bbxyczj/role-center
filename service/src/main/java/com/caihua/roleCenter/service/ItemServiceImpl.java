package com.caihua.roleCenter.service;

import com.caihua.roleCenter.common.utils.Md5Util;
import com.caihua.roleCenter.domain.mapper.ItemMapper;
import com.caihua.roleCenter.model.dto.Action;
import com.caihua.roleCenter.model.dto.Item;
import com.caihua.roleCenter.model.dto.User;
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
public class ItemServiceImpl implements ItemService{

    @Resource
    private ItemMapper itemMapper;

    @Resource
    private ActionService actionService;

    @Resource
    private UserService userService;

    @Resource
    private RoleActionService roleActionService;

    @Override
    public List<Item> select(Item model) {
        return itemMapper.select(model);
    }

    @Override
    public Item getById(Integer id) {
        if(id==null){
            return null;
        }
        return itemMapper.getById(id);
    }

    @Override
    public Item getByNo(String no) {
        if(no==null){
            return null;
        }
        return itemMapper.getByNo(no);
    }

    @Override
    public boolean doAddOrUpdate(Item item) {
        boolean flag;
        item.setUpdated(System.currentTimeMillis());
        if(item.getId()!=null&&item.getId()>0){
            flag= itemMapper.update(item)>0;

        }else {
            item.setCreated(System.currentTimeMillis());
            item.setStatus(1);
            item.setNo(Md5Util.md5To16(item.getName()+System.currentTimeMillis()));
            flag= itemMapper.insert(item)>0;
        }
        return flag;
    }

    @Override
    public boolean delete(Integer id) {
        return itemMapper.delete(id)>0;
    }

    @Override
    public List<Action> getActionIndex(ActionUserRequest request) {
        //查询出 用户角色 项目id
        Item item = getByNo(request.getItemNo());
        if(item==null){
            throw new RcException("查无此项目");
        }
        User user=new User();
        user.setId(request.getUserId());
        List<User> userList = userService.select(user);
        if(CollectionUtils.isEmpty(userList)){
            throw new RcException("查无此用户");
        }
        user=userList.get(0);
        TreeActionVo treeActionVo=new TreeActionVo();
        treeActionVo.setIsIndex(request.getIsIndex()==null?1:request.getIsIndex());
        treeActionVo.setItemId(item.getId());

        List<Integer> actionIds = roleActionService.selectByUserId(user.getId());
        if(CollectionUtils.isEmpty(actionIds)){
            throw new RcException("用户暂无任何权限");
        }
        treeActionVo.setActionIds(actionIds);
        return actionService.getActionByItemId(treeActionVo);
    }

    @Override
    public List<String> getActionAll(ActionUserRequest request) {

        //查询出 用户角色 项目id
        Item item = getByNo(request.getItemNo());
        if(item==null){
            throw new RcException("查无此项目");
        }
        User user=new User();
        user.setId(request.getUserId());
        List<User> userList = userService.select(user);
        if(CollectionUtils.isEmpty(userList)){
            throw new RcException("查无此用户");
        }
        user=userList.get(0);
        return userService.getFunctionStr(request.getItemNo(),user.getId());
    }

    @Override
    public Boolean getAction(ActionUserRequest request) {
        return actionService.getAction(request)!=null;
    }
}
