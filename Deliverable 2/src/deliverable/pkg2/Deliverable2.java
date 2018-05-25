/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deliverable.pkg2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Matouš Synek & Manuel Roselló
 */
public class Deliverable2 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainFXML.fxml"));
        Parent root = (Parent) loader.load();
        MainFXMLController controller = loader.getController();
        
        Scene scene = new Scene(root);
        
        controller.init(stage, scene);
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Deliverable 2");
        stage.show();
        
        stage.setOnCloseRequest(e->{
            System.exit(0);
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
