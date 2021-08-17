package com.caihua.roleCenter.domain.mapper;


import com.caihua.roleCenter.model.dto.Action;
import com.caihua.roleCenter.model.request.ActionUserRequest;
import com.caihua.roleCenter.model.vo.TreeActionVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * created by zhengliu on 2018/6/29
 */
public interface ActionMapper {
    int update(Action action);

    List<Action> select(Action model);

    @Select("select id,pid,itemId,itemName,name,url,isIndex,sort,updated,created from rc_action where id=#{id}")
    Action getById(Integer id);

    int insert(Action action);

    @Delete("delete from rc_action where id=#{id}")
    int delete(Integer id);

    List<Action> findByRoleId(Integer roleId);

    List<Action> getActionVo(TreeActionVo actionVo);

    Action getAction(ActionUserRequest request);
}
