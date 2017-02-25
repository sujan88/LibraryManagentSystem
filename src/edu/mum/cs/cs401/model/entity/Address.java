package edu.mum.cs.cs401.model.entity;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by 985333 on 9/13/2016.
 */
public class Address {
    private SimpleStringProperty street = new SimpleStringProperty();
    private SimpleStringProperty city  = new SimpleStringProperty();
    private SimpleStringProperty state  = new SimpleStringProperty();
    private SimpleStringProperty zip = new SimpleStringProperty();

    public Address(String street, String city, String state, String zip) {
        this.street.set(street);
        this.city.set(city);
        this.state.set(state);
        this.zip.set(zip);
    }

    public String getStreet() {
        return street.get();
    }
    public void setStreet(String street) {
        this.street.set(street);
    }
    public String getCity() {
        return city.get();
    }
    public void setCity(String city) {
        this.city.set(city);
    }
    public String getState() {
        return state.get();
    }
    public void setState(String state) {
        this.state.set(state);
    }
    public String getZip() {
        return zip.get();
    }
    public void setZip(String zip) {
        this.zip .set(zip);
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}
