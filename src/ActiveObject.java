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
 *
 *
 *
 *
 * author:petar
 */

public class ActiveObject {


    class Task implements Comparable < Task > {
        // smaller number means higher priority
        int priority;
        String name;
        Task(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }
        public int compareTo(Task other) {
            return Integer.compare(this.priority, other.priority);
        }
    }
    private PriorityBlockingQueue< Task > dispatchQueue = new PriorityBlockingQueue < > ();
    public ActiveObject() {
        // A priority scheduler
        new Thread(() -> {
        while (true) {
            try {
                Task task = dispatchQueue.take();
                System.out.println("Executing task " + task.name);
            } catch (InterruptedException e) {
                break;
            }
        }
      })
      .start();
    }
    public void doTask(String name, int priority) {
        dispatchQueue.put(new Task(name, priority));
    }

    public static void main(String []args)
    {
// Testing ActiveObject
        ActiveObject obj = new ActiveObject();
// Call doTask in different threads
        Thread t1 = new Thread(() -> {
                obj.doTask("1", 2);
});
        Thread t2 = new Thread(() -> {
                obj.doTask("2", 0);
});
        Thread t3 = new Thread(() -> {
                obj.doTask("3", 1);
});
        t1.start();
        t2.start();
        t3.start();


    }
}
