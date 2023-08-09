package Nodes;

import application.Main;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class ServerLayout extends StackPane{
	
	public ServerLayout() {
		
		Label running=new Label("Server is running! closing that window will close the server.");
		Label ip=new Label("Your IP: " +Main.getServIP().toString());
		Label download=new Label("Enter from browser for downloads clients");
		
		running.setStyle("-fx-translate-x:0;-fx-translate-y:-100;-fx-min-width:600;-fx-max-width:600;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14;-fx-text-fill:RED");
		ip.setStyle("-fx-translate-x:0;-fx-translate-y:-50;-fx-min-width:600;-fx-max-width:600;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14");
		download.setStyle("-fx-translate-x:0;-fx-translate-y:0;-fx-min-width:600;-fx-max-width:600;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14;");
		
		this.getChildren().addAll(running,ip,download);
		
	}

}
