package com.algorithm.complexity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyj
 * @Description: (希尔排序)
 * 把序列看作矩阵,逐列进行排序
 * @date 2021/4/29 11:31
 */
public class ShellSort extends Sort {

    @Override
    protected void sort() {
        List<Integer> stepSequence = shellStepSequence();
        for (Integer step : stepSequence) {
            sort(step);
        }
    }

    //分成多少列进行排序
    private void sort(int step) {
        //col为第几列
        for (int col = 0; col < step; col++) {
            //对第col列元素进行插入排序 排序的元素索引位置为col+row*step
            for (int i = col + step; i < array.length; i += step) {
                int index = i;
                //当前位置小于已比较位置则进行循环元素交换比较
                while (index > col && cmp(index, index - step) < 0) {
                    swap(index, index - step);
                    index -= step;
                }
            }
        }
    }

    //定义生成分割成列的步骤顺序(步长序列) age:{1,2,4,8}  n/2^k
    private List<Integer> shellStepSequence() {
        List<Integer> stepSequence = new ArrayList<>();
        int step = array.length;
        while ((step >>= 1) > 0) {
            stepSequence.add(step);
        }
        return stepSequence;
    }

    public static void main(String[] args) {
        ShellSort shellSort = new ShellSort();
        shellSort.testSort();
    }
}
