package Model;

import java.time.LocalDate;

/*I didnt change anything important here**/

import Exceptions.WrongAreaException;

public abstract class Delivery {
	private static int idCounter = 1;
	private int id;
	private DeliveryPerson deliveryPerson;
	private DeliveryArea area;
	private boolean isDelivered;
	private LocalDate deliveredDate;
	
	public Delivery(DeliveryPerson deliveryPerson, DeliveryArea area, boolean isDelivered,LocalDate diliveredDate) throws WrongAreaException {
		super();
		this.id = idCounter++;
		this.setDeliveryPerson(deliveryPerson);
		this.setArea(area);
		this.setDelivered(isDelivered);
		this.setDeliveredDate(diliveredDate);
	}
	
	public Delivery(int id,DeliveryPerson deliveryPerson, DeliveryArea area,boolean isDelivered,LocalDate diliveredDate) throws WrongAreaException {
		super();
		this.id = id;
		this.setDeliveryPerson(deliveryPerson);
		this.setArea(area);
		this.setDelivered(isDelivered);
		this.setDeliveredDate(diliveredDate);
		if(id>=idCounter) {
			idCounter=id+1;
		}
	}
	
	public Delivery(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public DeliveryPerson getDeliveryPerson() {
		return deliveryPerson;
	}

	public void setDeliveryPerson(DeliveryPerson deliveryPerson) {
		this.deliveryPerson = deliveryPerson;
	}

	public DeliveryArea getArea() {
		return area;
	}

	public void setArea(DeliveryArea area) throws WrongAreaException{
		this.area = area;
	}

	public boolean isDelivered() {
		return isDelivered;
	}

	public void setDelivered(boolean isDelivered) {
		this.isDelivered = isDelivered;
	}
	

	public LocalDate getDeliveredDate() {
		return deliveredDate;
	}

	public void setDeliveredDate(LocalDate deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Delivery other = (Delivery) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id + " " + deliveryPerson.getFirstName()+" "+deliveryPerson.getLastName()+ " " + area +" "+ ((isDelivered)?"delivered":"") ;
	}


}
