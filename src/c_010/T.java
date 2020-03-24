package c_010;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法可以调用另外一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候仍然会得到该对象的锁，
 * 也就是说synchronized获得的锁是可重入的
 * 这里是继承中可能发生的情形，子类调用父类方法  -->可重入锁保证继承的机制
 */
class T {
    synchronized void m(){
        System.out.println("m start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("m end");
    }
    /*主方法*/
    public static void main(String[] args) {
        new TT().m();
    }
}

        /*内部类*/
class TT extends T{
    @Override
    synchronized void m(){
        System.out.println("child m start");
        super.m();  /*调用父类m方法，申请的是“T”类型实例对象*/
        System.out.println("chile m end");
    }
}
