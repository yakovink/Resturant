package Model;

/*I added here oolean field for private dishes that customers choose to add or remove dishes from them.
 * private dishes will be removed when they will get to their client**/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Utils.DishType;
public class Dish {
	
	private static int idCounter = 1;
	private int id;
	private String dishName;
	private DishType type;
	private ArrayList<Component> componenets;
	private double price;
	private int timeToMake;
	private Double calories;
	private Double colesterol;
	private Double Ne;

	private boolean publicDish;
	
	// constructors 
	
	public Dish(String dishName, DishType type, ArrayList<Component> componenets, int timeToMake, boolean publicDish) {
		super();
		this.id = idCounter++;
		this.setDishName(dishName);
		this.setType(type);
		this.componenets = componenets;
		this.setTimeToMake(timeToMake);
		this.calcDishPrice();
		this.setHealveProperties();
	}
	






	public boolean isPublicDish() {
		return publicDish;
	}



	public void setPublicDish(boolean publicDish) {
		this.publicDish = publicDish;
	}



	public Dish(int id, String dishName, DishType type, ArrayList<Component> componenets, int timeToMake,  boolean puBlicDish) {
		super();
		this.setId(id);
		this.setDishName(dishName);
		this.setType(type);
		this.componenets = componenets;
		this.setTimeToMake(timeToMake);
		this.calcDishPrice();
		this.setHealveProperties();
		if(id>=idCounter) {
			idCounter=id+1;
		}
	}
	
	public Dish(int id) {
		this.id = id;
	}
	
	// getters setters
	
	public static int getIdCounter() {
		return idCounter;
	}
	public static void setIdCounter(int idCounter) {
		Dish.idCounter = idCounter;
	}
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	public DishType getType() {
		return type;
	}
	public void setType(DishType type) {
		this.type = type;
	}
	public List<Component> getComponenets() {
		return Collections.unmodifiableList(this.componenets);
	}
	
	public double getPrice() {
		this.calcDishPrice();
		return price;
	}
	public void setPrice(double price) {
			this.price = price;

	}
	
	public double getCost() {
		double sum=0;
		for(Component c:this.getComponenets()) {
			sum+=c.getPrice();
		}
		return sum;
	}
	
	public Double getCalories() {
		return calories;
	}


	public Double getColesterol() {
		return colesterol;
	}


	public Double getNe() {
		return Ne;
	}
	
	public void setHealveProperties() {
		Double cal=0.0,col=0.0,ne=0.0;
		for(Component c:componenets) {
			cal+=c.getCalories();
			col+=c.getColesterol();
			ne+=c.getNe();
		}
		this.calories=cal;
		this.colesterol=col;
		this.Ne=ne;
	}

	public int getTimeToMake() {
		return timeToMake;
	}
	public void setTimeToMake(int timeToMake) {
		this.timeToMake = timeToMake;
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
		Dish other = (Dish) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return  id + " " + dishName + " " +type+" "+  price +" "+timeToMake;
	}
	
	
	// methods
	
	public double calcDishPrice() {
		double price = 0.0;
		for(Component c : getComponenets()) {
			price += c.getPrice();
		}
		price = price*3;
		this.price=price;
		return price;
	}
	
	public boolean addComponent(Component c) {
		return this.componenets.add(c);
	}
	
	public boolean removeComponent(Component c) {
		return this.componenets.remove(c);
	}
}
