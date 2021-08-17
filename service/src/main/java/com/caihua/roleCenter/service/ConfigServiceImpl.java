package com.caihua.roleCenter.service;

import com.caihua.roleCenter.domain.mapper.ConfigMapper;
import com.caihua.roleCenter.model.dto.Config;
import com.caihua.roleCenter.common.exception.Message;
import com.caihua.roleCenter.common.exception.RcException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * linggan on 2018/5/10
 * 配置文件模块
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigMapper configMapper;

    @Override
    public List<Config> queryByPage(Config config) {

        return configMapper.select(config);
    }

    @Override
    public Config queryByKeyword(String keyword)  {

        if (StringUtils.isEmpty(keyword)) {

            throw new RcException(Message.E4000, "必要参数keyword为空");
        }

        return configMapper.selectByKeyword(keyword);
    }

    @Override
    public String getStr(String keyword)  {

        Config config = queryByKeyword(keyword);

        if (config != null) {

            return config.getValue();
        }


        return null;
    }

    @Override
    public Integer getInt(String keyword)  {

        Config config = queryByKeyword(keyword);

        if (config != null) {

            String val = config.getValue();

            if (StringUtils.isNotEmpty(val)) {

                return Integer.parseInt(val);
            }
        }

        return null;
    }

    @Override
    public boolean edit(Config config)  {
        config.setUpdated(System.currentTimeMillis());
        check(config);

        return configMapper.update(config) > 0 ? true : false;
    }

    @Override
    public boolean remove(String keyword)  {

        if (StringUtils.isEmpty(keyword)) {

            throw new RcException(Message.E4000, "必要参数keyword为空");
        }

        return configMapper.delete(keyword) > 0 ? true : false;
    }

    @Override
    public boolean add(Config config)  {
        config.setCreated(System.currentTimeMillis());
        check(config);

       if( total(config.getKeyword()) > 0){

           throw new RcException(Message.E4000, "当前keyword参数名已存在");
       }

        return configMapper.insert(config) > 0 ? true : false;
    }

    @Override
    public int total(String keyword)  {

        if (StringUtils.isEmpty(keyword)) {

            throw new RcException(Message.E4000, "必要参数为空");
        }

        return configMapper.count(keyword);
    }

    private void check(Config config)  {

        if (config == null) {

            throw new RcException(Message.E4000, "必要参数为空");
        }

        if (StringUtils.isEmpty(config.getKeyword())
                || StringUtils.isEmpty(config.getValue())
                || StringUtils.isEmpty(config.getComments())) {

            throw new RcException(Message.E4000, "必要参数为空");
        }

    }
}
