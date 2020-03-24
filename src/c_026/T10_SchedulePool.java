package c_026;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class T10_SchedulePool {
    public static void main(String[] args) {
        ScheduledExecutorService service= Executors.newScheduledThreadPool(4);

        /**
         * Creates and executes a periodic action that becomes enabled first
         * after the given initial delay*/
        service.scheduleAtFixedRate(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        },0,500,TimeUnit.MILLISECONDS); /*延迟参数在此*/
    }
}
