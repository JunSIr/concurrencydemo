package c_022;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal线程局部变量
 */
public class ThreadLocal1 {
    volatile static Person p =new Person();  /*使用volatile-->线程可见
                                                *注意这里不写Volatile可能发生问题，也可能不发生问题*/

    public static void main(String[] args) {
        /*查到pname*/
        new Thread(()->{
            System.out.println(p.name);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(p.name);
        }).start();

        /*修改name为lisi*/
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.name="lisi1";
        }).start();
    }
}

class Person{
    /*默认name为zhangsan*/
    String name="zhangsan";
}
