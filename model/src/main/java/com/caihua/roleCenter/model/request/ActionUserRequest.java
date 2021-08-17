package com.caihua.roleCenter.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhengliu
 * @createTime 2018/8/13
 */
@ApiModel
@Data
public class ActionUserRequest implements Serializable{

    @ApiModelProperty("项目编号")
    private String itemNo;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("接口地址，判断单个接口权限传值")
    private String url;

    @ApiModelProperty("获取导航拦 传 1 ，获取全部 传 0  获取非导航栏 传 2 ")
    private Integer isIndex;

}
