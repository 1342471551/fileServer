package com.excel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangyj
 * @Description: (导出日志系统信息 控制器)
 * @date 2022/1/5 16:07
 */
public class OperLogController {

    @Resource
    private OperLogService operLogService;

    /**
     * 导出系统日志信息-V2测试
     *
     * @return
     */
    @GetMapping("/log-export/v2")
    public void exportSysLogV2(@RequestParam(name = "userName", required = false) String userName,
                               @RequestParam(name = "startDate", required = false) String startDate,
                               @RequestParam(name = "endDate", required = false) String endDate,
                               @RequestParam(name = "type", required = false) String type,
                               HttpServletResponse response) {
        operLogService.exportSysLogV2(userName, startDate, endDate, type, response);
    }

}
