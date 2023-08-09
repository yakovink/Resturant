package Model;


/*I added down there as internal class the delivering proccess.
 *  the delivering triggered if new delivery created by AI, if cook finish to do his job or if delivery person back from delivering.
 *  for the delivering process the delivery person will sleep twice - while coming to the customer, and while he come back.
 *  between them he will execute the deliver method and will check "delivered" on his delivery**/


import java.time.LocalDate;
import java.util.TreeSet;

import Exceptions.DefaultException;
import Utils.Gender;
import Utils.SpecificTime;
import Utils.Vehicle;

public class DeliveryPerson extends Worker {
	private static int idCounter = 1;
	private Vehicle vehicle;
	private DeliveryArea area;
	
	public DeliveryPerson(String firstName, String lastName, LocalDate birthDay, Gender gender, Vehicle vehicle,
			DeliveryArea area, String email, String phone) {
		super(idCounter++, firstName, lastName, birthDay, gender, email, phone, 5500.0);
		this.setVehicle(vehicle);
		this.setArea(area);
	}
	


	public DeliveryPerson(int id,String firstName, String lastName, LocalDate birthDay, Gender gender, Vehicle vehicle,
			DeliveryArea area, String email, String phone){
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
			this.setName("delivering_delivery_"+d.getId());
		}

		@SuppressWarnings("deprecation")
		public void run() {
			try {
				if(d==null) {
					dp.setBusy(false);
					this.stop();
				}
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
				Restaurant.getInstance().deliver(d);
				Thread.sleep(60000*d.getArea().getDeliverTime());
				dp.setBusy(false);
				if(!Restaurant.getInstance().getWaitForDecide().get(d.getArea()).isEmpty()) {
					Restaurant.getInstance().createAIMacine(dp, dp.getArea(),new TreeSet<>(Restaurant.getInstance().getWaitForDecide().get(d.getArea())));
					(new Delivering(Restaurant.getInstance().getWaitForDelivering().get(d.getArea()).poll(),dp)).start();
				}
				
				this.stop();
			}
			catch(Exception e) {
				System.err.println("Delivey person "+this.dp+" working thread has faild!");
				this.stop();
			}
				
	}
	}
}



