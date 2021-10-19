package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (Nim游戏)
 * https://leetcode-cn.com/problems/nim-game/
 * @date 2021/9/18 11:19
 */
public class _292Nim游戏 {

    /**
     * 面对4的整数倍的人永远无法翻身，你拿N根对手就会拿4-N根，保证每回合共减4根，你永远对面4倍数，直到4.
     * 相反，如果最开始不是4倍数，你可以拿掉刚好剩下4倍数根，让他永远对面4倍数。
     * 巴什博奕，n%(m+1)!=0时，先手总是会赢的
     */
    public boolean canWinNim(int n) {
        if (n % 4 != 0) return true;
        return false;
    }
}
