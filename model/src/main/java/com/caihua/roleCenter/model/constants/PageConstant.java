package com.caihua.roleCenter.model.constants;

/**
 * linggan on 2018/1/24
 * <p>
 * 分页常量
 */
public interface PageConstant {

    //默认每页数量
    Integer PAGE_SIZE = 10;

    //默认页码
    Integer PAGE_NUM = 1;

    /**
     * 是否分页
     */
    class IsPage {

        //默认打开分页
        public static final int OPEN = 1;

        //不分页
        public static final int CLOSE = 2;

    }

}
