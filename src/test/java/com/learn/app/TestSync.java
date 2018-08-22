package com.learn.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试同步修饰的方法或者类
 *
 * @author kevin
 * @date 2018/8/22
 * @since 0.1.0
 **/
public class TestSync {
    private final int i;
    private  static Integer h = 1;

    public TestSync(int i) {
        this.i = i;
    }


    public synchronized void method1() throws InterruptedException {
        synchronized (TestSync.class){
            System.out.println("method1 in "+Thread.currentThread().getName()+" start at "+System.currentTimeMillis());
            Thread.sleep(6000);
            System.out.println("method1 in "+Thread.currentThread().getName()+" end at "+System.currentTimeMillis());}
    }
    public static synchronized void method2() throws InterruptedException {
        while (true){
            System.out.println(h);
            System.out.println("method2 in "+Thread.currentThread().getName()+"running  "+System.currentTimeMillis());
            Thread.sleep(6000);
        }
    }

    public static void main(String[] args) {
        TestSync testSync = new TestSync(1);
        TestSync testSync1 = new TestSync(2);

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    testSync1.method1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 4; i++) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread 1 is still alive ");
                }
            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    testSync.method2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }

}
