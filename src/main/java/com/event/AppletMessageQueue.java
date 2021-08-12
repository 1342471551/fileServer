package com.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wangyj
 * @Description: (小程序消息监听公共实现队列)
 * @date 2020/11/19 15:49
 */
@Component
@Slf4j
public class AppletMessageQueue<T> {

    //用来获取string容器中的bean
    @Resource
    ApplicationContext applicationContext;

    @EventListener
    public void messageConsumer(AppletMessage<T> message) {
        AppletMessageEnum code = AppletMessageEnum.getCode(message.getTopic());
        AppletMessageListener<T> appletMessageListener = null;
        try {
            appletMessageListener =(AppletMessageListener<T>) applicationContext.getBean(code.getBean());
        }catch (NoSuchBeanDefinitionException e){
            log.info("String单例池中没有此bean对象"+code.getBean());
        }
        appletMessageListener.listener(message.getT(),message.getEventMap());
    }
}
