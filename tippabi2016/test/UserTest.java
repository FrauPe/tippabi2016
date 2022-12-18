
import server.User;

/**
 *
 * @author Pardella
 */
public class UserTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        User u1 = new User("a","1");
        User u2 = new User("b","2");
        System.out.println(u1.getUserID()+" "+u2.getUserID());
    }
    
}
