package com.caihua.roleCenter.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * linggan on 2018/5/30
 */
public class Md5Util {

    /**
     * md5 16位
     * @param str
     * @return
     */
    public final static String md5To16(String str){

        String md5 = DigestUtils.md5Hex(str);

        return md5.substring(8,24);

    }

    public static void main(String[] args) {
        String s = md5To16("123456");
        System.out.println(s);
    }
}
