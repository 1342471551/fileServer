package com.event.model;

import lombok.Data;

/**
 * @author wangyj
 * @Description: (用户类)
 * @date 2020/11/18 20:32
 */
@Data
public class User {
    private String name;
    private String id;

    public User(String name, String id) {
        this.name = name;
        this.id = id;
    }
}
