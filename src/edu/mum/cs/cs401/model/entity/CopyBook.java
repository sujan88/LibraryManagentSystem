package edu.mum.cs.cs401.model.entity;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by 985333 on 9/13/2016.
 */
public class CopyBook {

    public SimpleStringProperty copyId = new SimpleStringProperty();
    public SimpleStringProperty availablity = new SimpleStringProperty("Yes");

    public CopyBook(String copyNumber) {
        this.copyId.set( copyNumber);
    }

    public String getCopyId() {
        return copyId.get();
    }

    public void setCopyId(String copyId) {
        this.copyId.set(copyId);
    }

    public String getAvailablity() {
        return availablity.get();
    }

    public void setAvailablity(String availablity) {
        this.availablity.set(availablity);
    }

    @Override
    public String toString() {
        return "CopyBook{" +
                "copyNumber=" + copyId +
                ", availablity=" + availablity +
                '}';
    }
}
