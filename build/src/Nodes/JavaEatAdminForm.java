package Nodes;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Stack;

import CRM.CrmThread;
import Forms.ConfirmBox;
import Model.Restaurant;
import application.Main;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


public class JavaEatAdminForm extends StackPane{
	


	private static JavaEatAdminForm form;


	public static void setForm(JavaEatAdminForm form) {
		JavaEatAdminForm.form = form;
	}

	public static JavaEatAdminForm getInstance() {
		if(form==null) {
			try {
				form=new JavaEatAdminForm();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.exit(0);
			}
		}
		return form;
	}

	@SuppressWarnings("deprecation")
	public JavaEatAdminForm() throws IOException {
		

		Main.getLayout().setStyle("-fx-background-image:url('file:images/AdminBackground.png');-fx-background-position: center center;-fx-background-repeat: stretch;");
		ImageView logo=new ImageView(new Image("file:images/logo&slogen.png"));
		logo.setStyle("-fx-min-width:350;-fx-max-width:350;-fx-min-height:200;-fx-max-height:200;");
		Main.getLayout().setLeft(logo);
		
		Button newM=new Button("Add");
		Button remM=new Button("Remove");
		Button editM=new Button("Edit");
		Button quotM=new Button("Quota");
		Button crmM=new Button("crm");
		Button logoutM=new Button("Logout");
		
		newM.setStyle("-fx-translate-x:-200;-fx-translate-y:-300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:100;-fx-max-height:150;-fx-font-size:25;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:50px");
		editM.setStyle("-fx-translate-x:-200;-fx-translate-y:-100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:100;-fx-max-height:150;-fx-font-size:25;-fx-font-weight:bold;-fx-background-color:#1279BE;-fx-background-radius:50px");
		remM.setStyle("-fx-translate-x:-200;-fx-translate-y:100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:100;-fx-max-height:150;-fx-font-size:25;-fx-font-weight:bold;-fx-background-color:#E83334;-fx-background-radius:50px");
		crmM.setStyle("-fx-translate-x:200;-fx-translate-y:-300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:100;-fx-max-height:150;-fx-font-size:25;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:50px");
		quotM.setStyle("-fx-translate-x:200;-fx-translate-y:-100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:100;-fx-max-height:150;-fx-font-size:25;-fx-font-weight:bold;-fx-background-color:#1279BE;-fx-background-radius:50px");
		logoutM.setStyle("-fx-translate-x:200;-fx-translate-y:100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:100;-fx-max-height:150;-fx-font-size:25;-fx-font-weight:bold;-fx-background-color:#E83334;-fx-background-radius:50px");
		
		newM.setOnAction(e->Main.getLayout().setCenter(new NewMenu(Main.getLayout())));
		editM.setOnAction(e->Main.getLayout().setCenter(new EditMenu(Main.getLayout())));
		remM.setOnAction(e->Main.getLayout().setCenter(new RemoveMenu(Main.getLayout())));
		crmM.setOnAction(e->{
			try {
				Main.getLayout().setCenter(new CrmLayout());
			} catch (InterruptedException | IOException e1) {e1.printStackTrace();	}
		});
		quotM.setOnAction(e->Main.getLayout().setCenter(new QuotaMenu(Main.getLayout())));
		logoutM.setOnAction(e->{
			boolean close=ConfirmBox.display("Do you sure you want to exit?");
			if(close) {
				try {
					Restaurant.getRestaurant().writeObject(new ObjectOutputStream(new FileOutputStream("Rest.ser")));
					CrmThread.getInstance().stop();
					System.exit(1);
				} catch (IOException e1) {			}
			}
		});

		this.getChildren().addAll(newM,remM,editM,quotM,crmM,logoutM);
		ToolsBar.getInstance().getLastStacks().push(this);
	}












}
