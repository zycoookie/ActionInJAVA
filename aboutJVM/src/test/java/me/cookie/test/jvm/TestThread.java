package me.cookie.test.jvm;

import me.cookie.test.jvm.concurrent.InterruptThread;
import me.cookie.test.jvm.concurrent.NoInterruptThread;
import org.junit.Test;

/**
 * Created by cookie on 2017/7/2.
 */
public class TestThread {

    /**
     * 测试线程的中断状态
     * 中断方法在线程开始之后才会生效。。。
     * 发生中断之后中断状态清除，如有需要手动设置会中断状态
     * 开始之前手动中断并不会导致线程从等待状态中抛出中断异常
     */
    @Test
    public void testInterrupt(){
        InterruptThread thread = new InterruptThread();
        thread.interrupt();
        System.out.println("thread interrupt "+thread.isInterrupted());
        thread.start();
        thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("throw ");
        }
    }

    /**
     * 测试线程的中断状态
     * 有些方法接收到中断后并不会抛出中断异常，需要自己发现
     * 中断引起的方法返回后，不会清除中断异常
     */
    @Test
    public void testNoInterrupt(){
        InterruptThread thread = new NoInterruptThread();
        System.out.println("thread interrupt "+thread.isInterrupted());
        thread.start();
        thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("throw ");
        }
    }
}
