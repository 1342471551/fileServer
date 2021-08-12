package com.event.impl;

import com.event.AppletMessageListener;
import com.event.model.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author wangyj
 * @Description: (订单异步监听实现)
 * @date 2020/11/18 20:41
 */
@Component("orderAppletMessageImpl")
public class OrderAppletMessageImpl implements AppletMessageListener<Order> {
    @Override
    public void listener(Order order, Map<String, String> eventMap) {
        System.out.println("根据传入order和参数map进行异步操作"+order);
    }
}
