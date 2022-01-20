package com.excel;

import javax.servlet.http.HttpServletResponse;

/**
 * @author wangyj
 * @Description: (导出接口)
 * @date 2022/1/5 16:13
 */
public interface OperLogService {

    void exportSysLogV2(String userName, String startDate, String endDate, String type, HttpServletResponse response);
}
