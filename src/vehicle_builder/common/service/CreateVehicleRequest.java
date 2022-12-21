package vehicle_builder.common.service;

public class CreateVehicleRequest extends VehicleBuilderRequest{
    // getter and setter and constructor
    private String owner;

    public CreateVehicleRequest(String owner, int price) {
        this.owner = owner;
        this.price = price;
    }

    private int price;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
