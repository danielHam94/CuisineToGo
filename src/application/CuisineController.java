package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.control.RadioButton;

public class CuisineController {
	@FXML
	private AnchorPane map;
	@FXML
	private Button stepButton;
	@FXML
	private Button createOrderButton;
	@FXML
	private TextField foodNameTextfield;
	@FXML
	private TextField quantityTextField;
	@FXML
	private TextField priceTextField;
	@FXML
	private TextField customerNameTextField;
	@FXML
	private TextField timeTextField;
	@FXML
	private TextField addressTextField;
	@FXML
	private RadioButton onButton;
	@FXML
	private ToggleGroup logInOut;
	@FXML
	private RadioButton offButton;
	@FXML
	private Button createDriverButton;
	@FXML
	private TextField driverNameTextField;
	@FXML
	private TextField locationTextField;
	@FXML
	private TextField grossIncomeTextField;
	@FXML
	private TextField deliveryCountTextField;
	@FXML
	private TextField restaurantField;
	@FXML
	private TextField restaurantNameTextField;
	@FXML
	private TextField restaurantLocationTextField;
	@FXML
	private Button createRestaurantButton;
	@FXML
	private ChoiceBox restaurantBox;
	@FXML
	private ToggleGroup tipGroup;
	@FXML
	private RadioButton tenButton;
	@FXML
	private RadioButton fifteenButton;
	@FXML
	private RadioButton twentyButton;
	@FXML
	private RadioButton customButton;
	@FXML
	private TextField tipAmountTextField;
	@FXML
	private TextField averageTipTextField;
	@FXML
	private TextField totalTipTextField;
	
	// Variables for delivered count and gross income
	int deliveryCount = 0;
	double grossIncome = 0;
	
	// Variables for average tip and total tip
	double averageTip = 0;
	double totalTip = 0;
	
	// Array lists for orders and drivers
	private ArrayList<Order> orderList = new ArrayList<>();
	private ArrayList<Driver> driverList = new ArrayList<>();
	
	// Declare circle outside so we can access to remove in other buttons below
	private static Circle newCustomer;
	// Create order button
	
    HashMap<String, String> restaurantLocation = new HashMap<>(); 

	
	@FXML
	void createRestaurantButtonClick(ActionEvent event) {
		String restaurantName;
		String restAddress;
		restaurantName = restaurantNameTextField.getText();
		restAddress = restaurantLocationTextField.getText();
		restaurantBox.getItems().add(restaurantName);
		restaurantLocation.put(restaurantName, restAddress);
	}
	
	@FXML
	void createOrderButtonClick(ActionEvent event) {
		// Declare Array list and variables
		ArrayList<String> foodList = new ArrayList();
		String nameOfFood;
		int quantity;
		double pricePerItem; 
		String customerName;
		String time;
		String address;
		String restaurantAddress;
		
		// Set order information from text field
		nameOfFood = foodNameTextfield.getText();
		foodList.add(nameOfFood);
		quantity = Integer.parseInt(quantityTextField.getText());
		pricePerItem = Double.parseDouble(priceTextField.getText());
		customerName = customerNameTextField.getText();
		time = timeTextField.getText();
		address = addressTextField.getText();
		
		// Split address to X and Y
		String[] addressArray = address.split(",");
		int addressX = Integer.parseInt(addressArray[0]);
		int addressY = Integer.parseInt(addressArray[1]);
		
		restaurantAddress = restaurantLocation.get((String) restaurantBox.getValue());
		
		if (restaurantAddress.isEmpty()) {
			int restaurantAddressX = 100;
			int restaurantAddressY = 100;
			Circle restaurant = new Circle(100,100,10,Color.AQUA);
			map.getChildren().add(restaurant);
		}
		else {
			String[] restaurantAddressArray = restaurantAddress.split(",");
			int restaurantAddressX = Integer.parseInt(restaurantAddressArray[0]);
			int restaurantAddressY = Integer.parseInt(restaurantAddressArray[1]);
			Circle restaurant = new Circle(restaurantAddressX,restaurantAddressY,10,Color.AQUA);
			map.getChildren().add(restaurant);
		// Driver moves for order every time step button is clicked

		}
		
		// Place customer in grid
		newCustomer = new Circle(addressX,addressY,5,Color.GOLD);
		map.getChildren().add(newCustomer);
		
		
		// Create order and add to order list
		Order order = new Order(foodList, quantity, pricePerItem, customerName, time, addressX, addressY, false);
		orderList.add(order);
	}
	
	public static Circle creatingDriver;
	// Create driver button
	@FXML
	void createDriverButtonClick(ActionEvent event) {
		// Declare variables
		String driverName;
		String driverLocation;
		
		// Get driver name and address from text field
		driverName = driverNameTextField.getText();
		driverLocation = locationTextField.getText();
		
		// Split address to X and Y
		String[] driverAddressArray = driverLocation.split(",");
		int addressX = Integer.parseInt(driverAddressArray[0]);
		int addressY = Integer.parseInt(driverAddressArray[1]);
		
		// Button to show availability of driver
		boolean status = true;
		if(onButton.isSelected()) {
			status = true;
		} else {
			status = false;
		}
		
		// Place driver in grid
		creatingDriver = new Circle(addressX,addressY,5,Color.PURPLE);
		map.getChildren().add(creatingDriver);
		
		
		// Create driver and add to driver list
		Driver driver = new Driver(driverName, addressX, addressY, 
				0, status, orderList);
		driverList.add(driver);
	}
	
	// Step button
	@FXML
	void stepButtonClick(ActionEvent event) {
		// Remove driver start point to show driver moving
		map.getChildren().remove(creatingDriver);
		// Restaurant at 100,100
		assignDriver();
		
		for(Driver d : driverList) {
			moveDriver(d);
		}
		
	}
	
	// Variables to get if-else statements and conditions working below
	int tempCount = 0;
	Random speed = new Random();
	int n = tempCount + speed.nextInt(6);
	boolean reachedRest = false;
	public static Circle newDriver;
	
	
	public void assignDriver() {
		System.out.println(reachedRest);
		int minDistance = 200; 
		int counter = 0;
		int driverIndex = 0;
		String restaurantAddress;
		int restaurantAddressX;
		int restaurantAddressY;
		
		restaurantAddress = restaurantLocation.get((String) restaurantBox.getValue());
		
		if (restaurantAddress.isEmpty()) {
			restaurantAddressX = 100;
			restaurantAddressY = 100;
		}
		else {
			String[] restaurantAddressArray = restaurantAddress.split(",");
			restaurantAddressX = Integer.parseInt(restaurantAddressArray[0]);
			restaurantAddressY = Integer.parseInt(restaurantAddressArray[1]);
		}

		
		//Loop through available drivers
		Driver toBeAssigned = null;
		for(Driver driver: driverList) {
	        if (driver.getAvailability() == true) {
	        	//Indices 1 and 2 in driverList are X and Y coordinates. Below I cast them to integers.
	            int distanceX = driver.getCurrentLocationX();
	        	int distanceY = driver.getCurrentLocationY();
	        	//Below, I take the absolute difference of x and y coordinates from the restaurant pick-up point
	        	//(100,100) to find the total distance and compare it to the minimum distance.
	        	int totalDistance = (Math.abs(restaurantAddressX - distanceX) + Math.abs(restaurantAddressY - distanceY));
	        	if (totalDistance < minDistance) {
	        		minDistance = totalDistance;
	        		//If the driver's distance is smaller than the minimum, the min value is updated, and the 
	        		//index of driverList (based on counter) is updated to give the closest driver's value.
	        		int closestDriver = counter;
	        		//driverIndex is the index of the closest driver. This driver can be accessed in the driverList and assigned to the most recent order.
	        		driverIndex = closestDriver;
	        		toBeAssigned = driver;
	        		break;
	        		}
	        	}
	        counter++;
	        }
		if(toBeAssigned == null) {
			toBeAssigned = driverList.get(0);
			driverList.get(0).changeAvailability();
		}
		
		Order currentOrder = new Order();
		for(Order o : orderList) {
			if(o.getDeliveryStatus() == false) {
				if(!o.isAssigned()) {
					toBeAssigned.addOrder(o);
					o.assign();
					break;
				}
			}
		}
		
		
        	}
	
	// Move driver to each order
	public void moveDriver(Driver currentDriver) {
		
		Order currentOrder = null;
		for(Order o : currentDriver.getOrder()) {
			// if order is not delivered set currentOrder = o
			if(o.getDeliveryStatus() == false) {
				currentOrder = o;
				break;
			}
		}
		 
		String restaurantAddress;
		int restaurantAddressX;
		int restaurantAddressY;
		
		restaurantAddress = restaurantLocation.get((String) restaurantBox.getValue());
		
		if (restaurantAddress.isEmpty()) {
			restaurantAddressX = 100;
			restaurantAddressY = 100;
		}
		else {
			String[] restaurantAddressArray = restaurantAddress.split(",");
			restaurantAddressX = Integer.parseInt(restaurantAddressArray[0]);
			restaurantAddressY = Integer.parseInt(restaurantAddressArray[1]);
		}
		// move driver movement into here
		currentDriver.changeAvailability();
		int orderX;
		int orderY;
		try {
			orderX = currentOrder.getAddressX();
			orderY = currentOrder.getAddressY();
		} catch(Exception e) {
			orderX = restaurantAddressX;
			orderY = restaurantAddressY;
		}
        
		/*String restaurantAddress;
		int restaurantAddressX;
		int restaurantAddressY;
		
		restaurantAddress = restaurantLocation.get((String) restaurantBox.getValue());
		
		if (restaurantAddress.isEmpty()) {
			restaurantAddressX = 100;
			restaurantAddressY = 100;
		}
		else {
			String[] restaurantAddressArray = restaurantAddress.split(",");
			restaurantAddressX = Integer.parseInt(restaurantAddressArray[0]);
			restaurantAddressY = Integer.parseInt(restaurantAddressArray[1]);
		}*/
        
        if(!reachedRest)
        {
        if(currentDriver.getCurrentLocationX() < restaurantAddressX) {
        	map.getChildren().remove(newDriver);
        	currentDriver.advanceCurrentLocationX();
        	newDriver = new Circle(currentDriver.getCurrentLocationX(),currentDriver.getCurrentLocationY(),5,Color.PURPLE);
    		map.getChildren().add(newDriver);
        }
        else if(currentDriver.getCurrentLocationX() > restaurantAddressX) {
        	map.getChildren().remove(newDriver);
        	currentDriver.decreaseCurrentLocationX();
    		newDriver = new Circle(currentDriver.getCurrentLocationX(),currentDriver.getCurrentLocationY(),5,Color.PURPLE);
    		map.getChildren().add(newDriver);
        }
        else if(currentDriver.getCurrentLocationY() < restaurantAddressY) {
        	map.getChildren().remove(newDriver);
        	currentDriver.advanceCurrentLocationY();
    		newDriver = new Circle(currentDriver.getCurrentLocationX(),currentDriver.getCurrentLocationY(),5,Color.PURPLE);
    		map.getChildren().add(newDriver);
        }
        else if(currentDriver.getCurrentLocationY() > restaurantAddressY) {
        	map.getChildren().remove(newDriver);
        	currentDriver.decreaseCurrentLocationY();
    		newDriver = new Circle(currentDriver.getCurrentLocationX(),currentDriver.getCurrentLocationY(),5,Color.PURPLE);
    		map.getChildren().add(newDriver);
        } else if((currentDriver.getCurrentLocationX() == restaurantAddressX && currentDriver.getCurrentLocationY() == restaurantAddressY)) {
        	map.getChildren().remove(newDriver);
        	reachedRest = true;
        	tempCount++;
        }
        }
        //else if reachedRest == true
        else
        {
        	tempCount++;
        	if(tempCount>=n)
        	{
        		if(currentDriver.getCurrentLocationX() < orderX) {
                	map.getChildren().remove(newDriver);
        			currentDriver.advanceCurrentLocationX();
                	newDriver = new Circle(currentDriver.getCurrentLocationX(),currentDriver.getCurrentLocationY(),5,Color.PURPLE);
            		map.getChildren().add(newDriver);
            		tempCount++;
                }
                else if(currentDriver.getCurrentLocationX() > orderX) {
                	map.getChildren().remove(newDriver);
                	currentDriver.decreaseCurrentLocationX();
            		newDriver = new Circle(currentDriver.getCurrentLocationX(),currentDriver.getCurrentLocationY(),5,Color.PURPLE);
            		map.getChildren().add(newDriver);
            		tempCount++;
                }
                else if(currentDriver.getCurrentLocationY() < orderY) {
                	map.getChildren().remove(newDriver);
                	currentDriver.advanceCurrentLocationY();
            		newDriver = new Circle(currentDriver.getCurrentLocationX(),currentDriver.getCurrentLocationY(),5,Color.PURPLE);
            		map.getChildren().add(newDriver);
            		tempCount++;
                }
                else if(currentDriver.getCurrentLocationY() > orderY) {
                	map.getChildren().remove(newDriver);
                	currentDriver.decreaseCurrentLocationY();
            		newDriver = new Circle(currentDriver.getCurrentLocationX(),currentDriver.getCurrentLocationY(),5,Color.PURPLE);
            		map.getChildren().add(newDriver);
            		tempCount++;
                }
                else if((currentDriver.getCurrentLocationX() == orderX && currentDriver.getCurrentLocationY() == orderY)) {
                	// Results when driver completes delivery
                	map.getChildren().remove(newDriver);
                	
                	//Attempt to remove the customer location after delivery
                	
                	map.getChildren().remove(newCustomer);
                	
                	
                	//map.getChildren().remove(new Circle(currentOrder.getAddressX(),currentOrder.getAddressY(),5,Color.GOLD));
                	
                	
                	int index = orderList.indexOf(currentOrder);
                	orderList.get(index).changeDeliverStatus();
                	currentDriver.changeAvailability();
                	new Alert(AlertType.INFORMATION, "Order Delivered by " + currentDriver.getName() + "\nOrder Time: " + tempCount + " units.").show();
                	//Deliver count and gross income
                	deliveryCount++;
                	grossIncome += (currentOrder.getPricePerItem() * currentOrder.getQuantity());
                	String deliveryCountOutput = String.valueOf(deliveryCount);
                	grossIncomeTextField.setText("$" + String.format("%.2f", grossIncome));
                	deliveryCountTextField.setText(deliveryCountOutput);
                	
                	if (tenButton.isSelected()) {
                		totalTip += (currentOrder.getPricePerItem() * currentOrder.getQuantity()) * .10;
                		totalTipTextField.setText("$" + String.format("%.2f", totalTip));
                	}
                	else if (fifteenButton.isSelected()) {
                		totalTip += (currentOrder.getPricePerItem() * currentOrder.getQuantity()) * .15;
                		totalTipTextField.setText("$" + String.format("%.2f", totalTip));
                	}
                	else if (twentyButton.isSelected()) {
                		totalTip += (currentOrder.getPricePerItem() * currentOrder.getQuantity()) * .20;
                		totalTipTextField.setText("$" + String.format("%.2f", totalTip));
                	}
                	else if (customButton.isSelected()) {
                		totalTip += (currentOrder.getPricePerItem() * currentOrder.getQuantity()) * Double.parseDouble(tipAmountTextField.getText());
                		totalTipTextField.setText("$" + String.format("%.2f", totalTip));
                	}
                	                	
                	averageTipTextField.setText("$" + String.format("%.2f", (totalTip / deliveryCount)));
                	
                	reachedRest = false;
                	tempCount = 0;
                	
                	// Clear text fields
                	foodNameTextfield.setText("");
                	quantityTextField.setText("");
                	priceTextField.setText("");
                	customerNameTextField.setText("");
                	timeTextField.setText("");
                	addressTextField.setText("");
                	driverNameTextField.setText("");
                	locationTextField.setText("");
                	tipAmountTextField.setText("");
                	restaurantNameTextField.setText("");
                	restaurantLocationTextField.setText("");

                	// Reset all buttons
                	onButton.setSelected(false);
                	offButton.setSelected(false);
                	tenButton.setSelected(false);
                	fifteenButton.setSelected(false);
                	twentyButton.setSelected(false);
                	customButton.setSelected(false);
                }
        	}
        }
        
                	
                	
                	
	}
        
    	
	
	// Get order list (for test code)
	public  ArrayList<Order> getList() {
		return this.orderList;
	}
	
	// Get driver list (for test code)
	public  ArrayList<Driver> getDriverList() {
		return this.driverList;
	}

}
