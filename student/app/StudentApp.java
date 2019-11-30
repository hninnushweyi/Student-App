/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import student.app.controllers.MainController;

/**
 *
 * @author Lenovo
 */
public class StudentApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/student/app/views/main.fxml"));
        Parent root = loader.load();
        
//        MainController controller = loader.getController();
//        String str = controller.getText();
//        System.out.println("Text from main:"+str);
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Student App");
        stage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
