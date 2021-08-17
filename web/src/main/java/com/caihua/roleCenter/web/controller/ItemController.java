package com.caihua.roleCenter.web.controller;

import com.alibaba.fastjson.JSON;
import com.caihua.roleCenter.model.dto.Action;
import com.caihua.roleCenter.model.dto.Item;
import com.caihua.roleCenter.model.request.ActionUserRequest;
import com.caihua.roleCenter.model.result.DwzResult;
import com.caihua.roleCenter.model.result.Result;
import com.caihua.roleCenter.model.vo.TreeActionVo;
import com.caihua.roleCenter.service.ActionService;
import com.caihua.roleCenter.service.ItemService;
import com.caihua.roleCenter.web.annotation.NotLogin;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("item")
public class ItemController extends BaseController {

    @Resource
    private ItemService itemService;

    @Resource
    private ActionService actionService;


    @RequestMapping(value = "list",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView list(Item model){
        ModelAndView modelAndView=new ModelAndView("item/list");
        PageHelper.startPage(model.getPageNum(),model.getNumPerPage());
        List<Item> select = itemService.select(model);
        modelAndView.addObject("pageInfo",new PageInfo<>(select));
        modelAndView.addObject("model",model);
        return modelAndView;
    }
    @GetMapping("addOrUpdate")
    public ModelAndView addOrUpdate(Integer id){
        ModelAndView modelAndView = new ModelAndView("item/addOrUpdate");
        modelAndView.addObject("item",itemService.getById(id));
        return modelAndView;
    }

    @PostMapping("doAddOrUpdate")
    public DwzResult doAddOrUpdate(Item item) {

        boolean flag = itemService.doAddOrUpdate(item);

        if (flag) {
            return success("item/list");
        }

        return fail();
    }

    @GetMapping("relationActionList")
    public ModelAndView relationActionList(Integer itemId){
        ModelAndView modelAndView = new ModelAndView("item/relationAction");
        // TODO: 2018/6/29 返回树形结构
        TreeActionVo treeActionVo=new TreeActionVo();
        treeActionVo.setItemId(itemId);
        modelAndView.addObject("actions",JSON.toJSONString(actionService.getActionByItemId(treeActionVo)));
        modelAndView.addObject("itemId",itemId);
        return modelAndView;
    }

    @NotLogin
    @GetMapping("getItemActionJson")
    public List<Action> getItemActionJson(Integer itemId){
        TreeActionVo treeActionVo=new TreeActionVo();
        treeActionVo.setItemId(itemId);
        return actionService.getActionByItemId(treeActionVo);
    }

    @NotLogin
    @PostMapping("setItemActionJson")
    public Result setItemActionJson(@RequestBody List<Action> list, Integer itemId){
        return new Result(actionService.setItemActionJson(list,itemId,0));
    }


    @GetMapping("treeActionBack")
    public ModelAndView treeActionBack(Integer itemId){
        ModelAndView modelAndView = new ModelAndView("action/treeActionBack");
        // TODO: 2018/6/29 返回树形结构
        TreeActionVo treeActionVo=new TreeActionVo();
        treeActionVo.setItemId(itemId);
        modelAndView.addObject("actions", JSON.toJSONString(actionService.getActionByItemId(treeActionVo)));
        modelAndView.addObject("item",itemService.getById(itemId));
        return modelAndView;
    }

    @GetMapping("delete")
    public DwzResult delete(Integer id) {

        boolean flag = itemService.delete(id);

        if (flag) {
            return successDel("item/list");
        }

        return fail();
    }

    @GetMapping("getActionIndex")
    @NotLogin
    public Result getActionIndex(ActionUserRequest request){
        List<Action> actionIndex = itemService.getActionIndex(request);
        return  new Result(actionIndex);
    }

}
