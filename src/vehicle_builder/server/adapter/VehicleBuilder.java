package vehicle_builder.server.adapter;

import vehicle_builder.common.model.Vehicle;

public interface VehicleBuilder {
    Vehicle createVehicle(String owner, int price);
}
