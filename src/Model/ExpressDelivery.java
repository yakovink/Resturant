package Model;

import java.time.LocalDate;

import Exceptions.WrongAreaException;
/*I didnt change anything important here**/
public class ExpressDelivery extends Delivery {
	private Order order;
	private double postage;
	
	public ExpressDelivery(DeliveryPerson deliveryPerson, DeliveryArea area, boolean isDelivered , Order order , double postage, LocalDate deliveredDate) throws WrongAreaException {
		super(deliveryPerson, area, isDelivered, deliveredDate);
		this.setOrder(order);
		this.setPostage(postage);
	}
	
	public ExpressDelivery(int id,DeliveryPerson deliveryPerson, DeliveryArea area, boolean isDelivered , Order order , double postage, LocalDate deliveredDate) throws WrongAreaException {
		super(id,deliveryPerson, area, isDelivered, deliveredDate);
		this.setOrder(order);
		this.setPostage(postage);
	}
	
	public ExpressDelivery(DeliveryPerson deliveryPerson, DeliveryArea area, boolean isDelivered , Order order, LocalDate deliveredDate) throws WrongAreaException {
		super(deliveryPerson, area, isDelivered,deliveredDate);
		this.setOrder(order);
		this.setPostage(area.getDeliverTime());
	}
	
	public ExpressDelivery(int id) {
		super(id);
	}
	
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) throws WrongAreaException {
		if(!this.getArea().getNeighberhoods().contains(order.getCustomer().getNeighberhood())) {
			throw new WrongAreaException();
		}
		this.order = order;
	}
	
	public double getPostage() {
		return postage;
	}
	
	public void setPostage(double postage) {
		this.postage = postage;
	}
	
	@Override
	public String toString() {
		return "Express " +super.toString() + " " + postage;
	}	
}
