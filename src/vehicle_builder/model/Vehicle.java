/**
 * Created by Bob on 4/7/17.
 */
package vehicle_builder.model;
// ctl + o parent's obj
public abstract class Vehicle {
    private String owner;
    private int price;

    public Vehicle(String owner, int price) {
        this.owner = owner;
        this.price = price;
    }

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

    public abstract void start();

    @Override
    public int hashCode() {
        return super.hashCode();
        // mem address
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
