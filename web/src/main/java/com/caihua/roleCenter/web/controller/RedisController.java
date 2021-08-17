package com.caihua.roleCenter.web.controller;

import com.caihua.roleCenter.model.result.Result;
import com.caihua.roleCenter.web.annotation.NotLogin;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhengliu
 * @createTime 2018/8/13
 */
@RestController
@RequestMapping("redis")
public class RedisController extends BaseController {


    @Resource
    private RedisTemplate redisTemplate;


    @GetMapping("get")
    @NotLogin
    public Result get(String key){
        return new Result(redisTemplate.opsForValue().get(key));
    }
    @NotLogin
    @GetMapping("set")
    public Result set(String key,String value){
        redisTemplate.opsForValue().set(key,value);
        return new Result();
    }
    @NotLogin
    @GetMapping("remove")
    public Result remove(String key){
        return new Result(redisTemplate.delete(key));
    }
}
