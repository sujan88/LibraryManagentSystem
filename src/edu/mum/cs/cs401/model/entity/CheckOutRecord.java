package edu.mum.cs.cs401.model.entity;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by 985333 on 9/14/2016.
 */
public class CheckOutRecord
{
    public SimpleListProperty<CheckOutEntry> checkOutEntryList =  new SimpleListProperty<>();

    public List<CheckOutEntry> getCheckOutEntryList() {
        return checkOutEntryList.get();
  }

    public void setCheckOutEntryList(ObservableList<CheckOutEntry> checkOutEntryList) {
        this.checkOutEntryList.set(checkOutEntryList);
    }

    public void addListCheckoutEntries(List<CheckOutEntry> checkOutEntryList) {
        ObservableList<CheckOutEntry> observableList = FXCollections.observableArrayList(checkOutEntryList);
        this.checkOutEntryList = new SimpleListProperty<>(observableList);
    }
}
