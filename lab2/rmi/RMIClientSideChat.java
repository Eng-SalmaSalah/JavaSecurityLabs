/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author salma
 */
public class RMIClientSideChat extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        ClientGUIBase chatApp=new ClientGUIBase(primaryStage);
        Scene scene = new Scene(chatApp, 600, 400);
        primaryStage.setTitle("Chatting Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
