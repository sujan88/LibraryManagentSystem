package midexam.useinterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hards on 9/16/2016.
 */
public class Test {

    public static void main(String arg[]){

        Person emp =  new Person("1","sujan","duminda",new ArrayList(),"1222233","suja@gmal.com");
        PersonRole role1= new Admin();
        PersonRole role2= new Librarian();

        emp.getRole().add(role1);
        emp.getRole().add(role2);
    }
}
