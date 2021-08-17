package com.caihua.roleCenter.service;

import com.caihua.roleCenter.model.dto.Action;
import com.caihua.roleCenter.model.request.ActionUserRequest;
import com.caihua.roleCenter.model.vo.TreeActionVo;

import java.util.List;

/**
 * created by zhengliu on 2018/6/29
 */
public interface ActionService {
    List<Action> select(Action model);

    Action getById(Integer id);

    boolean doAddOrUpdate(Action item);

    boolean delete(Integer id);

    List<Action> findByRoleId(Integer roleId);

    List<Action> getActionByItemId(TreeActionVo treeActionVo);

    Action getAction(ActionUserRequest request);

    int setItemActionJson(List<Action> list, Integer itemId, int pid);
}

