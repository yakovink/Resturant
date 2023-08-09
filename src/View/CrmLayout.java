package View;


/*this is the CRM layout. it has 3 filtered list for cooks, delivery persons and orders.
 *that layout changed dynamicly by SyncThread process**/

import java.io.IOException;

import CRM.SyncThread;
import View.Nodes.ToolsBar;
import application.App;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;

public class CrmLayout extends StackPane{
	
	ListView<String> cooks;
	ListView<String> deliveryPersons;
	ListView<String> orders;


	


	public CrmLayout() throws IOException  {
		ToolsBar.getInstance().getPlace().pushText("CRM");
		double w=App.getW();
		double h=App.getH();
		double s=App.getS();
		this.setAlignment(Pos.CENTER);
		cooks=new ListView<>();
		deliveryPersons=new ListView<>();
		orders=new ListView<>();

		Label cook=new Label("Cooks");
		Label dp=new Label("Delivery Persons");
		Label ord=new Label("Orders");
		SyncThread sync=new SyncThread(this);
		sync.start();
		sync.getOut().println("sync");
		String[] lists=sync.getIn().readLine().split("-");
		
		cooks.getItems().addAll(lists[0].split(","));
		deliveryPersons.getItems().addAll(lists[1].split(","));
		orders.getItems().addAll(lists[2].split(","));
		cook.setStyle(String.format("-fx-translate-x:-400;-fx-translate-y:-350;-fx-max-width:350;-fx-max-height:50;-fx-font-size:20;-fx-font-weight:bold",-400*w,-350*h,350*w,50*h,20*s));
		dp.setStyle(String.format("-fx-translate-x:0;-fx-translate-y:-350;-fx-max-width:350;-fx-max-height:50;-fx-font-size:20;-fx-font-weight:bold",0*w,-350*h,350*w,50*h,20*s));
		ord.setStyle(String.format("-fx-translate-x:400;-fx-translate-y:-350;-fx-max-width:350;-fx-max-height:50;-fx-font-size:20;-fx-font-weight:bold",400*w,-350*h,350*w,50*h,20*s));
		cooks.setStyle(String.format("-fx-translate-x:-400;-fx-translate-y:-100;-fx-max-width:350;-fx-max-height:400;-fx-font-size:20;",-400*w,-100*h,350*w,400*h,20*s));
		deliveryPersons.setStyle(String.format("-fx-translate-x:0;-fx-translate-y:-100;-fx-max-width:350;-fx-max-height:400;-fx-font-size:20",0*w,-100*h,350*w,400*h,20*s));
		orders.setStyle(String.format("-fx-translate-x:400;-fx-translate-y:-100;-fx-max-width:350;-fx-max-height:400;-fx-font-size:20",400*w,-100*h,350*w,400*h,20*s));
		this.getChildren().addAll(cooks,deliveryPersons,orders,cook,dp,ord);
		ToolsBar.getInstance().getLastStacks().push(App.getLayout().getCenter());
		App.getLayout().setCenter(this);
		
	}

	public ListView<String> getCooks() {
		return cooks;
	}

	public void setCooks(ListView<String> cooks) {
		this.cooks = cooks;
	}

	public ListView<String> getDeliveryPersons() {
		return deliveryPersons;
	}

	public void setDeliveryPersons(ListView<String> deliveryPersons) {
		this.deliveryPersons = deliveryPersons;
	}

	public ListView<String> getOrders() {
		return orders;
	}

	public void setOrders(ListView<String> orders) {
		this.orders = orders;
	}
}
