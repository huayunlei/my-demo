package com.my.demo;

import org.junit.Test;

/**
 * @author 72084513
 * @create 2021-06-17
 * @version:
 */
public class StringBufferTest {
    @Test
    public void test1() {
        StringBuffer stringBuffer = new StringBuffer("");
        System.out.println(stringBuffer.toString());
        System.out.println(stringBuffer.length());
    }
}
