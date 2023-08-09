package Model;

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
	private String image;
	private boolean publicDish;
	
	// constructors 
	
	public Dish(String dishName, DishType type, ArrayList<Component> componenets, int timeToMake, boolean puBlicDish) {
		super();
		this.id = idCounter++;
		this.setDishName(dishName);
		this.setType(type);
		this.componenets = componenets;
		this.setTimeToMake(timeToMake);
		this.calcDishPrice();
		this.setHealveProperties();
		this.image="images/dishDefault.png";
	}
	


	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public boolean isPublicDish() {
		return publicDish;
	}



	public void setPublicDish(boolean publicDish) {
		this.publicDish = publicDish;
	}



	public Dish(int id, String dishName, DishType type, ArrayList<Component> componenets, int timeToMake,String img,  boolean puBlicDish) {
		super();
		this.setId(id);
		this.setDishName(dishName);
		this.setType(type);
		this.componenets = componenets;
		this.setTimeToMake(timeToMake);
		this.calcDishPrice();
		this.setHealveProperties();
		this.setImage(img);
		if(id>=idCounter) {
			idCounter=id+1;
		}
		this.setImage(img);
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
		if(price > 0.0)
			this.price = price;
		else
			price = 0.0;
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
