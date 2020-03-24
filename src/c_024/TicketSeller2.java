package c_024;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * 有n张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 *
 * 分析下面的程序可能会产生哪些问题？
 * 重复销售？超量销售？
 *
 */
public class TicketSeller2 {
    /*可变数组-->Vector  -->线程安全的
    * As of the Java 2 platform v1.2, this class was retrofitted to implement the List interface,
     * making it a member of the Java Collections Framework. Unlike the new collection implementations,
     * Vector is synchronized. If a thread-safe implementation is not needed,
     * it is recommended to use ArrayList in place of Vector.
    * */

    static Vector<String> tickets=new Vector<>();

    static {
        for (int i = 0; i < 100; i++) {
            /*tickets余量为100  -> 超量会报异常
            * vector只是个容器，需要先往里面扔东西
            * vector的容量会自动增长
            * vector的存储不是线性的... 所以出来的结果会增增减减*/
            tickets.add("票编号："+i);
        }
    }

    public static void main(String[] args) {
        /*一次十个线程买票*/
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
               while (tickets.size()>0){

            /*如果加上下面这段代码
            结果不是顺序（无syn的情况下）《--判断与操作分离-->判断可以取，别的线程打断，提前取了，这边就出异常了
            解决方法：可以加syn
             try {
                       TimeUnit.MILLISECONDS.sleep(1000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }*/

                   System.out.println(Thread.currentThread().getName()+"销售了--"+tickets.remove(0));
               }
            },"窗口"+i).start();
        }
    }
}
