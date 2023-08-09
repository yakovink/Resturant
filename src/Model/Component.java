package Model;

import java.io.Serializable;
import java.util.List;


public class Component implements Comparable<Component>, Serializable{

	/**
	 * I added here some healve properties. because it a junk restaurant, it imortant for people to know that.
	 */
	private static final long serialVersionUID = 535979989738043841L;
	private static int idCounter = 1;
	private Integer id;
	private String componentName;
	private boolean hasLactose;
	private boolean hasGluten;
	private Double price;
	private Double calories;
	private Double colesterol;
	private Double Ne;
	
	public Component(String componentName, boolean hasLactose, boolean hasGluten, double price, List<Double> healveProperties) {
		this.id = idCounter++;
		this.setComponentName(componentName);
		this.setHasLactose(hasLactose);
		this.setHasGluten(hasGluten);
		this.setCalories(healveProperties.get(0));
		this.setColesterol(healveProperties.get(1));
		this.setNe(healveProperties.get(2));
		setPrice(price);
	}
	
	public Component(int id, String componentName, boolean hasLactose, boolean hasGluten, double price, List<Double> healveProperties){
		super();
		this.id = id;
		this.setComponentName(componentName);
		this.setHasLactose(hasLactose);
		this.setHasGluten(hasGluten);
		this.setCalories(healveProperties.get(0));
		this.setColesterol(healveProperties.get(1));
		this.setNe(healveProperties.get(2));
		setPrice(price);
		if(id>=idCounter) {
			idCounter=id+1;
		}
	}
	
	public Double getCalories() {
		return calories;
	}

	public void setCalories(Double calories) {

		this.calories = calories;
	}

	public Double getColesterol() {
		return colesterol;
	}

	public void setColesterol(Double colesterol){

		this.colesterol = colesterol;
	}

	public Double getNe() {
		return Ne;
	}

	public void setNe(Double ne) {
		Ne = ne;
	}

	public Component(int id) {
		this.id = id;
	}

	public static int getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(int idCounter) {
		Component.idCounter = idCounter;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public boolean isHasLactose() {
		return hasLactose;
	}

	public void setHasLactose(boolean hasLactose) {
		this.hasLactose = hasLactose;
	}

	public boolean isHasGluten() {
		return hasGluten;
	}

	public void setHasGluten(boolean hasGluten) {
		this.hasGluten = hasGluten;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
			this.price = price;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Component other = (Component) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return id+" "+this.componentName+" "+price;
	}


	@Override
	public int compareTo(Component o) {
		if(this.price.compareTo(o.getPrice())!=0)
			return this.price.compareTo(o.getPrice());
		return this.id.compareTo(o.getId());
	}
}
