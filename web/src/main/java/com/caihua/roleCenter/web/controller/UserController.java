package com.caihua.roleCenter.web.controller;

import com.caihua.roleCenter.model.dto.User;
import com.caihua.roleCenter.model.result.DwzResult;
import com.caihua.roleCenter.service.UserRoleService;
import com.caihua.roleCenter.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * created by zhengliu on 2018/6/27
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @Resource
    private UserRoleService userRoleService;

    @RequestMapping(value = "list",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView list(User model){
        ModelAndView modelAndView=new ModelAndView("user/list");
        PageHelper.startPage(model.getPageNum(),model.getNumPerPage());
        List<User> select = userService.select(model);
        modelAndView.addObject("pageInfo",new PageInfo<>(select));
        modelAndView.addObject("model",model);
        return modelAndView;
    }
    @GetMapping("addOrUpdate")
    public ModelAndView addOrUpdate(Integer id){
        ModelAndView modelAndView = new ModelAndView("user/addOrUpdate");
        modelAndView.addObject("user",userService.getById(id));
        return modelAndView;
    }

    @PostMapping("doAddOrUpdate")
    public DwzResult doAddOrUpdate(User user) {

        boolean flag = userService.doAddOrUpdate(user);

        if (flag) {
            return success("user/list");
        }

        return fail();
    }

    @PostMapping("doAddRole")
    public DwzResult doAddRole(Integer userId,String[] ids) {
        boolean flag=userRoleService.insert(userId,ids);
        if(flag){
            return successDel("user/list");
        }
        return fail();
    }



    @GetMapping("delete")
    public DwzResult delete(Integer id) {

        boolean flag = userService.delete(id);

        if (flag) {
            return successDel("user/list");
        }

        return fail();
    }

    @GetMapping("deleteRelationRole")
    public DwzResult deleteRelationRole(Integer roleId,Integer userId){
        boolean flag=userRoleService.deleteRelationRole(roleId,userId);
        if(flag){
            return successDel("user/list");
        }
        return fail();
    }

}
