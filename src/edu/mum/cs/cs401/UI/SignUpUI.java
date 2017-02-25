package edu.mum.cs.cs401.UI;/**
 * Created by 985333 on 9/13/2016.
 */

import edu.mum.cs.cs401.controller.LoginController;
import edu.mum.cs.cs401.controller.SignUpController;
import edu.mum.cs.cs401.model.entity.Address;
import edu.mum.cs.cs401.model.entity.Admin;
import edu.mum.cs.cs401.model.entity.Librarian;
import edu.mum.cs.cs401.model.entity.Person;
import edu.mum.cs.cs401.util.LMessage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SignUpUI implements IWindow {

    private Stage  stage = null;

    public SignUpUI() {
        this.stage = new Stage();
    }

    public static void main(String[] args) {

        List<Address> addresses=new ArrayList<>();
        addresses.add(new Address("1000 N", "fairfilef", "iowa", "50557"));
        Admin admin=new Admin("1","sujan","duminda",addresses ,"12343444","email@gmail.com",Person.ADMIN);
        admin.setUsername("sujan88");
        admin.setPassword("123");
        LoginController.addPerson(admin);


        Librarian librarian=new Librarian("2","Hung","Jung",addresses ,"322333","email@gmail.com",Person.LIBRARIAN);
        librarian.setUsername("hung86");
        librarian.setPassword("321");
        LoginController.addPerson(librarian);

    }

    @Override
    public void openWindow() throws Exception {
        stage.setTitle("SignUp Form ( Admin / Librarian )");

        StackPane root = new StackPane();

        Scene scene = new Scene(root, 600, 300, Color.WHITE);

        GridPane parentGrid= new GridPane();
        parentGrid.setAlignment(Pos.CENTER);
        parentGrid.setHgap(10);
        parentGrid.setVgap(10);
        parentGrid.setPadding(new Insets(0,25,0,25));
        // parentGrid.setGridLinesVisible(true);
        root.getChildren().add(parentGrid);

        // column size , width , height can be changed by CoulmnConstrains .
        ColumnConstraints column1 = new ColumnConstraints(150); // can be applied for text box which need width 150
        ColumnConstraints column2 = new ColumnConstraints(50); // can be applied for text box which need width 50



        GridPane topGrid= new GridPane();
        topGrid.setAlignment(Pos.TOP_CENTER);
        topGrid.setHgap(10);
        topGrid.setVgap(10);
        topGrid.setPadding(new Insets(5,25,0,25));
        topGrid.getColumnConstraints().add(column1);
        //topGrid.setGridLinesVisible(true);

        Label name = new Label("First Name");
        TextField textName = new TextField();
        Label lname = new Label("Last Name");
        TextField textlname= new TextField();
        Label phone = new Label("Phone Number");
        TextField textPhone = new TextField();
        Label email = new Label("Email");
        TextField textEmail= new TextField();

        topGrid.add(name,0,0);
        topGrid.add(textName,0,1);
        topGrid.add(lname,1,0);
        topGrid.add(textlname,1,1);
        topGrid.add(phone,2,0);
        topGrid.add(textPhone,2,1);
        topGrid.add(email,3,0);
        topGrid.add(textEmail,3,1);

        parentGrid.getChildren().add(topGrid);

        GridPane centerGrid= new GridPane();
        centerGrid.setAlignment(Pos.TOP_CENTER);
        centerGrid.setHgap(10);
        centerGrid.setVgap(5);
        centerGrid.setPadding(new Insets(5,25,5,25));


        parentGrid.add(centerGrid, 0,1);

        Label street = new Label("stree");
        TextField textstreet = new TextField();
        Label city = new Label("city");
        TextField textCity= new TextField();
        Label state = new Label("State");
        TextField textState= new TextField();
        Label zip = new Label("zip");
        TextField textZip = new TextField();


        centerGrid.add(street,0,0);
        centerGrid.add(textstreet,0,1);
        centerGrid.add(city,1,0);
        centerGrid.add(textCity,1,1);
        centerGrid.add(state,2,0);
        centerGrid.add(textState,2,1);
        centerGrid.add(zip,3,0);
        centerGrid.add(textZip,3,1);



        centerGrid.getColumnConstraints().addAll(column1,column1,column1,column2);

        GridPane centerGrid2= new GridPane();
        centerGrid2.setAlignment(Pos.TOP_CENTER);
        centerGrid2.setHgap(10);
        centerGrid2.setVgap(5);
        centerGrid2.setPadding(new Insets(5,25,5,25));

        Label uname = new Label("User Name");
        TextField textUname = new TextField();
        Label password = new Label("Password");
        TextField textPassword= new TextField();
        centerGrid2.add(uname,0,0);
        centerGrid2.add(textUname,0,1);
        centerGrid2.add(password,1,0);
        centerGrid2.add(textPassword,1,1);

        parentGrid.add(centerGrid2, 0,2);

        GridPane bottomGrid= new GridPane();
        bottomGrid.setAlignment(Pos.TOP_LEFT);
        bottomGrid.setHgap(10);
        bottomGrid.setVgap(10);
        bottomGrid.setPadding(new Insets(0,25,0,25));


        GridPane bottomGrid2= new GridPane();
        bottomGrid2.setAlignment(Pos.BASELINE_RIGHT);
        bottomGrid2.setHgap(30);
        bottomGrid2.setVgap(10);
        bottomGrid2.setPadding(new Insets(25,25,25,25));

        parentGrid.add(bottomGrid2, 0,4);

        Label role=new Label("Select Role");
        ComboBox<String> roleCombo=new ComboBox<>();
        roleCombo.getItems().addAll(
                Person.ADMIN,
                Person.LIBRARIAN
        );



        parentGrid.add(bottomGrid, 0,3);

        bottomGrid.add(role,0,0);
        bottomGrid.add(roleCombo,1,0);
        Button submitBtn= new Button("SignUp");
        submitBtn.setAlignment(Pos.BASELINE_RIGHT);
        submitBtn.setOnAction(event ->  {

                List<Address> addresses = new ArrayList<Address>();
                addresses.add(new Address(street.getText(),street.getText(),state.getText(),zip.getText()));
                Person person = null;
            if(roleCombo.getSelectionModel().getSelectedItem() == null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("");
                alert.setContentText("Please select the role!");
                alert.showAndWait();

            }
                if(roleCombo.getSelectionModel().getSelectedItem().equals(Person.ADMIN)){

                    person = new Admin( String.valueOf(SignUpController.getInstance().getlastId()+1),textName.getText(),textlname.getText(),addresses,textPhone.getText(),textEmail.getText(),Person.ADMIN);
                }
                else if(roleCombo.getSelectionModel().getSelectedItem().equals(Person.LIBRARIAN)){
                    person = new Librarian( String.valueOf(SignUpController.getInstance().getlastId()+1),textName.getText(),textlname.getText(),addresses,textPhone.getText(),textEmail.getText(),Person.LIBRARIAN);

                }

                if(person != null){
                    person.setUsername(textUname.getText());
                    person.setPassword(textPassword.getText());
                    SignUpController.getInstance().addPerson(person);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("");
                    alert.setContentText("Do you want to login ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {;
                        stage.hide();
                        LoginUI.stage.show();
                    }
                    else {
                        System.exit(0);
                    }

                }else
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Please fill the info!");
                        alert.showAndWait();

                }

        });

        Button back = new Button("<< Back");
        back.setAlignment(Pos.BASELINE_LEFT);
        bottomGrid2.add(back,0,0);
        back.setOnAction(event -> {
            stage.hide();
            LoginUI.stage.show();
        });

        bottomGrid2.add(submitBtn,1,0);

        stage.setScene(scene);
        stage.show();




    }
}
