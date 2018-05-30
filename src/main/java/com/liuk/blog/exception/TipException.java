package com.liuk.blog.exception;

/**
 * 自定义异常,用于提示
 * Created By Liuk On 2018/05/20.
 */
public class TipException extends RuntimeException{

    //无参构造方法
    public TipException(){
        super();
    }

    //有参的构造方法
    public TipException(String message){
        super(message);
    }

    // 用指定的详细信息和原因构造一个新的异常
    public TipException(String message, Throwable cause){
        super(message,cause);
    }

    //用指定原因构造一个新的异常
    public TipException(Throwable cause) {
        super(cause);
    }
}
