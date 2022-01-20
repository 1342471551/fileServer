package com.algorithm.complexity;

/**
 * @author wangyj
 * @Description: (排序算法)
 * @date 2021/4/27 10:21
 */
public abstract class Sort {
    protected Integer[] array;
    //记录比较次数
    protected int cmpCount;
    //记录交换次数
    protected int swapCount;

    public void sort(Integer[] array) {
        if (array == null || array.length < 2) return;
        //把传入的数组赋值给抽象类,让实现类直接使用抽象类中元素进行算法的实现
        this.array = array;
        sort();
    }

    protected abstract void sort();

    /**
     * >0 i1>i2
     * <0 i1<i2
     * =0 i1=i2
     */
    protected int cmp(int i1, int i2) {
        cmpCount++;
        return array[i1] - array[i2];
    }

    //定义交换方法
    protected void swap(int i1, int i2) {
        swapCount++;
        int tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
    }

    protected void testSort() {
        Integer[] array = {12, 34, 23, 64, 12, 32, 28, 321};
        sort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
