package threadLocal;


/**
 * ThreadLocal(extends Object)  from java.util package
 * class provides thread-local variables:
 * these variables differ from their normal counterparts in that each thread that accesses one (via its get or set method) has its own,
 * independently initialized copy of the variable
 *
 * Construct allows us to store the data that will be accesible only    by a specific thread.
 * having variables specific to a thread its called ThreadLocal
 *
 * first example is not gonna use the threadlocal ,second yes
 * first example :Storing user data in Maps:
 *
 *
 * author:petar
 */

public class App {

    public static void main(String[] arags) throws InterruptedException {

        SharedMapWithUserContext firstUser = new SharedMapWithUserContext(1);
        SharedMapWithUserContext secondUser = new SharedMapWithUserContext(2);

        Thread t1= new Thread(firstUser);
        Thread t2= new Thread(secondUser);

        t1.start();
        t2.start();

        t1.join();
        t1.join();






    }
}