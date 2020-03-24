package c_025;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;


public class T07_DelayQueue {
    static BlockingQueue<MyTask> tasks=new DelayQueue<>();

    static class MyTask implements Delayed{

        long runningTime;

        /*构造器*/
        MyTask(long rt){
            this.runningTime=rt;
        }

        @Override
        public long getDelay(TimeUnit unit) {

            return unit.convert(runningTime-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
        }

        @Override/*重写了conpareTo方法*/
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS)<o.getDelay(TimeUnit.MILLISECONDS)){
                return -1;
            }else if (this.getDelay(TimeUnit.MILLISECONDS)>o.getDelay(TimeUnit.MILLISECONDS)){
                return 1;
            }
            return 0;
        }

        @Override/*重写tuString方法*/
        public String toString() {
            return ""+runningTime;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long now=System.currentTimeMillis();
        MyTask t1=new MyTask(now+1000);
        MyTask t2=new MyTask(now+2000);
        MyTask t3=new MyTask(now+1500);
        MyTask t4=new MyTask(now+2500);
        MyTask t5=new MyTask(now+500);

        tasks.put(t1);
        tasks.put(t2);
        tasks.put(t3);
        tasks.put(t4);
        tasks.put(t5);

        System.out.println(tasks);

        for (int i = 0; i < 5; i++) {
            System.out.println(tasks.take());
        }
    }
}
