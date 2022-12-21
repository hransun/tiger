package vehicle_builder.server.adapter;

import vehicle_builder.common.model.Vehicle;
import vehicle_builder.common.model.VehicleFactory;
import vehicle_builder.server.service.VehicleStore;

public class VehicleBuilderImpl implements VehicleBuilder{
    private VehicleStore store;

    public VehicleBuilderImpl() {
        store = new VehicleStore();
    }

    @Override
    public Vehicle createVehicle(String owner, int price) {
        Vehicle vehicle = VehicleFactory.createVehicle(owner,price);
        store.addVehicle(vehicle);
        return vehicle;
    }
}
