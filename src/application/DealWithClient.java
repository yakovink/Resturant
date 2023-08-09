package application;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import java.util.TreeSet;


import Exceptions.FaildToAddException;
import Exceptions.FaildToRemoveException;
import Exceptions.IllegalCustomerException;
import Exceptions.SensitiveException;
import Exceptions.WrongAreaException;
import Model.*;
import Utils.DishType;
import Utils.Expertise;
import Utils.Gender;
import Utils.Neighberhood;
import Utils.ServerAction;
import Utils.Vehicle;


/*deal with client class used tp deal a specific client. this is the external thread that the main server thread opens for clients.
 * the class contains input and output for the server socket side, HashMap with all possable client commands and output.
 * when run method is active, the thread run in while-true loop until the client is disconnect.
 * the methods gets text arguments from the clients, analize them to internal objects and use the as the client needs.
 *  for last, they save their output to "output" static string and the loop thread write the output to the client**/


public class DealWithClient extends Thread{
	
	private BufferedReader in;
	private PrintWriter out;
	private Socket cl;
	private static HashMap<String,ServerAction> commands=new HashMap<>();
	private static String output;
	
	
	
	public BufferedReader getIn() {
		return in;
	}

	public void setIn(BufferedReader in) {
		this.in = in;
	}

	public PrintWriter getOut() {
		return out;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}

	public Socket getCl() {
		return cl;
	}

	public void setCl(Socket cl) {
		this.cl = cl;
	}

	static {
		
		
		
		commands.put("addComp", args->{
			
			try {
				String[] healve=args[5].split(",");
				Component comp = new Component(args[1], Boolean.parseBoolean(args[2]), Boolean.parseBoolean(args[3]), Double.parseDouble(args[4]), Arrays.asList(Double.parseDouble(healve[0]),Double.parseDouble(healve[1]),Double.parseDouble(healve[2])));
				output=String.valueOf(Restaurant.getInstance().addComponent(comp));
				if(output.equals("false")) {
					output+="/faild to add the component";
				}
				else {
					output+="/"+comp.getId();
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				output="false/number format error";
			}
			
		});
		commands.put("addDish", args->{
			ArrayList<Component> comps=new ArrayList<>();
			Arrays.asList(args[3].split(",")).forEach(e->comps.add(Restaurant.getInstance().getRealComponent(Integer.parseInt(e))));;
			Dish dish=new Dish(args[1], DishType.valueOf(args[2]), comps, Integer.parseInt(args[4]),Boolean.parseBoolean(args[5]));
			output=String.valueOf(Restaurant.getInstance().addDish(dish));
			if(output.equals("false")) {
				output+="/faild to add the dish";
			}
			else {
				output+="/"+dish.getId();
			}
		});
		commands.put("addOrd", args->{
			
			try {
				ArrayList<Dish> dishes=new ArrayList<>();
				Arrays.asList(args[2].split(",")).forEach(e->dishes.add(Restaurant.getInstance().getRealDish(Integer.parseInt(e))));
				Order ord=new Order(Restaurant.getInstance().getRealCustomer(Integer.parseInt(args[1])), dishes, null);
				output=String.valueOf(Restaurant.getInstance().addOrder(ord));
				if(output.equals("false")) {
					output+="/faild to add the order";
				}
				else {
					output+="/"+ord.getId();
				}
			} catch (IllegalCustomerException e1) {
				output="false/we do not doing bussiness with that customer";
			} catch (SensitiveException e1) {
				output="false/that customer is sensitive to one dish or more";
			}
		});
		commands.put("addRegDel", args->{
			
			try {
				TreeSet<Order> ords=new TreeSet<>();
				Arrays.asList(args[1].split(",")).forEach(e->ords.add(Restaurant.getInstance().getRealOrder(Integer.parseInt(e))));
				String [] date=args[5].split("-");
				LocalDate ld=LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
				RegularDelivery rd = new RegularDelivery(ords, Restaurant.getInstance().getRealDeliveryPerson(Integer.parseInt(args[2])), Restaurant.getInstance().getRealDeliveryArea(Integer.parseInt(args[3])), Boolean.parseBoolean(args[4]), ld);
				output=String.valueOf(Restaurant.getInstance().addDelivery(rd));
				if(output.equals("false")) {
					output+="/faild to add the delivery";
				}
				else {
					output+="/"+rd.getId();
				}
			} catch (NumberFormatException e1) {
				output="false/number format error";
			} catch (WrongAreaException e1) {
				output="false/please verify that the area is correct";
			}
			
		});
		commands.put("addExpDel", args->{

			try {
				String [] date1=args[6].split("-");
				LocalDate ld1=LocalDate.of(Integer.parseInt(date1[0]), Integer.parseInt(date1[1]), Integer.parseInt(date1[2]));
				ExpressDelivery ed = new ExpressDelivery(Restaurant.getInstance().getRealDeliveryPerson(Integer.parseInt(args[1])), Restaurant.getInstance().getRealDeliveryArea(Integer.parseInt(args[2])), Boolean.parseBoolean(args[3]), Restaurant.getInstance().getRealOrder(Integer.parseInt(args[4])), Double.parseDouble(args[5]), ld1);
				output=String.valueOf(Restaurant.getInstance().addDelivery(ed));
				if(output.equals("false")) {
					output+="/faild to add the delivery";
				}
				else {
					output+="/"+ed.getId();
				}
			} catch (NumberFormatException e) {
				output="false/number format error";
			} catch (WrongAreaException e) {
				output="false/please verify that the area is correct";
			}
			
		});
		commands.put("addCook", args->{



				try {
					String [] date11=args[4].split("-");
					LocalDate ld11=LocalDate.of(Integer.parseInt(date11[0]), Integer.parseInt(date11[1]), Integer.parseInt(date11[2]));
					Cook cook = new Cook(Integer.parseInt(args[1]), args[2], args[3], ld11, Gender.valueOf(args[5]), Expertise.valueOf(args[6]), Boolean.parseBoolean(args[7]), args[8], args[9]);
					output=String.valueOf(Restaurant.getInstance().addCook(cook));
					if(output.equals("false")) {
						output+="/faild to add the cook";
					}
					else {
						output+="/"+cook.getId();
					}
				} catch (NumberFormatException e) {
					output="false/number format error";
				} 

		});
		commands.put("addDp", args->{
			try {
				String [] date12=args[4].split("-");
				LocalDate ld12=LocalDate.of(Integer.parseInt(date12[0]), Integer.parseInt(date12[1]), Integer.parseInt(date12[2]));
				DeliveryPerson dp = new DeliveryPerson(Integer.parseInt(args[1]), args[2], args[3], ld12, Gender.valueOf(args[5]),Vehicle.valueOf(args[6]), Restaurant.getInstance().getRealDeliveryArea(Integer.parseInt(args[7])), args[8], args[9]);
				output=String.valueOf(Restaurant.getInstance().addDeliveryPerson(dp,Restaurant.getInstance().getRealDeliveryArea(Integer.parseInt(args[7]))));
				if(output.equals("false")) {
					output+="/faild to add the delivery person";
				}
				else {
					output+="/"+dp.getId();
				}
			} catch (NumberFormatException e) {
				output="false/number format error";
			} 
		});
		commands.put("addCust", args->{
			
			try {
				String [] date13=args[4].split("-");
				LocalDate ld13=LocalDate.of(Integer.parseInt(date13[0]), Integer.parseInt(date13[1]), Integer.parseInt(date13[2]));
				Customer cust = new Customer(Integer.parseInt(args[1]), args[2], args[3], ld13, Gender.valueOf(args[5]), Neighberhood.valueOf(args[6]), Boolean.parseBoolean(args[7]), Boolean.parseBoolean(args[8]), args[9], args[10], args[11], args[12]);
				output=String.valueOf(Restaurant.getInstance().addCustomer(cust));
				if(output.equals("false")) {
					output+="/faild to add the account";
				}
				else {
					output+="/"+cust.getId();
				}
			} catch (NumberFormatException e) {
				output="false/number format error";
			}
			
		});
		commands.put("addArea", args->{
			HashSet<Neighberhood> nbs=new HashSet<>();
			Arrays.asList(args[2].split(",")).forEach(e->nbs.add(Neighberhood.valueOf(e)));
			DeliveryArea da=new DeliveryArea(args[1], nbs, Integer.parseInt(args[3]));
			output=String.valueOf(Restaurant.getInstance().addDeliveryArea(da));
			if(output.equals("false")) {
				output+="/faild to add the area";
			}
			else {
				output+="/"+da.getId();
			}
		});
		commands.put("addBl", args->{
			output=String.valueOf(Restaurant.getInstance().addCustomerToBlackList(Restaurant.getInstance().getRealCustomer(Integer.parseInt(args[1]))));
			if(output.equals("false")) {
				output+="/faild to add the customer to the black list";
			}
			else {
				output+="/"+args[1];
			}
		});
		
		
		commands.put("remComp", args->{
			output=String.valueOf(Restaurant.getInstance().removeComponent(Restaurant.getInstance().getRealComponent(Integer.parseInt(args[1]))));
			if(output.equals("false")) {
				output+="/faild to remove the component";
			}
			else {
				output+="/"+args[1];
			}
		});
		commands.put("remDish",args->{
			output=String.valueOf(Restaurant.getInstance().removeDish(Restaurant.getInstance().getRealDish(Integer.parseInt(args[1]))));
			if(output.equals("false")) {
				output+="/faild to remove the dish";
			}
			else {
				output+="/"+args[1];
			}
		});
		commands.put("remOrd",args->{
			output=String.valueOf(Restaurant.getInstance().removeOrder(Restaurant.getInstance().getRealOrder(Integer.parseInt(args[1]))));
			if(output.equals("false")) {
				output+="/faild to remove the order";
			}
			else {
				output+="/"+args[1];
			}
		});
		commands.put("remDel",args->{
			output=String.valueOf(Restaurant.getInstance().removeDelivery(Restaurant.getInstance().getRealDelivery(Integer.parseInt(args[1]))));
			if(output.equals("false")) {
				output+="/faild to remove the delivery";
			}
			else {
				output+="/"+args[1];
			}
		});
		commands.put("remCook",args->{
			output=String.valueOf(Restaurant.getInstance().removeCook(Restaurant.getInstance().getRealCook(Integer.parseInt(args[1]))));
			if(output.equals("false")) {
				output+="/faild to remove the cook";
			}
			else {
				output+="/"+args[1];
			}
		});
		commands.put("remDp",args->{
			output=String.valueOf(Restaurant.getInstance().removeDeliveryPerson(Restaurant.getInstance().getRealDeliveryPerson(Integer.parseInt(args[1]))));
			if(output.equals("false")) {
				output+="/faild to remove the delivery person";
			}
			else {
				output+="/"+args[1];
			}
		});
		commands.put("remCust",args->{
			output=String.valueOf(Restaurant.getInstance().removeCustomer(Restaurant.getInstance().getRealCustomer(Integer.parseInt(args[1]))));
			if(output.equals("false")) {
				output+="/faild to remove the account";
			}
			else {
				output+="/"+args[1];
			}
		});
		commands.put("remArea",args->{
			try {
				output=String.valueOf(Restaurant.getInstance().removeDeliveryArea(Restaurant.getInstance().getRealDeliveryArea(Integer.parseInt(args[1])),(Restaurant.getInstance().getRealDeliveryArea(Integer.parseInt(args[2])))));
				if(output.equals("false")) {
					output+="/faild to remove the area";
				}
				else {
					output+="/"+args[1];
				}
			} catch (NumberFormatException e) {
				output="false/number format error";
			} catch (WrongAreaException e) {
				output="false/please verify that the area is correct";
			}
		});
		commands.put("editComp", args->{

			
			try {
				Component comp1=Restaurant.getInstance().getRealComponent(Integer.parseInt(args[1]));
				comp1.setId(Integer.parseInt(args[2]));
				comp1.setComponentName(args[3]);
				comp1.setHasGluten(Boolean.parseBoolean(args[4]));
				comp1.setHasLactose(Boolean.parseBoolean(args[5]));
				comp1.setPrice(Double.parseDouble(args[6]));
				comp1.setCalories(Double.parseDouble(args[7]));
				comp1.setColesterol(Double.parseDouble(args[8]));
				comp1.setNe(Double.parseDouble(args[9]));
				for(Dish d:Restaurant.getInstance().getDishes().values()) {
					if(d.getComponenets().contains(comp1)) {
						d.calcDishPrice();
						d.setHealveProperties();
					}
				}
				output="true";
			} catch (NumberFormatException e) {
				output="false/number format error";
			}
			
		

	});
		commands.put("editDish", args->{
		try {
			Dish dish1=Restaurant.getInstance().getRealDish(Integer.parseInt(args[1]));
			HashSet<Component> chosenComponenets =new HashSet<>();
			Arrays.asList(args[5].split(",")).forEach(e->chosenComponenets.add(Restaurant.getInstance().getRealComponent(Integer.parseInt(e))));
			for(Component c:chosenComponenets) {
				if(dish1.getComponenets().contains(c)) {
					continue;
				}
				if(!dish1.addComponent(c)) {
					throw new FaildToAddException();
				}
			}
			for(Component c:dish1.getComponenets()) {
				if(chosenComponenets.contains(c)) {
					continue;
				}
				if(!dish1.removeComponent(c)) {
					throw new FaildToRemoveException();
				}
			}
			dish1.setId(Integer.parseInt(args[2]));
			dish1.setDishName(args[3]);
			dish1.setTimeToMake(Integer.parseInt(args[6]));
			dish1.setType(DishType.valueOf(args[4]));
			dish1.setHealveProperties();
			dish1.calcDishPrice();
			dish1.setPublicDish(Boolean.parseBoolean(args[7]));
			output="true";
			}
		catch(FaildToRemoveException ex) {
			output="false/failed to remove component";
		} catch (FaildToAddException e1) {
			output="false/failed to add component";
		}
});
		commands.put("editOrd", args->{
		
			try {
			Order order=Restaurant.getInstance().getRealOrder(Integer.parseInt(args[1]));
			HashSet<Dish> chosenDishes=new HashSet<>();
			Arrays.asList(args[4].split(",")).forEach(e->chosenDishes.add(Restaurant.getInstance().getRealDish(Integer.parseInt(e))));
			for(Dish d:chosenDishes) {
				if(order.getDishes().contains(d)) {
					continue;
				}
				if(!order.addDish(d)) {
					throw new FaildToAddException();
				}
			}
			for(Dish d:order.getDishes()) {
				if(chosenDishes.contains(d)) {
					continue;
				}
				if(!order.removeDish(d)) {
					throw new FaildToRemoveException();
				}
			}
		order.setId(Integer.parseInt(args[2]));
		order.setCustomer(Restaurant.getInstance().getRealCustomer(Integer.parseInt(args[3])));
		if(!args[5].equals("null")) {
		order.setDelivery(Restaurant.getInstance().getRealDelivery(Integer.parseInt(args[5])));
		}
		output="true";
			}
			catch(FaildToRemoveException ex) {
				output="false/failed to remove dish";
			} catch (FaildToAddException e1) {
				output="false/failed to add dish";
			}


		});
		commands.put("editRegDel", args->{
		
		try {
			RegularDelivery rd1=(RegularDelivery)Restaurant.getInstance().getRealDelivery(Integer.parseInt(args[1]));
			HashSet<Order> chosenOrders=new HashSet<>();
			Arrays.asList(args[3].split(",")).forEach(e->chosenOrders.add(Restaurant.getInstance().getRealOrder(Integer.parseInt(e))));
			String[] date111=args[6].split("-");
			for(Order o:chosenOrders) {
				if(rd1.getOrders().contains(o)) {
					continue;
				}
				if(!rd1.addOrder(o)) {
					throw new FaildToAddException();
				}
			}
			for(Order o:rd1.getOrders()) {
				if(chosenOrders.contains(o)) {
					continue;
				}
				if(!rd1.removeOrder(o)) {
					throw new FaildToRemoveException();
				}
			}
			rd1.setId(Integer.parseInt(args[2]));
			rd1.setDeliveryPerson(Restaurant.getInstance().getRealDeliveryPerson(Integer.parseInt(args[4])));
			rd1.setArea(Restaurant.getInstance().getRealDeliveryArea(Integer.parseInt(args[5])));
			rd1.setDeliveredDate(LocalDate.of(Integer.parseInt(date111[0]), Integer.parseInt(date111[1]), Integer.parseInt(date111[2])));
			rd1.setDelivered(Boolean.parseBoolean(args[7]));
			
			output="true";
			}
		catch(FaildToRemoveException ex) {
			output="false/failed to remove order";
		} catch (FaildToAddException e1) {
			output="false/failed to add order";
		} catch (NumberFormatException e1) {
			output="false/number format error";
		} catch (WrongAreaException e1) {
			output="false/please verify that the area is correct";
		}
		});
		commands.put("editExpDel", args->{

			
			try {
				String[] date111=args[7].split("-");
				ExpressDelivery exp=(ExpressDelivery)Restaurant.getInstance().getRealDelivery(Integer.parseInt(args[1]));
				exp.setId(Integer.parseInt(args[2]));
				exp.setOrder(Restaurant.getInstance().getRealOrder(Integer.parseInt(args[3])));
				exp.setDeliveryPerson(Restaurant.getInstance().getRealDeliveryPerson(Integer.parseInt(args[4])));
				exp.setArea(Restaurant.getInstance().getRealDeliveryArea(Integer.parseInt(args[5])));
				exp.setDelivered(Boolean.parseBoolean(args[6]));
				exp.setDeliveredDate(LocalDate.of(Integer.parseInt(date111[0]), Integer.parseInt(date111[1]), Integer.parseInt(date111[2])));
				exp.setPostage(Double.parseDouble(args[8]));
				
				output="true";
			} catch (NumberFormatException e) {
				output="false/number format error";
			} catch (WrongAreaException e) {
				output="false/please verify that the area is correct";
			}
			
		
		});
		commands.put("editCook", args->{

			
			try {
				Cook c=Restaurant.getInstance().getRealCook(Integer.parseInt(args[1]));
				String[] date111=args[5].split("-");
				c.setId(Integer.parseInt(args[2]));
				c.setFirstName(args[3]);
				c.setLastName(args[4]);
				c.setBirthDay(LocalDate.of(Integer.parseInt(date111[0]), Integer.parseInt(date111[1]), Integer.parseInt(date111[2])));
				c.setGender(Gender.valueOf(args[6]));
				c.setExpert(Expertise.valueOf(args[7]));
				c.setEmail(args[9]);
				c.setPhoneNumber(args[10]);
				c.setChef(Boolean.parseBoolean(args[8]));
				output="true";
			} catch (NumberFormatException e) {
				output="false/number format error";
			}
			

		
		});
		commands.put("editDp", args->{
			try {
				DeliveryPerson dlp=Restaurant.getInstance().getRealDeliveryPerson(Integer.parseInt(args[1]));
				String[] date111=args[5].split("-");
				dlp.setId(Integer.parseInt(args[2]));
				dlp.setFirstName(args[3]);
				dlp.setLastName(args[4]);
				dlp.setBirthDay(LocalDate.of(Integer.parseInt(date111[0]), Integer.parseInt(date111[1]), Integer.parseInt(date111[2])));
				dlp.setGender(Gender.valueOf(args[6]));
				dlp.setVehicle(Vehicle.valueOf(args[7]));
				dlp.setArea(Restaurant.getInstance().getRealDeliveryArea(Integer.parseInt(args[8])));
				dlp.setEmail(args[9]);
				dlp.setPhoneNumber(args[10]);
				
				output="true";
			} catch (NumberFormatException e) {
				output="false/number format error";
			}
			

			

		});
		commands.put("editCust", args->{
			
			try {
				Customer cust1=Restaurant.getInstance().getRealCustomer(Integer.parseInt(args[1]));
				String[] date111=args[5].split("-");
				cust1.setId(Integer.parseInt(args[2]));
				cust1.setFirstName(args[3]);
				cust1.setLastName(args[4]);
				cust1.setBirthDay(LocalDate.of(Integer.parseInt(date111[0]), Integer.parseInt(date111[1]), Integer.parseInt(date111[2])));
				cust1.setGender(Gender.valueOf(args[6]));
				cust1.setNeighberhood(Neighberhood.valueOf(args[7]));
				cust1.setSensitiveToGluten(Boolean.parseBoolean(args[8]));
				cust1.setSensitiveToLactose(Boolean.parseBoolean(args[9]));
				cust1.setEmail(args[10]);
				cust1.setPhoneNumber(args[11]);
				cust1.getAccount().setUserName(args[12]);
				cust1.getAccount().setPassword(args[13]);
				
				output="true";
			}catch (NumberFormatException e) {
				output="false/number format error";
			}


		});
		commands.put("editArea", args->{
			try {
			DeliveryArea area=Restaurant.getInstance().getRealDeliveryArea(Integer.parseInt(args[1]));
			HashSet<DeliveryPerson> dps=new HashSet<>();
			HashSet<Delivery> ds=new HashSet<>();
			HashSet<Neighberhood> nebs=new HashSet<>();
			if(!args[4].isBlank())
				Arrays.asList(args[4].split(",")).forEach(e->dps.add(Restaurant.getInstance().getRealDeliveryPerson(Integer.parseInt(e))));
			if(!args[5].isBlank())
				Arrays.asList(args[5].split(",")).forEach(e->ds.add(Restaurant.getInstance().getRealDelivery(Integer.parseInt(e))));
			if(!args[6].isBlank())
				Arrays.asList(args[6].split(",")).forEach(e->nebs.add(Neighberhood.valueOf(e)));
			for(DeliveryPerson d:dps) {
				if(area.getDelPersons().contains(d)) {
					continue;
				}
				if(!area.addDelPerson(d)) {
					throw new FaildToAddException();
				}
			}
			for(DeliveryPerson d:area.getDelPersons()) {
				if(dps.contains(d)) {
					continue;
				}
				if(!area.removeDelPerson(d)) {
					throw new FaildToRemoveException();
				}
			}
			for(Delivery d:ds) {
				if(area.getDelivers().contains(d)) {
					continue;
				}
				if(!area.addDelivery(d)) {
					throw new FaildToAddException();
				}
			}
			for(Delivery d:area.getDelivers()) {
				if(ds.contains(d)) {
					continue;
				}
				if(!area.removeDelivery(d)) {
					throw new FaildToRemoveException();
				}
			}
			for(Neighberhood n:nebs) {
				if(area.getNeighberhoods().contains(n)) {
					continue;
				}
				if(!area.addNeighberhood(n)) {
					throw new FaildToAddException();
				}
			}
			for(Neighberhood n:area.getNeighberhoods()) {
				if(nebs.contains(n)) {
					continue;
				}
				if(!area.removeNeighberhood(n)) {
					throw new FaildToRemoveException();
				}
			}
			area.setId(Integer.parseInt(args[2]));
			area.setAreaName(args[3]);
			output="true";
			}
			catch(FaildToRemoveException ex) {
				output="false/failed to remove iten";
			} catch (FaildToAddException e1) {
				output="false/failed to add item";
			}
		});
	
		
		commands.put("RelDishList", args->{
		Collection<Dish> dishes1=Restaurant.getInstance().getReleventDishList(Restaurant.getInstance().getRealCustomer(Integer.parseInt(args[1])));
		output="";
		for(Dish d:dishes1) {
			output+=","+d.toString();
		}
		if(!output.isEmpty()) {
			output=output.substring(1);
		};
		});
		
		commands.put("CookByExp", args->{
		Collection<Cook> cooks1=Restaurant.getInstance().GetCooksByExpertise(Expertise.valueOf(args[1]));
		HashSet<String> cooks2=new HashSet<>();
		cooks1.forEach(e->cooks2.add(e.toString()));
		output=String.join(",", cooks2);
		});
		
		commands.put("DelByPer", args->{
		Collection<Delivery> dels1=Restaurant.getInstance().getDeliveriesByPerson(Restaurant.getInstance().getRealDeliveryPerson(Integer.parseInt(args[1])), Integer.parseInt(args[2]));
		output="";
		for(Delivery del:dels1) {
			output+=","+del.toString();
		}
		if(!output.isEmpty()) {
			output=output.substring(1);
		}
		});
		
		commands.put("ordByCust", args->{
			TreeSet<Order> orders1=Restaurant.getInstance().getOrderByCustomer().get(Restaurant.getInstance().getRealCustomer(Integer.parseInt(args[1])));
			if(orders1==null) {
				orders1=new TreeSet<>();
			}
			LinkedList<String> orders2=new LinkedList<>();
			orders1.forEach(e->orders2.add(e.toString()));
			output=String.join(",", orders2);
			});
		
		commands.put("DelPerType", args->{
		HashMap<String,Integer> map=Restaurant.getInstance().getNumberOfDeliveriesPerType();
		output="";
		for(String s:map.keySet()) {
			output+=","+s+":  "+map.get(s);
		}
		if(!output.isEmpty()) {
			output=output.substring(1);
		}
		});
		
		commands.put("RevFromDel", args->{
		output=String.valueOf(Restaurant.getInstance().revenueFromExpressDeliveries());
		});
		
		commands.put("PopComp", args->{
		LinkedList<Component> comps1=Restaurant.getInstance().getPopularComponents();
		LinkedList<String> comps2=new LinkedList<>();
		comps1.forEach(e->comps2.add(e.toString()));
		output=String.join(",", comps2);
		});
		
		commands.put("ProfRel", args->{
		TreeSet<Dish> dshs=Restaurant.getInstance().getProfitRelation();
		output="";
		for(Dish d:dshs) {
			output+=","+d.toString();
		}
		if(!output.isEmpty()) {
			output=output.substring(1);
		}
		});
		
		commands.put("AI", args->{
		try {
			TreeSet<Order> orders=new TreeSet<>();
			Arrays.asList(args[2].split(",")).forEach(e->orders.add(Restaurant.getInstance().getRealOrder(Integer.parseInt(e))));
			TreeSet<Delivery> dels;
			DeliveryPerson dp=Restaurant.getInstance().getRealDeliveryPerson(Integer.parseInt(args[1]));
			dels = Restaurant.getInstance().createAIMacine(dp,dp.getArea(),orders);
			output="";
			for(Delivery del:dels) {
				output+=","+del.toString();
			}
			if(!output.isEmpty()) {
				output=output.substring(1);
			}
			
		} catch (NumberFormatException e1) {
			output="false/number format error";
		} catch (WrongAreaException e1) {
			output="false/please verify that the area is correct";
		}
		});
		
		commands.put("getCooks", args->{
		Collection<Cook> cooks=Restaurant.getInstance().getCooks().values();
		output="";
		for(Cook c:cooks) {
			output+=","+c.toString();
		}
		if(!output.isEmpty()) {
			output=output.substring(1);
		}
		});
		
		commands.put("getDeps", args->{
		Collection<DeliveryPerson> deps=Restaurant.getInstance().getDeliveryPersons().values();
		output="";
		for(DeliveryPerson d:deps) {
			output+=","+d.toString();
		}
		if(!output.isEmpty()) {
			output=output.substring(1);
		}
		});
		
		commands.put("getCusts", args->{
		Collection<Customer> custs=Restaurant.getInstance().getCustomers().values();
		output="";
		for(Customer c:custs) {
			output+=","+c.toString();
		}
		if(!output.isEmpty()) {
			output=output.substring(1);
		}
		});
		
		commands.put("getComps", args->{
		Collection<Component> cmps=Restaurant.getInstance().getComponenets().values();
		output="";
		for(Component c:cmps) {
			output+=","+c.toString();
		}
		if(!output.isEmpty()) {
			output=output.substring(1);
		}
		});
		
		commands.put("getDishes", args->{
		Collection<Dish> dishs=Restaurant.getInstance().getDishes().values();
		output="";
		for(Dish d:dishs) {
			output+=","+d.toString();
		}
		if(!output.isEmpty()) {
			output=output.substring(1);
		}
		});
		
		commands.put("getOrds", args->{
		Collection<Order> ords1=Restaurant.getInstance().getOrders().values();
		output="";
		for(Order o:ords1) {
			output+=","+o.toString();
		}
		if(!output.isEmpty()) {
			output=output.substring(1);
		}
		});
		
		commands.put("getDels", args->{
		Collection<Delivery> dls=Restaurant.getInstance().getDeliveries().values();
		output="";
		for(Delivery d:dls) {
			output+=","+d.toString();
		}
		if(!output.isEmpty()) {
			output=output.substring(1);
		}
		});
		
		commands.put("getAreas", args->{
		Collection<DeliveryArea> areas=Restaurant.getInstance().getAreas().values();
		output="";
		for(DeliveryArea d:areas) {
			output+=","+d.toString();
		}
		if(!output.isEmpty()) {
			output=output.substring(1);
		}
		});
		
		commands.put("getBL", args->{
		Collection<Customer> bl=Restaurant.getInstance().getBlackList();
		output="";
		for(Customer c:bl) {
			output+=","+c.toString();
		}
		if(!output.isEmpty()) {
			output=output.substring(1);
		}
		});
		
		
		
		commands.put("getCustArea", args->{
			Customer cust1=Restaurant.getInstance().getRealCustomer(Integer.parseInt(args[1]));
			output=Restaurant.getInstance().getCustomerArea(cust1).toString();
		});
		commands.put("calcOrderParams",args->{
			Order order=Restaurant.getInstance().getRealOrder(Integer.parseInt(args[1]));
			output=order.getPrice()+"/"+order.orderWaitingTime(Restaurant.getInstance().getCustomerArea(order.getCustomer()));
		});
		
		commands.put("forDecide", args->{
			Collection<Order> collection=Restaurant.getInstance().getWaitForDecide().get(Restaurant.getInstance().getRealDeliveryArea(Integer.parseInt(args[1])));
			Collection<String> ords=new HashSet<>();
			collection.forEach(e->{
				ords.add(e.toString());
			});
			output=String.join(",", ords);

		});
		
		commands.put("getRealArea", args->{
		if(args.length<2) {
			output="";
			return;
		}
		DeliveryArea delarea=Restaurant.getInstance().getRealDeliveryArea(Integer.parseInt(args[1]));
		String areaDeps="";
		String areaDels="";
		String areaNebs="";
		for(DeliveryPerson i:delarea.getDelPersons()) {
			areaDeps+=","+i.toString();
		}
		for(Delivery i:delarea.getDelivers()) {
			areaDels+=","+i.toString();
		}
		for(Neighberhood i:delarea.getNeighberhoods()) {
			areaNebs+=","+i.toString();
		}
		if(!areaDeps.isEmpty()) {
		areaDeps=areaDeps.substring(1);
		}
		if(!areaDels.isEmpty()) {
		areaDels=areaDels.substring(1);
		}
		if(!areaNebs.isEmpty()) {
		areaNebs=areaNebs.substring(1);
		}
		output=delarea.getId()+"/"+delarea.getAreaName()+"/"+areaDeps+"/"+areaDels+"/"+areaNebs+"/"+delarea.getDeliverTime();
		});
		
		commands.put("getRealComp", args->{
			if(args.length<2) {
				output="";
				return;
			}
		Component component=Restaurant.getInstance().getRealComponent(Integer.parseInt(args[1]));
		output=component.getId()+"/"+component.getComponentName()+"/"+String.valueOf(component.isHasGluten())+"/"+String.valueOf(component.isHasLactose())+"/"+component.getPrice()+"/"+component.getCalories()+"/"+component.getColesterol()+"/"+component.getNe();
		});
		
		commands.put("getRealDish", args->{
			if(args.length<2) {
				output="";
				return;
			}
		Dish dsh=Restaurant.getInstance().getRealDish(Integer.parseInt(args[1]));
		String dishComps="";
		for(Component i:dsh.getComponenets()) {
			dishComps+=","+i.toString();
		}
		if(!output.isEmpty()) {
			dishComps=dishComps.substring(1);
		}
		output=dsh.getId()+"-"+dsh.getDishName()+"-"+dsh.getType().name()+"-"+dishComps+"-"+dsh.getTimeToMake()+"-"+dsh.getPrice()+"-"+dsh.getCalories()+"-"+dsh.getColesterol()+"-"+dsh.getNe()+"-"+String.valueOf(dsh.isPublicDish());
		});
		
		commands.put("getRealOrd", args->{
			if(args.length<2) {
				output="";
				return;
			}
		Order or=Restaurant.getInstance().getRealOrder(Integer.parseInt(args[1]));
		String orDishes="";
		for(Dish i:or.getDishes()) {
			orDishes+=","+i.toString();
		}
		if(!output.isEmpty()) {
			orDishes=orDishes.substring(1);
		}
		output=or.getId()+"/"+or.getCustomer()+"/"+orDishes+"/"+or.getDelivery()+"/"+or.getStatus().name()+"/"+or.getCreated();
		});
		
		
		commands.put("getRealDel", args->{
			if(args.length<2) {
				output="";
				return;
			}
		Delivery del=Restaurant.getInstance().getRealDelivery(Integer.parseInt(args[1]));
		if(del instanceof RegularDelivery) {
			RegularDelivery reg=(RegularDelivery)del;
			String delOrders="";
			for(Order i:reg.getOrders()) {
				delOrders+=","+i.toString();
			}
			if(!output.isEmpty()) {
				delOrders=delOrders.substring(1);
			}
			output="reg/"+reg.getId()+"/"+delOrders+"/"+reg.getDeliveryPerson()+"/"+reg.getArea()+"/"+reg.getDeliveredDate().format(DateTimeFormatter.ISO_LOCAL_DATE)+"/"+String.valueOf(reg.isDelivered());
		}
		else {
			ExpressDelivery exp=(ExpressDelivery)del;
			output="exp/"+exp.getId()+"/"+exp.getOrder()+"/"+exp.getDeliveryPerson()+"/"+exp.getArea()+"/"+exp.getDeliveredDate().format(DateTimeFormatter.ISO_LOCAL_DATE)+"/"+String.valueOf(exp.isDelivered())+"/"+exp.getPostage();
		}
		});
		
		commands.put("getRealCook", args->{
			if(args.length<2) {
				output="";
				return;
			}
		Cook ck=Restaurant.getInstance().getRealCook(Integer.parseInt(args[1]));
		output=ck.getId()+"/"+ck.getFirstName()+"/"+ck.getLastName()+"/"+ck.getBirthDay().format(DateTimeFormatter.ISO_LOCAL_DATE)+"/"+ck.getGender().name()+"/"+ck.getExpert().name()+"/"+String.valueOf(ck.isChef())+"/"+ck.getEmail()+"/"+ck.getPhoneNumber()+"/"+String.valueOf(ck.isBusy());
		});
		
		commands.put("getRealDep", args->{
			if(args.length<2) {
				output="";
				return;
			}
		DeliveryPerson dep=Restaurant.getInstance().getRealDeliveryPerson(Integer.parseInt(args[1]));
		output=dep.getId()+"/"+dep.getFirstName()+"/"+dep.getLastName()+"/"+dep.getBirthDay().format(DateTimeFormatter.ISO_LOCAL_DATE)+"/"+dep.getGender().name()+"/"+dep.getVehicle().name()+"/"+dep.getArea()+"/"+dep.getEmail()+"/"+dep.getPhoneNumber()+"/"+String.valueOf(dep.isBusy());
		});
		
		commands.put( "getRealCust", args->{
			if(args.length<2) {
				output="";
				return;
			}
		Customer cst=Restaurant.getInstance().getRealCustomer(Integer.parseInt(args[1]));
		output=cst.getId()+"!"+cst.getFirstName()+"!"+cst.getLastName()+"!"+cst.getBirthDay().format(DateTimeFormatter.ISO_LOCAL_DATE)+"!"+cst.getGender().name()+"!"+cst.getNeighberhood().name()+"!"+String.valueOf(cst.isSensitiveToGluten())+"!"+String.valueOf(cst.isSensitiveToLactose())+"!"+cst.getEmail()+"!"+cst.getPhoneNumber()+"!"+cst.getAccount().getUserName()+"!"+cst.getAccount().getDecryptPassword();
		});
		
		commands.put("login", args->{
		Account a=new Account(args[1],args[2]);
		if(Restaurant.getInstance().getAccounts().containsKey(a)) {
			Customer cust=Restaurant.getInstance().getAccounts().get(a);
			output="true/"+cust.getId();
		}
		else {
			output="false";
		}
		});
		
		commands.put("save", args->{
			try {
				Restaurant.getInstance().writeObject();
				output="true";
			} catch (IOException e) {
				output="false";
			}
		});
	}
		
	

	public DealWithClient(Socket cl) {
		
		try {
			this.setName("DealWith_"+cl.getInetAddress().getHostAddress());
			out=new PrintWriter(cl.getOutputStream(),true);
			in=new BufferedReader(new InputStreamReader(cl.getInputStream()));
			this.cl=cl;
			commands.put( "shutdown", args->{	try {
				ServerThread.getInstance().shutdown();
			} catch (FileNotFoundException e) {
				System.exit(0);
			}
			
			});
		}
		 catch (IOException e) {
				System.exit(0);
		}
		
	};
	
	@SuppressWarnings("deprecation")
	public void run() {

			
			String inLine;
			
			while(true) {
				try {
					if(!cl.isConnected()) {
						break;
					}
					inLine=in.readLine();
					String[]args=inLine.split("/");
					if(!commands.containsKey(args[0])) {
						output="X/"+args[0];
						
					}
					else {
					commands.get(args[0]).command(args);
					if(output.isBlank()) {
						output="E";
					}
					}
					out.println(output);
					
				} catch (IOException e) {
					break;
				}
			}	
			this.stop();
	}


		

	}

