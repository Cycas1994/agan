package com.cycas.flowabledemo.domain.dto;

import lombok.Data;

import java.util.Map;

/**
 * @author xin.na
 * @since 2025/6/23 13:59
 */
@Data
public class CompleteTaskDto {
    private String taskId;
    private String userId;
    private Map<String, Object> variables;
}
