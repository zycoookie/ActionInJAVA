package me.cookie.test.jvm.singleton;

/**
 * Created by cookie on 2017/6/1.
 *
 * 使用内部类实现懒汉模式
 */
public class Singleton {
    private Singleton(){

    }

    public static Singleton getInstance(){
        return SingletonViewHolder.mInstance;
    }

    private static class SingletonViewHolder{
        private static final Singleton mInstance = new Singleton();
        static{
            System.out.println("load");
        }
    }
}
