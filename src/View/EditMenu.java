package View;

/*this is the edit menu form. all edit method are method of this class.
 * the edit function action is simple - first user choose the object you want to edit frim filtered box, and the user get layout with its details.
 * for insert your changes to the data base user need click on "edit".
 * unlike new function, the edit function dont use constractors, but set/add/remove of internal objects.
 * before insert the changes, the program trigered all fields to throw exceptions if they are unlegel.
 * if exception throwed, the changes will not insert and the user pop-up window with exception message.**/



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;


import Exceptions.*;
import Utils.DishType;
import Utils.Expertise;
import Utils.Gender;
import Utils.Neighberhood;
import Utils.Vehicle;
import View.Nodes.EmailField;
import View.Nodes.FilteredBox;
import View.Nodes.FilteredList;
import View.Nodes.IndComboBox;
import View.Nodes.IndDatePicker;
import View.Nodes.IndNumField;
import View.Nodes.IndPasswordField;
import View.Nodes.IndTextField;
import View.Nodes.IntegerField;
import View.Nodes.PersonIdField;
import View.Nodes.PhoneField;
import View.Nodes.ToolsBar;
import application.App;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

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

	private double w;
	private double h;
	private double s;
	


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


	public EditMenu() {
		w=App.getW();
		h=App.getH();
		s=App.getS();
		ToolsBar.getInstance().getPlace().pushText("Edit menu");
		this.layout=App.getLayout();

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
		
		editCook.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px",-200*w,-100*h,300*w,300*w,100*h,100*h,25*s));
		editDeliveryPerson.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px",-200*w,50*h,300*w,300*w,100*h,100*h,25*s));
		editCustomer.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px",-200*w,200*h,300*w,300*w,100*h,100*h,25*s));
		editComponent.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px",-200*w,-250*h,300*w,300*w,100*h,100*h,25*s));
		editDish.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px",200*w,-100*h,300*w,300*w,100*h,100*h,25*s));
		editOrder.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px",200*w,50*h,300*w,300*w,100*h,100*h,25*s));
		editDelivery.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px",200*w,200*h,300*w,300*w,100*h,100*h,25*s));
		editArea.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px",200*w,-250*h,300*w,300*w,100*h,100*h,25*s));

		
		this.getChildren().addAll(editCook,editDeliveryPerson,editCustomer,editComponent,editDish,editOrder,editDelivery,editArea);
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(this);
		
	}


	private void editArea() {
		ToolsBar.getInstance().getPlace().pushText("Edit area");
		ToolsBar.getInstance().getOut().println("getAreas");
		Collection<String> list;
		try {
			list=Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
		} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {
			PopUp.display(e1.getMessage(), false);
			list=new HashSet<>();
		}
		FilteredBox<String> searchBox=new FilteredBox<>(list);
		searchBox.getSearch().setPromptText("Search your area here");
		searchBox.getBox().getSelectionModel().selectedItemProperty().addListener(e->{
				ToolsBar.getInstance().getOut().println("getRealArea/"+searchBox.getBox().getSelectionModel().getSelectedItem().split(" ")[0]);

				String[] info;
				try {
					info = ToolsBar.getInstance().getIn().checkedReadLine().split("/");
				} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e4) {
					PopUp.display(e4.getMessage(), false);
					return;
				}
				StackPane stack=new StackPane();
				stack.setAlignment(Pos.CENTER);
				Collection<String> tmp;
				
				Label id=new Label("Area id: ");
				Label name=new Label("Area name: ");
				Label time=new Label("Time to get: ");
				
				IntegerField inputid=new IntegerField(info[0]);
				IndTextField inputName=new IndTextField(info[1]);
				IntegerField inputTime=new IntegerField(info[5]);
				
				ToolsBar.getInstance().getOut().println("getDeps");
				try {
					tmp=Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
				} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e4) {
					// TODO Auto-generated catch block
					tmp=new HashSet<>();
				}
				FilteredList<String> deps=new FilteredList<>(tmp);
				FilteredList<String> inputDeps=new FilteredList<>(Arrays.asList(info[2].split(",")));
				Button addDp=new Button("add");
				Button remDp=new Button("remove");
				deps.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				deps.removeAll(inputDeps.getList().getItems());
				inputDeps.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				addDp.setOnAction(e2->{
					inputDeps.addAll(deps.getList().getSelectionModel().getSelectedItems());
					deps.removeAll(deps.getList().getSelectionModel().getSelectedItems());
				});
				remDp.setOnAction(e2->{
					deps.addAll(inputDeps.getList().getSelectionModel().getSelectedItems());
					inputDeps.removeAll(inputDeps.getList().getSelectionModel().getSelectedItems());
				});
				
				
				ToolsBar.getInstance().getOut().println("getDels");
				try {
					tmp=Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
				} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e4) {
					PopUp.display(e4.getMessage(), false);
					tmp=new HashSet<>();
				}
				FilteredList<String> dels=new FilteredList<>(tmp);
				FilteredList<String> inputDels=new FilteredList<>(Arrays.asList(info[3].split(",")));
				Button addDl=new Button("add");
				Button remDl=new Button("remove");
				dels.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				dels.removeAll(inputDels.getList().getItems());
				inputDels.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				addDl.setOnAction(e2->{
					inputDels.addAll(dels.getList().getSelectionModel().getSelectedItems());
					dels.removeAll(dels.getList().getSelectionModel().getSelectedItems());
				});
				remDl.setOnAction(e2->{
					dels.addAll(inputDels.getList().getSelectionModel().getSelectedItems());
					inputDels.removeAll(inputDels.getList().getSelectionModel().getSelectedItems());
				});
				

				FilteredList<Neighberhood> nebs=new FilteredList<>(Arrays.asList(Neighberhood.values()));
				HashSet<Neighberhood> n=new HashSet<>();
				Arrays.asList(info[4].split(",")).forEach(e1->n.add(Neighberhood.valueOf(e1)));
				FilteredList<Neighberhood> inputNebs=new FilteredList<>(n);
				Button addNb=new Button("add");
				Button remNb=new Button("remove");
				nebs.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				inputNebs.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				nebs.removeAll(inputNebs.getList().getItems());
				addNb.setOnAction(e2->{
					inputNebs.addAll(nebs.getList().getSelectionModel().getSelectedItems());
					nebs.removeAll(nebs.getList().getSelectionModel().getSelectedItems());
				});
				remNb.setOnAction(e2->{
					nebs.addAll(inputNebs.getList().getSelectionModel().getSelectedItems());
					inputNebs.removeAll(inputNebs.getList().getSelectionModel().getSelectedItems());
				});
				Button edit=new Button("edit");
				
				Label depLabel=new Label("delivery persons:");
				Label delLabel=new Label("deliveries:");
				Label nebLabel=new Label("neighberhoods:");
				
				
				id.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f",-550*w,-300*h,150*w,150*w,50*h,50*h,14*s));
				name.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f",-150*w,-300*h,150*w,150*w,50*h,50*h,14*s));
				time.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f",250*w,-300*h,150*w,150*w,50*h,50*h,14*s));
				inputid.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-400*w,-300*h,150*w,150*w,50*h,50*h,14*s));
				inputName.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,-300*h,150*w,150*w,50*h,50*h,14*s));
				inputTime.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",400*w,-300*h,150*w,150*w,50*h,50*h,14*s));
				
				deps.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-500*w,-100*h,150*w,250*w,200*h,200*h,14*s));
				inputDeps.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-500*w,150*h,150*w,250*w,200*h,200*h,14*s));
				addDp.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-575*w,300*h,100*w,100*w,50*h,50*h,14*s));
				remDp.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-425*w,300*h,100*w,100*w,50*h,50*h,14*s));
				depLabel.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f",-500*w,-250*h,150*w,150*w,50*h,50*h,14*s));
				
				dels.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-100*w,-100*h,150*w,250*w,200*h,200*h,14*s));
				inputDels.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-100*w,150*h,150*w,250*w,200*h,200*h,14*s));
				addDl.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-175*w,300*h,100*w,100*w,50*h,50*h,14*s));
				remDl.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-25*w,300*h,100*w,100*w,50*h,50*h,14*s));
				delLabel.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f",-100*w,-250*h,150*w,150*w,50*h,50*h,14*s));
				
				nebs.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",300*w,-100*h,150*w,250*w,200*h,200*h,14*s));
				inputNebs.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",300*w,150*h,150*w,250*w,200*h,200*h,14*s));
				addNb.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",225*w,300*h,100*w,100*w,50*h,50*h,14*s));
				remNb.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",375*w,300*h,100*w,100*w,50*h,50*h,14*s));
				nebLabel.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f",300*w,-250*h,150*w,150*w,50*h,50*h,14*s));
				
				edit.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",550*w,-75*h,150*w,150*w,120*h,120*h,20*s));
				searchBox.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-200*w,-400*h,250*w,250*w,100*h,100*h,20*s));
				
				
				edit.setOnAction(e2->{
					HashSet<String> set=new HashSet<>();
					try {
						if(inputNebs.getList().getItems().isEmpty()) {
							throw new BlankFieldException();
						}
						inputid.isLegal();
						inputName.isLegal();
						inputTime.isLegal();

						String originalId=info[0];	
						info[0]=inputid.getText();
						info[1]=inputName.getText();
						info[5]=inputTime.getText();

						deps.getList().getItems().forEach(e3->set.add(e3.split(" ")[0]));
						info[2]=String.join(",", set);
						set.clear();
						dels.getList().getItems().forEach(e3->set.add(e3.split(" ")[1]));
						info[3]=String.join(",", set);
						info[4]="";
						for(Neighberhood s:inputNebs.getList().getItems()) {
							info[4]+=","+s.name();
						}
						info[4]=info[4].substring(1);
						ToolsBar.getInstance().getOut().println("editArea/"+originalId+"/"+String.join("/",info));
						String[] answer = ToolsBar.getInstance().getIn().checkedReadLine().split("/");
						if(Boolean.parseBoolean(answer[0])) {
							PopUp.display(String.format("Area %s was editted succesfully", info[1]),true);
						}
						else {
							if(answer.length==1) {
								PopUp.display(String.format("Area %s was not editted", info[1]),false);
							}
							else {
								PopUp.display(answer[1],false);
							}
						}
					}  catch (BlankFieldException | IllegalCharacterException | LieException | ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException | NotNumberException e4) {
						PopUp.display(e4.getMessage(),false);
					} 
				});
				stack.getChildren().addAll(id,name,time,inputid,inputName,inputTime,deps,inputDeps,addDp,remDp,dels,inputDels,addDl,remDl,nebs,inputNebs,addNb,remNb,edit,searchBox,delLabel,depLabel,nebLabel);
				layout.setCenter(stack);
	});
		searchBox.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,0*h,250*w,250*w,100*h,100*h,20*s));
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
		ToolsBar.getInstance().getPlace().pushText("Edit delivery");

		ToolsBar.getInstance().getOut().println("getDels");
		Collection<String> list;
		try {
			list=Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
		} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {
			PopUp.display(e1.getMessage(), false);
			list=new HashSet<>();
		}
		FilteredBox<String> searchBox=new FilteredBox<>(list);
		searchBox.getSearch().setPromptText("Search your delivery here");
		searchBox.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-500*w,0*h,250*w,250*w,100*h,100*h,20*s));
		searchBox.getBox().getSelectionModel().selectedItemProperty().addListener(e->{
			ToolsBar.getInstance().getOut().println("getRealDel/"+searchBox.getBox().getSelectionModel().getSelectedItem().split(" ")[1]);
			String[] info;
			try {
				info = ToolsBar.getInstance().getIn().checkedReadLine().split("/");
			} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e4) {
				PopUp.display(e4.getMessage(), false);
				return;
			}
			StackPane grid=new StackPane();
			grid.setAlignment(Pos.CENTER);
			
			Label ID=new Label("ID: ");
			Label delPerson=new Label("Delivery person: ");
			Label area=new Label("Delivery area: ");
			Label delivered=new Label("Delivered? ");
			Label date=new Label("Delivery date: ");
			
			IntegerField inputID=new IntegerField(info[1]);
			ToolsBar.getInstance().getOut().println("getRealArea/"+info[4].split(" ")[0]);
			Collection<String> tmp;
			try {
				tmp=Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split("/")[2].split(","));
			} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e2) {
				PopUp.display(e2.getMessage(), false);
				tmp=new HashSet<>();
			}
			FilteredBox<String> inputDelPerson=new FilteredBox<>(tmp);
			ToolsBar.getInstance().getOut().println("getAreas");
			try {
				tmp=Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
			} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e2) {
				PopUp.display(e2.getMessage(), false);
				tmp=new HashSet<>();
			}
			FilteredBox<String> inputArea=new FilteredBox<>(tmp);
			CheckBox inputDelivered=new CheckBox();
			DatePicker inputDate=new DatePicker();
			inputDate.valueProperty().addListener(e1->{
				try {
				if(inputDate.getValue().isBefore(LocalDate.now())||inputDate.getValue()==null) {
					throw new ToLateException();
				}
				App.setBorderColor(inputDate, "GREEN");
				}
				catch(ToLateException e2) {
					App.setBorderColor(inputDate, "RED");
				}
			});
			Button button=new Button("edit");
			
			inputDelPerson.getBox().getSelectionModel().select(info[3]);
			inputArea.getBox().getSelectionModel().select(info[4]);
			inputDelivered.setSelected(Boolean.parseBoolean(info[6]));
			String[] ld=info[5].split("-");
			inputDate.setValue(LocalDate.of(Integer.parseInt(ld[0]),Integer.parseInt(ld[1]),Integer.parseInt(ld[2])));
			
			ID.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,-400*h,200*w,200*w,50*h,50*h,20*s));
			delPerson.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,-300*h,200*w,200*w,50*h,50*h,20*s));
			area.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,-200*h,200*w,200*w,50*h,50*h,20*s));
			delivered.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,-100*h,200*w,200*w,50*h,50*h,20*s));
			date.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,0*h,200*w,200*w,50*h,50*h,20*s));
			
			inputID.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,-400*h,200*w,200*w,50*h,50*h,20*s));
			inputDelPerson.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,-275*h,200*w,200*w,100*h,100*h,20*s));
			inputArea.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,-175*h,200*w,200*w,100*h,100*h,20*s));
			inputDelivered.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,-100*h,200*w,200*w,50*h,50*h,20*s));
			inputDate.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,0*h,200*w,200*w,50*h,50*h,20*s));
			
			button.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",500*w,25*h,150*w,150*w,120*h,120*h,20*s));
			
			
			
			
			grid.getChildren().addAll(searchBox,ID,delPerson,area,delivered,date,inputID,inputDelPerson,inputArea,inputDelivered,inputDate,button);
			if(info[0].equals("reg")) {

				ToolsBar.getInstance().getOut().println("getOrds");
				Collection<String> ords;
				try {
					ords=Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
				} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {
					PopUp.display(e1.getMessage(), false);
					ords=new HashSet<>();
				}
				FilteredList<String> allOrders=new FilteredList<>(ords);
				FilteredList<String> chosenOrders=new FilteredList<>(Arrays.asList(info[2].split(",")));
				Button add=new Button("add orders");
				Button remove=new Button("remove Orders");
				
				allOrders.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-250*w,300*h,250*w,250*w,400*h,400*h,20*s));
				chosenOrders.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",250*w,300*h,250*w,250*w,400*h,400*h,20*s));
				add.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,150*h,200*w,200*w,75*h,75*h,20*s));
				remove.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,250*h,200*w,200*w,75*h,75*h,20*s));
				
				allOrders.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				chosenOrders.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				
				allOrders.removeAll(chosenOrders.getList().getItems());
				add.setOnAction(eb->{
					chosenOrders.addAll(allOrders.getList().getSelectionModel().getSelectedItems());
					allOrders.removeAll(allOrders.getList().getSelectionModel().getSelectedItems());
				});
				remove.setOnAction(eb->{
					allOrders.addAll(chosenOrders.getList().getSelectionModel().getSelectedItems());
					chosenOrders.removeAll(chosenOrders.getList().getSelectionModel().getSelectedItems());
				});
				
				
				grid.getChildren().clear();
				grid.getChildren().addAll(searchBox,ID,delPerson,area,delivered,date,inputID,inputDelPerson,inputArea,inputDelivered,inputDate,button,allOrders,chosenOrders,add,remove);
				button.setOnAction(eb->{
					try {
					if(chosenOrders.getList().getItems().isEmpty()) {
						throw new BlankFieldException();
					}
					if(inputDate.getValue().isBefore(LocalDate.now())||inputDate.getValue()==null) {
						throw new ToLateException();
					}
					inputID.isLegal();
					inputArea.isLegal();
					
					
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
					String[] answer=ToolsBar.getInstance().getIn().checkedReadLine().split("/");
					if(Boolean.parseBoolean(answer[0])) {
						PopUp.display(String.format("Delivery %s was editted succesfully", newInfo[0]),true);
					}
					else {
						if(answer.length==1) {
							PopUp.display(String.format("Delivery %s was not editted", newInfo[0]),false);
						}
						else {
							PopUp.display(answer[1],false);
						}
						}
					}
					catch (BlankFieldException | IllegalCharacterException | LieException | ToLateException | ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException | NotNumberException e1) {
						PopUp.display(e1.getMessage(),false);
					} 
				});
				ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
				layout.setCenter(grid);
			}
			else {
				
				Label postage=new Label("Postage: ");
				Label order=new Label("order: ");
				IndNumField inputPostage=new IndNumField(info[7]);
				ToolsBar.getInstance().getOut().println("getOrds");
				Collection<String> ords;
				try {
					ords=Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
				} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {
					PopUp.display(e1.getMessage(), false);
					ords=new HashSet<>();
				}
				FilteredBox<String> inputOrder=new FilteredBox<>(ords);
				
				inputOrder.getBox().getSelectionModel().select(info[2]);
				
				postage.setStyle(String.format("-fx-translate-x:-150;-fx-translate-y:100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20",-150*w,100*h,200*w,200*w,50*h,50*h,20*s));
				order.setStyle(String.format("-fx-translate-x:-150;-fx-translate-y:200;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20",-150*w,200*h,200*w,200*w,50*h,50*h,20*s));
				inputPostage.checkStyle(String.format("-fx-translate-x:150;-fx-translate-y:100;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-background-radius:15px",150*w,100*h,200*w,200*w,50*h,50*h,20*s));
				inputOrder.setStyle(String.format("-fx-translate-x:150;-fx-translate-y:175;-fx-min-width:200;-fx-max-width:200;-fx-min-height:100;-fx-max-height:100;-fx-font-size:20;-fx-background-radius:15px",150*w,175*h,200*w,200*w,100*h,100*h,20*s));
				
				grid.getChildren().clear();
				grid.getChildren().addAll(searchBox,ID,delPerson,area,delivered,date,inputID,inputDelPerson,inputArea,inputDelivered,inputDate,button,order,postage,inputOrder,inputPostage);
				
				button.setOnAction(eb->{
					try {
						if(inputDate.getValue().isBefore(LocalDate.now())||inputDate.getValue()==null) {
							throw new ToLateException();
						}
						
						inputID.isLegal();
						inputPostage.isLegal();
						inputArea.isLegal();
						inputOrder.isLegal();
						
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
						String[] answer=ToolsBar.getInstance().getIn().checkedReadLine().split("/");
						if(Boolean.parseBoolean(answer[0])) {
							PopUp.display(String.format("Delivery %s was editted succesfully", newInfo[0]),true);
						}
						else {
							if(answer.length==1) {
								PopUp.display(String.format("Delivery %s was not editted", newInfo[0]),false);
							}
							else{
								PopUp.display(answer[1],false);
							}
						}
					}
					catch (BlankFieldException | IllegalCharacterException | LieException | ToLateException | ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException | NotNumberException e1) {
					PopUp.display(e1.getMessage(),false);
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
		ToolsBar.getInstance().getPlace().pushText("Edit order");
		ToolsBar.getInstance().getOut().println("getOrds");
		Collection<String> list;
		try {
			list=Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
		} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {
			PopUp.display(e1.getMessage(), false);
			list=new HashSet<>();
		}
		FilteredBox<String> searchBox=new FilteredBox<>(list);
		searchBox.getSearch().setPromptText("Search your order here");
		searchBox.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,0*h,250*w,250*w,100*h,100*h,20*s));
		searchBox.getBox().getSelectionModel().selectedItemProperty().addListener(e->{
			ToolsBar.getInstance().getOut().println("getRealOrd/"+searchBox.getBox().getSelectionModel().getSelectedItem().split(" ")[0]);
			String[] info;
			try {
				info = ToolsBar.getInstance().getIn().checkedReadLine().split("/");
			} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e4) {
				PopUp.display(e4.getMessage(), false);
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
				params = ToolsBar.getInstance().getIn().checkedReadLine().split("/");
			} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {
				PopUp.display(e1.getMessage(), false);
				params=new String[2];
				params[0]=params[1]="0";
			}
			Label price=new Label("price: "+params[0]);
			Label waitingTime=new Label("waiting time: "+params[1]);
			Label status=new Label(info[4]);
			IntegerField inputID=new IntegerField(String.valueOf(info[0]));
			ToolsBar.getInstance().getOut().println("getCusts");
			Collection<String> tmp;
			try {
				tmp = Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
			} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {
				tmp=new HashSet<>();
			}
			FilteredBox<String> custInput=new FilteredBox<>(tmp);
			ToolsBar.getInstance().getOut().println("getDels");
			try {
				tmp=Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
			} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {
				tmp=new HashSet<>();
			}
			FilteredBox<String> deliveryInput=new FilteredBox<>(tmp);
			ToolsBar.getInstance().getOut().println("getDishes");
			try {
				tmp=Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
			} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {
				tmp=new HashSet<>();
			}
			FilteredList<String> allDishes=new FilteredList<>(tmp);
			FilteredList<String> chosenDishes=new FilteredList<>(Arrays.asList(info[2].split(",")));
			Button add=new Button("add Dishes");
			Button remove=new Button("remove Dishes");
			Button button=new Button("edit");
			
			ID.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,-300*h,200*w,200*w,50*h,50*h,20*s));
			customer.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,-200*h,200*w,200*w,50*h,50*h,20*s));
			delivery.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,-100*h,200*w,200*w,50*h,50*h,20*s));
			price.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-500*w,-300*h,400*w,400*w,50*h,50*h,20*s));
			waitingTime.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-500*w,-200*h,400*w,400*w,50*h,50*h,20*s));
			status.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-500*w,-100*h,400*w,400*w,50*h,50*h,20*s));
			
			
			inputID.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,-300*h,200*w,200*w,50*h,50*h,20*s));
			custInput.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,-200*h,200*w,200*w,50*h,50*h,20*s));
			deliveryInput.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,-100*h,200*w,200*w,50*h,50*h,20*s));	
			
			allDishes.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-250*w,200*h,250*w,250*w,300*h,300*h,20*s));
			chosenDishes.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",250*w,200*h,250*w,250*w,300*h,300*h,20*s));
			add.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,150*h,200*w,200*w,75*h,75*h,20*s));
			remove.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,250*h,200*w,200*w,75*h,75*h,20*s));
			button.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",500*w,-75*h,150*w,150*w,120*h,120*h,20*s));
			searchBox.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-650*w,0*h,300*w,300*w,100*h,100*h,20*s));
			
			allDishes.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			chosenDishes.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			custInput.getBox().getSelectionModel().select(info[1]);
			deliveryInput.getBox().getSelectionModel().select(info[3]);
			
			add.setOnAction(eb->{
				chosenDishes.addAll(allDishes.getList().getSelectionModel().getSelectedItems());
				ToolsBar.getInstance().getOut().println("calcOrderParams/"+info[0]+"/"+info[1].split(" ")[0]);
				String[] p;
				try {
					p=ToolsBar.getInstance().getIn().checkedReadLine().split("/");
				} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {
					p=new String[2];
					p[0]=p[1]="0";
				}
				price.setText("price: "+p[0]);
				waitingTime.setText("waiting time: "+p[1]);
			});
			remove.setOnAction(eb->{
				chosenDishes.removeAll(chosenDishes.getList().getSelectionModel().getSelectedItems());
				ToolsBar.getInstance().getOut().println("calcOrderParams/"+info[0]+"/"+info[1].split(" ")[0]);
				String[] p;
				try {
					p=ToolsBar.getInstance().getIn().checkedReadLine().split("/");
				} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {
					p=new String[2];
					p[0]=p[1]="0";
				}
				price.setText("price: "+p[0]);
				waitingTime.setText("waiting time: "+p[1]);
			});
			button.setOnAction(eb->{
				try {
				if(chosenDishes.getList().getItems().isEmpty()) {
					throw new BlankFieldException();
				}

				inputID.isLegal();
				custInput.isLegal();
				String originalId=info[0];
				info[0]=inputID.getText();
				info[1]=custInput.getBox().getSelectionModel().getSelectedItem().split(" ")[0];
				HashSet<String> dishes=new HashSet<>();
				chosenDishes.getList().getItems().forEach(e2->dishes.add(e2.split(" ")[0]));
				info[2]=String.join(",", dishes);
				if(!deliveryInput.getBox().getSelectionModel().getSelectedItem().equals("null")) {
					info[3]=deliveryInput.getBox().getSelectionModel().getSelectedItem().split(" ")[1];
				}
				ToolsBar.getInstance().getOut().println("editOrd/"+originalId+"/"+String.join("/", info));
				String[] answer=ToolsBar.getInstance().getIn().checkedReadLine().split("/");
				if(Boolean.parseBoolean(answer[0])) {
					PopUp.display("Order "+originalId+" was eddited successfilly!",true);
				}
				else {
					if(answer.length==1) {
						PopUp.display("Order "+originalId+" was not eddited",false);
					}
					else {
						PopUp.display(answer[1],false);
					}
				}
				
				}
				 catch (BlankFieldException | IllegalCharacterException | LieException | ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException | NotNumberException e1) {
					// TODO Auto-generated catch block
				PopUp.display(e1.getMessage(),false);
				}
			});
			grid.getChildren().addAll(searchBox,ID,customer,delivery,inputID,custInput,deliveryInput,allDishes,chosenDishes,add,remove,button,price,waitingTime,status);
			layout.setCenter(grid);
		});
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(searchBox);
	}

	//int id,Customer customer, ArrayList<Dish> dishes, Delivery delivery
	
	private void editDish() {
		ToolsBar.getInstance().getPlace().pushText("Edit dish");
		ToolsBar.getInstance().getOut().println("getDishes");
		Collection<String> list;
		try {
			list=Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
		} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {
			PopUp.display(e1.getMessage(), false);
			list=new HashSet<>();
		}
		FilteredBox<String> searchBox=new FilteredBox<>(list);
		searchBox.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,0*h,250*w,250*w,100*h,100*h,20*s));
		searchBox.getSearch().setPromptText("Search your dish here");
		searchBox.getBox().getSelectionModel().selectedItemProperty().addListener(e->{
			ToolsBar.getInstance().getOut().println("getRealDish/"+searchBox.getBox().getSelectionModel().getSelectedItem().split(" ")[0]);
			String[] info;
			try {
				info = ToolsBar.getInstance().getIn().checkedReadLine().split("-");
			} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e4) {
				PopUp.display(e4.getMessage(), false);
				return;
			}
			StackPane grid=new StackPane();
			grid.setAlignment(Pos.CENTER);
			Label ID=new Label("ID:  ");
			Label name=new Label("Dish name:  ");
			Label type=new Label("Dish type:  ");
			Label time=new Label("Time to make:  ");
			Label price=new Label("price is: "+info[5]);
			Label colesterol=new Label("colesterol: "+info[7]);
			Label calories=new Label("calories: "+info[6]);
			Label natran=new Label("natran: "+info[8]);
			IntegerField inputID=new IntegerField(String.valueOf(info[0]));
			IndTextField inputName=new IndTextField(info[1]);
			IntegerField inputTime=new IntegerField(info[4]);
			IndComboBox<DishType> inputType=new IndComboBox<>();
			ToolsBar.getInstance().getOut().println("getComps");
			

			

			
			Collection<String> tmp;
			try {
				tmp = Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
			} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {
				PopUp.display(e1.getMessage(), false);
				tmp=new HashSet<>();
			}
			FilteredList<String> allComponenets=new FilteredList<>(tmp);
			FilteredList<String> chosenComponenets=new FilteredList<>(Arrays.asList(info[3].split(",")));
			Button add=new Button("add Componenets");
			Button remove=new Button("remove Componenets");
			Button button=new Button("edit");

			
			ID.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,-300*h,200*w,200*w,50*h,50*h,20*s));
			name.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,-200*h,200*w,200*w,50*h,50*h,20*s));
			type.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,-100*h,200*w,200*w,50*h,50*h,20*s));
			time.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,0*h,200*w,200*w,50*h,50*h,20*s));
			price.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-550*w,-200*h,200*w,200*w,50*h,50*h,20*s));
			colesterol.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-550*w,-100*h,200*w,200*w,50*h,50*h,20*s));
			calories.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-550*w,0*h,200*w,200*w,50*h,50*h,20*s));
			natran.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-550*w,100*h,200*w,200*w,50*h,50*h,20*s));
			
			inputID.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,-300*h,200*w,200*w,50*h,50*h,20*s));
			inputName.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,-200*h,200*w,200*w,50*h,50*h,20*s));
			inputType.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,-100*h,200*w,200*w,50*h,50*h,20*s));
			inputTime.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,0*h,200*w,200*w,50*h,50*h,20*s));
			
			allComponenets.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-250*w,200*h,150*w,150*w,250*h,250*h,14*s));
			chosenComponenets.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",250*w,200*h,150*w,150*w,250*h,250*h,14*s));
			add.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,150*h,200*w,200*w,50*h,50*h,14*s));
			remove.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,200*h,200*w,200*w,50*h,50*h,14*s));
			
			button.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",500*w,-75*h,150*w,150*w,120*h,120*h,20*s));
			searchBox.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,-400*h,250*w,250*w,100*h,100*h,20*s));
			
			inputType.getItems().addAll(DishType.values());
			inputType.getSelectionModel().select(DishType.valueOf(info[2]));
			allComponenets.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			chosenComponenets.getList().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			
			add.setOnAction(eb->{
				chosenComponenets.addAll(allComponenets.getList().getSelectionModel().getSelectedItems());
			});
			remove.setOnAction(eb->{
				chosenComponenets.removeAll(chosenComponenets.getList().getSelectionModel().getSelectedItems());
			});
			button.setOnAction(eb->{
				try {
					if(inputType.getSelectionModel().isEmpty()||chosenComponenets.getList().getItems().isEmpty()) {
						throw new BlankFieldException();
					}
					inputID.isLegal();
					inputName.isLegal();
					inputTime.isLegal();
					inputType.isLegal();
					
					String originalId=info[0];
					info[0]=inputID.getText();
					info[1]=inputName.getText();
					info[2]=inputType.getSelectionModel().getSelectedItem().name();
					HashSet<String> set=new HashSet<>();
					chosenComponenets.getList().getItems().forEach(e3->set.add(e3.split(" ")[0]));
					info[3]=String.join(",", set);
					info[4]=inputTime.getText();
					String[] p=Arrays.copyOfRange(info, 0, 5);
					ToolsBar.getInstance().getOut().println("editDish/"+originalId+"/"+String.join("/", p)+"/true/");
					String[] answer=ToolsBar.getInstance().getIn().checkedReadLine().split("/");
					if(Boolean.parseBoolean(answer[0])) {
						ToolsBar.getInstance().getOut().println("getRealDish/"+info[0]);
						String[] updatedArgs=ToolsBar.getInstance().getIn().checkedReadLine().split("-");
						price.setText("price is: "+updatedArgs[5]);
						colesterol.setText("colesterol: "+updatedArgs[7]);
						calories.setText("calories: "+updatedArgs[6]);
						natran.setText("natran: "+updatedArgs[8]);
						PopUp.display("Dish "+info[1]+" was eddited successfilly!",true);
					}
					else {
						if(answer.length==1) {
							PopUp.display("Dish "+info[1]+" was not eddited",false);
						}
						else {
							PopUp.display(answer[1],false);
						}
					}
					}
				catch (BlankFieldException |IllegalCharacterException | LieException | ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException | NotNumberException  e1) {
				PopUp.display(e1.getMessage(),false);
				} 
			});
	
			grid.getChildren().addAll(searchBox,ID,name,time,type,inputID,inputName,inputTime,inputType,button,price,colesterol,calories,natran,allComponenets,chosenComponenets,add,remove);
			layout.setCenter(grid);
			
			
		});
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(searchBox);
	}


	//int id, String dishName, DishType type, ArrayList<Component> componenets, int timeToMake

	private void editComponent() {
		ToolsBar.getInstance().getPlace().pushText("Edit component");
		ToolsBar.getInstance().getOut().println("getComps");
		Collection<String> list;
		try {
			list=Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
		} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {
			PopUp.display(e1.getMessage(), false);
			list=new HashSet<>();
		}
		FilteredBox<String> searchBox=new FilteredBox<>(list);
		searchBox.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,0*h,250*w,250*w,100*h,100*h,20*s));
		searchBox.getSearch().setPromptText("Search your component here");
		searchBox.getBox().getSelectionModel().selectedItemProperty().addListener(e->{
			
			ToolsBar.getInstance().getOut().println("getRealComp/"+searchBox.getBox().getSelectionModel().getSelectedItem().split(" ")[0]);
			String[] info;
			try {
				info = ToolsBar.getInstance().getIn().checkedReadLine().split("/");
			} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e4) {
				PopUp.display(e4.getMessage(), false);
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
			IntegerField inputID=new IntegerField(String.valueOf(info[0])) ;
			IndTextField inputName=new IndTextField(info[1]) ;
			IndNumField inputPrice=new IndNumField(String.valueOf(info[4])) ;
			IndNumField inputCal=new IndNumField(String.valueOf(info[5])) ;
			IndNumField inputCol=new IndNumField(String.valueOf(info[6])) ;
			IndNumField inputNe=new IndNumField(String.valueOf(info[7])) ;
			CheckBox inputGlut=new CheckBox();
			CheckBox inputLact=new CheckBox();
			Button button=new Button("edit");

			
			id.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,-400*h,200*w,200*w,50*h,50*h,20*s));
			name.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,-300*h,200*w,200*w,50*h,50*h,20*s));
			price.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,-200*h,200*w,200*w,50*h,50*h,20*s));
			cal.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,-100*h,200*w,200*w,50*h,50*h,20*s));
			col.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,0*h,200*w,200*w,50*h,50*h,20*s));
			ne.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,100*h,200*w,200*w,50*h,50*h,20*s));
			lact.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,200*h,200*w,200*w,50*h,50*h,20*s));
			glut.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-150*w,300*h,200*w,200*w,50*h,50*h,20*s));
			
			inputID.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,-400*h,200*w,200*w,50*h,50*h,20*s));
			inputName.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,-300*h,200*w,200*w,50*h,50*h,20*s));
			inputPrice.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,-200*h,200*w,200*w,50*h,50*h,20*s));
			inputCal.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,-100*h,200*w,200*w,50*h,50*h,20*s));
			inputCol.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,0*h,200*w,200*w,50*h,50*h,20*s));
			inputNe.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,100*h,200*w,200*w,50*h,50*h,20*s));
			inputLact.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,200*h,200*w,200*w,50*h,50*h,20*s));
			inputGlut.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",150*w,300*h,200*w,200*w,50*h,50*h,20*s));
			
			button.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",450*w,-75*h,150*w,150*w,120*h,120*h,20*s));
			searchBox.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-450*w,-350*h,250*w,250*w,100*h,100*h,20*s));
			
			inputLact.setSelected(Boolean.parseBoolean(info[3]));
			inputGlut.setSelected(Boolean.parseBoolean(info[2]));
			
			button.setOnAction(eb->{
				try {
					
					inputID.isLegal();
					inputName.isLegal();
					inputPrice.isLegal();
					inputCal.isLegal();
					inputCol.isLegal();
					inputNe.isLegal();
					
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
					String[] answer=ToolsBar.getInstance().getIn().checkedReadLine().split("/");
					if(Boolean.parseBoolean(answer[0])) {
						PopUp.display("component was editted successfully!",true);
					}
					else {
						if(answer.length==1) {
							PopUp.display("component was not eddited",false);
						}
						else {
							PopUp.display(answer[1],false);
						}
					}
					

				}
				catch (BlankFieldException |IllegalCharacterException| LieException | ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException | NotNumberException  e1) {
				PopUp.display(e1.getMessage(),false);
				}
			});
			
			grid.getChildren().addAll(searchBox,id,name,price,col,cal,ne,glut,lact,inputID,inputName,inputPrice,inputCal,inputCol,inputNe,inputGlut,inputLact,button);
			layout.setCenter(grid);
			
		});
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(searchBox);
	}
	
	//int id, String componentName, boolean hasLactose, boolean hasGluten, double price, List<Double> healveProperties


	public void editCustomer(){
		ToolsBar.getInstance().getPlace().pushText("Edit customer");
		ToolsBar.getInstance().getOut().println("getCusts");
		Collection<String> list;
		try {
			list=Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
		} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {
			PopUp.display(e1.getMessage(), false);
			list=new HashSet<>();
		}
		FilteredBox<String> searchBox=new FilteredBox<>(list);
		searchBox.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,0*h,250*w,250*w,100*h,100*h,20*s));
		searchBox.getSearch().setPromptText("Search your customer here");
		searchBox.getBox().getSelectionModel().selectedItemProperty().addListener(e->{
			customerForm(searchBox.getBox().getSelectionModel().getSelectedItem().split(" ")[0]);
		});
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(searchBox);

	}

	public static void customerForm(String Custid) {
		double w=App.getW();
		double h=App.getH();
		double s=App.getS();
		
		
		ToolsBar.getInstance().getOut().println("getRealCust/"+Custid);
		String[] info;
		try {
			info = ToolsBar.getInstance().getIn().checkedReadLine().split("!");
		} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e4) {
			PopUp.display(e4.getMessage(), false);
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
		
		PersonIdField inputid=new PersonIdField(String.valueOf(info[0]));
		IndTextField inputfirstName=new IndTextField(info[1]);
		IndTextField inputlastName=new IndTextField(info[2]);
		String[] ld=info[3].split("-");
		IndDatePicker inputbirth=new IndDatePicker(LocalDate.of(Integer.parseInt(ld[0]), Integer.parseInt(ld[1]), Integer.parseInt(ld[2])));
		FilteredBox<Gender> inputgender=new FilteredBox<>(Arrays.asList(Gender.values()));
		FilteredBox<Neighberhood> inputneighberhood=new FilteredBox<>(Arrays.asList(Neighberhood.values()));
		
		EmailField inputemail=new EmailField(info[8]);
		PhoneField inputphone=new PhoneField(info[9]);
		IndTextField inputusername=new IndTextField(info[10]);
		IndPasswordField inputPassword=new IndPasswordField();
		CheckBox inputlact=new CheckBox();
		CheckBox inputglut=new CheckBox();
		
		Button edit=new Button("edit");
		
		id.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-550*w,-200*h,200*w,200*w,50*h,50*h,20*s));
		firstName.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-550*w,-100*h,200*w,200*w,50*h,50*h,20*s));
		lastName.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-550*w,0*h,200*w,200*w,50*h,50*h,20*s));
		birth.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-550*w,100*h,200*w,200*w,50*h,50*h,20*s));
		gender.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-550*w,200*h,200*w,200*w,50*h,50*h,20*s));
		neighberhood.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",50*w,-300*h,200*w,200*w,50*h,50*h,20*s));
		email.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",50*w,-200*h,200*w,200*w,50*h,50*h,20*s));
		phone.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",50*w,-100*h,200*w,200*w,50*h,50*h,20*s));
		username.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",50*w,0*h,200*w,200*w,50*h,50*h,20*s));
		password.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",50*w,100*h,200*w,200*w,50*h,50*h,20*s));
		lactose.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",50*w,200*h,200*w,200*w,50*h,50*h,20*s));
		gluten.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",50*w,300*h,200*w,200*w,50*h,50*h,20*s));
		
		inputid.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-250*w,-200*h,200*w,200*w,50*h,50*h,20*s));
		inputfirstName.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-250*w,-100*h,200*w,200*w,50*h,50*h,20*s));
		inputlastName.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-250*w,0*h,200*w,200*w,50*h,50*h,20*s));
		inputbirth.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-250*w,100*h,200*w,200*w,50*h,50*h,20*s));
		inputgender.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-250*w,200*h,200*w,200*w,50*h,50*h,20*s));
		inputneighberhood.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",350*w,-325*h,200*w,200*w,50*h,50*h,20*s));
		inputemail.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",350*w,-200*h,200*w,200*w,50*h,50*h,20*s));
		inputphone.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",350*w,-100*h,200*w,200*w,50*h,50*h,20*s));
		inputusername.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",350*w,0*h,200*w,200*w,50*h,50*h,20*s));
		inputPassword.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",350*w,100*h,200*w,200*w,50*h,50*h,20*s));
		inputlact.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",350*w,200*h,200*w,200*w,50*h,50*h,20*s));
		inputglut.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",350*w,300*h,200*w,200*w,50*h,50*h,20*s));
		
		edit.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",600*w,-75*h,150*w,150*w,120*h,120*h,20*s));
	
		edit.setOnAction(eb->{
			try {

				inputfirstName.isLegal();
				inputlastName.isLegal();
				inputemail.isLegalMail();
				inputphone.isLegalPhone();
				inputusername.isLegal();
				inputid.isLegalID();
				inputbirth.isLegal();
				inputgender.isLegal();
				inputneighberhood.isLegal();
				
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
					inputPassword.isLegal();
					info[11]=inputPassword.getText();
				}
				ToolsBar.getInstance().getOut().println("editCust/"+originalId+"/"+String.join("/", info));
				String[] answer=ToolsBar.getInstance().getIn().checkedReadLine().split("/");
				if(Boolean.parseBoolean(answer[0])) {
					PopUp.display("customer was editted successfully!",true);
				}
				else {
					if(answer.length==1) {
						PopUp.display("customer was not eddited",false);
					}
					else {
						PopUp.display(answer[1],false);
					}
				}

			}
			catch (BlankFieldException | IllegalCharacterException | LieException | InvalidIDException | InvalidPhoneException |LowPasswordException| InvalidEmailException | ToYoungException | ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException | ToLateException | NumberFormatException | NotNumberException e1) {
			PopUp.display(e1.getMessage(),false);
			}
		});
		
		inputgender.getBox().getSelectionModel().select(Gender.valueOf(info[4]));
		inputneighberhood.getBox().getSelectionModel().select(Neighberhood.valueOf(info[5]));
		inputlact.setSelected(Boolean.parseBoolean(info[7]));
		inputglut.setSelected(Boolean.parseBoolean(info[6]));
		
		stack.getChildren().addAll(id,firstName,lastName,birth,gender,neighberhood,email,phone,username,password,lactose,gluten,inputid,inputfirstName,inputlastName,inputbirth,inputgender,inputneighberhood,inputemail,inputphone,inputusername,inputPassword,inputlact,inputglut,edit);
		ToolsBar.getInstance().getLastStacks().push(App.getLayout().getCenter());
		App.getLayout().setCenter(stack);

	}
//int id,String firstName, String lastName, LocalDate birthDay, Gender gender, Neighberhood neighberhood,	boolean isSensitiveToLactose, boolean isSensitiveToGluten, String email,String phone
	private void editDeliveryPerson() {
		ToolsBar.getInstance().getPlace().pushText("Edit delivery person");
		ToolsBar.getInstance().getOut().println("getDeps");
		Collection<String> list;
		try {
			list=Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
		} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {
			PopUp.display(e1.getMessage(), false);
			list=new HashSet<>();
		}
		FilteredBox<String> searchBox=new FilteredBox<>(list);
		searchBox.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,0*h,250*w,250*w,100*h,100*h,20*s));
		searchBox.getSearch().setPromptText("Search your delperson here");
		searchBox.getBox().getSelectionModel().selectedItemProperty().addListener(e->{
			ToolsBar.getInstance().getOut().println("getRealDep/"+searchBox.getBox().getSelectionModel().getSelectedItem().split(" ")[0]);
			String[] info;
			try {
				info = ToolsBar.getInstance().getIn().checkedReadLine().split("/");
			} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e4) {
				PopUp.display(e4.getMessage(), false);
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
			
			PersonIdField inputid=new PersonIdField(info[0]);
			IndTextField inputfirstName=new IndTextField(info[1]);
			IndTextField inputlastName=new IndTextField(info[2]);
			String[] ld=info[3].split("-");
			IndDatePicker inputbirth=new IndDatePicker(LocalDate.of(Integer.parseInt(ld[0]), Integer.parseInt(ld[1]), Integer.parseInt(ld[2])));
			FilteredBox<Gender> inputgender=new FilteredBox<>(Gender.values());
			
			IndComboBox<Vehicle> inputvehicle=new IndComboBox<>();
			EmailField inputemail=new EmailField(info[7]);
			PhoneField inputphone=new PhoneField(info[8]);
			ToolsBar.getInstance().getOut().println("getAreas");
			Collection<String> tmp;
			try {
				tmp = Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
			} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {
				PopUp.display(e1.getMessage(), false);
				tmp=new HashSet<>();
			}
			FilteredBox<String> inputArea=new FilteredBox<>(tmp);
			
			Button edit=new Button("edit");
			
			id.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-550*w,-200*h,200*w,200*w,50*h,50*h,20*s));
			firstName.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-550*w,-100*h,200*w,200*w,50*h,50*h,20*s));
			lastName.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-550*w,0*h,200*w,200*w,50*h,50*h,20*s));
			birth.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-550*w,100*h,200*w,200*w,50*h,50*h,20*s));
			gender.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-550*w,200*h,200*w,200*w,50*h,50*h,20*s));
			vehicle.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",50*w,-200*h,200*w,200*w,50*h,50*h,20*s));
			email.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",50*w,-100*h,200*w,200*w,50*h,50*h,20*s));
			phone.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",50*w,0*h,200*w,200*w,50*h,50*h,20*s));
			area.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",50*w,100*h,200*w,200*w,50*h,50*h,20*s));
			
			inputid.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-250*w,-200*h,200*w,200*w,50*h,50*h,20*s));
			inputfirstName.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-250*w,-100*h,200*w,200*w,50*h,50*h,20*s));
			inputlastName.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-250*w,0*h,200*w,200*w,50*h,50*h,20*s));
			inputbirth.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-250*w,100*h,200*w,200*w,50*h,50*h,20*s));
			inputgender.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-250*w,200*h,200*w,200*w,50*h,50*h,20*s));
			inputvehicle.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",350*w,-200*h,200*w,200*w,50*h,50*h,20*s));
			inputemail.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",350*w,-100*h,200*w,200*w,50*h,50*h,20*s));
			inputphone.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",350*w,0*h,200*w,200*w,50*h,50*h,20*s));
			inputArea.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",350*w,100*h,200*w,200*w,50*h,50*h,20*s));
			
			edit.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",600*w,-75*h,150*w,150*w,120*h,120*h,20*s));
			searchBox.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,-400*h,250*w,250*w,100*h,100*h,20*s));
			
			edit.setOnAction(eb->{
				try {
					
					inputfirstName.isLegal();
					inputlastName.isLegal();
					inputemail.isLegalMail();
					inputphone.isLegalPhone();
					inputid.isLegalID();
					inputbirth.isLegal();
					inputgender.isLegal();
					inputvehicle.isLegal();
					inputArea.isLegal();
					
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
					String[] answer=ToolsBar.getInstance().getIn().checkedReadLine().split("/");
					if(Boolean.parseBoolean(answer[0])) {
						PopUp.display("delivery person was editted successfully!",true);
					}
					else {
						if(answer.length==1) {
							PopUp.display("delivery person was not eddited",false);
						}
						else {
							PopUp.display(answer[1],false);
						}
					}
				}
				catch (BlankFieldException | IllegalCharacterException | LieException | InvalidIDException | InvalidPhoneException | InvalidEmailException | ToYoungException | ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException | ToLateException | NumberFormatException | NotNumberException e1) {
					PopUp.display(e1.getMessage(),false);
					}
				
			});
			

			inputgender.getBox().getSelectionModel().select(Gender.valueOf(info[4]));
			inputvehicle.getItems().addAll(Arrays.asList(Vehicle.values()));
			inputvehicle.getSelectionModel().select(Vehicle.valueOf(info[5]));
			inputArea.getBox().getSelectionModel().select(info[6]);
			stack.getChildren().addAll(id,firstName,lastName,birth,gender,inputArea,email,phone,inputvehicle,area,vehicle,inputid,inputfirstName,inputlastName,inputbirth,inputgender,inputemail,inputphone,edit,searchBox);
			layout.setCenter(stack);
		});
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(searchBox);
	}
//int id,String firstName, String lastName, LocalDate birthDay, Gender gender, Vehicle vehicle,	DeliveryArea area, String email, String phone

	private void editCook() {
		ToolsBar.getInstance().getPlace().pushText("Edit cook");
		ToolsBar.getInstance().getOut().println("getCooks");
		Collection<String> list;
		try {
			list=Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
		} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {
			PopUp.display(e1.getMessage(), false);
			list=new HashSet<>();
		}
		FilteredBox<String> searchBox=new FilteredBox<>(list);
		searchBox.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,0*h,250*w,250*w,100*h,100*h,20*s));
		searchBox.getSearch().setPromptText("Search your cook here");
		searchBox.getBox().getSelectionModel().selectedItemProperty().addListener(e->{
			ToolsBar.getInstance().getOut().println("getRealCook/"+searchBox.getBox().getSelectionModel().getSelectedItem().split(" ")[0]);
			String[] info;
			try {
				info = ToolsBar.getInstance().getIn().checkedReadLine().split("/");
			} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e4) {
				PopUp.display(e4.getMessage(), false);
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
			
			PersonIdField inputid=new PersonIdField(info[0]);
			IndTextField inputfirstName=new IndTextField(info[1]);
			IndTextField inputlastName=new IndTextField(info[2]);
			String[] ld=info[3].split("-");
			IndDatePicker inputbirth=new IndDatePicker(LocalDate.of(Integer.parseInt(ld[0]), Integer.parseInt(ld[1]), Integer.parseInt(ld[2])));
			FilteredBox<Gender> inputgender=new FilteredBox<>(Gender.values());
			
			IndComboBox<Expertise> inputexpertise=new IndComboBox<>();
			CheckBox inputchef=new CheckBox();
			EmailField inputemail=new EmailField(info[7]);
			PhoneField inputphone=new PhoneField(info[8]);
			
			
			Button edit=new Button("edit");
			
			id.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-550*w,-200*h,200*w,200*w,50*h,50*h,20*s));
			firstName.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-550*w,-100*h,200*w,200*w,50*h,50*h,20*s));
			lastName.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-550*w,0*h,200*w,200*w,50*h,50*h,20*s));
			birth.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-550*w,100*h,200*w,200*w,50*h,50*h,20*s));
			gender.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-550*w,200*h,200*w,200*w,50*h,50*h,20*s));
			expertise.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-50*w,-200*h,200*w,200*w,50*h,50*h,20*s));
			email.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-50*w,-100*h,200*w,200*w,50*h,50*h,20*s));
			phone.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-50*w,0*h,200*w,200*w,50*h,50*h,20*s));
			chef.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-50*w,100*h,200*w,200*w,50*h,50*h,20*s));
			
			inputid.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-250*w,-200*h,200*w,200*w,50*h,50*h,20*s));
			inputfirstName.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-250*w,-100*h,200*w,200*w,50*h,50*h,20*s));
			inputlastName.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-250*w,0*h,200*w,200*w,50*h,50*h,20*s));
			inputbirth.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-250*w,100*h,200*w,200*w,50*h,50*h,20*s));
			inputgender.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-250*w,200*h,200*w,200*w,50*h,50*h,20*s));
			inputexpertise.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",350*w,-200*h,200*w,200*w,50*h,50*h,20*s));
			inputemail.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",350*w,-100*h,200*w,200*w,50*h,50*h,20*s));
			inputphone.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",350*w,0*h,200*w,200*w,50*h,50*h,20*s));
			inputchef.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",350*w,100*h,200*w,200*w,50*h,50*h,20*s));	
			
			edit.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",600*w,-75*h,150*w,150*w,120*h,120*h,20*s));
			searchBox.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,-400*h,250*w,250*w,100*h,100*h,20*s));
			
			edit.setOnAction(eb->{
				try {
					
					inputfirstName.isLegal();
					inputlastName.isLegal();
					inputemail.isLegalMail();
					inputphone.isLegalPhone();
					inputid.isLegalID();
					inputbirth.isLegal();
					inputgender.isLegal();
					inputexpertise.isLegal();
					
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
					String[] answer=ToolsBar.getInstance().getIn().checkedReadLine().split("/");
					if(Boolean.parseBoolean(answer[0])) {
						PopUp.display("cook was editted successfully!",true);
					}
					else {
						if(answer.length==1) {
							PopUp.display("cook person was not eddited",false);
						}
						else {
							PopUp.display(answer[1],false);
						}
					}
				}
				catch (BlankFieldException | IllegalCharacterException | LieException | InvalidIDException | InvalidPhoneException | InvalidEmailException | ToYoungException | ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException | ToLateException | NumberFormatException | NotNumberException e1) {
					PopUp.display(e1.getMessage(),false);
					}
				 
			});
			
			inputgender.getBox().getSelectionModel().select(Gender.valueOf(info[4]));
			inputexpertise.getItems().addAll(Expertise.values());
			inputexpertise.getSelectionModel().select(Expertise.valueOf(info[5]));
			inputchef.setSelected(Boolean.parseBoolean(info[6]));
			stack.getChildren().addAll(id,firstName,lastName,birth,gender,inputchef,email,phone,inputexpertise,expertise,chef,inputid,inputfirstName,inputlastName,inputbirth,inputgender,inputemail,inputphone,edit,searchBox);

			layout.setCenter(stack);

		});
		
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(searchBox);
	}
//int id,String firstName, String lastName, LocalDate birthDay, Gender gender, Expertise expert,boolean isChef, String email, String phone 
}
