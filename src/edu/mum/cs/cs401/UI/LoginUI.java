package edu.mum.cs.cs401.UI;/**
 * Created by 985333 on 9/13/2016.
 */

import edu.mum.cs.cs401.controller.AdminController;
import edu.mum.cs.cs401.controller.LibrarianController;
import edu.mum.cs.cs401.controller.LoginController;
import edu.mum.cs.cs401.util.LMessage;
import edu.mum.cs.cs401.model.entity.Address;
import edu.mum.cs.cs401.model.entity.Admin;
import edu.mum.cs.cs401.model.entity.Librarian;
import edu.mum.cs.cs401.model.entity.Person;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class LoginUI extends Application {

    public static Stage stage;
   // private static LoginUI instance = new LoginUI();

   /* public static  LoginUI getInstance(){
        return instance;
    }
    */

    public LoginUI(){
        stage= new Stage();
    }
    public static void main(String[] args) {

        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address("1000 N", "fairfilef", "iowa", "50557"));
        Admin admin = new Admin("1","sujan","duminda", addresses,"12343444","email@gmail.com", Person.ADMIN);
        admin.setUsername("sujan88");
        admin.setPassword("123");
        LoginController.addPerson(admin);

        Librarian librarian = new Librarian("2","Hung","Jung", addresses ,"322333","email3@gmail.com",Person.LIBRARIAN);
        librarian.setUsername("hung86");
        librarian.setPassword("321");
        LoginController.addPerson(librarian);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        LoginUI.stage=primaryStage;
        primaryStage.setTitle("Login Form");

        StackPane root = new StackPane();

        Scene scene = new Scene(root, 500, 200, Color.WHITE);

        GridPane parentGrid= new GridPane();
        parentGrid.setAlignment(Pos.CENTER_LEFT);
        parentGrid.setHgap(40);
        parentGrid.setVgap(10);
        parentGrid.setPadding(new Insets(0,25,0,25));

        //parentGrid.setGridLinesVisible(true);
        root.getChildren().add(parentGrid);
        //parentGrid.setGridLinesVisible(true);
        // column size , width , height can be changed by CoulmnConstrains .
        ColumnConstraints column1 = new ColumnConstraints(150); // can be applied for text box which need width 150
        ColumnConstraints column2 = new ColumnConstraints(200); // can be applied for text box which need width 50

        GridPane rightPane= new GridPane();
        rightPane.setAlignment(Pos.CENTER_LEFT);
        rightPane.setHgap(10);
        rightPane.setVgap(10);
        rightPane.setPadding(new Insets(25,5,0,25));
        // rightPane.getColumnConstraints().add(column1);
        //rightPane.setGridLinesVisible(true);

        Label signUpLable = new Label("Register as Admin or Librarian");
        Button signUpBtn= new Button("Sign Up");
        signUpBtn.setTextFill(Color.web("#0076a3"));


        VBox vBox = new VBox(5);

        vBox.getChildren().addAll(signUpLable,signUpBtn);
        rightPane.getChildren().add(vBox);

        parentGrid.getChildren().add(rightPane);

        GridPane leftPane= new GridPane();
        leftPane.setAlignment(Pos.CENTER_LEFT);
        leftPane.setHgap(10);
        leftPane.setVgap(5);
        leftPane.setPadding(new Insets(15,25,5,5));

        parentGrid.add(leftPane, 1,0);

        Button loginBtn= new Button("Sign In");

        Label input = new Label("User Name");
        TextField textUserName = new TextField();
        Label output = new Label("Password");
        TextField textPassword = new TextField();


        leftPane.add(input,0,0);
        leftPane.add(textUserName,0,1);
        leftPane.add(output,0,2);
        leftPane.add(textPassword,0,3);
        leftPane.add(loginBtn,0,4);


        leftPane.getColumnConstraints().add(column1);

        GridPane messagePane= new GridPane();
        messagePane.setAlignment(Pos.BOTTOM_LEFT);
        messagePane.setHgap(10);
        messagePane.setVgap(5);
        messagePane.setPadding(new Insets(5,0,5,5));

        Label errorMsg = new Label("");
        errorMsg.setTextFill(Color.web("#0076a3"));

        messagePane.add(errorMsg,0,0);
        parentGrid.add(messagePane, 1,1);
        loginBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


               String uname= textUserName.getText();
               String pword=  textPassword.getText();

               LMessage msg =  LoginController.getInstance().login(uname, pword);

             if(msg.getStates()==LMessage.SUCCESS){
                 primaryStage.hide();
                Person p= (Person) msg.getReturnDate();
                 try{
                 if (p.getRole().equals(Person.ADMIN))
                 {

                         AdminController.getInstance().openPersonWindow(p) ;


                 }
                 else if (p.getRole().equals(Person.LIBRARIAN))
                 {

                         LibrarianController.getInstance().openPersonWindow(p);

                 }
                 else {
                     errorMsg.setText("O VALID PERSON ROLE");
                 }
                 }
                 catch (Exception e) {
                     e.printStackTrace();
                 }
             }
             else {

                 errorMsg.setText((String)msg.getReturnDate());
             }


            }
        });

        signUpBtn.setOnAction(event -> {
            primaryStage.hide();
            SignUpUI signUpUI = new SignUpUI();
            try {
                signUpUI.openWindow();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();



    }
}
