package com.caihua.roleCenter.service;

import com.caihua.roleCenter.model.dto.Action;
import com.caihua.roleCenter.model.dto.User;

import java.util.List;

/**
 * created by zhengliu on 2018/6/27
 */
public interface UserService {

    List<User> select(User model);

    boolean doAddOrUpdate(User user);

    User getById(Integer id);

    boolean delete(Integer id);

    List<Action> getFunction(String itemNo, Integer userId);

    List<String> getFunctionStr(String itemNo, Integer userId);

    int updateUserInfoBatch();

    String getIndexMenu(List<Action> actionIndex);

    User getByCardNum(String cardNum);

}

