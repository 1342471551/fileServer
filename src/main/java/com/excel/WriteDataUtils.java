package com.excel;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wangyj
 * @Description: (封装查询结果，获取分组数据)
 * @date 2022/1/6 9:18
 */
public class WriteDataUtils {

    public static List<List<String>> getValuesOut(List<Map<String, Object>> lists, int max, String type) {
        List<List<String>> rest = new ArrayList<List<String>>();
        for (int i = 0; i < lists.size(); i++) {
            if (StringUtils.isNotBlank(type)) {
                String[] columns = new TitlesColumns().getInitColumns().get(type + "_V");
                List<String> item = new ArrayList<String>();
                item.add(lists.get(i).get(columns[0]) + "");
                item.add(lists.get(i).get(columns[1]) + "");
                item.add(lists.get(i).get(columns[2]) + "");
                item.add(lists.get(i).get(columns[3]) + "");
                item.add(lists.get(i).get(columns[4]) + "");
                item.add(lists.get(i).get(columns[5]) + "");
                rest.add(item);
            }
        }
        return rest;
    }

    /**
     * 将数据进行分组
     *
     * @param data
     * @param groupNum
     * @return
     */
    public static <T> List<List<List<T>>> groupData(List<List<T>> data, Integer groupNum) {
        int all = data.size();
        int other = all % groupNum;
        int groupItemNum = all / groupNum;
        List<List<List<T>>> runList = new ArrayList<List<List<T>>>();
        while (data == null || data.size() > 0) {
            if (data.size() <= other + groupItemNum) {
                List<List<T>> lists = data.subList(0, data.size());
                List<List<T>> item = new ArrayList<List<T>>();
                item.addAll(lists);
                runList.add(item);
                data.removeAll(item);
            } else {
                List<List<T>> lists = data.subList(0, groupItemNum);
                List<List<T>> item = new ArrayList<List<T>>();
                item.addAll(lists);
                runList.add(item);
                data.removeAll(item);
            }
        }
        return runList;
    }
}
