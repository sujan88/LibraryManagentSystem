package edu.mum.cs.cs401.controller;

import edu.mum.cs.cs401.UI.IWindow;
import edu.mum.cs.cs401.dataaccess.DataBase;
import edu.mum.cs.cs401.model.entity.Address;
import edu.mum.cs.cs401.model.entity.Member;
import edu.mum.cs.cs401.model.entity.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * Created by 985333 on 9/13/2016.
 */
public class MemberFormController implements IWindow{


     Stage stageForm=null;
    private static MemberFormController instance = new MemberFormController();


    @FXML
    TextField fnameText;
    @FXML
    TextField  lnameText;
    @FXML
    TextField  emailText;
    @FXML
    TextField  phoneText;
    @FXML
    TextField  streetText;
    @FXML
    TextField  cityText;
    @FXML
    TextField  stateText;
    @FXML
    TextField  zipText;


    @FXML
    TextField  searchMemberText;


    public MemberFormController()
    {
     stageForm = new Stage();
    }

    public static MemberFormController getInstance(){
        return instance;
    }


    @Override
    public void openWindow() throws Exception {
        GridPane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../UI/memberForm.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        stageForm=new Stage();
        stageForm.setTitle("Member Control Form");
        stageForm.setScene(scene);
        stageForm.show();
    }


    public void CancelBtnAction(ActionEvent actionEvent) {
        stageForm.close();
    }



    @FXML
    public void SaveBtnAction(ActionEvent actionEvent) {

        MemberController parentControler= MemberController.getInstance();
        int lastIndex = parentControler.getMemebrDbTableSize();


        List<Address> adList = new ArrayList<>();
        adList.add(new Address(streetText.getText(),cityText.getText(),stateText.getText(),zipText.getText()));
        Member newMember = new Member(lastIndex+1+"", fnameText.getText(),lnameText.getText(),adList,phoneText.getText(),emailText.getText(), Person.MEMBER);
        parentControler.addMember(newMember);
        MemberFormController.getInstance().stageForm.close();
    }
}
