package com.design.strategy.share;

/**
 * @author wangyj
 * @Description: (单向分享实现类)
 * @date 2021/6/10 16:50
 */
public class SingleItemShare implements ShareStrategy{

    @Override
    public void shareAlgorithm(String param) {
        System.out.println("当前分享图片是" + param);
    }
}
