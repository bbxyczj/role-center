package com.caihua.roleCenter.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.caihua.roleCenter.common.utils.HttpUtil;
import com.caihua.roleCenter.common.utils.Md5Util;
import com.caihua.roleCenter.domain.mapper.UserMapper;
import com.caihua.roleCenter.model.constants.Constants;
import com.caihua.roleCenter.model.dto.Action;
import com.caihua.roleCenter.model.dto.User;
import com.caihua.roleCenter.common.exception.RcException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * created by zhengliu on 2018/6/27
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Resource
    private ConfigService configService;

    @Resource
    private UserRoleService userRoleService;


    @Override
    public List<User> select(User model) {
        return userMapper.select(model);
    }

    @Override
    public boolean doAddOrUpdate(User user) {
        boolean flag;
        user.setPassword(Md5Util.md5To16(user.getPassword()));
        if(user.getId()!=null&&user.getId()>0){
            user.setUpdated(System.currentTimeMillis());
            User oldUser = userMapper.getById(user.getId());
            if(oldUser!=null){
                //修改
                if(user.getStatus().equals(oldUser.getStatus())&&
                        user.getCardNum().equals(oldUser.getCardNum())&&
                        user.getNickname().equals(oldUser.getNickname())&&
                        user.getEmail().equals(oldUser.getEmail())&&
                        user.getPassword().equals(oldUser.getPassword())){
                    //数据未变化，不更新
                    return false;
                }
                flag= userMapper.update(user)>0;
            }else {
                throw new RcException("查无此用户");
            }
        } else {
            //新增
            user.setCreated(System.currentTimeMillis());
            flag= userMapper.insert(user)>0;
        }
        if(user.getRoleId()!=null){
            String[] ids=new String[]{user.getRoleId().toString()};
            userRoleService.insert(user.getId(),ids);
        }
        return flag;
    }

    @Override
    public User getById(Integer id) {
        if(id!=null&&id>0){
            return userMapper.getById(id);
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return userMapper.delete(id)>0;
    }

    @Override
    public List<Action> getFunction(String itemNo, Integer userId) {
        //查询到用户到角色，通过角色查询用户在某项目的所以权限
        return userMapper.getFunction(itemNo,userId);
    }

    @Override
    public List<String> getFunctionStr(String itemNo, Integer userId) {
        return userMapper.getFunctionStr(itemNo,userId);
    }

    @Override
    public int updateUserInfoBatch() {
        String s = HttpUtil.sendGet(configService.getStr(Constants.PERSONNEL_URL));
        JSONObject jsonObject = JSON.parseObject(s);
        List<User> userList = JSON.parseArray(jsonObject.getString("data"), User.class);
        Integer i=0;
        for (User user:userList) {
            if(StringUtils.isEmpty(user.getNickname())||user.getId()==null){
                continue;
            }
            if(StringUtils.isEmpty(user.getEmail())){
                if(StringUtils.isNotEmpty(user.getJob_number())){
                    user.setEmail(user.getJob_number());
                }else {
                    user.setEmail(user.getNickname());
                }
            }
            if(StringUtils.isEmpty(user.getCardNum())){
                if(StringUtils.isNotEmpty(user.getJob_number())){
                    user.setCardNum(user.getJob_number());
                }else {
                    user.setCardNum(user.getNickname());
                }
            }
            if(user.getStatus()==null){
                user.setStatus(1);
            }
            boolean b = doAddOrUpdate(user);
            if(b){
                i++;
            }
        }
        return i;
    }



    @Override
    public String getIndexMenu(List<Action> actionIndex) {
        String html="";
        for (Action action:actionIndex) {
            html+="<div class=\"accordionHeader\"><h2><span>Folder</span>"+action.getName()+"</h2></div>";
            html+="<div class=\"accordionContent\"><ul class=\"tree treeFolder\">";
            if(!CollectionUtils.isEmpty(action.getActionList())){
                html+=setMenu(action.getActionList());
            }
            html+="</ul>";
            html+="</div>";
        }

        return html;
    }

    @Override
    public User getByCardNum(String cardNum) {
        return userMapper.getByCardNum(cardNum);
    }

    private String setMenu(List<Action> actions){
        String html="";

        for(Action action:actions){
            html+="<li><a href=\""+action.getUrl() +"\"  target=\"navTab\" rel=\""+ action.getUrl() +"\">"+action.getName()
                    +"</a>";
            if(!CollectionUtils.isEmpty(action.getActionList())){
                html+="<ul>"+setMenu(action.getActionList())+"</ul>";
            }
            html+="</li>";
        }
        return html;
    }
}
