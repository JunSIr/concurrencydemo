package c_007;

/**
 * 同步和非同步方法是否可以同时调用
 * 可以
 */
class T {

    /*同步方法*/
    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName()+"m1 start...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" m1 end");
    }

    /*非同步方法*/
    public void m2(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" m2");
    }

    public static void main(String[] args) {
        T t=new T();
        new Thread(()->t.m1(),"t1").start();
        new Thread(()->t.m2(),"t2").start();

        /*
        * 等效写法
        new Thread(t::m1,"t1").start();
        new Thread(t::m2,"t2").start();*/
    }
}
