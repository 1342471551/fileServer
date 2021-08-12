package com.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author wangyj
 * @Description: (小程序消息监听类,使用泛型接收多渠道消息,topic进行枚举区分获取对应执行代码bean)
 * @date 2020/11/18 20:53
 */
@Component
public class AppletMessageEvent<T> implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    //提供公共的监听方法
    public void sendMessage(String topic,T t){
        AppletMessage<T> appletMessage=new AppletMessage<>(this,t,topic);
        applicationEventPublisher.publishEvent(appletMessage);
    }

    //提供带map参数的公共监听方法
    public void sendMessageByMap(String topic, T t, Map<String,String> map){
        AppletMessage<T> appletMessage=new AppletMessage<>(this,t,topic,map);
        applicationEventPublisher.publishEvent(appletMessage);
    }


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher=applicationEventPublisher;
    }
}
