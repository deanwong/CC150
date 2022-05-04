package com.deanwangpro;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 循环打印ABC
 */
public class MainLock2 {

    private int count = 0;

    private final Lock lock = new ReentrantLock();
    private final Condition a = lock.newCondition();
    private final Condition b = lock.newCondition();
    private final Condition c = lock.newCondition();

    public static class AThread implements Runnable {

        private MainLock2 mainLock;
        private int count = 1;

        public AThread(MainLock2 mainLock) {
            this.mainLock = mainLock;
        }

        @Override
        public void run() {
            while (mainLock.count < 30) {
                try {
                    mainLock.lock.lock();
                    while (mainLock.count % 3 != 0) {
                        mainLock.a.await();
                    }
                    //System.out.println(Thread.currentThread().getName() + " :" + count++);
                    if (mainLock.count < 30) {
                        System.out.println(Thread.currentThread().getName() + " : A");
                    }
                    mainLock.count++;
                    mainLock.b.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mainLock.lock.unlock();
                }
            }
        }
    }

    public static class BThread implements Runnable {

        private MainLock2 mainLock;
        private int count = 1;

        public BThread(MainLock2 mainLock) {
            this.mainLock = mainLock;
        }

        @Override
        public void run() {
            while (mainLock.count < 30) {
                try {
                    mainLock.lock.lock();
                    while (mainLock.count % 3 != 1) {
                        mainLock.b.await();
                    }
                    //System.out.println(Thread.currentThread().getName() + " :" + count++);
                    if (mainLock.count < 30) {
                        System.out.println(Thread.currentThread().getName() + " : B");
                    }
                    mainLock.count++;
                    mainLock.c.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mainLock.lock.unlock();
                }
            }
        }
    }

    public static class CThread implements Runnable {

        private MainLock2 mainLock;
        private int count = 1;

        public CThread(MainLock2 mainLock) {
            this.mainLock = mainLock;
        }

        @Override
        public void run() {
            while (mainLock.count < 30) {
                try {
                    mainLock.lock.lock();
                    while (mainLock.count % 3 != 2) {
                        mainLock.c.await();
                    }
                    //System.out.println(Thread.currentThread().getName() + " :" + count++);
                    if (mainLock.count < 30) {
                        System.out.println(Thread.currentThread().getName() + " : C");
                    }
                    mainLock.count++;
                    mainLock.a.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mainLock.lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(((4 + 1) << 1));
        System.out.println(2 * 4 + 2);

        MainLock2 mainLock = new MainLock2();
        Thread t1 = new Thread(new AThread(mainLock));
        t1.setName("AThread");
        Thread t2 = new Thread(new BThread(mainLock));
        t2.setName("BThread");
        Thread t3 = new Thread(new CThread(mainLock));
        t3.setName("CThread");
        t1.start();
        t2.start();
        t3.start();
    }
}
