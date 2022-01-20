package com.concurrent;

/**
 * @author wangyj
 * @Description: (这里用一句话描述这个类的作用)
 * @date 2021/10/25 17:54
 */
public class PhaserTest {

    public static MyPhaser myPhaser = new MyPhaser();

    public static void main(String[] args) {
        MyPhaser myPhaser = new MyPhaser();
        // 一次性注册5个party，即建立5个屏障点
        myPhaser.bulkRegister(5);
        for (int i = 0; i < 5; i++) {
            Thread runner = new Thread(new Runnable() {

                @Override
                public void run() {
                    // 第一阶段（周期），phaser的周期数初始值为0
                    System.out.println(Thread.currentThread().getName() + "到达了起跑点！");
                    // 到达了屏障点（起跑点），阻塞等待其他线程
                    myPhaser.arriveAndAwaitAdvance();

                    // 继续运行，将进入第二阶段，phaser的周期数加一
                    System.out.println(Thread.currentThread().getName() + "准备起跑！");
                    // 到达了屏障点（准备起跑），阻塞等待其他线程
                    myPhaser.arriveAndAwaitAdvance();

                    // 进入第三阶段
                    System.out.println(Thread.currentThread().getName() + "到达了终点！");
                    // 参数者到达了终点，结束比赛，不再等待其他参赛者
                    myPhaser.arriveAndDeregister();// 取消注册一个party
                }
            }, "参赛者" + i + "号");
            runner.start();
        }
    }
}
