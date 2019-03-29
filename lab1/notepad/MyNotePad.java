/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author salma
 */
public class MyNotePad extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       
        
        MyNotePadDesignBase root = new MyNotePadDesignBase(primaryStage);
        
        
        Scene scene = new Scene(root, 800, 900);
        
        primaryStage.setTitle("NotePad");
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
