package Nodes;

import java.net.InetAddress;
import java.net.UnknownHostException;

import application.Main;
import application.ServerThread;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class RunMode extends StackPane{
	
	
	public RunMode(BorderPane layout) {
		
		this.setAlignment(Pos.CENTER);
		Label choose=new Label("Activation mode");
		Label information=new Label();
		TextField serverIP=new TextField();
		ComboBox<String> inputchoose=new ComboBox<>();
		Button activate=new Button("Activate now");
		serverIP.setPromptText("your server ip");
		serverIP.setEditable(false);
		inputchoose.getItems().addAll("Client only","Server only","Hybrid mode");
		inputchoose.getSelectionModel().selectedItemProperty().addListener(e->{
			switch(inputchoose.getSelectionModel().getSelectedItem()) {
			case "Client only":
				information.setText("this mode will activate gui interface for database management, but you need to connect external database server");
				serverIP.setEditable(true);
				break;
			case "Server only":
				information.setText("this mode will activate gui interface for database management, but you need to connect external database server");
				serverIP.setEditable(false);
				try {
					serverIP.setPromptText(InetAddress.getLocalHost().getHostAddress());
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					information.setText("internal error. cnnot connecting to loop address");
				}
				break;
			
			case "Hybrid mode":
				information.setText("this mode combine server and gui client. optimal choise for program testing");
				serverIP.setEditable(false);
				try {
					serverIP.setPromptText(InetAddress.getLocalHost().getHostAddress());
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					information.setText("internal error. cnnot connecting to loop address");
				}
				break;
			}
		});
		inputchoose.getSelectionModel().select("Hybrid mode");
		
		
		activate.setOnAction(e->{
			switch(inputchoose.getSelectionModel().getSelectedItem()) {
			case "Client only":
				
				try {
					Main.setServer(false);
					Main.setClient(true);
					Main.setServIP(InetAddress.getByName(serverIP.getText()));
					layout.setCenter(new Login(layout));
					layout.setTop(ToolsBar.getInstance());
				} catch (UnknownHostException e2) {
					// TODO Auto-generated catch block
					information.setText("connection faild");
				}
				break;
			case "Server only":
				try {
					Main.setServIP(InetAddress.getLocalHost());
					Main.setServer(true);
					Main.setClient(false);
					layout.setCenter(new ServerLayout());
					ServerThread st=new ServerThread();
					st.start();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					information.setText("internal error. cannot connecting to loop address");
				}
					
				break;
			
			case "Hybrid mode":
				try {
					Main.setServIP(InetAddress.getLocalHost());
					Main.setServer(true);
					Main.setClient(true);
					ServerThread st=new ServerThread();
					st.start();
					layout.setCenter(new Login(layout));
					layout.setBottom(new ServerLayout());
					layout.setTop(ToolsBar.getInstance());
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					information.setText("internal error. cnnot connecting to loop address");
				}
				break;
			}
		});
		
		
		choose.setStyle("-fx-translate-x:-150;-fx-translate-y:-200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
		inputchoose.setStyle("-fx-translate-x:150;-fx-translate-y:-200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		serverIP.setStyle("-fx-translate-x:0;-fx-translate-y:-50;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		information.setStyle("-fx-translate-x:0;-fx-translate-y:100;-fx-min-width:600;-fx-max-width:600;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14;");
		activate.setStyle("-fx-translate-x:400;-fx-translate-y:-200;-fx-min-width:150;-fx-max-width:150;-fx-min-height:120;-fx-max-height:120;-fx-font-size:20;-fx-background-radius:15px");
		this.getChildren().addAll(choose,inputchoose,serverIP,information,activate);
	}

}
