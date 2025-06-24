package com.cycas.flowabledemo.security;

/**
 * @author xin.na
 * @since 2025/6/24 14:15
 */
public class TenantContextHolder {

    private static final ThreadLocal<String> context = new ThreadLocal<>();

    public static void setTenantId(String tenantId) {
        context.set(tenantId);
    }

    public static String getTenantId() {
        return context.get();
    }

    public static void clear() {
        context.remove();
    }
}
