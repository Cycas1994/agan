package com.cycas.limit.handler;

import com.cycas.limit.exception.RateLimitException;
import com.cycas.limit.model.enums.Mode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xin.na
 * @since 2024/2/7 11:26
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String REMAINING_HEADER = "X-RateLimit-Remaining";

    @ExceptionHandler({RateLimitException.class})
    @ResponseBody
    public ResponseEntity<String> RateLimitExceptionHandler(RateLimitException e) {
        HttpHeaders headers = new HttpHeaders();
        if (e.getMode().equals(Mode.SLIDING_WINDOW)) {
            headers.add(HttpHeaders.RETRY_AFTER, String.valueOf(e.getExtra()));
        } else {
            headers.add(REMAINING_HEADER, String.valueOf(e.getExtra()));
        }
        return ResponseEntity.status(429)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"code\":429,\"msg\":\"Too Many Requests\"}");
    }
}
