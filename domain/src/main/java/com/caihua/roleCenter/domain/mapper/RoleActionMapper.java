package com.caihua.roleCenter.domain.mapper;

import com.caihua.roleCenter.model.dto.RoleAction;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

/**
 * created by zhengliu on 2018/6/29
 */
public interface RoleActionMapper {
    @Insert("insert into rc_role_action (roleId,actionId) values(#{roleId},#{actionId})")
    int insert(RoleAction roleAction);

    int count(RoleAction roleAction);

    int delete(RoleAction roleAction);

    List<Integer> selectByUserId(Integer userId);
}
