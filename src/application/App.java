package application;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.InetAddress;

import Exceptions.BlankFieldException;
import Exceptions.IllegalCharacterException;
import View.RunMode;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/*App class is actualy the Main class. from compilation reasons I was divide it fot 2 different classes.
 * App class contains the Main static properties for all of the program: the ip of the internal/external server,
 *  if there is client or server and what is the proportion of the resolution.
 *  App has another 2 static helper for checking fields for having illegal char and another method for external set the inductive of textfiels.
 *  the first layout in that program is RunMode form.**/

public class App extends Application {
	private static Stage window;
	
	private static boolean server;
	private static boolean client;
	private static InetAddress servIP;
	private static double w;
	private static double h;
	private static double s;
	
	public static double getS() {
		return s;
	}


	public static void setS(double s) {
		App.s = s;
	}


	private static BorderPane layout;
	
	public void start(Stage primaryStage) {
		try {
			window=primaryStage;
			layout=new BorderPane();
			Dimension res=Toolkit.getDefaultToolkit().getScreenSize();
			double width=res.getWidth();
			double height=res.getHeight();
			double size=Math.sqrt(width*width+height*height);
			w=width/1980;
			h=height/1080;
			s=size/Math.sqrt(1980*1980+1080/1080);
			Scene scene=new Scene(layout,width,height);
			RunMode run=new RunMode();
			layout.setCenter(run);
			window.setFullScreen(true);
			window.setScene(scene);
			window.show();
			
		} catch(Exception e) {
			e.printStackTrace();
			System.err.println("error, establish faild");
			System.exit(0);
		}
	}


	public static BorderPane getLayout() {
		return layout;
	}


	public static void setLayout(BorderPane layout) {
		App.layout = layout;
	}


	public static boolean isServer() {
		return server;
	}


	public static Stage getWindow() {
		return window;
	}


	public static void setWindow(Stage window) {
		App.window = window;
	}


	public static void setServer(boolean server) {
		App.server = server;
	}


	public static boolean isClient() {
		return client;
	}


	public static void setClient(boolean client) {
		App.client = client;
	}


	public static double getW() {
		return w;
	}


	public static void setW(double w) {
		App.w = w;
	}


	public static double getH() {
		return h;
	}


	public static void setH(double h) {
		App.h = h;
	}


	public static InetAddress getServIP() {
		return servIP;
	}


	public static void setServIP(InetAddress servIP) {
		App.servIP = servIP;
	}


	public static void app(String[] args) {
		launch(args);
	}
	public static boolean checkLegalCharacters(TextField tx) throws BlankFieldException, IllegalCharacterException {
		String s=tx.getText();
		if(s.isBlank()) {
			throw new BlankFieldException();
		}
		char[] characters= {' ','/','-','!',',','$','\\','%'};
		for(Character c:characters) {
			if(s.contains(String.valueOf(c))) {
				throw new IllegalCharacterException(s);
			}
		}
		return true;
	}
	public static void setBorderColor(Node tx,String color) {
		String[] style=tx.getStyle().split(";");
		for(int i=0;i<style.length;i++) {
			String s=style[i];
			if(s.contains("-fx-border-color")) {
				style[i]="-fx-border-color:"+color;
				tx.setStyle(String.join(";", style));
				return;
			}
		}
		tx.setStyle("-fx-border-color:"+color+";"+String.join(";", style));
		return;
	}

}
