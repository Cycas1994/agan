package com.cycas.flowabledemo.domain.vo;

import lombok.Data;
import org.flowable.task.api.Task;

import java.util.Date;

/**
 * @author xin.na
 * @since 2025/6/24 10:22
 */
@Data
public class TaskVo {
    /** 任务 ID */
    private String taskId;

    /** 任务名称 */
    private String name;

    /** 流程实例 ID */
    private String processInstanceId;

    /** 创建时间 */
    private Date createTime;

    /** 流程定义 Key（可选） */
    private String processDefinitionKey;

    /** 当前节点定义 key（可选） */
    private String taskDefinitionKey;

    /** 所属业务 Key（可选） */
    private String businessKey;

    public static TaskVo from(Task task) {
        TaskVo vo = new TaskVo();
        vo.setTaskId(task.getId());
        vo.setName(task.getName());
        vo.setProcessInstanceId(task.getProcessInstanceId());
        vo.setCreateTime(task.getCreateTime());
        vo.setProcessDefinitionKey(task.getProcessDefinitionId());
        vo.setTaskDefinitionKey(task.getTaskDefinitionKey());

        return vo;
    }
}
