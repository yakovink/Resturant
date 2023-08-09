package Model;


import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.TreeSet;

import Exceptions.DefaultException;
import Exceptions.IllegalSalaryException;
import Exceptions.InvalidEmailException;
import Exceptions.InvalidIDException;
import Exceptions.InvalidPhoneException;
import Exceptions.ToYoungException;
import Utils.Gender;
import Utils.SpecificTime;
import Utils.Vehicle;

public class DeliveryPerson extends Worker {
	private static int idCounter = 1;
	private Vehicle vehicle;
	private DeliveryArea area;
	
	public DeliveryPerson(String firstName, String lastName, LocalDate birthDay, Gender gender, Vehicle vehicle,
			DeliveryArea area, String email, String phone) throws ToYoungException, InvalidIDException, InvalidPhoneException, UnknownHostException, InvalidEmailException, IllegalSalaryException {
		super(idCounter++, firstName, lastName, birthDay, gender, email, phone, 5500.0);
		this.setVehicle(vehicle);
		this.setArea(area);
	}
	


	public DeliveryPerson(int id,String firstName, String lastName, LocalDate birthDay, Gender gender, Vehicle vehicle,
			DeliveryArea area, String email, String phone) throws ToYoungException, InvalidIDException, InvalidPhoneException, UnknownHostException, InvalidEmailException, IllegalSalaryException {
		super(id, firstName, lastName, birthDay, gender, email, phone, 5500.0);
		this.setVehicle(vehicle);
		this.setArea(area);
	}
	
	public DeliveryPerson(int id) {
		super(id);
	}
	
	public static int getIdCounter() {
		return idCounter;
	}
	public static void setIdCounter(int idCounter) {
		DeliveryPerson.idCounter = idCounter;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public DeliveryArea getArea() {
		return area;
	}
	public void setArea(DeliveryArea area) {
		this.area = area;
	}
	@Override
	public String toString() {
		return super.toString()+" " + vehicle;
	}
	
	public class Delivering extends Thread{
		private DeliveryPerson dp;
		private Delivery d;
		
		public Delivering(Delivery d,DeliveryPerson dp) {
			this.d=d;
			this.dp=dp;
		}

		@SuppressWarnings("deprecation")
		public void run() {
			try {
				Thread.setDefaultUncaughtExceptionHandler(new DefaultException());
				dp.setBusy(true);
				if(d instanceof RegularDelivery) {
					for(Order o:((RegularDelivery)d).getOrders()) {
						o.setSended(SpecificTime.now());
					}
				}
				else{
					((ExpressDelivery)d).getOrder().setSended(SpecificTime.now());
				}
				Thread.sleep(60000*d.getArea().getDeliverTime());
				Restaurant.getRestaurant().deliver(d);
				Thread.sleep(60000*d.getArea().getDeliverTime());
				dp.setBusy(false);
				if(!Restaurant.getRestaurant().getWaitForDecide().get(d.getArea()).isEmpty()) {
					Restaurant.getRestaurant().createAIMacine(dp, dp.getArea(),new TreeSet<>(Restaurant.getRestaurant().getWaitForDecide().get(d.getArea())));
					(new Delivering(Restaurant.getRestaurant().getWaitForDelivering().get(d.getArea()).poll(),dp)).start();
				}
				
				this.stop();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	}
	}
}



