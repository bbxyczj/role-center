package com.caihua.roleCenter.model.dto;

import com.caihua.roleCenter.model.constants.PageConstant;
import lombok.Data;

import java.io.Serializable;

/**
 * linggan on 2018/1/15
 * 基础参数
 */
@Data
public class BaseModel implements Serializable {

    private static final long serialVersionUID = -8809124461542777585L;

    //是否分页1.分页，2.不分页
    protected transient Integer isPage = PageConstant.IsPage.OPEN;

    //每页显示大小
    protected transient Integer numPerPage = PageConstant.PAGE_SIZE;

    /**
     * 当前页码
     */
    protected transient Integer pageNum = PageConstant.PAGE_NUM;

    //列名，查询数据库时，返回字段名
    protected transient String columns;

    //创建时间
    protected  Long created;

    //修改时间
    protected Long updated;

    //是否删除 （1未删，2已删）//默认查全部未删
    protected Integer isDel;

    //开始创建时间
    protected transient Long startCreated;

    //创建结束时间
    protected transient Long endCreated;

    //开始修改时间
    protected transient Long startUpdated;

    //结束修改时间
    protected transient Long endUpdated;


}
