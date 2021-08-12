package com.algorithm.complexity;

/**
 * @author wangyj
 * @Description: (计数排序)针对整数
 * 统计数据出现次数,在对应索引(数据本身当作索引)进行出现次数记录
 * @date 2021/5/6 11:04
 */
public class CountingSort extends Sort {


    protected void sort1() {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) max = array[i];
        }
        //开辟整形数组,系统默认都填充为0
        int[] counts = new int[max + 1];
        //统计整数出现次数
        for (int i = 0; i < array.length; i++) {
            counts[array[i]]++;
        }
        //根据整数出现次数 进行排序
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            while (counts[i]-- > 0) array[index++] = i;
        }
    }

    @Override
    protected void sort() {
        //改进版本寻找最小最大值开辟数组空间,索引累加记录次数,从右向左(稳定性)遍历元素乱序数组找到对应数组索引记录次数(位置--)填充原始数组位置(排序后位置)
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) max = array[i];
            if (array[i] < min) min = array[i];
        }
        //开辟内存空间,存储累加次数
        int[] counts = new int[max - min + 1];
        //统计次数
        for (int i = 0; i < array.length; i++) {
            counts[array[i]-min]++;
        }
        //累加次数
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }
        //从后往前遍历,放到有序数组中合适位置
        int[] newArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            //找到数据对应索引下标--算出新数组对应索引位置放到数组中
            newArray[--counts[array[i] - min]] = array[i];
        }
        //覆盖原来数组
        for (int i = 0; i < array.length; i++) {
            array[i] = newArray[i];
        }
    }

    public static void main(String[] args) {
        CountingSort countingSort = new CountingSort();
        countingSort.testSort();
    }
}
