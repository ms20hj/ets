package com.cms.ets.web.annotation;

import java.lang.annotation.*;

/**
 * 操作日志注解
 * @date 2019年11月15日14:17:32
 * @author Cms
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.PARAMETER })
@Documented
public @interface OperationLog {

    /**
     * 操作模块名称
     * 如：应用管理、系统管理
     * @return String
     */
    String moduleName() default "";

    /**
     * 菜单
     * @return 菜单名
     */
    String menuName() default "";

    /**
     * 操作类型
     * 如：增删改查
     * @return String
     */
    String action() default "";


}
