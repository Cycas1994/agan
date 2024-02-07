package com.cycas.limit.model.annotation;

import com.cycas.limit.model.enums.Mode;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {

    //===================== 公共参数 ============================

    Mode mode() default Mode.FIXED_WINDOW;

    /**
     * 时间窗口模式表示时间窗口内请求的数量
     * 令牌桶模式表示每秒生成令牌的数量
     * @return
     */
    int rate();

    /**
     * 获取key
     * @return
     */
    String[] keys() default {};

    /**
     * 限流后的自定义回退后的拒绝逻辑
     * @return
     */
    String fallbackFunction() default "";

    /**
     * 自定义业务key的Function
     * @return
     */
    String customKeyFunction() default "";

    /**
     * 时间窗口流量数量表达式
     * @return
     */
    String rateExpression() default "";

    //===================== 时间窗口模式参数 ============================

    /**
     * 时间窗口，最小单位秒，如 2s，2h，2d
     * @return
     */
    String rateInterval() default "1s";

    //===================== 令牌桶模式参数 ============================

    /**
     * 令牌桶容量
     * @return
     */
    int bucketCapacity() default 1;

    /**
     * 令牌桶容量表达式
     * @return
     */
    String bucketCapacityExpression() default "";

    /**
     * 每次获取多少令牌
     * @return
     */
    int requestTokens() default 1;

}
