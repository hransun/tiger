package vehicle_builder.common.service;

import java.io.Serializable;

public class VehicleBuilderRequest implements Serializable {
    String message;

    public VehicleBuilderRequest() {
        this.message = "";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
