package application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import junit.framework.TestCase;

public class OrderTest {

	@Test
	public void testName() {
		Order testOrder = new Order(null, 1, 5.0, "Mary", "5:00", 50,50, false);
		assertEquals("The name is the same", "Mary", testOrder.getCustomerName());
	}
	
	@Test
	public void testAddressX() {
		Order testOrder = new Order(null, 1, 5.0, "Mary", "5:00", 50,50, false);
		assertEquals("Address X is correct", 50, testOrder.getAddressX());
	}	
	
	@Test
	public void testAddressY() {
		Order testOrder = new Order(null, 1, 5.0, "Mary", "5:00", 50,50, false);
		assertEquals("Address Y is correct", 50 , testOrder.getAddressY());
	}
	
	@Test
	public void testTime() {
		Order testOrder = new Order(null, 1, 5.0, "Mary", "5:00", 50,50, false);
		assertEquals("The time is correct", "5:00", testOrder.getTime());
	}
	
	@Test
	public void testPrice() {
		Order testOrder = new Order(null, 1, 5.0, "Mary", "5:00", 50,50, false);
		assertEquals("The price is correct", 5.0, 0, testOrder.getPricePerItem());
	}
	
	@Test
	public void testOrderFilled() {
		Order testOrder = new Order(null, 1, 5.0, "Mary", "5:00", 50,50, true);
		assertTrue("The order is filled", testOrder.getDeliveryStatus());
	}
	
	@Test
	public void testNotOrderFilled() {
		Order testOrder = new Order(null, 1, 5.0, "Mary", "5:00", 50,50, false);
		assertFalse("The order is not filled", testOrder.getDeliveryStatus());
	}

}
