package c_026;

import java.util.concurrent.*;


public class T06_Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /*定义任务*/
        FutureTask<Integer> task=new FutureTask<>(()->{
            /*相当于定义run方法*/
            TimeUnit.MILLISECONDS.sleep(500);
            return 1000;
        });

        new Thread(task).start(); /*新创建线程来执行这个任务task*/

        System.out.println(task.get());//阻塞，等待计算完成，取回结果 --> 1000

        /*线程池操作*/
        ExecutorService service= Executors.newFixedThreadPool(5);
        /*任务定义*/
        Future<Integer> f=service.submit(()->{
            TimeUnit.MILLISECONDS.sleep(500);
            return 1;
        });

        System.out.println(f.get());
        System.out.println(f.isDone());

    }
}
