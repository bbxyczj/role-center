package com.caihua.roleCenter.model.dto;

import lombok.Data;

/**
 * linggan on 2018/6/27
 */
@Data
public class Config extends BaseModel{

    //key
    protected String keyword;

    //val
    protected String value;

    //说明
    protected String comments;
}
