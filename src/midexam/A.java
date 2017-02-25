package midexam;

/**
 * Created by hards on 9/16/2016.
 */
public class A {

    int sup= callNumber();
    static {
        System.out.println("Super static block");
    }

     int callNumber(){
        System.out.println("Call Super number");
        return 1;
    }

    public void m(){
        System.out.println("super M");
    }
    public A(){
        System.out.println("Super class created");
    }


   public static void mStatic(){
        System.out.println("super MStaic");
    }
}
