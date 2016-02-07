import javax.swing.JLabel.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JLabel;

import static javafx.application.Application.launch;
import javafx.stage.Stage;


public class Login extends Stage
{
String user="admin";
String pw="admin123",checkUser,checkPw;
/////////JLabel label=new JLabel(<html><input type="button"target="_blank"href="www.fb.com"click=" "/></html>);
    /**
     *
     * @param args
     */
    

Login()
    {
        
           // primaryStage.getIcons().add(new Image("C:\\Users\\Akash\\Desktop\\bground\\newtron.png"));
        BorderPane bp=new BorderPane();
        bp.setPadding(new Insets(10,50,50,50));
        
        HBox hb=new HBox();
        hb.setPadding(new Insets(20,20,20,20));
        
        GridPane grid=new GridPane();
        grid.setPadding(new Insets(20,20,20,20));
        grid.setHgap(5);
        grid.setVgap(5);
        
        Label lbluser=new Label("UserName ");
        final TextField txt=new TextField();
        Label lblpwd=new Label("Password ");
        final PasswordField pwd=new  PasswordField();
        Button btn=new Button("Login");
        Button sign=new Button("SignUp");
        final Label lblmsg=new Label();
        
        grid.add(lbluser,0,0);
        grid.add(txt,1,0);
        grid.add(lblpwd,0,1);
        grid.add(pwd,1,1);
        grid.add(btn, 2, 3);
        grid.add(sign, 1, 3);
        grid .add(lblmsg,1,2);
        
       Reflection r=new Reflection();
       r.setFraction(0.7f);
       grid.setEffect(r);
       
       DropShadow drop=new DropShadow();
       drop.setOffsetX(5);
       drop.setOffsetY(5);
       
       Text text=new Text("Login Portal");
       text.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
       text.setEffect(drop);
       text.setFill(Color.BLACK);

        Group root=new Group();
       Image image = new Image("http://icons.iconarchive.com/icons/hopstarter/soft-scraps/48/Lock-Unlock-icon.png");
          ImageView view=new ImageView(image);
         root.getChildren().add(view);
       hb.getChildren().add(text);
       
       bp.setId("bp");
       grid.setId("root");
       btn.setId("text");
       
       
       sign.setOnAction(new EventHandler() {

           
          
        //  Create c=new Create();
           
            @Override
            public void handle(Event event) {
                signup x =new signup();
                x.setVisible(true);
               
            }
        });
               
       
       btn.setOnAction(new EventHandler<ActionEvent>()
       {

            @Override
            public void handle(ActionEvent event) {
                checkUser=txt.getText().toString() ;
                checkPw=pwd.getText().toString() ;
               
                if(checkUser.equals(user) && checkPw.equals(pw))
                {

           lblmsg.setText("Congratulations!");
           new WebBrowser1();
           txt.setText(user);
          lblmsg.setTextFill(Color.GREEN);
          txt.setDisable(true);
          pwd.setDisable(true);
         // Main m=new Main();
        //WebBrowser br=new WebBrowser();
                     // txt.setText(user);
         
         //w.setVisible(true);
  
          
     
        
          }
          else{
           lblmsg.setText("Something went wrong.");

           lblmsg.setTextFill(Color.RED);
          // new WebBrowser();
          }
          txt.setText("");
          pwd.setText("");
         }

            
       });
        bp.setTop(hb);
        bp.setCenter(grid);
        
        //Scene scene=new Scene(bp);
       
        this.setScene(new Scene(bp,400,300));
        this.setResizable(false);
        String css = this.getClass().getResource("login.css").toExternalForm(); 
       
        this.show();
        
        
       
        
        
       
     
            
            
            
            
          
    }  
    public static void main(String[] args) {

        launch(args);
    }
}
