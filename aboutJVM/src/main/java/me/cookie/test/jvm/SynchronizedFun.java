package me.cookie.test.jvm;

/**
 * Created by cookie on 2017/5/30.
 */
public class SynchronizedFun {

    /**
     * 同步标记在方法描述符中
     */
    public synchronized void functionA(){
        System.out.println("a");
    }

    /**
     * 显示添加了monitorenter和monitorexit进行同步
     */
    public void functionB(){
        synchronized (SynchronizedFun.class){
            System.out.println("b");
        }
    }
}
