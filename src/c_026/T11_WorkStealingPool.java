package c_026;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static javafx.scene.input.KeyCode.R;


public class T11_WorkStealingPool {
    public static void main(String[] args) throws IOException {
        /*Creates a work-stealing thread pool using all available processors as its target parallelism level.
        * 平行 -> using all available processors
        * */
        ExecutorService service= Executors.newWorkStealingPool();

        System.out.println(Runtime.getRuntime().availableProcessors());

        service.execute(new R(1000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));

        //由于产生的精灵线程(守护线程、后台线程)，主线程不阻塞的话，看不到输出
        System.in.read();
    }

    static class R implements Runnable{

        int time;

        R(int t){
            this.time=t;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(time+" "+Thread.currentThread().getName());
        }
    }
}
