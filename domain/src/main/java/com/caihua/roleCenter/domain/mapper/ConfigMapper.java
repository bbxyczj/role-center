package com.caihua.roleCenter.domain.mapper;

import com.caihua.roleCenter.model.dto.Config;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * linggan on 2018/5/9
 * 配置文件模块
 */
public interface ConfigMapper {

    @Select("select keyword,value,comments from rc_config where keyword= #{keyword}")
    Config selectByKeyword(String keyword);

    List<Config> select(Config config);

    @Insert(" insert into rc_config (keyword,value,comments)values(#{keyword},#{value},#{comments})")
    int insert(Config config);

    int update(Config config);

    @Delete("delete from rc_config where keyword = #{keyword}")
    int delete(String keyword);

    @Select("select count(keyword) from rc_config where keyword= #{keyword}")
    int count(String keyword);
}
