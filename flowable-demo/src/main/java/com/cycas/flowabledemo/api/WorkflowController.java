package com.cycas.flowabledemo.api;

import com.cycas.flowabledemo.domain.dto.CompleteTaskDto;
import com.cycas.flowabledemo.domain.dto.StartProcessDto;
import com.cycas.flowabledemo.domain.vo.ProcessDefinitionVo;
import com.cycas.flowabledemo.domain.vo.TaskHistoryVo;
import com.cycas.flowabledemo.domain.vo.TaskVo;
import org.apache.commons.io.IOUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xin.na
 * @since 2025/6/23 13:57
 */
@RestController
@RequestMapping("/api/workflow")
public class WorkflowController {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ProcessEngineConfiguration processEngineConfiguration;

    /**
     * 查询流程定义列表
     *
     * @return
     */
    @GetMapping("/definitions")
    public List<ProcessDefinitionVo> listDefinitions() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()
                .latestVersion()
                .orderByProcessDefinitionName().asc()
                .list();

        return list.stream()
                .map(ProcessDefinitionVo::from)
                .collect(Collectors.toList());
    }

    /**
     * 开始流程
     *
     * @param dto
     * @return
     */
    @PostMapping("/start")
    public String start(@RequestBody StartProcessDto dto) {
        Authentication.setAuthenticatedUserId(dto.getInitiator()); // 设置发起人
        ProcessInstance pi = runtimeService.createProcessInstanceBuilder()
                .processDefinitionKey(dto.getProcessKey())
                .businessKey(dto.getBusinessKey())
                .variables(dto.getVariables())
                .start();
        return pi.getId();
    }

    /**
     * 任务列表
     *
     * @param userId
     * @return
     */
    @GetMapping("/tasks")
    public List<TaskVo> tasks(@RequestParam String userId) {
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(userId).list();
        return taskList.stream().map(task -> {
            TaskVo vo = TaskVo.from(task);
            // 如果需要获取 businessKey，可通过 runtimeService 查询（需注入）
            String businessKey = runtimeService.createProcessInstanceQuery()
                    .processInstanceId(task.getProcessInstanceId())
                    .singleResult().getBusinessKey();
            vo.setBusinessKey(businessKey);
            return vo;
        }).collect(Collectors.toList());
    }

    /**
     * 任务处理
     *
     * @param dto
     * @return
     */
    @PostMapping("/task/complete")
    public String complete(@RequestBody CompleteTaskDto dto) {
        taskService.complete(dto.getTaskId(), dto.getVariables());
        return "任务处理成功";
    }

    /**
     * 查询流程状态
     *
     * @param businessKey
     * @return
     */
    @GetMapping("/status/{businessKey}")
    public Map<String, Object> getStatus(@PathVariable String businessKey) {
        Map<String, Object> result = new HashMap<>();

        ProcessInstance instance = runtimeService.createProcessInstanceQuery()
                .processInstanceBusinessKey(businessKey)
                .singleResult();

        if (instance == null) {
            result.put("status", "completed");
        } else {
            List<Task> tasks = taskService.createTaskQuery()
                    .processInstanceId(instance.getProcessInstanceId())
                    .list();
            result.put("status", "active");
            result.put("currentTask", tasks.stream().map(Task::getName).collect(Collectors.toList()));
//            result.put("assignee", task.getAssignee());
        }
        return result;
    }

    /**
     * 查询流程历史记录
     */
    @GetMapping("/history/{processInstanceId}")
    public List<TaskHistoryVo> getHistory(@PathVariable String processInstanceId) {
        return historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId)
                .orderByHistoricTaskInstanceEndTime().asc()
                .list()
                .stream()
                .map(ht -> {
                    TaskHistoryVo dto = new TaskHistoryVo();
                    dto.setTaskName(ht.getName());
                    dto.setUserId(ht.getAssignee());
                    dto.setApproveTime(ht.getEndTime());
                    return dto;
                }).collect(Collectors.toList());
    }

    /**
     * 查询流程图
     *
     * @param processInstanceId
     * @param response
     */
    @GetMapping(value = "/diagram/{processInstanceId}", produces = MediaType.IMAGE_PNG_VALUE)
    public void diagram(@PathVariable String processInstanceId, HttpServletResponse response) throws IOException {
        ProcessInstance pi = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();

        if (pi == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "流程不存在");
            return;
        }

        // 获取流程定义模型
        BpmnModel model = repositoryService.getBpmnModel(pi.getProcessDefinitionId());

        // 历史活动节点
        List<String> highLightedActivities = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId)
                .orderByHistoricActivityInstanceStartTime().asc()
                .list()
                .stream()
                .map(HistoricActivityInstance::getActivityId)
                .collect(Collectors.toList());

        // 流程图生成器
        ProcessDiagramGenerator generator = processEngineConfiguration.getProcessDiagramGenerator();

        InputStream is = generator.generateDiagram(
                model,
                "png",
                highLightedActivities,
                Collections.emptyList(),
                true
        );

        // 输出
        IOUtils.copy(is, response.getOutputStream());
        is.close();
    }


}
