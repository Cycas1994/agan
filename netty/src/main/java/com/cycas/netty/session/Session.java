package com.cycas.netty.session;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author xin.na
 * @since 2024/10/16 16:06
 */
@Data
@AllArgsConstructor
public class Session {
    private String userId;
    private String username;
}
