package com.caihua.roleCenter.service;

import com.caihua.roleCenter.model.dto.Role;

import java.util.List;

/**
 * created by zhengliu on 2018/6/28
 */
public interface RoleService {
    List<Role> select(Role model);

    Role getById(Integer id);

    boolean doAddOrUpdate(Role role);

    boolean delete(Integer id);

    List<Role> findByUserId(Integer userId);

}
