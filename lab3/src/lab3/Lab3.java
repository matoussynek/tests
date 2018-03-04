
package lab3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Matouš
 */
public class Lab3 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        Parent root = myLoader.load();  

        
        Scene scene = new Scene(root,590,590);
        
        stage.setScene(scene);
        stage.setTitle("Ultimate listadder 3000");
        stage.show();
        stage.setResizable(false);
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
