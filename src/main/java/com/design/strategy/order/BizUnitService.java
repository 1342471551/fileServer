package com.design.strategy.order;

import org.springframework.stereotype.Service;

/**
 * @author wangyj
 * @Description: (提供业务逻辑单元)
 * @date 2021/6/10 15:09
 */
@Service
public class BizUnitService {

    public String bizOne(String order) {
        return order + "各种花式操作1";
    }

    public String bizTwo(String order) {
        return order + "各种花式操作2";
    }

    public String bizThree(String order) {
        return order + "各种花式操作3";
    }
}
