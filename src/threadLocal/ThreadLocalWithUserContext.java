package threadLocal;

/**
 * example of storing user data in ThreadLocal of Context();
 *
 *
 *
 *
 */
public class ThreadLocalWithUserContext implements  Runnable  {

    /**
     * ThreadLocal:provides thread local variables:differnece from normal counterparts is that each thread acesses one
     * has its own independently copy of the variable.
     */
    private static ThreadLocal<Context>userContext=new ThreadLocal<>();


    private Integer userId;
    //class for database access(simulation):getting username out of userId
    private UserRepo userRepo=new UserRepo();


    //constructor for setting the userId value
    public ThreadLocalWithUserContext(int userId)
    {
        this.userId=userId;
    }

    @Override
    public void run()
    {
        String userName=userRepo.getUserName(userId);
        //Sets the current thread's copy of this thread-local variable to the specified value.

        //set(T value)
        userContext.set(new Context(userName));

        System.out.println("Thread context for given user_id: "+userId+"  "+userContext.get());
    }
}
