package com.excel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author wangyj
 * @Description: (将数据写入到具体的excel中)
 * @date 2022/1/6 9:18
 */
public class WritePOIUtils {

    private static Object LOCK = new Object();

    public static synchronized XSSFRow getRow(XSSFSheet sheetAt,Integer i) {
        return sheetAt.getRow(i) == null ? sheetAt.createRow(i) : sheetAt.getRow(i);
    }

    public static XSSFCell getCell(XSSFRow row,Integer j) {
        return row.getCell(j) == null ? row.createCell(j) : row.getCell(j);
    }

    public void setWorkbookData(XSSFWorkbook workbook,
                                List<List<String>> data,
                                Integer startNum) {
        XSSFSheet sheetAt = workbook.getSheetAt(0);
        Integer endNum = data.size() + startNum;
        Integer index = 0;
        for (int i = startNum; i < endNum; i++) {
            XSSFRow row = getRow(sheetAt,i);
            List <String> values = data.get(index);
            for (int j = 0; j < values.size(); j++) {
                XSSFCell cell = getCell(row,j);
                /*cell.setCellValue(test.get(j));*/
                String s = values.get(j);
                synchronized (LOCK) {
                    cell.setCellValue(s);
                }
            }
            index++;
        }
    }

    public static void writeFile (XSSFWorkbook workbook) throws IOException {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("C:\\logs\\multi.xlsx");
            //向d://test.xls中写数据
            out.flush();
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
