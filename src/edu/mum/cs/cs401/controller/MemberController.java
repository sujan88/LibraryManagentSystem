package edu.mum.cs.cs401.controller;

import edu.mum.cs.cs401.UI.IWindow;
import edu.mum.cs.cs401.dataaccess.DataAccess;
import edu.mum.cs.cs401.dataaccess.DataAccessImpl;
import edu.mum.cs.cs401.dataaccess.DataBase;
import edu.mum.cs.cs401.model.entity.Address;
import edu.mum.cs.cs401.model.entity.Book;
import edu.mum.cs.cs401.model.entity.Member;
import edu.mum.cs.cs401.model.entity.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * Created by 985333 on 9/13/2016.
 */
public class MemberController implements IWindow, Initializable{

    static Stage stage = null;
    private static MemberController instance = new MemberController();

    // The table's data
    private ObservableList<Member> data;
    // The table and columns

    @FXML
    TableColumn midCol;
    @FXML
    TableView<Member> memberTableView;
    @FXML
    TableColumn fnameCol;
    @FXML
    TableColumn lnameCol;
    @FXML
    TableColumn emailCol;
    @FXML
    TableColumn phoneCol;


    @FXML
    TextField  searchMemberText;
    @FXML
    TextField  searchByFname;


    public MemberController()
    {
     stage = new Stage();
        data = FXCollections.observableArrayList();
    }

    public static MemberController getInstance(){
        return instance;
    }


    public  Member updateMember(Member member){
        return DataBase.memberTable.update(member.getId() ,member);
    }



    public Member modifyMember(String mid, Member member){
        return DataBase.memberTable.update(mid, member);
    }

    public  Member deleteMember(String memberId){
        return DataBase.memberTable.delete(memberId);
    }

    public  Member getMember(String memberId){
        return DataBase.memberTable.get(memberId);
    }

    public  int  getMemebrDbTableSize(){
        return DataBase.memberTable.getValueSize();
    }

    @Override
    public void openWindow() throws Exception {
        GridPane root =  FXMLLoader.load(getClass().getResource("../UI/member.fxml"));
        Scene scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        stage.setTitle("Member Control Manager");
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void searchMemberBtnAction(ActionEvent actionEvent) {

            String mid = searchMemberText.getText();
        if(mid.isEmpty())
        {
       loadAll();
        }
        else {
            Member member = getMember(mid);
            data.add(member); // load into JavaFx table
        }
        }

    @FXML
    public void addNewMemberBtnAction(ActionEvent actionEvent) {
        try {

            MemberFormController.getInstance().openWindow();
            MemberController.getInstance().loadAll();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set up the table data

        searchByFname.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                  for(Member m:  DataBase.memberTable.getDb().values()){
                      if(m.getFirstName().equals(searchByFname.getText())){
                          data.add(m);
                      }
                  }

                }
            }
        });

        midCol.setCellValueFactory(
                new PropertyValueFactory<Member,String>("id")
        );
        fnameCol.setCellValueFactory(
                new PropertyValueFactory<Member,String>("firstName")
        );
        lnameCol.setCellValueFactory(
                new PropertyValueFactory<Member,String>("lastName")
        );
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Member,String>("email")
        );
        phoneCol.setCellValueFactory(
                new PropertyValueFactory<Member,String>("phoneNumber")
        );

        fnameCol.setCellFactory(TextFieldTableCell.<Member> forTableColumn());
        fnameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Member,String>>() {

            @Override
            public void handle(TableColumn.CellEditEvent<Member,String> event) {
                ((Member) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setFirstName(event.getNewValue());
            }
        });

        fnameCol.setCellFactory(TextFieldTableCell.<Member> forTableColumn());
        fnameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Member,String>>() {

            @Override
            public void handle(TableColumn.CellEditEvent<Member,String> event) {
                ((Member) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setFirstName(event.getNewValue());
            }
        });

        lnameCol.setCellFactory(TextFieldTableCell.<Member> forTableColumn());
        lnameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Member,String>>() {

            @Override
            public void handle(TableColumn.CellEditEvent<Member,String> event) {
                ((Member) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setLastName(event.getNewValue());
            }
        });

        emailCol.setCellFactory(TextFieldTableCell.<Member> forTableColumn());
        emailCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Member,String>>() {

            @Override
            public void handle(TableColumn.CellEditEvent<Member,String> event) {
                ((Member) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setEmail(event.getNewValue());
            }
        });
        phoneCol.setCellFactory(TextFieldTableCell.<Member> forTableColumn());
        phoneCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Member,String>>() {

            @Override
            public void handle(TableColumn.CellEditEvent<Member,String> event) {
                ((Member) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setPhoneNumber(event.getNewValue());
            }
        });
        memberTableView.setItems(data);
    }

    public void adminHomeBtnAction(ActionEvent actionEvent) {
        MemberController.getInstance().stage.hide();
        AdminController.getInstance().adminStage.show();
    }

    @FXML
    public void updateSaveBTN(ActionEvent actionEvent) {

        data.stream().forEach(m-> updateMember(m));
        MemberController.getInstance().stage.close();
        AdminController.getInstance().adminStage.show();

    }

    public void clearMembersBtnAction(ActionEvent actionEvent) {
        data.clear();
    }

    public void addMember(Member newMember){
        data.add(newMember);
        DataBase.memberTable.add(newMember.getId() ,newMember);
    }

    public void loadAll(){
        DataBase.memberTable.getDb().values().stream().forEach(m-> data.add((m)));
    }

}
