package midexam.factory;

/**
 * Created by hards on 9/16/2016.
 */
public class Test
{
    public static void main(String arg[]){
       Car car= CarFactory.createCar("COMMON");
           System.out.println(car.calculatePrice());
    }
}
