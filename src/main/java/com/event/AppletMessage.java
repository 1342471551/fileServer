package com.event;

import org.springframework.context.ApplicationEvent;

import java.util.Map;

/**
 * @author wangyj
 * @Description: (监听器参数类)
 * @date 2020/11/18 20:16
 */
public class AppletMessage<T> extends ApplicationEvent {

    private T t;
    private String topic;
    private Map<String,String> eventMap;

    public AppletMessage(Object source,T t, String topic, Map<String, String> eventMap) {
        super(source);
        this.t = t;
        this.topic = topic;
        this.eventMap = eventMap;
    }

    public AppletMessage(Object source,T t, String topic) {
        super(source);
        this.t = t;
        this.topic = topic;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Map<String, String> getEventMap() {
        return eventMap;
    }

    public void setEventMap(Map<String, String> eventMap) {
        this.eventMap = eventMap;
    }
}
