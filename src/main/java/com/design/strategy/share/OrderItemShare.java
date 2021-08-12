package com.design.strategy.share;

/**
 * @author wangyj
 * @Description: (订单分享策略实现类)
 * @date 2021/6/10 16:48
 */
public class OrderItemShare implements ShareStrategy {
    @Override
    public void shareAlgorithm(String param) {
        System.out.println("当前分享图片是" + param);
    }
}
