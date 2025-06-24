package com.cycas.flowabledemo.security;

import com.cycas.flowabledemo.common.exception.UnauthorizedException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xin.na
 * @since 2025/6/24 14:02
 */
@Component
public class SignatureAuthInterceptor implements HandlerInterceptor {

    private static final long TIMEOUT_MS = 5 * 60 * 1000; // 5分钟过期

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tenantId = request.getHeader("X-Tenant-Id");
        String timestamp = request.getHeader("X-Timestamp");
        String nonce = request.getHeader("X-Nonce");
        String signature = request.getHeader("X-Signature");
        if (StringUtils.isAnyBlank(tenantId, timestamp, nonce, signature)) {
            throw new UnauthorizedException("签名参数缺失");
        }

        long ts = Long.parseLong(timestamp);
        if (Math.abs(System.currentTimeMillis() - ts) > TIMEOUT_MS) {
            throw new UnauthorizedException("请求过期");
        }

        String secret = "secret";
        if (StringUtils.isBlank( secret)) {
            throw new UnauthorizedException("签名密钥不存在");
        }

        String expected = DigestUtils.sha256Hex(tenantId + timestamp + nonce + secret);
        if (!expected.equals(signature)) {
            throw new UnauthorizedException("签名验证失败");
        }

        // 设置租户上下文（可用于流程接口内获取）
        TenantContextHolder.setTenantId(tenantId);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        TenantContextHolder.clear(); // 避免线程泄漏
    }
}
