package com.cycas.flowabledemo.common.exception;

/**
 * @author xin.na
 * @since 2025/6/24 14:24
 */
public class BizException extends RuntimeException {
    private final String code;
    private final String message;

    public BizException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
