package com.design.singleton;

/**
 * @author wangyj
 * @Description: (使用枚举定义单例模式)
 * @date 2021/6/10 17:36
 */
public class SingletonObject {

    private SingletonObject() {

    }

    private enum Singleton {
        //定义SingletonObject的枚举类型
        INSTANCE;
        private final SingletonObject instance;

        Singleton() {
            instance = new SingletonObject();
        }

        private SingletonObject getInstance() {
            return instance;
        }
    }

    public static SingletonObject getInstance() {
        return Singleton.INSTANCE.getInstance();
    }
}
