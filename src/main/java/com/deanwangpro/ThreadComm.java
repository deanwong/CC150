package com.deanwangpro;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;


public class ThreadComm {

    public static void main(String[] args) {
        cyclicBarrier();
//        countDownLatch();
//        semaphore();
    }


    private static void cyclicBarrier() {
        CyclicBarrier cb = new CyclicBarrier(3);

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread run");
                    try {
                        Thread.sleep(2000);
                        cb.await();
                        System.out.println("finished");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private static void countDownLatch() {
        CountDownLatch cdl = new CountDownLatch(3);


        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread run");
                    try {
                        Thread.sleep(2000);
                        cdl.countDown();
                        System.out.println("finished");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        System.out.println("main thread");
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread finished");

    }

    private static void semaphore() {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 9; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread run");
                    try {
                        semaphore.acquire();
                        Thread.sleep(2000);
                        semaphore.release();
                        System.out.println("finished");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }


}
