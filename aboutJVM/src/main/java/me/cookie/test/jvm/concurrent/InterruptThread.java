package me.cookie.test.jvm.concurrent;

/**
 * Created by cookie on 2017/7/2.
 */
public class InterruptThread extends Thread {

    @Override
    public void run(){
        System.out.println("the interrupt of Thread:"+Thread.currentThread().isInterrupted());


        try {
            process();
            System.out.println("no throw interrupt");
        } catch (InterruptedException e) {
            System.out.println("throw interrupt");
        }

        System.out.println("the interrupt of Thread:"+Thread.currentThread().isInterrupted());
    }

    void process() throws InterruptedException {
        sleep(10000);
    }
}
