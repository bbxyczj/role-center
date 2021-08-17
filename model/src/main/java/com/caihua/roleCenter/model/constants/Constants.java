package com.caihua.roleCenter.model.constants;

/**
 * linggan on 2018/1/15
 */
public interface Constants {

    /**
     * 人事系统地址
     */
    String PERSONNEL_URL="personnel_url";

    /**
     * 是否删除
     */
    class IsDel {

        //已删
        public static final Integer YES = 2;

        //未删
        public static final Integer NO = 1;

    }

    String USER_INFO="user_info";
    String FUNCTION_INFO="function_info";
    String FUNCTION_INFO_URL="function_info_url";


    String LOGIN_URL = "http://pms.ultimavip.org/login/third";
    String LOGOUT_URL = "http://pms.ultimavip.org/logout";
    String LOGIN_USER = "loginUser";

    String THIS_ITEM_NO="32edc2301c0f74a8";


    String CODE_PREFIX="RC:LOGIN_CODE:";


}
