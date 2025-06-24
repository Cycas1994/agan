package com.cycas.flowabledemo.common.exception;

/**
 * @author xin.na
 * @since 2025/6/24 14:28
 */
public class UnauthorizedException extends BizException {

    public UnauthorizedException(String message) {
        super("401", message);
    }

}
