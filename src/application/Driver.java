package application;
import java.util.ArrayList;

// Driver class that represents each driver currently working for the company
public class Driver {
	// Create variables and array list
	private String name = "";
	private int currentLocationX = 0;
	private int currentLocationY = 0;
	private int hoursWorked = 0;
	private boolean available = true;
	private ArrayList<Order> orderList;
	private boolean reachedRest = false;
	
	public Driver() {
	}
	
	// Set driver class and variables
	public Driver(String name, int currentLocationX, int currentLocationY,
			int hoursWorked, boolean available, ArrayList<Order> orderList) {
		this.name = name;
		this.currentLocationX = currentLocationX;
		this.currentLocationY = currentLocationY;
		this.hoursWorked = hoursWorked;
		this.available = available;
		this.orderList = orderList;
	}
	
	public void addOrder(Order o) {
		orderList.add(o);
	}
	
	public ArrayList<Order> getOrder(){
		return orderList;
	}

	
	public boolean getReachedRest() {
		return reachedRest;
	}
	public void reachRest() {
		reachedRest = !reachedRest;
	}
	
	// Method to set new name
	public void setName(String newName) {
		if(!newName.equals(""))
			name = newName;
		else
			System.out.print("You must enter a name");
	}
	
	public void advanceCurrentLocationX() {
		currentLocationX += 20;
	}
	
	public void decreaseCurrentLocationX() {
		currentLocationX -= 20;
	}
	
	public void advanceCurrentLocationY() {
		currentLocationY += 20;
	}
	
	public void decreaseCurrentLocationY() {
		currentLocationY -= 20;
	}
	
	
	// Get methods (for test code)
	public String getName() {
		return this.name;
	}
	
	public int getCurrentLocationX() {
		return this.currentLocationX;
	}
	
	public int getCurrentLocationY() {
		return this.currentLocationY;
	}
	
	public int getHoursWorked() {
		return this.hoursWorked;
	}
	
	// Method to change availability
	public void changeAvailability() {
		available = !available;
	}
	
	public boolean getAvailability() {
		return this.available;
	}
}