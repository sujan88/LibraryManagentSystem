package edu.mum.cs.cs401.dataaccess;

import edu.mum.cs.cs401.model.entity.Book;
import edu.mum.cs.cs401.model.entity.CheckOutEntry;
import edu.mum.cs.cs401.model.entity.Member;
import edu.mum.cs.cs401.model.entity.Person;

/**
 * Created by 985333 on 9/13/2016.
 */
public class DataBase {
    public static DataAccess<String , Book> bookTable = new DataAccessImpl<>(); // ISBN , BOOK
    public static DataAccess<String , Person> userTable = new DataAccessImpl<>(); // USRNAME , ADMIN/LIRAIAN
    public static DataAccess<String , Member> memberTable = new DataAccessImpl<>(); // memebrId , Member
    public static DataAccess<String , CheckOutEntry> checkOutEntryTable = new DataAccessImpl<>(); // ceId , CheckOutEntry

    public  static Member loadMember(String memebrId){
        return  DataBase.memberTable.get(memebrId);
    }
}
