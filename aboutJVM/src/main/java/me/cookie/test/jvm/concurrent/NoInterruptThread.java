package me.cookie.test.jvm.concurrent;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by cookie on 2017/7/2.
 */
public class NoInterruptThread extends InterruptThread{

    @Override
    public void process(){
        LockSupport.park();
    }
}
