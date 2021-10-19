package com.algorithm.structure.hash;

import java.util.Objects;

/**
 * @author wangyj
 * @Description: (自定义对象类)
 * @date 2021/4/19 9:30
 */
public class Person {
    private int age;
    private float height;
    private String name;

    public Person(int age, float height, String name) {
        this.age = age;
        this.height = height;
        this.name = name;
    }


    //重写object方法自定义类对象hashcode
    @Override
    public int hashCode() {
        int hashCode = Integer.hashCode(age);
        hashCode = hashCode * 31 + Float.hashCode(height);
        hashCode = hashCode * 31 + (name != null ? name.hashCode() : 0);
        return hashCode;
    }

    //重写equals方法,比较hashcode相等的元素值是否相等

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        //传入对象为空,或者传入类不是此类型返回false
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Float.compare(person.height, height) == 0 &&
                Objects.equals(name, person.name);
    }
}