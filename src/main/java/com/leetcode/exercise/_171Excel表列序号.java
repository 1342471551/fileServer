package com.leetcode.exercise;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author wangyj
 * @Description: (Excel表列序号)
 * https://leetcode-cn.com/problems/excel-sheet-column-number/
 * @date 2021/7/30 8:19
 */
public class _171Excel表列序号 {

    public static int titleToNumber(String columnTitle) {
        char[] charArray = columnTitle.toCharArray();
        int res = 0;
        for (int i = 0; i < charArray.length; i++) {
            res = res * 26 + (charArray[i] - 'A' + 1);
        }
        return res;
    }
}
