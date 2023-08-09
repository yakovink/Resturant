package Nodes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.imageio.ImageIO;

import CRM.CrmThread;
import Exceptions.*;
import Utils.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class NewMenu extends StackPane{

	private Button addCook;
	private Button addDeliveryPerson;
	private Button addCustomer;
	private Button addComponent;
	private Button addDish;
	private Button addOrder;
	private Button addDelivery;
	private Button addArea;
	


	
	



	public NewMenu(BorderPane layout) {
		
		addCook=new Button("New Cook...");
		addDeliveryPerson=new Button("New DeliveryPerson...");
		addCustomer=new Button("New Customer...");
		addComponent=new Button("New Component...");
		addDish=new Button("New Dish...");
		addOrder=new Button("New Order...");
		addDelivery=new Button("New Delivery...");
		addArea=new Button("New Area...");

		addCook.setStyle("-fx-translate-x:-200;-fx-translate-y:-100;-fx-min-width:300;-fx-max-width:300;-fx-min-height:100;-fx-max-height:100;-fx-font-size:25;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px");
		addDeliveryPerson.setStyle("-fx-translate-x:-200;-fx-translate-y:50;-fx-min-width:300;-fx-max-width:300;-fx-min-height:100;-fx-max-height:100;-fx-font-size:25;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px");
		addCustomer.setStyle("-fx-translate-x:-200;-fx-translate-y:200;-fx-min-width:300;-fx-max-width:300;-fx-min-height:100;-fx-max-height:100;-fx-font-size:25;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px");
		addComponent.setStyle("-fx-translate-x:-200;-fx-translate-y:-250;-fx-min-width:300;-fx-max-width:300;-fx-min-height:100;-fx-max-height:100;-fx-font-size:25;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px");
		addDish.setStyle("-fx-translate-x:200;-fx-translate-y:-100;-fx-min-width:300;-fx-max-width:300;-fx-min-height:100;-fx-max-height:100;-fx-font-size:25;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px");
		addOrder.setStyle("-fx-translate-x:200;-fx-translate-y:50;-fx-min-width:300;-fx-max-width:300;-fx-min-height:100;-fx-max-height:100;-fx-font-size:25;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px");
		addDelivery.setStyle("-fx-translate-x:200;-fx-translate-y:200;-fx-min-width:300;-fx-max-width:300;-fx-min-height:100;-fx-max-height:100;-fx-font-size:25;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px");
		addArea.setStyle("-fx-translate-x:200;-fx-translate-y:-250;-fx-min-width:300;-fx-max-width:300;-fx-min-height:100;-fx-max-height:100;-fx-font-size:25;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px");
		
		this.addCook.setOnAction(e->newCook(layout));
		this.addDeliveryPerson.setOnAction(e->newDeliveryPerson(layout));
		this.addCustomer.setOnAction(e->newCustomer(layout));
		this.addComponent.setOnAction(e->newComponent(layout));
		this.addDish.setOnAction(e->newDish(layout));
		this.addOrder.setOnAction(e->newOrder(layout));
		this.addDelivery.setOnAction(e->newDelivery(layout));
		this.addArea.setOnAction(e->newArea(layout));
	
		
		this.getChildren().addAll(addCook,addDeliveryPerson,addCustomer,addComponent,addDish,addOrder,addDelivery,addArea);
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(this);
		
	}





	private void newArea(BorderPane layout) {
		StackPane grid=new StackPane();
		Label status=new Label("");
		Label areaName=new Label("Area name:     ");
		Label deliveryTime=new Label("Delivery time: ");
		TextField inputName=new TextField();
		TextField inputTime=new TextField();
		Button button=new Button("Done");
		FilteredList<Neighberhood> neighbehoods=new FilteredList<>(Arrays.asList(Neighberhood.values()));
		neighbehoods.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		button.setOnAction(e->{
			try {
			
			String nebs="";
			for(Neighberhood n:neighbehoods.getList().getSelectionModel().getSelectedItems()){
				nebs+=","+n.name();
			}
			nebs=nebs.substring(1);
			String buffer="addArea/"+inputName.getText()+"/"+nebs+"/"+inputTime.getText();
			ToolsBar.getInstance().getOut().println(buffer);
			buffer=ToolsBar.getInstance().getIn().readLine();
				if(!Boolean.parseBoolean(buffer.split("/")[0])) {
					throw new FaildToAddException();
				}
				inputName.clear();
				inputTime.clear();
				neighbehoods.getList().getSelectionModel().clearSelection();
				status.setText("Area added successfully!");
				status.setTextFill(Color.GREEN);
			}
			catch(Exception ec) {
				status.setText(ec.getMessage());
				status.setTextFill(Color.RED);
			}
		});
		
		areaName.setStyle("-fx-translate-x:100;-fx-translate-y:-200;-fx-font-size:20;-fx-min-width:150;-fx-max-width:150;-fx-min-height:40;-fx-max-height:40");
		deliveryTime.setStyle("-fx-translate-x:100;-fx-translate-y:-150;-fx-font-size:20;-fx-min-width:150;-fx-max-width:150;-fx-min-height:40;-fx-max-height:40");
		inputName.setStyle("-fx-translate-x:300;-fx-translate-y:-200;-fx-background-radius:15px;-fx-min-width:150;-fx-max-width:150;-fx-min-height:40;-fx-max-height:40");
		inputTime.setStyle("-fx-translate-x:300;-fx-translate-y:-150;-fx-background-radius:15px;-fx-min-width:150;-fx-max-width:150;-fx-min-height:40;-fx-max-height:40");
		neighbehoods.setStyle("-fx-translate-x:-175;-fx-translate-y:-100;-fx-min-width:150;-fx-max-width:150;-fx-min-height:300;-fx-max-height:300");
		button.setStyle("-fx-translate-x:500;-fx-translate-y:-300;-fx-background-radius:15px;-fx-min-width:150;-fx-max-width:150;-fx-min-height:120;-fx-max-height:120");
		status.setStyle("-fx-translate-x:500;-fx-translate-y:-100;-fx-font-size:15");
		

		grid.getChildren().addAll(areaName,deliveryTime,inputName,inputTime,neighbehoods,button,status);
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(grid);	
	}


	private void newDelivery(BorderPane layout) {
	
		ComboBox<String> type=new ComboBox<>();
		type.getItems().addAll("Express Delivery","Regular Delivery");
		
		//superClass Delivery Items
		Label dp=new Label("Delivery person: ");
		Label area=new Label("Delivery area: ");
		Label date=new Label("Delivery Date: ");
		ToolsBar.getInstance().getOut().println("getDeps");
		Collection<String> ourdps;
		try {
			ourdps=Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			ourdps=new HashSet<>();
		}
		FilteredBox<String> inputDp=new FilteredBox<>(ourdps);
		ToolsBar.getInstance().getOut().println("getDels");
		ComboBox<String> inputArea=new ComboBox<>();
		try {
			inputArea.getItems().addAll(ToolsBar.getInstance().getIn().readLine().split(","));
		} catch (IOException e3) {	}
		DatePicker inputDate=new DatePicker();
		Button button = new Button("Done");
		Label status=new Label();
		
		type.setStyle("-fx-translate-x:0;-fx-translate-y:0;-fx-min-width:300;-fx-max-width:300;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		dp.setStyle("-fx-translate-x:-150;-fx-translate-y:-300;-fx-min-width:250;-fx-max-width:250;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		area.setStyle("-fx-translate-x:-150;-fx-translate-y:-200;-fx-min-width:250;-fx-max-width:250;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		date.setStyle("-fx-translate-x:-150;-fx-translate-y:-100;-fx-min-width:250;-fx-max-width:250;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		inputDp.setStyle("-fx-translate-x:150;-fx-translate-y:-325;-fx-min-width:250;-fx-max-width:250;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		inputArea.setStyle("-fx-translate-x:150;-fx-translate-y:-200;-fx-min-width:250;-fx-max-width:250;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		inputDate.setStyle("-fx-translate-x:150;-fx-translate-y:-100;-fx-min-width:250;-fx-max-width:250;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		
		type.setPromptText("Choose Delivery type");
		type.getSelectionModel().selectedItemProperty().addListener(e->{
			if(type.getSelectionModel().getSelectedIndex()==0) {
				StackPane express=new StackPane();
				express.setAlignment(Pos.CENTER);
				
				Label order=new Label("Order: ");
				Label postage=new Label("Postage: ");
				ToolsBar.getInstance().getOut().println("getOrds");
				Collection<String> ourorders;
				try {
					ourorders = Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					ourorders = new HashSet<>();
				}
				FilteredBox<String> inputOrder=new FilteredBox<>(ourorders);
				TextField inputPostage=new TextField();

				
				order.setStyle("-fx-translate-x:-150;-fx-translate-y:0;-fx-min-width:250;-fx-max-width:250;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
				postage.setStyle("-fx-translate-x:-150;-fx-translate-y:100;-fx-min-width:250;-fx-max-width:250;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");		
				inputOrder.setStyle("-fx-translate-x:150;-fx-translate-y:-25;-fx-min-width:250;-fx-max-width:250;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
				inputPostage.setStyle("-fx-translate-x:150;-fx-translate-y:100;-fx-min-width:250;-fx-max-width:250;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
				button.setStyle("-fx-translate-x:450;-fx-translate-y:-75;-fx-min-width:150;-fx-max-width:150;-fx-min-height:120;-fx-max-height:120;-fx-font-size:20;-fx-background-radius:15px");
				status.setStyle("-fx-translate-x:450;-fx-translate-y:75;-fx-min-width:250;-fx-max-width:250;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
				type.setStyle("-fx-translate-x:0;-fx-translate-y:-400;-fx-min-width:300;-fx-max-width:300;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
				
				button.setOnAction(e2->{
					try {
						ToolsBar.getInstance().getOut().println("addExpDel/"+inputDp.getBox().getSelectionModel().getSelectedItem().split(" ")[0]+"/"+inputArea.getSelectionModel().getSelectedItem().split(" ")[0]+"/"+inputPostage.getText()+"/"+inputDate.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
						if(!Boolean.parseBoolean(ToolsBar.getInstance().getIn().readLine().split("/")[0])){
							throw new FaildToAddException();
						}
						inputDp.getBox().getSelectionModel().clearSelection();
						inputArea.getSelectionModel().clearSelection();
						inputOrder.getBox().getSelectionModel().clearSelection();
						status.setText("Delivery added successfully");
						status.setTextFill(Color.GREEN);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						status.setText(e1.getMessage());
						status.setTextFill(Color.RED);
					}
				});

				express.getChildren().addAll(type,dp,area,date,inputDp,inputArea,inputDate,button,order,postage,inputOrder,inputPostage,status);
				ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
				layout.setCenter(express);
			}
			
			else {
				StackPane regular=new StackPane();
				regular.setAlignment(Pos.CENTER);
				ToolsBar.getInstance().getOut().println("getOrds");
				Collection<String> ourorders;
				try {
					ourorders = Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					ourorders = new HashSet<>();
				}
				FilteredList<String> orders=new FilteredList<>(ourorders);
				ListView<String> inputOrders=new ListView<>();
				orders.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				inputOrders.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				Button addOrders=new Button("Add orders");
				Button removeOrders=new Button("Remove orders");

				addOrders.setOnAction(e2->{
					inputOrders.getItems().addAll(orders.getList().getSelectionModel().getSelectedItems());
					orders.getList().getSelectionModel().clearSelection();
				});
				removeOrders.setOnAction(e2->{
					inputOrders.getItems().removeAll(inputOrders.getSelectionModel().getSelectedItems());
				});
				
				button.setOnAction(e2->{

					try {
						String ords="";
						for(String s:inputOrders.getItems()) {
							ords+=","+s.split(" ")[0];
						}
						ords=ords.substring(1);
						ToolsBar.getInstance().getOut().println("addRegDel/"+ords+"/"+inputDp.getBox().getSelectionModel().getSelectedItem().split(" ")[0]+"/"+inputArea.getSelectionModel().getSelectedItem().split(" ")[0]+"/false/"+inputDate.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
						if(!Boolean.parseBoolean(ToolsBar.getInstance().getIn().readLine().split("/")[0])) {
							throw new FaildToAddException();
						}
						inputDp.getBox().getSelectionModel().clearSelection();
						inputArea.getSelectionModel().clearSelection();
						inputOrders.getItems().clear();
						orders.getList().getSelectionModel().clearSelection();
						status.setText("Delivery added successfully");
						status.setTextFill(Color.GREEN);
					} catch (IOException | FaildToAddException e1) {
						// TODO Auto-generated catch block
						status.setText(e1.getMessage());
						status.setTextFill(Color.RED);
					}
			});
				
				orders.setStyle("-fx-translate-x:-300;-fx-translate-y:200;-fx-min-width:300;-fx-max-width:300;-fx-min-height:500;-fx-max-height:500;");
				inputOrders.setStyle("-fx-translate-x:300;-fx-translate-y:165;-fx-min-width:300;-fx-max-width:300;-fx-min-height:420;-fx-max-height:400;");		
				addOrders.setStyle("-fx-translate-x:0;-fx-translate-y:0;-fx-min-width:250;-fx-max-width:250;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
				removeOrders.setStyle("-fx-translate-x:0;-fx-translate-y:100;-fx-min-width:250;-fx-max-width:250;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
				button.setStyle("-fx-translate-x:600;-fx-translate-y:-75;-fx-min-width:150;-fx-max-width:150;-fx-min-height:120;-fx-max-height:120;-fx-font-size:20;-fx-background-radius:15px");
				status.setStyle("-fx-translate-x:600;-fx-translate-y:75;-fx-min-width:250;-fx-max-width:250;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
				type.setStyle("-fx-translate-x:-0;-fx-translate-y:-400;-fx-min-width:300;-fx-max-width:300;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
				regular.getChildren().addAll(type,dp,area,date,inputDp,inputArea,inputDate,button,orders,inputOrders,addOrders,removeOrders);
				ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
				layout.setCenter(regular);
				
			}
		});
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(type);
	}
	
	//TreeSet<Order> orders, DeliveryPerson deliveryPerson, DeliveryArea area,	boolean isDelivered,LocalDate deliveredDate
	//DeliveryPerson deliveryPerson, DeliveryArea area, boolean isDelivered , Order order , double postage, LocalDate deliveredDate
	//DeliveryPerson deliveryPerson, DeliveryArea area, boolean isDelivered,LocalDate diliveredDate
	

	private void newOrder(BorderPane layout) {
		StackPane grid=new StackPane();
		grid.setAlignment(Pos.CENTER);
		
		Label customerId=new Label("customer ID: ");
		TextField inputCustomerId=new TextField();
		
		ListView<String> inputDishes=new ListView<>();
		Button addDishes=new Button("Add dish");
		Button removeDishes=new Button("Remove dish");
		Button button=new Button("Done");
		Label status=new Label();
		ToolsBar.getInstance().getOut().println("getDishes");
		Collection<String> ourdishes;
		try {
			ourdishes =Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			ourdishes = new HashSet<>();
		}
		FilteredList<String> dishes=new FilteredList<>(ourdishes);
		dishes.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		Label priceLabel=new Label();
		Label TimeToReady=new Label();
		
		inputDishes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		
		
		customerId.setStyle("-fx-translate-x:-100;-fx-translate-y:-500;-fx-min-width:150;-fx-max-width:150;-fx-min-height:40;-fx-max-height:40;-fx-font-size:20");
		inputCustomerId.setStyle("-fx-translate-x:100;-fx-translate-y:-500;-fx-min-width:150;-fx-max-width:150;-fx-min-height:40;-fx-max-height:40;-fx-font-size:20;-fx-background-radius:15px");
		dishes.setStyle("-fx-translate-x:-250;-fx-translate-y:0;-fx-min-width:250;-fx-max-width:250;-fx-min-height:500;-fx-max-height:500;");
		inputDishes.setStyle("-fx-translate-x:250;-fx-translate-y:-35;-fx-min-width:250;-fx-max-width:250;-fx-min-height:430;-fx-max-height:430;");
		addDishes.setStyle("-fx-translate-x:0;-fx-translate-y:-75;-fx-min-width:175;-fx-max-width:175;-fx-min-height:75;-fx-max-height:75;-fx-font-size:20;-fx-background-radius:15px");
		removeDishes.setStyle("-fx-translate-x:0;-fx-translate-y:75;-fx-min-width:175;-fx-max-width:175;-fx-min-height:75;-fx-max-height:75;-fx-font-size:20;-fx-background-radius:15px");
		priceLabel.setStyle("-fx-translate-x:-300;-fx-translate-y:300;-fx-min-width:300;-fx-max-width:300;-fx-min-height:100;-fx-max-height:100;-fx-font-size:20");
		TimeToReady.setStyle("-fx-translate-x:-300;-fx-translate-y:350;-fx-min-width:300;-fx-max-width:300;-fx-min-height:100;-fx-max-height:100;-fx-font-size:20");
		button.setStyle("-fx-translate-x:500;-fx-translate-y:-50;-fx-min-width:150;-fx-max-width:150;-fx-min-height:120;-fx-max-height:120;-fx-font-size:25;-fx-background-radius:15px");
		status.setStyle("-fx-translate-x:500;-fx-translate-y:100;-fx-min-width:150;-fx-max-width:150;-fx-min-height:100;-fx-max-height:100;-fx-font-size:20;");
		
		addDishes.setOnAction(e->{
			inputDishes.getItems().addAll(dishes.getList().getSelectionModel().getSelectedItems());
			dishes.getList().getSelectionModel().clearSelection();
			double price=0;
			int time=0;
			for(String d:inputDishes.getItems()) {
				price+=Double.parseDouble(d.split(" ")[3]);
				time+=Integer.parseInt(d.split(" ")[4]);
			}
			ToolsBar.getInstance().getOut().println("getCustArea/"+inputCustomerId.getText());
			try {
				time+=Integer.parseInt(ToolsBar.getInstance().getIn().readLine().split(" ")[3]);
			} catch (NumberFormatException | IOException e1) {	}
			if(!CrmThread.getInstance().getOrders().isEmpty()) {
				time+=CrmThread.getInstance().getOrders().last().getTimeLong().ByMinutes();
			}
			priceLabel.setText("for payment: "+price);
			TimeToReady.setText("Time to ready: "+time);
		});
		removeDishes.setOnAction(e->{
			inputDishes.getItems().removeAll(inputDishes.getSelectionModel().getSelectedItems());
			double price=0;
			int time=0;
			for(String d:inputDishes.getItems()) {
					price+=Double.parseDouble(d.split(" ")[3]);
					time+=Integer.parseInt(d.split(" ")[4]);
			}
			ToolsBar.getInstance().getOut().println("getCustArea/"+inputCustomerId.getText());
			try {
				time+=Integer.parseInt(ToolsBar.getInstance().getIn().readLine().split(" ")[3]);
			} 
			catch (NumberFormatException | IOException e1) {	}
			priceLabel.setText("for payment: "+price);
			TimeToReady.setText("Time to ready: "+time);
		});
		button.setOnAction(e->{
			try {
				String s="";
				for(String s2:inputDishes.getItems()) {
					s+=","+s2.split(" ")[0];
				}
				s=s.substring(1);
				ToolsBar.getInstance().getOut().println("addOrd/"+inputCustomerId.getText()+"/"+s);
				if(!Boolean.parseBoolean(ToolsBar.getInstance().getIn().readLine().split("/")[0])) {
					throw new FaildToAddException();
				}
				inputCustomerId.clear();
				dishes.getList().getSelectionModel().clearSelection();
				inputDishes.getItems().clear();
				status.setText("Order added successfully");
				status.setTextFill(Color.GREEN);
			}
			catch(Exception ex){
				status.setText(ex.getMessage());
				status.setTextFill(Color.RED);
			}
		});

		grid.getChildren().addAll(priceLabel,TimeToReady,customerId,inputCustomerId,addDishes,removeDishes,dishes,status,inputDishes,button);
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(grid);
		
	}

	//Customer customer, ArrayList<Dish> dishes, Delivery delivery

	private void newDish(BorderPane layout) {
		StackPane grid=new StackPane();
		grid.setAlignment(Pos.CENTER);
		
		Label dishName=new Label("Dish name: ");
		Label dishTime=new Label("Time to make: ");
		Label dishType=new Label("Dish type: ");
		TextField inputName=new TextField();
		TextField inputTime=new TextField();
		ComboBox<DishType> inputType=new ComboBox<>();
		ListView<String> inputComps=new ListView<>();
		ToolsBar.getInstance().getOut().println("getComps");
		Collection<String> ourcomps;
		try {
			ourcomps=Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			ourcomps=new HashSet<>();
		}
		FilteredList<String> dishComps=new FilteredList<>(ourcomps);
		Button button=new Button("Done");
		Button addComp=new Button("add componenets");
		Button removeComp=new Button("remove componenets");
		
		Label status=new Label();

		inputComps.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		dishComps.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		inputType.getItems().addAll(DishType.values());
		button.setOnAction(e->{
			try {
				String str="";
				for(String s:inputComps.getItems()) {
					str+=","+s.split(" ")[0];
				}
				str=str.substring(1);
				ToolsBar.getInstance().getOut().println("addDish/"+inputName.getText()+"/"+inputType.getSelectionModel().getSelectedItem().name()+"/"+str+"/"+inputTime.getText()+"/true");
				if(!Boolean.parseBoolean(ToolsBar.getInstance().getIn().readLine().split("/")[0])) {
					throw new FaildToAddException();
				}
				inputName.clear();
				inputTime.clear();
				inputType.getSelectionModel().clearSelection();
				inputComps.getItems().clear();
				status.setText("Dish added successfully");
				status.setTextFill(Color.GREEN);
			}
			catch(FaildToAddException|NumberFormatException|NullPointerException | IOException ex) {
				status.setText(ex.getMessage());
				status.setTextFill(Color.RED);
			}
		});
		addComp.setOnAction(e->{
			inputComps.getItems().addAll(dishComps.getList().getSelectionModel().getSelectedItems());
			dishComps.getList().getSelectionModel().clearSelection();
		});
		removeComp.setOnAction(e->{
			inputComps.getItems().removeAll(inputComps.getSelectionModel().getSelectedItems());
		});

		dishName.setStyle("-fx-translate-x:-100;-fx-translate-y:-500;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		dishTime.setStyle("-fx-translate-x:-100;-fx-translate-y:-400;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		dishType.setStyle("-fx-translate-x:-100;-fx-translate-y:-300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		inputName.setStyle("-fx-translate-x:100;-fx-translate-y:-500;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		inputTime.setStyle("-fx-translate-x:100;-fx-translate-y:-400;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		inputType.setStyle("-fx-translate-x:100;-fx-translate-y:-300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		dishComps.setStyle("-fx-translate-x:-300;-fx-translate-y:0;-fx-min-width:250;-fx-max-width:250;-fx-min-height:500;-fx-max-height:500;");
		inputComps.setStyle("-fx-translate-x:300;-fx-translate-y:-35;-fx-min-width:250;-fx-max-width:250;-fx-min-height:430;-fx-max-height:430;");
		addComp.setStyle("-fx-translate-x:0;-fx-translate-y:-50;-fx-min-width:250;-fx-max-width:150;-fx-min-height:75;-fx-max-height:75;-fx-font-size:20;-fx-background-radius:15px");
		removeComp.setStyle("-fx-translate-x:0;-fx-translate-y:50;-fx-min-width:250;-fx-max-width:150;-fx-min-height:75;-fx-max-height:75;-fx-font-size:20;-fx-background-radius:15px");
		button.setStyle("-fx-translate-x:600;-fx-translate-y:-50;-fx-min-width:150;-fx-max-width:150;-fx-min-height:120;-fx-max-height:120;-fx-font-size:25;-fx-background-radius:15px");
		status.setStyle("-fx-translate-x:600;-fx-translate-y:50;-fx-min-width:300;-fx-max-width:300;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		

		grid.getChildren().addAll(dishName,dishTime,dishType,inputName,inputTime,inputType,inputComps,dishComps,button,addComp,removeComp,status);
		layout.setCenter(grid);	
		ToolsBar.getInstance().getLastStacks().push(grid);
	}

	private void newComponent(BorderPane layout) {
		StackPane grid=new StackPane();
		grid.setAlignment(Pos.CENTER);
		
		Label compName=new Label("Component name: ");
		Label compPrice=new Label("Component price: ");
		Label compCal=new Label("Component calories: ");
		Label compCol=new Label("Component colesterol: ");
		Label compNe=new Label("Component Natran: ");
		Label compHasGlut=new Label("Has Gluten? ");
		Label compHasLact=new Label("Has Lactose? ");
		TextField inputName=new TextField();
		TextField inputPrice=new TextField();
		TextField inputCal=new TextField();
		TextField inputCol=new TextField();
		TextField inputNe=new TextField();
		CheckBox inputGlut=new CheckBox();
		CheckBox inputLact=new CheckBox();
		Label status=new Label();
		Button button=new Button("Done");
		button.setOnAction(e->{

				try {
					ToolsBar.getInstance().getOut().println("addComp/"+inputName.getText()+"/"+inputGlut.isSelected()+"/"+inputLact.isSelected()+"/"+inputPrice.getText()+"/"+inputCal.getText()+","+inputCol.getText()+","+inputNe.getText());
					if(!Boolean.parseBoolean(ToolsBar.getInstance().getIn().readLine().split("/")[0])) {
						throw new FaildToAddException();
					}
					inputName.clear();
					inputPrice.clear();
					inputCal.clear();
					inputCol.clear();
					inputNe.clear();
					inputGlut.setSelected(false);
					inputLact.setSelected(false);
					status.setText("added component successfully");
					status.setTextFill(Color.GREEN);
				} catch (FaildToAddException|NumberFormatException|NullPointerException | IOException e1) {
					status.setText(e1.getMessage());
					status.setTextFill(Color.RED);
				}
		});
		
		compName.setStyle("-fx-translate-x:-150;-fx-translate-y:-400;-fx-min-width:250;-fx-max-width:250;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		compPrice.setStyle("-fx-translate-x:-150;-fx-translate-y:-300;-fx-min-width:250;-fx-max-width:250;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		compCal.setStyle("-fx-translate-x:-150;-fx-translate-y:-200;-fx-min-width:250;-fx-max-width:250;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		compCol.setStyle("-fx-translate-x:-150;-fx-translate-y:-100;-fx-min-width:250;-fx-max-width:250;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		compNe.setStyle("-fx-translate-x:-150;-fx-translate-y:0;-fx-min-width:250;-fx-max-width:250;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		compHasGlut.setStyle("-fx-translate-x:-150;-fx-translate-y:100;-fx-min-width:250;-fx-max-width:250;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		compHasLact.setStyle("-fx-translate-x:-150;-fx-translate-y:200;-fx-min-width:250;-fx-max-width:250;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		inputName.setStyle("-fx-translate-x:150;-fx-translate-y:-400;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		inputPrice.setStyle("-fx-translate-x:150;-fx-translate-y:-300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		inputCal.setStyle("-fx-translate-x:150;-fx-translate-y:-200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		inputCol.setStyle("-fx-translate-x:150;-fx-translate-y:-100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		inputNe.setStyle("-fx-translate-x:150;-fx-translate-y:0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		inputGlut.setStyle("-fx-translate-x:150;-fx-translate-y:100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		inputLact.setStyle("-fx-translate-x:150;-fx-translate-y:200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		button.setStyle("-fx-translate-x:450;-fx-translate-y:-75;-fx-min-width:150;-fx-max-width:150;-fx-min-height:120;-fx-max-height:120;-fx-font-size:20;-fx-background-radius:15px");
		status.setStyle("-fx-translate-x:450;-fx-translate-y:75;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");

		grid.getChildren().addAll(compName,compPrice,compCal,compCol,compNe,compHasGlut,compHasLact,inputName,inputPrice,inputCal,inputCol,inputNe,inputGlut,inputLact,button,status);
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(grid);	
	}


	@SuppressWarnings("unchecked")
	public void newCustomer(BorderPane layout) {
		StackPane grid=newPersonGrid();
		Label neighberhood=new Label("Neighberhood: ");
		Label sensetiveToLactose=new Label("Sensetive to Lactose? ");
		Label sensetiveToGluten=new Label("Sensetive to gluten? ");
		Label username=new Label("username: ");
		Label password=new Label("password: ");
		String[] imgPath={"images/manDefault.png"};
		FilteredBox<Neighberhood> inputNeighberhood=new FilteredBox<>(Arrays.asList(Neighberhood.values()));
		CheckBox inputGlut=new CheckBox();
		CheckBox inputLact=new CheckBox();
		TextField inputUsername=new TextField();
		PasswordField inputPassword=new PasswordField();
		
		Button img=new Button();
		
		Label status=new Label();
		
		neighberhood.setStyle("-fx-translate-x:-150;-fx-translate-y:100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		sensetiveToLactose.setStyle("-fx-translate-x:-150;-fx-translate-y:200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		sensetiveToGluten.setStyle("-fx-translate-x:-150;-fx-translate-y:300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		inputNeighberhood.setStyle("-fx-translate-x:150;-fx-translate-y:75;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		inputGlut.setStyle("-fx-translate-x:150;-fx-translate-y:200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		inputLact.setStyle("-fx-translate-x:150;-fx-translate-y:300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		status.setStyle("-fx-translate-x:400;-fx-translate-y:75;-fx-min-width:200;-fx-max-width:150;-fx-min-height:120;-fx-max-height:120;-fx-font-size:20;");
		username.setStyle("-fx-translate-x:-750;-fx-translate-y:0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		password.setStyle("-fx-translate-x:-750;-fx-translate-y:100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		inputUsername.setStyle("-fx-translate-x:-450;-fx-translate-y:0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		inputPassword.setStyle("-fx-translate-x:-450;-fx-translate-y:100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		img.setStyle("-fx-translate-x:450;-fx-translate-y:-450;-fx-min-width:100;-fx-max-width:100;-fx-min-height:150;-fx-max-height:150;-fx-background-radius:15px;-fx-background-image:url(file:"+imgPath[0]+");-fx-background-size: 150px 200px;-fx-background-repeat: no-repeat;-fx-background-position: center;");
		
		((Button) grid.getChildren().get(14)).setOnAction(e->{
			
			try {
				imgPath[0]=imgPath[0].replace("/", "@");
				ToolsBar.getInstance().getOut().println("addCust/"+((TextField)grid.getChildren().get(7)).getText()+"/"+((TextField)grid.getChildren().get(8)).getText()+"/"+((TextField)grid.getChildren().get(9)).getText()+"/"+((DatePicker)grid.getChildren().get(10)).getValue().format(DateTimeFormatter.ISO_LOCAL_DATE)+"/"+((ComboBox<Gender>)grid.getChildren().get(11)).getSelectionModel().getSelectedItem().name()+"/"+inputNeighberhood.getBox().getSelectionModel().getSelectedItem().name()+"/"+String.valueOf(inputLact.isSelected())+"/"+String.valueOf(inputGlut.isSelected())+"/"+((TextField)grid.getChildren().get(12)).getText()+"/"+((TextField)grid.getChildren().get(13)).getText()+"/"+inputUsername.getText()+"/"+inputPassword.getText()+"/"+imgPath[0]);
				if(!Boolean.parseBoolean(ToolsBar.getInstance().getIn().readLine().split("/")[0])) {
					throw new FaildToAddException();
				}
				((TextField)grid.getChildren().get(7)).clear();
				((TextField)grid.getChildren().get(8)).clear();
				((TextField)grid.getChildren().get(9)).clear();
				((ComboBox<Gender>)grid.getChildren().get(11)).getSelectionModel().clearSelection();
				inputNeighberhood.getBox().getSelectionModel().clearSelection();
				inputUsername.clear();
				inputPassword.clear();
				inputGlut.setSelected(false);
				inputLact.setSelected(false);
				((TextField)grid.getChildren().get(12)).clear();
				((TextField)grid.getChildren().get(13)).clear();
				status.setText("Customer added sucessfully");
				status.setTextFill(Color.GREEN);
				
			} catch (IOException | IllegalArgumentException | FaildToAddException e1) {
				status.setText(e1.getMessage());
				status.setTextFill(Color.RED);
			}
		});	
		
		img.setOnAction(e4->{
			
			try {
				FileChooser fileChooser=new FileChooser();
				fileChooser.setTitle("Choose Picture");
				fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
				File selectedFile=fileChooser.showOpenDialog(new Stage());
				BufferedImage bi=ImageIO.read(selectedFile);
				imgPath[0]="images/customer"+((TextField)grid.getChildren().get(7)).getText()+".png";
				ImageIO.write(bi, "png", new File(imgPath[0]));
				img.setStyle("-fx-translate-x:400;-fx-translate-y:-400;-fx-min-width:150;-fx-max-width:150;-fx-min-height:200;-fx-max-height:200;-fx-background-radius:15px;-fx-background-image:url(file:"+imgPath[0]+");-fx-background-size: 150px 200px;-fx-background-repeat: no-repeat;-fx-background-position: center;");
				
			} catch (IOException e1) {	
				status.setText("invalid image path");
				status.setTextFill(Color.RED);
			}
			
		});
		
		((ComboBox<Gender>)grid.getChildren().get(11)).getSelectionModel().selectedItemProperty().addListener(e4->{
			if(((ComboBox<Gender>)grid.getChildren().get(11)).getSelectionModel().getSelectedItem().equals(Gender.Male)) {
				imgPath[0]="images/manDefault.png";
				try {
					File selectedFile=new File("images/manDefault.png");
					BufferedImage bi=ImageIO.read(selectedFile);
					ImageIO.write(bi, "png", new File("images/customer"+((TextField)grid.getChildren().get(7)).getText()+".png"));
					img.setStyle("-fx-translate-x:400;-fx-translate-y:-400;-fx-min-width:150;-fx-max-width:150;-fx-min-height:200;-fx-max-height:200;-fx-background-radius:15px;-fx-background-image:url(file:"+imgPath[0]+");-fx-background-size: 150px 200px;-fx-background-repeat: no-repeat;-fx-background-position: center;");
					
				} catch (IOException e1) {	
					status.setText("invalid image path");
					status.setTextFill(Color.RED);
				}
			}
			else {
				imgPath[0]="images/womanDefault.jpg";
				try {
					File selectedFile=new File(imgPath[0]);
					BufferedImage bi=ImageIO.read(selectedFile);
					ImageIO.write(bi, "png", new File("images/customer"+((TextField)grid.getChildren().get(7)).getText()+".png"));
					img.setStyle("-fx-translate-x:400;-fx-translate-y:-400;-fx-min-width:150;-fx-max-width:150;-fx-min-height:200;-fx-max-height:200;-fx-background-radius:15px;-fx-background-image:url(file:"+imgPath[0]+");-fx-background-size: 150px 200px;-fx-background-repeat: no-repeat;-fx-background-position: center;");
					
				} catch (IOException e1) {	
					status.setText("invalid image path");
					status.setTextFill(Color.RED);
				}
			}
		});
		
		grid.getChildren().addAll(neighberhood,sensetiveToLactose,sensetiveToGluten,inputNeighberhood,inputGlut,inputLact,status,username,password,inputUsername,inputPassword,img);
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(grid);
	}

	//int id,String firstName, String lastName, LocalDate birthDay, Gender gender,	Neighberhood neighberhood,	boolean isSensitiveToLactose, boolean isSensitiveToGluten, String email,String phone

	
	@SuppressWarnings("unchecked")
	private void newDeliveryPerson(BorderPane layout) {
		StackPane grid=newPersonGrid();
		Label vehicle=new Label("Vehicle: ");
		Label area=new Label("Area: ");
		ComboBox<Vehicle> inputVehicle=new ComboBox<>();
		inputVehicle.getItems().addAll(Vehicle.values());
		ComboBox<String> inputArea=new ComboBox<>();
		ToolsBar.getInstance().getOut().println("getAreas");
		try {
			inputArea.getItems().addAll(Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(",")));
		} catch (IOException e2) {	}
		Label status=new Label();
		
		vehicle.setStyle("-fx-translate-x:-150;-fx-translate-y:100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		area.setStyle("-fx-translate-x:-150;-fx-translate-y:200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		inputVehicle.setStyle("-fx-translate-x:150;-fx-translate-y:100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		inputArea.setStyle("-fx-translate-x:150;-fx-translate-y:200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		status.setStyle("-fx-translate-x:400;-fx-translate-y:75;-fx-min-width:200;-fx-max-width:150;-fx-min-height:120;-fx-max-height:120;-fx-font-size:20;");
		
		((Button) grid.getChildren().get(14)).setOnAction(e->{
			
			try {
				ToolsBar.getInstance().getOut().println("addDp/"+((TextField)grid.getChildren().get(7)).getText()+"/"+((TextField)grid.getChildren().get(8)).getText()+"/"+((TextField)grid.getChildren().get(9)).getText()+"/"+((DatePicker)grid.getChildren().get(10)).getValue().format(DateTimeFormatter.ISO_LOCAL_DATE)+"/"+((ComboBox<Gender>)grid.getChildren().get(11)).getSelectionModel().getSelectedItem().name()+"/"+inputVehicle.getSelectionModel().getSelectedItem().name()+"/"+inputArea.getSelectionModel().getSelectedItem().split(" ")[0]+"/"+((TextField)grid.getChildren().get(12)).getText()+"/"+((TextField)grid.getChildren().get(13)).getText());
				if(!Boolean.parseBoolean(ToolsBar.getInstance().getIn().readLine().split("/")[0])) {
					throw new FaildToAddException();
				}
				((TextField)grid.getChildren().get(7)).clear();
				((TextField)grid.getChildren().get(8)).clear();
				((TextField)grid.getChildren().get(9)).clear();
				((ComboBox<Gender>)grid.getChildren().get(11)).getSelectionModel().clearSelection();
				inputVehicle.getSelectionModel().clearSelection();
				inputArea.getSelectionModel().clearSelection();
				((TextField)grid.getChildren().get(12)).clear();
				status.setText("Delivery person added successfully");
				status.setTextFill(Color.GREEN);
				
				
			} catch (IllegalArgumentException | IOException | FaildToAddException e1) {
				// TODO Auto-generated catch block
				status.setText(e1.getMessage());
				status.setTextFill(Color.RED);
			}
		});;	
		

		grid.getChildren().addAll(vehicle,area,inputVehicle,inputArea,status);
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(grid);
	}
	
	//int id,String firstName, String lastName, LocalDate birthDay, Gender gender, Vehicle vehicle, DeliveryArea area, String email, String phone
	
	private StackPane newPersonGrid() {
		StackPane grid=new StackPane();
		grid.setAlignment(Pos.CENTER);
		
		Label id=new Label("ID: ");
		Label firstName=new Label("First name: ");
		Label lastName=new Label("Last name: ");
		Label birthDay=new Label("Birth day: ");
		Label gender=new Label("gender: ");
		Label email=new Label("email: ");
		Label phone=new Label("phone: ");
		TextField inputId=new TextField();
		TextField inputfirstName=new TextField();
		TextField inputlastName=new TextField();
		DatePicker inputbirthDay=new DatePicker();
		ComboBox<Gender> inputgender=new ComboBox<Gender>();
		inputgender.getItems().addAll(Gender.values());
		TextField inputemail=new TextField();
		TextField inputphone=new TextField();		
		Button button=new Button("Done");
		
		id.setStyle("-fx-translate-x:-750;-fx-translate-y:-300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		firstName.setStyle("-fx-translate-x:-750;-fx-translate-y:-200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		lastName.setStyle("-fx-translate-x:-750;-fx-translate-y:-100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		birthDay.setStyle("-fx-translate-x:-150;-fx-translate-y:-300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		gender.setStyle("-fx-translate-x:-150;-fx-translate-y:-200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		email.setStyle("-fx-translate-x:-150;-fx-translate-y:-100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		phone.setStyle("-fx-translate-x:-150;-fx-translate-y:0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		
		inputId.setStyle("-fx-translate-x:-450;-fx-translate-y:-300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		inputfirstName.setStyle("-fx-translate-x:-450;-fx-translate-y:-200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		inputlastName.setStyle("-fx-translate-x:-450;-fx-translate-y:-100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		inputbirthDay.setStyle("-fx-translate-x:150;-fx-translate-y:-300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		inputgender.setStyle("-fx-translate-x:150;-fx-translate-y:-200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		inputemail.setStyle("-fx-translate-x:150;-fx-translate-y:-100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		inputphone.setStyle("-fx-translate-x:150;-fx-translate-y:0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		
		button.setStyle("-fx-translate-x:400;-fx-translate-y:-75;-fx-min-width:150;-fx-max-width:150;-fx-min-height:120;-fx-max-height:120;-fx-font-size:20;-fx-background-radius:15px");
		
		
		
		grid.getChildren().addAll(id,firstName,lastName,birthDay,gender,email,phone,inputId,inputfirstName,inputlastName,inputbirthDay,inputgender,inputemail,inputphone,button);
		return grid;
	}


	@SuppressWarnings("unchecked")
	private void newCook(BorderPane layout) {
		StackPane grid=newPersonGrid();
		Label expertise=new Label("Expertise: ");
		Label isShef=new Label("Is shef? ");
		ComboBox<Expertise> inputExpertise=new ComboBox<>();
		inputExpertise.getItems().addAll(Expertise.values());
		CheckBox inputShef=new CheckBox();
		Label status=new Label();
		
		expertise.setStyle("-fx-translate-x:-150;-fx-translate-y:100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		isShef.setStyle("-fx-translate-x:-150;-fx-translate-y:200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		inputExpertise.setStyle("-fx-translate-x:150;-fx-translate-y:100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		inputShef.setStyle("-fx-translate-x:150;-fx-translate-y:200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
		status.setStyle("-fx-translate-x:400;-fx-translate-y:75;-fx-min-width:200;-fx-max-width:150;-fx-min-height:120;-fx-max-height:120;-fx-font-size:20;");
		
		((Button) grid.getChildren().get(14)).setOnAction(e->{
			
			try {
				ToolsBar.getInstance().getOut().println("addCook/"+((TextField)grid.getChildren().get(7)).getText()+"/"+((TextField)grid.getChildren().get(8)).getText()+"/"+((TextField)grid.getChildren().get(9)).getText()+"/"+((DatePicker)grid.getChildren().get(10)).getValue().format(DateTimeFormatter.ISO_LOCAL_DATE)+"/"+((ComboBox<Gender>)grid.getChildren().get(11)).getSelectionModel().getSelectedItem().name()+"/"+inputExpertise.getSelectionModel().getSelectedItem().name()+"/"+String.valueOf(inputShef.isSelected())+"/"+((TextField)grid.getChildren().get(12)).getText()+"/"+((TextField)grid.getChildren().get(13)).getText());
				if(!Boolean.parseBoolean(ToolsBar.getInstance().getIn().readLine().split("/")[0])) {
					throw new FaildToAddException();
				}
				((TextField)grid.getChildren().get(7)).clear();
				((TextField)grid.getChildren().get(8)).clear();
				((TextField)grid.getChildren().get(9)).clear();
				((ComboBox<Gender>)grid.getChildren().get(11)).getSelectionModel().clearSelection();
				inputExpertise.getSelectionModel().clearSelection();
				inputShef.setSelected(false);
				((TextField)grid.getChildren().get(12)).clear();
				((TextField)grid.getChildren().get(13)).clear();
				status.setText("Cook added successfully");
				status.setTextFill(Color.GREEN);
				
				
			} catch ( IOException |IllegalArgumentException | FaildToAddException e1) {
				// TODO Auto-generated catch block
				status.setText(e1.getMessage());
				status.setTextFill(Color.RED);

			}
		});;	

		
		grid.getChildren().addAll(expertise,isShef,inputExpertise,inputShef,status);
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(grid);
		
	}

	//int id, String firstName, String lastName, LocalDate birthDay, Gender gender, Expertise expert, boolean isChef, String email, String phone

	
	

}




