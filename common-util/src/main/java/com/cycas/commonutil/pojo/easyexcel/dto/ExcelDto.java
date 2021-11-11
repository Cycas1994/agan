package com.cycas.commonutil.pojo.easyexcel.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ExcelDto {

    @ExcelProperty(value = "坐席人员", index = 0)
    private String signerName;
    @ExcelProperty(value = "所属机构", index = 1)
    private String orgName;
    @ExcelProperty(value = "合作机构", index = 2)
    private String coopOrg;
    @ExcelProperty(value = "所属事业部", index = 3)
    private String bizUnit;
    @ExcelProperty(value = "视频申请次数", index = 4)
    private int applyCount;
    @ExcelProperty(value = "首次接通数量", index = 5)
    private int connCount;
    @ExcelProperty(value = "首次接通率", index = 6)
    private double connRate;
    @ExcelProperty(value = "平均面谈时长", index = 7)
    private double avgTime;
}
