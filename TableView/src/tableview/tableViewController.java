/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableview;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Matouš
 */
public class tableViewController implements Initializable {
    
    @FXML
    private TableColumn<Person, Integer > idCol;
    @FXML
    private TableColumn<Person, String> nameCol;
    @FXML
    private TableColumn<Person, String> cityCol;
    @FXML
    private TableColumn<Person, String> imgCol;
    @FXML
    public TableView<Person> t;

    public ObservableList<Person> observableList =  FXCollections.observableArrayList();
    
    private Person selected;
    @FXML
    private Button addB;
    @FXML
    private Button modB;
    @FXML
    private Button removeB;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        removeB.setDisable(true);
        modB.setDisable(true);
        t.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                removeB.setDisable(true);
                modB.setDisable(true);
            }
            else{
                selected = newValue;
                removeB.setDisable(false);
                modB.setDisable(false);
            }
        });
        
        idCol.setCellValueFactory(value -> new SimpleIntegerProperty(value.getValue().getId()).asObject());
        nameCol.setCellValueFactory(value -> value.getValue().fullNameProperty());
        cityCol.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getResidence().getCity().toUpperCase()));
        imgCol.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getPathImage()));
        imgCol.setCellFactory( v -> {
            return new TableCell<Person,String>(){
                private ImageView img = new ImageView();
                @Override
                protected void updateItem(String item,boolean empty){
                    super.updateItem(item,empty);
                    if (item == null || empty){
                        setGraphic(null);
                    }
                    else{
                        img.setImage(new Image(item,32,32,true,true));
                        setGraphic(img);
                    }
                }
            };
        });
        observableList.add(new Person(1,"Ja Rom Ir", new Residence("Olomouc", "CZ"),"/img/broccoli.png"));
        modify();
    }   
    private void modify(){
        t.setItems(observableList);
    }
    @FXML
    private void add() 
        throws IOException {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("AddWindowFXML.fxml"));
        AnchorPane root = (AnchorPane) myLoader.load();
        AddWindowFXMLController addW = myLoader.<AddWindowFXMLController>getController();

        Stage newWindow = new Stage();
        newWindow.setTitle("Add Window");
        newWindow.setScene(new Scene(root));
        newWindow.show();
        newWindow.setResizable(false);
        addW.initStage(newWindow,t,observableList);
    }
    @FXML
    private void mod()
    throws IOException {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("AddWindowFXML.fxml"));
        AnchorPane root = (AnchorPane) myLoader.load();
        AddWindowFXMLController addW = myLoader.<AddWindowFXMLController>getController();

        Stage newWindow = new Stage();
        newWindow.setTitle("Edit Window");
        newWindow.setScene(new Scene(root));
        newWindow.show();
        addW.initStage(newWindow,t,observableList,selected);
    }
    @FXML
    private void remove(){
        observableList.remove(selected);
        modify();
    }
    
}
