package com.transportocol.converter;


/**
 * @author wangyj
 * @Description: (数据转换类)
 * @date 2020/12/9 16:02
 */
public interface DataConverter<T,E> {

    /**
     * from t 2 e
     * @param t
     * @return
     */
    E convert(T t);
}
