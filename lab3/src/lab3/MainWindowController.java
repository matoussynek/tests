
package lab3;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *  Controller of main window.
 * @author Matouš
 */
public class MainWindowController implements Initializable {
    
    @FXML
    private Label label;  //Label to display selected item
    @FXML
    public ListView<Person> listB;
    @FXML
    private Button addB;
    @FXML
    private Button removeB;

    
    static List<Person> list= new ArrayList<Person>();
    public ObservableList<Person> observableList =  FXCollections.observableList(list);
    
    Person selected;
    @FXML
    private Button up;
    @FXML
    private Button down;
    @FXML
    private Button editB;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        observableList.add(new Person("Matous","Synek","01.01.1901"));
        observableList.add(new Person("Bobby","McFerrin","01.01.1902"));
        observableList.add(new Person("Michael","Jackson","01.01.1903"));
        modify();
        
        removeB.setDisable(true);
        editB.setDisable(true);
        addB.setDisable(false);
        up.setDisable(true);
        down.setDisable(true);
       
        
        listB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue == null){
            label.setText("Selected item: none");
            removeB.setDisable(true);
            editB.setDisable(true);
        }
        else {
            selected = newValue;
            label.setText("Selected item: " + selected);
            removeB.setDisable(false);
            editB.setDisable(false);
            if (observableList.indexOf(selected) == 0){
                up.setDisable(true);
            }
            else up.setDisable(false);
            if (observableList.indexOf(selected) == observableList.size()-1 ){
                down.setDisable(true);
            }
            else down.setDisable(false);
            }
        
        
    
    });
    }

    public void modify(){
        listB.setItems(observableList);
    }

    @FXML
    private void add() 
        throws IOException {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("AddWindow.fxml"));
        AnchorPane root = (AnchorPane) myLoader.load();
        AddWindowController addW = myLoader.<AddWindowController>getController();

        Stage newWindow = new Stage();
        newWindow.setTitle("Add Window");
        newWindow.setScene(new Scene(root));
        newWindow.show();
        addW.initStage(newWindow,listB);
    }
    @FXML
    private void deleteFromList() {
        observableList.remove(selected);
        modify();
    }

    @FXML
    private void moveU() {
        up.setDisable(false);
        down.setDisable(false);
        int index = observableList.indexOf(selected);
        if (index == 0 ){
            up.setDisable(true);
            return;
        }
        Person temp = observableList.get(index-1);
        observableList.set(index-1,selected);
        observableList.set(index, temp);
        selected = observableList.get(index-1);
        listB.getSelectionModel().select(observableList.indexOf(selected));
        if (index - 1 == 0 ) up.setDisable(true);
   }

    @FXML
    private void moveD() {
        up.setDisable(false);
        down.setDisable(false);
        int index = observableList.indexOf(selected);
        if (index == observableList.size()-1 ){
            down.setDisable(true);
            return;
        }
        Person temp = observableList.get(index+1);
        observableList.set(index+1,selected);
        observableList.set(index, temp);
        selected = observableList.get(index+1);
        listB.getSelectionModel().select(observableList.indexOf(selected));
        if (index + 1 == observableList.size()-1 ) down.setDisable(true);
    }

    @FXML
    private void edit() 
    throws IOException {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("AddWindow.fxml"));
        AnchorPane root = (AnchorPane) myLoader.load();
        AddWindowController addW = myLoader.<AddWindowController>getController();
        
        Stage newWindow = new Stage();
        newWindow.setTitle("Edit Window");
        newWindow.setScene(new Scene(root));
        newWindow.show();
        addW.initEdit(newWindow,listB,selected);

    }
}
