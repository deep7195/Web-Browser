
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.scene.image.Image;
import java.util.ArrayList;

import javafx.scene.web.WebHistory;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TabPane;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class WebBrowser1 extends Stage
                        {
                        String result;
                        Scene scene;
                        BorderPane root;
                        Button reloadButton, goButton, backButton, forwardButton,
                                homeButton, sethomeButton, addbookmarkButton,
                                clearBookmarkButton,clearHistoryButton,
                                newwindowButton,loginButton,proxyButton;
        
                        ComboBox<String> bookmarksComboBox ;
                        ComboBox<String> HistoryComboBox;
                        TextField addressField;
                        WebView webView;
                        WebView smallView = new WebView();
                        ProgressBar progressBar;
                        WebEngine webEngine;
                        ColorPicker picker;
                        
                        int count=0;
                        


                        private String homeAddress = "www.google.com";
                        private ArrayList<String> addresses = new ArrayList<String>();
                        private int addressPointer = -1;
        
        
                        
                        
                        public void loadRandomAddress(String address) 
                        {
                        if(address == null)
                        {address = addressField.getText();
                        }
                       progressBar.progressProperty().bind(webEngine.getLoadWorker().progressProperty());
                         webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>()
                        {
                        @Override
                        public void changed(ObservableValue<? extends Worker.State> value,
			Worker.State oldState, Worker.State newState)
                        {
			if(newState == Worker.State.SUCCEEDED)
                                {
				System.out.println("Location loaded + " + webEngine.getLocation());
                                addressField.setText(webEngine.getLocation());
                                try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("HistoryFile.txt", true))))
                                {
                                out.println(webEngine.getLocation()); 
                                            }
                                catch (IOException e) {
                                    e.printStackTrace();
                                    }
                                
                                //For Reading History from the file
                                
                             
                              
                                
                               BufferedReader br = null;
                                
                            try {

			String sCurrentLine;
                        
			br = new BufferedReader(new FileReader("HistoryFile.txt"));
                        
			while ((sCurrentLine = br.readLine()) != null) {
                                
				HistoryComboBox.getItems().add(sCurrentLine);
                                
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
                                
                              //reading Bookmark from a file
                      
                        br = null;
                            try {

			String sCurrentLine;
                         
			br = new BufferedReader(new FileReader("BookmarkFile.txt"));

			while ((sCurrentLine = br.readLine()) != null) {
                            System.out.println(sCurrentLine);
				bookmarksComboBox.getItems().add(sCurrentLine);
                                
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
                            
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}  
		

                                
                                
				}
                        if(newState == Worker.State.FAILED)
                        {
                        webEngine.load("file:///C:/Users/Akash/Desktop/new/Capture.JPG");
                        
                        		}
			}});
            
                        
                        address = extractAddress(address);
                        System.out.println(address);
                        webEngine.load("http://" + address);
                        addressField.setText(webEngine.getLocation());
                         removeObsoleteAddresses();
                        addresses.add(address);
                        resetButtons();

       
	}
  private void removeObsoleteAddresses() {
		addressPointer++;
		while (addresses.size() - 1 >= addressPointer) {
			addresses.remove(addresses.size() - 1);
		}
	}

	private void loadPointedAddress() {
		System.out.println(addresses.get(addressPointer));
		webEngine.load("http://" + addresses.get(addressPointer));
		addressField.setText(webEngine.getLocation());
	}

        
        private void resetButtons() {
		System.out.println(addresses);
		System.out.println(addressPointer);

		if (addressPointer <= 0)
			backButton.setDisable(true);
		else
			backButton.setDisable(false);

		if (addressPointer >= addresses.size() - 1)
			forwardButton.setDisable(true);
		else
			forwardButton.setDisable(false);
	}

	private String extractAddress(String fullAddress) {
            result = fullAddress;
		if (fullAddress.startsWith("http://") ) {
			result = fullAddress.substring("http://".length());
		}
		else if (fullAddress.startsWith("https://")) {
			result = fullAddress.substring("https://".length());
		} 
		
		return result;
	}



        
        
      WebBrowser1()
      {
            
        //    stage.initStyle(StageStyle.TRANSPARENT);
               //StageStyle.TRANSPARENT
		System.out.println(Thread.currentThread().getName());
//                
                
		// The vertical box that will hold two horizontal boxes.
		VBox vBox = new VBox(5);
		vBox.setAlignment(Pos.CENTER);
                
                
                

		// Horizontal boxes that will host buttons and address field.
		HBox hBox0 = new HBox(5);
		hBox0.setAlignment(Pos.CENTER);
		HBox hBox1 = new HBox(5);
		hBox1.setAlignment(Pos.CENTER);
                

		//------------Buttons for navigation.----------------//
		reloadButton = new Button();
		reloadButton.setGraphic(new ImageView(new Image(getClass()
				.getResourceAsStream("resources/refresh.png"))));
		goButton = new Button();
                goButton.setGraphic(new ImageView(new Image(getClass()
				.getResourceAsStream("resources/go.png"))));
		backButton = new Button();
		backButton.setGraphic(new ImageView(new Image(getClass()
				.getResourceAsStream("resources/back3.jpg"))));
		forwardButton = new Button();
		forwardButton.setGraphic(new ImageView(new Image(getClass()
				.getResourceAsStream("resources/forward3.jpg"))));
		homeButton = new Button();
		homeButton.setGraphic(new ImageView(new Image(getClass()
				.getResourceAsStream("resources/home1.png"))));
		sethomeButton = new Button("Set Home");
		clearBookmarkButton = new Button("clearB");
                clearHistoryButton =new Button("ClearH");
		addbookmarkButton = new Button();
		addbookmarkButton.setGraphic(new ImageView(new Image(getClass()
				.getResourceAsStream("resources/star1.png"))));
                newwindowButton=new Button();
                newwindowButton.setGraphic(new ImageView(new Image(getClass()
				.getResourceAsStream("resources/new.png"))));
                
                picker = new ColorPicker();
                
                progressBar = new ProgressBar(0);
                loginButton=new Button();
                loginButton.setGraphic(new ImageView(new Image(getClass()
				.getResourceAsStream("resources/incognito.png"))));
                proxyButton=new Button();
                proxyButton.setGraphic(new ImageView(new Image(getClass()
				.getResourceAsStream("resources/off.png"))));                                               
              
		//------------ComboBox for bookmarks----------//
		bookmarksComboBox = new ComboBox<String>();
		bookmarksComboBox.setMaxWidth(200);
		bookmarksComboBox.setMinWidth(200);
		bookmarksComboBox.setPromptText("Go to bookmark ...");
		//bookmarksComboBox.valueProperty().addListener(bookmarks);
                
                HistoryComboBox = new ComboBox<String>();
		HistoryComboBox.setMaxWidth(200);
		HistoryComboBox.setMinWidth(200);
		HistoryComboBox.setPromptText("Go to history ...");
                
               
     
		// The TextField for entering URLs.
		addressField = new TextField("Enter URLs here...");
		addressField.setPrefColumnCount(50);


		//--------- Add all out navigation nodes to the hbox.----------//
                    
                        
		hBox0.getChildren().addAll(backButton, forwardButton, homeButton,
                                    reloadButton, addressField, goButton,newwindowButton,loginButton,proxyButton);
		hBox1.getChildren().addAll(addbookmarkButton,
                                    bookmarksComboBox,clearBookmarkButton,progressBar, HistoryComboBox,
                                    clearHistoryButton,sethomeButton,picker);
               vBox.getChildren().addAll(hBox0, hBox1);
             
                
		// WebVFpiew that displays the page.
                                                      
                                                     
		webView = new WebView();
                   
		// The engine that manages the pages.
		webEngine = webView.getEngine();
		webEngine.setJavaScriptEnabled(true);
		homeAddress = extractAddress(homeAddress);
		webEngine.load("http://" + homeAddress);
                loadRandomAddress(homeAddress);
                
                
                
		BorderPane root = new BorderPane();
		root.setPrefSize(1024, 768);
                root.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #b3ccff, #661a33)");
		root.setTop(vBox);
                root.setCenter(webView);
                
                   
                 this.setScene(new Scene(root,1366,715));
                 this.show();
   
		


                webEngine.setCreatePopupHandler(new Callback<PopupFeatures, WebEngine>() {
                @Override public WebEngine call(PopupFeatures config) {                
                    {
                        root.setCenter(smallView);
                    
                    return smallView.getEngine();
                    }
                }
                     });
                
               
            picker.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                Color value = picker.getValue();
                String colorString = value.toString();
                String substring = colorString.substring(2, colorString.length()-2);
                root.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#" + substring + ", #661a33)");
                }
                });
         proxyButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
                   count ++;
                   System.out.println("COUNT="+count);
                   if(count%2!=0)
                   { proxyButton.setGraphic(new ImageView(new Image(getClass()
				.getResourceAsStream("resources/on.png"))));
                   final Glow glow = new Glow();
                   glow.setLevel(0.6);
                   proxyButton.setEffect(glow);
                   
                        //proxyButton.setDisable(true);
                   }
                   else
                   {
                       proxyButton.setGraphic(new ImageView(new Image(getClass()
				.getResourceAsStream("resources/off.png"))));
                       final Glow glow = new Glow();
                   glow.setLevel(0.0);
                   proxyButton.setEffect(glow);
                         
                   }
		}
	});                
                                               
	loginButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
		 new Login();
		}
	});   
        
    newwindowButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
		 new WebBrowser();
		}
	});
      
        
     homeButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			loadRandomAddress(homeAddress);
		}
	});
     
      backButton.setOnAction (new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {

			addressPointer--;
			if (addressPointer >= 0) {
				loadPointedAddress();
			} else {
				addressPointer = 0;
			}
			resetButtons();
		}
	});

	forwardButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {

			addressPointer++;
			if (addressPointer <= addresses.size() - 1) {
				loadPointedAddress();
			} else {
				addressPointer = addresses.size() - 1;
			}
			resetButtons();
		}
	});
        
        
       
	reloadButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			webEngine.reload();
			resetButtons();
		}
	});

	
	clearHistoryButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
	    File file=new File("HistoryFile.txt");
	     file.delete();	
             HistoryComboBox.getItems().removeAll(HistoryComboBox.getItems());
                              
          
		}
	});
	
	clearBookmarkButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			
		File file=new File("BookmarkFile.txt");
	     file.delete();
             bookmarksComboBox.getItems().removeAll(bookmarksComboBox.getItems());
                            
                              		
		}
	});
        
        addressField.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
                       String address = null;
                         if(count%2!=0)
                   { 
                      loadRandomAddress(address);
                   address = addressField.getText();
                    webEngine.load("http://www.teampirateproxy.appspot.com/" + address.replace("http://",""));
                    
                        addressField.setText(address);
                        //proxyButton.setDisable(true);
                   }
                   else
                   {
                       
                       
	
                    address = addressField.getText();
                    webEngine.load("http://" + address);
                    loadRandomAddress(address);
                    
                        addressField.setText(address);   
                        
                   }
			
                       
                       
                        
		}
	});

      goButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
                       String address = null;
                         if(count%2!=0)
                   { 
                      loadRandomAddress(address);
                   address = addressField.getText();
                    webEngine.load("http://www.teampirateproxy.appspot.com/" + address.replace("http://",""));
                    
                        addressField.setText(address);
                        //proxyButton.setDisable(true);
                   }
                   else
                   {
                       
                       
	
                    address = addressField.getText();
                    webEngine.load("http://" + address);
                    loadRandomAddress(address);
                    
                        addressField.setText(address);   
                        
                   }
		
                       
                       
                        
		}
	});
      
	sethomeButton.setOnAction (new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {

			homeAddress = addressField.getText();
			homeAddress = extractAddress(homeAddress);
			System.out.println(homeAddress);
			resetButtons();

		}
	});
	
	addbookmarkButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
                        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("BookmarkFile.txt", true))))
                {
                          out.println(addressField.getText()); 
                                            }
                catch (IOException e) {
                e.printStackTrace();
                 }
			String address = webEngine.getLocation();
			address = extractAddress(address);
			System.out.println(address);
                        //adding to a bookmarks dropdown list
                      // bookmarksComboBox.getItems().add(webEngine.getLocation());
                       
			//bookmarksComboBox.getItems().add(addressField.getText());
                        
                       
                       //reading Bookmark from a file
                      
                        BufferedReader br = null;
                            try {

			String sCurrentLine;
                         
			br = new BufferedReader(new FileReader("BookmarkFile.txt"));

			while ((sCurrentLine = br.readLine()) != null) {
                            System.out.println(sCurrentLine);
				bookmarksComboBox.getItems().add(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
                            
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}  
		}
	});
      
	
	bookmarksComboBox.valueProperty().addListener(new ChangeListener<String>() {
		@Override
		public void changed(ObservableValue ov, String t, String t1) {
			
			webEngine.load(t1);
			addressField.setText(webEngine.getLocation());
			removeObsoleteAddresses();//
			addresses.add(t1);
			resetButtons();
		}
	});
        
        
        HistoryComboBox.valueProperty().addListener(new ChangeListener<String>() {
		@Override
		public void changed(ObservableValue ov, String t, String t1) {
			
			webEngine.load(t1);
			addressField.setText(webEngine.getLocation());
			removeObsoleteAddresses();
			addresses.add(t1);
			resetButtons();
		}
	});
        
	
}

////---------------MAIN---------------------////

	public static void main(String[] args)
   {
       launch(args);
   }
}

  
