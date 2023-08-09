package Model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;


import Exceptions.ConvertToExpressException;
import Exceptions.IllegalCustomerException;
import Exceptions.IllegalSalaryException;
import Exceptions.InvalidEmailException;
import Exceptions.InvalidIDException;
import Exceptions.InvalidPhoneException;
import Exceptions.LieException;
import Exceptions.NoComponentsExceptions;
import Exceptions.SensitiveException;
import Exceptions.ToYoungException;
import Exceptions.WrongAreaException;
import Utils.DishType;
import Utils.Encryption;
import Utils.Expertise;
import Utils.Gender;
import Utils.Neighberhood;
import Utils.OrderStatus;
import Utils.SpecificTime;
import Utils.Vehicle;


public class Restaurant implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1743194275108968365L;

	private static Restaurant restaurant = null;

	private  HashMap<Integer, Cook> cooks;
	private  HashMap<Integer, DeliveryPerson> deliveryPersons;
	private  HashMap<Integer, Customer> customers;
	private  HashMap<Integer, Dish> dishes;
	private  HashMap<Integer, Component> componenets;
	private  HashMap<Integer, Order> orders;
	private  HashMap<Integer, Delivery> deliveries;
	private  HashMap<Integer, DeliveryArea> areas;

	/*NEW*/
	private HashMap<Customer, TreeSet<Order>> orderByCustomer;
	private HashMap<DeliveryArea, HashSet<Order>> orderByDeliveryArea;
	private HashSet<Customer> blackList;
	private HashMap<Account,Customer> accounts;
	
	/*More NEW*/
	private LinkedList<Order> waitForCooking;
	private HashMap<DeliveryArea,LinkedList<Order>> waitForDecide;
	private HashMap<DeliveryArea,LinkedList<Delivery>> waitForDelivering;
	
	
	private Double budget;
	
	
	public static Restaurant getInstance() {
	

			try {
				if(restaurant == null) {
					restaurant = new Restaurant();
					restaurant.readObject(new ObjectInputStream(new FileInputStream("Rest.ser")));
				}
				return restaurant;
			} catch (NumberFormatException | IOException | LieException | ToYoungException | InvalidIDException
					| InvalidPhoneException | InvalidEmailException | IllegalSalaryException | IllegalCustomerException
					| SensitiveException | WrongAreaException e) {
				// TODO Auto-generated catch block
				return restaurant;
			}
		}
		
	

	public static Restaurant getRestaurant() {
		return restaurant;
	}

	public static void setRestaurant(Restaurant restaurant) {
		Restaurant.restaurant = restaurant;
	}

	private Restaurant() {
		cooks = new HashMap<>();
		deliveryPersons = new HashMap<>();
		customers = new HashMap<>();
		dishes = new HashMap<>();
		componenets = new HashMap<>();
		orders = new HashMap<>();
		deliveries = new HashMap<>();
		areas = new HashMap<>();
		orderByCustomer = new HashMap<>();
		orderByDeliveryArea = new HashMap<>();
		blackList = new HashSet<>();
		setAccounts(new HashMap<>());
		
		waitForCooking=new LinkedList<>();
		waitForDecide=new HashMap<>();
		waitForDelivering=new HashMap<>();
		
		budget=0.0;
	}
	
	
	public HashMap<DeliveryArea, LinkedList<Order>> getWaitForDecide() {
		return waitForDecide;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public String generateDefaultPassword(Person person) {
		char [] a= {Character.toUpperCase(person.getFirstName().toCharArray()[0]),Character.toLowerCase(person.getLastName().toCharArray()[0]),person.getPhoneNumber().toCharArray()[0],person.getEmail().toCharArray()[0]};
		return new String(a)+person.getBirthDay().getDayOfMonth()+person.getBirthDay().getMonthValue()+person.getBirthDay().getDayOfYear();

	}

	public HashMap<Integer, Cook> getCooks() {
		return cooks;
	}

	
	public void setCooks(HashMap<Integer, Cook> cooks) {
		this.cooks = cooks;
	}

	public HashMap<Integer, DeliveryPerson> getDeliveryPersons() {
		return deliveryPersons;
	}

	public void setDeliveryPersons(HashMap<Integer, DeliveryPerson> deliveryPersons) {
		this.deliveryPersons = deliveryPersons;
	}

	public HashMap<Integer, Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(HashMap<Integer, Customer> customers) {
		this.customers = customers;
	}

	public HashMap<Integer, Dish> getDishes() {
		return dishes;
	}

	public void setDishes(HashMap<Integer, Dish> dishes) {
		this.dishes = dishes;
	}

	public HashMap<Integer, Component> getComponenets() {
		return componenets;
	}

	public void setComponenets(HashMap<Integer, Component> componenets) {
		this.componenets = componenets;
	}

	public HashMap<Integer, Order> getOrders() {
		return orders;
	}

	public void setOrders(HashMap<Integer, Order> orders) {
		this.orders = orders;
	}

	public HashMap<Integer, Delivery> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(HashMap<Integer, Delivery> deliveries) {
		this.deliveries = deliveries;
	}

	public  HashMap<Integer, DeliveryArea> getAreas() {
		return areas;
	}

	public void setAreas(HashMap<Integer, DeliveryArea> areas) {
		this.areas = areas;
	}

	public HashMap<Customer, TreeSet<Order>> getOrderByCustomer() {
		return orderByCustomer;
	}

	public void setOrderByCustomer(HashMap<Customer, TreeSet<Order>> orderByCustomer) {
		this.orderByCustomer = orderByCustomer;
	}

	public HashMap<DeliveryArea, HashSet<Order>> getOrderByDeliveryArea() {
		return orderByDeliveryArea;
	}

	public void setOrderByDeliveryArea(HashMap<DeliveryArea, HashSet<Order>> orderByDeliveryArea) {
		this.orderByDeliveryArea = orderByDeliveryArea;
	}

	public HashSet<Customer> getBlackList() {
		return blackList;
	}

	public void setBlackList(HashSet<Customer> blackList) {
		this.blackList = blackList;
	}
	

	public boolean addCook(Cook cook) {
		if(cook == null || getCooks().containsKey(cook.getId()))
			return false;

		return getCooks().put(cook.getId(), cook) == null;
	}

	public boolean addDeliveryPerson(DeliveryPerson dp, DeliveryArea da) {
		if(dp == null || getDeliveryPersons().containsKey(dp.getId()) || !getAreas().containsKey(da.getId()))
			return false;

		return deliveryPersons.put(dp.getId() , dp) ==null && da.addDelPerson(dp);
	}

	public boolean addCustomer(Customer cust) {
		if(cust == null || getCustomers().containsKey(cust.getId()))
			return false;

		return getCustomers().put(cust.getId(), cust) ==null&&accounts.put(cust.getAccount(),cust)==null;
	}

	public boolean addDish(Dish dish) {
		if(dish == null || getDishes().containsKey(dish.getId()))
			return false;
		for(Component c : dish.getComponenets()) {
			if(!getComponenets().containsKey(c.getId()))
				return false;
		}

		return getDishes().put(dish.getId(), dish) ==null;
	}

	public boolean addComponent(Component comp) {
		if(comp == null || getComponenets().containsKey(comp.getId()))
			return false;

		return getComponenets().put(comp.getId(), comp) ==null;
	}

	public boolean addOrder(Order order) throws IllegalCustomerException, SensitiveException {

			if(order == null || getOrders().containsKey(order.getId()))
				return false;
			if(order.getCustomer() == null || !getCustomers().containsKey(order.getCustomer().getId()))
				return false;
			if(getBlackList().contains(order.getCustomer())) {
				throw new IllegalCustomerException();
			}
			for(Dish d : order.getDishes()){
				if(!getDishes().containsKey(d.getId()))
					return false;
				for(Component c: d.getComponenets()) {
					if(order.getCustomer().isSensitiveToGluten() && c.isHasGluten()) {
						throw new SensitiveException(order.getCustomer().getFirstName() + " " +order.getCustomer().getLastName(), d.getDishName());
					}
					else if(order.getCustomer().isSensitiveToLactose() && c.isHasLactose()) {
						throw new SensitiveException(order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName(), d.getDishName());
					}
				}
			}
			waitForCooking.addLast(order);
			for(Cook cook:this.getCooks().values()) {
				if(!cook.isBusy()) {
					(cook.new Cooking(waitForCooking.poll(),cook)).start();;
				}
			}
			if(!this.orderByCustomer.containsKey(order.getCustomer())) {
				this.getOrderByCustomer().put(order.getCustomer(), new TreeSet<>());
				this.getOrderByDeliveryArea().put(this.getCustomerArea(order.getCustomer()), new HashSet<>());
			}
			this.budget+=order.getPrice();
			return getOrders().put(order.getId(), order) == null&&this.orderByDeliveryArea.get(this.getCustomerArea(order.getCustomer())).add(order)&&this.orderByCustomer.get(order.getCustomer()).add(order);
		
	}

	public LinkedList<Order> getWaitForCooking() {
		return waitForCooking;
	}

	public DeliveryArea getCustomerArea(Customer c) {

			for(DeliveryArea da:getAreas().values()) {
				if(da.getNeighberhoods().contains(c.getNeighberhood())) {
					return da;
				}
			}
			return null;

	}
	
	

	public HashMap<DeliveryArea, LinkedList<Delivery>> getWaitForDelivering() {
		return waitForDelivering;
	}

	public boolean addDelivery(Delivery delivery) throws WrongAreaException{
		try {
			if(delivery == null || getDeliveries().containsKey(delivery.getId()) || !getDeliveryPersons().containsKey(delivery.getDeliveryPerson().getId()))
			{
				return false;
			}
		if(!this.waitForDelivering.containsKey(delivery.getArea())) {
			this.waitForDelivering.put(delivery.getArea(), new LinkedList<>());
		}
		this.waitForDelivering.get(delivery.getArea()).addLast(delivery);
			if(delivery instanceof RegularDelivery) {
				RegularDelivery rd = (RegularDelivery)delivery;
				for(Order o : rd.getOrders()){
					if(!getOrders().containsKey(o.getId()))
						return false;
					o.setDelivery(delivery);
				}
				if(rd.getOrders().size() ==1) {
					throw new ConvertToExpressException();
				}
				if(rd.getOrders().isEmpty())
					return false;
			}
			else {
				ExpressDelivery ed = (ExpressDelivery)delivery;
				if(!getOrders().containsKey(ed.getOrder().getId()))
						return false;
					ed.getOrder().setDelivery(delivery);
			}
		}catch(ConvertToExpressException e) {
			RegularDelivery rd = (RegularDelivery)delivery;
			delivery = new ExpressDelivery(rd.getDeliveryPerson(), rd.getArea(),rd.isDelivered(), rd.getOrders().first() ,rd.getDeliveredDate());
		}finally {
			delivery.getArea().addDelivery(delivery);
			if(delivery instanceof RegularDelivery) {
				RegularDelivery rg = (RegularDelivery)delivery;
				for(Order o: rg.getOrders()) {
					TreeSet<Order> orders = orderByCustomer.get(o.getCustomer());
					if(orders == null)
						orders = new TreeSet<>(new Comparator<Order>() {

							@Override
							public int compare(Order o1, Order o2) {
								return o1.getDelivery().getDeliveredDate().compareTo(o2.getDelivery().getDeliveredDate());
							}
						});
					orders.add(o);
					orderByCustomer.put(o.getCustomer(), orders);
				}
			}
			else {
				ExpressDelivery ex = (ExpressDelivery)delivery;
				TreeSet<Order> orders = orderByCustomer.get(ex.getOrder().getCustomer());
				if(orders == null)
					orders = new TreeSet<>(new Comparator<Order>() {

						@Override
						public int compare(Order o1, Order o2) {
							return o1.getDelivery().getDeliveredDate().compareTo(o2.getDelivery().getDeliveredDate());
						}
					});
				orders.add(ex.getOrder());
				orderByCustomer.put(ex.getOrder().getCustomer(), orders);
			}
		}
			for(DeliveryPerson dp:delivery.getArea().getDelPersons()) {
				if(!dp.isBusy()&&!delivery.isDelivered()) {

					(dp.new Delivering(waitForDelivering.get(delivery.getArea()).poll(),dp)).start();;
				}
			}
		
		return getDeliveries().put(delivery.getId(),delivery) ==null;
	}

	public boolean addDeliveryArea(DeliveryArea da) {
		if(da == null || getAreas().containsKey(da.getId())) {
			return false;}
		this.getWaitForDecide().put(da, new LinkedList<>());
		this.getWaitForDelivering().put(da, new LinkedList<>());
		return getAreas().put(da.getId(), da) ==null;
	}
	
	public boolean addCustomerToBlackList(Customer c) {
		if(c == null)
			return false;
		return getBlackList().add(c);
	}

	public boolean removeCook(Cook cook) {
		if(cook == null || !getCooks().containsKey(cook.getId()))
			return false;
		return getCooks().remove(cook.getId()) !=null;
	}

	public boolean removeDeliveryPerson(DeliveryPerson dp) {
		if(dp == null || !getDeliveryPersons().containsKey(dp.getId()))
			return false;
		for(Delivery d : getDeliveries().values()) {
			if(d.getDeliveryPerson().equals(dp)) {
				d.setDeliveryPerson(null);
			}
		}
		return getDeliveryPersons().remove(dp.getId())!= null && dp.getArea().removeDelPerson(dp);
	}

	public boolean removeCustomer(Customer cust) {
		if(cust == null || !getCustomers().containsKey(cust.getId()))
			return false;
		return getCustomers().remove(cust.getId())!=null;
	}

	public boolean removeDish(Dish dish) {
		if(dish == null || !getDishes().containsKey(dish.getId()))
			return false;
		for(Delivery d : deliveries.values()) {
			if(!d.isDelivered()) {
				if(d instanceof RegularDelivery) {
					RegularDelivery rd = (RegularDelivery)d;
					for(Order o : rd.getOrders()) {
						o.removeDish(dish);
					}
				}
				else {
					ExpressDelivery ed = (ExpressDelivery)d;
					ed.getOrder().removeDish(dish);
				}
			}
		}
		return getDishes().remove(dish.getId())!=null;
	}

	public boolean removeComponent(Component comp) {
		Dish removeDish = null;
		try {
			if(comp == null || !getComponenets().containsKey(comp.getId()))
				return false;
			for(Dish d : getDishes().values()) {
				d.removeComponent(comp);
				if(d.getComponenets().isEmpty()) {
					removeDish = d;
					throw new NoComponentsExceptions(d);
				}
			}
		}catch(NoComponentsExceptions e) {
			removeDish(removeDish);
		}
		return getComponenets().remove(comp.getId())!=null;
	}

	public boolean removeOrder(Order order) {
		if(order == null || !getOrders().containsKey(order.getId()))
			return false;
		
		if(order.getStatus().equals(OrderStatus.Delivered)||order.getStatus().equals(OrderStatus.inDeliveryProgress)) {
			return false;
		}

		
		if(getOrders().remove(order.getId())!=null) {
			budget-=order.getPrice();
			Iterator<Dish> iter=order.getDishes().iterator();
			while(iter.hasNext()) {
				Dish dish=iter.next();
				if(!dish.isPublicDish()) {
					this.removeDish(dish);
				}
			}
			this.getOrderByCustomer().get(order.getCustomer()).remove(order);
			if(order.getStatus().equals(OrderStatus.waitingForCook)) {
				this.getWaitForCooking().remove(order);
			}
			if(order.getStatus().equals(OrderStatus.waitingForDecide)) {
				this.getWaitForDecide().get(this.getCustomerArea(order.getCustomer())).remove(order);
			}
			if(order.getStatus().equals(OrderStatus.waitingForDelivering)) {
				if(order.getDelivery() instanceof RegularDelivery) {
					RegularDelivery rd = (RegularDelivery) order.getDelivery();
					if(rd.getOrders().size()==1) {
						return this.removeDelivery(rd)&&this.getWaitForDecide().get(this.getCustomerArea(order.getCustomer())).remove(order);
					}
					else {
						return rd.removeOrder(order);
					}
				}
				else if(order.getDelivery() instanceof ExpressDelivery){
					return this.removeDelivery(order.getDelivery())&&this.getWaitForDecide().get(this.getCustomerArea(order.getCustomer())).remove(order);
				}
			}
			if(order.getStatus().equals(OrderStatus.inCookProgress)){
				return true;
			}
		}
		return false;
	}
	
	
	public boolean removeDelivery(Delivery delivery) {
		if(delivery == null || !getDeliveries().containsKey(delivery.getId()))
			return false;
		if(delivery instanceof RegularDelivery) {
			RegularDelivery rd = (RegularDelivery)delivery;
			for(Order o : rd.getOrders()) {
				o.setDelivery(null);
				this.getWaitForDecide().get(rd.getArea()).addLast(o);
			}
		}
		else {
			ExpressDelivery ed = (ExpressDelivery) delivery;
			ed.getOrder().setDelivery(null);
			this.getWaitForDecide().get(ed.getArea()).addLast(ed.getOrder());
		}
		return getDeliveries().remove(delivery.getId()) != null && delivery.getArea().removeDelivery(delivery);
	}

	public boolean removeDeliveryArea(DeliveryArea oldArea, DeliveryArea newArea) throws WrongAreaException {
		if(oldArea == null || newArea == null || !getAreas().containsKey(oldArea.getId()) || !getAreas().containsKey(newArea.getId()))
			return false;
		for(Neighberhood n : oldArea.getNeighberhoods()) {
			newArea.addNeighberhood(n);
		}
		for(Delivery d : oldArea.getDelivers()) {
			newArea.addDelivery(d);
		}
		for(DeliveryPerson dp : oldArea.getDelPersons()) {
			newArea.addDelPerson(dp);
		}
		for(DeliveryPerson dp : oldArea.getDelPersons()) {
			dp.setArea(newArea);
		}
		for(Delivery d : oldArea.getDelivers()) {
			d.setArea(newArea);
		}
		return getAreas().remove(oldArea.getId()) != null;
	}

	public Cook getRealCook(int id) {
		return getCooks().get(id);
	}

	public DeliveryPerson getRealDeliveryPerson(int id) {
		return getDeliveryPersons().get(id);
	}

	public Customer getRealCustomer(int id) {
		return getCustomers().get(id);
	}

	public Dish getRealDish(int id) {
		return getDishes().get(id);
	}

	public Component getRealComponent(int id) {
		return getComponenets().get(id);
	}

	public Order getRealOrder(int id) {
		return getOrders().get(id);
	}

	public Delivery getRealDelivery(int id) {
		return getDeliveries().get(id);
	}

	public DeliveryArea getRealDeliveryArea(int id) {
		return getAreas().get(id);
	}


	/*QUEREIES*/
	public Collection<Dish> getReleventDishList(Customer c){
		ArrayList<Dish> dishList = new ArrayList<>();
		if(!c.isSensitiveToGluten() && !c.isSensitiveToLactose())
			return getDishes().values();
		for(Dish d : getDishes().values()) {
			boolean isValid = true;
			for(Component comp : d.getComponenets()) {
				if(c.isSensitiveToGluten() && c.isSensitiveToLactose()) {
					if(comp.isHasGluten() || comp.isHasLactose())
						isValid = false;
				}
				else if(c.isSensitiveToGluten() && comp.isHasGluten()) {
					isValid = false;
				}
				else if(c.isSensitiveToLactose() && comp.isHasLactose()) {
					isValid = false;
				}
			}
			if(isValid)
				dishList.add(d);
		}
		return dishList;
	}
	
	public void deliver(Delivery d) {
		d.setDelivered(true);
		if(d instanceof ExpressDelivery) {
			((ExpressDelivery)d).getOrder().setReachToDestination(SpecificTime.now());
		}
		else {
			for(Order o:((RegularDelivery)d).getOrders()) {
				o.setReachToDestination(SpecificTime.now());
				Iterator<Dish> iter=o.getDishes().iterator();
				while(iter.hasNext()) {
					Dish dish=iter.next();
					if(!dish.isPublicDish()) {
						this.removeDish(dish);
					}
				}
			}
		}
	}
	
	public ArrayList<Cook> GetCooksByExpertise(Expertise e){
		ArrayList<Cook> cooks = new ArrayList<>();
		for(Cook c : getCooks().values()) {
			if(c.getExpert().equals(e))
				cooks.add(c);
		}
		return cooks;
	}
	
	/*NEW QUERIES*/
	public TreeSet<Delivery> getDeliveriesByPerson(DeliveryPerson dp , int month){
		TreeSet<Delivery> delivered = new TreeSet<>(new Comparator<Delivery>() {

			@Override
			public int compare(Delivery o1, Delivery o2) {
				if(o1.getDeliveredDate().getDayOfMonth() > o2.getDeliveredDate().getDayOfMonth())
					return 1;
				if(o1.getDeliveredDate().getDayOfMonth() < o2.getDeliveredDate().getDayOfMonth())
					return -1;
				return o1.getId()>o2.getId() ? 1 :-1;
			}
		});
		for(Delivery d: getDeliveries().values()) {
			if(d.getDeliveryPerson().equals(dp) && d.getDeliveredDate().getMonthValue() == month)
				delivered.add(d);
		}
		return delivered;
	}
	
	public HashMap<String,Integer> getNumberOfDeliveriesPerType(){
		HashMap<String, Integer> deliveriesPerType = new HashMap<>();
		deliveriesPerType.put("Regular delivery", 0);
		deliveriesPerType.put("Express delivery", 0);
		int amount;
		for(Delivery d: getDeliveries().values()) {
			LocalDate today = LocalDate.now();
			if(d instanceof RegularDelivery && d.getDeliveredDate().getYear() == today.getYear()) {
				amount = deliveriesPerType.get("Regular delivery");
				deliveriesPerType.put("Regular delivery",amount+1);
			}
			else {
				if(d.getDeliveredDate().getYear() == today.getYear()) {
					amount = deliveriesPerType.get("Express delivery");
					deliveriesPerType.put("Express delivery",amount+1);
				}
			}
		}
		return deliveriesPerType;
	}
	
	public double revenueFromExpressDeliveries() {
		HashSet<Customer> customers = new HashSet<>();
		double amountOfPostages = 0;
		for(Delivery d: getDeliveries().values()) {
			if(d instanceof ExpressDelivery) {
				ExpressDelivery ed = (ExpressDelivery)d;
				amountOfPostages+=ed.getPostage();
				customers.add(ed.getOrder().getCustomer());
			}
		}
		amountOfPostages+=(30*customers.size());
		return amountOfPostages;
	}
	
	public LinkedList<Component> getPopularComponents(){
		HashMap<Component, Integer> componentsandAmount = new HashMap<>();
		for(Dish d: getDishes().values()) {
			for(Component c: d.getComponenets()) {
				Integer numOfComponent = componentsandAmount.get(c);
				if(numOfComponent == null)
					numOfComponent = 0;
				componentsandAmount.put(c, numOfComponent+1);
			}
		}
		LinkedList<Component> popularComponents = new LinkedList<>();
		for(Component c: componentsandAmount.keySet()) {
			popularComponents.add(c);
		}
		popularComponents.sort(new Comparator<Component>() {

			@Override
			public int compare(Component o1, Component o2) {
				int result = -1 * componentsandAmount.get(o1).compareTo(componentsandAmount.get(o2));
				if(result !=0)
					return result;
				if(o1.getId() > o2.getId())
					return -1;
				return 1;
			}
		});
		return popularComponents;
	}
	
	public TreeSet<Dish> getProfitRelation(){
		TreeSet<Dish> profit = new TreeSet<Dish>((Dish o1, Dish o2) -> {
			if((o2.getPrice()/o2.getTimeToMake())>(o1.getPrice()/o1.getTimeToMake()))
				return 1;
			else if((o2.getPrice()/o2.getTimeToMake())<(o1.getPrice()/o1.getTimeToMake()))
				return -1;
			return -1*o1.getId().compareTo(o2.getId());
		});
		for(Dish d: getDishes().values()) {
			profit.add(d);
		}
		return profit;
	}
	
	public TreeSet<Delivery> createAIMacine(DeliveryPerson dp, DeliveryArea da, TreeSet<Order> orders) throws WrongAreaException{
		TreeSet<Delivery> AIDecision = new TreeSet<>(new Comparator<Delivery>() {

			@Override
			public int compare(Delivery o1, Delivery o2) {
				if(o2 instanceof RegularDelivery && o1 instanceof ExpressDelivery)
					return -1;
				if(o2 instanceof ExpressDelivery && o1 instanceof RegularDelivery)
					return 1;
				return o2.getId()>o1.getId() ? -1: 1;
			}
		});
		this.waitForDelivering.put(da, new LinkedList<Delivery>());
		TreeSet<Order> toRegularDelivery = new TreeSet<>();
		if(orders.size()<=2) {
			for(Order o: orders) {
				ExpressDelivery ed = new ExpressDelivery(dp, da, false, o,LocalDate.of(2021,1,1));
				AIDecision.add(ed);
				o.setStatus(OrderStatus.waitingForDelivering);
			}
		}
		else {
			for(Order o: orders) {
				if(o.getCustomer().isSensitiveToGluten() || o.getCustomer().isSensitiveToLactose()) {
					ExpressDelivery ed = new ExpressDelivery(dp, da, false, o,LocalDate.of(2021,1,1));
					AIDecision.add(ed);
				}
				else
					toRegularDelivery.add(o);
				o.setStatus(OrderStatus.waitingForDelivering);
			}
			if(toRegularDelivery.size()<2) {
				ExpressDelivery ed = new ExpressDelivery(dp, da, false, toRegularDelivery.first(),LocalDate.of(2021, 1, 1));
				AIDecision.add(ed);
			}
			else {
				RegularDelivery rd = new RegularDelivery(toRegularDelivery, dp, da, false, LocalDate.of(2021, 1, 1));
				AIDecision.add(rd);
			}
		}
		for(Delivery del:AIDecision) {
			System.out.println(del);
			this.addDelivery(del);
			this.waitForDelivering.get(da).addLast(del);
		}
		return AIDecision;
	}

	public HashMap<Account,Customer> getAccounts() {
		return accounts;
	}

	public void setAccounts(HashMap<Account,Customer> accounts) {
		this.accounts = accounts;
	}
	
	public void writeObject(ObjectOutputStream pw) throws IOException {

		for(DeliveryArea da:areas.values()) {
			pw.writeBytes(da.getId()+","+da.getAreaName()+","+da.getDeliverTime()+"\n");
			for(Neighberhood n:da.getNeighberhoods()) {
				pw.writeBytes(","+n);
			}
			pw.writeBytes("\n");
		}
		pw.writeBytes("areas end\n");
		for(Component c:componenets.values()) {
			pw.writeBytes(c.getId()+","+c.getComponentName()+","+c.isHasLactose()+","+c.isHasGluten()+","+c.getPrice()+","+c.getCalories()+","+c.getColesterol()+","+c.getNe()+"\n");
		}
		pw.writeBytes("componenets end\n");
		for(Dish d:dishes.values()) {
			pw.writeBytes(d.getId()+","+d.getDishName()+","+d.getType()+","+d.getTimeToMake()+","+d.getImage()+","+String.valueOf(d.isPublicDish())+"\n");
			for(Component c:d.getComponenets()) {
				pw.writeBytes(","+c.getId());
			}
			pw.writeBytes("\n");
		}
		pw.writeBytes("dishes end\n");
		for(Cook c:cooks.values()) {
			pw.writeBytes(c.getId()+","+c.getFirstName()+","+c.getLastName()+","+c.getBirthDay().format(DateTimeFormatter.BASIC_ISO_DATE)+","+c.getGender()+","+c.getExpert()+","+c.isChef()+","+c.getEmail()+","+c.getPhoneNumber()+"\n");
		}
		pw.writeBytes("cooks end\n");
		for(DeliveryPerson dp:deliveryPersons.values()) {
			pw.writeBytes(dp.getId()+","+dp.getFirstName()+","+dp.getLastName()+","+dp.getBirthDay().format(DateTimeFormatter.BASIC_ISO_DATE)+","+dp.getGender()+","+dp.getVehicle()+","+dp.getArea().getId()+","+dp.getEmail()+","+dp.getPhoneNumber()+"\n");
		}
		pw.writeBytes("deleveryPersons end\n");
		for(Customer c:customers.values()) {
			pw.writeBytes(c.getId()+","+c.getFirstName()+","+c.getLastName()+","+c.getBirthDay().format(DateTimeFormatter.BASIC_ISO_DATE)+","+c.getGender()+","+c.isSensitiveToGluten()+","+c.isSensitiveToLactose()+","+c.getNeighberhood()+","+c.getEmail()+","+c.getPhoneNumber()+","+c.getAccount().getUserName()+","+c.getAccount().getPassword()+","+c.getImage()+"\n");
		}
		pw.writeBytes("customers end\n");
		for(Order o:orders.values()) {
				pw.writeBytes(o.getId()+","+o.getCustomer().getId()+","+o.getStatus().name()+"\n");
			for(Dish d:o.getDishes()) {
				pw.writeBytes(","+d.getId());
			}
			pw.writeBytes("\n");
		}
		pw.writeBytes("orders end\n");
		for(Delivery d:deliveries.values()) {
			if(d instanceof RegularDelivery) {
				pw.writeBytes("RegularDelivery,"+d.getId()+","+d.getDeliveryPerson().getId()+","+d.getArea().getId()+","+d.isDelivered()+","+d.getDeliveredDate().format(DateTimeFormatter.BASIC_ISO_DATE)+"\n");
				for(Order o:((RegularDelivery)d).getOrders()) {
					pw.writeBytes(","+o.getId());
				}
				pw.writeBytes("\n");
			}
			else {
				pw.writeBytes("ExpressDelivery,"+d.getId()+","+d.getDeliveryPerson().getId()+","+d.getArea().getId()+","+d.isDelivered()+","+((ExpressDelivery)d).getOrder().getId()+","+((ExpressDelivery)d).getPostage()+","+d.getDeliveredDate().format(DateTimeFormatter.BASIC_ISO_DATE)+"\n");
			}
		}
		pw.writeBytes("deliveries end\n");
		//int id,DeliveryPerson deliveryPerson, DeliveryArea area, boolean isDelivered , Order order , double postage, LocalDate deliveredDate
		for(Customer c:blackList) {
			pw.writeBytes(","+c.getId());
		}
		pw.writeBytes("\nblacklist end\n");
		pw.close();
	}
	

	
	@SuppressWarnings("deprecation")
	public void readObject(ObjectInputStream bd) throws NumberFormatException, IOException, LieException, ToYoungException, InvalidIDException, InvalidPhoneException, InvalidEmailException, IllegalSalaryException, IllegalCustomerException, SensitiveException, WrongAreaException  {
		String Buffer;
		String[] BO;
		
		while(!(Buffer=bd.readLine()).equals("areas end")) {
			BO=Buffer.split(",");
			String[] ln=bd.readLine().split(",");
			HashSet<Neighberhood> hn=new HashSet<>();
			for(int i=1;i<ln.length;i++) {
				hn.add(Neighberhood.valueOf(ln[i]));
			}
			this.addDeliveryArea(new DeliveryArea(Integer.parseInt(BO[0]),BO[1],hn,Integer.parseInt(BO[2])));
		}
		while(!(Buffer=bd.readLine()).equals("componenets end")) {
			BO=Buffer.split(",");
			this.addComponent(new Component(Integer.parseInt(BO[0]),BO[1],Boolean.parseBoolean(BO[2]),Boolean.parseBoolean(BO[3]),Double.parseDouble(BO[4]),Arrays.asList(Double.parseDouble(BO[5]),Double.parseDouble(BO[6]),Double.parseDouble(BO[7]))));
		}
		while(!(Buffer=bd.readLine()).equals("dishes end")) {
			BO=Buffer.split(",");
			String[] comps=bd.readLine().split(",");
			ArrayList<Component> arcomp=new ArrayList<>();
			for(int i=1;i<comps.length;i++) {
				arcomp.add(this.getRealComponent(Integer.parseInt(comps[i])));
			}
			this.addDish(new Dish(Integer.parseInt(BO[0]),BO[1],DishType.valueOf(BO[2]),arcomp,Integer.parseInt(BO[3]),BO[4],Boolean.parseBoolean(BO[5])));
		}	
		while(!(Buffer=bd.readLine()).equals("cooks end")) {
			BO=Buffer.split(",");
			int bdint=Integer.parseInt(BO[3]);
			LocalDate ld=LocalDate.of(bdint/10000, (bdint/100)%100, bdint%100);
			this.addCook(new Cook(Integer.parseInt(BO[0]),BO[1],BO[2],ld,Gender.valueOf(BO[4]),Expertise.valueOf(BO[5]),Boolean.parseBoolean(BO[6]),BO[7],BO[8]));
		}
		
		while(!(Buffer=bd.readLine()).equals("deleveryPersons end")) {
			BO=Buffer.split(",");
			DeliveryArea da=this.getRealDeliveryArea(Integer.parseInt(BO[6]));
			int bdint=Integer.parseInt(BO[3]);
			LocalDate ld=LocalDate.of(bdint/10000, (bdint/100)%100, bdint%100);
			this.addDeliveryPerson(new DeliveryPerson(Integer.parseInt(BO[0]),BO[1],BO[2],ld,Gender.valueOf(BO[4]),Vehicle.valueOf(BO[5]),da,BO[7],BO[8]),da);	
		}
		
		while(!(Buffer=bd.readLine()).equals("customers end")) {
			BO=Buffer.split(",");
			int bdint=Integer.parseInt(BO[3]);
			LocalDate ld=LocalDate.of(bdint/10000, (bdint/100)%100, bdint%100);
			this.addCustomer(new Customer(Integer.parseInt(BO[0]),BO[1],BO[2],ld,Gender.valueOf(BO[4]),Neighberhood.valueOf(BO[7]),Boolean.parseBoolean(BO[5]),Boolean.parseBoolean(BO[6]),BO[8],BO[9],BO[10],Encryption.decrypt(BO[11]),BO[12]));
		}
		
		while(!(Buffer=bd.readLine()).equals("orders end")) {
			BO=Buffer.split(",");
			String[] dshs=bd.readLine().split(",");
			ArrayList<Dish> ardishes=new ArrayList<>();
			for(int i=1;i<dshs.length;i++) {
				ardishes.add(this.getRealDish(Integer.parseInt(dshs[i])));
			}
			Order o=new Order(Integer.parseInt(BO[0]),this.getRealCustomer(Integer.parseInt(BO[1])),ardishes,null,OrderStatus.valueOf(BO[2]));
			this.addOrder(o);
		}
		//"RegularDelivery,"+d.getId()+","+d.getDeliveryPerson().getId()+","+d.getArea().getId()+","+d.getDeliveredDate().format(DateTimeFormatter.BASIC_ISO_DATE)+"\n"
		while(!(Buffer=bd.readLine()).equals("deliveries end")) {
			BO=Buffer.split(",");
			if(BO[0].equals("RegularDelivery")) {
				String[] ords=bd.readLine().split(",");
				TreeSet<Order> treeords=new TreeSet<>();
				int bdint=Integer.parseInt(BO[5]);
				LocalDate ld=LocalDate.of(bdint/10000, (bdint/100)%100, bdint%100);
				for(int i=1;i<ords.length;i++) {
					treeords.add(this.getRealOrder(Integer.parseInt(ords[i])));
				}
				this.addDelivery(new RegularDelivery(Integer.parseInt(BO[1]),treeords,this.getRealDeliveryPerson(Integer.parseInt(BO[2])),this.getRealDeliveryArea(Integer.parseInt(BO[3])),Boolean.parseBoolean(BO[4]),ld));
			}
			else {
				int bdint=Integer.parseInt(BO[7]);
				LocalDate ld=LocalDate.of(bdint/10000, (bdint/100)%100, bdint%100);
				this.addDelivery(new ExpressDelivery(Integer.parseInt(BO[1]),this.getRealDeliveryPerson(Integer.parseInt(BO[2])),this.getRealDeliveryArea(Integer.parseInt(BO[3])),Boolean.parseBoolean(BO[4]),this.getRealOrder(Integer.parseInt(BO[5])),Double.parseDouble(BO[6]),ld));
			}
			
		}
		while(!(Buffer=bd.readLine()).equals("blacklist end")) {
			BO=Buffer.split(",");
			for(int i=1;i<BO.length;i++) {
				this.addCustomerToBlackList(this.getRealCustomer(Integer.parseInt(BO[i])));
			}
		}
		
	
		bd.close();
		//"ExpressDelivery,"+d.getId()+","+d.getDeliveryPerson().getId()+","+d.getArea().getId()+","+d.getDeliveredDate().format(DateTimeFormatter.BASIC_ISO_DATE)+","+((ExpressDelivery)d).getId()
		//int id,DeliveryPerson deliveryPerson, DeliveryArea area, boolean isDelivered , Order order , double postage, LocalDate deliveredDate
	}
}
