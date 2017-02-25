package midexam.factory;

/**
 * Created by hards on 9/16/2016.
 */
public class CarFactory
{
   static Car createCar(String carType){
        Car car;
        if(carType.equals("SPORT"))
        {
           return new SportyCar();
        }
        else if (carType.equals("BMW")){
           return new BMWCar();
        }
        else  if(carType.equals("COMMON")){
            return new CommanBMWSport();
        }
        return null;
    }
}
