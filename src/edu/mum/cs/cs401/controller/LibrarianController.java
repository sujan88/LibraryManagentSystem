package edu.mum.cs.cs401.controller;

import edu.mum.cs.cs401.UI.IPersonWindow;
import edu.mum.cs.cs401.model.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


/**
 * Created by 985333 on 9/13/2016.
 */
public class LibrarianController implements Initializable , IPersonWindow {

    Librarian librarian = null;
    static  Stage librarianStage = null;
    @FXML
    TextField usernameTextField;

    @FXML
    TextField isbnTextField;

    @FXML
    TableView<Member> memberLibraryTable;

    @FXML
    TableColumn idMemberCol;

    @FXML
    TableColumn fnameMemberCol;

    @FXML
    TableColumn lnameMemberCol;

    @FXML
    TableColumn phoneMemberCol;

    @FXML
    TableView<CopyBook> copyOfBookTable;

    @FXML
    TableColumn copyNumberBookCol;

    @FXML
    TableColumn availableCopyBookBookCol;

    @FXML
    Label errorMessageLbl;

    // The table's data
    ObservableList<Member> dataMember;
    ObservableList<CopyBook> dataBook;

    private static LibrarianController instance = new LibrarianController();

    public LibrarianController(){
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address("1000 N", "fairfilef", "iowa", "50557"));
        Member memlibrary = new Member("3","New","Jung", addresses ,"322333", "new@gmail.com", Person.MEMBER);
        MemberController.getInstance().addMember(memlibrary);

        List<CopyBook> copyBookList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            copyBookList.add(new CopyBook("TB001_" + i));
        }
        Book book = new Book(copyBookList);
        book.isbn.setValue("TB001");
        book.title.setValue("TextBook");
        book.author.setValue("Jim");
        BookController.getInstance().addBook(book);
        librarianStage= new Stage();
    }

    public static LibrarianController getInstance(){
        return instance;
    }

    public Member searchLibraryMember(String userName){
        return MemberController.getInstance().getMember(userName);
    }

    public Book searchBook(String isbn) {
        return BookController.getInstance().getBook(isbn);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idMemberCol.setCellValueFactory(
                new PropertyValueFactory<Member,String>("id")
        );

        fnameMemberCol.setCellValueFactory(
                new PropertyValueFactory<Member,String>("firstName")
        );

        lnameMemberCol.setCellValueFactory(
                new PropertyValueFactory<Member,String>("lasttName")
        );

        phoneMemberCol.setCellValueFactory(
                new PropertyValueFactory<Member,String>("phoneNumber")
        );

        dataMember = FXCollections.observableArrayList();
        memberLibraryTable.setItems(dataMember);


        copyNumberBookCol.setCellValueFactory(
                new PropertyValueFactory<CopyBook, String>("copyId")
        );

        availableCopyBookBookCol.setCellValueFactory(
                new PropertyValueFactory<CopyBook, Boolean>("availablity")
        );

        dataBook = FXCollections.observableArrayList();
        copyOfBookTable.setItems(dataBook);

        memberLibraryTable.setRowFactory( tv -> {
            TableRow<Member> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Member member = row.getItem();
                    System.out.println("--------- Checkout record of library member ---------");
                    System.out.println("| Id: " + member.getId());
                    System.out.println("| First Name: " + member.getFirstName());
                    System.out.println("| Last Name: " + member.getLastName());
                    System.out.println("| Email: " + member.getEmail());
                    System.out.println("| Phone: " + member.getPhoneNumber());

                    CheckOutRecord checkOutRecord = member.getCheckOutRecord();
                    if(checkOutRecord == null) {
                        System.out.println("-----------------------------------------------------");
                    } else {
                        List<CheckOutEntry> checkOutEntries = checkOutRecord.getCheckOutEntryList();

                        for(CheckOutEntry checkOutEntry : checkOutEntries) {
                            CopyBook copyBook = checkOutEntry.getCopyBook();
                            Date CheckoutDate = checkOutEntry.getCheckoutDate();
                            Date duaDate = checkOutEntry.getDueDate();
                            System.out.println("| ID Copy book: " + copyBook.getCopyId() + "   Checkout Date: " + CheckoutDate.toString() + "   Due Date: " + duaDate.toString());
                        }
                        System.out.println("-----------------------------------------------------");
                    }
                }
            });
            return row ;
        });
    }

    @FXML
   public void searchLibraryMemberAction(ActionEvent event) {
        dataMember.clear();
        Member mb = searchLibraryMember(usernameTextField.getText());
        dataMember.add(mb);
    }

    @FXML
    public void searchBook(ActionEvent event) {
        dataBook.clear();
        Book book = searchBook(isbnTextField.getText());
        if (book != null){
            List<CopyBook> lstCopyBooks = book.getCopyBooks();
            dataBook.addAll(lstCopyBooks);
        } else {
            errorMessageLbl.setText("This book does not exist!");
            return;
        }
    }

    @FXML
    public void checkoutBookAction(ActionEvent event) {
        if(dataMember.size() == 0) {
            errorMessageLbl.setText("You should chose a library member!");
            return;
        }

        Member mb = dataMember.get(0);
        Book book = searchBook(isbnTextField.getText());

        if(book == null) {
            errorMessageLbl.setText("You should select a coppy book!");
            return;
        }

        List<CopyBook> lstCopyBooks = book.getCopyBooks();
        CopyBook b = copyOfBookTable.getSelectionModel().getSelectedItem();

        if(b == null) {
            errorMessageLbl.setText("You should select a coppy book!");
            return;
        }

        for(CopyBook cb : lstCopyBooks) {
            if(cb.getCopyId().equals(b.getCopyId())){
                if(cb.getAvailablity().equals("Yes")) {
                    cb.setAvailablity("No");
                } else {
                    errorMessageLbl.setText("This copy book is not available.\nPlease select another!");
                    return;
                }

            }
        }

        Book newBook = new Book(lstCopyBooks);
        newBook.isbn.setValue(book.getIsbn());
        newBook.title.setValue(book.getTitle());
        newBook.author.setValue(book.getAuthor());
        newBook.maxBorrowDays.setValue(book.getMaxBorrowDays());

        Date today = new Date();
        int maxBorrowDate = newBook.getMaxBorrowDays();

        mb = checkoutBook(mb, b, today, maxBorrowDate);

        MemberController.getInstance().modifyMember(mb.getId(), mb);

        BookController.getInstance().modifyBook(newBook.getIsbn(), newBook);

        searchBook(new ActionEvent());
        errorMessageLbl.setText("");
    }

    public Member checkoutBook(Member member, CopyBook copyBook, Date today, int maxBorrowDate) {
        Date duaDate = new Date(today.getTime() + maxBorrowDate * 1000 * 60 * 60 * 24);

        CheckOutEntry checkOutEntry = new CheckOutEntry();
        checkOutEntry.setCopyBook(copyBook);
        checkOutEntry.setCheckoutDate(today);
        checkOutEntry.setDueDate(duaDate);

        CheckOutRecord checkOutRecord = member.getCheckOutRecord();

        if(checkOutRecord == null) {
            checkOutRecord = new CheckOutRecord();
            List<CheckOutEntry> checkOutEntries = new ArrayList<>();
            checkOutEntries.add(checkOutEntry);
            checkOutRecord.addListCheckoutEntries(checkOutEntries);
        } else {
            List<CheckOutEntry> checkOutEntries = checkOutRecord.getCheckOutEntryList();
            checkOutEntries.add(checkOutEntry);
            checkOutRecord.addListCheckoutEntries(checkOutEntries);
        }

        member.setCheckOutRecord(checkOutRecord);
        return member;
    }



    @Override
    public void openPersonWindow(Person perosn) throws Exception {
        librarian = (Librarian) perosn;
        librarianStage.setTitle("Labrarian Window - " + perosn.getFirstName());
        GridPane root =  FXMLLoader.load(getClass().getResource("../UI/Librarian.fxml"));
        Scene sence = new Scene(root, 600, 500);
        librarianStage.setScene(sence);
        librarianStage.show();
    }
}
