package com.caihua.roleCenter.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author XE组-陈正健
 * @version 1.0
 * @date 2021/8/16 14:54
 */
@Data
public class UserLoginRequest {

    @ApiModelProperty(value = "卡号")
    @NotBlank(message = "卡号不能为空")
    private String cardNum;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "验证码")
    @NotBlank(message = "验证码不能为空")
    private String code;


}
