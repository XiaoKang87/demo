package com.example.demo;

import java.util.function.Function;

/**
 * Created by Wu.Kang on 2017/6/12.
 */
public class FunctionTest {
    String intToString(IntFunction<String> func) {
        return func.apply(13);
    }

    interface IntFunction<R> extends Function<Integer, R> {

    }
}
