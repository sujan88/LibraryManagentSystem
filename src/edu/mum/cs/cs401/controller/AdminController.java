package edu.mum.cs.cs401.controller;

import edu.mum.cs.cs401.UI.IPersonWindow;
import edu.mum.cs.cs401.UI.LoginUI;
import edu.mum.cs.cs401.model.entity.Admin;
import edu.mum.cs.cs401.model.entity.Book;
import edu.mum.cs.cs401.model.entity.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * Created by 985333 on 9/13/2016.
 */
public class AdminController implements IPersonWindow {


    static Admin admin = null;
    public Stage adminStage = null;

    private static AdminController instance = new AdminController();

    public  static AdminController getInstance(){
        return  instance;
    }

    public  AdminController(){
        this.adminStage = new Stage();
    }
    public void addNewBook(Book book){
        BookController.getInstance().addBook(book);

    }

    @FXML
    public void bookBtnAction(ActionEvent actionEvent) throws IOException {

        AdminController.getInstance().adminStage.hide();
        try
        {
            BookController.getInstance().openWindow();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @FXML
    public void memberBtnAction(ActionEvent actionEvent) {
        AdminController.getInstance().adminStage.hide();
        try
        {
            MemberController.getInstance().openWindow();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void openPersonWindow(Person perosn ) throws Exception
    {
        admin = (Admin) perosn;

        adminStage.setTitle("Admin Window -  " + admin.getFirstName().toUpperCase());

        GridPane root =  FXMLLoader.load(getClass().getResource("../UI/admin.fxml"));
        Scene scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        adminStage.setScene(scene);
        adminStage.show();


    }

    @FXML
    public void signOutBtnAction(ActionEvent actionEvent) {
        AdminController.getInstance().adminStage.close();
         LoginUI.stage.show();
    }
}
