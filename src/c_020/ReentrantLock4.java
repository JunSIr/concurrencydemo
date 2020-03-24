package c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock还可以调用lockInterruptibly方法，可以对线程interrupt方法做出响应
 * 在一个线程等待锁的过程中，可以被打断
 */
public class ReentrantLock4 {
    public static void main(String[] args) {
        Lock lock=new ReentrantLock();

        Thread t1=new Thread(()->{
            lock.lock(); /*加锁成功*/
            try {
                System.out.println("t1 start");

                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);/*无限睡眠、线程2拿不到锁*/
                System.out.println("t1 end");
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
                //e.printStackTrace();

            } finally {
                /*这里最终会执行，放给线程2*/
                lock.unlock();
            }
        });

        t1.start();


        Thread t2=new Thread(()->{

            try {
                /*在try中进行上锁-->导致异常 -->打断*/
                lock.lockInterruptibly(); //可以对interrupt()做出响应，在这被打断，执行finally方法-->try外定义则无法执行finally内的方法
                System.out.println("t2 start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t2 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("打断响应且释放锁");
                lock.unlock();

            }

        });

        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.interrupt();//打断线程2的等待
    }
}
