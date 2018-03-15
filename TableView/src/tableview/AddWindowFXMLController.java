/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableview;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Matouš
 */
public class AddWindowFXMLController implements Initializable {

    @FXML
    private Button acceptB;
    @FXML
    private Button cancelB;
    @FXML
    private TextField idL;
    @FXML
    private TextField fullNameL;
    @FXML
    private TextField cityL;
    @FXML
    private TextField provinceL;
    @FXML
    private ChoiceBox<String> imageC;

    private Stage current;
    private TableView t;
    private tableViewController c;
    private Person selected;
    private boolean editing = false;
    /**
     * Initializes the controller class.
     */
    public ObservableList<String> value =  FXCollections.observableArrayList();
    public ObservableList<Person> l =  FXCollections.observableArrayList();
    @FXML
    private Label error;
    public void initStage(Stage s, TableView t, ObservableList<Person> l ){
        current =s;
        this.t = t;
        this.l = l;
        acceptB.setText("Add");
    }
    public void initStage(Stage s, TableView t, ObservableList<Person> l, Person p){
        current =s;
        this.t = t;
        this.l = l;
        acceptB.setText("Modify");
        selected = p;
        idL.setText(""+selected.getId());
        fullNameL.setText(selected.getFullName());
        cityL.setText(selected.getResidence().getCity());
        provinceL.setText(selected.getResidence().getProvince());
        imageC.setValue(selected.getPathImage());
        editing = true;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadList(new File("src\\img"));
        imageC.setItems(value);
        c = new tableViewController();
        error.setVisible(false);
    }
    @FXML
    private void edit(){
        if (!check()) return;
        if (editing){
            selected.setFullName(fullNameL.getText());
            selected.setId(Integer.parseInt(idL.getText()));
            selected.setResidence(new Residence(cityL.getText(),provinceL.getText()));
            selected.setPathImage(imageC.getValue());
        }
        else l.add(new Person(Integer.parseInt(idL.getText()),fullNameL.getText(),new Residence(cityL.getText(),provinceL.getText()),imageC.getValue()));
        t.setItems(l);
        c.observableList = l;
        c.t =t;
        c.t.refresh();
        close();
    }
    @FXML
    private void close(){
        current.hide();
    }
    private boolean check(){
        error.setVisible(true);
        if (!isInteger(idL.getText())){
            error.setText("Incorrect id.");
            return false;
        }
        else if (fullNameL.getText().isEmpty()){
           error.setText("Incorrect name.");
           return false;
        } 
        else if (cityL.getText().isEmpty() || provinceL.getText().isEmpty()){
           error.setText("Incorrect Residence.");
           return false;
        } 
        else if (imageC.getValue()==null){
           error.setText("Incorrect image.");
           return false;
        }
        return true;
    }
    public static boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException e) { 
            return false; 
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }
    private void loadList(final File folder){
        for (final File f : folder.listFiles()){
            String temp = f.getPath().toString().substring(3);
            temp = temp.replaceAll("\\\\", "/");
            value.add(temp);
        }
    }

    
}
