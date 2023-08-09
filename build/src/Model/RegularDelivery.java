package Model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import Exceptions.WrongAreaException;

public class RegularDelivery extends Delivery {
	private TreeSet<Order> orders;
	
	public RegularDelivery(DeliveryPerson deliveryPerson, DeliveryArea area, boolean isDelivered,LocalDate deliveredDate) throws WrongAreaException {
		super(deliveryPerson, area, isDelivered, deliveredDate);
		this.orders = new TreeSet<>();
	}

	public RegularDelivery(TreeSet<Order> orders, DeliveryPerson deliveryPerson, DeliveryArea area,	boolean isDelivered,LocalDate deliveredDate) throws WrongAreaException {
		super(deliveryPerson, area, isDelivered, deliveredDate);
		
		for(Order o:orders) {
			if(!this.getArea().getNeighberhoods().contains(o.getCustomer().getNeighberhood())) {
				throw new WrongAreaException();
			}
		}
		this.orders = orders;
	}
	
	public RegularDelivery(int id,TreeSet<Order> orders, DeliveryPerson deliveryPerson, DeliveryArea area,	boolean isDelivered,LocalDate deliveredDate) throws WrongAreaException {
		super(id,deliveryPerson, area, isDelivered, deliveredDate);
		
		for(Order o:orders) {
			if(!this.getArea().getNeighberhoods().contains(o.getCustomer().getNeighberhood())) {
				throw new WrongAreaException();
			}
		}
		this.orders = orders;
	}
	
	public RegularDelivery(int id) {
		super(id);
	}

	public SortedSet<Order> getOrders() {
		return Collections.unmodifiableSortedSet(orders);
	}
	
	//Methods
	
	public boolean addOrder(Order o) throws WrongAreaException {
		if(!this.getArea().getNeighberhoods().contains(o.getCustomer().getNeighberhood())) {
			throw new WrongAreaException();
		}
		return orders.add(o);
	}
	
	@Override
	public String toString() {
		return "RegularDelivery "+super.toString();
	}

	public boolean removeOrder(Order o) {
		return orders.remove(o);
	}

}
