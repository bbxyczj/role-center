package com.caihua.roleCenter.domain.mapper;

import com.caihua.roleCenter.model.dto.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * created by zhengliu on 2018/6/28
 */
public interface RoleMapper {
    List<Role> select(Role model);

    @Select("select id,name,note,updated,created from rc_role where id=#{id}")
    Role getById(Integer id);

    int insert(Role role);

    int update(Role role);

    @Delete("delete from rc_role where id=#{id}")
    int delete(Integer id);

    List<Role> findByUserId(Integer userId);
}
