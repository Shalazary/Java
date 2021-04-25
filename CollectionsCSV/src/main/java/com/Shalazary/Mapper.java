package com.Shalazary;

public abstract class Mapper<T, R> {
    public abstract R map(T mappingObj) throws Exception;
}
