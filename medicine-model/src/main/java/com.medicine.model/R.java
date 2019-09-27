package com.medicine.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 高壮壮
 * @Date 2019/9/27
 */
@Data
public class R<T> implements Serializable {

    private  int code;
    private  String message;
    private  T result;


    static R ok(){
        return  new R();
    }
    static <T> R<T>  ok(T object){
        R obj = new R();
        obj.setResult(object);
        return obj;
    }

    static <T> R<T> ok(T object,String message){
        R obj = new R();
        obj.setResult(object);
        obj.setMessage(message);
        return obj;
    }

    static <T> R<T> error(int code){
        R obj = new R();
        obj.setCode(code);
        return obj;
    }

    static <T> R<T> error(int code,String message){
        R obj = new R();
        obj.setCode(code);
        obj.setMessage(message);
        return obj;
    }
}
