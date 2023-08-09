package CRM;


import Exceptions.ToLateException;

/*that is class for entry object with specific details for the crm thread for orders process
 * the entry have comparability by the time from the order being created.**/



import Model.Order;
import Utils.OrderStatus;
import Utils.SpecificTime;
import Utils.TimeDistance;

public class OrderEntry implements Comparable<OrderEntry>{
	private Order order;
	private OrderStatus status;
	private TimeDistance timeLong;
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public TimeDistance getTimeLong() {
		return timeLong;
	}
	public void setTimeLong(SpecificTime timeLong) throws ToLateException  {
		this.timeLong = SpecificTime.now().Distance(order.getCreated());
	}
	public OrderEntry(Order order) throws  ToLateException {
		super();
		this.order = order;
		this.status = order.getStatus();
		this.timeLong = SpecificTime.now().Distance(order.getCreated());
	}
	@Override
	public int compareTo(OrderEntry o) {
		// TODO Auto-generated method stub
		int i=timeLong.compareTo(o.getTimeLong());
		return (i==0)?order.getId()-o.getOrder().getId():i;
	}
	@Override
	public String toString() {
		return order.getId() + " " + status + " " + timeLong;
	}
	
	
	
	
}
