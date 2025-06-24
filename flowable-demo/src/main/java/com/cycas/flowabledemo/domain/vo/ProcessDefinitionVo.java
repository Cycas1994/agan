package com.cycas.flowabledemo.domain.vo;

import lombok.Data;
import org.flowable.engine.repository.ProcessDefinition;

/**
 * @author xin.na
 * @since 2025/6/24 10:53
 */
@Data
public class ProcessDefinitionVo {
    private String key;
    private String name;
    private int version;


    public static ProcessDefinitionVo from(ProcessDefinition processDefinition) {
        ProcessDefinitionVo vo = new ProcessDefinitionVo();
        vo.setKey(processDefinition.getKey());
        vo.setName(processDefinition.getName());
        vo.setVersion(processDefinition.getVersion());
        return vo;
    }
}

