package CRM;


import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import Exceptions.DefaultException;
import Model.*;

public class CrmThread extends Thread{
	private SortedSet<WorkerEntry> cooks;
	private SortedSet<WorkerEntry> deliveryPersons;
	private SortedSet<OrderEntry> orders;

	
	private static CrmThread mycrm;
	
	public static CrmThread getInstance() {
		if(mycrm==null) {
			mycrm=new CrmThread();
		}
		return mycrm;
	}

	public SortedSet<WorkerEntry> getCooks() {
		return cooks;
	}



	public void setCooks(TreeSet<WorkerEntry> cooks) {
		this.cooks = cooks;
	}



	public SortedSet<WorkerEntry> getDeliveryPersons() {
		return deliveryPersons;
	}



	public void setDeliveryPersons(TreeSet<WorkerEntry> deliveryPersons) {
		this.deliveryPersons = deliveryPersons;
	}



	public SortedSet<OrderEntry> getOrders() {
		return orders;
	}



	public void setOrders(TreeSet<OrderEntry> orders) {
		this.orders = orders;
	}



	public static CrmThread getMycrm() {
		return mycrm;
	}

	public static void setMycrm(CrmThread mycrm) {
		CrmThread.mycrm = mycrm;
	}
	
	public CrmThread() {
		
		cooks= Collections.synchronizedSortedSet(new TreeSet<WorkerEntry>());
		deliveryPersons= Collections.synchronizedSortedSet(new TreeSet<WorkerEntry>());
		orders=Collections.synchronizedSortedSet(new TreeSet<OrderEntry>()) ;
		
	}
	public void run() {
		CrmThread.setDefaultUncaughtExceptionHandler(new DefaultException());
		CrmListener listener=new CrmListener();
		listener.start();
		
		while(true) {
			try {
			cooks.clear();
			deliveryPersons.clear();
			orders.clear();
			
			for(Cook c:Restaurant.getRestaurant().getCooks().values()) {
				cooks.add(new WorkerEntry(c));
				
			}
			for(DeliveryPerson dp:Restaurant.getRestaurant().getDeliveryPersons().values()) {
				deliveryPersons.add(new WorkerEntry(dp));
			}
			for(Order o:Restaurant.getRestaurant().getOrders().values()) {
				if(o.getReachToDestination()==null) {
					this.orders.add(new OrderEntry(o));
				}
			}
			Thread.sleep(1000);
			}

			catch(Exception e) {	}
		}

	}
	
	
}
