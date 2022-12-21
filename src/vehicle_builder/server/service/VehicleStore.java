package vehicle_builder.server.service;

import vehicle_builder.common.model.Vehicle;
import vehicle_builder.util.SleepUtil;
import java.util.HashMap;
import java.util.Map;

public class VehicleStore {
    private static Map<String, Vehicle> vehicleMap = new HashMap<>();
    public synchronized void addVehicle(Vehicle  vehicle) {
        if (!vehicleMap.containsKey(vehicle.getOwner())) {
            vehicleMap.put(vehicle.getOwner(),vehicle);
            System.out.println(String.format("Successfully  add vehicle,owner: %s, price: %d",
                    vehicle.getOwner(),vehicle.getPrice()));
        } else {
            System.out.print(String.format("Duplicated owner: %s",vehicle.getOwner()));
        }
    }
}
