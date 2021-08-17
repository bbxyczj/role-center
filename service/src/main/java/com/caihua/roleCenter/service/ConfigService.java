package com.caihua.roleCenter.service;


import com.caihua.roleCenter.model.dto.Config;

import java.util.List;

/**
 * linggan on 2018/5/10
 * 配置文件模块
 */
public interface ConfigService {

    List<Config> queryByPage(Config config);

    Config queryByKeyword(String keyword);

    String getStr(String keyword);

    Integer getInt(String keyword);

    boolean edit(Config config);

    boolean remove(String keyword);

    boolean add(Config config);

    int total(String keyword);

}
