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
import Utils.Expertise;
import Utils.Gender;
import Utils.SpecificTime;

public class Cook extends Worker{
	private static int idCounter = 1;
	private Expertise expert;
	private boolean isChef;

	
	public Cook(String firstName, String lastName, LocalDate birthDay, Gender gender, Expertise expert,
			boolean isChef, String email, String phone ) throws ToYoungException, InvalidIDException, InvalidPhoneException, UnknownHostException, InvalidEmailException, IllegalSalaryException {
		super(idCounter++, firstName, lastName, birthDay, gender, email, phone,isChef?8000.0:6000.0);
		this.setExpert(expert);
		this.setChef(isChef);
	}
	



	public Cook(int id,String firstName, String lastName, LocalDate birthDay, Gender gender, Expertise expert,
			boolean isChef, String email, String phone ) throws ToYoungException, InvalidIDException, InvalidPhoneException, UnknownHostException, InvalidEmailException, IllegalSalaryException {
		super(id, firstName, lastName, birthDay, gender, email, phone,isChef?8000.0:6000.0);
		this.setExpert(expert);
		this.setChef(isChef);
	}
	

	public Cook(int id) {
		super(id);
	}

	public Expertise getExpert() {
		return expert;
	}

	public void setExpert(Expertise expert) {
		this.expert = expert;
	}

	public boolean isChef() {
		return isChef;
	}

	public void setChef(boolean isChef) {
		this.isChef = isChef;
	}
	

	public static int getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(int idCounter) {
		Cook.idCounter = idCounter;
	}

	@Override
	public String toString() {
		return super.toString()+" " + expert;
	}
	
	public class Cooking extends Thread{
		
		private Order o;
		private Cook c;

		
		public Cooking(Order o,Cook c) {
			this.o=o;
			this.c=c;
		}
		
		
		@SuppressWarnings("deprecation")
		public void run() {
			o.setStartedToCook(SpecificTime.now());
			try {
			c.setBusy(true);
			int sum=0;
			for(Dish d:o.getDishes()) {
				sum+=d.getTimeToMake();
			}
			this.setUncaughtExceptionHandler(new DefaultException());
			Thread.sleep(60000*sum);

			c.setBusy(false);
			o.setFinishToCook(SpecificTime.now());

			DeliveryArea da=Restaurant.getRestaurant().getCustomerArea(o.getCustomer());
			Restaurant.getRestaurant().getWaitForDecide().get(da).addLast(o);
			
			for(DeliveryPerson dp:da.getDelPersons()) {
				
				if(!dp.isBusy()) {
						TreeSet<Delivery> ready=Restaurant.getRestaurant().createAIMacine(dp, da, new TreeSet<Order>(Restaurant.getRestaurant().getWaitForDecide().get(da)));
						Restaurant.getRestaurant().getWaitForDecide().get(da).clear();
						for(Delivery d: ready) {
							Restaurant.getRestaurant().getWaitForDelivering().get(da).addLast(d);
						}
				}
			}
			
			if(!Restaurant.getRestaurant().getWaitForCooking().isEmpty()) {
				Cooking cooking=new Cooking(Restaurant.getRestaurant().getWaitForCooking().poll(),c);
				cooking.start();
			}
			this.stop();

			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}



