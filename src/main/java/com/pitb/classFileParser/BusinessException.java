package com.pitb.classFileParser;

/**
 * @package: com.improve.jvm.classFileParser
 * @date: 2020-07-30 14:31
 * @author: corey.yang
 */
public class BusinessException extends RuntimeException {

    public BusinessException(){

    }

    public BusinessException(String msg){
        super(msg);
    }

    public static BusinessException of(String msg) {
        return new BusinessException(msg);
    }
}
