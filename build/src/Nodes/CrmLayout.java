package Nodes;


import java.io.IOException;

import CRM.SyncThread;
import application.Main;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class CrmLayout extends StackPane{
	
	ListView<String> cooks;
	ListView<String> deliveryPersons;
	ListView<String> orders;

	
	public CrmLayout() throws InterruptedException, IOException {
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
		cook.setStyle("-fx-translate-x:-400;-fx-translate-y:-350;-fx-max-width:350;-fx-max-height:50;-fx-font-size:20;-fx-font-weight:bold");
		dp.setStyle("-fx-translate-x:0;-fx-translate-y:-350;-fx-max-width:350;-fx-max-height:50;-fx-font-size:20;-fx-font-weight:bold");
		ord.setStyle("-fx-translate-x:400;-fx-translate-y:-350;-fx-max-width:350;-fx-max-height:50;-fx-font-size:20;-fx-font-weight:bold");
		cooks.setStyle("-fx-translate-x:-400;-fx-translate-y:-100;-fx-max-width:350;-fx-max-height:400;-fx-font-size:20;");
		deliveryPersons.setStyle("-fx-translate-x:0;-fx-translate-y:-100;-fx-max-width:350;-fx-max-height:400;-fx-font-size:20");
		orders.setStyle("-fx-translate-x:400;-fx-translate-y:-100;-fx-max-width:350;-fx-max-height:400;-fx-font-size:20");

		this.getChildren().addAll(cooks,deliveryPersons,orders,cook,dp,ord);
		ToolsBar.getInstance().getLastStacks().push(Main.getLayout().getCenter());
		Main.getLayout().setCenter(this);
		
		
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
