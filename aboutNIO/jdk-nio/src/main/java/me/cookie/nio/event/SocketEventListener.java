package me.cookie.nio.event;

import me.cookie.nio.LifeCycle;
import me.cookie.nio.exception.ExceptionCode;
import me.cookie.nio.exception.NioException;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;

/**
 * Created by cookie on 2017/6/20.
 */
public class SocketEventListener implements EventListener,LifeCycle{

    private Selector selector;

    @Override
    public void init() {
        try {
            selector = SelectorProvider.provider().openSelector();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void finish() {

    }

    @Override
    public void listen() throws NioException {
        if(selector.isOpen()){

        }else{
            throw new NioException(ExceptionCode.SELECTOR_NOT_OPEN);
        }
    }
}
