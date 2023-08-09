package View;



/*this is the remove menu class. all of the remove methods are methods of this class.
 *the remove action: you choose some object from the list and click "remove".
 *if remove action faild, user gers pop-up**/

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import Exceptions.CommandWasNotFoundException;
import Exceptions.EmptyBufferException;
import Exceptions.FaildToRemoveException;
import Exceptions.ReaderConnectionException;
import View.Nodes.FilteredBox;
import View.Nodes.FilteredList;
import View.Nodes.ToolsBar;
import application.App;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class RemoveMenu extends StackPane{




	private Button removeCook;
	private Button removeDeliveryPerson;
	private Button removeCustomer;
	private Button removeComponent;
	private Button removeDish;
	private Button removeOrder;
	private Button removeDelivery;
	private Button removeArea;

	private double w;
	private double h;
	private double s;
	
	private BorderPane layout;


	


	public RemoveMenu() {
		ToolsBar.getInstance().getPlace().pushText("Remove menu");
		w=App.getW();
		h=App.getH();
		s=App.getS();
		
		this.layout=App.getLayout();
		
		
		removeArea=new Button("Remove Area...");
		removeComponent=new Button("Remove Component...");
		removeDish=new Button("Remove Dish...");
		removeCook=new Button("Remove Cook...");
		removeDeliveryPerson=new Button("Remove Delivery Person...");
		removeCustomer=new Button("Remove Customer...");
		removeOrder=new Button("Remove Order...");
		removeDelivery=new Button("Remove Delivery...");

		
		removeCook.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px",-200*w,-250*h,300*w,300*w,100*h,100*h,25*s));
		removeDeliveryPerson.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px",-200*w,-100*h,300*w,300*w,100*h,100*h,25*s));
		removeCustomer.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px",-200*w,50*h,300*w,300*w,100*h,100*h,25*s));
		removeComponent.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px",-200*w,200*h,300*w,300*w,100*h,100*h,25*s));
		removeDish.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px",200*w,-250*h,300*w,300*w,100*h,100*h,25*s));
		removeOrder.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px",200*w,-100*h,300*w,300*w,100*h,100*h,25*s));
		removeDelivery.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px",200*w,50*h,300*w,300*w,100*h,100*h,25*s));
		removeArea.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-color:#76BD79;-fx-background-radius:30px",200*w,200*h,300*w,300*w,100*h,100*h,25*s));

		
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
		ToolsBar.getInstance().getPlace().pushText("Remove delivery");
		ToolsBar.getInstance().getOut().println("getDels");
		Collection<String> ourlist;
		try {
			ourlist=Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
		} catch (EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e1) {
			PopUp.display(e1.getMessage(), false);
			ourlist=new HashSet<>();
		}
		FilteredList<String> list=new FilteredList<>(ourlist);
		Button remove=new Button("remove");
		StackPane vbox=new StackPane();
		vbox.getChildren().addAll(list,remove);
		vbox.setAlignment(Pos.CENTER);
		remove.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",400*w,-75*h,150*w,150*w,120*h,120*h,20*s));
		vbox.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,0*h,300*w,300*w,500*h,500*h,20*s));
		
		
		remove.setOnAction(e->{
			for(String d:list.getList().getSelectionModel().getSelectedItems()) {
				String[] answer= {"false","internal error"};
				try {
					ToolsBar.getInstance().getOut().println("remDel/"+d.split(" ")[1]);
					answer=ToolsBar.getInstance().getIn().checkedReadLine().split("/");
					if(!Boolean.parseBoolean(answer[0])) {
						throw new FaildToRemoveException(answer[1]);
					}
						
					list.removeAll(list.getList().getSelectionModel().getSelectedItems());
					PopUp.display("Delivery removed successfully!", true);
				}
				catch(FaildToRemoveException | EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException  er) {
					PopUp.display(er.getMessage(), false);
				} 
			}
			
		});
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(vbox);
	}




	private void removeOrder() {
		ToolsBar.getInstance().getPlace().pushText("Remove order");
		ToolsBar.getInstance().getOut().println("getOrds");
		Collection<String> ourlist;
		try {
			ourlist=Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
		} catch ( EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e1) {
			PopUp.display(e1.getMessage(), false);
			ourlist=new HashSet<>();
		}
		FilteredList<String> list=new FilteredList<>(ourlist);
		Button remove=new Button("remove");
		StackPane vbox=new StackPane();
		vbox.getChildren().addAll(list,remove);
		vbox.setAlignment(Pos.CENTER);
		remove.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",400*w,-75*h,150*w,150*w,120*h,120*h,20*s));
		vbox.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,0*h,300*w,300*w,500*h,500*h,20*s));
		
		
		remove.setOnAction(e->{
			for(String o:list.getList().getSelectionModel().getSelectedItems()) {
				String[] answer= {"false","internal error"};
				try {
					ToolsBar.getInstance().getOut().println("remOrd/"+o.split(" ")[0]);
					answer=ToolsBar.getInstance().getIn().checkedReadLine().split("/");
					if(!Boolean.parseBoolean(answer[0])) {
						throw new FaildToRemoveException(answer[1]);
					}
					list.removeAll(list.getList().getSelectionModel().getSelectedItems());
					PopUp.display("Order removed successfully!", true);
				}
				catch(FaildToRemoveException | EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException er) {
					PopUp.display(er.getMessage(), false);
				} 
			}
			
		});
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(vbox);
	}




	private void removeCustomer() {
		ToolsBar.getInstance().getPlace().pushText("Remove customer");
		ToolsBar.getInstance().getOut().println("getCusts");
		Collection<String> ourlist;
		try {
			ourlist=Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
		} catch (EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e1) {
			PopUp.display(e1.getMessage(), false);
			ourlist=new HashSet<>();
		}
		FilteredList<String> list=new FilteredList<>(ourlist);
		Button remove=new Button("remove");
		StackPane vbox=new StackPane();
		vbox.getChildren().addAll(list,remove);
		vbox.setAlignment(Pos.CENTER);
		remove.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",400*w,-75*h,150*w,150*w,120*h,120*h,20*s));
		vbox.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,0*h,300*w,300*w,500*h,500*h,20*s));
		
		
		remove.setOnAction(e->{
			String[] answer= {"false","internal error"};
			for(String c:list.getList().getSelectionModel().getSelectedItems()) {
				try {
					ToolsBar.getInstance().getOut().println("remCust/"+c.split(" ")[0]);
					answer=ToolsBar.getInstance().getIn().checkedReadLine().split("/");
					if(!Boolean.parseBoolean(answer[0])) {
						throw new FaildToRemoveException(answer[1]);
					}
					list.removeAll(list.getList().getSelectionModel().getSelectedItems());
					PopUp.display("Customer removed successfully!", true);
				}
				catch(FaildToRemoveException | EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException er) {
					PopUp.display(er.getMessage(), false);
				} 
			}
			
		});
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(vbox);
	}




	private void removeDeliveryPerson() {
		ToolsBar.getInstance().getPlace().pushText("Remove delivery person");
		ToolsBar.getInstance().getOut().println("getDeps");
		Collection<String> ourlist;
		try {
			ourlist=Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
		} catch (EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e1) {
			PopUp.display(e1.getMessage(), false);
			ourlist=new HashSet<>();
		}
		FilteredList<String> list=new FilteredList<>(ourlist);
		Button remove=new Button("remove");
		StackPane vbox=new StackPane();
		vbox.getChildren().addAll(list,remove);
		vbox.setAlignment(Pos.CENTER);
		remove.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",400*w,-75*h,150*w,150*w,120*h,120*h,20*s));
		vbox.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,0*h,300*w,300*w,500*h,500*h,20*s));
		
		
		remove.setOnAction(e->{
			String[] answer= {"false","internal error"};
			for(String dp:list.getList().getSelectionModel().getSelectedItems()) {
				try {
					ToolsBar.getInstance().getOut().println("remDp/"+dp.split(" ")[0]);
					answer=ToolsBar.getInstance().getIn().checkedReadLine().split("/");
					if(!Boolean.parseBoolean(answer[0])) {
						throw new FaildToRemoveException(answer[1]);
					}
					list.removeAll(list.getList().getSelectionModel().getSelectedItems());
					PopUp.display("Delivery Person removed successfully!", true);
				}
				catch(FaildToRemoveException | EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException er) {
					PopUp.display(er.getMessage(), false);
				} 
			}
			
		});
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(vbox);
	}




	private void removeCook() {
		ToolsBar.getInstance().getPlace().pushText("Remove cook");
		ToolsBar.getInstance().getOut().println("getCooks");
		Collection<String> ourlist;
		try {
			ourlist=Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
		} catch (EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e1) {
			PopUp.display(e1.getMessage(), false);
			ourlist=new HashSet<>();
		}
		FilteredList<String> list=new FilteredList<>(ourlist);
		Button remove=new Button("remove");
		StackPane vbox=new StackPane();
		vbox.getChildren().addAll(list,remove);
		vbox.setAlignment(Pos.CENTER);
		remove.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",400*w,-75*h,150*w,150*w,120*h,120*h,20*s));
		vbox.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,0*h,300*w,300*w,500*h,500*h,20*s));
		
		
		remove.setOnAction(e->{
			String[] answer= {"false","internal error"};
			for(String c:list.getList().getSelectionModel().getSelectedItems()) {
				try {
					ToolsBar.getInstance().getOut().println("remCook/"+c.split(" ")[0]);
					answer=ToolsBar.getInstance().getIn().checkedReadLine().split("/");
					if(!Boolean.parseBoolean(answer[0])) {
						throw new FaildToRemoveException(answer[1]);
					}
					list.removeAll(list.getList().getSelectionModel().getSelectedItems());
					PopUp.display("Cook removed successfully!", true);
				}
				catch(FaildToRemoveException | EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException er) {
					PopUp.display(er.getMessage(), false);
				} 
			}
			
		});
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(vbox);
	}




	private void removeDish() {
		ToolsBar.getInstance().getPlace().pushText("Remove dish");
		ToolsBar.getInstance().getOut().println("getDishes");
		Collection<String> ourlist;
		try {
			ourlist=Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
		} catch (EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e1) {
			PopUp.display(e1.getMessage(), false);
			ourlist=new HashSet<>();
		}
		FilteredList<String> list=new FilteredList<>(ourlist);
		Button remove=new Button("remove");
		VBox vbox=new VBox();
		vbox.getChildren().addAll(list,remove);
		vbox.setAlignment(Pos.CENTER);
		remove.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",400*w,-75*h,150*w,150*w,120*h,120*h,20*s));
		vbox.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,0*h,300*w,300*w,500*h,500*h,20*s));
		remove.setOnAction(e->{
			String[] answer= {"false","internal error"};
			for(String d:list.getList().getSelectionModel().getSelectedItems()) {
				try {
					ToolsBar.getInstance().getOut().println("remDish/"+d.split(" ")[0]);
					answer=ToolsBar.getInstance().getIn().checkedReadLine().split("/");
					if(!Boolean.parseBoolean(answer[0])) {
						throw new FaildToRemoveException(answer[1]);
					}
					list.removeAll(list.getList().getSelectionModel().getSelectedItems());
					PopUp.display("Dish removed successfully!", true);
				}
				catch(FaildToRemoveException | EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException er) {
					PopUp.display(er.getMessage(), false);
				} 
			}
			
		});
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(vbox);
	}




	private void removeComponent() {
		ToolsBar.getInstance().getPlace().pushText("Remove component");
		ToolsBar.getInstance().getOut().println("getComps");
		Collection<String> ourlist;
		try {
			ourlist=Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
		} catch (EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e1) {
			PopUp.display(e1.getMessage(), false);
			ourlist=new HashSet<>();
		}
		FilteredList<String> list=new FilteredList<>(ourlist);
		Button remove=new Button("remove");
		StackPane vbox=new StackPane();
		vbox.getChildren().addAll(list,remove);
		vbox.setAlignment(Pos.CENTER);
		remove.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",400*w,-75*h,150*w,150*w,120*h,120*h,20*s));
		vbox.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,0*h,300*w,300*w,500*h,500*h,20*s));
		
		
		remove.setOnAction(e->{
			String[] answer= {"false","internal error"};
			for(String c:list.getList().getSelectionModel().getSelectedItems()) {
				try {
					ToolsBar.getInstance().getOut().println("remComp/"+c.split(" ")[0]);
					answer=ToolsBar.getInstance().getIn().checkedReadLine().split("/");
					if(!Boolean.parseBoolean(answer[0])) {
						throw new FaildToRemoveException(answer[1]);
					}
					list.removeAll(list.getList().getSelectionModel().getSelectedItems());
					PopUp.display("Component removed successfully!", true);
				}
				catch(FaildToRemoveException | EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException er) {
					PopUp.display(er.getMessage(), false);
				}
			}
		});
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(vbox);
	}




	private void removeArea() {
		ToolsBar.getInstance().getPlace().pushText("Remove area");
		ToolsBar.getInstance().getOut().println("getAreas");
		Collection<String> ourlist;
		try {
			ourlist=Arrays.asList(ToolsBar.getInstance().getIn().checkedReadLine().split(","));
		} catch (EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e1) {
			PopUp.display(e1.getMessage(), false);
			ourlist=new HashSet<>();
		}
		FilteredBox<String> oldArea=new FilteredBox<>(ourlist);
		FilteredBox<String> newArea=new FilteredBox<>(ourlist);
		Button button=new Button("Remove");

		
		oldArea.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:20px",-250*w,0*h,250*w,250*w,500*h,500*h,20*s));
		newArea.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:20px",250*w,0*h,250*w,250*w,500*h,500*h,20*s));
		button.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-redius:15px",500*w,-75*h,150*w,150*w,120*h,120*h,20*s));
	
		button.setOnAction(e->{
			String olda=oldArea.getBox().getSelectionModel().getSelectedItem();
			String newa=newArea.getBox().getSelectionModel().getSelectedItem();
			if(!olda.equals(newa)) {
				String[] answer= {"false","internal error"};
				try {
					
					ToolsBar.getInstance().getOut().println("remArea/"+olda.split(" ")[0]+"/"+newa.split(" ")[0]);
					answer=ToolsBar.getInstance().getIn().checkedReadLine().split("/");
					if(!Boolean.parseBoolean(answer[0])) {
						throw new FaildToRemoveException(answer[1]);
					}
					oldArea.getBox().getSelectionModel().clearSelection();
					oldArea.getBox().getItems().remove(olda);
					newArea.getBox().getSelectionModel().clearSelection();
					newArea.getBox().getItems().remove(olda);
					PopUp.display("Area removed successfully!", true);
				}
				catch (EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException | FaildToRemoveException e1) {
					PopUp.display(e1.getMessage(), false);
				}
				
			}
			else {
				PopUp.display("You cant choose the same area!", false);
			}
		});
		StackPane hbox=new StackPane();
		hbox.getChildren().addAll(oldArea,button,newArea);
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(hbox);

	}
	
}
