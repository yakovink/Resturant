package View;


/*thats class implements the whole database pane.
 *there is one scroll and 9 filtered lists inside it, one after each other.
 *in every list there are full information objects so the user can search by search by each parameter.**/

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;

import Exceptions.*;
import Utils.ClientReader;
import View.Nodes.*;
import application.App;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class AllDataMenu extends StackPane {
	
	private FilteredList<String> areas;
	private FilteredList<String> deliveries;
	private FilteredList<String> orders;
	private FilteredList<String> dishes;
	private FilteredList<String> componenets;
	private FilteredList<String> cooks;
	private FilteredList<String> deliveryPersons;
	private FilteredList<String> customers;
	private FilteredList<String> blackList;
	
	private BorderPane layout;
	
	private PrintWriter out;
	private ClientReader in;
	
	private double w;
	private double h;
	private double s;
	
	private VBox lists;
	private ScrollPane scroll1;
	


	
	public AllDataMenu() {
		super();
		this.setAlignment(Pos.CENTER);
		
		this.layout=App.getLayout();
		
		
		
		
		out=ToolsBar.getInstance().getOut();
		in=ToolsBar.getInstance().getIn();
		w=App.getW();
		h=App.getH();
		s=App.getS();
		ToolsBar.getInstance().getPlace().pushText("All data menu");
		
		
		Label area=new Label("Areas");
		Label delivery=new Label("Deliveries");
		Label order=new Label("Orders");
		Label dish=new Label("Dishes");
		Label component=new Label("Componenets");
		Label customer=new Label("Customers");
		Label deliveryPerson=new Label("Delivery persons");
		Label cook=new Label("Cooks");
		Label black=new Label("Black list");
		
		
		try {

		areas=new FilteredList<>(new LinkedList<>());
			try {
		
		out.println("getAreas");
		Arrays.asList(in.checkedReadLine().split(",")).forEach(e->{
			try {

				out.println("getRealArea/"+e.split(" ")[0]);
				String[] tmp=in.checkedReadLine().split("/");
				String[] deps=tmp[2].split(",");
				if(!deps[0].isBlank()) {
					for(int i=0;i<deps.length;i++) {
						String[] toString=deps[i].split(" ");
						deps[i]=toString[1]+" "+toString[2];
					}
				}
				String[] dels=tmp[3].split(",");
				if(!dels[0].isBlank()) {
					for(int i=0;i<dels.length;i++) {
						String[] toString=dels[i].split(" ");
						dels[i]=toString[1];
					}
				}
				String entry=tmp[1]+":   ID: "+tmp[0]+",   Time to deliver:  "+tmp[5]+"\nDelivery persons: "+String.join(", ", deps)+"\nDeliveries ID: "+String.join(", ", dels)+"\nNeighberhoods: "+tmp[4];
				areas.add(entry);
			} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {
				PopUp.display(e1.getMessage(),false);
			}
		});
		}
		catch(EmptyBufferException e1) {}

			deliveries=new FilteredList<>(new LinkedList<>());
			try {
		
		
		out.println("getDels");
		Arrays.asList(in.checkedReadLine().split(",")).forEach(e->{
			try {

				out.println("getRealDel/"+e.split(" ")[1]);
				String[] tmp=in.checkedReadLine().split("/");
				String[] dp=tmp[3].split(" ");
				tmp[3]=dp[1]+" "+dp[2];
				String entry="Delivery "+tmp[1]+",  Type: "+tmp[0]+"\nArea: "+tmp[4].split(" ")[1]+",  At date: "+tmp[5]+"\n"+tmp[6]+"  by "+tmp[3];
				if(tmp[0].equals("reg")) {
					String[] ords=tmp[2].split(",");
					if(ords[0].isBlank()) {
					for(int i=0;i<ords.length;i++) {
						String[] toString=ords[i].split(" ");
						ords[i]=toString[0];
					}
					}
					entry+="\nOrders: "+String.join(",", ords);
				}
				else {
					entry+="\nOrder: "+tmp[2].split(" ")[0]+",  postage:  "+tmp[7];
				}
				
				deliveries.add(entry);
			} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {
				PopUp.display(e1.getMessage(),false);
			}
		});
		}
		catch(EmptyBufferException e1) {}
			orders=new FilteredList<>(new LinkedList<>());
			try {
		
		out.println("getOrds");
		Arrays.asList(in.checkedReadLine().split(",")).forEach(e->{
			try {

				out.println("getRealOrd/"+e.split(" ")[0]);

				String[] tmp=in.checkedReadLine().split("/");

				String[] cust=tmp[1].split(" ");

				out.println("getCustArea/"+cust[0]);
				String custArea=in.checkedReadLine().split(" ")[1];

				tmp[1]=cust[1]+" "+cust[2];

				String[] dishes=tmp[2].split(",");

				if(!dishes[0].isBlank()) {
				for(int i=0;i<dishes.length;i++) {
					String[] d=dishes[i].split(" ");
					dishes[i]=d[1];
				}}

				
				
				String entry="Order "+tmp[0]+" for customer: "+cust[1]+" ("+custArea+")"+"\nOrdered on "+tmp[5]+", currect status: "+tmp[4]+"\nDishes: "+String.join(",", dishes);

				orders.add(entry);
			} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {
				PopUp.display(e1.getMessage(),false);
			}
		});
		}
		catch(EmptyBufferException e1) {}
			
			dishes=new FilteredList<>(new LinkedList<>());
			try {
		
		out.println("getDishes");
		Arrays.asList(in.checkedReadLine().split(",")).forEach(e->{
			try {
				
				out.println("getRealDish/"+e.split(" ")[0]);
				String[] tmp=in.checkedReadLine().split("-");
				String[] comps=tmp[3].split(",");
				if(comps[0].isBlank()) {
				for(int i=0;i<comps.length;i++) {
					String[] c=comps[i].split(" ");
					comps[i]=c[1];
				}
				}
				String entry=tmp[1]+":  ID: "+tmp[0]+",  Type: "+tmp[2]+"\nTime to make: "+tmp[4]+",  price: "+tmp[5]+"\nNutritional value- calories: "+tmp[6]+", colesterol: "+tmp[7]+", natran: "+tmp[8]+"\nComponenets: "+String.join(",", comps)+"\n"+((Boolean.parseBoolean(tmp[9]))?"public":"private");
				dishes.add(entry);
			} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {
				PopUp.display(e1.getMessage(),false);
			}
		});
		}
			
		catch(EmptyBufferException e1) {}

			componenets=new FilteredList<>(new LinkedList<>());
			try {
				
				out.println("getComps");
				Arrays.asList(in.checkedReadLine().split(",")).forEach(e->{
			try {
				out.println("getRealComp/"+e.split(" ")[0]);
				String[] tmp=in.checkedReadLine().split("/");
				
				
				String entry=tmp[1]+":   ID: "+tmp[0]+", price: "+tmp[4]+"\nNutritional value- calories: "+tmp[5]+", colesterol: "+tmp[6]+", natran: "+tmp[7]+"\n"+(Boolean.parseBoolean(tmp[2])?"Has Gluten   ":"")+(Boolean.parseBoolean(tmp[3])?"Has Lactose   ":"");
				componenets.add(entry);
			} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {
				PopUp.display(e1.getMessage(),false);
			}
		});
		}
		catch(EmptyBufferException e1) {}
			

			customers=new FilteredList<>(new LinkedList<>());		
			try {
		
		out.println("getCusts");
		Arrays.asList(in.checkedReadLine().split(",")).forEach(e->{
			try {
				out.println("getRealCust/"+e.split(" ")[0]);
				String[] tmp=in.checkedReadLine().split("!");
				
				String entry="Name: "+tmp[1]+" "+tmp[2]+",  ID: "+tmp[0]+"\nEmail: "+tmp[8]+",  Phone: "+tmp[9]+"\nBirthday: "+tmp[3]+", Gender: "+tmp[4]+", neighberhood: "+tmp[5]+"\nUsernae: "+tmp[10]+", password: "+tmp[11]+"\n"+(Boolean.parseBoolean(tmp[6])?"Sensitive to Gluten   ":"")+(Boolean.parseBoolean(tmp[7])?"Sensitive to Lactose   ":"");
				customers.add(entry);
			} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {
				PopUp.display(e1.getMessage(),false);
			}
		});
		}
		catch(EmptyBufferException e1) {}

		deliveryPersons=new FilteredList<>(new LinkedList<>());
		try {
		out.println("getDeps");
		Arrays.asList(in.checkedReadLine().split(",")).forEach(e->{
			try {
				out.println("getRealDep/"+e.split(" ")[0]);
				String[] tmp=in.checkedReadLine().split("/");
				tmp[6]=tmp[6].split(" ")[1];
				String entry="Name: "+tmp[1]+" "+tmp[2]+",  ID: "+tmp[0]+"\nEmail: "+tmp[7]+",  Phone: "+tmp[8]+"\nBirthday: "+tmp[3]+", Gender: "+tmp[4]+"\nVehicle: "+tmp[5]+", Area: "+tmp[6]+"\n"+(Boolean.parseBoolean(tmp[9])?"busy now":"free now");
				deliveryPersons.add(entry);
			} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {
				PopUp.display(e1.getMessage(),false);
			}
		});
		}
		catch(EmptyBufferException e1) {}

		cooks=new FilteredList<>(new LinkedList<>());
		try {

		out.println("getCooks");
		Arrays.asList(in.checkedReadLine().split(",")).forEach(e->{
			try {
				out.println("getRealCook/"+e.split(" ")[0]);
				String[] tmp=in.checkedReadLine().split("/");		
				String entry="Name: "+tmp[1]+" "+tmp[2]+",  ID: "+tmp[0]+"\nEmail: "+tmp[7]+",  Phone: "+tmp[8]+"\nBirthday: "+tmp[3]+", Gender: "+tmp[4]+", Expertise: "+tmp[5]+"\n"+(Boolean.parseBoolean(tmp[9])?"busy now":"free now")+(Boolean.parseBoolean(tmp[6])?",        is shef":"");
				cooks.add(entry);
			} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {
				PopUp.display(e1.getMessage(),false);
			}
		});
		}
		catch(EmptyBufferException e1) {}

		
		blackList=new FilteredList<>(new LinkedList<>());
		try {
		
		out.println("getBL");
		Arrays.asList(in.checkedReadLine().split(",")).forEach(e -> {
			try {
				out.println("getRealCust/"+e);
				String[] tmp=in.checkedReadLine().split("!");
				
				String entry="Name: "+tmp[1]+" "+tmp[2]+",  ID: "+tmp[0]+"\nEmail: "+tmp[8]+",  Phone: "+tmp[9]+"\nBirthday: "+tmp[3]+", Gender: "+tmp[4]+", neighberhood: "+tmp[5]+"\nUsernae: "+tmp[10]+", password: "+tmp[11]+"\n"+(Boolean.parseBoolean(tmp[6])?"Sensitive to Gluten   ":"")+(Boolean.parseBoolean(tmp[7])?"Sensitive to Lactose   ":"");
				blackList.add(entry);
			} catch (ReaderConnectionException | EmptyBufferException | CommandWasNotFoundException e1) {
				PopUp.display(e1.getMessage(),false);
			}
		});
		}
		catch(EmptyBufferException e1) {}
		

		lists=new VBox();

		lists.getChildren().addAll(area,areas,delivery,deliveries,order,orders,dish,dishes,component,componenets,customer,customers,deliveryPerson,deliveryPersons,cook,cooks,black,blackList);

		for(int i=0;i<lists.getChildren().size();i++) {
			Node list=lists.getChildren().get(i);
			if(i%2==1) {
				list.setStyle(String.format("-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;", 500*w,500*w,400*h,400*h,16*s));
			}
			else {
				list.setStyle(String.format("-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f", 200*w,200*w,50*h,50*h,20*s));
			}
		}


		lists.setPadding(new Insets(25,10,25,10));
		scroll1=new ScrollPane();
		scroll1.setContent(lists);

		scroll1.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f",-200*w,-50*h, 800*w,800*w,800*h,800*h));

		ToolsBar.getInstance().getLastStacks().push(App.getLayout().getCenter());

		this.getChildren().add(scroll1);
		layout.setCenter(this);
		
		}
		catch(ReaderConnectionException  | CommandWasNotFoundException e) {
			PopUp.display(e.getMessage(),false);
		} 
		
	}

	public FilteredList<String> getAreas() {
		return areas;
	}

	public void setAreas(FilteredList<String> areas) {
		this.areas = areas;
	}

	public FilteredList<String> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(FilteredList<String> deliveries) {
		this.deliveries = deliveries;
	}

	public FilteredList<String> getOrders() {
		return orders;
	}

	public void setOrders(FilteredList<String> orders) {
		this.orders = orders;
	}

	public FilteredList<String> getDishes() {
		return dishes;
	}

	public void setDishes(FilteredList<String> dishes) {
		this.dishes = dishes;
	}

	public FilteredList<String> getComponenets() {
		return componenets;
	}

	public void setComponenets(FilteredList<String> componenets) {
		this.componenets = componenets;
	}

	public FilteredList<String> getCooks() {
		return cooks;
	}

	public void setCooks(FilteredList<String> cooks) {
		this.cooks = cooks;
	}

	public FilteredList<String> getDeliveryPersons() {
		return deliveryPersons;
	}

	public void setDeliveryPersons(FilteredList<String> deliveryPersons) {
		this.deliveryPersons = deliveryPersons;
	}

	public FilteredList<String> getCustomers() {
		return customers;
	}

	public void setCustomers(FilteredList<String> customers) {
		this.customers = customers;
	}

	public FilteredList<String> getBlackList() {
		return blackList;
	}

	public void setBlackList(FilteredList<String> blackList) {
		this.blackList = blackList;
	}

	public ScrollPane getScroll() {
		return scroll1;
	}

	public void setScroll(ScrollPane scroll) {
		this.scroll1 = scroll;
	}

	
	

}
