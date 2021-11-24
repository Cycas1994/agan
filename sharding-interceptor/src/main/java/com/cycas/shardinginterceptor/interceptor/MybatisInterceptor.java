package com.cycas.shardinginterceptor.interceptor;


import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

@Component
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
})
public class MybatisInterceptor implements Interceptor {

    /**
     * 服务名称
     */
    private String applicationName;

    /**
     * 标志位
     */
    private boolean hcUpdateEventEnable;

    /**
     * hc表新增修改需要通知的表
     */
    private Set<String> hcUpdateTables;


    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        if (hcUpdateEventEnable) {

        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
        this.applicationName = properties.getProperty("applicationName", "");
        String enable = properties.getProperty("hcUpdateEventEnable", "false");
        this.hcUpdateEventEnable = (Boolean.valueOf(enable));
        String hcUpdateTables = properties.getProperty("hcUpdateTables");
        this.hcUpdateTables = new HashSet<>(Arrays.asList(StringUtils.split(hcUpdateTables,",")));
    }
}
