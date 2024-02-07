package com.cycas.limit.limiter.core;

import com.cycas.limit.model.Result;
import com.cycas.limit.model.Rule;

public interface RateLimiter {

    Result isAllowed(Rule rule);

}
