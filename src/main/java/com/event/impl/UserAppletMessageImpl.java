package com.event.impl;


import com.event.AppletMessageListener;
import com.event.model.User;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author wangyj
 * @Description: (用户类监听实现)
 * @date 2020/11/18 20:38
 */
@Component("userAppletMessageImpl")
public class UserAppletMessageImpl implements AppletMessageListener<User> {
    @Override
    public void listener(User user, Map<String, String> eventMap) {
        System.out.println("进行传入user和map进行异步事件操作"+user);
    }
}
