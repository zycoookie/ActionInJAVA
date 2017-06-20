package me.cookie.nio.job;

import me.cookie.nio.LifeCycle;

/**
 * Created by cookie on 2017/6/20.
 */
public interface Job extends LifeCycle {

    Object process();

}
