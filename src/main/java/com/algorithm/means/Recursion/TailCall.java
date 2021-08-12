package com.algorithm.means.Recursion;

/**
 * @author wangyj
 * @Description: (尾调用优化)
 * @date 2021/6/1 17:03
 */
public class TailCall {

    void test1() {
        int a = 10;
        int b = a + 20;
        //当代码尾部调用别的函数可以利用此方法的栈帧做的内存优化
        //当调用方法不是在尾部则不能使用,因为调用的函数可能会改变栈帧中局部变量的值若调用完毕还会执行此方法中的计算可能会造成数据紊乱
        test2();
    }

    void test2() {

    }
}
