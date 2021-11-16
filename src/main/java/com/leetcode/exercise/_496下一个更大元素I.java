package com.leetcode.exercise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * @author wangyj
 * @Description: (下一个更大元素I)
 * https://leetcode-cn.com/problems/next-greater-element-i/
 * @date 2021/10/26 11:25
 */
public class _496下一个更大元素I {

    /**
     * 通过Stack、HashMap解决
     * 先遍历大数组nums2，首先将第一个元素入栈；
     * 继续遍历，当当前元素小于栈顶元素时，继续将它入栈；当当前元素大于栈顶元素时，栈顶元素出栈，此时应将该出栈的元素与当前元素形成key-value键值对，存入HashMap中；
     * 当遍历完nums2后，得到nums2中元素所对应的下一个更大元素的hash表；
     * 遍历nums1的元素在hashMap中去查找‘下一个更大元素’，当找不到时则为-1。
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<Integer>();
        HashMap<Integer, Integer> hasMap = new HashMap<Integer, Integer>();

        int[] result = new int[nums1.length];

        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peek() < num) {
                hasMap.put(stack.pop(), num);
            }
            stack.push(num);
        }

        for (int i = 0; i < nums1.length; i++) result[i] = hasMap.getOrDefault(nums1[i], -1);

        return result;
    }
}
