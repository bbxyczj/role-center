package com.caihua.roleCenter.service;

/**
 * created by zhengliu on 2018/6/28
 */
public interface UserRoleService {
    boolean insert(Integer userId, String[] ids);

    boolean deleteRelationRole(Integer roleId, Integer userId);
}
