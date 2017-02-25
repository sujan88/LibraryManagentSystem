package edu.mum.cs.cs401.model.entity;



import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 985333 on 9/13/2016.
 */
public class Book {
    public SimpleStringProperty isbn = new SimpleStringProperty();
    public SimpleStringProperty title = new SimpleStringProperty();
    public SimpleStringProperty author = new SimpleStringProperty();
    public SimpleIntegerProperty maxBorrowDays = new SimpleIntegerProperty();
    public SimpleListProperty<CopyBook> copyBooks = new SimpleListProperty<>();


    public Book(List<CopyBook> copyBooksList)
    {
        ObservableList<CopyBook> observableList = FXCollections.observableArrayList(copyBooksList);
        this.copyBooks=new SimpleListProperty<>(observableList);

    }
    public String getIsbn() {
        return isbn.get();
    }


    public String getTitle() {
        return title.get();
    }


    public String getAuthor() {
        return author.get();
    }

    public List<CopyBook> getCopyBooks() {
        return copyBooks.get();
    }


    public int getMaxBorrowDays() {
        return maxBorrowDays.get();
    }

    public void setMaxBorrowDays(int maxBorrowDays) {
        this.maxBorrowDays.set(maxBorrowDays);
    }



    @Override
    public String toString() {
        return "Book{" +
                "ISBN='" + isbn.get() + '\'' +
                ", title='" + title.get() + '\'' +
                ", auther='" + author.get() + '\'' +
                ", copyBooks=" + copyBooks.get() +
                '}';
    }
}
