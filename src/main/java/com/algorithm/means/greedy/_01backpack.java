package com.algorithm.means.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author wangyj
 * @Description: (0 - 1背包问题) 贪心
 * @date 2021/6/4 15:34
 */
public class _01backpack {

    Article[] articles = new Article[]{
            new Article(35, 10), new Article(30, 40),
            new Article(60, 30), new Article(50, 50),
            new Article(40, 35), new Article(10, 40),
            new Article(25, 30)
    };

    public static void main(String[] args) {
        _01backpack backpack = new _01backpack();
        int backpack1 = backpack.backpack(150, (Article a1, Article a2) -> {
            return Double.compare(a2.ValueDensity, a1.ValueDensity);
        });
        System.out.println(backpack1);
    }

    int backpack(int grossWeight, Comparator<Article> comparator) {
        int grossValue = 0;
        Arrays.sort(articles, comparator);
        for (int i = 0; i < articles.length; i++) {
            if (grossWeight >= articles[i].weight) {
                grossWeight -= articles[i].weight;
                grossValue += articles[i].value;
            }
        }
        return grossValue;
    }


    //初始化物品重量和价值
    public static class Article {
        private int weight;
        private int value;
        private double ValueDensity;

        public Article(int weight, int value) {
            this.weight = weight;
            this.value = value;
            ValueDensity = (value * 1.0) / weight;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public double getValueDensity() {
            return ValueDensity;
        }

        public void setValueDensity(double valueDensity) {
            ValueDensity = valueDensity;
        }
    }
}
