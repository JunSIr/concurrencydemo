package c_025;

import java.util.concurrent.LinkedTransferQueue;

/*A BlockingQueue in which producers may wait for consumers to receive elements.
 A TransferQueue may be useful for example in message passing applications in which producers sometimes (using method transfer(E)) await receipt of elements by consumers invoking take or poll,
  .transfer:Transfers the element to a consumer, waiting if necessary to do so

    *LinkedTransferQueue
    * take:Retrieves and removes the head of this queue, waiting if necessary until an element becomes available.
    * poll:Retrieves and removes the head of this queue, or returns null if this queue is empty.
    * 简而言之，一个等，一个不等
 */

public class T08_TransferQueue {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs=new LinkedTransferQueue<>();

        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //strs.transfer("aaa");
        strs.put("aaa");

        /*new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/
    }
}
