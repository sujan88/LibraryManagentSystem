package midexam;

import edu.mum.cs.cs401.dataaccess.DataBase;
import edu.mum.cs.cs401.model.entity.CheckOutEntry;
import edu.mum.cs.cs401.model.entity.Member;

import java.util.List;

/**
 * Created by hards on 9/16/2016.
 */
public class ASUB extends A {

     int sub= callNumber();

    static {
        System.out.println("Sub static block");
    }

     int callNumber(){
        System.out.println("Call sub number");
        return 1;
    }

    public ASUB(){
        System.out.println("Sub class created");
    }

    @Override
    public void m(){
        System.out.println("sub M");
    }



    public  static void mStatic(){
        System.out.println("sub MStaic");
    }


    public static void main(String arg[]){
        A a=new ASUB()
                ;
//        a.m();
//        A.mStatic();
//        ASUB.mStatic();
    }

}
