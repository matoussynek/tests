/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import static javafx.application.Platform.exit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author Matouš Synek
 */
public class menuAppController implements Initializable {

    @FXML
    private RadioMenuItem amazonBuy;
    @FXML
    private RadioMenuItem ebayBuy;
    @FXML
    private Label label;

    @FXML
    private void exitA(){
        Alert alert  = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Exit Attempt");
        alert.setHeaderText("Do you really want to exit?");
        
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK){
            exit();
        }
    }
    
    private void approved(String s){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Purchase completed!");
        alert.setHeaderText("You have completed your purchase!");
        alert.setContentText("You have bought in " + s);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            alert.close();
        }
    }
    
    private void deny(String s){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Purchase denied!");
        alert.setHeaderText("You cannot buy in " +s);
        alert.setContentText("Please change the current selection in Options menu");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            alert.close();
        }
    }
    
    @FXML
    private void openBlogger(){
        
        List<String> list = new ArrayList<>();
        list.add("Athos' blog");
        list.add("Porthos' blog");
        list.add("Aramis' blog");
        
        ChoiceDialog<String> whatever = new ChoiceDialog("Select blog", list);
        whatever.setTitle("Select a blog");
        whatever.setHeaderText("Which blog do you want to visit?");
        whatever.setContentText("Choose: ");
        
        Optional<String> result = whatever.showAndWait();
        if (result.isPresent()){
            label.setText("You have selected: " + result.get());
            whatever.close();
        }
    }
    
    private void name(String s){
        TextInputDialog whatever = new TextInputDialog("James");
        whatever.setTitle("Introduce your name");
        whatever.setHeaderText("Which user do you want to use to write in " + s +"?");
        whatever.setContentText("Enter your name: ");
        
        Optional<String> result = whatever.showAndWait();
        if (result.isPresent()){
            label.setText("Message sent as: " + result.get());
            whatever.close();
        }
    }
    
    public void openWebpage(String url) throws Exception {
        Desktop.getDesktop().browse(new URL(url).toURI());
}
    
    @FXML
    private void openAmazon() throws Exception{
        if (amazonBuy.isSelected()){
            approved("Amazon");
            //openWebpage("http://www.amazon.es");
        }
        else deny("Amazon");
    }
    
    @FXML
    private void openEbay(){
        if (ebayBuy.isSelected()){
            approved("Ebay");
        }
        else deny("Ebay");
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        label.setText("");
    }    

    @FXML
    private void openFacebook(ActionEvent event) {
        name("Facebook");
    }

    @FXML
    private void openGoogle(ActionEvent event) {
        name("GooglePlus");
    }

    
}
