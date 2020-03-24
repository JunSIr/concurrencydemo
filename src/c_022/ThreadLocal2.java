package c_022;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal线程局部变量
 *
 * ThreadLocal是使用空间换时间，synchronized是使用时间换空间
 * 比如在hibernate中的session就存在ThreadLocal中，避免synchronized的使用
 */
public class ThreadLocal2 {
    //volatile static Person p =new Person();
    static ThreadLocal<Person> t1=new ThreadLocal<>(); /*不使用volatile,各个线程维护各自的"本地副本”*/

    public static void main(String[] args) {
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(t1.get()); /*使用get获取默认值，get不到线程2中的new Person 所以结果为null*/

        }).start();


        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t1.set(new Person()); /*set->设置副本为new Person*/
        }).start();
    }

    static class Person{
        String name="zhangsan";
    }
}



