package com.my.demo.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hyl
 * @create 2021-05-21
 * @version: branch_member_20210510_v4_3_4
 */
public class JvmOomTest {

    static class OomObject{

    }

    public static void main(String[] args) {
        List<OomObject> list = new ArrayList<>();
        while(true) {
            list.add(new OomObject());
        }
    }
}
