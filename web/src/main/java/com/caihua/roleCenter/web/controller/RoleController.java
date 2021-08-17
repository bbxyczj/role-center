package com.caihua.roleCenter.web.controller;

import com.caihua.roleCenter.model.dto.Role;
import com.caihua.roleCenter.model.dto.RoleAction;
import com.caihua.roleCenter.model.result.DwzResult;
import com.caihua.roleCenter.service.RoleActionService;
import com.caihua.roleCenter.service.RoleService;
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
 * created by zhengliu on 2018/6/28
 */
@RestController
@RequestMapping("role")
public class RoleController extends BaseController {

    @Resource
    private RoleService roleService;

    @Resource
    private UserService userService;

    @Resource
    private RoleActionService roleActionService;



    @RequestMapping(value = "list",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView list(Role model,Integer userId){
        ModelAndView modelAndView = new ModelAndView("/role/list");
        PageHelper.startPage(model.getPageNum(),model.getNumPerPage());
        List<Role> select = roleService.select(model);
        modelAndView.addObject("pageInfo",new PageInfo<>(select));
        modelAndView.addObject("model",model);
        modelAndView.addObject("userId",userId);
        return modelAndView;
    }

    @GetMapping("relationRoleList")
    public ModelAndView relationRoleList(Role model,Integer userId){
        ModelAndView modelAndView = new ModelAndView("/role/relationRole");
        List<Role> select = roleService.findByUserId(userId);
        modelAndView.addObject("list",select);
        modelAndView.addObject("user",userService.getById(userId));
        return modelAndView;
    }

    @GetMapping("addRoleToUserList")
    public ModelAndView addRoleToUserList(Role model,Integer userId){
        ModelAndView modelAndView = new ModelAndView("/role/addRoleToUser");
        List<Role> select = roleService.select(model);
        modelAndView.addObject("model",model);
        modelAndView.addObject("list",select);
        modelAndView.addObject("userId",userId);
        return modelAndView;
    }

    @PostMapping("doAddAction")
    public DwzResult doAddRole(Integer roleId,String[] ids) {
        boolean flag=roleActionService.insert(roleId,ids);
        if(flag){
            return successDel("role/list");
        }
        return fail();
    }


    @GetMapping("addOrUpdate")
    public ModelAndView addOrUpdate(Integer id){
        ModelAndView modelAndView = new ModelAndView("/role/addOrUpdate");
        modelAndView.addObject("role",roleService.getById(id));
        return modelAndView;
    }

    @PostMapping("doAddOrUpdate")
    public DwzResult doAddOrUpdate(Role role){
        boolean flag=roleService.doAddOrUpdate(role);
        if(flag){
            return success("role/list");
        }
        return fail();
    }

    @GetMapping("delete")
    public DwzResult delete(Integer id){
        boolean flag=roleService.delete(id);
        if(flag){
            return successDel("role/list");
        }
        return fail();
    }

    @GetMapping("deleteRelationAction")
    public DwzResult deleteRelationAction(RoleAction roleAction){
        boolean flag=roleActionService.deleteRelationAction(roleAction);
        if(flag){
            return successDel("role/list");
        }
        return fail();
    }


}
