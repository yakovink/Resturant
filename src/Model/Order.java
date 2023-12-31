package Model;

import java.util.*;

import Utils.OrderStatus;
import Utils.SpecificTime;

/*I added here fields for monitoring the ordering process - Times for checkOuts and statis field.
 * I added more method for calculate the time get for that order to reach the customer house.**/

public class Order implements Comparable<Order>{
	private static int idCounter = 1;
	private Integer id;
	private Customer customer;
	private ArrayList<Dish> dishes;
	private Delivery delivery;
	private SpecificTime created;
	private SpecificTime startedToCook;
	private SpecificTime finishToCook;
	private SpecificTime waitToDeliver;
	private SpecificTime sended;
	private SpecificTime ReachToDestination;
	private OrderStatus status;
	
	// constructors 
	
	public Order(Customer customer, ArrayList<Dish> dishes, Delivery delivery) {
		super();
		this.id = idCounter++;
		this.setCustomer(customer);
		this.dishes = dishes;
		this.setDelivery(delivery);
		this.setCreated(SpecificTime.now());
	}
	
	public Order(int id,Customer customer, ArrayList<Dish> dishes, Delivery delivery, OrderStatus status, SpecificTime create) {
		super();
		this.id = id;
		this.setCustomer(customer);
		this.dishes = dishes;
		this.setDelivery(delivery);
		this.setStatus(status);
		if(status.equals(OrderStatus.Delivered)) {
			this.setCreated(create);
			this.setReachToDestination(create);
		}
		else if(status.equals(OrderStatus.inCookProgress)||status.equals(OrderStatus.waitingForCook)||status.equals(OrderStatus.waitingForDecide)){
			this.setCreated(SpecificTime.now());
		}
		else {
			this.setCreated(create);
			this.setWaitToDeliver(SpecificTime.now());
		}
		if(id>=idCounter) {
			idCounter=id+1;
		}
		
	}
	


	public SpecificTime getCreated() {
		return created;
	}

	public void setCreated(SpecificTime created) {
		this.created=SpecificTime.now();
		this.setStatus(OrderStatus.waitingForCook);
		
	}

	public SpecificTime getStartedToCook() {
		return startedToCook;
	}

	public void setStartedToCook(SpecificTime startedToCook) {
		this.startedToCook = SpecificTime.now();
		this.setStatus(OrderStatus.inCookProgress);
	}

	public SpecificTime getFinishToCook() {
		return finishToCook;
	}

	public void setFinishToCook(SpecificTime finishToCook) {
		this.finishToCook = SpecificTime.now();
		this.setStatus(OrderStatus.waitingForDecide);
	}

	public SpecificTime getWaitToDeliver() {
		return waitToDeliver;
	}

	public void setWaitToDeliver(SpecificTime waitToDeliver) {
		this.finishToCook = SpecificTime.now();
		this.setStatus(OrderStatus.waitingForDelivering);
	}

	public SpecificTime getSended() {
		return sended;
	}

	public void setSended(SpecificTime sended) {
		this.sended = SpecificTime.now();
		this.setStatus(OrderStatus.inDeliveryProgress);
	}

	public SpecificTime getReachToDestination() {
		return ReachToDestination;
	}

	public void setReachToDestination(SpecificTime reachToDestination) {
		ReachToDestination = SpecificTime.now();
		this.setStatus(OrderStatus.Delivered);
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}



	public Order(int id) {
		this.id = id;
	}
	
	// getters setters
	
	public Double getPrice() {
		Double sum=0.0;
		for(Dish d:dishes) {
			sum+=d.getPrice();
		}
		return sum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Dish> getDishes() {
		return Collections.unmodifiableList(dishes);
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	@Override
	public String toString() {
		return  id + " " + customer;
	}
	
	// methods

	public double calcOrderRevenue() {
		double revenue = 0.0;
		for(Dish d : getDishes()) {
			double price = d.calcDishPrice();
			double cost = 0.0;
			for(Component c : d.getComponenets()) {
				cost += c.getPrice();
			}
			revenue += (price - cost);
		}
		return revenue;
	}
	
	public int orderWaitingTime(DeliveryArea da) {
		int time = 0;
		time += da.getDeliverTime();
		for(Dish d : getDishes()) {
			time += d.getTimeToMake();
		}
		return time;
	}

	
	public boolean addDish(Dish d) {
		return dishes.add(d);
	}
	
	public boolean removeDish(Dish d) {
		return dishes.remove(d);
	}
	
	@Override
	public int compareTo(Order o) {
		return this.id.compareTo(o.getId());
	}

}
