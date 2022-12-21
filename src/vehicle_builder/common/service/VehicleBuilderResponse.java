package vehicle_builder.common.service;

import java.io.Serializable;

public class VehicleBuilderResponse implements Serializable {
    String message;

    public VehicleBuilderResponse() {
        this.message = "";
    }

    public VehicleBuilderResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
