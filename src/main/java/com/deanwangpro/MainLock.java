package com.deanwangpro;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 循环打印奇偶数
 */
public class MainLock {

    private int start = 1;

    private final Lock lock = new ReentrantLock();
    private final Condition even = lock.newCondition();
    private final Condition odd = lock.newCondition();

    public static class OddThread implements Runnable {

        private MainLock mainLock;

        public OddThread(MainLock mainLock) {
            this.mainLock = mainLock;
        }

        @Override
        public void run() {
            while (mainLock.start < 100) {
                try {
                    mainLock.lock.lock();
                    while (mainLock.start % 2 == 0) {
                        mainLock.odd.await();
                    }
                    System.out.println(Thread.currentThread().getName() + " :" + mainLock.start++);
                    mainLock.even.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mainLock.lock.unlock();
                }
            }
        }
    }

    public static class EvenThread implements Runnable {

        private MainLock mainLock;

        public EvenThread(MainLock mainLock) {
            this.mainLock = mainLock;
        }

        @Override
        public void run() {
            while (mainLock.start < 100) {
                try {
                    mainLock.lock.lock();
                    while (mainLock.start % 2 == 1) {
                        mainLock.even.await();
                    }
                    System.out.println(Thread.currentThread().getName() + ":" + mainLock.start++);
                    mainLock.odd.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mainLock.lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        MainLock mainLock = new MainLock();
        Thread t1 = new Thread(new OddThread(mainLock));
        t1.setName("OddThread");
        Thread t2 = new Thread(new EvenThread(mainLock));
        t2.setName("EvenThread");
        t1.start();
        t2.start();
    }
}
