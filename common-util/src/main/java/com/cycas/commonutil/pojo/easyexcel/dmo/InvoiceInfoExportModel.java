package com.cycas.commonutil.pojo.easyexcel.dmo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class InvoiceInfoExportModel {

    @ExcelProperty(value = {"单号"}, index = 0)
    private String applyId;

    @ExcelProperty(value = "{时间}", index = 1)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date applyTime;

    @ExcelProperty(value = {"发票类型"}, index = 2)
    private String invoiceType;
}
