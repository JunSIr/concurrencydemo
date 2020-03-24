package c_026;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 应用
 * nasa --> 平行（并行）计算
 */
public class T07_ParallelComputing {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /*正常计算*/
        long start=System.currentTimeMillis();
        List<Integer> results=getPrime(1,200000);
        long end=System.currentTimeMillis();
        System.out.println(end-start);

        /*CPU核心数*/
        final int cpuCoreNum=4;

        /*线程池方式*/
        ExecutorService service= Executors.newFixedThreadPool(5);


        MyTask t1=new MyTask(1,80000);
        MyTask t2=new MyTask(80001,130000);
        MyTask t3=new MyTask(130001,170000);
        MyTask t4=new MyTask(170000,200000);

        Future<List<Integer>> f1=service.submit(t1);
        Future<List<Integer>> f2=service.submit(t2);
        Future<List<Integer>> f3=service.submit(t3);
        Future<List<Integer>> f4=service.submit(t4);

        start=System.currentTimeMillis();
        f1.get();
        f2.get();
        f3.get();
        f4.get();

        end=System.currentTimeMillis();
        System.out.println(end-start);



    }
                            /*implements Runnable*/
    static class MyTask implements Callable<List<Integer>>{

        /*开始位置与结束位置*/
        int startPos,endPos;

        /*构造器*/
        MyTask(int s,int e){
            this.startPos=s;
            this.endPos=e;
        }

        /*重写Callable的cALL方法 --> 大致跟run方法一样*/
        @Override
        public List<Integer> call() throws Exception {
            List<Integer> r=getPrime(startPos,endPos);
            return r;
        }
    }

    /*判断质数*/
    private static boolean isPrime(int num){
        for (int i = 2; i < num / 2; i++) {
            if (num % i == 0){
                return false;
            }
        }
        return true;
    }

    /*获取质数*/
    private static List<Integer> getPrime(int start, int end) {
        List<Integer> result=new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if (isPrime(i)){
                result.add(i);
            }
        }
        return result;
    }
}
