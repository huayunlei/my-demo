package com.my.demo.test;

import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;

/**
 * @author hyl
 * @create 2020-05-27
 * @version:
 */
public class GuavaRangeTest {

    public static void main(String[] args) {
        RangeMap<Integer, String> level = TreeRangeMap.create();
        level.put(Range.closed(90,100), "A");
        level.put(Range.closedOpen(80,90), "B");
        level.put(Range.lessThan(80), "C");

        System.out.println(level.get(95));
        System.out.println(level.get(85));
        System.out.println(level.get(75));

    }
}
