package com.caihua.roleCenter.web.controller;

import com.caihua.roleCenter.model.dto.Config;
import com.caihua.roleCenter.model.result.DwzResult;
import com.caihua.roleCenter.service.ConfigService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * linggan on 2018/1/11
 */
@RestController
@RequestMapping("config")
public class ConfigController extends BaseController {

    @Autowired
    private ConfigService configService;

    @PostMapping("remove")
    public DwzResult remove(String keyword) {

        boolean flag = configService.remove(keyword);

        if (flag) {
            return successDel("config/list");
        }

        return fail();

    }

    /**
     * 检查当前keyword 是否存在
     *
     * @param keyword
     * @return
     */
    @GetMapping("checkKeyword")
    public boolean checkKeyword(String keyword) {

        return configService.total(keyword) > 0 ? false : true;
    }

    @GetMapping("edit")
    public ModelAndView edit(String keyword) {
        ModelAndView mav = new ModelAndView();
        Config config = configService.queryByKeyword(keyword);
        mav.addObject("model", config);
        mav.setViewName("config/edit");
        return mav;
    }

    @PostMapping("doEdit")
    public DwzResult doEdit(Config config) {

        boolean flag = configService.edit(config);

        if (flag) {
            return success("config/list");
        }

        return fail();
    }

    @GetMapping("add")
    public ModelAndView add() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("config/add");
        return mav;
    }

    @PostMapping("doAdd")
    public DwzResult doAdd(Config config) {

        boolean flag = configService.add(config);

        if (flag) {
            return success("config/list");
        }

        return fail();
    }

    @RequestMapping(value = "list",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView list(Config model) {

        ModelAndView mav = new ModelAndView();

        PageHelper.startPage(model.getPageNum(), model.getNumPerPage());
        PageInfo pageInfo = new PageInfo(configService.queryByPage(model));

        mav.addObject("pageInfo", pageInfo);
        mav.addObject("model", model);
        mav.setViewName("config/list");

        return mav;

    }


}
