package me.cookie.test.jvm.classload;

/**
 * Created by cookie on 2017/6/1.
 */
public class Super extends SuperFather{
    static {
        System.out.println("Super");
    }
}
