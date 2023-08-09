package Nodes;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.imageio.ImageIO;

import Utils.DishType;
import Utils.Expertise;
import Utils.Gender;
import Utils.Neighberhood;
import Utils.Vehicle;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class EditMenu extends StackPane{

	private Button editCook;
	private Button editDeliveryPerson;
	private Button editCustomer;
	private Button editComponent;
	private Button editDish;
	private Button editOrder;
	private Button editDelivery;
	private Button editArea;

	private BorderPane layout;
	
	





	public Button getEditCook() {
		return editCook;
	}


	public void setEditCook(Button editCook) {
		this.editCook = editCook;
	}


	public Button getEditDeliveryPerson() {
		return editDeliveryPerson;
	}


	public void setEditDeliveryPerson(Button editDeliveryPerson) {
		this.editDeliveryPerson = editDeliveryPerson;
	}


	public Button getEditCustomer() {
		return editCustomer;
	}


	public void setEditCustomer(Button editCustomer) {
		this.editCustomer = editCustomer;
	}


	public Button getEditComponent() {
		return editComponent;
	}


	public void setEditComponent(Button editComponent) {
		this.editComponent = editComponent;
	}


	public Button getEditDish() {
		return editDish;
	}


	public void setEditDish(Button editDish) {
		this.editDish = editDish;
	}


	public Button getEditOrder() {
		return editOrder;
	}


	public void setEditOrder(Button editOrder) {
		this.editOrder = editOrder;
	}


	public Button getEditDelivery() {
		return editDelivery;
	}


	public void setEditDelivery(Button editDelivery) {
		this.editDelivery = editDelivery;
	}


	public Button getEditArea() {
		return editArea;
	}


	public void setEditArea(Button editArea) {
		this.editArea = editArea;
	}


	public EditMenu(BorderPane layout) {
		this.layout=layout;

		editCook=new Button("Edit Cook...");
		editDeliveryPerson=new Button("Edit DeliveryPerson...");
		editCustomer=new Button("Edit Customer...");
		editComponent=new Button("Edit Component...");
		editDish=new Button("Edit Dish...");
		editOrder=new Button("Edit Order...");
		editDelivery=new Button("Edit Delivery...");
		editArea=new Button("Edit Area...");
		
		
		this.editCook.setOnAction(e->editCook());
		this.editDeliveryPerson.setOnAction(e->editDeliveryPerson());
		this.editCustomer.setOnAction(e->editCustomer());
		this.editComponent.setOnAction(e->editComponent());
		this.editDish.setOnAction(e->editDish());
		this.editOrder.setOnAction(e->editOrder());
		this.editDelivery.setOnAction(e->editDelivery());
		this.editArea.setOnAction(e->editArea());
		
		editCook.setStyle("-fx-translate-x:-200;-fx-translate-y:-100;-fx-min-width:300;-fx-max-width:300;-fx-min-height:100;-fx-max-height:100;-fx-font-size:25;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px");
		editDeliveryPerson.setStyle("-fx-translate-x:-200;-fx-translate-y:50;-fx-min-width:300;-fx-max-width:300;-fx-min-height:100;-fx-max-height:100;-fx-font-size:25;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px");
		editCustomer.setStyle("-fx-translate-x:-200;-fx-translate-y:200;-fx-min-width:300;-fx-max-width:300;-fx-min-height:100;-fx-max-height:100;-fx-font-size:25;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px");
		editComponent.setStyle("-fx-translate-x:-200;-fx-translate-y:-250;-fx-min-width:300;-fx-max-width:300;-fx-min-height:100;-fx-max-height:100;-fx-font-size:25;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px");
		editDish.setStyle("-fx-translate-x:200;-fx-translate-y:-100;-fx-min-width:300;-fx-max-width:300;-fx-min-height:100;-fx-max-height:100;-fx-font-size:25;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px");
		editOrder.setStyle("-fx-translate-x:200;-fx-translate-y:50;-fx-min-width:300;-fx-max-width:300;-fx-min-height:100;-fx-max-height:100;-fx-font-size:25;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px");
		editDelivery.setStyle("-fx-translate-x:200;-fx-translate-y:200;-fx-min-width:300;-fx-max-width:300;-fx-min-height:100;-fx-max-height:100;-fx-font-size:25;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px");
		editArea.setStyle("-fx-translate-x:200;-fx-translate-y:-250;-fx-min-width:300;-fx-max-width:300;-fx-min-height:100;-fx-max-height:100;-fx-font-size:25;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px");

		
		this.getChildren().addAll(editCook,editDeliveryPerson,editCustomer,editComponent,editDish,editOrder,editDelivery,editArea);
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(this);
		
	}


	private void editArea() {
		ToolsBar.getInstance().getOut().println("getAreas");
		Collection<String> list;
		try {
			list=Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
		} catch (IOException e1) {
			list=new HashSet<>();
		}
		FilteredBox<String> searchBox=new FilteredBox<>(list);
		searchBox.getSearch().setPromptText("Search your area here");
		searchBox.getBox().getSelectionModel().selectedItemProperty().addListener(e->{
				ToolsBar.getInstance().getOut().println("getRealArea/"+searchBox.getBox().getSelectionModel().getSelectedItem().split(" ")[0]);

				String[] info;
				try {
					info = ToolsBar.getInstance().getIn().readLine().split("/");
				} catch (IOException e4) {
					return;
				}
				StackPane stack=new StackPane();
				stack.setAlignment(Pos.CENTER);
				Collection<String> tmp;
				
				Label id=new Label("Area id: ");
				Label name=new Label("Area name: ");
				Label time=new Label("Time to get: ");
				
				TextField inputid=new TextField(info[0]);
				TextField inputName=new TextField(info[1]);
				TextField inputTime=new TextField(info[5]);
				
				ToolsBar.getInstance().getOut().println("getDeps");
				try {
					tmp=Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
				} catch (IOException e4) {
					// TODO Auto-generated catch block
					tmp=new HashSet<>();
				}
				FilteredList<String> deps=new FilteredList<>(tmp);
				FilteredList<String> inputDeps=new FilteredList<>(Arrays.asList(info[2].split(",")));
				Button addDp=new Button("add");
				Button remDp=new Button("remove");
				deps.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				deps.getList().getItems().removeAll(inputDeps.getList().getItems());
				inputDeps.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				addDp.setOnAction(e2->{
					inputDeps.getList().getItems().addAll(deps.getList().getSelectionModel().getSelectedItems());
					deps.getList().getItems().removeAll(deps.getList().getSelectionModel().getSelectedItems());
				});
				remDp.setOnAction(e2->{
					deps.getList().getItems().addAll(inputDeps.getList().getSelectionModel().getSelectedItems());
					inputDeps.getList().getItems().removeAll(inputDeps.getList().getSelectionModel().getSelectedItems());
				});
				
				
				ToolsBar.getInstance().getOut().println("getDels");
				try {
					tmp=Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
				} catch (IOException e4) {
					tmp=new HashSet<>();
				}
				FilteredList<String> dels=new FilteredList<>(tmp);
				FilteredList<String> inputDels=new FilteredList<>(Arrays.asList(info[3].split(",")));
				Button addDl=new Button("add");
				Button remDl=new Button("remove");
				dels.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				dels.getList().getItems().removeAll(inputDels.getList().getItems());
				inputDels.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				addDl.setOnAction(e2->{
					inputDels.getList().getItems().addAll(dels.getList().getSelectionModel().getSelectedItems());
					dels.getList().getItems().removeAll(dels.getList().getSelectionModel().getSelectedItems());
				});
				remDl.setOnAction(e2->{
					dels.getList().getItems().addAll(inputDels.getList().getSelectionModel().getSelectedItems());
					inputDels.getList().getItems().removeAll(inputDels.getList().getSelectionModel().getSelectedItems());
				});
				

				FilteredList<Neighberhood> nebs=new FilteredList<>(Arrays.asList(Neighberhood.values()));
				HashSet<Neighberhood> n=new HashSet<>();
				Arrays.asList(info[4].split(",")).forEach(e1->n.add(Neighberhood.valueOf(e1)));
				FilteredList<Neighberhood> inputNebs=new FilteredList<>(n);
				Button addNb=new Button("add");
				Button remNb=new Button("remove");
				nebs.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				inputNebs.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				nebs.getList().getItems().removeAll(inputNebs.getList().getItems());
				addNb.setOnAction(e2->{
					inputNebs.getList().getItems().addAll(nebs.getList().getSelectionModel().getSelectedItems());
					nebs.getList().getItems().removeAll(nebs.getList().getSelectionModel().getSelectedItems());
				});
				remNb.setOnAction(e2->{
					nebs.getList().getItems().addAll(inputNebs.getList().getSelectionModel().getSelectedItems());
					inputNebs.getList().getItems().removeAll(inputNebs.getList().getSelectionModel().getSelectedItems());
				});
				Label status=new Label();
				Button edit=new Button();
				
				id.setStyle("-fx-translate-x:-600;-fx-translate-y:-200;-fx-min-width:150;-fx-max-width:150;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14");
				name.setStyle("-fx-translate-x:-200;-fx-translate-y:-200;-fx-min-width:150;-fx-max-width:150;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14");
				time.setStyle("-fx-translate-x:200;-fx-translate-y:-200;-fx-min-width:150;-fx-max-width:150;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14");
				inputid.setStyle("-fx-translate-x:-400;-fx-translate-y:-200;-fx-min-width:150;-fx-max-width:150;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14;-fx-background-radius:15px");
				inputName.setStyle("-fx-translate-x:0;-fx-translate-y:-200;-fx-min-width:150;-fx-max-width:150;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14;-fx-background-radius:15px");
				inputTime.setStyle("-fx-translate-x:400;-fx-translate-y:-200;-fx-min-width:150;-fx-max-width:150;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14;-fx-background-radius:15px");
				
				deps.setStyle("-fx-translate-x:-500;-fx-translate-y:-100;-fx-min-width:150;-fx-max-width:150;-fx-min-height:200;-fx-max-height:200;-fx-font-size:14;-fx-background-radius:15px");
				inputDeps.setStyle("-fx-translate-x:-500;-fx-translate-y:150;-fx-min-width:150;-fx-max-width:150;-fx-min-height:200;-fx-max-height:200;-fx-font-size:14;-fx-background-radius:15px");
				addDp.setStyle("-fx-translate-x:-600;-fx-translate-y:400;-fx-min-width:75;-fx-max-width:75;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14;-fx-background-radius:15px");
				remDp.setStyle("-fx-translate-x:-400;-fx-translate-y:400;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14;-fx-background-radius:15px");
				
				dels.setStyle("-fx-translate-x:-100;-fx-translate-y:-100;-fx-min-width:150;-fx-max-width:150;-fx-min-height:200;-fx-max-height:200;-fx-font-size:14;-fx-background-radius:15px");
				inputDels.setStyle("-fx-translate-x:-100;-fx-translate-y:150;-fx-min-width:150;-fx-max-width:150;-fx-min-height:200;-fx-max-height:200;-fx-font-size:14;-fx-background-radius:15px");
				addDl.setStyle("-fx-translate-x:-200;-fx-translate-y:400;-fx-min-width:75;-fx-max-width:75;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14;-fx-background-radius:15px");
				remDl.setStyle("-fx-translate-x:0;-fx-translate-y:400;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14;-fx-background-radius:15px");
				
				nebs.setStyle("-fx-translate-x:300;-fx-translate-y:-100;-fx-min-width:150;-fx-max-width:150;-fx-min-height:200;-fx-max-height:200;-fx-font-size:14;-fx-background-radius:15px");
				inputNebs.setStyle("-fx-translate-x:300;-fx-translate-y:150;-fx-min-width:150;-fx-max-width:150;-fx-min-height:200;-fx-max-height:200;-fx-font-size:14;-fx-background-radius:15px");
				addNb.setStyle("-fx-translate-x:200;-fx-translate-y:400;-fx-min-width:75;-fx-max-width:75;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14;-fx-background-radius:15px");
				remNb.setStyle("-fx-translate-x:400;-fx-translate-y:400;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14;-fx-background-radius:15px");
				
				status.setStyle("-fx-translate-x:300;-fx-translate-y:-325;-fx-min-width:400;-fx-max-width:400;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14");
				edit.setStyle("-fx-translate-x:300;-fx-translate-y:-475;-fx-min-width:150;-fx-max-width:150;-fx-min-height:120;-fx-max-height:120;-fx-font-size:20;-fx-background-radius:15px");
				searchBox.setStyle("-fx-translate-x:-200;-fx-translate-y:-400;-fx-min-width:250;-fx-max-width:250;-fx-min-height:100;-fx-max-height:100;-fx-font-size:20;-fx-background-radius:15px");
				
				HashSet<String> set=new HashSet<>();
				edit.setOnAction(e2->{

					try {

						String originalId=info[0];
						info[0]=inputid.getText();
						info[1]=inputName.getText();
						info[5]=inputTime.getText();

						deps.getList().getItems().forEach(e3->set.add(e3.split(" ")[0]));
						info[2]=String.join(",", set);
						set.clear();
						dels.getList().getItems().forEach(e3->set.add(e3.split(" ")[0]));
						info[3]=String.join(",", set);
						info[4]="";
						for(Neighberhood s:nebs.getList().getItems()) {
							info[4]+=","+s.name();
						}
						info[4]=info[4].substring(1);
						ToolsBar.getInstance().getOut().println("editArea/"+originalId+"/"+String.join("/",info));
						String[] answer = ToolsBar.getInstance().getIn().readLine().split("/");
						if(Boolean.parseBoolean(answer[0])) {
							status.setText(String.format("Area %s was editted succesfully", info[1]));
							status.setTextFill(Color.GREEN);
						}
						else {
							if(answer.length==1) {
								status.setText(String.format("Area %s was not editted", info[1]));
								status.setTextFill(Color.RED);
							}
							else {
								status.setText(answer[1]);
								status.setTextFill(Color.RED);
							}
						}
					} catch (IOException e3) {
						status.setText("please check your internet connection");
						status.setTextFill(Color.RED);
					}
				});
				stack.getChildren().addAll(id,name,time,inputid,inputName,inputTime,deps,inputDeps,addDp,remDp,dels,inputDels,addDl,remDl,nebs,inputNebs,addNb,remNb,status,edit,searchBox);
				layout.setCenter(stack);
	});
		searchBox.setStyle("-fx-translate-x:0;-fx-translate-y:0;-fx-min-width:250;-fx-max-width:250;-fx-min-height:100;-fx-max-height:100;-fx-font-size:20;-fx-background-radius:15px");
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(searchBox);
	}

	/*	private static int idCounter = 1;
	private int id;
	private String areaName;
	private HashSet<DeliveryPerson> delPersons;
	private HashSet<Delivery> delivers;
	private HashSet<Neighberhood> neighberhoods;
	private final int deliverTime;**/

	private void editDelivery() {

		ToolsBar.getInstance().getOut().println("getDels");
		Collection<String> list;
		try {
			list=Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
		} catch (IOException e1) {
			list=new HashSet<>();
		}
		FilteredBox<String> searchBox=new FilteredBox<>(list);
		searchBox.getSearch().setPromptText("Search your delivery here");
		searchBox.setStyle("-fx-translate-x:0;-fx-translate-y:-500;-fx-min-width:250;-fx-max-width:250;-fx-min-height:100;-fx-max-height:100;-fx-font-size:20;-fx-background-radius:15px;");
		searchBox.getBox().getSelectionModel().selectedItemProperty().addListener(e->{
			ToolsBar.getInstance().getOut().println("getRealDel/"+searchBox.getBox().getSelectionModel().getSelectedItem().split(" ")[1]);
			String[] info;
			try {
				info = ToolsBar.getInstance().getIn().readLine().split("/");
			} catch (IOException e4) {
				return;
			}
			StackPane grid=new StackPane();
			grid.setAlignment(Pos.CENTER);
			
			Label ID=new Label("ID: ");
			Label delPerson=new Label("Delivery person: ");
			Label area=new Label("Delivery area: ");
			Label delivered=new Label("Delivered? ");
			Label date=new Label("Delivery date: ");
			Label status=new Label();
			
			TextField inputID=new TextField(info[1]);
			ToolsBar.getInstance().getOut().println("getRealArea/"+info[4].split(" ")[0]);
			Collection<String> tmp;
			try {
				tmp=Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				tmp=new HashSet<>();
			}
			FilteredBox<String> inputDelPerson=new FilteredBox<>(tmp);
			ToolsBar.getInstance().getOut().println("getAreas");
			try {
				tmp=Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
			} catch (IOException e2) {
				tmp=new HashSet<>();
			}
			FilteredBox<String> inputArea=new FilteredBox<>(tmp);
			CheckBox inputDelivered=new CheckBox();
			DatePicker inputDate=new DatePicker();
			Button button=new Button("edit");
			
			inputDelPerson.getBox().getSelectionModel().select(info[3]);
			inputArea.getBox().getSelectionModel().select(info[4]);
			inputDelivered.setSelected(Boolean.parseBoolean(info[6]));
			String[] ld=info[5].split("-");
			inputDate.setValue(LocalDate.of(Integer.parseInt(ld[0]),Integer.parseInt(ld[1]),Integer.parseInt(ld[2])));
			
			ID.setStyle("-fx-translate-x:-150;-fx-translate-y:-400;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			delPerson.setStyle("-fx-translate-x:-150;-fx-translate-y:-300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			area.setStyle("-fx-translate-x:-150;-fx-translate-y:-200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			delivered.setStyle("-fx-translate-x:-150;-fx-translate-y:-100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			date.setStyle("-fx-translate-x:-150;-fx-translate-y:0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			inputID.setStyle("-fx-translate-x:150;-fx-translate-y:-400;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputDelPerson.setStyle("-fx-translate-x:150;-fx-translate-y:-275;-fx-min-width:200;-fx-max-width:200;-fx-min-height:100;-fx-max-height:100;-fx-font-size:20;-fx-background-radius:15px");
			inputArea.setStyle("-fx-translate-x:150;-fx-translate-y:-175;-fx-min-width:200;-fx-max-width:200;-fx-min-height:100;-fx-max-height:100;-fx-font-size:20;-fx-background-radius:15px");
			inputDelivered.setStyle("-fx-translate-x:150;-fx-translate-y:-100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputDate.setStyle("-fx-translate-x:150;-fx-translate-y:0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			button.setStyle("-fx-translate-x:500;-fx-translate-y:25;-fx-min-width:150;-fx-max-width:150;-fx-min-height:120;-fx-max-height:120;-fx-font-size:20;-fx-background-radius:15px");
			status.setStyle("-fx-translate-x:500;-fx-translate-y:175;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			
			
			
			
			grid.getChildren().addAll(searchBox,ID,delPerson,area,delivered,date,status,inputID,inputDelPerson,inputArea,inputDelivered,inputDate,button);
			if(info[0].equals("reg")) {

				ToolsBar.getInstance().getOut().println("getOrds");
				Collection<String> ords;
				try {
					ords=Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					ords=new HashSet<>();
				}
				FilteredList<String> allOrders=new FilteredList<>(ords);
				FilteredList<String> chosenOrders=new FilteredList<>(Arrays.asList(info[2].split(",")));
				Button add=new Button("add orders");
				Button remove=new Button("remove Orders");
				
				allOrders.setStyle("-fx-translate-x:-250;-fx-translate-y:300;-fx-min-width:250;-fx-max-width:250;-fx-min-height:400;-fx-max-height:500;-fx-font-size:20;-fx-background-radius:15px");
				chosenOrders.setStyle("-fx-translate-x:250;-fx-translate-y:300;-fx-min-width:250;-fx-max-width:250;-fx-min-height:400;-fx-max-height:500;-fx-font-size:20;-fx-background-radius:15px");
				add.setStyle("-fx-translate-x:0;-fx-translate-y:150;-fx-min-width:200;-fx-max-width:200;-fx-min-height:75;-fx-max-height:75;-fx-font-size:20;-fx-background-radius:15px");
				remove.setStyle("-fx-translate-x:0;-fx-translate-y:250;-fx-min-width:200;-fx-max-width:200;-fx-min-height:75;-fx-max-height:75;-fx-font-size:20;-fx-background-radius:15px");
				
				allOrders.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				chosenOrders.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				
				allOrders.getList().getItems().removeAll(chosenOrders.getList().getItems());
				add.setOnAction(eb->{
					chosenOrders.getList().getItems().addAll(allOrders.getList().getSelectionModel().getSelectedItems());
					allOrders.getList().getItems().removeAll(allOrders.getList().getSelectionModel().getSelectedItems());
				});
				remove.setOnAction(eb->{
					allOrders.getList().getItems().addAll(chosenOrders.getList().getSelectionModel().getSelectedItems());
					chosenOrders.getList().getItems().removeAll(chosenOrders.getList().getSelectionModel().getSelectedItems());
				});
				
				
				grid.getChildren().clear();
				grid.getChildren().addAll(searchBox,ID,delPerson,area,delivered,date,status,inputID,inputDelPerson,inputArea,inputDelivered,inputDate,button,allOrders,chosenOrders,add,remove);
				button.setOnAction(eb->{
					try {
					String originalId=info[1];
					info[1]=inputID.getText();
					HashSet<String> set=new HashSet<>();
					chosenOrders.getList().getItems().forEach(e2->set.add(e2.split(" ")[0]));
					info[7]=String.join(",", set);
					info[3]=inputDelPerson.getBox().getSelectionModel().getSelectedItem().split(" ")[0];
					info[4]=inputArea.getBox().getSelectionModel().getSelectedItem().split(" ")[0];
					info[5]=inputDate.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE);
					info[6]=String.valueOf(inputDelivered.isSelected());
					String[] newInfo=Arrays.copyOfRange(info, 1, 7);
					ToolsBar.getInstance().getOut().println("editRegDel/"+originalId+"/"+String.join("/", newInfo));
					String[] answer=ToolsBar.getInstance().getIn().readLine().split("/");
					if(Boolean.parseBoolean(answer[0])) {
						status.setText(String.format("Delivery %s was editted succesfully", newInfo[0]));
						status.setTextFill(Color.GREEN);
					}
					else {
						if(answer.length==1) {
							status.setText(String.format("Delivery %s was not editted", newInfo[0]));
							status.setTextFill(Color.RED);
						}
						else {
							status.setText(answer[1]);
							status.setTextFill(Color.RED);
						}
						}
					}
					catch (IOException e3) {
							status.setText("please check your internet connection");
							status.setTextFill(Color.RED);
					}
				});
				ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
				layout.setCenter(grid);
			}
			else {
				
				Label postage=new Label("Postage: ");
				Label order=new Label("order: ");
				TextField inputPostage=new TextField(info[7]);
				ToolsBar.getInstance().getOut().println("getOrds");
				Collection<String> ords;
				try {
					ords=Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					ords=new HashSet<>();
				}
				FilteredBox<String> inputOrder=new FilteredBox<>(ords);
				
				inputOrder.getBox().getSelectionModel().select(info[2]);
				
				postage.setStyle("-fx-translate-x:-150;-fx-translate-y:0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
				order.setStyle("-fx-translate-x:-150;-fx-translate-y:100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
				inputPostage.setStyle("-fx-translate-x:150;-fx-translate-y:0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
				inputOrder.setStyle("-fx-translate-x:150;-fx-translate-y:125;-fx-min-width:200;-fx-max-width:200;-fx-min-height:100;-fx-max-height:100;-fx-font-size:20;-fx-background-radius:15px");
				
				grid.getChildren().clear();
				grid.getChildren().addAll(searchBox,ID,delPerson,area,delivered,date,status,inputID,inputDelPerson,inputArea,inputDelivered,inputDate,button,order,postage,inputOrder,inputPostage);
				
				button.setOnAction(eb->{
					try {
						String originalId=info[1];
						info[1]=inputID.getText();
						info[2]=inputOrder.getBox().getSelectionModel().getSelectedItem().split(" ")[0];
						info[3]=inputDelPerson.getBox().getSelectionModel().getSelectedItem().split(" ")[0];
						info[4]=inputArea.getBox().getSelectionModel().getSelectedItem().split(" ")[0];
						info[5]=String.valueOf(inputDelivered.isSelected());
						info[6]=inputDate.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE);
						info[7]=inputPostage.getText();
						String[] newInfo=Arrays.copyOfRange(info, 1, 8);
						ToolsBar.getInstance().getOut().println("editExpDel/"+originalId+"/"+String.join("/", newInfo));
						String[] answer=ToolsBar.getInstance().getIn().readLine().split("/");
						if(Boolean.parseBoolean(answer[0])) {
							status.setText(String.format("Delivery %s was editted succesfully", newInfo[0]));
							status.setTextFill(Color.GREEN);
						}
						else {
							if(answer.length==1) {
								status.setText(String.format("Delivery %s was not editted", newInfo[0]));
								status.setTextFill(Color.RED);
							}
							else{
								status.setText(answer[1]);
								status.setTextFill(Color.RED);
							}
						}
					}
					catch (IOException e3) {
						status.setText("please check your internet connection");
						status.setTextFill(Color.RED);
				}
				});
				layout.setCenter(grid);
			}
			
		});
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(searchBox);
	}
//int id,TreeSet<Order> orders, DeliveryPerson deliveryPerson, DeliveryArea area,	boolean isDelivered,LocalDate deliveredDate

	private void editOrder() {
		ToolsBar.getInstance().getOut().println("getOrds");
		Collection<String> list;
		try {
			list=Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
		} catch (IOException e1) {
			list=new HashSet<>();
		}
		FilteredBox<String> searchBox=new FilteredBox<>(list);
		searchBox.getSearch().setPromptText("Search your order here");
		searchBox.setStyle("-fx-translate-x:0;-fx-translate-y:0;-fx-min-width:300;-fx-max-width:300;-fx-min-height:100;-fx-max-height:100;-fx-font-size:20;-fx-background-radius:15px");
		searchBox.getBox().getSelectionModel().selectedItemProperty().addListener(e->{
			ToolsBar.getInstance().getOut().println("getRealOrd/"+searchBox.getBox().getSelectionModel().getSelectedItem().split(" ")[0]);
			String[] info;
			try {
				info = ToolsBar.getInstance().getIn().readLine().split("/");
			} catch (IOException e4) {
				return;
			}
			StackPane grid=new StackPane();
			grid.setAlignment(Pos.CENTER);		
			
			Label ID=new Label("ID: ");
			Label customer=new Label("customer: ");
			Label delivery=new Label("delivery: ");
			ToolsBar.getInstance().getOut().println("calcOrderParams/"+info[0]+"/"+info[1].split(" ")[0]);
			String[] params;
			try {
				params = ToolsBar.getInstance().getIn().readLine().split("/");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				params=new String[2];
				params[0]=params[1]="0";
			}
			Label price=new Label("price: "+params[0]);
			Label waitingTime=new Label("waiting time: "+params[1]);
			TextField inputID=new TextField(String.valueOf(info[0]));
			ToolsBar.getInstance().getOut().println("getCusts");
			Collection<String> tmp;
			try {
				tmp = Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				tmp=new HashSet<>();
			}
			FilteredBox<String> custInput=new FilteredBox<>(tmp);
			ToolsBar.getInstance().getOut().println("getDels");
			try {
				tmp=Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				tmp=new HashSet<>();
			}
			FilteredBox<String> deliveryInput=new FilteredBox<>(tmp);
			ToolsBar.getInstance().getOut().println("getDishes");
			try {
				tmp=Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				tmp=new HashSet<>();
			}
			FilteredList<String> allDishes=new FilteredList<>(tmp);
			FilteredList<String> chosenDishes=new FilteredList<>(Arrays.asList(info[2].split(",")));
			Button add=new Button("add Dishes");
			Button remove=new Button("remove Dishes");
			Button button=new Button("edit");
			Label status=new Label();
			
			ID.setStyle("-fx-translate-x:-150;-fx-translate-y:-400;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			customer.setStyle("-fx-translate-x:-150;-fx-translate-y:-300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			delivery.setStyle("-fx-translate-x:-150;-fx-translate-y:-200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			price.setStyle("-fx-translate-x:0;-fx-translate-y:-100;-fx-min-width:400;-fx-max-width:400;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			waitingTime.setStyle("-fx-translate-x:0;-fx-translate-y:0;-fx-min-width:400;-fx-max-width:400;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			inputID.setStyle("-fx-translate-x:150;-fx-translate-y:-400;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			custInput.setStyle("-fx-translate-x:150;-fx-translate-y:-275;-fx-min-width:200;-fx-max-width:200;-fx-min-height:100;-fx-max-height:100;-fx-font-size:20;-fx-background-radius:15px");
			deliveryInput.setStyle("-fx-translate-x:150;-fx-translate-y:-175;-fx-min-width:200;-fx-max-width:200;-fx-min-height:100;-fx-max-height:100;-fx-font-size:20;-fx-background-radius:15px");	
			allDishes.setStyle("-fx-translate-x:-250;-fx-translate-y:300;-fx-min-width:250;-fx-max-width:250;-fx-min-height:300;-fx-max-height:500;-fx-font-size:20;-fx-background-radius:15px");
			chosenDishes.setStyle("-fx-translate-x:250;-fx-translate-y:300;-fx-min-width:250;-fx-max-width:250;-fx-min-height:300;-fx-max-height:500;-fx-font-size:20;-fx-background-radius:15px");
			add.setStyle("-fx-translate-x:0;-fx-translate-y:150;-fx-min-width:200;-fx-max-width:200;-fx-min-height:75;-fx-max-height:75;-fx-font-size:20;-fx-background-radius:15px");
			remove.setStyle("-fx-translate-x:0;-fx-translate-y:250;-fx-min-width:200;-fx-max-width:200;-fx-min-height:75;-fx-max-height:75;-fx-font-size:20;-fx-background-radius:15px");
			button.setStyle("-fx-translate-x:500;-fx-translate-y:-75;-fx-min-width:150;-fx-max-width:150;-fx-min-height:120;-fx-max-height:120;-fx-font-size:20;-fx-background-radius:15px");
			status.setStyle("-fx-translate-x:500;-fx-translate-y:75;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			searchBox.setStyle("-fx-translate-x:0;-fx-translate-y:-500;-fx-min-width:300;-fx-max-width:300;-fx-min-height:100;-fx-max-height:100;-fx-font-size:20;-fx-background-radius:15px");
			
			allDishes.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			chosenDishes.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			custInput.getBox().getSelectionModel().select(info[1]);
			deliveryInput.getBox().getSelectionModel().select(info[3]);
			
			add.setOnAction(eb->{
				chosenDishes.getList().getItems().addAll(allDishes.getList().getSelectionModel().getSelectedItems());
				ToolsBar.getInstance().getOut().println("calcOrderParams/"+info[0]+"/"+info[1].split(" ")[0]);
				String[] p;
				try {
					p=ToolsBar.getInstance().getIn().readLine().split("/");
				} catch (IOException e1) {
					p=new String[2];
					p[0]=p[1]="0";
				}
				price.setText("price: "+p[0]);
				waitingTime.setText("waiting time: "+p[1]);
			});
			remove.setOnAction(eb->{
				chosenDishes.getList().getItems().removeAll(chosenDishes.getList().getSelectionModel().getSelectedItems());
				ToolsBar.getInstance().getOut().println("calcOrderParams/"+info[0]+"/"+info[1].split(" ")[0]);
				String[] p;
				try {
					p=ToolsBar.getInstance().getIn().readLine().split("/");
				} catch (IOException e1) {
					p=new String[2];
					p[0]=p[1]="0";
				}
				price.setText("price: "+p[0]);
				waitingTime.setText("waiting time: "+p[1]);
			});
			button.setOnAction(eb->{
				try {
				String originalId=info[0];
				info[0]=inputID.getText();
				info[1]=custInput.getBox().getSelectionModel().getSelectedItem().split(" ")[0];
				HashSet<String> dishes=new HashSet<>();
				chosenDishes.getList().getItems().forEach(e2->dishes.add(e2.split(" ")[0]));
				info[2]=String.join(",", dishes);
				info[3]=deliveryInput.getBox().getSelectionModel().getSelectedItem().split(" ")[0];
				ToolsBar.getInstance().getOut().println("editOrd/"+originalId+"/"+String.join("/", info));
				String[] answer=ToolsBar.getInstance().getIn().readLine().split("/");
				if(Boolean.parseBoolean(answer[0])) {
					status.setText("Order "+originalId+" was eddited successfilly!");
					status.setTextFill(Color.GREEN);
				}
				else {
					if(answer.length==1) {
						status.setText("Order "+originalId+" was not eddited");
						status.setTextFill(Color.RED);
					}
					else {
						status.setText(answer[1]);
						status.setTextFill(Color.RED);
					}
				}
				
				}
				catch (IOException e3) {
					status.setText("please check your internet connection");
					status.setTextFill(Color.RED);
			}
			});
			grid.getChildren().addAll(searchBox,ID,customer,delivery,inputID,custInput,deliveryInput,allDishes,chosenDishes,add,remove,button,status,price,waitingTime);
			layout.setCenter(grid);
		});
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(searchBox);
	}

	//int id,Customer customer, ArrayList<Dish> dishes, Delivery delivery
	
	private void editDish() {
		ToolsBar.getInstance().getOut().println("getDishes");
		Collection<String> list;
		try {
			list=Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
		} catch (IOException e1) {
			list=new HashSet<>();
		}
		FilteredBox<String> searchBox=new FilteredBox<>(list);
		searchBox.setStyle("-fx-translate-x:0;-fx-translate-y:0;-fx-min-width:250;-fx-max-width:250;-fx-min-height:100;-fx-max-height:100;-fx-font-size:20;-fx-background-radius:15px");
		searchBox.getSearch().setPromptText("Search your dish here");
		searchBox.getBox().getSelectionModel().selectedItemProperty().addListener(e->{
			ToolsBar.getInstance().getOut().println("getRealDish/"+searchBox.getBox().getSelectionModel().getSelectedItem().split(" ")[0]);
			String[] info;
			try {
				info = ToolsBar.getInstance().getIn().readLine().split("-");
			} catch (IOException e4) {
				return;
			}
			StackPane grid=new StackPane();
			grid.setAlignment(Pos.CENTER);
			Button img=new Button();
			Label ID=new Label("ID:  ");
			Label name=new Label("Dish name:  ");
			Label type=new Label("Dish type:  ");
			Label time=new Label("Time to make:  ");
			Label price=new Label("price is: "+info[5]);
			Label colesterol=new Label("colesterol: "+info[7]);
			Label calories=new Label("calories: "+info[6]);
			Label natran=new Label("natran: "+info[8]);
			TextField inputID=new TextField(String.valueOf(info[0]));
			TextField inputName=new TextField(info[1]);
			TextField inputTime=new TextField(info[4]);
			ComboBox<DishType> inputType=new ComboBox<>();
			ToolsBar.getInstance().getOut().println("getComps");
			

			

			
			Collection<String> tmp;
			try {
				tmp = Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
			} catch (IOException e1) {
				tmp=new HashSet<>();
			}
			FilteredList<String> allComponenets=new FilteredList<>(tmp);
			FilteredList<String> chosenComponenets=new FilteredList<>(Arrays.asList(info[3].split(",")));
			Button add=new Button("add Componenets");
			Button remove=new Button("remove Componenets");
			Label status=new Label();
			Button button=new Button("edit");
			img.setOnAction(e4->{
				
				try {
					FileChooser fileChooser=new FileChooser();
					fileChooser.setTitle("Choose Picture");
					fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
					File selectedFile=fileChooser.showOpenDialog(new Stage());
					BufferedImage bi=ImageIO.read(selectedFile);
					ImageIO.write(bi, "png", new File(info[9]));
					info[9]="images/dish"+info[0]+".png";
					img.setStyle("-fx-translate-x:400;-fx-translate-y:-400;-fx-min-width:150;-fx-max-width:150;-fx-min-height:200;-fx-max-height:200;-fx-background-radius:15px;-fx-background-image:url(file:"+info[9]+");-fx-background-size: 150px 200px;-fx-background-repeat: no-repeat;-fx-background-position: center;");
					
				} catch (IOException e1) {	
					status.setText("invalid image path");
					status.setTextFill(Color.RED);
				}
				
			});
			
			try {
				BufferedImage ba=ImageIO.read(new File(info[9]));
				ImageIO.write(ba, "png", new File(info[9]));
			} catch (IOException e2) {
				status.setText("could not load the image");
				status.setTextFill(Color.RED);
			}
			
			img.setStyle("-fx-translate-x:400;-fx-translate-y:-400;-fx-min-width:150;-fx-max-width:150;-fx-min-height:200;-fx-max-height:200;-fx-background-radius:15px;-fx-background-image:url(file:"+info[9]+");-fx-background-size: 150px 200px;-fx-background-repeat: no-repeat;-fx-background-position: center;");
			ID.setStyle("-fx-translate-x:-150;-fx-translate-y:-400;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
			name.setStyle("-fx-translate-x:-150;-fx-translate-y:-300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
			type.setStyle("-fx-translate-x:-150;-fx-translate-y:-200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
			time.setStyle("-fx-translate-x:-150;-fx-translate-y:-100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
			price.setStyle("-fx-translate-x:-550;-fx-translate-y:-200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
			colesterol.setStyle("-fx-translate-x:-550;-fx-translate-y:-100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
			calories.setStyle("-fx-translate-x:-550;-fx-translate-y:-0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
			natran.setStyle("-fx-translate-x:-550;-fx-translate-y:100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
			inputID.setStyle("-fx-translate-x:150;-fx-translate-y:-400;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputName.setStyle("-fx-translate-x:150;-fx-translate-y:-300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputType.setStyle("-fx-translate-x:150;-fx-translate-y:-200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputTime.setStyle("-fx-translate-x:150;-fx-translate-y:-100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			allComponenets.setStyle("-fx-translate-x:-250;-fx-translate-y:200;-fx-min-width:250;-fx-max-width:250;-fx-min-height:400;-fx-max-height:400;-fx-font-size:20;-fx-background-radius:15px");
			chosenComponenets.setStyle("-fx-translate-x:250;-fx-translate-y:200;-fx-min-width:250;-fx-max-width:250;-fx-min-height:400;-fx-max-height:400;-fx-font-size:20;-fx-background-radius:15px");
			add.setStyle("-fx-translate-x:0;-fx-translate-y:150;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			remove.setStyle("-fx-translate-x:0;-fx-translate-y:200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			button.setStyle("-fx-translate-x:500;-fx-translate-y:-75;-fx-min-width:150;-fx-max-width:150;-fx-min-height:120;-fx-max-height:120;-fx-font-size:20;-fx-background-radius:15px");
			status.setStyle("-fx-translate-x:500;-fx-translate-y:75;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			searchBox.setStyle("-fx-translate-x:0;-fx-translate-y:-500;-fx-min-width:250;-fx-max-width:250;-fx-min-height:100;-fx-max-height:100;-fx-font-size:20;-fx-background-radius:15px");
			
			inputType.getItems().addAll(DishType.values());
			inputType.getSelectionModel().select(DishType.valueOf(info[2]));
			allComponenets.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			chosenComponenets.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			
			add.setOnAction(eb->{
				chosenComponenets.getList().getItems().addAll(allComponenets.getList().getSelectionModel().getSelectedItems());
			});
			remove.setOnAction(eb->{
				chosenComponenets.getList().getItems().removeAll(chosenComponenets.getList().getSelectionModel().getSelectedItems());
			});
			button.setOnAction(eb->{
				try {
					String originalId=info[0];
					info[0]=inputID.getText();
					info[1]=inputName.getText();
					info[2]=inputType.getSelectionModel().getSelectedItem().name();
					HashSet<String> set=new HashSet<>();
					chosenComponenets.getList().getItems().forEach(e3->set.add(e3.split(" ")[0]));
					info[3]=String.join(",", set);
					info[4]=inputTime.getText();
					info[9]=info[9].replace("/", "@");
					String[] p=Arrays.copyOfRange(info, 0, 5);
					ToolsBar.getInstance().getOut().println("editDish/"+originalId+"/"+String.join("/", p)+"/true/"+info[9]);
					String[] answer=ToolsBar.getInstance().getIn().readLine().split("/");
					if(Boolean.parseBoolean(answer[0])) {
						ToolsBar.getInstance().getOut().println("getRealDish/"+info[0]);
						String[] updatedArgs=ToolsBar.getInstance().getIn().readLine().split("-");
						price.setText("price is: "+updatedArgs[5]);
						colesterol.setText("colesterol: "+updatedArgs[7]);
						calories.setText("calories: "+updatedArgs[6]);
						natran.setText("natran: "+updatedArgs[8]);
						status.setText("Dish "+info[1]+" was eddited successfilly!");
						status.setTextFill(Color.GREEN);
					}
					else {
						if(answer.length==1) {
							status.setText("Dish "+info[1]+" was not eddited");
							status.setTextFill(Color.RED);
						}
						else {
							status.setText(answer[1]);
							status.setTextFill(Color.RED);
						}
					}
					}
				catch (IOException e3) {
					status.setText("please check your internet connection");
					status.setTextFill(Color.RED);
			}
			});
	
			grid.getChildren().addAll(searchBox,ID,name,time,type,inputID,inputName,inputTime,inputType,status,button,price,colesterol,calories,natran,allComponenets,chosenComponenets,add,remove,img);
			layout.setCenter(grid);
			
			
		});
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(searchBox);
	}


	//int id, String dishName, DishType type, ArrayList<Component> componenets, int timeToMake

	private void editComponent() {
		ToolsBar.getInstance().getOut().println("getComps");
		Collection<String> list;
		try {
			list=Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
		} catch (IOException e1) {
			list=new HashSet<>();
		}
		FilteredBox<String> searchBox=new FilteredBox<>(list);
		searchBox.setStyle("-fx-translate-x:0;-fx-translate-y:0;-fx-min-width:250;-fx-max-width:250;-fx-min-height:100;-fx-max-heigh:100;-fx-font-size:20;-fx-background-radius:15px");
		searchBox.getSearch().setPromptText("Search your component here");
		searchBox.getBox().getSelectionModel().selectedItemProperty().addListener(e->{
			
			ToolsBar.getInstance().getOut().println("getRealComp/"+searchBox.getBox().getSelectionModel().getSelectedItem().split(" ")[0]);
			String[] info;
			try {
				info = ToolsBar.getInstance().getIn().readLine().split("/");
			} catch (IOException e4) {
				return;
			}
			StackPane grid=new StackPane();
			grid.setAlignment(Pos.CENTER);
			
			Label id=new Label("ID:  ");
			Label name=new Label("component name:  ");
			Label lact=new Label("has a lactose?  ");
			Label glut=new Label("has a gluten?  ");
			Label price=new Label("price:  ");
			Label cal=new Label("calories:  ");
			Label col=new Label("colesterol:  ");
			Label ne=new Label("natran:  ");
			TextField inputID=new TextField(String.valueOf(info[0])) ;
			TextField inputName=new TextField(info[1]) ;
			TextField inputPrice=new TextField(String.valueOf(info[4])) ;
			TextField inputCal=new TextField(String.valueOf(info[5])) ;
			TextField inputCol=new TextField(String.valueOf(info[6])) ;
			TextField inputNe=new TextField(String.valueOf(info[7])) ;
			CheckBox inputGlut=new CheckBox();
			CheckBox inputLact=new CheckBox();
			Button button=new Button("edit");
			Label status=new Label();
			
			id.setStyle("-fx-translate-x:-150;-fx-translate-y:-400;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-heigh:50;-fx-font-size:20;");
			name.setStyle("-fx-translate-x:-150;-fx-translate-y:-300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-heigh:50;-fx-font-size:20;");
			price.setStyle("-fx-translate-x:-150;-fx-translate-y:-200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-heigh:50;-fx-font-size:20;");
			cal.setStyle("-fx-translate-x:-150;-fx-translate-y:-100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-heigh:50;-fx-font-size:20;");
			col.setStyle("-fx-translate-x:-150;-fx-translate-y:0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-heigh:50;-fx-font-size:20;");
			ne.setStyle("-fx-translate-x:-150;-fx-translate-y:100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-heigh:50;-fx-font-size:20;");
			lact.setStyle("-fx-translate-x:-150;-fx-translate-y:200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-heigh:50;-fx-font-size:20;");
			glut.setStyle("-fx-translate-x:-150;-fx-translate-y:300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-heigh:50;-fx-font-size:20;");
			inputID.setStyle("-fx-translate-x:150;-fx-translate-y:-400;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-heigh:50;-fx-font-size:20;-fx-background-radius:15px");
			inputName.setStyle("-fx-translate-x:150;-fx-translate-y:-300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-heigh:50;-fx-font-size:20;-fx-background-radius:15px");
			inputPrice.setStyle("-fx-translate-x:-150;-fx-translate-y:-200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-heigh:50;-fx-font-size:20;-fx-background-radius:15px");
			inputCal.setStyle("-fx-translate-x:150;-fx-translate-y:-100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-heigh:50;-fx-font-size:20;-fx-background-radius:15px");
			inputCol.setStyle("-fx-translate-x:150;-fx-translate-y:0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-heigh:50;-fx-font-size:20;-fx-background-radius:15px");
			inputNe.setStyle("-fx-translate-x:150;-fx-translate-y:100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-heigh:50;-fx-font-size:20;-fx-background-radius:15px");
			inputLact.setStyle("-fx-translate-x:150;-fx-translate-y:200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-heigh:50;-fx-font-size:20;-fx-background-radius:15px");
			inputGlut.setStyle("-fx-translate-x:150;-fx-translate-y:300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-heigh:50;-fx-font-size:20;-fx-background-radius:15px");
			button.setStyle("-fx-translate-x:450;-fx-translate-y:-75;-fx-min-width:150;-fx-max-width:150;-fx-min-height:120;-fx-max-heigh:120;-fx-font-size:20;-fx-background-radius:15px");
			status.setStyle("-fx-translate-x:550;-fx-translate-y:75;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-heigh:50;-fx-font-size:20;");
			searchBox.setStyle("-fx-translate-x:0;-fx-translate-y:-500;-fx-min-width:250;-fx-max-width:250;-fx-min-height:100;-fx-max-heigh:100;-fx-font-size:20;-fx-background-radius:15px");
			
			inputLact.setSelected(Boolean.parseBoolean(info[3]));
			inputGlut.setSelected(Boolean.parseBoolean(info[2]));
			
			button.setOnAction(eb->{
				try {
					String originalId=info[0];
					info[0]=inputID.getText();
					info[1]=inputName.getText();
					info[2]=String.valueOf(inputGlut.isSelected());
					info[3]=String.valueOf(inputLact.isSelected());
					info[4]=inputPrice.getText();
					info[5]=inputCal.getText();
					info[6]=inputCol.getText();
					info[7]=inputNe.getText();
					
					ToolsBar.getInstance().getOut().println("editComp/"+originalId+"/"+String.join("/", info));
					String[] answer=ToolsBar.getInstance().getIn().readLine().split("/");
					if(Boolean.parseBoolean(answer[0])) {
						status.setText("component was editted successfully!");
						status.setTextFill(Color.GREEN);
					}
					else {
						if(answer.length==1) {
							status.setText("component was not eddited");
							status.setTextFill(Color.RED);
						}
						else {
							status.setText(answer[1]);
							status.setTextFill(Color.RED);
						}
					}
					

				}
				catch (IOException e3) {
					status.setText("please check your internet connection");
					status.setTextFill(Color.RED);
			}
			});
			
			grid.getChildren().addAll(searchBox,id,name,price,col,cal,ne,glut,lact,inputID,inputName,inputPrice,inputCal,inputCol,inputNe,inputGlut,inputLact,button,status);
			layout.setCenter(grid);
			
		});
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(searchBox);
	}
	
	//int id, String componentName, boolean hasLactose, boolean hasGluten, double price, List<Double> healveProperties


	public void editCustomer() {
		ToolsBar.getInstance().getOut().println("getCusts");
		Collection<String> list;
		try {
			list=Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
		} catch (IOException e1) {
			list=new HashSet<>();
		}
		FilteredBox<String> searchBox=new FilteredBox<>(list);
		searchBox.setStyle("-fx-translate-x:0;-fx-translate-y:0;-fx-min-width:250;-fx-max-width:250;-fx-min-height:100;-fx-max-heigh:100;-fx-font-size:20;-fx-background-radius:15px");
		searchBox.getSearch().setPromptText("Search your customer here");
		searchBox.getBox().getSelectionModel().selectedItemProperty().addListener(e->{
			ToolsBar.getInstance().getOut().println("getRealCust/"+searchBox.getBox().getSelectionModel().getSelectedItem().split(" ")[0]);
			String[] info;
			try {
				info = ToolsBar.getInstance().getIn().readLine().split("!");
				System.out.println(info[12]);
			} catch (IOException e4) {
				return;
			}
			StackPane stack=new StackPane();
			stack.setAlignment(Pos.CENTER);
			
			Label id=new Label("id: ");
			Label firstName=new Label("first name: ");
			Label lastName=new Label("last name: ");
			Label birth=new Label("birthday: ");
			Label gender=new Label("gender: ");
			
			Label neighberhood=new Label("neighberhood: ");
			Label email=new Label("email address: ");
			Label phone=new Label("phone number: ");
			Label username=new Label("username: ");
			Label password=new Label("password: ");
			Label lactose=new Label("sensitive to lactose? ");
			Label gluten=new Label("sensitive to gluten?");
			
			TextField inputid=new TextField(String.valueOf(info[0]));
			TextField inputfirstName=new TextField(info[1]);
			TextField inputlastName=new TextField(info[2]);
			String[] ld=info[3].split("-");
			DatePicker inputbirth=new DatePicker(LocalDate.of(Integer.parseInt(ld[0]), Integer.parseInt(ld[1]), Integer.parseInt(ld[2])));
			FilteredBox<Gender> inputgender=new FilteredBox<>(Arrays.asList(Gender.values()));
			FilteredBox<Neighberhood> inputneighberhood=new FilteredBox<>(Arrays.asList(Neighberhood.values()));
			
			TextField inputemail=new TextField(info[8]);
			TextField inputphone=new TextField(info[9]);
			TextField inputusername=new TextField(info[10]);
			PasswordField inputPassword=new PasswordField();
			CheckBox inputlact=new CheckBox();
			CheckBox inputglut=new CheckBox();
			
			Button img=new Button();
			
			Button edit=new Button("edit");
			Label status=new Label();
			
			id.setStyle("-fx-translate-x:-550;-fx-translate-y:-300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			firstName.setStyle("-fx-translate-x:-550;-fx-translate-y:-200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			lastName.setStyle("-fx-translate-x:-550;-fx-translate-y:-100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			birth.setStyle("-fx-translate-x:-550;-fx-translate-y:0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			gender.setStyle("-fx-translate-x:-550;-fx-translate-y:100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			neighberhood.setStyle("-fx-translate-x:50;-fx-translate-y:-400;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			email.setStyle("-fx-translate-x:50;-fx-translate-y:-300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			phone.setStyle("-fx-translate-x:50;-fx-translate-y:-200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			username.setStyle("-fx-translate-x:50;-fx-translate-y:-100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			password.setStyle("-fx-translate-x:50;-fx-translate-y:0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			lactose.setStyle("-fx-translate-x:50;-fx-translate-y:100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			gluten.setStyle("-fx-translate-x:50;-fx-translate-y:200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			inputid.setStyle("-fx-translate-x:-250;-fx-translate-y:-300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputfirstName.setStyle("-fx-translate-x:-250;-fx-translate-y:-200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputlastName.setStyle("-fx-translate-x:-250;-fx-translate-y:-100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputbirth.setStyle("-fx-translate-x:-250;-fx-translate-y:0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputgender.setStyle("-fx-translate-x:-250;-fx-translate-y:100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputneighberhood.setStyle("-fx-translate-x:350;-fx-translate-y:-375;-fx-min-width:200;-fx-max-width:200;-fx-min-height:100;-fx-max-height:100;-fx-font-size:20;-fx-background-radius:15px");
			inputemail.setStyle("-fx-translate-x:350;-fx-translate-y:-300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputphone.setStyle("-fx-translate-x:350;-fx-translate-y:-200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputusername.setStyle("-fx-translate-x:350;-fx-translate-y:-100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputPassword.setStyle("-fx-translate-x:350;-fx-translate-y:0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputlact.setStyle("-fx-translate-x:350;-fx-translate-y:100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputglut.setStyle("-fx-translate-x:350;-fx-translate-y:200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			edit.setStyle("-fx-translate-x:600;-fx-translate-y:-75;-fx-min-width:150;-fx-max-width:150;-fx-min-height:120;-fx-max-height:120;-fx-font-size:20;-fx-background-radius:15px");
			status.setStyle("-fx-translate-x:600;-fx-translate-y:75;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			searchBox.setStyle("-fx-translate-x:-0;-fx-translate-y:-500;-fx-min-width:250;-fx-max-width:250;-fx-min-height:100;-fx-max-height:100;-fx-font-size:20;-fx-background-radius:15px");
			img.setStyle("-fx-translate-x:600;-fx-translate-y:-500;-fx-min-width:100;-fx-max-width:100;-fx-min-height:150;-fx-max-height:150;-fx-background-radius:15px;-fx-background-image:url(file:"+info[12]+");-fx-background-size: 150px 200px;-fx-background-repeat: no-repeat;-fx-background-position: center;");
			
			edit.setOnAction(eb->{
				try {
					String originalId=info[0];
					info[0]=inputid.getText();
					info[1]=inputfirstName.getText();
					info[2]=inputlastName.getText();
					info[3]=inputbirth.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE);
					info[4]=inputgender.getBox().getSelectionModel().getSelectedItem().name();
					info[5]=inputneighberhood.getBox().getSelectionModel().getSelectedItem().name();
					info[6]=String.valueOf(inputglut.isSelected());
					info[7]=String.valueOf(inputlact.isSelected());
					info[8]=inputemail.getText();
					info[9]=inputphone.getText();
					info[10]=inputusername.getText();
					if(!inputPassword.getText().isBlank()) {
						info[11]=inputPassword.getText();
					}
					info[12]=info[12].replace("/", "@");
					ToolsBar.getInstance().getOut().println("editCust/"+originalId+"/"+String.join("/", info));
					String[] answer=ToolsBar.getInstance().getIn().readLine().split("/");
					if(Boolean.parseBoolean(answer[0])) {
						status.setText("customer was editted successfully!");
						status.setTextFill(Color.GREEN);
					}
					else {
						if(answer.length==1) {
							status.setText("customer was not eddited");
							status.setTextFill(Color.RED);
						}
						else {
							status.setText(answer[1]);
							status.setTextFill(Color.RED);
						}
					}
					

				}
				catch (IOException e3) {
					status.setText("please check your internet connection");
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
					info[12]="images/customer"+info[0]+".png";
					ImageIO.write(bi, "png", new File(info[12]));
					img.getCssMetaData().remove(8);
					img.setStyle("-fx-translate-x:600;-fx-translate-y:-500;-fx-min-width:150;-fx-max-width:150;-fx-min-height:200;-fx-max-height:200;-fx-background-radius:15px;-fx-background-image:url(file:"+info[12]+");-fx-background-size: 150px 200px;-fx-background-repeat: no-repeat;-fx-background-position: center;");
					
				} catch (IOException e1) {	
					status.setText("invalid image path");
					status.setTextFill(Color.RED);
				}
			});
			if(!info[12].equals("images/customer"+info[0]+".png")) {
				inputgender.getBox().getSelectionModel().selectedItemProperty().addListener(e4->{
					if(inputgender.getBox().getSelectionModel().getSelectedItem().equals(Gender.Male)) {
						info[12]="images/manDefault.png";
						try {
							File selectedFile=new File("images/manDefault.png");
							BufferedImage bi=ImageIO.read(selectedFile);
							ImageIO.write(bi, "png", new File("images/customer"+info[0]+".png"));
							img.setStyle("-fx-translate-x:600;-fx-translate-y:-500;-fx-min-width:150;-fx-max-width:150;-fx-min-height:200;-fx-max-height:200;-fx-background-radius:15px;-fx-background-image:url(file:"+info[12]+");-fx-background-size: 150px 200px;-fx-background-repeat: no-repeat;-fx-background-position: center;");
							
						} catch (IOException e1) {	
							status.setText("invalid image path");
							status.setTextFill(Color.RED);
						}
					}
					else {
						info[12]="images/womanDefault.jpg";
						try {
							File selectedFile=new File(info[12]);
							BufferedImage bi=ImageIO.read(selectedFile);
							ImageIO.write(bi, "png", new File("images/customer"+info[0]+".png"));
							img.setStyle("-fx-translate-x:600;-fx-translate-y:-500;-fx-min-width:150;-fx-max-width:150;-fx-min-height:200;-fx-max-height:200;-fx-background-radius:15px;-fx-background-image:url(file:"+info[12]+");-fx-background-size: 150px 200px;-fx-background-repeat: no-repeat;-fx-background-position: center;");
							
						} catch (IOException e1) {	
							status.setText("invalid image path");
							status.setTextFill(Color.RED);
						}
					}
				});
			}
			
			inputgender.getBox().getSelectionModel().select(Gender.valueOf(info[4]));
			inputneighberhood.getBox().getSelectionModel().select(Neighberhood.valueOf(info[5]));
			inputlact.setSelected(Boolean.parseBoolean(info[7]));
			inputglut.setSelected(Boolean.parseBoolean(info[6]));
			
			stack.getChildren().addAll(id,firstName,lastName,birth,gender,neighberhood,email,phone,username,password,lactose,gluten,inputid,inputfirstName,inputlastName,inputbirth,inputgender,inputneighberhood,inputemail,inputphone,inputusername,inputPassword,inputlact,inputglut,edit,status,searchBox,img);
			layout.setCenter(stack);

		});
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(searchBox);

	}

//int id,String firstName, String lastName, LocalDate birthDay, Gender gender, Neighberhood neighberhood,	boolean isSensitiveToLactose, boolean isSensitiveToGluten, String email,String phone
	private void editDeliveryPerson() {
		ToolsBar.getInstance().getOut().println("getDeps");
		Collection<String> list;
		try {
			list=Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
		} catch (IOException e1) {
			list=new HashSet<>();
		}
		FilteredBox<String> searchBox=new FilteredBox<>(list);
		searchBox.setStyle("-fx-translate-x:0;-fx-translate-y:0;-fx-min-width:250;-fx-max-width:250;-fx-min-height:100;-fx-max-heigh:100;-fx-font-size:20;-fx-background-radius:15px");
		searchBox.getSearch().setPromptText("Search your delperson here");
		searchBox.getBox().getSelectionModel().selectedItemProperty().addListener(e->{
			ToolsBar.getInstance().getOut().println("getRealDep/"+searchBox.getBox().getSelectionModel().getSelectedItem().split(" ")[0]);
			String[] info;
			try {
				info = ToolsBar.getInstance().getIn().readLine().split("/");
			} catch (IOException e4) {
				return;
			}
			StackPane stack=new StackPane();
			stack.setAlignment(Pos.CENTER);
			
			
			Label id=new Label("id: ");
			Label firstName=new Label("first name: ");
			Label lastName=new Label("last name: ");
			Label birth=new Label("birthday: ");
			Label gender=new Label("gender: ");
			
			Label vehicle=new Label("vehicle: ");
			Label email=new Label("email address: ");
			Label phone=new Label("phone number: ");
			Label area=new Label("area: ");
			
			TextField inputid=new TextField(info[0]);
			TextField inputfirstName=new TextField(info[1]);
			TextField inputlastName=new TextField(info[2]);
			String[] ld=info[3].split("-");
			DatePicker inputbirth=new DatePicker(LocalDate.of(Integer.parseInt(ld[0]), Integer.parseInt(ld[1]), Integer.parseInt(ld[2])));
			FilteredBox<Gender> inputgender=new FilteredBox<>(Gender.values());
			
			ComboBox<Vehicle> inputvehicle=new ComboBox<>();
			TextField inputemail=new TextField(info[7]);
			TextField inputphone=new TextField(info[8]);
			ToolsBar.getInstance().getOut().println("getAreas");
			Collection<String> tmp;
			try {
				tmp = Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				tmp=new HashSet<>();
			}
			FilteredBox<String> inputArea=new FilteredBox<>(tmp);
			
			Button edit=new Button("edit");
			Label status=new Label();
			
			id.setStyle("-fx-translate-x:-550;-fx-translate-y:-300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			firstName.setStyle("-fx-translate-x:-550;-fx-translate-y:-200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			lastName.setStyle("-fx-translate-x:-550;-fx-translate-y:-100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			birth.setStyle("-fx-translate-x:-550;-fx-translate-y:0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			gender.setStyle("-fx-translate-x:-550;-fx-translate-y:100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			vehicle.setStyle("-fx-translate-x:50;-fx-translate-y:-300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			email.setStyle("-fx-translate-x:50;-fx-translate-y:-200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			phone.setStyle("-fx-translate-x:50;-fx-translate-y:-100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			area.setStyle("-fx-translate-x:50;-fx-translate-y:0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			inputid.setStyle("-fx-translate-x:-250;-fx-translate-y:-300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputfirstName.setStyle("-fx-translate-x:-250;-fx-translate-y:-200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputlastName.setStyle("-fx-translate-x:-250;-fx-translate-y:-100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputbirth.setStyle("-fx-translate-x:-250;-fx-translate-y:0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputgender.setStyle("-fx-translate-x:-250;-fx-translate-y:100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputvehicle.setStyle("-fx-translate-x:350;-fx-translate-y:-300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputemail.setStyle("-fx-translate-x:350;-fx-translate-y:-200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputphone.setStyle("-fx-translate-x:350;-fx-translate-y:-100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputArea.setStyle("-fx-translate-x:350;-fx-translate-y:0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			edit.setStyle("-fx-translate-x:600;-fx-translate-y:-75;-fx-min-width:150;-fx-max-width:150;-fx-min-height:120;-fx-max-height:120;-fx-font-size:20;-fx-background-radius:15px");
			status.setStyle("-fx-translate-x:600;-fx-translate-y:75;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			searchBox.setStyle("-fx-translate-x:-0;-fx-translate-y:-500;-fx-min-width:250;-fx-max-width:250;-fx-min-height:100;-fx-max-height:100;-fx-font-size:20;-fx-background-radius:15px");
			
			edit.setOnAction(eb->{
				try {
					String originalId=info[0];
					info[0]=inputid.getText();
					info[1]=inputfirstName.getText();
					info[2]=inputlastName.getText();
					info[3]=inputbirth.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE);
					info[4]=inputgender.getBox().getSelectionModel().getSelectedItem().name();
					info[5]=inputvehicle.getSelectionModel().getSelectedItem().name();
					info[6]=inputArea.getBox().getSelectionModel().getSelectedItem().split(" ")[0];
					info[7]=inputemail.getText();
					info[8]=inputphone.getText();
					
					ToolsBar.getInstance().getOut().println("editDp/"+originalId+"/"+String.join("/", info));
					String[] answer=ToolsBar.getInstance().getIn().readLine().split("/");
					if(Boolean.parseBoolean(answer[0])) {
						status.setText("delivery person was editted successfully!");
						status.setTextFill(Color.GREEN);
					}
					else {
						if(answer.length==1) {
							status.setText("delivery person was not eddited");
							status.setTextFill(Color.RED);
						}
						else {
							status.setText(answer[1]);
							status.setTextFill(Color.RED);
						}
					}
				}
				catch (IOException e3) {
					status.setText("please check your internet connection");
					status.setTextFill(Color.RED);
			}
			});
			

			inputgender.getBox().getSelectionModel().select(Gender.valueOf(info[4]));
			inputvehicle.getItems().addAll(Arrays.asList(Vehicle.values()));
			inputvehicle.getSelectionModel().select(Vehicle.valueOf(info[5]));
			inputArea.getBox().getSelectionModel().select(info[6]);
			stack.getChildren().addAll(id,firstName,lastName,birth,gender,inputArea,email,phone,inputvehicle,area,vehicle,inputid,inputfirstName,inputlastName,inputbirth,inputgender,inputemail,inputphone,edit,status,searchBox);
			layout.setCenter(stack);
		});
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(searchBox);
	}
//int id,String firstName, String lastName, LocalDate birthDay, Gender gender, Vehicle vehicle,	DeliveryArea area, String email, String phone

	private void editCook() {
		ToolsBar.getInstance().getOut().println("getCooks");
		Collection<String> list;
		try {
			list=Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
		} catch (IOException e1) {
			list=new HashSet<>();
		}
		FilteredBox<String> searchBox=new FilteredBox<>(list);
		searchBox.setStyle("-fx-translate-x:0;-fx-translate-y:0;-fx-min-width:250;-fx-max-width:250;-fx-min-height:100;-fx-max-heigh:100;-fx-font-size:20;-fx-background-radius:15px");
		searchBox.getSearch().setPromptText("Search your cook here");
		searchBox.getBox().getSelectionModel().selectedItemProperty().addListener(e->{
			ToolsBar.getInstance().getOut().println("getRealCook/"+searchBox.getBox().getSelectionModel().getSelectedItem().split(" ")[0]);
			String[] info;
			try {
				info = ToolsBar.getInstance().getIn().readLine().split("/");
			} catch (IOException e4) {
				return;
			}
			StackPane stack=new StackPane();
			stack.setAlignment(Pos.CENTER);
			
			
			Label id=new Label("id: ");
			Label firstName=new Label("first name: ");
			Label lastName=new Label("last name: ");
			Label birth=new Label("birthday: ");
			Label gender=new Label("gender: ");
			
			Label expertise=new Label("expertise: ");
			Label email=new Label("email address: ");
			Label phone=new Label("phone number: ");
			Label chef=new Label("is shef? ");
			
			TextField inputid=new TextField(info[0]);
			TextField inputfirstName=new TextField(info[1]);
			TextField inputlastName=new TextField(info[2]);
			String[] ld=info[3].split("-");
			DatePicker inputbirth=new DatePicker(LocalDate.of(Integer.parseInt(ld[0]), Integer.parseInt(ld[1]), Integer.parseInt(ld[2])));
			FilteredBox<Gender> inputgender=new FilteredBox<>(Gender.values());
			
			ComboBox<Expertise> inputexpertise=new ComboBox<>();
			CheckBox inputchef=new CheckBox();
			TextField inputemail=new TextField(info[7]);
			TextField inputphone=new TextField(info[8]);
			
			
			Button edit=new Button("edit");
			Label status=new Label();
			
			id.setStyle("-fx-translate-x:-550;-fx-translate-y:-300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			firstName.setStyle("-fx-translate-x:-550;-fx-translate-y:-200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			lastName.setStyle("-fx-translate-x:-550;-fx-translate-y:-100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			birth.setStyle("-fx-translate-x:-550;-fx-translate-y:0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			gender.setStyle("-fx-translate-x:-550;-fx-translate-y:100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			expertise.setStyle("-fx-translate-x:50;-fx-translate-y:-300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			email.setStyle("-fx-translate-x:50;-fx-translate-y:-200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			phone.setStyle("-fx-translate-x:50;-fx-translate-y:-100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			chef.setStyle("-fx-translate-x:50;-fx-translate-y:0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			inputid.setStyle("-fx-translate-x:-250;-fx-translate-y:-300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputfirstName.setStyle("-fx-translate-x:-250;-fx-translate-y:-200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputlastName.setStyle("-fx-translate-x:-250;-fx-translate-y:-100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputbirth.setStyle("-fx-translate-x:-250;-fx-translate-y:0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputgender.setStyle("-fx-translate-x:-250;-fx-translate-y:100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputexpertise.setStyle("-fx-translate-x:350;-fx-translate-y:-300;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputemail.setStyle("-fx-translate-x:350;-fx-translate-y:-200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputphone.setStyle("-fx-translate-x:350;-fx-translate-y:-100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");
			inputchef.setStyle("-fx-translate-x:350;-fx-translate-y:0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px");	
			edit.setStyle("-fx-translate-x:600;-fx-translate-y:-75;-fx-min-width:150;-fx-max-width:150;-fx-min-height:120;-fx-max-height:120;-fx-font-size:20;-fx-background-radius:15px");
			status.setStyle("-fx-translate-x:600;-fx-translate-y:75;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
			searchBox.setStyle("-fx-translate-x:-0;-fx-translate-y:-500;-fx-min-width:250;-fx-max-width:250;-fx-min-height:100;-fx-max-height:100;-fx-font-size:20;-fx-background-radius:15px");
			
			edit.setOnAction(eb->{
				try {
					String originalId=info[0];
					info[0]=inputid.getText();
					info[1]=inputfirstName.getText();
					info[2]=inputlastName.getText();
					info[3]=inputbirth.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE);
					info[4]=inputgender.getBox().getSelectionModel().getSelectedItem().name();
					info[5]=inputexpertise.getSelectionModel().getSelectedItem().name();
					info[6]=String.valueOf(inputchef.isSelected());
					info[7]=inputemail.getText();
					info[8]=inputphone.getText();
					
					ToolsBar.getInstance().getOut().println("editCook/"+originalId+"/"+String.join("/", info));
					String[] answer=ToolsBar.getInstance().getIn().readLine().split("/");
					if(Boolean.parseBoolean(answer[0])) {
						status.setText("cook was editted successfully!");
						status.setTextFill(Color.GREEN);
					}
					else {
						if(answer.length==1) {
							status.setText("cook person was not eddited");
							status.setTextFill(Color.RED);
						}
						else {
							status.setText(answer[1]);
							status.setTextFill(Color.RED);
						}
					}
				}
				catch (IOException e3) {
					status.setText("please check your internet connection");
					status.setTextFill(Color.RED);
			}
			});
			
			inputgender.getBox().getSelectionModel().select(Gender.valueOf(info[4]));
			inputexpertise.getItems().addAll(Expertise.values());
			inputexpertise.getSelectionModel().select(Expertise.valueOf(info[5]));
			inputchef.setSelected(Boolean.parseBoolean(info[6]));
			stack.getChildren().addAll(id,firstName,lastName,birth,gender,inputchef,email,phone,inputexpertise,expertise,chef,inputid,inputfirstName,inputlastName,inputbirth,inputgender,inputemail,inputphone,edit,status,searchBox);

			layout.setCenter(stack);

		});
		
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(searchBox);
	}
//int id,String firstName, String lastName, LocalDate birthDay, Gender gender, Expertise expert,boolean isChef, String email, String phone 
}
