package com.cycas.commonutil.easyexcel;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

public class ExcelFillCellMergeStrategy implements CellWriteHandler {

    private static final Logger logger = LoggerFactory.getLogger(ExcelFillCellMergeStrategy.class);

    // 合并的列有哪些
    private int[] mergeColumnIndex;
    // 从哪一行开始合并
    private int mergeRowIndex;

    public ExcelFillCellMergeStrategy() {}

    public ExcelFillCellMergeStrategy(int[] mergeColumnIndex, int mergeRowIndex) {
        this.mergeColumnIndex = mergeColumnIndex;
        this.mergeRowIndex = mergeRowIndex;
    }

    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head, Integer integer, Integer integer1, Boolean aBoolean) {

    }

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head, Integer integer, Boolean aBoolean) {

    }

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> list, Cell cell, Head head, Integer integer, Boolean aBoolean) {
        int curRowIndex = cell.getRowIndex();
        int curColIndex = cell.getColumnIndex();
        logger.info("afterCellDispose curRowIndex:{}, curColIndex:{}", curRowIndex, curColIndex);
        if (curRowIndex > mergeRowIndex) {
            for (int i = 0; i < mergeColumnIndex.length; i++) {
                if (curColIndex == mergeColumnIndex[i]) {
                    mergeWithPrevRow(writeSheetHolder, cell, curRowIndex, curColIndex);
                    break;
                }
            }
        }
    }

    /**
     * 当前单元格向上合并
     *
     * @param writeSheetHolder
     * @param cell             当前单元格
     * @param curRowIndex      当前行
     * @param curColIndex      当前列
     */
    private void mergeWithPrevRow(WriteSheetHolder writeSheetHolder, Cell cell, int curRowIndex, int curColIndex) {
        Object curData = getCellValue(cell);
        Object preData = getCellValue(cell.getSheet().getRow(curRowIndex - 1).getCell(curColIndex));
        logger.info("mergeWithPrevRow preData:{},curData:{}", preData, curData);
        // 将当前单元格数据与上一个单元格数据比较
        Boolean dataBool = preData.equals(curData);
        // 此处需要注意：因为我是按照序号确定是否需要合并的，所以获取每一行第一列数据和上一行第一列数据进行比较，如果相等合并
        Object curFirstColData = getCellValue(cell.getRow().getCell(0));
        Object preFirstColData = getCellValue(cell.getSheet().getRow(curRowIndex - 1).getCell(0));
        logger.info("mergeWithPrevRow preFirstColData:{},curFirstColData:{}", preFirstColData, curFirstColData);
        Boolean bool = preFirstColData.equals(curFirstColData);
        if (dataBool && bool) {
            Sheet sheet = writeSheetHolder.getSheet();
            List<CellRangeAddress> mergeRegions = sheet.getMergedRegions();
            logger.info("curRowIndex:{},curColIndex:{},mergeRegions:{}", curRowIndex,curColIndex, mergeRegions);
            boolean isMerged = false;
            for (int i = 0; i < mergeRegions.size(); i++) {
                CellRangeAddress cellRangeAddr = mergeRegions.get(i);
                // 若上一个单元格已经被合并，则先移出原有的合并单元，再重新添加合并单元
                if (cellRangeAddr.isInRange(curRowIndex - 1, curColIndex)) {
                    sheet.removeMergedRegion(i);
                    cellRangeAddr.setLastRow(curRowIndex);
                    sheet.addMergedRegion(cellRangeAddr);
                    isMerged = true;
                }
            }
            // 若上一个单元格未被合并，则新增合并单元
            if (!isMerged) {
                CellRangeAddress cellRangeAddress = new CellRangeAddress(curRowIndex - 1, curRowIndex, curColIndex, curColIndex);
                sheet.addMergedRegion(cellRangeAddress);
            }
        }
    }

    private String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                return HSSFDateUtil.getJavaDate(cell.getNumericCellValue()).toString();
            } else {
                return new BigDecimal(cell.getNumericCellValue()).toString();
            }
        } else if (cell.getCellTypeEnum() == CellType.STRING) {
            return StringUtils.trimToEmpty(cell.getStringCellValue());
        } else if (cell.getCellTypeEnum() == CellType.FORMULA) {
            return StringUtils.trimToEmpty(cell.getCellFormula());
        } else if (cell.getCellTypeEnum() == CellType.BLANK) {
            return "";
        } else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellTypeEnum() == CellType.ERROR) {
            return "ERROR";
        } else {
            return cell.toString().trim();
        }
    }
}
