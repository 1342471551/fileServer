package com.event.model;

import lombok.Data;

/**
 * @author wangyj
 * @Description: (订单类)
 * @date 2020/11/18 20:36
 */
@Data
public class Order {
    private String id;
    private String product;

    public Order(String id, String product) {
        this.id = id;
        this.product = product;
    }
}
