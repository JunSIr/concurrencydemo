package c_009;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法可以调用另一个同步方法，一个线程已经拥有了某个对象的锁，再次申请的时候仍然会得到该对象的锁
 * 也就是说synchronized获得的锁是可重入的  -->synchronized为可重用锁
 */
class T {
    synchronized void m1(){
        System.out.println("m1 start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();  /*执行m2需要获得类型为T的实例对象（锁对象），在m1线程持有该锁的情况下，还可以继续申请该锁，且仍会得到*/
    }

    synchronized void m2(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("m2");
    }
}
