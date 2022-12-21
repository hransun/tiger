/**
 * Created by Bob on 4/7/17.
 */
package vehicle_builder.common.model;

import java.io.Serializable;

// ctl + o parent's obj
public abstract class Vehicle implements Serializable {
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
    public String toString(){
        return  String.format("%s buys %s at $%d",owner,this.getClass().getSimpleName(),getPrice());

    }

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
