package vehicle_builder.adapter;

import vehicle_builder.model.Vehicle;
import vehicle_builder.model.VehicleFactory;
import vehicle_builder.service.VehicleStore;

public class VehicleBuilderImpl implements VehicleBuilder {
    private VehicleStore store;
    public VehicleBuilderImpl() {
        store = new VehicleStore();
    }
    @Override
    public void createVehicle(String owner, int price) {
        Vehicle vehicle = VehicleFactory.createVehicle(owner,price);
        store.addVehicle(vehicle);

    }

    @Override
    public void displayVehicle(String owner) {

    }

    @Override
    public void displayVehicle(Vehicle vehicle) {

    }
}
