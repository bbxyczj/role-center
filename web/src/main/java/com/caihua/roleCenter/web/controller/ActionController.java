package com.caihua.roleCenter.web.controller;

import com.caihua.roleCenter.model.dto.Action;
import com.caihua.roleCenter.model.dto.Item;
import com.caihua.roleCenter.model.result.DwzResult;
import com.caihua.roleCenter.service.ActionService;
import com.caihua.roleCenter.service.ItemService;
import com.caihua.roleCenter.service.RoleService;
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
 * created by zhengliu on 2018/6/29
 */
@RestController
@RequestMapping("action")
public class ActionController extends BaseController {

    @Resource
    private ActionService actionService;

    @Resource
    private ItemService itemService;

    @Resource
    private RoleService roleService;


    @RequestMapping(value = "list",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView list(Action model){
        ModelAndView modelAndView=new ModelAndView("action/list");
        PageHelper.startPage(model.getPageNum(),model.getNumPerPage());
        List<Action> select = actionService.select(model);
        modelAndView.addObject("pageInfo",new PageInfo<>(select));
        modelAndView.addObject("model",model);
        return modelAndView;
    }

    @RequestMapping("addActionToRoleList")
    public ModelAndView addActionToRoleList(Action model,Integer roleId){
        ModelAndView modelAndView = new ModelAndView("/action/addActionToRole");
        List<Action> select = actionService.select(model);
        modelAndView.addObject("model",model);
        modelAndView.addObject("list",select);
        modelAndView.addObject("roleId",roleId);
        return modelAndView;
    }

    @GetMapping("relationActionList")
    public ModelAndView relationRoleList(Action model, Integer roleId){
        ModelAndView modelAndView = new ModelAndView("/action/relationAction");
        List<Action> select = actionService.findByRoleId(roleId);
        modelAndView.addObject("list",select);
        modelAndView.addObject("role",roleService.getById(roleId));
        return modelAndView;
    }


    @GetMapping("addOrUpdate")
    public ModelAndView addOrUpdate(Integer id,Integer itemId){
        ModelAndView modelAndView = new ModelAndView("action/addOrUpdate");
        Item item = new Item();
        item.setStatus(1);
        Action action = actionService.getById(id);
        if(itemId==null&&action!=null){
            itemId=action.getItemId();
        }
        modelAndView.addObject("itemList",itemService.select(item));
        modelAndView.addObject("action",action);
        modelAndView.addObject("myItem",itemService.getById(itemId));
        return modelAndView;
    }

    @PostMapping("doAddOrUpdate")
    public DwzResult doAddOrUpdate(Action item) {

        boolean flag = actionService.doAddOrUpdate(item);

        if (flag) {
            return success("action/list");
        }
        return fail();
    }


    @GetMapping("delete")
    public DwzResult delete(Integer id) {

        boolean flag = actionService.delete(id);

        if (flag) {
            return successDel("action/list");
        }

        return fail();
    }
    @GetMapping("deleteItemAction")
    public DwzResult deleteItemAction(Integer[] ids) {
        for (Integer id:ids) {
            actionService.delete(id);
        }
        return success("action/list");
    }
}
