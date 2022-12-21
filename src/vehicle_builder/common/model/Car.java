package vehicle_builder.common.model;

/**
 * Created by Bob on 4/7/17.
 */
public class Car extends Vehicle {
    public Car(String owner, int price) {
        super(owner, price);
    }

    @Override
    public void start() {
        System.out.println("vehicle_builder.common.model.Car start...");
    }
}
