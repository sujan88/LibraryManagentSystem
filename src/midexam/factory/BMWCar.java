package midexam.factory;

/**
 * Created by hards on 9/16/2016.
 */
public class BMWCar implements Car {
    @Override
    public double calculatePrice() {
        System.out.println("BMX");
        return 200000;
    }
}
