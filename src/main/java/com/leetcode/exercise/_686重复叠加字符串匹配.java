package com.leetcode.exercise;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author wangyj
 * @Description: (重复叠加字符串匹配)
 * https://leetcode-cn.com/problems/repeated-String-match/
 * @date 2021/12/22 10:19
 */
public class _686重复叠加字符串匹配 {

    //只有可能是b/a或者b/a+1 依次拿字符串截取匹配 发现有匹配的则返回 没有则-1
    public static int repeatedStringMatch(String a, String b) {
        int l = (b.length() + a.length() - 1)/a.length();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<l;i++)
            sb.append(a);
        for(int i=0;i<=sb.length()-b.length();i++){
            if(sb.substring(i, i + b.length()).equals(b))
                return l;
        }
        sb.append(a);
        for(int i=a.length()*l-b.length()+1;i<=sb.length()-b.length();i++)
            if(sb.substring(i, i + b.length()).equals(b))
                return l+1;
        return -1;
    }

    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        try {
            System.out.println(simpleDateFormat.parse("2020-02-20"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
