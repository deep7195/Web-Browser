
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Deependra
 */
public class Main  extends Application{
   // private Object stage;

    @Override
    public void start(Stage primaryStage)
    { 
         
        //primaryStage.getIcons().add(new Image("resources/newtron1.png"));
    
      new WebBrowser();
      
        
        
        
    }
    public static void main(String[] args)
    {
      launch(args);  
       
    
}
}

 
