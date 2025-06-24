package com.cycas.flowabledemo.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author xin.na
 * @since 2025/6/24 10:58
 */
@Data
public class TaskHistoryVo {

    private String taskName;
    private String userId;
    private Date approveTime;
    private String comment;

}
