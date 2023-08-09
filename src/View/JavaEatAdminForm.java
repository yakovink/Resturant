package View;

import java.io.IOException;

import View.Nodes.ToolsBar;
import application.App;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/*this is the main admin form. it has 7 buttons:
 * Add - for add methods.
 * Remove - for remove methods.
 * edis - for edit methods.
 * quota - for the 7 quotas from exersice 2.
 * Crm - for crm layout.
 * All data - for the data display pane.
 * logout - back to login.**/


public class JavaEatAdminForm extends StackPane{
	






	public JavaEatAdminForm() throws IOException {
		ToolsBar.getInstance().getPlace().pushText("Welcome manager!");
		double w=App.getW();
		double h=App.getH();
		double s=App.getS();
		
		App.getLayout().setStyle("-fx-background-image:url('file:images/AdminBackground.png');-fx-background-position: center center;-fx-background-repeat: stretch;");
		
		ImageView logo=new ImageView(new Image("file:images/logo&slogen.png"));
		logo.setStyle(String.format("-fx-translate-x:0;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;",550*App.getH(),350*App.getW(),350*App.getW(),200*App.getH(),200*App.getH()));
		logo.setFitWidth(350*App.getW());
		logo.setFitHeight(200*App.getH());
		App.getLayout().setLeft(logo);
		
		
		Button newM=new Button("Add");
		Button remM=new Button("Remove");
		Button editM=new Button("Edit");
		Button quotM=new Button("Quota");
		Button crmM=new Button("crm");
		Button logoutM=new Button("Logout");
		Button allData=new Button ("All data");
		
		newM.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:50px",-200*w,-300*h,200*w,200*w,100*h,150*h,25*s));
		editM.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#1279BE;-fx-background-radius:50px",-200*w,-100*h,200*w,200*w,100*h,150*h,25*s));
		remM.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#E83334;-fx-background-radius:50px",-200*w,100*h,200*w,200*w,100*h,150*h,25*s));
		crmM.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:50px",200*w,-300*h,200*w,200*w,100*h,150*h,25*s));
		quotM.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#1279BE;-fx-background-radius:50px",200*w,-100*h,200*w,200*w,100*h,150*h,25*s));
		logoutM.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#E83334;-fx-background-radius:50px",200*w,100*h,200*w,200*w,100*h,150*h,25*s));
		allData.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#B2B2B2;-fx-background-radius:50px",0*w,300*h,200*w,200*w,100*h,150*h,25*s));
		
		
		newM.setOnAction(e->App.getLayout().setCenter(new NewMenu()));
		editM.setOnAction(e->App.getLayout().setCenter(new EditMenu()));
		remM.setOnAction(e->App.getLayout().setCenter(new RemoveMenu()));
		allData.setOnAction(e->App.getLayout().setCenter(new AllDataMenu()));
		crmM.setOnAction(e->{

				try {
					App.getLayout().setCenter(new CrmLayout());
				} catch (IOException e1) {
					PopUp.display("internal error", false);
				}
		});
		quotM.setOnAction(e->App.getLayout().setCenter(new QuotaMenu()));
		logoutM.setOnAction(e->{
			ToolsBar.getInstance().getLastStacks().clear();;
			
			App.getLayout().setCenter(new Login());
			App.getLayout().setLeft(null);
			App.getLayout().setTop(ToolsBar.getInstance());
		});

		this.getChildren().addAll(newM,remM,editM,quotM,crmM,logoutM,allData);
		ToolsBar.getInstance().getLastStacks().push(App.getLayout().getCenter());
	}












}
