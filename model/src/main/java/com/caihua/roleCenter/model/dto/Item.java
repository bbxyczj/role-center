package com.caihua.roleCenter.model.dto;

import lombok.Data;

/**
 * created by zhengliu on 2018/6/29
 */
@Data
public class Item extends BaseModel{
    private Integer id;

    private String name;

    private String no;

    private int status;
}
