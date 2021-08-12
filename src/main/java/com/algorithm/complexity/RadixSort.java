package com.algorithm.complexity;

/**
 * @author wangyj
 * @Description: (基数排序)
 * 依次对个位,十位,百位,千位...进行排序
 * @date 2021/5/6 15:34
 */
public class RadixSort extends Sort {

    @Override
    protected void sort() {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) max = array[i];
        }
        //使用二维数组进行桶计数并排序
        int[][] buckets = new int[10][array.length];
        //记录每个桶的元素数量
        int[] bucketSizes = new int[buckets.length];
        for (int divider = 1; divider <= max; divider *= 10) {
            for (int i = 0; i < array.length; i++) {
                int no = array[i] / divider % 10;
                //先横向找数组,再竖向找数组
                buckets[no][bucketSizes[no]++]=array[i];
            }
            int index=0;
            for (int i=0;i<buckets.length;i++){
                for (int j=0;j<bucketSizes[i];j++){
                    array[index++]=buckets[i][j];
                }
                //只需要清除记录数组bucketSizes,二维数组覆盖即可,无需清除
                bucketSizes[i]=0;
            }
        }
    }


    protected void sort1() {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) max = array[i];
        }
        //个位 array[i] / 1 % 10
        //十位 array[i] / 10 % 10
        //百位 array[i] / 100 % 10
        for (int divider = 1; divider <= max; divider *= 10) {
            RadixSort(divider);
        }
    }

    //进行位数的排序
    protected void RadixSort(int divider) {
        //开辟内存空间,存储累加次数
        int[] counts = new int[10];
        //统计次数
        for (int i = 0; i < array.length; i++) {
            counts[array[i] / divider % 10]++;
        }
        //累加次数
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }
        //从后往前遍历,放到有序数组中合适位置
        int[] newArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            //找到数据对应索引下标--算出新数组对应索引位置放到数组中
            newArray[--counts[array[i] / divider % 10]] = array[i];
        }
        //覆盖原来数组
        for (int i = 0; i < array.length; i++) {
            array[i] = newArray[i];
        }
    }

    public static void main(String[] args) {
        RadixSort radixSort = new RadixSort();
        radixSort.testSort();
    }
}
