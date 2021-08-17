package com.caihua.roleCenter.domain.mapper;

import com.caihua.roleCenter.model.dto.Item;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * created by zhengliu on 2018/6/29
 */
public interface ItemMapper {

    List<Item> select(Item model);

    @Select("select id,name,no,status,created,updated from rc_item where id=#{id}")
    Item getById(Integer id);

    int update(Item item);

    @Insert("insert into rc_item (name,no,status,created,updated) values(#{name},#{no},#{status},#{created},#{updated})")
    int insert(Item item);

    @Delete("delete from rc_item where id=#{id}")
    int delete(Integer id);

    @Select("select id,name,no,status,created,updated from rc_item where no=#{no} and status=1")
    Item getByNo(String no);
}
