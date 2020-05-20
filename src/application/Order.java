package application;
import java.util.ArrayList;	

// Order class that represents individual order of food
public class Order{
	//Create array list and variables
	private ArrayList<String> foodList = new ArrayList();
	private int quantity = 1;
	private double pricePerItem = 1.0;
	private String nameOfCustomer = "";
	private String timeOfOrder = "";
	//Create two address variables x and y
	private int addressX = 0;
	private int addressY = 0;
	private boolean deliverStatus = false;
	private boolean assigned = false;

	public Order() {
	}
	
	// Set order class with variables
	public Order(ArrayList<String> foodList, int quantity, double pricePerItem, String nameOfCustomer,
			String timeOfOrder, int addressX, int addressY, boolean deliverStatus) {
		this.foodList = foodList;
		this.quantity = quantity;
		this.pricePerItem = pricePerItem;
		this.nameOfCustomer = nameOfCustomer;
		this.timeOfOrder = timeOfOrder;
		this.addressX = addressX;
		this.addressY = addressY;
		this.deliverStatus = deliverStatus;
	}
	
	public boolean isAssigned() {
		return assigned;
	}
	
	public void assign() {
		assigned = true;
	}
	
	// Method to change deliver status
	public void changeDeliverStatus() {
		deliverStatus = !deliverStatus;
	}

	// Print message
	public String toString() {
		String message = "Order: ";
		// For loop to get all items in food list
		for(String x : foodList) {
			message += x + " ";
		}
		message += 
				"\nQuantity: " + quantity +
				"\nPrice per Item: " + pricePerItem +
				"\nName of Customer: " + nameOfCustomer +
				"\nTime of Order: " + timeOfOrder +
				"\nAddress: " + addressX + " " + addressY +
				"\nDeliver Status: " + deliverStatus + "\n\n";
		return message;
		}
	
	// Get methods below (for test code)
	public String getCustomerName() {
		return this.nameOfCustomer;
	}
	
	public boolean getDeliveryStatus() {
		return this.deliverStatus;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public double getPricePerItem() {
		return this.pricePerItem;
	}
	
	public String getTime() {
		return this.timeOfOrder;
	}
	
	public int getAddressX() {
		return this.addressX;
	}
	
	public int getAddressY() {
		return this.addressY;
	}
}