package me.cookie.nio.exception;

/**
 * Created by cookie on 2017/6/20.
 */
public class NioException extends Exception{

    public NioException(ExceptionCode code){
        super(code.name());
    }

}
