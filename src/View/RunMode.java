package View;


/*this is the runMode choosing pane. at this window the user need to decide if he want a server, a client for other cumputer server,
 * or a hybrid mode between them. the recommend mode for checking the program is the hybrid mode**/

import java.net.InetAddress;
import java.net.UnknownHostException;

import View.Nodes.ToolsBar;
import application.App;
import application.ServerThread;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class RunMode extends StackPane{
	
	private static RunMode run;
	
	public static RunMode getInstance() {
		if(run==null) {
			run=new RunMode();
		}
		return run;
	}
	
	
	public RunMode() {
		
		BorderPane layout=App.getLayout();
		
		double w=App.getW();
		double h=App.getH();
		double s=App.getS();
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
					App.setServer(false);
					App.setClient(true);
					App.setServIP(InetAddress.getByName(serverIP.getText()));
					layout.setTop(ToolsBar.getInstance());
					layout.setCenter(new Login());
					
				}
						

				 catch (UnknownHostException e2) {
					information.setText("connection faild");
				}
				break;
			case "Server only":
				try {
					App.setServIP(InetAddress.getLocalHost());
					App.setServer(true);
					App.setClient(false);
					
					layout.setCenter(new ServerLayout());
					ServerThread.getInstance().start();
					
					layout.setTop(ToolsBar.getInstance());
					
					break;
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					information.setText("internal error. cannot connecting to loop address");
					break;
				}
					
				
			
			case "Hybrid mode":
				try {
					App.setServIP(InetAddress.getLocalHost());
					App.setServer(true);
					App.setClient(true);
					ServerThread.getInstance().start();
					layout.setBottom(new ServerLayout());
					layout.setTop(ToolsBar.getInstance());
					layout.setCenter(new Login());
					
					
					
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					information.setText("internal error. cnnot connecting to loop address");
				}
				break;
			}
		});
		

		choose.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,-200*h,200*w,200*w,50*h,50*h,20*s));
		inputchoose.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,-200*h,200*w,200*w,50*h,50*h,14*s));
		serverIP.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,-50*h,200*w,200*w,50*h,(50*h),(20*s)));
		information.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",(0*w),(100*h),(1000*w),(1000*w),(50*h),(50*h),(14*s)));
		activate.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",(400*w),(-200*h),(150*w),(150*w),(120*h),(120*h),(14*s)));
		this.getChildren().addAll(choose,inputchoose,serverIP,information,activate);
	}

}
