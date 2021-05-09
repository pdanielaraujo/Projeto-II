/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Pedro
 */
public class DesktopUI extends Application {
    public static Stage guiStage;
    @Override
    public void start(Stage stage) throws Exception {
        guiStage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/Login/LoginBibliotecario.fxml"));
        Scene scene = new Scene(root);
        
        stage.setTitle("Biblioteca App");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setResizable(true);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
