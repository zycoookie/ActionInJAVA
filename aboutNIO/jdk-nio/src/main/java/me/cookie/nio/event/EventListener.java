package me.cookie.nio.event;

import me.cookie.nio.LifeCycle;
import me.cookie.nio.exception.NioException;

/**
 * Created by cookie on 2017/6/20.
 */
public interface EventListener extends LifeCycle{

    void listen() throws NioException;

}
