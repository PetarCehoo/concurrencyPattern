package threadLocal;


/**
 * Model class for userName
 * methods:
 * getUserName()->returns useranme;
 */

public class Context {

    private String userName;

    public Context(String userName){this.userName=userName;}

    public String getUserName(){return userName;}

}
