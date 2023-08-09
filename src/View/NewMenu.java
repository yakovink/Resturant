package View;



/*this is the new menu form. all new methods are methods of this class.
 * the new function action is simple user fill the fields with valid parameters and click add.
 * the after clicking add, the client will sent the parameters to the dealWith thread,
 * and the dealWith will constract the new object and add it to restaurant database..
 * before sending the parameters, the program trigered all fields to throw exceptions if they are unlegel.
 * if exception throwed, the add commant wond sent and the user pop-up window with exception message.**/

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import CRM.*;
import Utils.*;


import application.App;
import javafx.geometry.Pos;
import javafx.scene.control.*;

import javafx.scene.layout.StackPane;
import Exceptions.*;

import View.Nodes.*;


public class NewMenu extends StackPane{

	private Button addCook;
	private Button addDeliveryPerson;
	private Button addCustomer;
	private Button addComponent;
	private Button addDish;
	private Button addOrder;
	private Button addDelivery;
	private Button addArea;
	
	private double w;
	private double h;
	private double s;
	


	public NewMenu() {
		ToolsBar.getInstance().getPlace().pushText("Add menu");
		w=App.getW();
		h=App.getH();
		s=App.getS();
		
		addCook=new Button("New Cook...");
		addDeliveryPerson=new Button("New DeliveryPerson...");
		addCustomer=new Button("New Customer...");
		addComponent=new Button("New Component...");
		addDish=new Button("New Dish...");
		addOrder=new Button("New Order...");
		addDelivery=new Button("New Delivery...");
		addArea=new Button("New Area...");

		addCook.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px",-200*w,-100*h,300*w,300*w,100*h,100*h,25*s));
		addDeliveryPerson.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px",-200*w,50*h,300*w,300*w,100*h,100*h,20*s));
		addCustomer.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px",-200*w,200*h,300*w,300*w,100*h,100*h,25*s));
		addComponent.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px",-200*w,-250*h,300*w,300*w,100*h,100*h,25*s));
		addDish.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px",200*w,-100*h,300*w,300*w,100*h,100*h,25*s));
		addOrder.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px",200*w,50*h,300*w,300*w,100*h,100*h,25*s));
		addDelivery.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px",200*w,200*h,300*w,300*w,100*h,100*h,25*s));
		addArea.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px",200*w,-250*h,300*w,300*w,100*h,100*h,25*s));
		
		this.addCook.setOnAction(e->newCook());
		this.addDeliveryPerson.setOnAction(e->newDeliveryPerson());
		this.addCustomer.setOnAction(e->newCustomer());
		this.addComponent.setOnAction(e->newComponent());
		this.addDish.setOnAction(e->newDish());
		this.addOrder.setOnAction(e->newOrder());
		this.addDelivery.setOnAction(e->newDelivery());
		this.addArea.setOnAction(e->newArea());
	
		
		this.getChildren().addAll(addCook,addDeliveryPerson,addCustomer,addComponent,addDish,addOrder,addDelivery,addArea);
		ToolsBar.getInstance().getLastStacks().push(App.getLayout().getCenter());
		App.getLayout().setCenter(this);
		
	}





	private void newArea() {
		ToolsBar.getInstance().getPlace().pushText("Add area");
		StackPane grid=new StackPane();
		Label areaName=new Label("Area name:     ");
		Label deliveryTime=new Label("Delivery time: ");
		IndTextField inputName=new IndTextField();
		IntegerField inputTime=new IntegerField();
		Button button=new Button("Done");

		FilteredList<Neighberhood> neighbehoods=new FilteredList<>(Arrays.asList(Neighberhood.values()));
		neighbehoods.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		button.setOnAction(e->{
			String[] answer= {"false","internal error!"};
			try {
				if(neighbehoods.getList().getSelectionModel().isEmpty()) {
					throw new BlankFieldException();
				}
				inputName.isLegal();
				inputTime.isLegal();

				String nebs="";
				for(Neighberhood n:neighbehoods.getList().getSelectionModel().getSelectedItems()){
					nebs+=","+n.name();
				}
				nebs=nebs.substring(1);
				ToolsBar.getInstance().getOut().println("addArea/"+inputName.getText()+"/"+nebs+"/"+inputTime.getText());
				
			
				answer = ToolsBar.getInstance().getIn().checkedReadLine().split("/");
				if(!Boolean.parseBoolean(answer[0])) {
					throw new FaildToAddException(answer[1]);
				}
				inputName.clear();
				inputTime.clear();
				neighbehoods.getList().getSelectionModel().clearSelection();
				PopUp.display("Area added successfully!", true);
			}  catch (BlankFieldException|IllegalCharacterException|LieException | EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException | FaildToAddException | NotNumberException e1) {
				PopUp.display(e1.getMessage(), false);
			}
			
		});
		
		areaName.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",100*w,-200*h,150*w,150*w,40*h,40*h,20*s));
		deliveryTime.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",100*w,-150*h,150*w,150*w,40*h,40*h,20*s));
		inputName.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;fx-background-radius:15px;",300*w,-200*h,150*w,150*w,40*h,40*h,20*s));
		inputTime.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;fx-background-radius:15px;",300*w,-150*h,150*w,150*w,40*h,40*h,20*s));
		neighbehoods.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;fx-background-radius:15px;",-175*w,-100*h,150*w,150*w,300*h,300*h,14*s));
		button.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;fx-background-radius:15px;",500*w,-300*h,150*w,150*w,120*h,120*h,20*s));		

		grid.getChildren().addAll(areaName,deliveryTime,inputName,inputTime,neighbehoods,button);
		ToolsBar.getInstance().getLastStacks().push(App.getLayout().getCenter());
		App.getLayout().setCenter(grid);	
	}


	private void newDelivery() {
		ToolsBar.getInstance().getPlace().pushText("Add delivery");

		ComboBox<String> type=new ComboBox<>();
		type.getItems().addAll("Express Delivery","Regular Delivery");
		
		//superClass Delivery Items
		Label dp=new Label("Delivery person: ");
		Label area=new Label("Delivery area: ");
		Label date=new Label("Delivery Date: ");
		ToolsBar.getInstance().getOut().println("getDeps");
		Collection<String> ourdps;
		try {
			ourdps=Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
		} catch (EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e3) {
			PopUp.display("there is no delivery persons!", false);
			ourdps=new HashSet<>();
		}
		FilteredBox<String> inputDp=new FilteredBox<>(ourdps);
		ToolsBar.getInstance().getOut().println("getDels");
		IndComboBox<String> inputArea=new IndComboBox<>();
		try {
			inputArea.getItems().addAll(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
		} catch (EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e3) {	}
		DatePicker inputDate=new DatePicker();
		Button button = new Button("Done");

		inputDate.valueProperty().addListener(e->{
			try {
			if(inputDate.getValue().isBefore(LocalDate.now())||inputDate.getValue()==null) {
				throw new ToLateException();
			}
			App.setBorderColor(inputDate, "GREEN");
			}
			catch(ToLateException e1) {
				App.setBorderColor(inputDate, "RED");
			}
		});
		
		type.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,0*h,300*w,300*w,50*h,50*h,20*s));
		dp.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,-300*h,250*w,250*w,50*h,50*h,20*s));
		area.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,-200*h,250*w,250*w,50*h,50*h,20*s));
		date.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,-100*h,250*w,250*w,50*h,50*h,20*s));
		inputDp.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,-325*h,250*w,250*w,50*h,50*h,20*s));
		inputArea.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,-200*h,250*w,250*w,50*h,50*h,20*s));
		inputDate.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,-100*h,250*w,250*w,50*h,50*h,20*s));
		
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
					ourorders = Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
				} catch (EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e3) {
					ourorders = new HashSet<>();
				}
				FilteredBox<String> inputOrder=new FilteredBox<>(ourorders);
				IndNumField inputPostage=new IndNumField();
				
				
				order.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,0*h,250*w,250*w,50*h,50*h,20*s));
				postage.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,100*h,250*w,250*w,50*h,50*h,20*s));		
				inputOrder.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,0*h,250*w,250*w,50*h,50*h,20*s));
				inputPostage.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,100*h,250*w,250*w,50*h,50*h,20*s));
				button.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",450*w,-75*h,150*w,150*w,120*h,120*h,20*s));
				type.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,-400*h,300*w,300*w,50*h,50*h,20*s));
				
				button.setOnAction(e2->{
					String[] answer= {"false","internal error!"};
					try {
						if(inputDate.getValue().isBefore(LocalDate.now())||inputDate.getValue()==null) {
							throw new ToLateException();
						}
						inputPostage.isLegal();
						inputDp.isLegal();
						inputArea.isLegal();
						inputOrder.isLegal();
						
						ToolsBar.getInstance().getOut().println("addExpDel/"+inputDp.getBox().getSelectionModel().getSelectedItem().split(" ")[0]+"/"+inputArea.getSelectionModel().getSelectedItem().split(" ")[0]+"/"+inputPostage.getText()+"/"+inputDate.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
						answer=ToolsBar.getInstance().getIn().checkedReadLine().split("/");
						if(!Boolean.parseBoolean(answer[0])){
							throw new FaildToAddException(answer[1]);
						}
						inputDp.getBox().getSelectionModel().clearSelection();
						inputArea.getSelectionModel().clearSelection();
						inputOrder.getBox().getSelectionModel().clearSelection();
						inputDate.setValue(null);
						PopUp.display("Delivery added successfully!", true);
					} catch (BlankFieldException |IllegalCharacterException |LieException |ToLateException | EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException | FaildToAddException | NotNumberException e1) {
						PopUp.display(e1.getMessage(), false);
					} 
					 
				});

				express.getChildren().addAll(type,dp,area,date,inputDp,inputArea,inputDate,button,order,postage,inputOrder,inputPostage);
				ToolsBar.getInstance().getLastStacks().push(App.getLayout().getCenter());
				App.getLayout().setCenter(express);
			}
			
			else {
				StackPane regular=new StackPane();
				regular.setAlignment(Pos.CENTER);
				ToolsBar.getInstance().getOut().println("getOrds");
				Collection<String> ourorders;
				try {
					ourorders = Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
				} catch (EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e3) {
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
					String[] answer= {"false","internal error!"};
					try {
						if(inputDate.getValue().isBefore(LocalDate.now())||inputDate.getValue()==null) {
							throw new ToLateException();
						}
						if(inputOrders.getItems().isEmpty()){
							throw new BlankFieldException();
						}
						inputDp.isLegal();
						inputArea.isLegal();
						
						String ords="";
						for(String s:inputOrders.getItems()) {
							ords+=","+s.split(" ")[0];
						}
						ords=ords.substring(1);
						ToolsBar.getInstance().getOut().println("addRegDel/"+ords+"/"+inputDp.getBox().getSelectionModel().getSelectedItem().split(" ")[0]+"/"+inputArea.getSelectionModel().getSelectedItem().split(" ")[0]+"/false/"+inputDate.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
						answer=ToolsBar.getInstance().getIn().checkedReadLine().split("/");
						if(!Boolean.parseBoolean(answer[0])) {
							throw new FaildToAddException(answer[1]);
						}
						inputDp.getBox().getSelectionModel().clearSelection();
						inputArea.getSelectionModel().clearSelection();
						inputOrders.getItems().clear();
						orders.getList().getSelectionModel().clearSelection();
						PopUp.display("Delivery added successfully!", true);
					}  catch (BlankFieldException | ToLateException | EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException | FaildToAddException e1) {
						PopUp.display(e1.getMessage(), false);
					}
					
			});
				
				orders.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-300*w,200*h,300*w,300*w,400*h,400*h,20*s));
				inputOrders.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",300*w,165*h,300*w,300*w,350*h,350*h,20*s));		
				addOrders.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,0*h,250*w,250*w,50*h,50*h,20*s));
				removeOrders.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,100*h,250*w,250*w,50*h,50*h,20*s));
				button.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",600*w,-75*h,150*w,150*w,120*h,120*h,20*s));
				type.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,-400*h,300*w,300*w,50*h,50*h,20*s));
				regular.getChildren().addAll(type,dp,area,date,inputDp,inputArea,inputDate,button,orders,inputOrders,addOrders,removeOrders);
				ToolsBar.getInstance().getLastStacks().push(App.getLayout().getCenter());
				App.getLayout().setCenter(regular);
				
			}
		});
		ToolsBar.getInstance().getLastStacks().push(App.getLayout().getCenter());
		App.getLayout().setCenter(type);
	}
	
	//TreeSet<Order> orders, DeliveryPerson deliveryPerson, DeliveryArea area,	boolean isDelivered,LocalDate deliveredDate
	//DeliveryPerson deliveryPerson, DeliveryArea area, boolean isDelivered , Order order , double postage, LocalDate deliveredDate
	//DeliveryPerson deliveryPerson, DeliveryArea area, boolean isDelivered,LocalDate diliveredDate
	

	private void newOrder() {
		ToolsBar.getInstance().getPlace().pushText("Add order");
		StackPane grid=new StackPane();
		grid.setAlignment(Pos.CENTER);
		
		Label customerId=new Label("customer ID: ");
		PersonIdField inputCustomerId=new PersonIdField();
		
		ListView<String> inputDishes=new ListView<>();
		Button addDishes=new Button("Add dish");
		Button removeDishes=new Button("Remove dish");
		Button button=new Button("Done");
		ToolsBar.getInstance().getOut().println("getDishes");
		Collection<String> ourdishes;
		try {
			ourdishes =Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
		} catch (EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e2) {
			ourdishes = new HashSet<>();
		}
		FilteredList<String> dishes=new FilteredList<>(ourdishes);
		dishes.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		Label priceLabel=new Label();
		Label TimeToReady=new Label();
		
		inputDishes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		
		
		customerId.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-100*w,-300*h,150*w,150*w,40*h,40*h,20*s));
		inputCustomerId.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",100*w,-300*h,150*w,150*w,40*h,40*h,20*s));
		dishes.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-250*w,0*h,250*w,250*w,500*h,500*h,20*s));
		inputDishes.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",250*w,-35*h,250*w,250*w,430*h,430*h,20*s));
		addDishes.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,-75*h,175*w,175*w,75*h,75*h,20*s));
		removeDishes.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,75*h,175*w,175*w,75*h,75*h,20*s));
		priceLabel.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-300*w,300*h,300*w,300*w,100*h,100*h,20*s));
		TimeToReady.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-300*w,350*h,300*w,300*w,100*h,100*h,20*s));
		button.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",500*w,-50*h,150*w,150*w,120*h,120*h,20*s));
		
		addDishes.setOnAction(e->{
			inputDishes.getItems().addAll(dishes.getList().getSelectionModel().getSelectedItems());
			dishes.getList().getSelectionModel().clearSelection();
			double price=0;
			int time=0;
			for(String d:inputDishes.getItems()) {
				price+=Double.parseDouble(d.split(" ")[3]);
				time+=Integer.parseInt(d.split(" ")[4]);
			}
			if(!inputCustomerId.getText().isBlank()) {
				ToolsBar.getInstance().getOut().println("getCustArea/"+inputCustomerId.getText());
				try {
					time+=Integer.parseInt(ToolsBar.getInstance().getIn().checkedReadLine().split(" ")[3]);
				} catch (NumberFormatException | EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e1) {
					PopUp.display(e1.getMessage(), false);
				}
			}
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
				time+=Integer.parseInt(ToolsBar.getInstance().getIn().checkedReadLine().split(" ")[3]);
			} 
			catch (NumberFormatException | EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e1) {PopUp.display(e1.getMessage(), false);	}
			priceLabel.setText("for payment: "+price);
			TimeToReady.setText("Time to ready: "+time);
		});
		button.setOnAction(e->{
			String[] answer= {"false","internal error!"};
			try {
				if(inputDishes.getSelectionModel().isEmpty()) {
					throw new BlankFieldException();
				}
				inputCustomerId.isLegalID();

				String s="";
				for(String s2:inputDishes.getItems()) {
					s+=","+s2.split(" ")[0];
				}
				s=s.substring(1);
				ToolsBar.getInstance().getOut().println("addOrd/"+inputCustomerId.getText()+"/"+s);
				answer=ToolsBar.getInstance().getIn().checkedReadLine().split("/");
				if(!Boolean.parseBoolean(answer[0])) {
					throw new FaildToAddException(answer[1]);
				}
				inputCustomerId.clear();
				dishes.getList().getSelectionModel().clearSelection();
				inputDishes.getItems().clear();
				PopUp.display("Order added successfully!", true);
			}
			catch (BlankFieldException |IllegalCharacterException| LieException|InvalidIDException | EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException | FaildToAddException | NumberFormatException | NotNumberException e1) {
				PopUp.display(e1.getMessage(), false);
			} 
			
		});

		grid.getChildren().addAll(priceLabel,TimeToReady,customerId,inputCustomerId,addDishes,removeDishes,dishes,inputDishes,button);
		ToolsBar.getInstance().getLastStacks().push(App.getLayout().getCenter());
		App.getLayout().setCenter(grid);
		
	}

	//Customer customer, ArrayList<Dish> dishes, Delivery delivery

	private void newDish() {
		ToolsBar.getInstance().getPlace().pushText("Add dish");
		StackPane grid=new StackPane();
		grid.setAlignment(Pos.CENTER);
		
		Label dishName=new Label("Dish name: ");
		Label dishTime=new Label("Time to make: ");
		Label dishType=new Label("Dish type: ");
		IndTextField inputName=new IndTextField();
		IntegerField inputTime=new IntegerField();
		IndComboBox<DishType> inputType=new IndComboBox<>();
		ListView<String> inputComps=new ListView<>();
		ToolsBar.getInstance().getOut().println("getComps");
		Collection<String> ourcomps;
		try {
			ourcomps=Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
		} catch (EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e1) {
			ourcomps=new HashSet<>();
		}
		FilteredList<String> dishComps=new FilteredList<>(ourcomps);
		Button button=new Button("Done");
		Button addComp=new Button("add componenets");
		Button removeComp=new Button("remove componenets");


		inputComps.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		dishComps.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		inputType.getItems().addAll(DishType.values());
		button.setOnAction(e->{
			String[] answer= {"false","internal error!"};
			try {
				if(inputComps.getItems().isEmpty()) {
					throw new BlankFieldException();
				}
				inputName.isLegal();
				inputTime.isLegal();
				inputType.isLegal();
				
				
				String str="";
				for(String s:inputComps.getItems()) {
					str+=","+s.split(" ")[0];
				}
				str=str.substring(1);
				ToolsBar.getInstance().getOut().println("addDish/"+inputName.getText()+"/"+inputType.getSelectionModel().getSelectedItem().name()+"/"+str+"/"+inputTime.getText()+"/true");
				answer=ToolsBar.getInstance().getIn().checkedReadLine().split("/");
				if(!Boolean.parseBoolean(answer[0])) {
					throw new FaildToAddException(answer[1]);
				}
				inputName.clear();
				inputTime.clear();
				inputType.getSelectionModel().clearSelection();
				inputComps.getItems().clear();
				PopUp.display("Dish added successfully!", true);
			}
			catch (BlankFieldException | IllegalCharacterException | LieException | EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException | FaildToAddException | NotNumberException e1) {
				PopUp.display(e1.getMessage(), false);
			}
			
		});
		addComp.setOnAction(e->{
			inputComps.getItems().addAll(dishComps.getList().getSelectionModel().getSelectedItems());
			dishComps.getList().getSelectionModel().clearSelection();
		});
		removeComp.setOnAction(e->{
			inputComps.getItems().removeAll(inputComps.getSelectionModel().getSelectedItems());
		});

		dishName.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-100*w,-400*h,200*w,200*w,50*h,50*h,20*s));
		dishTime.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-100*w,-300*h,200*w,200*w,50*h,50*h,20*s));
		dishType.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-100*w,-200*h,200*w,200*w,50*h,50*h,20*s));
		inputName.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",100*w,-400*h,200*w,200*w,50*h,50*h,20*s));
		inputTime.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",100*w,-300*h,200*w,200*w,50*h,50*h,20*s));
		inputType.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",100*w,-200*h,200*w,200*w,50*h,50*h,20*s));
		dishComps.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-300*w,100*h,250*w,250*w,500*h,500*h,20*s));
		inputComps.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",300*w,65*h,250*w,250*w,430*h,430*h,20*s));
		addComp.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,50*h,250*w,250*w,75*h,75*h,20*s));
		removeComp.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,150*h,250*w,250*w,75*h,75*h,20*s));
		button.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",600*w,-50*h,150*w,150*w,120*h,120*h,20*s));
		

		grid.getChildren().addAll(dishName,dishTime,dishType,inputName,inputTime,inputType,inputComps,dishComps,button,addComp,removeComp);
		ToolsBar.getInstance().getLastStacks().push(App.getLayout().getCenter());
		App.getLayout().setCenter(grid);	
	}

	private void newComponent() {
		ToolsBar.getInstance().getPlace().pushText("Add componenet");
		StackPane grid=new StackPane();
		grid.setAlignment(Pos.CENTER);
		
		Label compName=new Label("Component name: ");
		Label compPrice=new Label("Component price: ");
		Label compCal=new Label("Component calories: ");
		Label compCol=new Label("Component colesterol: ");
		Label compNe=new Label("Component Natran: ");
		Label compHasGlut=new Label("Has Gluten? ");
		Label compHasLact=new Label("Has Lactose? ");
		IndTextField inputName=new IndTextField();
		IndNumField inputPrice=new IndNumField();
		IndNumField inputCal=new IndNumField();
		IndNumField inputCol=new IndNumField();
		IndNumField inputNe=new IndNumField();
		CheckBox inputGlut=new CheckBox();
		CheckBox inputLact=new CheckBox();
		Button button=new Button("Done");
		button.setOnAction(e->{
				String[] answer= {"internal error","false"};
				try {
					inputName.isLegal();
					inputPrice.isLegal();
					inputCal.isLegal();
					inputCol.isLegal();
					inputNe.isLegal();
					
					
					ToolsBar.getInstance().getOut().println("addComp/"+inputName.getText()+"/"+inputGlut.isSelected()+"/"+inputLact.isSelected()+"/"+inputPrice.getText()+"/"+inputCal.getText()+","+inputCol.getText()+","+inputNe.getText());
					answer=ToolsBar.getInstance().getIn().checkedReadLine().split("/");
					if(!Boolean.parseBoolean(answer[0])) {
						throw new FaildToAddException(answer[1]);
					}
					inputName.clear();
					inputPrice.clear();
					inputCal.clear();
					inputCol.clear();
					inputNe.clear();
					inputGlut.setSelected(false);
					inputLact.setSelected(false);
					PopUp.display("Component added successfully!", true);
				}   catch (BlankFieldException |IllegalCharacterException |LieException | EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException | FaildToAddException | NotNumberException e1) {
					PopUp.display(e1.getMessage(), false);
				}
				
		});
		
		compName.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,-400*h,250*w,250*w,50*h,50*h,20*s));
		compPrice.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,-300*h,250*w,250*w,50*h,50*h,20*s));
		compCal.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,-200*h,250*w,250*w,50*h,50*h,20*s));
		compCol.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,-100*h,250*w,250*w,50*h,50*h,20*s));
		compNe.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,0*h,250*w,250*w,50*h,50*h,20*s));
		compHasGlut.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,100*h,250*w,250*w,50*h,50*h,20*s));
		compHasLact.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,200*h,250*w,250*w,50*h,50*h,20*s));
		inputName.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,-400*h,250*w,250*w,50*h,50*h,20*s));
		inputPrice.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,-300*h,250*w,250*w,50*h,50*h,20*s));
		inputCal.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,-200*h,250*w,250*w,50*h,50*h,20*s));
		inputCol.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,-100*h,250*w,250*w,50*h,50*h,20*s));
		inputNe.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,0*h,250*w,250*w,50*h,50*h,20*s));
		inputGlut.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,100*h,250*w,250*w,50*h,50*h,20*s));
		inputLact.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,200*h,250*w,250*w,50*h,50*h,20*s));
		button.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",450*w,-75*h,150*w,150*w,120*h,120*h,20*s));

		grid.getChildren().addAll(compName,compPrice,compCal,compCol,compNe,compHasGlut,compHasLact,inputName,inputPrice,inputCal,inputCol,inputNe,inputGlut,inputLact,button);
		ToolsBar.getInstance().getLastStacks().push(App.getLayout().getCenter());
		App.getLayout().setCenter(grid);	
	}



	@SuppressWarnings("unchecked")
	public void newCustomer() {
		
		
		
		ToolsBar.getInstance().getPlace().pushText("Add customer");
		
		StackPane grid=newPersonGrid();
		Label neighberhood=new Label("Neighberhood: ");
		Label sensetiveToLactose=new Label("Sensetive to Lactose? ");
		Label sensetiveToGluten=new Label("Sensetive to gluten? ");
		Label username=new Label("username: ");
		Label password=new Label("password: ");
		FilteredBox<Neighberhood> inputNeighberhood=new FilteredBox<>(Arrays.asList(Neighberhood.values()));
		CheckBox inputGlut=new CheckBox();
		CheckBox inputLact=new CheckBox();
		IndTextField inputUsername=new IndTextField();
		IndPasswordField inputPassword=new IndPasswordField();
		

		
		neighberhood.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,100*h,200*w,200*w,50*h,50*h,20*s));
		sensetiveToLactose.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,200*h,250*w,250*w,50*h,50*h,20*s));
		sensetiveToGluten.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,300*h,250*w,250*w,50*h,50*h,20*s));
		inputNeighberhood.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,75*h,200*w,200*w,50*h,50*h,20*s));
		inputGlut.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,200*h,200*w,200*w,50*h,50*h,20*s));
		inputLact.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,300*h,200*w,200*w,50*h,50*h,20*s));
		username.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-750*w,0*h,200*w,200*w,50*h,50*h,20*s));
		password.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-750*w,100*h,200*w,200*w,50*h,50*h,20*s));
		inputUsername.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-450*w,0*h,200*w,200*w,50*h,50*h,20*s));
		inputPassword.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-450*w,100*h,200*w,200*w,50*h,50*h,20*s));
	
		((Button) grid.getChildren().get(14)).setOnAction(e->{
			String[] answer= {"false","internal error!"};
			try {
				
				((PersonIdField)grid.getChildren().get(7)).isLegalID();
				((IndTextField)grid.getChildren().get(8)).isLegal();
				((IndTextField)grid.getChildren().get(9)).isLegal();
				((EmailField)grid.getChildren().get(12)).isLegalMail();
				((PhoneField)grid.getChildren().get(13)).isLegalPhone();
				((IndDatePicker)grid.getChildren().get(10)).isLegal();
				((IndComboBox<Gender>)grid.getChildren().get(11)).isLegal();
				inputUsername.isLegal();
				inputPassword.isLegal();
				inputNeighberhood.isLegal();
				
				ToolsBar.getInstance().getOut().println("addCust/"+((TextField)grid.getChildren().get(7)).getText()+"/"+((TextField)grid.getChildren().get(8)).getText()+"/"+((TextField)grid.getChildren().get(9)).getText()+"/"+((DatePicker)grid.getChildren().get(10)).getValue().format(DateTimeFormatter.ISO_LOCAL_DATE)+"/"+((ComboBox<Gender>)grid.getChildren().get(11)).getSelectionModel().getSelectedItem().name()+"/"+inputNeighberhood.getBox().getSelectionModel().getSelectedItem().name()+"/"+String.valueOf(inputLact.isSelected())+"/"+String.valueOf(inputGlut.isSelected())+"/"+((TextField)grid.getChildren().get(12)).getText()+"/"+((TextField)grid.getChildren().get(13)).getText()+"/"+inputUsername.getText()+"/"+inputPassword.getText());
				answer=ToolsBar.getInstance().getIn().checkedReadLine().split("/");
				if(!Boolean.parseBoolean(answer[0])) {
					throw new FaildToAddException(answer[1]);
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
				((IndDatePicker)grid.getChildren().get(10)).setValue(null);
				
				PopUp.display("Account added successfully!", true);
				
			} 
			catch (BlankFieldException |InvalidPhoneException |  IllegalCharacterException | LieException | ToYoungException | InvalidEmailException|LowPasswordException| InvalidIDException | EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException | FaildToAddException | ToLateException | NumberFormatException | NotNumberException  e1) {
				PopUp.display(e1.getMessage(), false);
			} 
			
			
			
		});	
		
		grid.getChildren().addAll(neighberhood,sensetiveToLactose,sensetiveToGluten,inputNeighberhood,inputGlut,inputLact,username,password,inputUsername,inputPassword);
		ToolsBar.getInstance().getLastStacks().push(App.getLayout().getCenter());
		App.getLayout().setCenter(grid);
	}

	//int id,String firstName, String lastName, LocalDate birthDay, Gender gender,	Neighberhood neighberhood,	boolean isSensitiveToLactose, boolean isSensitiveToGluten, String email,String phone

	
	@SuppressWarnings("unchecked")
	private void newDeliveryPerson() {
		ToolsBar.getInstance().getPlace().pushText("Add delivery person");
		StackPane grid=newPersonGrid();
		Label vehicle=new Label("Vehicle: ");
		Label area=new Label("Area: ");
		IndComboBox<Vehicle> inputVehicle=new IndComboBox<>();
		inputVehicle.getItems().addAll(Vehicle.values());
		FilteredBox<String> inputArea=new FilteredBox<>(new HashSet<>());
		ToolsBar.getInstance().getOut().println("getAreas");
		try {
			inputArea.addAll(Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(",")));
		} catch (EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e2) {PopUp.display(e2.getMessage(), false);	}
		
		vehicle.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,100*h,200*w,200*w,50*h,50*h,20*s));
		area.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,200*h,200*w,200*w,50*h,50*h,20*s));
		inputVehicle.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,100*h,200*w,200*w,50*h,50*h,20*s));
		inputArea.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,200*h,200*w,200*w,50*h,50*h,20*s));
		
		((Button) grid.getChildren().get(14)).setOnAction(e->{
			String[] answer= {"false","internal error!"};
			try {
				
				((PersonIdField)grid.getChildren().get(7)).isLegalID();
				((IndTextField)grid.getChildren().get(8)).isLegal();
				((IndTextField)grid.getChildren().get(9)).isLegal();
				((EmailField)grid.getChildren().get(12)).isLegalMail();
				((PhoneField)grid.getChildren().get(13)).isLegalPhone();
				((IndDatePicker)grid.getChildren().get(10)).isLegal();
				((IndComboBox<Gender>)grid.getChildren().get(11)).isLegal();
				inputArea.isLegal();
				inputVehicle.isLegal();
				
				ToolsBar.getInstance().getOut().println("addDp/"+((TextField)grid.getChildren().get(7)).getText()+"/"+((TextField)grid.getChildren().get(8)).getText()+"/"+((TextField)grid.getChildren().get(9)).getText()+"/"+((DatePicker)grid.getChildren().get(10)).getValue().format(DateTimeFormatter.ISO_LOCAL_DATE)+"/"+((ComboBox<Gender>)grid.getChildren().get(11)).getSelectionModel().getSelectedItem().name()+"/"+inputVehicle.getSelectionModel().getSelectedItem().name()+"/"+inputArea.getBox().getSelectionModel().getSelectedItem().split(" ")[0]+"/"+((TextField)grid.getChildren().get(12)).getText()+"/"+((TextField)grid.getChildren().get(13)).getText());
				answer=ToolsBar.getInstance().getIn().checkedReadLine().split("/");
				if(!Boolean.parseBoolean(answer[0])) {
					throw new FaildToAddException(answer[1]);
				}
				((TextField)grid.getChildren().get(7)).clear();
				((TextField)grid.getChildren().get(8)).clear();
				((TextField)grid.getChildren().get(9)).clear();
				((ComboBox<Gender>)grid.getChildren().get(11)).getSelectionModel().clearSelection();
				inputVehicle.getSelectionModel().clearSelection();
				inputArea.getBox().getSelectionModel().clearSelection();
				((TextField)grid.getChildren().get(12)).clear();
				((TextField)grid.getChildren().get(13)).clear();
				((IndDatePicker)grid.getChildren().get(10)).setValue(null);
				PopUp.display("Delivery person added successfully!", true);
				
				
			} 
			catch (BlankFieldException |InvalidPhoneException |  IllegalCharacterException | LieException | ToYoungException | InvalidEmailException| InvalidIDException | EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException | FaildToAddException | ToLateException | NumberFormatException | NotNumberException  e1) {
				PopUp.display(e1.getMessage(), false);
			}
			
		});	
		

		grid.getChildren().addAll(vehicle,area,inputVehicle,inputArea);
		ToolsBar.getInstance().getLastStacks().push(App.getLayout().getCenter());
		App.getLayout().setCenter(grid);
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
		PersonIdField inputId=new PersonIdField();
		IndTextField inputfirstName=new IndTextField();
		IndTextField inputlastName=new IndTextField();
		IndDatePicker inputbirthDay=new IndDatePicker();
		IndComboBox<Gender> inputgender=new IndComboBox<Gender>();
		inputgender.getItems().addAll(Gender.values());
		EmailField inputemail=new EmailField();
		PhoneField inputphone=new PhoneField();		
		Button button=new Button("Done");
		
		id.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-750*w,-300*h,200*w,200*w,50*h,50*h,20*s));
		firstName.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-750*w,-200*h,200*w,200*w,50*h,50*h,20*s));
		lastName.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-750*w,-100*h,200*w,200*w,50*h,50*h,20*s));
		birthDay.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,-300*h,200*w,200*w,50*h,50*h,20*s));
		gender.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,-200*h,200*w,200*w,50*h,50*h,20*s));
		email.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,-100*h,200*w,200*w,50*h,50*h,20*s));
		phone.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,0*h,200*w,200*w,50*h,50*h,20*s));
		
		inputId.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-450*w,-300*h,200*w,200*w,50*h,50*h,20*s));
		inputfirstName.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-450*w,-200*h,200*w,200*w,50*h,50*h,20*s));
		inputlastName.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-450*w,-100*h,200*w,200*w,50*h,50*h,20*s));
		inputbirthDay.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,-300*h,200*w,200*w,50*h,50*h,20*s));
		inputgender.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,-200*h,200*w,200*w,50*h,50*h,20*s));
		inputemail.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,-100*h,200*w,200*w,50*h,50*h,20*s));
		inputphone.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,0*h,200*w,200*w,50*h,50*h,20*s));
		
		button.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",400*w,-75*h,150*w,150*w,120*h,120*h,20*s));
		
		
		
		grid.getChildren().addAll(id,firstName,lastName,birthDay,gender,email,phone,inputId,inputfirstName,inputlastName,inputbirthDay,inputgender,inputemail,inputphone,button);
		return grid;
	}


	@SuppressWarnings("unchecked")
	private void newCook() {
		ToolsBar.getInstance().getPlace().pushText("Add cook");
		
		StackPane grid=newPersonGrid();
		Label expertise=new Label("Expertise: ");
		Label isShef=new Label("Is shef? ");
		IndComboBox<Expertise> inputExpertise=new IndComboBox<>();
		inputExpertise.getItems().addAll(Expertise.values());
		CheckBox inputShef=new CheckBox();

		
		expertise.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,100*h,200*w,200*w,50*h,50*h,20*s));
		isShef.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,200*h,200*w,200*w,50*h,50*h,20*s));
		inputExpertise.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,100*h,200*w,200*w,50*h,50*h,20*s));
		inputShef.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,200*h,200*w,200*w,50*h,50*h,20*s));
		
		((Button) grid.getChildren().get(14)).setOnAction(e->{
			String[] answer= {"false","internal error!"};
			try {
				
				((PersonIdField)grid.getChildren().get(7)).isLegalID();
				((IndTextField)grid.getChildren().get(8)).isLegal();
				((IndTextField)grid.getChildren().get(9)).isLegal();
				((EmailField)grid.getChildren().get(12)).isLegalMail();
				((PhoneField)grid.getChildren().get(13)).isLegalPhone();
				((IndDatePicker)grid.getChildren().get(10)).isLegal();
				((IndComboBox<Gender>)grid.getChildren().get(11)).isLegal();
				inputExpertise.isLegal();
				
				ToolsBar.getInstance().getOut().println("addCook/"+((TextField)grid.getChildren().get(7)).getText()+"/"+((TextField)grid.getChildren().get(8)).getText()+"/"+((TextField)grid.getChildren().get(9)).getText()+"/"+((DatePicker)grid.getChildren().get(10)).getValue().format(DateTimeFormatter.ISO_LOCAL_DATE)+"/"+((ComboBox<Gender>)grid.getChildren().get(11)).getSelectionModel().getSelectedItem().name()+"/"+inputExpertise.getSelectionModel().getSelectedItem().name()+"/"+String.valueOf(inputShef.isSelected())+"/"+((TextField)grid.getChildren().get(12)).getText()+"/"+((TextField)grid.getChildren().get(13)).getText());
				answer=ToolsBar.getInstance().getIn().checkedReadLine().split("/");
				if(!Boolean.parseBoolean(answer[0])) {
					throw new FaildToAddException(answer[1]);
				}
				((TextField)grid.getChildren().get(7)).clear();
				((TextField)grid.getChildren().get(8)).clear();
				((TextField)grid.getChildren().get(9)).clear();
				((ComboBox<Gender>)grid.getChildren().get(11)).getSelectionModel().clearSelection();
				inputExpertise.getSelectionModel().clearSelection();
				inputShef.setSelected(false);
				((TextField)grid.getChildren().get(12)).clear();
				((TextField)grid.getChildren().get(13)).clear();
				((IndDatePicker)grid.getChildren().get(10)).setValue(null);
				PopUp.display("Cook person added successfully!", true);
				
				
			} catch (BlankFieldException |InvalidPhoneException |  IllegalCharacterException | LieException | ToYoungException | InvalidEmailException| InvalidIDException | EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException | FaildToAddException | ToLateException | NumberFormatException | NotNumberException  e1) {
				PopUp.display(e1.getMessage(), false);
			}
			
			
		});	

		
		grid.getChildren().addAll(expertise,isShef,inputExpertise,inputShef);
		ToolsBar.getInstance().getLastStacks().push(App.getLayout().getCenter());
		App.getLayout().setCenter(grid);
		
	}

	//int id, String firstName, String lastName, LocalDate birthDay, Gender gender, Expertise expert, boolean isChef, String email, String phone

	
	

}




