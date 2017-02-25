package midexam.factory;

/**
 * Created by hards on 9/16/2016.
 */
public class CommanBMWSport extends BMWCar  {

    @Override
    public double calculatePrice() {
        System.out.println("Common");
       super.calculatePrice();
        return 3000;
    }
}
