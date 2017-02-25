package edu.mum.cs.cs401.controller;

import edu.mum.cs.cs401.UI.IWindow;
import edu.mum.cs.cs401.dataaccess.DataAccess;
import edu.mum.cs.cs401.dataaccess.DataAccessImpl;
import edu.mum.cs.cs401.dataaccess.DataBase;
import edu.mum.cs.cs401.model.entity.Book;
import edu.mum.cs.cs401.model.entity.CopyBook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * Created by 985333 on 9/13/2016.
 */
public class BookController implements Initializable , IWindow{

    static Stage stage = null;
    //search book
    @FXML
    TextField  searchBookText;

    // The table's data
    private ObservableList<Book> data;

    // The table and columns
    @FXML
    TableView<Book> bookTableView;

    @FXML
    TableColumn titleCol;
    @FXML
    TableColumn authorCol;
    @FXML
    TableColumn isbnCol;
    @FXML
    TableColumn maxBorrowDaysCol;

    // text boxes of Add book process
    @FXML
    TextField isbnText;
    @FXML
    TextField authorText;
    @FXML
    TextField titleText;
    @FXML
    TextField  noofCopyBook;
    @FXML
    TextField  maxBorrowDaysText;

    private static BookController instance = new BookController();


    public BookController()
    {
        stage = new Stage();
    }

    public static BookController getInstance(){
        return instance;
    }

    public  Book addBook(Book book){
        return DataBase.bookTable.add(book.getIsbn() ,book);
    }

    public  Book deleteBook(String ISBN){
        return DataBase.bookTable.delete(ISBN);
    }

    public Book getBook(String isbn) {
        return DataBase.bookTable.get(isbn);
    }

    public Book modifyBook(String isbn, Book book) {
        return DataBase.bookTable.update(isbn, book);
    }

    static long nextId = 1;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set up the table data

        isbnCol.setCellValueFactory(
                new PropertyValueFactory<Book,String>("isbn")
        );
        titleCol.setCellValueFactory(
                new PropertyValueFactory<Book,String>("title")
        );
        authorCol.setCellValueFactory(
                new PropertyValueFactory<Book,String>("author")
        );
        maxBorrowDaysCol.setCellValueFactory(
                new PropertyValueFactory<Book,Integer>("maxBorrowDays")
        );

        data = FXCollections.observableArrayList();
        bookTableView.setItems(data);
    }

    /**
     * this method has been initialized on admin.fxml under onAction tag of add book button
     * @param event
     * @return
     */
    @FXML
    private void addBookBtnAction(ActionEvent event) {
        int copyNo = 1;
        String isbn = isbnText.getText();
        if (isbn.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("");
            alert.setContentText("Please add the ISBN code ");
            alert.showAndWait();
        } else if (getBook(isbn) == null)
        {
            try {
                copyNo = Integer.parseInt(noofCopyBook.getText());
            }
            catch (NumberFormatException e)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("");
                alert.setContentText("No of Copy should be a number and cannot be empty. ");
                alert.showAndWait();
                return;
            }

            List<CopyBook> copyBookList = new ArrayList<>();
            for (int i = 0; i < copyNo; i++) {
                copyBookList.add(new CopyBook(isbn + "_" + i));
            }
            Book book = new Book(copyBookList);
            book.isbn.setValue(isbn);
            book.title.setValue(titleText.getText());
            book.author.setValue(authorText.getText());
            book.maxBorrowDays.setValue(Integer.parseInt(maxBorrowDaysText.getText()));
            data.add(book); // Javafx Table insert

            addBook(book); // Databse insert queary
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("");
            alert.setContentText(" This Book is already added.! ");
            alert.showAndWait();
        }
    }


    /**
     * this method has been initialized on admin.fxml under onAction tag of search button
     * @param event
     * @return
     */
    @FXML
    private void searchBookBtnAction(ActionEvent event)
    {
        String isbnSearch = searchBookText.getText();
        if(isbnSearch.isEmpty()){
            DataBase.bookTable.getDb().values().stream().forEach(b-> data.add((b)));
        }
        else {
            Book book = getBook(isbnSearch);
            data.add(book); // load into JavaFx table
        }
    }

    @FXML
    private void clearBookTable(ActionEvent event)
    {
        data.clear();
    }

    @Override
    public void openWindow() throws Exception {
        GridPane root =  FXMLLoader.load(getClass().getResource("../UI/bookHandller.fxml"));
        Scene scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        stage.setTitle("Book Control Manager");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void adminHomeBtnAction(ActionEvent actionEvent) {
        BookController.getInstance().stage.hide();
        AdminController.getInstance().adminStage.show();
    }


}
