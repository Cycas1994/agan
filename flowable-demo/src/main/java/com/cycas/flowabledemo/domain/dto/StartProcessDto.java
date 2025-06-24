package com.cycas.flowabledemo.domain.dto;

import lombok.Data;

import java.util.Map;

/**
 * @author xin.na
 * @since 2025/6/23 13:58
 */
@Data
public class StartProcessDto {
    private String processKey;
    private String businessKey;
    private String initiator;
    private Map<String, Object> variables;
}
