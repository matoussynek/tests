/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deliverable.pkg2;

import eu.hansolo.medusa.Clock;
import eu.hansolo.medusa.Gauge;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Matouš
 */
public class MainFXMLController implements Initializable {
    
    @FXML
    private ToggleButton scene1;
    @FXML
    private ToggleGroup tabs;
    @FXML
    private ImageView s1img;
    @FXML
    private ToggleButton scene2;
    @FXML
    private ImageView s2img;
    @FXML
    private ToggleButton scene3;
    @FXML
    private ImageView s3img;
    @FXML
    private ToggleButton scene4;
    @FXML
    private ImageView s4img;
    @FXML
    private ToggleButton nightMode;
    @FXML
    private ImageView nModeimg;
    @FXML
    private GridPane grid;
    
    Scene scene;
    
    private Gauge compass;
    @FXML
    private AnchorPane pane;
    
    public Color selectedDay = Color.web("0x26c6da");
    
    private String themeDay = getClass().getResource("/styles/day.css").toExternalForm();
    private String themeNight = getClass().getResource("/styles/night.css").toExternalForm();
    
    public void init(Scene s){
        scene = s;
        System.out.println(scene);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        compass = new Gauge();
        pane.getChildren().add(compass);

        
        compass.setValue(5);
        compass.setSkinType(Gauge.SkinType.GAUGE);
        compass.setPrefSize(320, 240);
        compass.setNeedleColor(selectedDay);
        GridPane.setColumnIndex(compass,0);
        GridPane.setRowIndex(compass, 0);
        
        
        scene1.selectedProperty().addListener((l,o,n)->{
            if (n){
                System.out.println(scene);
                compass.setVisible(true);
            }
            else compass.setVisible(false);
    });
    }    

    @FXML
    private void changeMode() {
        System.out.println(scene);
        if (nightMode.isSelected()){
            System.out.println("night");
            scene.getStylesheets().remove(themeDay);
            scene.getStylesheets().add(themeNight);
            s1img.setImage(new Image("/resources/compassWhite.png"));
            s2img.setImage(new Image("/resources/mapWhite.png"));
            s3img.setImage(new Image("/resources/windWhite.png"));
            s4img.setImage(new Image("/resources/graphWhite.png"));
            nModeimg.setImage(new Image("/resources/day.png"));
        }
        else{
            System.out.println("day");
            scene.getStylesheets().remove(themeNight);
            scene.getStylesheets().add(themeDay);
            s1img.setImage(new Image("/resources/compassBlack.png"));
            s2img.setImage(new Image("/resources/mapBlack.png"));
            s3img.setImage(new Image("/resources/windBlack.png"));
            s4img.setImage(new Image("/resources/graphBlack.png"));
            nModeimg.setImage(new Image("/resources/night.png"));
        }
    }
    
}
