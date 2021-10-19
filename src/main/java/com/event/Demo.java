package com.event;

import com.event.model.Order;
import com.event.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wangyj
 * @Description: (测试event监听事件)
 * @date 2020/11/19 16:14
 */
@Component
public class Demo {

    @Resource
    AppletMessageEvent<User> userAppletMessageEvent;

    @Resource
    AppletMessageEvent<Order> orderAppletMessageEvent;

    public void appletMessage() {
        User user = new User("111", "222");
        userAppletMessageEvent.sendMessage(AppletMessageEnum.User.getTopic(), user);
        Order order = new Order("222", "333");
        orderAppletMessageEvent.sendMessage(AppletMessageEnum.Order.getTopic(), order);
    }

}
