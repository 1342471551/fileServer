package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (超级洗衣机)
 * https://leetcode-cn.com/problems/super-washing-machines/
 * @date 2021/9/29 8:47
 */
public class _517超级洗衣机 {

    /**
     * 在把差值数组每一项变为0的操作中，只需要求出其中所需移动衣服最多的洗衣机，就是最少的移动次数。
     * 当diff[i] < 0 时，可以从左右两边的洗衣机获取衣服，取左右中的最大值；
     * 当diff[i] > 0 时，需要把洗衣机的衣服向左右转移，此时移动次数等于diff[i]
     * 我们从左向右依次把差值数组中的每一项变为0：考虑到与该洗衣机非相邻的洗衣机可能需要经过该洗衣机来转移衣服，因此
     * 用balance记录当前洗衣机上所要经过的流量。
     * balance += diff[i];
     * balance < 0 说明需要从右边获取衣服，balance > 0 说明需要向右边转移衣服。
     * 那么该洗衣机上的最大操作数为： max(diff[i], Math.abs(balance))
     */
    public static int findMinMoves(int[] machines) {
        int sum = 0;
        for (int num : machines)
            sum += num;
        if (sum % machines.length != 0)
            return -1;

        int target = sum / machines.length;
        //res是移动次数 balance是移动当前多余/需要的衣服数量
        int res = 0, balance = 0;
        for (int i = 0; i < machines.length; i++) {
            //记录当前洗衣机要经过的流量
            balance += machines[i] - target;
            res = Math.max(res, Math.max(machines[i] - target, Math.abs(balance)));
        }
        return res;
    }

}
