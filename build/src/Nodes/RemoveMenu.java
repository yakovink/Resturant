package Nodes;


import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import Exceptions.FaildToRemoveException;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class RemoveMenu extends StackPane{




	Button removeAccount;
	Button removeCook;
	Button removeDeliveryPerson;
	Button removeCustomer;
	Button removeComponent;
	Button removeDish;
	Button removeOrder;
	Button removeDelivery;
	Button removeArea;

	
	private BorderPane layout;

	
	


	public  RemoveMenu(BorderPane layout) {
		
		this.layout=layout;
		
		
		removeArea=new Button("Remove Area...");
		removeComponent=new Button("Remove Component...");
		removeDish=new Button("Remove Dish...");
		removeCook=new Button("Remove Cook...");
		removeDeliveryPerson=new Button("Remove Delivery Person...");
		removeCustomer=new Button("Remove Customer...");
		removeOrder=new Button("Remove Order...");
		removeDelivery=new Button("Remove Delivery...");

		
		removeCook.setStyle("-fx-translate-x:-200;-fx-translate-y:-100;-fx-min-width:300;-fx-max-width:300;-fx-min-height:100;-fx-max-height:100;-fx-font-size:25;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px");
		removeDeliveryPerson.setStyle("-fx-translate-x:-200;-fx-translate-y:50;-fx-min-width:300;-fx-max-width:300;-fx-min-height:100;-fx-max-height:100;-fx-font-size:25;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px");
		removeCustomer.setStyle("-fx-translate-x:-200;-fx-translate-y:200;-fx-min-width:300;-fx-max-width:300;-fx-min-height:100;-fx-max-height:100;-fx-font-size:25;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px");
		removeComponent.setStyle("-fx-translate-x:-200;-fx-translate-y:-250;-fx-min-width:300;-fx-max-width:300;-fx-min-height:100;-fx-max-height:100;-fx-font-size:25;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px");
		removeDish.setStyle("-fx-translate-x:200;-fx-translate-y:-100;-fx-min-width:300;-fx-max-width:300;-fx-min-height:100;-fx-max-height:100;-fx-font-size:25;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px");
		removeOrder.setStyle("-fx-translate-x:200;-fx-translate-y:50;-fx-min-width:300;-fx-max-width:300;-fx-min-height:100;-fx-max-height:100;-fx-font-size:25;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px");
		removeDelivery.setStyle("-fx-translate-x:200;-fx-translate-y:200;-fx-min-width:300;-fx-max-width:300;-fx-min-height:100;-fx-max-height:100;-fx-font-size:25;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px");
		removeArea.setStyle("-fx-translate-x:200;-fx-translate-y:-250;-fx-min-width:300;-fx-max-width:300;-fx-min-height:100;-fx-max-height:100;-fx-font-size:25;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px");

		
		this.removeArea.setOnAction(e->removeArea());
		this.removeComponent.setOnAction(e->removeComponent());
		this.removeDish.setOnAction(e->removeDish());
		this.removeCook.setOnAction(e->removeCook());
		this.removeDeliveryPerson.setOnAction(e->removeDeliveryPerson());
		this.removeCustomer.setOnAction(e->removeCustomer());
		this.removeOrder.setOnAction(e->removeOrder());
		this.removeDelivery.setOnAction(e->removeDelivery());

		
		
		
		this.getChildren().addAll(removeCook,removeDeliveryPerson,removeCustomer,removeComponent,removeDish,removeOrder,removeDelivery,removeArea);
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(this);
	}




	private void removeDelivery() {
		ToolsBar.getInstance().getOut().println("getDels");
		Collection<String> ourlist;
		try {
			ourlist=Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
		} catch (IOException e1) {
			ourlist=new HashSet<>();
		}
		FilteredList<String> list=new FilteredList<>(ourlist);
		Button remove=new Button("remove");
		Label status=new Label();
		StackPane vbox=new StackPane();
		vbox.getChildren().addAll(list,remove,status);
		vbox.setAlignment(Pos.CENTER);
		remove.setStyle("-fx-translate-x:400;-fx-translate-y:-75;-fx-min-width:150;-fx-max-width:150;-fx-min-height:120;-fx-max-height:120;-fx-font-size:20;-fx-background-radius:15px");
		status.setStyle("-fx-translate-x:400;-fx-translate-y:75;-fx-min-width:250;-fx-max-width:250;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
		vbox.setStyle("-fx-translate-x:0;-fx-translate-y:05;-fx-min-width:300;-fx-max-width:300;-fx-min-height:500;-fx-max-height:500;-fx-font-size:20;-fx-background-radius:15px");
		
		
		remove.setOnAction(e->{
			for(String d:list.getList().getSelectionModel().getSelectedItems()) {
				try {
					ToolsBar.getInstance().getOut().println("remDel/"+d.split(" ")[0]);
					if(!Boolean.parseBoolean(ToolsBar.getInstance().getIn().readLine())) {
						throw new FaildToRemoveException();
					}
						
					list.getList().getItems().removeAll(list.getList().getSelectionModel().getSelectedItems());
					status.setText("Delivery removed successfully");
					status.setTextFill(Color.GREEN);
				}
				catch(FaildToRemoveException | IOException er) {
					status.setText(er.getMessage());
					status.setTextFill(Color.RED);
				}
			}
			
		});
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(vbox);
	}




	private void removeOrder() {
		ToolsBar.getInstance().getOut().println("getOrds");
		Collection<String> ourlist;
		try {
			ourlist=Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
		} catch (IOException e1) {
			ourlist=new HashSet<>();
		}
		FilteredList<String> list=new FilteredList<>(ourlist);
		Button remove=new Button("remove");
		Label status=new Label();
		StackPane vbox=new StackPane();
		vbox.getChildren().addAll(list,remove,status);
		vbox.setAlignment(Pos.CENTER);
		remove.setStyle("-fx-translate-x:400;-fx-translate-y:-75;-fx-min-width:150;-fx-max-width:150;-fx-min-height:120;-fx-max-height:120;-fx-font-size:20;-fx-background-radius:15px");
		status.setStyle("-fx-translate-x:400;-fx-translate-y:75;-fx-min-width:250;-fx-max-width:250;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
		vbox.setStyle("-fx-translate-x:0;-fx-translate-y:05;-fx-min-width:300;-fx-max-width:300;-fx-min-height:500;-fx-max-height:500;-fx-font-size:20;-fx-background-radius:15px");
		
		
		remove.setOnAction(e->{
			for(String o:list.getList().getSelectionModel().getSelectedItems()) {
				try {
					ToolsBar.getInstance().getOut().println("remOrd/"+o.split(" ")[0]);
					if(!Boolean.parseBoolean(ToolsBar.getInstance().getIn().readLine())) {
						throw new FaildToRemoveException();
					}
					list.getList().getItems().removeAll(list.getList().getSelectionModel().getSelectedItems());
					status.setText("Order removed successfully");
					status.setTextFill(Color.GREEN);
				}
				catch(FaildToRemoveException | IOException er) {
					status.setText(er.getMessage());
					status.setTextFill(Color.RED);
				}
			}
			
		});
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(vbox);
	}




	private void removeCustomer() {
		ToolsBar.getInstance().getOut().println("getCusts");
		Collection<String> ourlist;
		try {
			ourlist=Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
		} catch (IOException e1) {
			ourlist=new HashSet<>();
		}
		FilteredList<String> list=new FilteredList<>(ourlist);
		Button remove=new Button("remove");
		Label status=new Label();
		StackPane vbox=new StackPane();
		vbox.getChildren().addAll(list,remove,status);
		vbox.setAlignment(Pos.CENTER);
		remove.setStyle("-fx-translate-x:400;-fx-translate-y:-75;-fx-min-width:150;-fx-max-width:150;-fx-min-height:120;-fx-max-height:120;-fx-font-size:20;-fx-background-radius:15px");
		status.setStyle("-fx-translate-x:400;-fx-translate-y:75;-fx-min-width:250;-fx-max-width:250;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
		vbox.setStyle("-fx-translate-x:0;-fx-translate-y:05;-fx-min-width:300;-fx-max-width:300;-fx-min-height:500;-fx-max-height:500;-fx-font-size:20;-fx-background-radius:15px");
		
		
		remove.setOnAction(e->{
			for(String c:list.getList().getSelectionModel().getSelectedItems()) {
				try {
					ToolsBar.getInstance().getOut().println("remCust/"+c.split(" ")[0]);
					if(!Boolean.parseBoolean(ToolsBar.getInstance().getIn().readLine())) {
						throw new FaildToRemoveException();
					}
					list.getList().getItems().removeAll(list.getList().getSelectionModel().getSelectedItems());
					status.setText("Customer removed successfully");
					status.setTextFill(Color.GREEN);
				}
				catch(FaildToRemoveException | IOException er) {
					status.setText(er.getMessage());
					status.setTextFill(Color.RED);
				}
			}
			
		});
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(vbox);
	}




	private void removeDeliveryPerson() {
		ToolsBar.getInstance().getOut().println("getDeps");
		Collection<String> ourlist;
		try {
			ourlist=Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
		} catch (IOException e1) {
			ourlist=new HashSet<>();
		}
		FilteredList<String> list=new FilteredList<>(ourlist);
		Button remove=new Button("remove");
		Label status=new Label();
		StackPane vbox=new StackPane();
		vbox.getChildren().addAll(list,remove,status);
		vbox.setAlignment(Pos.CENTER);
		remove.setStyle("-fx-translate-x:400;-fx-translate-y:-75;-fx-min-width:150;-fx-max-width:150;-fx-min-height:120;-fx-max-height:120;-fx-font-size:20;-fx-background-radius:15px");
		status.setStyle("-fx-translate-x:400;-fx-translate-y:75;-fx-min-width:250;-fx-max-width:250;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
		vbox.setStyle("-fx-translate-x:0;-fx-translate-y:05;-fx-min-width:300;-fx-max-width:300;-fx-min-height:500;-fx-max-height:500;-fx-font-size:20;-fx-background-radius:15px");
		
		
		remove.setOnAction(e->{
			for(String dp:list.getList().getSelectionModel().getSelectedItems()) {
				try {
					ToolsBar.getInstance().getOut().println("remDp/"+dp.split(" ")[0]);
					if(!Boolean.parseBoolean(ToolsBar.getInstance().getIn().readLine())) {
						throw new FaildToRemoveException();
					}
					list.getList().getItems().removeAll(list.getList().getSelectionModel().getSelectedItems());
					status.setText("Delivery person removed successfully");
					status.setTextFill(Color.GREEN);
				}
				catch(FaildToRemoveException | IOException er) {
					status.setText(er.getMessage());
					status.setTextFill(Color.RED);
				}
			}
			
		});
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(vbox);
	}




	private void removeCook() {
		ToolsBar.getInstance().getOut().println("getCooks");
		Collection<String> ourlist;
		try {
			ourlist=Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
		} catch (IOException e1) {
			ourlist=new HashSet<>();
		}
		FilteredList<String> list=new FilteredList<>(ourlist);
		Button remove=new Button("remove");
		Label status=new Label();
		StackPane vbox=new StackPane();
		vbox.getChildren().addAll(list,remove,status);
		vbox.setAlignment(Pos.CENTER);
		remove.setStyle("-fx-translate-x:400;-fx-translate-y:-75;-fx-min-width:150;-fx-max-width:150;-fx-min-height:120;-fx-max-height:120;-fx-font-size:20;-fx-background-radius:15px");
		status.setStyle("-fx-translate-x:400;-fx-translate-y:75;-fx-min-width:250;-fx-max-width:250;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
		vbox.setStyle("-fx-translate-x:0;-fx-translate-y:05;-fx-min-width:300;-fx-max-width:300;-fx-min-height:500;-fx-max-height:500;-fx-font-size:20;-fx-background-radius:15px");
		
		
		remove.setOnAction(e->{
			for(String c:list.getList().getSelectionModel().getSelectedItems()) {
				try {
					ToolsBar.getInstance().getOut().println("remCook/"+c.split(" ")[0]);
					if(!Boolean.parseBoolean(ToolsBar.getInstance().getIn().readLine())) {
						throw new FaildToRemoveException();
					}
					list.getList().getItems().removeAll(list.getList().getSelectionModel().getSelectedItems());
					status.setText("Cook removed successfully");
					status.setTextFill(Color.GREEN);
				}
				catch(FaildToRemoveException | IOException er) {
					status.setText(er.getMessage());
					status.setTextFill(Color.RED);
				}
			}
			
		});
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(vbox);
	}




	private void removeDish() {
		ToolsBar.getInstance().getOut().println("getDishes");
		Collection<String> ourlist;
		try {
			ourlist=Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
		} catch (IOException e1) {
			ourlist=new HashSet<>();
		}
		FilteredList<String> list=new FilteredList<>(ourlist);
		Button remove=new Button("remove");
		Label status=new Label();
		VBox vbox=new VBox();
		vbox.getChildren().addAll(list,remove,status);
		vbox.setAlignment(Pos.CENTER);
		remove.setStyle("-fx-translate-x:400;-fx-translate-y:-75;-fx-min-width:150;-fx-max-width:150;-fx-min-height:120;-fx-max-height:120;-fx-font-size:20;-fx-background-radius:15px");
		status.setStyle("-fx-translate-x:400;-fx-translate-y:75;-fx-min-width:250;-fx-max-width:250;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
		vbox.setStyle("-fx-translate-x:0;-fx-translate-y:05;-fx-min-width:300;-fx-max-width:300;-fx-min-height:500;-fx-max-height:500;-fx-font-size:20;-fx-background-radius:15px");
		remove.setOnAction(e->{
			for(String d:list.getList().getSelectionModel().getSelectedItems()) {
				try {
					ToolsBar.getInstance().getOut().println("remDish/"+d.split(" ")[0]);
					if(!Boolean.parseBoolean(ToolsBar.getInstance().getIn().readLine())) {
						throw new FaildToRemoveException();
					}
					list.getList().getItems().removeAll(list.getList().getSelectionModel().getSelectedItems());
					status.setText("Dish removed successfully");
					status.setTextFill(Color.GREEN);
				}
				catch(FaildToRemoveException | IOException er) {
					status.setText(er.getMessage());
					status.setTextFill(Color.RED);
				}
			}
			
		});
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(vbox);
	}




	private void removeComponent() {
		ToolsBar.getInstance().getOut().println("getComps");
		Collection<String> ourlist;
		try {
			ourlist=Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
		} catch (IOException e1) {
			ourlist=new HashSet<>();
		}
		FilteredList<String> list=new FilteredList<>(ourlist);
		Button remove=new Button("remove");
		Label status=new Label();
		StackPane vbox=new StackPane();
		vbox.getChildren().addAll(list,remove,status);
		vbox.setAlignment(Pos.CENTER);
		remove.setStyle("-fx-translate-x:400;-fx-translate-y:-75;-fx-min-width:150;-fx-max-width:150;-fx-min-height:120;-fx-max-height:120;-fx-font-size:20;-fx-background-radius:15px");
		status.setStyle("-fx-translate-x:400;-fx-translate-y:75;-fx-min-width:250;-fx-max-width:250;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;");
		vbox.setStyle("-fx-translate-x:0;-fx-translate-y:05;-fx-min-width:300;-fx-max-width:300;-fx-min-height:500;-fx-max-height:500;-fx-font-size:20;-fx-background-radius:15px");
		
		
		remove.setOnAction(e->{
			for(String c:list.getList().getSelectionModel().getSelectedItems()) {
				try {
					ToolsBar.getInstance().getOut().println("remDish/"+c.split(" ")[0]);
					if(!Boolean.parseBoolean(ToolsBar.getInstance().getIn().readLine())) {
						throw new FaildToRemoveException();
					}
					list.getList().getItems().removeAll(list.getList().getSelectionModel().getSelectedItems());
					status.setText("Component removed successfully");
					status.setTextFill(Color.GREEN);
				}
				catch(FaildToRemoveException | IOException er) {
					status.setText(er.getMessage());
					status.setTextFill(Color.RED);
				}
			}
			
		});
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(vbox);
	}




	private void removeArea() {
		
		ToolsBar.getInstance().getOut().println("getAreas");
		Collection<String> ourlist;
		try {
			ourlist=Arrays.asList(ToolsBar.getInstance().getIn().readLine().split(","));
		} catch (IOException e1) {
			ourlist=new HashSet<>();
		}
		FilteredBox<String> oldArea=new FilteredBox<>(ourlist);
		FilteredBox<String> newArea=new FilteredBox<>(ourlist);
		Button button=new Button("Remove");
		Label status=new Label();
		
		oldArea.setStyle("-fx-translate-x:-250;-fx-translate-y:0;-fx-min-width:250;-fx-max-width:250;-fx-min-height:500;-fx-max-height:500;-fx-font-size:20;-fx-background-radius:20px");
		newArea.setStyle("-fx-translate-x:250;-fx-translate-y:0;-fx-min-width:250;-fx-max-width:250;-fx-min-height:500;-fx-max-height:500;-fx-font-size:20;-fx-background-radius:20px");
		button.setStyle("-fx-translate-x:500;-fx-translate-y:-75;-fx-min-width:150;-fx-max-width:150;-fx-min-height:120;-fx-max-height:120;-fx-font-size:20;-fx-background-redius:15px");
		status.setStyle("-fx-translate-x:500;-fx-translate-y:75;-fx-min-width:250;-fx-max-width:250;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20");
		
		button.setOnAction(e->{
			String olda=oldArea.getBox().getSelectionModel().getSelectedItem();
			String newa=newArea.getBox().getSelectionModel().getSelectedItem();
			if(!olda.equals(newa)) {
				try {
					ToolsBar.getInstance().getOut().println("remDish/"+olda.split(" ")[0]+"/"+newa.split(" ")[0]);
					if(!Boolean.parseBoolean(ToolsBar.getInstance().getIn().readLine())) {
						throw new FaildToRemoveException();
					}
					oldArea.getBox().getSelectionModel().clearSelection();
					oldArea.getBox().getItems().remove(olda);
					newArea.getBox().getSelectionModel().clearSelection();
					newArea.getBox().getItems().remove(olda);
					status.setText("Area removed successfully");
					status.setTextFill(Color.GREEN);
				} catch (FaildToRemoveException | IOException e1) {
					// TODO Auto-generated catch block
					status.setText(e1.getMessage());
					status.setTextFill(Color.RED);
				}
			}
			else {
				status.setText("you cant choose the same area");
				status.setTextFill(Color.RED);
			}
		});
		StackPane hbox=new StackPane();
		hbox.getChildren().addAll(oldArea,button,newArea,status);
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(hbox);

	}
	
}
