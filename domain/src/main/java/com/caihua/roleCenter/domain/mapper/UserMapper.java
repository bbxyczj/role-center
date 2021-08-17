package com.caihua.roleCenter.domain.mapper;

import com.caihua.roleCenter.model.dto.Action;
import com.caihua.roleCenter.model.dto.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * created by zhengliu on 2018/6/27
 */
public interface UserMapper {

    List<User>  select(User user);

    int update(User user);

    int insert(User user);

    @Select("select id,email,nickname,cardNum,password,status,updated,created from rc_user where id=#{id}")
    User getById(int id);

    @Delete("delete from rc_user where id=#{id}")
    int delete(Integer id);

    @Select("select id,email,nickname,cardNum,password,status,updated,created from rc_user where cardNum=#{cardNum}")
    User getByCardNum(String cardNum);

    List<Action> getFunction(@Param("itemNo") String itemNo, @Param("userId") Integer userId);

    List<String> getFunctionStr(@Param("itemNo") String itemNo, @Param("userId") Integer userId);
}
