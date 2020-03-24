package c_025;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * https://blog.csdn.net/sunxianghuang/article/details/52221913
 * 阅读ConcurrentSkipListMap
 */

/*API给出的定义：
* The map is sorted according to the natural ordering of its keys,
* or by a Comparator provided at map creation time,
* depending on which constructor is used.
* */
public class T01_ConcurrentMap {
    public static void main(String[] args) {

        Map<String,String> map= new ConcurrentSkipListMap<>();    //高并发并且排序

        /*另外一些Map容器*/
        //Map<String,String> map= new ConcurrentHashMap<>();
        //Map<String,String> map= new Hashtable<>();
        //Map<String,String> map= new HashMap<>();
        //TreeMap
        Random r=new Random();
        Thread[] ths=new Thread[100];
        CountDownLatch latch=new CountDownLatch(ths.length);
        long start=System.currentTimeMillis();


        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    /*If the specified key is not already associated with a value, associate it with the given value*/
                    map.put("a"+r.nextInt(100000),"a"+r.nextInt(100000));
                    latch.countDown();
                }
            });
        }

        Arrays.asList(ths).forEach(t->t.start());

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        long end=System.currentTimeMillis();
        System.out.println(end-start);
    }
}
