package com.caihua.roleCenter.service;

import com.caihua.roleCenter.domain.mapper.RoleActionMapper;
import com.caihua.roleCenter.model.dto.RoleAction;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * created by zhengliu on 2018/6/29
 */
@Service
public class RoleActionServiceImpl implements RoleActionService{

    @Resource
    private RoleActionMapper roleActionMapper;


    @Override
    public boolean insert(Integer roleId, String[] ids) {
        RoleAction roleAction=new RoleAction();
        roleAction.setRoleId(roleId);
        for (String actionId:ids) {
            roleAction.setActionId(Integer.parseInt(actionId));
            int count=roleActionMapper.count(roleAction);
            if(count>0){
                continue;
            }
            roleActionMapper.insert(roleAction);
        }
        return true;
    }

    @Override
    public boolean deleteRelationAction(RoleAction roleAction) {
            return roleActionMapper.delete(roleAction)>0;
    }

    @Override
    public List<Integer> selectByUserId(Integer id) {
        return roleActionMapper.selectByUserId(id);
    }
}
