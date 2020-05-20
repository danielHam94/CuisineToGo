package application;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.TestCase;

public class DriverTest {

	@Test
	public void testName() {
		Driver testDriver = new Driver("Andy", 10, 10, 50, false, null);
		testDriver.setName("Ross");
		assertEquals("The name is the same", "Ross", testDriver.getName());
	}
	
	@Test
	public void testCurrentLocationX() {
		Driver testDriver = new Driver("Andy", 10, 10, 50, false, null);
		assertEquals("Current Location X is correct", 10, testDriver.getCurrentLocationX());
	}
	
	@Test
	public void testCurrentLocationY() {
		Driver testDriver = new Driver("Andy", 10, 10, 50, false, null);
		assertEquals("Current Location Y is correct", 10, testDriver.getCurrentLocationY());
	}
	
	@Test
	public void testHoursWorked() {
		Driver testDriver = new Driver("Andy", 10, 10, 50, false, null);
		assertEquals("Hours are correct", 50, testDriver.getHoursWorked());
	}
	
	@Test
	public void testAvailability() {
		Driver testDriver = new Driver("Andy", 10, 10, 50, false, null);
		testDriver.changeAvailability();
		assertTrue("Driver is available", testDriver.getAvailability());
	}
	
	@Test
	public void testNotAvailability() {
		Driver testDriver = new Driver("Andy", 10, 10, 50, true, null);
		testDriver.changeAvailability();
		assertFalse("Driver is not available", testDriver.getAvailability());
	}

}
