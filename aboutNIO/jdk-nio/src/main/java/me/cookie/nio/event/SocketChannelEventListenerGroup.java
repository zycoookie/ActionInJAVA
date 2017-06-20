package me.cookie.nio.event;

import me.cookie.nio.Group;
import me.cookie.nio.LifeCycle;

import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by cookie on 2017/6/20.
 */
public class SocketChannelEventListenerGroup implements Group , LifeCycle{

    private ExecutorService service;

    private volatile boolean init = false;
    private volatile boolean finish = false;

    @Override
    public void receive(Object object) {
        SocketChannel channel = (SocketChannel) object;
    }

    @Override
    public void init() {
        service = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        init = true;
    }

    @Override
    public void finish() {
        service.shutdown();
        finish = true;
    }
}
