package View;

import application.App;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/*that is the server layout, that show the server IP.
 *if you choose "server only" mode it will showd at the center of the screen.  if you choosed the hybrid one, it will showed at the bottom.**/

public class ServerLayout extends StackPane{
	
	public ServerLayout() {
		double w=App.getW();
		double h=App.getH();
		double s=App.getS();
		
		
		Label running=new Label("Server is running! closing that window will close the server.");
		Label ip=new Label("Your IP: " +App.getServIP().toString());
		
		running.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-text-fill:RED",0*w,-100*h,600*w,600*w,50*h,50*h,14*s));
		ip.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:14",0*w,-50*h,600*w,600*w,50*h,50*h,14*s));
		
		this.getChildren().addAll(running,ip);
		
	}

}
