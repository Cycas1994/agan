package com.cycas.commonutil.controller.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONArray;
import com.cycas.commonutil.easyexcel.ExcelFillCellMergeStrategy;
import com.cycas.commonutil.pojo.easyexcel.dto.ExcelDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/easyExcel")
public class EasyExcelController {

    private static final Logger logger = LoggerFactory.getLogger(EasyExcelController.class);

    @RequestMapping(value = "/exportXls", method = RequestMethod.GET)
    public void exportXls(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 7);
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            // 需要合并的列
            int[] mergeColumnIndex = {0, 1, 2};
            // 从那一行开始合并
            int mergeRowIndex = 0;
            //获取测试数据
            List<ExcelDto> excelDtoList = getExcelDtoList();
            EasyExcel.write(response.getOutputStream(), ExcelDto.class).registerWriteHandler(new ExcelFillCellMergeStrategy(mergeColumnIndex, mergeRowIndex))
                    .sheet("Sheet1").doWrite(excelDtoList);
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            try {
                response.getWriter().println("下载文件失败");
            } catch (IOException ex) {
                logger.error("response.getWriter().println error", ex);
            }
        }
    }

    private List<ExcelDto> getExcelDtoList() {
        return JSONArray.parseArray("[{\"applyCount\":2,\"avgTime\":12.5,\"bizUnit\":\"-\",\"connCount\":2,\"connRate\":1,\"coopOrg\":\"蔚蓝\",\"orgName\":\"朝阳支行\",\"signerName\":\"admin\"},{\"applyCount\":1,\"avgTime\":0,\"bizUnit\":\"北京\",\"connCount\":0,\"connRate\":0,\"coopOrg\":\"长安新生\",\"orgName\":\"朝阳支行\",\"signerName\":\"admin\"},{\"applyCount\":1,\"avgTime\":11,\"bizUnit\":\"山西\",\"connCount\":1,\"connRate\":1,\"coopOrg\":\"长安新生\",\"orgName\":\"朝阳支行\",\"signerName\":\"admin\"},{\"applyCount\":2,\"avgTime\":0,\"bizUnit\":\"北京\",\"connCount\":0,\"connRate\":0,\"coopOrg\":\"长安新生\",\"orgName\":\"丰台支行\",\"signerName\":\"张三\"},{\"applyCount\":1,\"avgTime\":0,\"bizUnit\":\"-\",\"connCount\":0,\"connRate\":0,\"coopOrg\":\"蔚蓝\",\"orgName\":\"朝阳支行\",\"signerName\":\"张三\"},{\"applyCount\":2,\"avgTime\":0,\"bizUnit\":\"北京\",\"connCount\":0,\"connRate\":0,\"coopOrg\":\"长安新生\",\"orgName\":\"朝阳支行\",\"signerName\":\"张三\"},{\"applyCount\":1,\"avgTime\":0,\"bizUnit\":\"山西\",\"connCount\":0,\"connRate\":0,\"coopOrg\":\"长安新生\",\"orgName\":\"朝阳支行\",\"signerName\":\"张三\"}]", ExcelDto.class);
    }
}
