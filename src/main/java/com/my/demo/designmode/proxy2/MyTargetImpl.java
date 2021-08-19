package com.my.demo.designmode.proxy2;

/**
 * @author hyl
 * @create 2020-05-20
 * @version:
 */
public class MyTargetImpl implements MyTarget {
    @Override
    public void execute1() {
        System.out.println(" MyTarget execute1 ");
    }

    @Override
    public void execute2() {
        System.out.println(" MyTarget execute2 ");
    }


}
