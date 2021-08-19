package com.my.demo.test;

/**
 * @author hyl
 * @create 2021-02-02
 * @version: branch_member_20210108_v4_3
 */
public class TestSynchronized {

    public static synchronized void test1()
    {
        int i = 5;
        while( i-- > 0)
        {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try
            {
                Thread.sleep(500);
            }
            catch (InterruptedException ie)
            {
            }
        }
    }

    public static synchronized void test2()
    {
        int i = 5;
        while( i-- > 0)
        {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try
            {
                Thread.sleep(500);
            }
            catch (InterruptedException ie)
            {
            }
        }
    }

    public void test3()
    {
        int i = 5;
        while( i-- > 0)
        {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try
            {
                Thread.sleep(500);
            }
            catch (InterruptedException ie)
            {
            }
        }
    }

    public static void test4()
    {
        int i = 5;
        while( i-- > 0)
        {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try
            {
                Thread.sleep(500);
            }
            catch (InterruptedException ie)
            {
            }
        }
    }

    public static void main(String[] args)
    {
        TestSynchronized myt1 = new TestSynchronized();
//        TestSynchronized myt2 = new TestSynchronized();
        Thread test1 = new Thread(  new Runnable() {  public void run() {  myt1.test1();  }  }, "test1"  );
        Thread test2 = new Thread(  new Runnable() {  public void run() { myt1.test2();   }  }, "test2"  );
        Thread test3 = new Thread(  new Runnable() {  public void run() { myt1.test3();   }  }, "test3"  );
        Thread test4 = new Thread(  new Runnable() {  public void run() { myt1.test4();   }  }, "test4"  );

        test1.start();
        test2.start();
        test3.start();
        test4.start();
//         TestRunnable tr=new TestRunnable();
//         Thread test3=new Thread(tr);
//         test3.start();
    }

}
