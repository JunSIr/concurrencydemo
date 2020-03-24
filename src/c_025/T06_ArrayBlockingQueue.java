package c_025;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
/*
backed by an arra--> 基于数组
* Once created, the capacity cannot be changed ->容量不可更改
* */

public class T06_ArrayBlockingQueue {
    static BlockingQueue<String> strs=new ArrayBlockingQueue<>(10);

    static Random r=new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            strs.put("a"+i);
        }

        //strs.put("aaa");  //满了就会等待，程序阻塞
        //strs.add("aaa");
        //strs.offer("aaa");
        strs.offer("aaa",1, TimeUnit.SECONDS);
        /*Inserts the specified element at the tail of this queue
        *if it is possible to do so immediately without exceeding the queue's capacity,
         *  returning true upon success and false if this queue is full.
         *  容量满了就不插入了 返回布尔
         *  */

        System.out.println(strs);
    }
}
