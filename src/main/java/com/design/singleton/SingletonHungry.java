package com.design.singleton;

/**
 * @author wangyj
 * @Description: (饿汉式)
 * 饿汉式大家可以理解为他饿，他想提前把对象new出来，
 * 这样别人哪怕是第一次获取这个类对象的时候直接就存在这个类了，省去了创建类这一步的开销
 * @date 2021/6/10 17:47
 */
public class SingletonHungry {

    private static SingletonHungry instance = new SingletonHungry();

    private SingletonHungry() {

    }

    public static SingletonHungry getInstance() {
        return instance;
    }
}
