package com.excel;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wangyj
 * @Description: (导出实现类)
 * @date 2022/1/5 16:58
 */
@Service
public class OperLogServiceImpl implements OperLogService {

    @Override
    public void exportSysLogV2(String userName, String startDate, String endDate, String type, HttpServletResponse response) {

//        List<OperLogVO> exportLists = workflowTaskMapper.getOperlogList(userName, startDate, endDate);
//        List<OperSysVO> handleLists = handleDbLists(exportLists);
//        List<Map<String, Object>> excelist = ExcelLocalUtils.convertList2Map(handleLists);

        //业务类数据集合
        List<Map<String, Object>> excelist = new ArrayList<>();

        //导出
        /*XSSFWorkbook xb = new XSSFWorkbook();
        ExcelLocalUtils.exportExcel(xb, excelist, EXPORT_TITLES, EXPORT_COLNUMS, SHEET_NAME, 0);
        ExcelLocalUtils.Out(xb, response, EXCEL_NAME);*/

        try {
            MultiWrite.exec(1000, 8, excelist, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
