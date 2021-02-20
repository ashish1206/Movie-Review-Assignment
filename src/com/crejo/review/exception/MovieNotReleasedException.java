package com.crejo.review.exception;

public class MovieNotReleasedException extends Exception{
    public MovieNotReleasedException(String msg){
        super(msg);
    }
}
