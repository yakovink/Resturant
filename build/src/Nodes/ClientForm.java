package Nodes;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import Exceptions.FaildToAddException;
import Exceptions.FaildToRemoveException;
import Utils.Expertise;
import application.Main;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class ClientForm extends StackPane{
	
	private  PrintWriter out;
	private BufferedReader in;
	private String[] custArgs;
	private HashMap<String,Integer> cart;
	private static ClientForm form;
	private Button img;
	private BorderPane layout;
	
	
	public double orderPaymentCalc() {
		double[] sum= {0};
		cart.keySet().forEach(dish->{
			double price;
			try {
				out.println("getRealDish/"+dish);
				price = Double.parseDouble(in.readLine().split("-")[5]);
				sum[0]+=price*cart.get(dish);
			} catch (NumberFormatException | IOException e) {	}
		});
		return sum[0];
	}



	public ClientForm(String[] args) throws IOException {
		this.layout=Main.getLayout();
		out=ToolsBar.getInstance().getOut();
		in=ToolsBar.getInstance().getIn();
		cart=new HashMap<>();
		custArgs=args;
		layout.setStyle("-fx-background-image:url('file:images/AdminBackground.png');-fx-background-position: center center;-fx-background-repeat: stretch;");
		layout.setTop(ToolsBar.getInstance());
		
		Label Welcome =new Label(String.format("Welcome %s!", args[1]));
		
		
		
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
		logout.setOnAction(e->this.logout());
		
		
		accountSettings.setStyle("-fx-translate-x:-400;-fx-translate-y:-500;-fx-min-width:150;-fx-max-width:150;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14;-fx-background-radius:15px");
		myOrders.setStyle("-fx-translate-x:-200;-fx-translate-y:-500;-fx-min-width:150;-fx-max-width:150;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14;-fx-background-radius:15px");
		myCart.setStyle("-fx-translate-x:0;-fx-translate-y:-500;-fx-min-width:150;-fx-max-width:150;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14;-fx-background-radius:15px");
		popComp.setStyle("-fx-translate-x:200;-fx-translate-y:-500;-fx-min-width:150;-fx-max-width:150;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14;-fx-background-radius:15px");
		relevantDishList.setStyle("-fx-translate-x:-400;-fx-translate-y:-400;-fx-min-width:150;-fx-max-width:150;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14;-fx-background-radius:15px");
		cooks.setStyle("-fx-translate-x:-200;-fx-translate-y:-400;-fx-min-width:150;-fx-max-width:150;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14;-fx-background-radius:15px");
		dishList.setStyle("-fx-translate-x:0;-fx-translate-y:-400;-fx-min-width:150;-fx-max-width:150;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14;-fx-background-radius:15px");
		logout.setStyle("-fx-translate-x:200;-fx-translate-y:-400;-fx-min-width:150;-fx-max-width:150;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14;-fx-background-radius:15px");
		Welcome.setStyle("-fx-translate-x:0;-fx-translate-y:-300;-fx-min-width:150;-fx-max-width:150;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14;");
		img=new Button();
		img.setStyle("-fx-translate-x:-100;-fx-translate-y:-200;-fx-min-width:100;-fx-max-width:100;-fx-min-height:150;-fx-max-height:150;-fx-background-radius:15px;-fx-background-image:url(file:"+args[12]+");-fx-background-size: 150px 200px;-fx-background-repeat: no-repeat;-fx-background-position: center;");
		img.setOnAction(e->{
			try {
				FileChooser fileChooser=new FileChooser();
				fileChooser.setTitle("Choose Picture");
				fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
				custArgs[14]="images/customer"+custArgs[0]+".png";
				File selectedFile=fileChooser.showOpenDialog(new Stage());
				Files.copy(Path.of(selectedFile.getAbsolutePath()), Path.of(custArgs[14]),StandardCopyOption.REPLACE_EXISTING);
				BufferedImage bi=ImageIO.read(selectedFile);
				ImageIO.write(bi, "png", new File(custArgs[14]));
				
				img.setStyle("-fx-translate-x:400;-fx-translate-y:-400;-fx-min-width:150;-fx-max-width:150;-fx-min-height:200;-fx-max-height:200;-fx-background-radius:15px;-fx-background-image:url(file:"+custArgs[14]+");-fx-background-size: 150px 200px;-fx-background-repeat: no-repeat;-fx-background-position: center;");
				
			} catch (IOException e1) {
				if(custArgs[4].equals("Male")) {
					img.setStyle("-fx-translate-x:400;-fx-translate-y:-400;-fx-min-width:150;-fx-max-width:150;-fx-min-height:200;-fx-max-height:200;-fx-background-radius:15px;-fx-background-image:url(file:manDefault.png);-fx-background-size: 150px 200px;-fx-background-repeat: no-repeat;-fx-background-position: center;");
				}
				else {
					img.setStyle("-fx-translate-x:400;-fx-translate-y:-400;-fx-min-width:150;-fx-max-width:150;-fx-min-height:200;-fx-max-height:200;-fx-background-radius:15px;-fx-background-image:url(file:womanDefault.jpg);-fx-background-size: 150px 200px;-fx-background-repeat: no-repeat;-fx-background-position: center;");
				}
			}
		});
		this.getChildren().addAll(accountSettings,myOrders,myCart,popComp,relevantDishList,cooks,dishList,logout);
		layout.setLeft(img);
		
		layout.setCenter(this);
		
		
	}



	private void logout() {
		ToolsBar.getInstance().getLastStacks().clear();;
		out.println("logout");
		layout.setCenter(new Login(layout));

	}

	@SuppressWarnings("unused")
	private StackPane dishView(String[] args) throws IOException {
		StackPane stack=new StackPane();
		stack.setAlignment(Pos.CENTER);
		boolean[] changed=new boolean[1];
		changed[0]=false;
		
		
		Button img=new Button();
		
		FilteredList<String> componenets=new FilteredList<>(args[3].split(","));
		out.println("getComps");
		String[] tmp=in.readLine().split(",");
		FilteredList<String> moreComps=new FilteredList<>(tmp);
		Button addComp=new Button("add");
		Button remComp=new Button("remove");
		Label details=new Label(String.format("Time to make:   %s\nDishType:   %s\nPrice:   %s\ncalories:   %s\ncolesterol:   %s\nnatran:   ", args[4],args[2],args[5],args[6],args[7],args[8]));
		TextField newName=new TextField();
		Label status=new Label();
		Button addToCart=new Button("add to cart");
		
		
		moreComps.getList().getItems().removeAll(componenets.getList().getItems());
		newName.setPromptText("enter new name");
		newName.setVisible(false);
		addComp.setOnAction(e->{
			moreComps.getList().getSelectionModel().getSelectedItems().forEach(e2->{
				out.println("getRealComp/"+e2.split(" ")[0]);
				String[] compArgs;
				try {
					compArgs = in.readLine().split("/");
					for(String s:compArgs) {
					}
					args[5]=String.valueOf(Double.parseDouble(args[5])+Double.parseDouble(compArgs[4]));
					args[6]=String.valueOf(Double.parseDouble(args[6])+Double.parseDouble(compArgs[5]));
					args[7]=String.valueOf(Double.parseDouble(args[7])+Double.parseDouble(compArgs[6]));
					args[8]=String.valueOf(Double.parseDouble(args[8])+Double.parseDouble(compArgs[7]));
				} catch (IOException e1) {}
				
			});
			details.setText(String.format("Time to make:   %s\nDishType:   %s\nPrice:   %s\ncalories:   %s\ncolesterol:   %s\nnatran:   ", args[4],args[2],args[5],args[6],args[7],args[8]));
			componenets.getList().getItems().addAll(moreComps.getList().getSelectionModel().getSelectedItems());
			moreComps.getList().getItems().removeAll(moreComps.getList().getSelectionModel().getSelectedItems());
			newName.setVisible(true);
			changed[0]=true;
		});
		remComp.setOnAction(e->{
			componenets.getList().getSelectionModel().getSelectedItems().forEach(e2->{
				out.println("getRealComp/"+e2.split(" ")[0]);
				String[] compArgs;
				try {
					compArgs = in.readLine().split("/");
					args[5]=String.valueOf(Double.parseDouble(args[5])-Double.parseDouble(compArgs[4]));
					args[6]=String.valueOf(Double.parseDouble(args[6])-Double.parseDouble(compArgs[5]));
					args[7]=String.valueOf(Double.parseDouble(args[7])-Double.parseDouble(compArgs[6]));
					args[8]=String.valueOf(Double.parseDouble(args[8])-Double.parseDouble(compArgs[7]));
				} catch (IOException e1) {}
				
			});
			details.setText(String.format("Time to make:   %s\nDishType:   %s\nPrice:   %s\ncalories:   %s\ncolesterol:   %s\nnatran:   ", args[4],args[2],args[5],args[6],args[7],args[8]));
			moreComps.getList().getItems().addAll(componenets.getList().getSelectionModel().getSelectedItems());
			componenets.getList().getItems().removeAll(componenets.getList().getSelectionModel().getSelectedItems());
			newName.setVisible(true);
			changed[0]=true;
		});
		
		addToCart.setOnAction(e->{
			if(changed[0]) {
				
				try {
					HashSet<String> set=new HashSet<>();
					componenets.getList().getItems().forEach(e2->set.add(e2.split(" ")[0]));
					out.println("addDish/"+newName.getText()+"/"+args[2]+"/"+String.join(",", set)+"/"+args[4]+"/false");
					String[] answer;
					answer = in.readLine().split("/");
					
					if(Boolean.parseBoolean(answer[0])) {
						if(cart.containsKey(args[0])) {
							cart.put(args[0], cart.get(args[0]+1));
						}
						else {
							cart.put(args[0], 1);
						}
						status.setText("dish was added successfully");
						status.setTextFill(Color.GREEN);
					}
					else {
						throw new FaildToAddException();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					status.setText("please check your internaet connection");
					status.setTextFill(Color.RED);
				} catch (FaildToAddException e1) {
					// TODO Auto-generated catch block
					status.setText("bad inputs");
					status.setTextFill(Color.RED);
				}
			}
			else {
				if(cart.containsKey(args[0])) {
					cart.put(args[0], cart.get(args[0])+1);
				}
				else {
					cart.put(args[0], 1);
				}
				status.setText("dish was added successfully");
				status.setTextFill(Color.GREEN);
			}
		});
		
		componenets.setStyle("-fx-translate-x:-200;-fx-translate-y:0;-fx-min-width:150;-fx-max-width:150;-fx-min-height:200;-fx-max-height:200;-fx-background-radius:15px;-fx-font-size:12");
		moreComps.setStyle("-fx-translate-x:-50;-fx-translate-y:0;-fx-min-width:150;-fx-max-width:150;-fx-min-height:200;-fx-max-height:200;-fx-background-radius:15px;-fx-font-size:12");
		addComp.setStyle("-fx-translate-x:100;-fx-translate-y:-50;-fx-min-width:100;-fx-max-width:100;-fx-min-height:50;-fx-max-height:50;-fx-background-radius:15px;-fx-font-size:12");
		remComp.setStyle("-fx-translate-x:100;-fx-translate-y:50;-fx-min-width:100;-fx-max-width:100;-fx-min-height:50;-fx-max-height:50;-fx-background-radius:15px;-fx-font-size:12");
		details.setStyle("-fx-translate-x:400;-fx-translate-y:-100;-fx-min-width:150;-fx-max-width:150;-fx-min-height:200;-fx-max-height:200;-fx-background-radius:15px;-fx-font-size:12");
		newName.setStyle("-fx-translate-x:100;-fx-translate-y:150;-fx-min-width:150;-fx-max-width:150;-fx-min-height:50;-fx-max-height:50;-fx-background-radius:15px;-fx-font-size:12");
		status.setStyle("-fx-translate-x:400;-fx-translate-y:150;-fx-min-width:150;-fx-max-width:150;-fx-min-height:50;-fx-max-height:50;-fx-background-radius:15px;-fx-font-size:12");
		addToCart.setStyle("-fx-translate-x:400;-fx-translate-y:75;-fx-min-width:75;-fx-max-width:75;-fx-min-height:50;-fx-max-height:50;-fx-background-radius:15px;-fx-font-size:12");
		img.setStyle("-fx-translate-x:-400;-fx-translate-y:-100;-fx-min-width:30;-fx-max-width:30;-fx-min-height:50;-fx-max-height:50;-fx-background-radius:15px;-fx-background-radius:15px;-fx-background-size: 30px 30px;-fx-background-image:url("+args[9]+");-fx-background-repeat: no-repeat;-fx-background-position: center;");
		stack.getChildren().addAll(img,componenets,moreComps,addComp,remComp,details,newName,status,addToCart);
		return stack;
		
	}


	private void dlist() {
		
		try {
			out.println("getDishes");
			String[] dishes=in.readLine().split(",");
			VBox stacks=new VBox();
			Arrays.asList(dishes).forEach(dish->{
				try {
					out.println("getRealDish/"+dish.split(" ")[0]);
					StackPane d=this.dishView(in.readLine().split("-"));
					d.setStyle("-fx-min-width:1000;-fx-max-width:1000;-fx-min-height:400;-fx-max-height:400;");
					stacks.getChildren().add(d);
				} catch (IOException e) {}
			});
			ScrollPane scroll=new ScrollPane();
			scroll.setPrefWidth(1050);
			scroll.setPrefHeight(850);
			scroll.setContent(stacks);
			ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
			layout.setCenter(scroll);
		} 
		catch (IOException e) {}
	}
	
	
	private Label cookView(String[] args) {
		Label info=new Label(String.format("ID:  %9s        name:   %s %s\n birth:%10s       Gender:   %s\nExp:  %10s       %s", args[0],args[1],args[2],args[3],args[4],args[5],(Boolean.parseBoolean(args[6])?"shef":"")));
		info.setAlignment(Pos.CENTER);
		return info;
	}
	
	private void cooks() {
		
		try {
			VBox pane=new VBox();
			FilteredBox<Expertise> searchBox=new FilteredBox<>(Expertise.values());
			out.println("getCooks");
			String[] cooks=in.readLine().split(",");
			VBox labels=new VBox();
			Arrays.asList(cooks).forEach(cook->{
				try {
					out.println("getRealCook/"+cook.split(" ")[0]);
					Label info=this.cookView(in.readLine().split("/"));
					info.setStyle("-fx-min-width:500;-fx-max-width:500;-fx-min-height:200;-fx-max-height:200;-fx-font-size:14");
					labels.getChildren().add(info);
				} catch (IOException e) {}
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
					String[] releavnt=in.readLine().split(",");
					Arrays.asList(releavnt).forEach(cook->{
						try {
							out.println("getRealCook/"+cook.split(" ")[0]);
							Label info=this.cookView(in.readLine().split("/"));
							info.setStyle("-fx-min-width:500;-fx-max-width:500;-fx-min-height:200;-fx-max-height:200;-fx-font-size:14");
							labels.getChildren().add(info);
						} catch (IOException e2) {}
					});
				} catch (IOException e1) {		}
			});
			ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
			layout.setCenter(pane);
		} catch (IOException e) {	}
		
		

	}



	private void myDishList() {
		try {
			out.println("RelDishList/"+custArgs[0]);
			String[] dishes=in.readLine().split(",");
			VBox stacks=new VBox();
			Arrays.asList(dishes).forEach(dish->{
				try {
						out.println("getRealDish/"+dish.split(" ")[0]);
						StackPane d=this.dishView(in.readLine().split("-"));
						d.setStyle("-fx-min-width:1000;-fx-max-width:1000;-fx-min-height:400;-fx-max-height:400;");
						stacks.getChildren().add(d);
				} catch (IOException e) {}
			});
			ScrollPane scroll=new ScrollPane();
			scroll.setPrefWidth(1050);
			scroll.setPrefHeight(850);
			scroll.setContent(stacks);
			ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
			layout.setCenter(scroll);
		} 
		catch (IOException e) {}
	}

	private Label compView(String[] args) {
		Label info=new Label(String.format("ID:  %9s        name:   %s\n price:%10s       calories:   %s\nnatran:%10s       colesterol:   %s\n%s  %s", args[0],args[1],args[4],args[5],args[6],args[7],Boolean.parseBoolean(args[2])?"Contains gluten":"",Boolean.parseBoolean(args[3])?"Contains lactose":""));
		info.setAlignment(Pos.CENTER);
		return info;
		

	}

	private void popComp() {
		
		try {
			out.println("PopComp");
			String[] comps=in.readLine().split(",");
			VBox stacks=new VBox();
			Arrays.asList(comps).forEach(dish->{
				try {
						out.println("getRealComp/"+dish.split(" ")[0]);
						Label c=this.compView(in.readLine().split("/"));
						c.setStyle("-fx-min-width:500;-fx-max-width:500;-fx-min-height:200;-fx-max-height:200;-fx-font-size:14");
						stacks.getChildren().add(c);
				} catch (IOException e) {}
			});
			ScrollPane scroll=new ScrollPane();
			scroll.setPrefWidth(550);
			scroll.setPrefHeight(850);
			scroll.setContent(stacks);
			ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
			layout.setCenter(scroll);
		} catch (IOException e) {	}
	}
	
	private StackPane dishesInOrd(String[] dish) {
		StackPane stack=new StackPane();
		stack.setAlignment(Pos.CENTER);
		Button image=new Button();
		image.setStyle("-fx-translate-x:-100;-fx-translate-y:0;-fx-min-width:50;-fx-max-width:50;-fx-min-height:75;-fx-max-height:75;-fx-background-radius:15px;-fx-background-size: 30px 30px;-fx-background-image:url("+dish[9]+");-fx-background-repeat: no-repeat;-fx-background-position: center;");
		Label dishDetails=new Label(dish[1]+"    price:  "+dish[5]);
		dishDetails.setStyle("-fx-translate-x:50;-fx-translate-y:0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:12");
		image.setOnAction(e2->this.myDishList());
		stack.getChildren().addAll(image,dishDetails);
		return stack;
		
	}

	private StackPane ordView(String[] args) {
		
		try {
			StackPane stack=new StackPane();
			stack.setAlignment(Pos.CENTER);
			boolean undelivered=args[3].equals("null");
			out.println("calcOrderParams/"+args[0]);
			String[] params=in.readLine().split("/");
			Label status=new Label("ID:   "+args[0]+" status:  "+args[4]+"\ncreated at "+args[5]+" by "+args[1]+"\n"+((undelivered)?"was not delivered yet":"delivered with: "+args[3])+"\norder price:  "+params[0]+"   waiting time:   "+params[1]);
			Button cancel=new Button("cancel");
			Label cancelStatus=new Label();
			cancel.setOnAction(e->{

					try {
						if(args[4].equals("Delivered")||args[4].equals("inDeliveryProgress")) {
							cancelStatus.setText("Too late. you cannot cancel that order.");
							cancelStatus.setTextFill(Color.RED);
						}
						else if(args[4].equals("waitingForDelivering")) {
							out.println("remDel/"+args[3].split(" ")[1]);
							if(!Boolean.parseBoolean(in.readLine())) {
								throw new FaildToRemoveException();
							}
							out.println("remOrd/"+args[0]);
							if(!Boolean.parseBoolean(in.readLine())) {
								throw new FaildToRemoveException();
							}
							else {
								cancelStatus.setText("order removed Successfully");
								cancelStatus.setTextFill(Color.GREEN);
							}
						}
						else {
							out.println("remOrd/"+args[0]);
							if(!Boolean.parseBoolean(in.readLine())) {
								throw new FaildToRemoveException();
							}
							else {
								cancelStatus.setText("order removed Successfully");
								cancelStatus.setTextFill(Color.GREEN);
							}
						}
					} catch (IOException e1) {	} 
					catch (FaildToRemoveException e1) {
						cancelStatus.setText("you cannot cancel that order");
						cancelStatus.setTextFill(Color.RED);
					}
				
			});
			
			VBox dishes=new VBox();
			Arrays.asList(args[2].split(",")).forEach(e->{
				try {
					out.println("getRealDish/"+e.split(" ")[0]);
					String[] dish=in.readLine().split("-");
					StackPane node=dishesInOrd(dish);
					node.setStyle("-fx-min-width:250;-fx-max-width:250;-fx-min-height:75;-fx-max-height:75");
					dishes.getChildren().add(node);
				} catch (IOException e1) {		}
			});
			ScrollPane scroll=new ScrollPane();
			scroll.setPrefWidth(450);
			scroll.setPrefHeight(300);
			scroll.setContent(dishes);
			
			status.setStyle("-fx-translate-x:0;-fx-translate-y:0;-fx-min-width:250;-fx-max-width:250;-fx-min-height:200;-fx-max-height:200;-fx-font-size:12");
			scroll.setStyle("-fx-translate-x:400;-fx-tanslate-y:0;-fx-min-width:275;-fx-max-width:275;-fx-min-height:325;-fx-max-height:325");
			cancel.setStyle("-fx-translate-x:800;-fx-translate-y:-50;-fx-min-width:100;-fx-max-width:100;-fx-min-height:50;-fx-max-height:50;-fx-font-size:12;-fx-background-radius:15px");
			cancelStatus.setStyle("-fx-translate-x:800;-fx-translate-y:50;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:12");
			stack.getChildren().addAll(status,scroll,cancel,cancelStatus);
			return stack;
			
		} catch (IOException e) {return new StackPane();}

	}


	private void orders() {
		try {
			out.println("ordByCust/"+custArgs[0]);

			String[] orders=in.readLine().split(",");
			if(orders[0].equals("empty")) {
				Label empty=new Label("you have no orders yet");
				empty.setStyle("-fx-translate-x:0;-fx-translate-y:0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-fill-color:RED");
				empty.setAlignment(Pos.CENTER);
				ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
				layout.setCenter(empty);
				return;
			}
			VBox stacks=new VBox();
			Arrays.asList(orders).forEach(order->{
				try {
						out.println("getRealOrd/"+order.split(" ")[0]);
						StackPane c=this.ordView(in.readLine().split("/"));
						c.setStyle("-fx-min-width:1000;-fx-max-width:1000;-fx-min-height:350;-fx-max-height:350");
						stacks.getChildren().add(c);
				} catch (IOException e) {}
			});
			ScrollPane scroll=new ScrollPane();
			scroll.setPrefWidth(1050);
			scroll.setPrefHeight(850);
			scroll.setContent(stacks);
			scroll.setStyle("-fx-translate-x:0;-fx-translate-y:0");
			ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
			layout.setCenter(scroll);
		} catch (IOException e) {	}
	}
	
	
	private StackPane dishInCart(String dish) {
		StackPane[] stack= {new StackPane()};
		stack[0].setAlignment(Pos.CENTER);
		try {
			
			System.out.println(1);
			out.println("getRealDish/"+dish);
			String[] dish2=in.readLine().split("-");
			System.out.println(11);
			StackPane c=this.dishesInOrd(dish2);
			System.out.println(12);
			Button add=new Button("++");
			Button rem=new Button("-");
			System.out.println(cart.get(dish));
			Label amount=new Label("amount:  "+String.valueOf(cart.get(dish)*Double.parseDouble(dish2[5])));
			System.out.println(14);
			Label price=new Label("price:  "+String.valueOf(cart.get(dish)*Integer.parseInt(dish)));
			System.out.println(2);
			add.setOnAction(e->{
				System.out.println(3);
				cart.put(dish, cart.get(dish)+1);
				System.out.println(4);
				amount.setText(String.valueOf(cart.get(dish)));
				System.out.println(5);
				price.setText(String.valueOf(cart.get(dish)*Integer.parseInt(dish)));
				System.out.println(6);
				if(cart.get(dish)==1) {
					rem.setDisable(false);
				}
			});
			System.out.println(1);
			rem.setOnAction(e->{
				cart.put(dish, cart.get(dish)-1);
				amount.setText(String.valueOf(cart.get(dish)));
				price.setText(String.valueOf(cart.get(dish)*Integer.parseInt(dish)));
				if(cart.get(dish)==0) {
					rem.setDisable(true);
				}
			});
			c.setStyle("-fx-translate-x:-300;-fx-translate-y:0;-fx-min-width:250;-fx-max-width:250;-fx-min-height:250;-fx-max-height:250");
			add.setStyle("-fx-translate-x:-100;-fx-translate-y:0;-fx-min-width:50;-fx-max-width:50;-fx-min-height:50;-fx-max-height:50;-fx-font-size:25;-fx-fill-collor:GREEN");
			rem.setStyle("-fx-translate-x:-50;-fx-translate-y:0;-fx-min-width:50;-fx-max-width:50;-fx-min-height:50;-fx-max-height:50;-fx-font-size:25;-fx-fill-collor:RED");
			amount.setStyle("-fx-translate-x:100;-fx-translate-y:-50;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14;");
			price.setStyle("-fx-translate-x:100;-fx-translate-y:50;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14;");
			stack[0].getChildren().addAll(c,add,rem,price);
		} catch (IOException e) {}
		return stack[0];
	}
	

	private void cart() {
		StackPane pane=new StackPane();
		if(cart.isEmpty()) {
			Label empty=new Label("you have no dishes yet");
			empty.setStyle("-fx-translate-x:0;-fx-translate-y:0;-fx-min-width:200;-fx-max-width:200;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-fill-color:RED");
			empty.setAlignment(Pos.CENTER);
			ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
			layout.setCenter(empty);
			return;
		}

		VBox stacks=new VBox();
		cart.keySet().forEach(dish->{
			StackPane c=dishInCart(dish);
			c.setStyle("-fx-min-width:500;-fx-max-width:500;-fx-min-height:300;-fx-max-height:300;");
			stacks.getChildren().add(c);
		});
		ScrollPane scroll=new ScrollPane();
		
		Button orderNow=new Button("order now!");
		Label forPay=new Label("Total payment: "+orderPaymentCalc());
		Button refresh=new Button("refresh");
		Label status=new Label();
		scroll.setPrefWidth(550);
		scroll.setPrefHeight(400);
		scroll.setContent(stacks);
		scroll.setStyle("-fx-translate-x:-200;-fx-translate-y:0;-fx-min-width:550;-fx-max-width:550;-fx-min-height:400;-fx-max-height:400");
		orderNow.setStyle("-fx-translate-x:200;-fx-translate-y:200;-fx-min-width:150;-fx-max-width:150;-fx-min-height:100;-fx-max-height:100;-fx-font-size:14");
		forPay.setStyle("-fx-translate-x:200;-fx-translate-y:100;-fx-min-width:150;-fx-max-width:150;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14");
		refresh.setStyle("-fx-translate-x:300;-fx-translate-y:100;-fx-min-width:50;-fx-max-width:50;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14");
		status.setStyle("-fx-translate-x:200;-fx-translate-y:300;-fx-min-width:150;-fx-max-width:150;-fx-min-height:50;-fx-max-height:50;-fx-font-size:14");
		
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
				String[] answer=in.readLine().split("/");
				if(Boolean.parseBoolean(answer[0])) {
					cart.clear();
					status.setText("order added successfully!");
					status.setTextFill(Color.GREEN);
					cart();
				}
				else {
					status.setText(answer[1]);
					status.setTextFill(Color.RED);
				}
			} catch (IOException e1) {	}
 		});
		
		refresh.setOnAction(e->cart());
		
		
		pane.getChildren().addAll(scroll,orderNow,forPay,refresh,status);
		ToolsBar.getInstance().getLastStacks().push(layout.getCenter());
		layout.setCenter(pane);
	}



	private void settings() {
		EditMenu edit=new EditMenu(layout);
		edit.editCustomer();
		
	}



	public static ClientForm getInstance(BorderPane layout,String[] args) {
		if(form==null) {
			try {
				form=new ClientForm(args);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return form;
	}
}
