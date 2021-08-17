package com.caihua.roleCenter.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * created by zhengliu on 2018/6/27
 */
@Data
@ApiModel("用户")
public class User extends BaseModel{
    @ApiModelProperty(value = "id,以业务编号+业务自己用户id传")
    private Integer id;


    @ApiModelProperty(value = "0 冒险元素 1 萍乡管家")
    private String company;

    @ApiModelProperty(value = "工号")
    private String job_number;

    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    @ApiModelProperty(value = "用户名")
    private String nickname;

    @ApiModelProperty(value = "卡号")
    private String cardNum;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "密码")
    private String password;
}
