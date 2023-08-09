package View;



/*this is the customer form class and its mehtods.
 * the constractor is the main form and its contains 8 buttons:
 * one for each customer method and more one for logout.
 * at the background there is running clip with dishes picture.
 * that class has singelton show.**/

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import Exceptions.*;

import Utils.*;
import Utils.RunGif;
import View.Nodes.FilteredBox;
import View.Nodes.FilteredList;
import View.Nodes.IndTextField;
import View.Nodes.ToolsBar;
import application.App;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ClientForm extends StackPane{
	
	private  PrintWriter out;
	private ClientReader in;
	private static String[] custArgs;
	private HashMap<String,Integer> cart;
	private static ClientForm form;
	private BorderPane layout;
	private static RunGif backroundGif;
	private double w;
	private double h;
	private double s;

	public static ClientForm getForm() {
		return form;
	}



	public static void setForm(ClientForm form) {
		ClientForm.form = form;
	}

	/*that method calculate the total payment on Customer cart/orders pane**/

	public double orderPaymentCalc() {
		double[] sum= {0};
		cart.keySet().forEach(dish->{
			double price;
			try {
				out.println("getRealDish/"+dish);
				price = Double.parseDouble(in.checkedReadLine().split("-")[5]);
				sum[0]+=price*cart.get(dish);
			} catch (NumberFormatException | ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e) {	PopUp.display(e.getMessage(),false);}
		});
		return sum[0];
	}



	private ClientForm(String[] args) throws ReaderConnectionException {
		
		w=App.getW();
		h=App.getH();
		s=App.getS();
		this.layout=App.getLayout();
		out=ToolsBar.getInstance().getOut();
		in=ToolsBar.getInstance().getIn();
		cart=new HashMap<>();
		custArgs=args;
		
		
		
		Button accountSettings=new Button("Settings");
		Button myOrders=new Button("my orders");
		Button myCart=new Button("my cart");
		Button popComp=new Button("popular componenets");
		Button relevantDishList=new Button("relevant dish list");
		Button cooks=new Button("Resaturant cooks");
		Button dishList=new Button("full dish list");
		Button logout=new Button("logout");
		
		
		accountSettings.setOnAction(e->this.settings());
		myOrders.setOnAction(e->this.orders());
		myCart.setOnAction(e->this.cart());
		popComp.setOnAction(e->this.popComp());
		relevantDishList.setOnAction(e->this.myDishList());
		cooks.setOnAction(e->this.cooks());
		dishList.setOnAction(e->this.dlist());
		logout.setOnAction(e->{
			ToolsBar.getInstance().getLastStacks().clear();
			layout.setCenter(new Login());
		});
		
		
		accountSettings.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-400*w,-400*h,250*w,250*w,150*h,150*h,14*s));
		myOrders.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-200*w,-400*h,250*w,250*w,150*h,150*h,14*s));
		myCart.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,-400*h,250*w,250*w,50*h,150*h,14*s));
		popComp.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",200*w,-400*h,250*w,250*w,150*h,150*h,14*s));
		relevantDishList.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-400*w,-250*h,250*w,250*w,150*h,50*h,14*s));
		cooks.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",-200*w,-250*h,250*w,250*w,150*h,150*h,14*s));
		dishList.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,-250*h,250*w,250*w,150*h,150*h,14*s));
		logout.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",200*w,-250*h,250*w,250*w,150*h,150*h,14*s));

		this.getChildren().addAll(accountSettings,myOrders,myCart,popComp,relevantDishList,cooks,dishList,logout);
		layout.setCenter(this);
		
		
	}

	/*that method designe each dish view while it showed in dish lists**/

	@SuppressWarnings("unused")
	private StackPane dishView(String[] args) throws ReaderConnectionException, EmptyBufferException, CommandWasNotFoundException {
		StackPane stack=new StackPane();
		stack.setAlignment(Pos.CENTER);
		boolean[] changed=new boolean[1];
		changed[0]=false;
		
		
		FilteredList<String> componenets=new FilteredList<>(args[3].split(","));
		out.println("getComps");
		String[] tmp=in.checkedReadLine().split(",");
		FilteredList<String> moreComps=new FilteredList<>(tmp);
		Button addComp=new Button("add");
		Button remComp=new Button("remove");
		Label details=new Label(String.format("dish:   %s\nTime to make:   %s\nDishType:   %s\nPrice:   %s\ncalories:   %s\ncolesterol:   %s\nnatran:   %s", args[1],args[4],args[2],args[5],args[6],args[7],args[8]));
		IndTextField newName=new IndTextField();
		Button addToCart=new Button("add to cart");
		
		
		moreComps.removeAll(componenets.getList().getItems());
		newName.setPromptText("enter new name");
		newName.setVisible(false);
		addComp.setOnAction(e->{
			moreComps.getList().getSelectionModel().getSelectedItems().forEach(e2->{
				out.println("getRealComp/"+e2.split(" ")[0]);
				String[] compArgs;
				try {
					compArgs = in.checkedReadLine().split("/");
					for(String s:compArgs) {
					}
					args[5]=String.valueOf(Double.parseDouble(args[5])+Double.parseDouble(compArgs[4]));
					args[6]=String.valueOf(Double.parseDouble(args[6])+Double.parseDouble(compArgs[5]));
					args[7]=String.valueOf(Double.parseDouble(args[7])+Double.parseDouble(compArgs[6]));
					args[8]=String.valueOf(Double.parseDouble(args[8])+Double.parseDouble(compArgs[7]));
				} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {PopUp.display(e1.getMessage(),false);}
				
			});
			details.setText(String.format("Time to make:   %s\nDishType:   %s\nPrice:   %s\ncalories:   %s\ncolesterol:   %s\nnatran:   ", args[4],args[2],args[5],args[6],args[7],args[8]));
			componenets.addAll(moreComps.getList().getSelectionModel().getSelectedItems());
			moreComps.removeAll(moreComps.getList().getSelectionModel().getSelectedItems());
			newName.setVisible(true);
			changed[0]=true;
		});
		remComp.setOnAction(e->{
			componenets.getList().getSelectionModel().getSelectedItems().forEach(e2->{
				out.println("getRealComp/"+e2.split(" ")[0]);
				String[] compArgs;
				try {
					compArgs = in.checkedReadLine().split("/");
					args[5]=String.valueOf(Double.parseDouble(args[5])-Double.parseDouble(compArgs[4]));
					args[6]=String.valueOf(Double.parseDouble(args[6])-Double.parseDouble(compArgs[5]));
					args[7]=String.valueOf(Double.parseDouble(args[7])-Double.parseDouble(compArgs[6]));
					args[8]=String.valueOf(Double.parseDouble(args[8])-Double.parseDouble(compArgs[7]));
				} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {PopUp.display(e1.getMessage(),false);}
				
			});
			details.setText(String.format("Time to make:   %s\nDishType:   %s\nPrice:   %s\ncalories:   %s\ncolesterol:   %s\nnatran:   ", args[4],args[2],args[5],args[6],args[7],args[8]));
			moreComps.addAll(componenets.getList().getSelectionModel().getSelectedItems());
			componenets.removeAll(componenets.getList().getSelectionModel().getSelectedItems());
			newName.setVisible(true);
			changed[0]=true;
		});
		
		addToCart.setOnAction(e->{
			if(changed[0]) {
				String[] answer= {"internal error","false"};
				try {
					HashSet<String> set=new HashSet<>();
					componenets.getList().getItems().forEach(e2->set.add(e2.split(" ")[0]));
					out.println("addDish/"+newName.getText()+"/"+args[2]+"/"+String.join(",", set)+"/"+args[4]+"/false");
					
					answer = in.checkedReadLine().split("/");
					
					if(Boolean.parseBoolean(answer[0])) {
						if(cart.containsKey(args[0])) {
							cart.put(args[0], cart.get(args[0]+1));
						}
						else {
							cart.put(args[0], 1);
						}
						PopUp.display("dish was added successfully",true);
					}
					else {
						throw new FaildToAddException();
					}
				} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {
					// TODO Auto-generated catch block
					PopUp.display(e1.getMessage(),false);
				} catch (FaildToAddException e1) {
					// TODO Auto-generated catch block
					PopUp.display(answer[1],false);
				}
			}
			else {
				if(cart.containsKey(args[0])) {
					cart.put(args[0], cart.get(args[0])+1);
				}
				else {
					cart.put(args[0], 1);
				}
				PopUp.display("dish was added successfully",true);
			}
		});
		
		componenets.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-background-radius:15px;-fx-font-size:%f",-200*w,0*h,150*w,150*w,200*h,200*h,12*s));
		moreComps.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-background-radius:15px;-fx-font-size:%f",-50*w,0*h,150*w,150*w,200*h,200*h,12*s));
		addComp.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-background-radius:15px;-fx-font-size:%f",100*w,-50*h,100*w,100*w,50*h,50*h,12*s));
		remComp.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-background-radius:15px;-fx-font-size:%f",100*w,50*h,100*w,100*w,50*h,50*h,12*s));
		details.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-background-radius:15px;-fx-font-size:%f",400*w,-100*h,150*w,150*w,200*h,200*h,12*s));
		newName.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-background-radius:15px;-fx-font-size:%f",100*w,150*h,150*w,150*w,50*h,50*h,12*s));
		addToCart.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-background-radius:15px;-fx-font-size:%f",400*w,75*h,125*w,125*w,50*h,50*h,12*s));
		stack.getChildren().addAll(componenets,moreComps,addComp,remComp,details,newName,addToCart);
		return stack;
		
	}


	/*that is the full dish list method. it collects dishView panes and put them in one scroll**/
	
	
	private void dlist() {
		ToolsBar.getInstance().getPlace().pushText("Dish list");
		try {
			out.println("getDishes");
			String[] dishes=in.checkedReadLine().split(",");
			if(dishes[0].equals("empty")) {
				PopUp.display("there is no dishes", false);
				return;
			}
			VBox stacks=new VBox();
			Arrays.asList(dishes).forEach(dish->{
				try {
					out.println("getRealDish/"+dish.split(" ")[0]);
					StackPane d=this.dishView(in.checkedReadLine().split("-"));
					d.setStyle(String.format("-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;",1000*w,1000*w,400*h,400*h));
					stacks.getChildren().add(d);
				} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e) {PopUp.display(e.getMessage(),false);}
			});
			ScrollPane scroll=new ScrollPane();
			scroll.setPrefWidth(1050*w);
			scroll.setPrefHeight(850*h);
			scroll.setContent(stacks);
			ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
			layout.setCenter(scroll);
		} 
		catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e) {PopUp.display(e.getMessage(),false);}
	}
	
	
	/*that method designe each cook view while it showed in cooks lists**/
	
	private Label cookView(String[] args) {
		Label info=new Label(String.format("ID:  %9s        name:   %s %s\n birth:%10s       Gender:   %s\nExp:  %10s       %s", args[0],args[1],args[2],args[3],args[4],args[5],(Boolean.parseBoolean(args[6])?"shef":"")));
		info.setAlignment(Pos.CENTER);
		return info;
	}
	
	/*that is the full cooks list method, sorted by their expertise. it collects cookView panes and put them in one scroll**/
	
	private void cooks() {
		ToolsBar.getInstance().getPlace().pushText("Cooks list");
		try {
			VBox pane=new VBox();
			FilteredBox<Expertise> searchBox=new FilteredBox<>(Expertise.values());
			out.println("getCooks");
			String[] cooks=in.checkedReadLine().split(",");
			if(cooks[0].equals("empty")) {
				PopUp.display("there is no cooks", false);
				return;
			}
			VBox labels=new VBox();
			Arrays.asList(cooks).forEach(cook->{
				try {
					out.println("getRealCook/"+cook.split(" ")[0]);
					Label info=this.cookView(in.checkedReadLine().split("/"));
					info.setStyle(String.format("-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f",500*w,500*w,200*h,200*h,14*s));
					labels.getChildren().add(info);
				} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e) {PopUp.display(e.getMessage(),false);}
			});
			ScrollPane scroll=new ScrollPane();
			scroll.setPrefWidth(550);
			scroll.setPrefHeight(850);
			scroll.setContent(labels);
			pane.getChildren().addAll(searchBox,scroll);
			
			searchBox.getBox().getSelectionModel().selectedItemProperty().addListener(e->{
				
				try {
					labels.getChildren().clear();
					out.println("CookByExp/"+searchBox.getBox().getSelectionModel().getSelectedItem().name());
					String[] releavnt=in.checkedReadLine().split(",");
					Arrays.asList(releavnt).forEach(cook->{
						try {
							out.println("getRealCook/"+cook.split(" ")[0]);
							Label info=this.cookView(in.checkedReadLine().split("/"));
							info.setStyle(String.format("-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f",500*w,500*w,200*h,200*h,14*s));
							labels.getChildren().add(info);
						} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e2) {PopUp.display(e2.getMessage(),false);}
					});
				} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {	PopUp.display(e1.getMessage(),false);	}
			});
			ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
			layout.setCenter(pane);
		} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e) {PopUp.display(e.getMessage(),false);	}
		
		

	}

	/*that is the relevant dish list method. it collects relevent dishView panes and put them in one scroll**/

	private void myDishList() {
		ToolsBar.getInstance().getPlace().pushText("My dish list");
		try {
			out.println("RelDishList/"+custArgs[0]);
			String[] dishes=in.checkedReadLine().split(",");
			if(dishes[0].equals("empty")) {
				PopUp.display("there is no dishes yet", false);
				return;
			}
			VBox stacks=new VBox();
			Arrays.asList(dishes).forEach(dish->{
				try {
						out.println("getRealDish/"+dish.split(" ")[0]);
						StackPane d=this.dishView(in.checkedReadLine().split("-"));
						d.setStyle(String.format("-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;",1000*w,1000*w,400*h,400*h));
						stacks.getChildren().add(d);
				} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e) {PopUp.display(e.getMessage(),false);}
			});
			ScrollPane scroll=new ScrollPane();
			scroll.setPrefWidth(1050);
			scroll.setPrefHeight(850);
			scroll.setContent(stacks);
			ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
			layout.setCenter(scroll);
		} 
		catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e) {PopUp.display(e.getMessage(),false);}
	}

	/*that method desigen each component view while it showed in componenets lists**/
	
	private Label compView(String[] args) {
		Label info=new Label(String.format("ID:  %9s        name:   %s\n price:%10s       calories:   %s\nnatran:%10s       colesterol:   %s\n%s  %s", args[0],args[1],args[4],args[5],args[6],args[7],Boolean.parseBoolean(args[2])?"Contains gluten":"",Boolean.parseBoolean(args[3])?"Contains lactose":""));
		info.setAlignment(Pos.CENTER);
		return info;
		

	}

	/*that is the popular component list method. it collects compView panes, sort  them by popularity and put them in one scroll**/
	
	
	private void popComp() {
		ToolsBar.getInstance().getPlace().pushText("Popular componenets");
		try {
			out.println("PopComp");
			String[] comps=in.checkedReadLine().split(",");
			if(comps[0].equals("empty")) {
				PopUp.display("there is no componenets yet", false);
				return;
			}
			VBox stacks=new VBox();
			Arrays.asList(comps).forEach(dish->{
				try {
						out.println("getRealComp/"+dish.split(" ")[0]);
						Label c=this.compView(in.checkedReadLine().split("/"));
						c.setStyle(String.format("-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f",500*w,500*w,200*h,200*h,14*s));
						stacks.getChildren().add(c);
				} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e) {PopUp.display(e.getMessage(),false);}
			});
			ScrollPane scroll=new ScrollPane();
			scroll.setPrefWidth(550);
			scroll.setPrefHeight(850);
			scroll.setContent(stacks);
			ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
			layout.setCenter(scroll);
		} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e) {	PopUp.display(e.getMessage(),false);}
	}
	
	/*that method desigen each dish view while it showed in order pane**/
	
	private Label dishesInOrd(String[] dish) {

		Label dishDetails=new Label(dish[1]+"    price:  "+dish[5]);
		dishDetails.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f",50*w,0*h,200*w,200*w,50*h,50*h,12*s));
		return dishDetails;
		
	}

	/*that method desigen each order view while it showed in orders pane**/
	
	private StackPane ordView(String[] args) {
		
		
		try {
			StackPane stack=new StackPane();
			stack.setAlignment(Pos.CENTER);
			boolean undelivered=args[3].equals("null");
			out.println("calcOrderParams/"+args[0]);
			String[] params=in.checkedReadLine().split("/");
			Label status=new Label("ID:   "+args[0]+" :  "+args[4]+"\ncreated at "+args[5]+" by "+args[1]+"\n"+((undelivered)?"was not delivered yet":"delivered with: "+args[3])+"\norder price:  "+params[0]+"   waiting time:   "+params[1]);
			Button cancel=new Button("cancel");
			cancel.setOnAction(e->{
				String[] answer= {"internal error","false"};
					try {
						if(args[4].equals("Delivered")||args[4].equals("inDeliveryProgress")) {
							PopUp.display("Too late. you cannot cancel that order",false);
						}
						else if(args[4].equals("waitingForDelivering")) {
							out.println("remDel/"+args[3].split(" ")[1]);
							answer=in.checkedReadLine().split("/");
							if(!Boolean.parseBoolean(answer[0])) {
								throw new FaildToRemoveException();
							}
							out.println("remOrd/"+args[0]);
							answer=in.checkedReadLine().split("/");
							if(!Boolean.parseBoolean(answer[0])) {
								throw new FaildToRemoveException();
							}
							else {
								PopUp.display("order removed Successfully",true);
							}
						}
						else {
							out.println("remOrd/"+args[0]);
							answer=in.checkedReadLine().split("/");
							if(!Boolean.parseBoolean(answer[0])) {
								throw new FaildToRemoveException();
							}
							else {
								PopUp.display("order removed Successfully",true);
							}
						}
					} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {PopUp.display(e1.getMessage(),false);} 
					catch (FaildToRemoveException e1) {
						PopUp.display(answer[1],false);
					}
				
			});
			VBox dishes=new VBox();
			Arrays.asList(args[2].split(",")).forEach(e->{
				try {
					out.println("getRealDish/"+e.split(" ")[0]);
					String[] dish=in.checkedReadLine().split("-");
					
					Label node=dishesInOrd(dish);
					node.setStyle(String.format("-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f",250*w,250*w,75*h,75*h));
					dishes.getChildren().add(node);
				} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {	PopUp.display(e1.getMessage(),false);	}
			});
			ScrollPane scroll=new ScrollPane();
			scroll.setContent(dishes);
			scroll.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f",400*w,0*h,275*w,275*w,325*h,325*h));
			cancel.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",800*w,100*h,100*w,100*w,50*h,50*h,12*s));
			status.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f",0*w,-100*h,400*w,400*w,200*h,200*h,12*s));
			stack.getChildren().addAll(scroll,cancel,status);
			return stack;
			
		} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e) {
			PopUp.display(e.getMessage(),false);
			return new StackPane();}

	}

	/*that is the orders by customer quota for that specific customer. it collects compView panes, sort  them by popularity and put them in one scroll**/

	private void orders() {
		
		ToolsBar.getInstance().getPlace().pushText("My orders");
		try {
			out.println("ordByCust/"+custArgs[0]);

			String[] orders=in.checkedReadLine().split(",");
			if(orders[0].equals("empty")) {
				PopUp.display("there is no orders yet", false);
				return;
			}
			VBox stacks=new VBox();
			Arrays.asList(orders).forEach(order->{
				try {
						out.println("getRealOrd/"+order.split(" ")[0]);
						StackPane c=this.ordView(in.checkedReadLine().split("/"));
						c.setStyle(String.format("-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f",1000*w,1000*w,350*h,350*h));
						stacks.getChildren().add(c);
				} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e) {PopUp.display(e.getMessage(),false);}
			});
			ScrollPane scroll=new ScrollPane();
			scroll.setPrefWidth(1050);
			scroll.setPrefHeight(850);
			scroll.setContent(stacks);
			scroll.setStyle("-fx-translate-x:0;-fx-translate-y:0");
			ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
			layout.setCenter(scroll);
		} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e) {PopUp.display(e.getMessage(),false);	}
	}
	
	/*that method desigen each dish view while it showed in order cart**/
	
	private StackPane dishInCart(String dish) {
		StackPane[] stack= {new StackPane()};
		stack[0].setAlignment(Pos.CENTER);
		try {
			
			out.println("getRealDish/"+dish);
			String[] dish2=in.checkedReadLine().split("-");
			Label c=this.dishesInOrd(dish2);
			Button add=new Button("+");
			Button rem=new Button("-");
			Label amount=new Label("amount:  "+String.valueOf(cart.get(dish)));
			add.setOnAction(e->{
				cart.put(dish, cart.get(dish)+1);
				amount.setText("amount:  "+String.valueOf(cart.get(dish)));
				if(cart.get(dish)==1) {
					rem.setDisable(false);
				}
			});
			rem.setOnAction(e->{
				cart.put(dish, cart.get(dish)-1);
				amount.setText("amount:  "+String.valueOf(cart.get(dish)));
				if(cart.get(dish)==0) {
					rem.setDisable(true);
				}
			});
			c.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f",100*w,0*h,250*w,250*w,250*h,250*h,14*s));
			add.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-fill-collor:GREEN",-150*w,0*h,75*w,75*w,75*h,75*h,20*s));
			rem.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-fill-collor:RED",-75*w,0*h,75*w,75*w,75*h,75*h,20*s));
			amount.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",200*w,0*h,100*w,100*w,50*h,50*h,14*s));
			stack[0].getChildren().addAll(c,add,rem,amount);
		} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e) {PopUp.display(e.getMessage(),false);}
		return stack[0];
	}
	
	/*that is the the cart pane. it collects all dishes that the customer coose but didnt ordered yet and put them in one scroll**/

	private void cart() {
		
		ToolsBar.getInstance().getPlace().pushText("My cart");
		StackPane pane=new StackPane();
		pane.setAlignment(Pos.CENTER);
		if(cart.isEmpty()) {
			PopUp.display("you have no dishes yet",false);
			return;
		}

		VBox stacks=new VBox();
		cart.keySet().forEach(dish->{
			StackPane c=dishInCart(dish);
			c.setStyle(String.format("-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;",400*w,400*w,75*h,75*h));
			stacks.getChildren().add(c);
		});
		ScrollPane scroll=new ScrollPane();
		
		Button orderNow=new Button("order now!");
		Label forPay=new Label("Total payment: "+orderPaymentCalc());
		Button refresh=new Button("refresh");
		scroll.setPrefWidth(550);
		scroll.setPrefHeight(400);
		scroll.setContent(stacks);
		scroll.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f",-200*w,0*h,450*w,450*w,450*h,450*h));
		orderNow.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f",200*w,200*h,150*w,150*w,100*h,100*h,14*s));
		forPay.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f",200*w,100*h,150*w,150*w,50*h,50*h,14*s));
		refresh.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f",350*w,200*h,150*w,150*w,100*h,100*h,14*s));
		
		orderNow.setOnAction(e->{
			try {
				LinkedList<String> dishes=new LinkedList<>();
				Iterator<String> iter=cart.keySet().iterator();
				
				while(iter.hasNext()){
					String dish=iter.next();
					for(int i=0;i<cart.get(dish);i++) {
						dishes.add(dish);
					}
					if(cart.get(dish)==0) {
						iter.remove();
					}
				}
				out.println("addOrd/"+custArgs[0]+"/"+String.join(",", dishes));
				String[] answer=in.checkedReadLine().split("/");
				if(Boolean.parseBoolean(answer[0])) {
					cart.clear();
					PopUp.display("order added successfully!",true);
					
					
				}
				else {
					PopUp.display(answer[1],false);
				}
			} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {
				PopUp.display(e1.getMessage(),false);
			}
 		});
		
		refresh.setOnAction(e->cart());
		
		pane.getChildren().addAll(scroll,orderNow,forPay,refresh);
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(pane);
	}

	
	/*this is the ccount settings edit method. it reference to editCustomer in EditMenu class.**/

	private void settings() {
		ToolsBar.getInstance().getPlace().pushText("Account settings");
		EditMenu.customerForm(custArgs[0]);
	}



	public static ClientForm getInstance(String[] args) {
		if(form==null||custArgs==null||!custArgs[0].equals(args[0])) {
			try {
				form=new ClientForm(args);
			} catch (ReaderConnectionException e) {
				System.exit(0);
			}
			if(backroundGif==null) {
				backroundGif=RunGif.getInstance();
			}
			backroundGif.start();
		}
		ToolsBar.getInstance().getPlace().pushText(String.format("Welcome %s!", args[1]));
		ToolsBar.getInstance().getLastStacks().push(App.getLayout().getCenter());
		return form;
	}
}
