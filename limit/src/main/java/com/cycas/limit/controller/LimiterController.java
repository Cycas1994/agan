package com.cycas.limit.controller;

import com.cycas.limit.model.annotation.RateLimit;
import com.cycas.limit.model.enums.Mode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xin.na
 * @since 2024/2/4 9:15
 */
@Slf4j
@RestController
@RequestMapping("/limiter")
public class LimiterController {

    @GetMapping("/fixed")
    @RateLimit(mode = Mode.FIXED_WINDOW, rate = 2, rateInterval = "10s")
    public String fixed() {
        return "fixed";
    }

    @GetMapping("/sliding")
    @RateLimit(mode = Mode.SLIDING_WINDOW, rate = 2, rateInterval = "10s")
    public String sliding() {
        return "sliding";
    }

    @GetMapping("/token")
    @RateLimit(mode = Mode.TOKEN_BUCKET, rate = 5, bucketCapacity = 10)
    public String token() {
        return "token";
    }
}
