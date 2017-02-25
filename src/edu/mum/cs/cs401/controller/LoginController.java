package edu.mum.cs.cs401.controller;

import edu.mum.cs.cs401.dataaccess.DataBase;
import edu.mum.cs.cs401.util.LMessage;
import edu.mum.cs.cs401.model.entity.Person;

/**
 * Created by 985333 on 9/13/2016.
 */
public class LoginController {

    private static LoginController instance = new LoginController();

    public LoginController() {

    }

    public static LoginController getInstance(){
        return instance;
    }

    public  LMessage login(String uname, String pword) {
        Person p = DataBase.userTable.get(uname);

        if (p != null && p.getPassword().equals(pword)) {
            return new LMessage(LMessage.SUCCESS,p);

        }
        return new LMessage(LMessage.FAILED,"INVALID USER NAME OR PASSWORD.");
    }

    public  int getlastId(){
       return DataBase.userTable.getValueSize();
    }

    /**
     * only valid for Admin or Library persons.
     * @param person
     * @return
     */
    public static Person addPerson(Person person){
        return DataBase.userTable.add(person.getUsername() ,person);
    }

}
