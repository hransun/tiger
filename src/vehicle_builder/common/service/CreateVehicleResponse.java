package vehicle_builder.common.service;

import vehicle_builder.common.model.Vehicle;

public class CreateVehicleResponse extends VehicleBuilderResponse{
    private Vehicle vehicle;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public CreateVehicleResponse() {
        this.vehicle = null;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
