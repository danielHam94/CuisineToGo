// Not using scheduler for the assignment. Using controller

package application;

import java.util.ArrayList;

public class Scheduler {
	
	CuisineController c = new CuisineController();
	
	//Problem with the code is I can't get the driver or order lists from the controller. I don't know how to use the getter method from another class.
	ArrayList<Driver> driverList = c.getDriverList();
	ArrayList<Order> orderList = c.getList();

	public void assignDriver() {
	
	int minDistance = 100; 
	int counter = 0;
	int driverIndex = 0;

	for(int i = 0; i < driverList.size();i++) {
		//4th element of driverList is status, below line means if driver is available
        if (driverList.get(4).equals(true)) {
        	//Indices 1 and 2 in driverList are X and Y coordinates. Below I cast them to integers.
        	String distanceXString = driverList.get(1).toString();
        	int distanceX = Integer.parseInt(distanceXString);
        	String distanceYString = driverList.get(2).toString();
        	int distanceY = Integer.parseInt(distanceYString);
        	//Below, I take the absolute difference of x and y coordinates from the restaurant pick-up point
        	//(50, 50) to find the total distance and compare it to the minimum distance.
        	int totalDistance = (Math.abs(50 - distanceX) + Math.abs(50 - distanceY));
        	if (totalDistance < minDistance) {
        		minDistance = totalDistance;
        		//If the driver's distance is smaller than the minimum, the min value is updated, and the 
        		//index of driverList (based on counter) is updated to give the closest driver's value.
        		int currentDriver = counter;
        		//driverIndex is the index of the closest driver. This driver can be accessed in the driverList and assigned to the most recent order.
        		driverIndex = currentDriver;
        		}
        	}
        counter++;
        }
	
	//Here is where driver should be assigned to the newest order. The variable driverIndex should give the closest driver to restaurant (50,50)
	//and by using driverList.get(driverIndex) the driver should be returned. Then that driver can be assigned to the newest order with orderList.get(0)
	//I think this code would work I just can't get the driverList or orderList.
	
	}
}