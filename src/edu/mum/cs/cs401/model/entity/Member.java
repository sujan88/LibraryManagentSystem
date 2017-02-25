package edu.mum.cs.cs401.model.entity;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;

/**
 * Created by 985333 on 9/13/2016.
 */
public class Member  extends  Person{

    public SimpleObjectProperty<CheckOutRecord> checkOutRecord= new SimpleObjectProperty<>();

    public CheckOutRecord getCheckOutRecord() {
        return checkOutRecord.get();
    }

    public SimpleObjectProperty<CheckOutRecord> checkOutRecordProperty() {
        return checkOutRecord;
    }

    public void setCheckOutRecord(CheckOutRecord checkOutRecord) {
        this.checkOutRecord.set(checkOutRecord);
    }

    public Member(String id, String firstName, String lastName, List<Address> addresses, String phoneNumber, String email, String role) {
        super(id, firstName, lastName, addresses, phoneNumber, email, Person.MEMBER);
    }
}
