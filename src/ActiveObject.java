import java.util.concurrent.PriorityBlockingQueue;

/**
 * Active object pattern:
 * decouples method execution from method invocation for objects that each reside in their own thread of control.
 * the goal is to introduce concurrency  by using async method invocation and scheduler for handling requests'
 *
 *
 * key elements:
     * 1)proxy(client interface) public method provided by activeobject to client's
     * 2)dispatch queue-list of pending requests by client
     * 3)Scheduler-determine the order to execute the request
     * 4)callback-result to be obtained by proxy after a request is executed
 *
 * Comparable -interface contains one method compareTo()
 *
 * PriorityBlockingQueue - is an class concurrent blocking queue data structure implementation
 * in which objects are processed based on their priority,blocking added thread will block until there is an item
 * in the queue.
 *
 *
 *
 * author:petar
 */

public class ActiveObject {

    //inserts object type Task in the Queue
    class Task implements Comparable < Task > {

        // smaller number means higher priority
        int priority;
        String name;

        //constructor
        Task(String name, int priority)
        {
            this.name = name;
            this.priority = priority;
        }

        public int compareTo(Task other)
        {
            System.out.println("compare to called "+priority+"other "+other.priority);
            return Integer.compare(this.priority, other.priority);
        }
    }
    //priority blocking queue type Task
    private PriorityBlockingQueue< Task > dispatchQueue = new PriorityBlockingQueue < > ();

    //constructor
    public ActiveObject(){
        // A priority scheduler
        new Thread(() -> {
        while (true) {
            try {
                System.out.println("tried");
                Task task = dispatchQueue.take();
                System.out.println("Executing task " + task.name);
            } catch (InterruptedException e)
            {
                break;
            }
        }
      })
      .start();
    }
    //starter point
    public void doTask(String name, int priority)
    {
        //Inserts the specified element into this priority blocking queue.
        dispatchQueue.put(new Task(name, priority));
    }

    public static void main(String []args)
    {
        // Testing ActiveObject
        ActiveObject obj = new ActiveObject();

        // Call doTask in different threads
        Thread t1 = new Thread(() -> {
                obj.doTask("1", 2); });

        Thread t2 = new Thread(() -> {
                obj.doTask("2", 0); });

        Thread t3 = new Thread(() -> {
                obj.doTask("3", 1); });

        //start threads
        t1.start();
        t2.start();
        t3.start();
        //call function when threads are finished

    }
}
