package com.event;

import java.util.Map;

/**
 * @author wangyj
 * @Description: (监听器实现接口类,通过泛型不同实现类衍生不同的实现方法)
 * @date 2020/11/18 20:21
 */
public interface AppletMessageListener<T> {

    void listener(T t, Map<String, String> eventMap);
}
