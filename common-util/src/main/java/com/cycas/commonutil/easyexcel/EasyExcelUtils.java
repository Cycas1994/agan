package com.cycas.commonutil.easyexcel;

import com.alibaba.excel.EasyExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EasyExcelUtils {

    private static Logger logger = LoggerFactory.getLogger(EasyExcelUtils.class);

    public static void exportExcelCellMerge(String fileName, HttpServletResponse response, Class<?> clazz, List<?> data) {

        // 需要合并的列
        int[] mergeColumnIndex = {0, 1};
        // 从那一行开始合并
        int mergeRowIndex = 0;
        try {
            //获取测试数据
            EasyExcel.write(response.getOutputStream(), clazz).registerWriteHandler(new ExcelFillCellMergeStrategy(mergeColumnIndex, mergeRowIndex))
                    .sheet("Sheet1").doWrite(data);
            logger.info("writerSheet start ...");
        } catch (IOException e) {
            logger.error("export excel error!", e);
            throw new RuntimeException("导出失败:" + e.getMessage());
        } finally {
        }
    }

}
