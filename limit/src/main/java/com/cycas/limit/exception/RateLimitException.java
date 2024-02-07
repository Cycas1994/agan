package com.cycas.limit.exception;

import com.cycas.limit.model.enums.Mode;

/**
 * @author xin.na
 * @since 2024/2/6 16:47
 */
public class RateLimitException extends RuntimeException {

    private final long extra;
    private final Mode mode;

    public RateLimitException(String message, long extra, Mode mode) {
        super(message);
        this.extra = extra;
        this.mode = mode;
    }

    public long getExtra() {
        return extra;
    }

    public Mode getMode() {
        return mode;
    }
}
