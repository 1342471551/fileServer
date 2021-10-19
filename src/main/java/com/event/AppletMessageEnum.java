package com.event;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyj
 * @Description: (对应参数枚举类,用来获取对应bean)
 * @date 2020/11/18 20:45
 */
public enum AppletMessageEnum {

    User("user","userAppletMessageImpl"),
    Order("order","orderAppletMessageImpl"),
    ;

    private String topic;
    private String bean;

    static Map<String,AppletMessageEnum> messageEnumMap=new HashMap<>();

    static {
        Arrays.stream(AppletMessageEnum.values()).forEach(e->messageEnumMap.put(e.topic,e));
    }

    public static AppletMessageEnum getCode(String topic){
        return messageEnumMap.get(topic);
    }

    AppletMessageEnum(String topic, String bean) {
        this.topic = topic;
        this.bean = bean;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getBean() {
        return bean;
    }

    public void setBean(String bean) {
        this.bean = bean;
    }
}
