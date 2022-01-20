package com.excel;

import java.util.HashMap;
import java.util.Map;
/**
 * @author wangyj
 * @Description: (表头初始化工具类)
 * @date 2022/1/6 9:19
 */
public class TitlesColumns {

    public static final String[] EXPORT_TITLES = {
            "操作人员", "登录IP", "模块标题", "业务类型", "操作状态", "操作时间"
    };

    public static final String[] EXPORT_COLNUMS = {
            "operName", "operIp", "title", "businessType", "status", "operTime"
    };

    public static Map<String,String[]> initTitles = new HashMap<>() ;

    public  static Map<String,String[]> initColumns =  new HashMap<>();

    public TitlesColumns(){
        initTitles = new HashMap<>();
        initTitles.put("001",EXPORT_TITLES);
        initColumns.put("001_V",EXPORT_COLNUMS);
    }

    public Map<String, String[]> getInitTitles() {
        return initTitles;
    }

    public Map<String, String[]> getInitColumns() {
        return initColumns;
    }
}
