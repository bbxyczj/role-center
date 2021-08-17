package com.caihua.roleCenter.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @author zhengliu
 * @createTime 2018/8/13
 */
@Data
public class TreeActionVo {

    private Integer pid;
    private Integer itemId;
    private Integer isIndex;
    private List<Integer> actionIds;
}
