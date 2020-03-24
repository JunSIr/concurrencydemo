package c_025;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/*A blocking queue in which each insert operation must wait for a corresponding remove operation by another thread, and vice versa. */

public class T09_SynchronusQueue {  //容量为0
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> strs=new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.put("aaa");  //阻塞等待消费者消费
        //strs.add("aaa");
        System.out.println(strs.size());
    }
}
