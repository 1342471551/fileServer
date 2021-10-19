package com.leetcode.exercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangyj
 * @Description: (二进制手表)
 * https://leetcode-cn.com/problems/binary-watch/
 * @date 2021/6/21 17:18
 */
public class _401二进制手表 {

    public List<String> readBinaryWatch(int turnedOn) {
        Map<Integer, List<String>> hour = new HashMap<>();
        Map<Integer, List<String>> mins = new HashMap<>();

        for (int i = 0; i < 12; i++) {
            int howManyOnes = getRes(i);
            hour.putIfAbsent(howManyOnes, new ArrayList<>());
            hour.get(howManyOnes).add(i + "");
        }

        for (int i = 0; i < 60; i++) {
            int howManyOnes = getRes(i);
            mins.putIfAbsent(howManyOnes, new ArrayList<>());
            if (i < 10)
                mins.get(howManyOnes).add("0" + i);
            else
                mins.get(howManyOnes).add(i + "");
        }

        List<String> res = new ArrayList<>();
        for (int i = 0; i <= turnedOn; i++) {
            if (!hour.containsKey(i) || !mins.containsKey(turnedOn - i)) continue;
            for (String h : hour.get(i)) {
                for (String m : mins.get(turnedOn - i)) {
                    res.add(h + ":" + m);
                }
            }
        }
        return res;
    }

    private int getRes(int num) {
        int counter = 0;
        while (num != 0) {
            if ((num & 1) == 1) {
                counter++;
            }
            num >>= 1;
        }
        return counter;
    }
}
