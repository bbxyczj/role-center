package com.caihua.roleCenter.domain.mapper;

import com.caihua.roleCenter.model.dto.UserRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * created by zhengliu on 2018/6/28
 */
public interface UserRoleMapper {
    @Insert("insert into rc_user_role (userId,roleId) values(#{userId},#{roleId})")
    int insert(UserRole userRole);

    List<UserRole> select(UserRole userRole);

    int count(UserRole userRole);

    int delete(@Param("roleId") Integer roleId, @Param("userId") Integer userId);
}
