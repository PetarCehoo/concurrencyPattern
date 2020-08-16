package threadLocal;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap:
 *
 *
 *
 *
 *
 *
 */
public class SharedMapWithUserContext implements Runnable{

    public static Map<Integer, Context> userContextPerUserId = new ConcurrentHashMap<>();

    private Integer userId;

    public Integer getUserId(){return userId;}

    private UserRepo userRepo=new UserRepo();

    public SharedMapWithUserContext(Integer userId) {
        this.userId = userId;
    }

    //void run method from Interface Runnable
    @Override
    public void run()
    {
        String userName=userRepo.getUserName(userId);
        System.out.println("user name "+userName+"user id "+userId);
        userContextPerUserId.put(userId,new Context(userName));
    }
}
