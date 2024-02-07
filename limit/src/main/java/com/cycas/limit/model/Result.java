package com.cycas.limit.model;

/**
 * @author xin.na
 * @since 2024/2/6 14:12
 */
public class Result {

    private boolean isAllow;
    private long extra;

    public Result(boolean isAllow) {
        this.isAllow = isAllow;
    }

    public Result(boolean isAllow, Long extra) {
        this.isAllow = isAllow;
        this.extra = extra;
    }

    public boolean isAllow() {
        return isAllow;
    }

    public void setAllow(boolean allow) {
        isAllow = allow;
    }

    public Long getExtra() {
        return extra;
    }

    public void setExtra(Long extra) {
        this.extra = extra;
    }
}
