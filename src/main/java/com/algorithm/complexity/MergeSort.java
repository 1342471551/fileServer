package com.algorithm.complexity;

/**
 * @author wangyj
 * @Description: (归并排序)
 * 1.分割序列到不可分割为止(1个元素)
 * 2.合并成有序元素序列
 * T(n)
 * =T(n/2)+T(n/2)+O(n)
 * =T(n)/n=T(n/2)/(n/2)+O(1)
 * 令s(n)=T(n)/n
 * =S(n)=S(n/2)+O(1)=S(n/4)+O(3)=S(n/2^k)+O(k)=S1+O(logn)
 * T(n)=n*S(n)=nlogn
 */
public class MergeSort extends Sort {

    private Integer[] leftArray;

    @Override
    protected void sort() {
        leftArray = new Integer[array.length >> 1];
        sort(0, array.length);
    }


    //对[begin,end)元素进行归并排序
    protected void sort(int begin, int end) {
        //元素只用1个或没有则直接返回
        if (end - begin < 2) return;
        int mid = (begin + end) >> 1;
        //范围是左闭右开
        sort(begin, mid);
        sort(mid, end);
        merge(begin, mid, end);
    }

    //将[begin,mid),[mid,begin)的元素进行合并(在同一块数组(内存)中)
    private void merge(int begin, int mid, int end) {
        //记录左边数组指针位置和结束位置
        int li = 0, le = mid - begin;
        //记录右边数组指针位置和结束位置
        int ri = mid, re = end;
        //记录覆盖的数组位置
        int ai = begin;
        //将数组左边部分备份再进行数据的移动
        for (int i = li; i < le; i++) {
            //右边数组传入开始为begin,长度为begin+i
            leftArray[i] = array[begin + i];
        }
        //若备份(左边)数组元素填充完毕则可以直接结束
        while (li < le) {
            //若右边数组元素备份完毕则全部进入else把左边数组元素填充
            if (ri < re && leftArray[li] >= array[ri]) {
                array[ai++] = array[ri++];
            } else {
                array[ai++] = leftArray[li++];
            }
        }
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        mergeSort.testSort();
    }
}
