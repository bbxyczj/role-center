package com.caihua.roleCenter.service;

import com.caihua.roleCenter.model.dto.RoleAction;

import java.util.List;

/**
 * created by zhengliu on 2018/6/29
 */
public interface RoleActionService {
    boolean insert(Integer roleId, String[] ids);

    boolean deleteRelationAction(RoleAction roleAction);

    List<Integer> selectByUserId(Integer id);
}
