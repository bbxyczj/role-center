package com.caihua.roleCenter.service;

import com.caihua.roleCenter.model.dto.Action;
import com.caihua.roleCenter.model.dto.Item;
import com.caihua.roleCenter.model.request.ActionUserRequest;

import java.util.List;

/**
 * created by zhengliu on 2018/6/29
 */
public interface ItemService {
    List<Item> select(Item model);

    Item getById(Integer id);

    Item getByNo(String no);

    boolean doAddOrUpdate(Item item);

    boolean delete(Integer id);

    List<Action> getActionIndex(ActionUserRequest request);

    List<String> getActionAll(ActionUserRequest request);

    Boolean getAction(ActionUserRequest request);
}
