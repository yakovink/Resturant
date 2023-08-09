package Nodes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

import Exceptions.WrongAreaException;
import Model.*;
import Utils.Expertise;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class QuotaMenu extends StackPane{


	public QuotaMenu( BorderPane layout) {
		this.setAlignment(Pos.CENTER);
		
		StackPane dishThings=this.dishThings();
		StackPane deliveryThings=this.deliveryThings();
		StackPane administrativeThings=this.administrativeThings();
		
		dishThings.setStyle("-fx-translate-x:-350;-fx-translate-y:-75;-fx-background-color:BLUE;-fx-background-radius:30px;-fx-min-width:550;-fx-max-width:550;-fx-min-height:850;-fx-max-height:850");
		deliveryThings.setStyle("-fx-translate-x:350;-fx-translate-y:-250;-fx-background-color:RED;-fx-background-radius:30px;-fx-min-width:800;-fx-max-width:800;-fx-min-height:1000;-fx-max-height:1000");
		administrativeThings.setStyle("-fx-translate-x:0;-fx-translate-y:100;-fx-background-color:GREEN;-fx-background-radius:30px;-fx-min-width:200;-fx-max-width:200;-fx-min-height:150;-fx-max-height:150");
		
		this.getChildren().addAll(dishThings,deliveryThings,administrativeThings);
		
		
		layout.setCenter(this);
	}
	
	private StackPane dishThings() {
		StackPane stack=new StackPane();
		stack.setAlignment(Pos.CENTER);
		
		StackPane cookByExp=this.cooksByExpertise();
		StackPane relDishList=this.relevantDishList();
		FilteredList<Component> popItems=this.popularTypes();
		FilteredList<Dish> profRelat=this.profitRelation();
		
		cookByExp.setStyle("-fx-translate-x:-125;-fx-translate-y:-175;");
		relDishList.setStyle("-fx-translate-x:125;-fx-translate-y:-175");
		popItems.setStyle("-fx-translate-x:-125;-fx-translate-y:175;-fx-min-width:200;-fx-max-width:200;-fx-min-height:150;-fx-max-height:150");
		profRelat.setStyle("-fx-translate-x:125;-fx-translate-y:175;-fx-min-width:200;-fx-max-width:200;-fx-min-height:150;-fx-max-height:150");
		
		stack.getChildren().addAll(cookByExp,relDishList,popItems,profRelat);
		return stack;
	}
	
	private StackPane deliveryThings() {
		StackPane stack=new StackPane();
		stack.setAlignment(Pos.CENTER);
		
		StackPane AI=this.AImachine();
		StackPane ordByArea=this.orderByDeliveryArea();
		StackPane ordByCust=this.orderByCustomer();
		StackPane delByPers=this.deliveriesByPerson();
		Label delPerType=this.numOfDeliveriesPerType();
		
		AI.setStyle("-fx-translate-x:250;-fx-translate-y:0");
		ordByArea.setStyle("-fx-translate-x:0;-fx-translate-y:-225");
		ordByCust.setStyle("-fx-translate-x:0;-fx-translate-y:225");
		delByPers.setStyle("-fx-translate-x:-250;-fx-translate-y:-238");
		delPerType.setStyle("-fx-translate-x:-250;-fx-translate-y:100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14;");
		
		stack.getChildren().addAll(AI,ordByArea,ordByCust,delByPers,delPerType);
		return stack;
	}
	
	private StackPane administrativeThings() {
		StackPane stack=new StackPane();
		stack.setAlignment(Pos.CENTER);
		
		Label budget=this.budget();
		Label revExp=this.revenueFromExpress();
		
		budget.setStyle("-fx-translate-x:0;-fx-translate-y:-50;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14;");
		revExp.setStyle("-fx-translate-x:0;-fx-translate-y:50;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14;");
	
		stack.getChildren().addAll(budget,revExp);
		return stack;
	}
	
	
	
	
	
	
	private Label budget() {
		Label label=new Label("Your budget is: "+Restaurant.getInstance().getBudget());
		return label;
	}
	
	private Label revenueFromExpress() {
		Label label=new Label("Your revenue from express\ndeliveries is "+Restaurant.getRestaurant().revenueFromExpressDeliveries());
		return label;

	}
	private Label numOfDeliveriesPerType() {

		Label label=new Label("Express deliveries: "+Restaurant.getRestaurant().getNumberOfDeliveriesPerType().get("Express Delivery")+"\nRegular deliveries: "+Restaurant.getRestaurant().getNumberOfDeliveriesPerType().get("Regular Delivery"));
		return label;
	}
	
	private StackPane deliveriesByPerson() {
		StackPane stack=new StackPane();
		stack.setAlignment(Pos.CENTER);
		FilteredBox<DeliveryPerson> searchBox=new FilteredBox<>(Restaurant.getRestaurant().getDeliveryPersons().values());
		FilteredBox<Integer> monthBox=new FilteredBox<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12));
		FilteredList<Delivery> relevant=new FilteredList<>(new HashSet<>());
		searchBox.getBox().getSelectionModel().selectedItemProperty().addListener(e->{
			relevant.getList().getItems().clear();
			relevant.getList().getItems().addAll(Restaurant.getInstance().getDeliveriesByPerson(searchBox.getBox().getSelectionModel().getSelectedItem(), monthBox.getBox().getSelectionModel().getSelectedItem()));
		});
		searchBox.getSearch().setPromptText("delivery person");
		monthBox.getBox().getSelectionModel().selectedItemProperty().addListener(e->{
			relevant.getList().getItems().clear();
			relevant.getList().getItems().addAll(Restaurant.getInstance().getDeliveriesByPerson(searchBox.getBox().getSelectionModel().getSelectedItem(), monthBox.getBox().getSelectionModel().getSelectedItem()));
		});
		monthBox.getSearch().setPromptText("month");
		
		searchBox.setStyle("-fx-translate-x:0;-fx-translate-y:-150;-fx-max-width:200;-fx-max-height:100;-fx-min-height:100;-fx-font-size:14;-fx-background-radius:15px");
		monthBox.setStyle("-fx-translate-x:0;-fx-translate-y:-50;-fx-max-width:200;-fx-max-height:100;-fx-min-height:100;-fx-font-size:14;-fx-background-radius:15px");
		relevant.setStyle("-fx-translate-x:0;-fx-translate-y:125;-fx-max-width:200;-fx-max-height:150;-fx-min-height:150;-fx-font-size:14;-fx-background-radius:15px");
		stack.getChildren().addAll(searchBox,monthBox,relevant);
		return stack;
	}
	
	private StackPane orderByDeliveryArea() {
		FilteredBox<DeliveryArea> searchBox=new FilteredBox<>(Restaurant.getRestaurant().getAreas().values());
		FilteredList<Order> relevant=new FilteredList<>(new HashSet<>());
		StackPane stack=new StackPane();
		stack.setAlignment(Pos.CENTER);
		searchBox.getBox().getSelectionModel().selectedItemProperty().addListener(e->{
			relevant.getList().getItems().clear();
			relevant.getList().getItems().addAll(Restaurant.getInstance().getOrderByDeliveryArea().get(searchBox.getBox().getSelectionModel().getSelectedItem()));
		});
		searchBox.setStyle("-fx-translate-x:0;-fx-translate-y:-175;-fx-min-width:200;-fx-max-width:200;-fx-min-height:100;-fx-max-height:100;-fx-font-size:14;-fx-background-radius:15px");
		relevant.setStyle("-fx-translate-x:0;-fx-translate-y:0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:150;-fx-max-height:150;-fx-font-size:14;-fx-background-radius:15px");
		stack.getChildren().addAll(searchBox,relevant);
		return stack;
	}
	
	private StackPane orderByCustomer() {
		StackPane stack=new StackPane();
		stack.setAlignment(Pos.CENTER);
		FilteredBox<Customer> searchBox=new FilteredBox<>(Restaurant.getRestaurant().getCustomers().values());
		FilteredList<Order> relevant=new FilteredList<>(new HashSet<>());
		searchBox.getBox().getSelectionModel().selectedItemProperty().addListener(e->{
			relevant.getList().getItems().clear();
			relevant.getList().getItems().addAll(Restaurant.getInstance().getOrderByCustomer().get(searchBox.getBox().getSelectionModel().getSelectedItem()));
		});
		searchBox.setStyle("-fx-translate-x:0;-fx-translate-y:-175;-fx-min-width:200;-fx-max-width:200;-fx-min-height:100;-fx-max-height:100;-fx-font-size:14;-fx-background-radius:15px");
		relevant.setStyle("-fx-translate-x:0;-fx-translate-y:0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:150;-fx-max-height:150;-fx-font-size:14;-fx-background-radius:15px");
		stack.getChildren().addAll(searchBox,relevant);
		return stack;
	}
	
	private StackPane AImachine() {
		StackPane stack=new StackPane();
		FilteredBox<DeliveryArea> searchBox=new FilteredBox<>(Restaurant.getRestaurant().getAreas().values());
		FilteredBox<DeliveryPerson> dps=new FilteredBox<>(new HashSet<>());
		FilteredList<Order> orders=new FilteredList<>(new HashSet<>());
		FilteredList<Delivery> deliveries=new FilteredList<>(new HashSet<>());
		Button decide=new Button("decide");
		Label status=new Label();
		
		searchBox.getSearch().setPromptText("Area");
		dps.getSearch().setPromptText("Delivery Person");
		orders.getSearch().setPromptText("Orders");
		deliveries.getSearch().setPromptText("Deliveries");
		
		searchBox.getBox().getSelectionModel().selectedItemProperty().addListener(e->{
			orders.getList().getItems().clear();
			orders.getList().getItems().addAll(Restaurant.getInstance().getWaitForDecide().get(searchBox.getBox().getSelectionModel().getSelectedItem()));
			dps.getBox().getItems().clear();
			for(DeliveryPerson dp:searchBox.getBox().getSelectionModel().getSelectedItem().getDelPersons()) {
				if(!dp.isBusy()) {
					dps.getBox().getItems().add(dp);
				}
			}
		});
		decide.setOnAction(e->{
			try {
				deliveries.getList().getItems().addAll(Restaurant.getInstance().createAIMacine(dps.getBox().getSelectionModel().getSelectedItem(), searchBox.getBox().getSelectionModel().getSelectedItem(), new TreeSet<>(orders.getList().getItems())));
				orders.getList().getItems().clear();
				status.setText("machine success");
				status.setTextFill(Color.GREEN);
			} catch (WrongAreaException  e1) {
				// TODO Auto-generated catch block
				status.setText(e1.getMessage());
				status.setTextFill(Color.RED);
			}
			
		});
		searchBox.setStyle("-fx-translate-x:0;-fx-translate-y:-385;-fx-min-width:200;-fx-max-width:200;-fx-min-height:100;-fx-max-height:100;-fx-font-size:14;-fx-background-radius:15px");
		dps.setStyle("-fx-translate-x:0;-fx-translate-y:-285;-fx-min-width:200;-fx-max-width:200;-fx-min-height:100;-fx-max-height:100;-fx-font-size:14;-fx-background-radius:15px");
		orders.setStyle("-fx-translate-x:0;-fx-translate-y:-85;-fx-min-width:200;-fx-max-width:200;-fx-min-height:150;-fx-max-height:150;-fx-font-size:14;-fx-background-radius:15px");
		decide.setStyle("-fx-translate-x:0;-fx-translate-y:115;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14;-fx-background-radius:15px");
		deliveries.setStyle("-fx-translate-x:0;-fx-translate-y:290;-fx-min-width:200;-fx-max-width:200;-fx-min-height:150;-fx-max-height:150;-fx-font-size:14;-fx-background-radius:15px");
		status.setStyle("-fx-translate-x:0;-fx-translate-y:490;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14;");
		
		stack.getChildren().addAll(searchBox,dps,orders,deliveries,decide,status);
		return stack;
	}
	
	private FilteredList<Dish> profitRelation() {
		FilteredList<Dish> message=new FilteredList<>(Restaurant.getRestaurant().getProfitRelation());
		message.setStyle("-fx-min-width:200;-fx-max-width:200;-fx-min-height:150;-fx-max-height:150;-fx-font-size:14;-fx-background-radius:15px");
		return message;
	}
	private FilteredList<Component> popularTypes() {
		FilteredList<Component> message=new FilteredList<>(Restaurant.getRestaurant().getPopularComponents());
		message.setStyle("-fx-min-width:200;-fx-max-width:200;-fx-min-height:150;-fx-max-height:150;-fx-font-size:14;-fx-background-radius:15px");
		return message;
	}

	private StackPane relevantDishList() {
		StackPane stack=new StackPane();
		stack.setAlignment(Pos.CENTER);
		FilteredBox<Customer> searchBox=new FilteredBox<>(Restaurant.getRestaurant().getCustomers().values());
		FilteredList<Dish> relevant=new FilteredList<>(Restaurant.getInstance().getDishes().values());
		searchBox.getBox().getSelectionModel().selectedItemProperty().addListener(e->{
			relevant.getList().getItems().clear();
			relevant.getList().getItems().addAll(Restaurant.getInstance().getReleventDishList(searchBox.getBox().getSelectionModel().getSelectedItem()));
		});
		searchBox.setStyle("-fx-translate-x:0;-fx-translate-y:-175;-fx-min-width:200;-fx-max-width:200;-fx-min-height:100;-fx-max-height:100;-fx-font-size:14;-fx-background-radius:15px");
		relevant.setStyle("-fx-translate-x:0;-fx-translate-y:0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:150;-fx-max-height:150;-fx-font-size:14;-fx-background-radius:15px");
		stack.getChildren().addAll(searchBox,relevant);
		return stack;
	}

	private StackPane cooksByExpertise() {
		StackPane stack=new StackPane();
		stack.setAlignment(Pos.CENTER);
		FilteredBox<Expertise> searchBox=new FilteredBox<>(Arrays.asList(Expertise.values()));
		FilteredList<Cook> relevant=new FilteredList<>(Restaurant.getInstance().getCooks().values());
		searchBox.getBox().getSelectionModel().selectedItemProperty().addListener(e->{
			relevant.getList().getItems().clear();
			relevant.getList().getItems().addAll(Restaurant.getInstance().GetCooksByExpertise(searchBox.getBox().getSelectionModel().getSelectedItem()));
		});
		searchBox.setStyle("-fx-translate-x:0;-fx-translate-y:-175;-fx-min-width:200;-fx-max-width:200;-fx-min-height:100;-fx-max-height:100;-fx-font-size:14;-fx-background-radius:15px");
		relevant.setStyle("-fx-translate-x:0;-fx-translate-y:0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:150;-fx-max-height:150;-fx-font-size:14;-fx-background-radius:15px");
		stack.getChildren().addAll(searchBox,relevant);
		return stack;
	}
}
