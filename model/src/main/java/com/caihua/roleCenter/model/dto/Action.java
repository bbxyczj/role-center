package com.caihua.roleCenter.model.dto;

import lombok.Data;

import java.util.List;

/**
 * created by zhengliu on 2018/6/29
 */
@Data
public class Action extends BaseModel{
    private Integer id;
    private Integer pid;
    private String itemName;
    private Integer itemId;
    private String name;
    private String url;
    private Integer isIndex;
    private Integer sort;

    private List<Action> actionList;
}
