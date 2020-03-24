package c_026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的概念
 */
public class T05_ThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service= Executors.newFixedThreadPool(5);   //execute submit
        for (int i = 0; i < 6; i++) {
            service.execute(()->{
                try {

                    TimeUnit.MILLISECONDS.sleep(5000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        /*打印线程池信息*/
        System.out.println(service);

        /*等任务执行完成后结束*/
        service.shutdown();
        System.out.println(service.isTerminated());

        /*是否shutdown状态*/
        System.out.println(service.isShutdown());

        System.out.println(service);

        TimeUnit.SECONDS.sleep(10);/*给时间让任务都执行完，根据CPU的性能，需要调控好时间才能看出区别*/

        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
        System.out.println(service);


    }
}
