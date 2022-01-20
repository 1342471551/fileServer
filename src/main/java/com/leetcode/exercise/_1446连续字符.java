package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (连续字符)
 * https://leetcode-cn.com/problems/consecutive-characters/
 * @date 2021/12/1 9:33
 */
public class _1446连续字符 {

    public int maxPower(String s) {
        //非连续
//        Map<Character, Integer> map = new HashMap<>();
//        for (int i = 0; i < s.length(); i++) {
//            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
//        }
//        AtomicReference<Integer> res = new AtomicReference<>(0);
//        map.forEach((a, b) -> {
//            if (b > res.get()) res.set(b);
//        });
//        return res.get();
        int res = 1, temp = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                temp++;
                res = Integer.max(res, temp);
            } else {
                temp = 1;
            }
        }
        return res;
    }
}
