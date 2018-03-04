/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *  Controller of second window for adding or editing element of ListView.
 * @author Matoušs Synek
 */
public class AddWindowController implements Initializable {

    @FXML
    private TextField nameL;
    @FXML
    private TextField surnameL;
    
    public MainWindowController firstC;
    
    
    private Stage current;
    private ListView list;
    @FXML
    private Button add;
    private Person person;
    private boolean editing = false;
    @FXML
    private TextField birthL;
    @FXML
    private Label error;


    public void initStage(Stage s, ListView l){
        editing = false;
        current = s;
        list = l;
        add.setText("Add");

    }
    public void initEdit(Stage s,ListView l, Person p){
        editing = true;
        current = s;
        list = l;
        person = p;
        add.setText("Edit");
        nameL.setText(person.firstName);
        surnameL.setText(person.lastName);
        birthL.setText(person.birthDate);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        error.setVisible(false);
        firstC= new MainWindowController();
    }    

    @FXML
    private void add() {
        if (!check()) {
            error.setVisible(true);
            return;
        }
        error.setVisible(false);
        if (!editing){
            firstC.observableList.add(new Person(nameL.getText(),surnameL.getText(),birthL.getText()));
            list.setItems( firstC.observableList);
            firstC.listB = list;
            firstC.listB.refresh();
            current.hide();
            return;
        }
        int index = firstC.observableList.indexOf(person);
        person.setFirstName(nameL.getText());
        person.setLastName(surnameL.getText());
        person.setBirthDate(birthL.getText());
        firstC.observableList.set(index, person);
        list.setItems( firstC.observableList);
        firstC.listB = list;
        firstC.listB.refresh();
        current.hide();
    }
    
    @FXML
    private void back(){
        current.hide();
    }

    @FXML
    private void keyPressed(KeyEvent event) {
        if (event.getCode()==KeyCode.ENTER) add();
    }
    
    private boolean check(){
        if (nameL.getText().isEmpty()) {
            error.setText("Firts name is empty.");
            return false;
        }
        if (surnameL.getText().isEmpty()){
            error.setText("Last name is empty.");
            return false;
        }
        if (!birthL.getText().isEmpty() && !birthL.getText().matches("\\d{2}.\\d{2}.\\d{4}")){
            error.setText("Incorrect birth date type (dd.mm.yyyy)");
            return false;
        }
        return true;
    }
    
}
