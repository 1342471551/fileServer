package com.transportocol;

/**
 * @author wangyj
 * @Description: (传输协议转换类)
 * @date 2020/12/9 16:30
 */
public interface Transform<T,R> {

    R transform(T t);
}
