package com.caihua.roleCenter.service;

import com.caihua.roleCenter.domain.mapper.UserRoleMapper;
import com.caihua.roleCenter.model.dto.UserRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * created by zhengliu on 2018/6/28
 */
@Service
public class UserRoleServiceImpl implements UserRoleService{

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public boolean insert(Integer userId,String[] ids) {
        UserRole userRole=new UserRole();
        userRole.setUserId(userId);
        userRole.setCreated(System.currentTimeMillis());
        userRole.setUpdated(System.currentTimeMillis());
        for (String s : ids) {
            //先查询用户是否有此角色
            userRole.setRoleId(Integer.parseInt(s));
            int count = userRoleMapper.count(userRole);
            if(count>0){
                continue;
            }
            userRoleMapper.insert(userRole);
        }
        return true;
    }

    @Override
    public boolean deleteRelationRole(Integer roleId, Integer userId) {
        return userRoleMapper.delete(roleId,userId)>0;
    }
}
