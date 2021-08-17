package com.caihua.roleCenter.web.controller;

import com.caihua.roleCenter.model.result.Result;
import com.caihua.roleCenter.service.UserService;
import com.caihua.roleCenter.web.annotation.NotLogin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhengliu
 * @createTime 2018/8/17
 */
@RestController
@RequestMapping("timer")
public class TimerController {

    @Resource
    private UserService userService;


    @GetMapping("updateUserInfoBatch")
    @NotLogin
    public Result updateUserInfoBatch(){
        return new Result(userService.updateUserInfoBatch());
    }
}
