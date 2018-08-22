package com.learn.day.one;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 队列训练
 *
 * @author kevin
 * @date 2018/8/21
 * @since 0.1.0
 **/
public class QueueTest1 {

    /**
     * 苹果篮子
     */
    public static class Basket {
        /**
         * 定义篮子，可容纳3
         */
        BlockingQueue<String> basket = new ArrayBlockingQueue<String>(3);

        /**
         * 定义生产方法
         */
        public void produce() throws InterruptedException {
            basket.put("AN APPLE");
        }

        /**
         * 定义消费方法
         */
        public String consume() throws InterruptedException {
            return basket.take();
        }

        public int getAppleNumber() {
            return basket.size();
        }
    }

    public static void testBasket() {
        //新建苹果篮子
        final Basket basket = new Basket();
        /**
         * 定义苹果生产者
         */
        class Produce implements Runnable {

            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println("苹果开始生产" + System.currentTimeMillis());
                        basket.produce();
                        System.out.println("苹果生产完毕" + System.currentTimeMillis());
                        System.out.println("生产后的苹果一共有"+basket.getAppleNumber()+"个");
                        Thread.sleep(300);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        class Consumer implements Runnable {

            final String num;

            public Consumer(String num) {
                this.num = num;
            }

            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println("消费者"+num+"准备消费苹果" + System.currentTimeMillis());
                        basket.consume();
                        System.out.println("消费者"+num+"消费苹果完毕" + System.currentTimeMillis());
                        System.out.println("消费后的苹果一共有"+basket.getAppleNumber()+"个");
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new Produce());
        executorService.submit(new Consumer("1"));
        executorService.submit(new Consumer("2"));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

    public static void main(String[] args) {
        QueueTest1.testBasket();
    }

}
