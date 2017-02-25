package midexam.useinterface;


import edu.mum.cs.cs401.model.entity.Address;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 985333 on 9/13/2016.
 */
public class Person {

    private List<PersonRole> role=new ArrayList<>();

    private SimpleStringProperty id = new SimpleStringProperty();
    private SimpleStringProperty firstName = new SimpleStringProperty();
    private SimpleStringProperty lastName = new SimpleStringProperty();
    private SimpleStringProperty email = new SimpleStringProperty();
    private SimpleListProperty<Address> addresses = null;
    private SimpleStringProperty phoneNumber = new SimpleStringProperty();


    public Person(String id, String firstName, String lastName, List<Address> addresses, String phoneNumber, String email) {
        this.id.set(id);
        this.firstName.set(firstName);
        this.lastName.set(lastName);

        ObservableList<Address> observableList = FXCollections.observableArrayList(addresses);
        this.addresses=new SimpleListProperty<>(observableList);

        this.phoneNumber.set(phoneNumber);
        this.email.set(email);

    }


    public List<PersonRole> getRole() {
        return role;
    }


}
