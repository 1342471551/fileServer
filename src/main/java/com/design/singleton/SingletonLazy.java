package com.design.singleton;

/**
 * @author wangyj
 * @Description: (懒汉式) 线程不安全
 * 懒汉式大家可以理解为他懒
 * 别人第一次调用的时候他发现自己的实例是空的，然后去初始化了，再赋值，后面的调用就和饿汉没区别了。
 * @date 2021/6/10 18:02
 */
public class SingletonLazy {

    private static SingletonLazy instance;

    private SingletonLazy() {

    }

    public static SingletonLazy getInstance() {
        //多线程调用判断的时候可能并发创建导致线程不安全
        if (instance == null) {
            instance = new SingletonLazy();
        }
        return instance;
    }
}
