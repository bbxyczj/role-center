package com.caihua.roleCenter.common.utils;


import com.caihua.roleCenter.common.exception.RcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashSet;
import java.util.Set;


/**
 * @author XE组-陈正健
 * @version 1.0
 * @date 2021/8/10 17:56
 */
public class ValidateFilter {

    private static final Logger log = LoggerFactory.getLogger(ValidateFilter.class);

    public static final String VALIDATE_DEFAULT = "DEFAULT";

    public static final String VALIDATE_CREATE = "CREATE";

    public static final String VALIDATE_UPDATE = "UPDATE";

    public static final String VALIDATE_DEL = "DELETE";

    public static final String VALIDATE_ERROR = "validate error :{}";

    private static Validator sValidator=Validation.buildDefaultValidatorFactory().getValidator();


    /**
     * 校验数据 获取过滤值
     *
     * @param obj  数据
     * @param type 类型
     * @param <T>  泛型
     * @return 校验结果 抛出异常结果
     */
    public static <T> void getFilterMessage(T obj, String type) {
        Set<ConstraintViolation<T>> validate = new HashSet<>();
        Set<String> messageList = new HashSet<>();
        switch (type) {
            case VALIDATE_DEFAULT:
                validate = sValidator.validate(obj, Default.class);
                break;
            case VALIDATE_CREATE:
                validate = sValidator.validate(obj, GroupType.Create.class);
                break;
            case VALIDATE_UPDATE:
                validate = sValidator.validate(obj, GroupType.Update.class);
                break;
            case VALIDATE_DEL:
                validate = sValidator.validate(obj, GroupType.Delete.class);
                break;
            default:
                validate = sValidator.validate(obj,Default.class);
                break;
        }
        if (!validate.isEmpty()) {
            for (ConstraintViolation<T> content : validate) {
                log.info(VALIDATE_ERROR, content.getMessage());
                messageList.add(content.getMessage());
            }
            throw new RcException("参数校验异常："+messageList.toString());
        }
    }



    public static <T> void getFilterMessage(T obj) {
        Set<ConstraintViolation<T>> validate = sValidator.validate(obj,Default.class);
        Set<String> messageList = new HashSet<>();
        if (!validate.isEmpty()) {
            for (ConstraintViolation<T> content : validate) {
                log.info(VALIDATE_ERROR, content.getMessage());
                messageList.add(content.getMessage());
            }
            throw new RcException("参数校验异常："+messageList.toString());
        }
    }
}
