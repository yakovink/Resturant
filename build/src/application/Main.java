package application;

	



import java.net.InetAddress;


import Nodes.RunMode;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class Main extends Application {
	private static Stage window;
	
	private static boolean server;
	private static boolean client;
	private static InetAddress servIP;
	
	private static BorderPane layout;
	
	public void start(Stage primaryStage) {
		try {
			window=primaryStage;
			layout=new BorderPane();
			
			RunMode run=new RunMode(layout);
			Scene scene=new Scene(layout,1980,1080);
			layout.setCenter(run);
			window.setScene(scene);
			window.setResizable(false);
			window.setFullScreen(true);
			window.show();
			
		} catch(Exception e) {
			System.err.println("error, establish faild");
			System.exit(0);
		}
	}


	public static BorderPane getLayout() {
		return layout;
	}


	public static void setLayout(BorderPane layout) {
		Main.layout = layout;
	}


	public static boolean isServer() {
		return server;
	}


	public static Stage getWindow() {
		return window;
	}


	public static void setWindow(Stage window) {
		Main.window = window;
	}


	public static void setServer(boolean server) {
		Main.server = server;
	}


	public static boolean isClient() {
		return client;
	}


	public static void setClient(boolean client) {
		Main.client = client;
	}


	public static InetAddress getServIP() {
		return servIP;
	}


	public static void setServIP(InetAddress servIP) {
		Main.servIP = servIP;
	}


	public static void main(String[] args) {
		launch(args);
	}
	

}
