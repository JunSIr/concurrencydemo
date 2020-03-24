package c_008;

import java.util.concurrent.TimeUnit;

/**   DirtyRead
 * 对业务写方法加锁
 * 对业务读方法不加锁
 * 容易产生脏读问题(dirtyRead)
 * 写还没写完  先读过去了  就是脏读
 */
class Account {
    private String name;
    private double balance; //余额

    public synchronized void set(String name,double balance){
        this.name=name;
        try {
            Thread.sleep(2000);   /*模拟睡眠器间，读线程执行完毕，这时张三的余额初始为0*/
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance=balance;
    }

    public /*synchronized*/ double getBalance(String name) {
        return this.balance;
    }

    public static void main(String[] args) {
        Account a=new Account();
        new Thread(()->a.set("张三",100.0)).start(); /*写线程*/

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(a.getBalance("张三"));

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(a.getBalance("张三")); /*读线程*/

    }
}
