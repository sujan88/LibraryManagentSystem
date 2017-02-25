package edu.mum.cs.cs401.controller;

import edu.mum.cs.cs401.dataaccess.DataBase;
import edu.mum.cs.cs401.model.entity.Person;
import edu.mum.cs.cs401.util.LMessage;

/**
 * Created by 985333 on 9/13/2016.
 */
public class SignUpController {

    private static SignUpController instance = new SignUpController();

    public SignUpController() {

    }

    public static SignUpController getInstance(){
        return instance;
    }



    public  int getlastId(){
       return DataBase.userTable.getValueSize();
    }

    /**
     * only valid for Admin or Library persons.
     * @param person
     * @return
     */
    public  Person addPerson(Person person){
        return DataBase.userTable.add(person.getUsername() ,person);
    }

}
