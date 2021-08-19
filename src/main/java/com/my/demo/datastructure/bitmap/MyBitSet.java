package com.my.demo.datastructure.bitmap;

/**
 * 位图
 * @author hyl
 * @create 2021-02-03
 * @version: branch_member_20210108_v4_3
 */
public class MyBitSet {

    private final char[] aChar; // 我们定义一个字符数组其大小
    private final int size;
    public MyBitSet(int size){
        // 初始化一个可以存size大小的位图
        // 这里当size为16时候，由于每个char元素占用16位，所以，我们初始化
        // 1个字节的字节数组就可以了
        aChar = new char[ size % 16 == 0? size / 16  : size /16 +1];
        this.size = size;
    }
    ///存储i数字
    public void set(int i) {
        // 当我们i能被16整除的时候
//        int index = i % 16 == 0? i / 16 - 1 : i /16;
//        int splitindex = 1 << (i - index * 16 - 1);

        int index = i >> 4;
        int splitindex = 1 << (i & 15);

        aChar[index] = (char) (aChar[index] | splitindex);
    }
    public boolean get(int i) {
//        int index = i % 16 == 0? i / 16 -1 : i /16;
//        int splitindex = 1 << (i - index * 16 - 1);

        int index = i >> 4;
        int splitindex = 1 << (i & 15);

        int i1 = aChar[index] & splitindex;
        return i1 == splitindex;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < aChar.length; i++) {
            sb.append(aChar[i]);
            System.out.println(Integer.toBinaryString(aChar[i]));
        }
        return sb.toString();
    }
    public static void main(String[] args) {

        int size = 1234567890; // 存储12亿3千万个数组
        MyBitSet myBitSet = new MyBitSet(size);
        myBitSet.set(1);
        myBitSet.set(2);
        myBitSet.set(3);
        myBitSet.set(31);
        myBitSet.set(32);
        myBitSet.set(16);
        System.out.println(myBitSet.aChar.length);

        for (int i = 1; i <= size; i++) {
            boolean b = myBitSet.get(i);
            if(b) System.out.println(i);
        }
//        long l = RamUsageEstimator.sizeOf(myBitSet);
//        System.out.println("size: "+l); //  size: 154321032 140m数据
    }

}
