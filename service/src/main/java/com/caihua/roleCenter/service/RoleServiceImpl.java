package com.caihua.roleCenter.service;

import com.caihua.roleCenter.domain.mapper.RoleMapper;
import com.caihua.roleCenter.model.dto.Role;
import com.caihua.roleCenter.model.dto.RoleAction;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * created by zhengliu on 2018/6/28
 */
@Service
public class RoleServiceImpl implements RoleService{

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private RoleActionService roleActionService;


    @Override
    public List<Role> select(Role model) {
        return roleMapper.select(model);
    }

    @Override
    public Role getById(Integer id) {
        if(id!=null&&id>0){
            return roleMapper.getById(id);
        }
        return null;
    }

    @Override
    public boolean doAddOrUpdate(Role role) {
        boolean flag;
        role.setUpdated(System.currentTimeMillis());
        if(role.getId()!=null&&role.getId()>0){
            //修改
            flag=roleMapper.update(role)>0;
        }else {
            role.setCreated(System.currentTimeMillis());
            //新增
            flag= roleMapper.insert(role)>0;
        }
        return flag;
    }

    @Override
    public boolean delete(Integer id) {
        int delete = roleMapper.delete(id);
        if(delete>0){
            userRoleService.deleteRelationRole(id,null);
            RoleAction roleAction=new RoleAction();
            roleAction.setRoleId(id);
            roleActionService.deleteRelationAction(roleAction);
        }
        return delete>0;
    }

    @Override
    public List<Role> findByUserId(Integer userId) {
        return roleMapper.findByUserId(userId);
    }

}
