package com.design.strategy.share;

/**
 * @author wangyj
 * @Description: (多商品分享实现类)
 * @date 2021/6/10 16:51
 */
public class MultiItemShare implements ShareStrategy {

    @Override
    public void shareAlgorithm(String param) {
        System.out.println("当前分享图片是" + param);
    }
}
