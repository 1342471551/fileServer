package com.excel;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangyj
 * @Description: (这里用一句话描述这个类的作用)
 * @date 2022/1/6 9:17
 */
public class XSSFWorkbookWrapper {

    private XSSFWorkbook workbook;
    private XSSFSheet sheetAt;

    public XSSFWorkbookWrapper() {
        workbook = new XSSFWorkbook();
        sheetAt = workbook.createSheet("main");
    }

    //初始化表头
    public void initTitile(String type) {
        XSSFRow row = sheetAt.createRow(0);
        AtomicInteger index = new AtomicInteger(0);
        String[] head = null;
        if(StringUtils.isNotBlank(type)){
            head = new TitlesColumns().getInitTitles().get(type);
        }
        for(int i=0; i<head.length; i++){
            row.createCell(i).setCellValue(head[i]);
        }
    }

    public XSSFWorkbook getWorkbook() {
        return workbook;
    }
}
