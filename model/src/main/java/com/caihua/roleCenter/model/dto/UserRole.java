package com.caihua.roleCenter.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * created by zhengliu on 2018/6/28
 */
@Data
@ApiModel
public class UserRole extends BaseModel{
    @ApiModelProperty(value = "用户id",required = true)
    private int userId;
    @ApiModelProperty(value = "角色id",required = true)
    private int roleId;
}
