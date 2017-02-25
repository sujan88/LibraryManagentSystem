package edu.mum.cs.cs401.model.entity;


import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.StringJoiner;

/**
 * Created by 985333 on 9/13/2016.
 */
public class Person {
    private SimpleStringProperty id = new SimpleStringProperty();
    private SimpleStringProperty firstName = new SimpleStringProperty();
    private SimpleStringProperty lastName = new SimpleStringProperty();
    private SimpleStringProperty email = new SimpleStringProperty();
    private SimpleListProperty<Address> addresses = null;
    private SimpleStringProperty phoneNumber = new SimpleStringProperty();
    private SimpleStringProperty username = new SimpleStringProperty();
    private SimpleStringProperty password = new SimpleStringProperty();
    public final static String ADMIN="A";
    public final static  String LIBRARIAN="L";
    public final static  String MEMBER="M";
    private SimpleStringProperty role = new SimpleStringProperty();

    public Person(String id, String firstName, String lastName, List<Address> addresses, String phoneNumber,String email, String role) {
        this.id.set(id);
        this.firstName.set(firstName);
        this.lastName.set(lastName);

        ObservableList<Address> observableList = FXCollections.observableArrayList(addresses);
        this.addresses=new SimpleListProperty<>(observableList);

        this.phoneNumber.set(phoneNumber);
        this.email.set(email);
        this.role.set(role);
    }

    public String getId() {
        return id.get();
    }
    public void setId(String id) {
        this.id.set(id);
    }
    public String getFirstName() {
        return firstName.get();
    }
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
    public String getLastName() {
        return lastName.get();
    }
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }
    public List<Address> getAddresses() {
        return addresses.get();
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses.addAll(addresses);
    }


    public void setUsername(String username) {
        this.username.set(username);
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getUsername() {
        return username.get();
    }

    public String getPassword() {
        return password.get();
    }


    public String getRole() {
        return role.get();
    }

    public void setRole(String role) {
        this.role.set(role);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }


}
