package com.caihua.roleCenter.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.caihua.roleCenter.common.exception.RcException;
import com.caihua.roleCenter.common.utils.Md5Util;
import com.caihua.roleCenter.common.utils.ValidateFilter;
import com.caihua.roleCenter.model.constants.Constants;
import com.caihua.roleCenter.model.dto.Action;
import com.caihua.roleCenter.model.dto.User;
import com.caihua.roleCenter.model.request.ActionUserRequest;
import com.caihua.roleCenter.model.request.UserLoginRequest;
import com.caihua.roleCenter.model.result.Result;
import com.caihua.roleCenter.service.ItemService;
import com.caihua.roleCenter.service.UserService;
import com.caihua.roleCenter.web.annotation.NotLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * created by zhengliu on 2018/7/18
 */
@RestController
public class LoginController extends BaseController{

    @Autowired
    private UserService userService;

    @Resource
    private ItemService itemService;

    @Autowired
    private RedisTemplate redisTemplate;



    /**
     * 将人事系统登录信息记录到Session中
     */
    @GetMapping(value = "/")
    @NotLogin
    public ModelAndView goLogin(String errorMessage) {
        return new ModelAndView("login").addObject("errorMessage",errorMessage);
    }

    /**
     * 将人事系统登录信息记录到Session中
     */
    @PostMapping(value = "/userLogin")
    @NotLogin
    public ModelAndView login(UserLoginRequest user, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        try {
            ValidateFilter.getFilterMessage(user);

            //验证码
            Object o = redisTemplate.opsForValue().get(Constants.CODE_PREFIX + session.getId());
            if (!user.getCode().equals(o)) {
                throw new RcException("验证码已失效请刷新页面");
            }
            String id = session.getId();
            User thisUser = userService.getByCardNum(user.getCardNum());
            if (thisUser != null && thisUser.getPassword().equals(Md5Util.md5To16(user.getPassword()))) {
                session.setAttribute(Constants.LOGIN_USER, thisUser);
//            session.setAttribute(Constants.FUNCTION_INFO_URL,userService.getFunctionStr(Constants.THIS_ITEM_NO,thisUser.getId()));
//            //登陆过期时间
                session.setMaxInactiveInterval(30 * 60);
//            redisTemplate.opsForValue().set(Constants.LOGIN_USER+id,thisUser,30*60,TimeUnit.SECONDS);
                redisTemplate.opsForValue().set(Constants.FUNCTION_INFO_URL + id,
                        JSONObject.toJSONString(userService.getFunctionStr(Constants.THIS_ITEM_NO, thisUser.getId())), 30 * 60, TimeUnit.SECONDS);
                modelAndView.setViewName("redirect:/index");
            }else {
                throw new RcException("密码错误");
            }
        } catch (RcException e) {
            modelAndView.setViewName("redirect:/");
            modelAndView.addObject("errorMessage",e.getMsg());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @NotLogin
    public Result logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        User userVo = (User) request.getSession().getAttribute(Constants.LOGIN_USER);
        if (userVo != null) {
            // 注销本地session
            session.removeAttribute(Constants.LOGIN_USER);
//            session.removeAttribute(Constants.FUNCTION_INFO_URL);
            redisTemplate.delete(Constants.FUNCTION_INFO_URL+session.getId());

        }
        response.sendRedirect("/");
        return new Result();
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(HttpSession session) {
//        String id = session.getId();
        User userVo = (User) session.getAttribute(Constants.LOGIN_USER);
//        User userVo = (User) redisTemplate.opsForValue().get(Constants.LOGIN_USER+id);
        ActionUserRequest request=new ActionUserRequest();
        request.setItemNo(Constants.THIS_ITEM_NO);
        request.setUserId(userVo.getId());
        List<Action> actionIndex = itemService.getActionIndex(request);

        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("indexMenu",userService.getIndexMenu(actionIndex));
        return modelAndView;
    }




}
