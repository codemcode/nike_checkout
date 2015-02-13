package javatests.com.checkout.services;

import static org.junit.Assert.assertEquals;

import java.com.checkout.dao.ItemDetailsDao;
import java.com.checkout.dao.ItemDetailsDaoImpl;
import java.com.checkout.exception.ItemAlreadyExistsException;
import java.com.checkout.exception.ItemDoesNotExistException;
import java.com.checkout.exception.NullOrEmptyItemsException;
import java.com.checkout.services.SuperMarket;
import java.com.checkout.services.SuperMarketImpl;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Test class for SuperMarketImpl
 * 
 * @author vinay
 * 
 */
@RunWith(JUnit4.class)
public class SuperMarketImplTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	ItemDetailsDao itemDetailsDao;
	SuperMarket superMarket;

	@Before
	public void setUp() {
		itemDetailsDao = new ItemDetailsDaoImpl();
		superMarket = new SuperMarketImpl();
	}

	@Test
	public void testCheckoutWithNullString() throws NullOrEmptyItemsException,
			ItemDoesNotExistException {
		exception.expect(NullOrEmptyItemsException.class);
		exception.expectMessage("Items string you entered is empty or null");
		superMarket.checkout(null);
	}

	@Test
	public void testCheckoutWithEmptyString() throws NullOrEmptyItemsException,
			ItemDoesNotExistException {
		exception.expect(NullOrEmptyItemsException.class);
		exception.expectMessage("Items string you entered is empty or null");
		superMarket.checkout("");
	}

	@Test
	public void testCheckoutItemNotAvailable()
			throws NullOrEmptyItemsException, ItemDoesNotExistException {
		exception.expect(ItemDoesNotExistException.class);
		superMarket.checkout("ABCY");
	}

	@Test
	public void testCheckoutWithoutDiscount() throws NullOrEmptyItemsException,
			ItemDoesNotExistException {
		assertEquals(200, superMarket.checkout("ABCABC"));

	}

	@Test
	public void testCheckoutWithDiscount() throws NullOrEmptyItemsException,
			ItemDoesNotExistException {
		assertEquals(390, superMarket.checkout("AABBBCCCCCBBB"));
	}

	@Test
	public void testAddItemWithExistingItemName() throws Exception {
		exception.expect(ItemAlreadyExistsException.class);
		exception.expectMessage("Item: D already exists in the system");
		superMarket.addItem("D", 15, 0, 0);
		superMarket.addItem("D", 45, 4, 2);
	}

	@Test
	public void testAddItemWithoutDiscount() throws Exception {
		superMarket.addItem("S", 1000);
		assertEquals(5000, superMarket.checkout("SSSSS"));
		superMarket.addItem("H", 11, 0, 0);
		assertEquals(55, superMarket.checkout("HHHHH"));
	}

	@Test
	public void testAddItemWithDiscount() throws Exception {
		// same result with/without discount
		superMarket.addItem("N", 200, 1, 1);
		superMarket.addItem("I", 100, 3, 1);
		// wrong discount (5 at price of 10) so it should not be applied
		superMarket.addItem("K", 500, 5, 10);
		// wrong discount (2 at price of 0) so it should not be applied
		superMarket.addItem("E", 300, 2, 0);
		assertEquals(400, superMarket.checkout("ABCIN"));
		assertEquals(200, superMarket.checkout("IIII"));
		// will calculate regular price only as discount is wrong
		assertEquals(2500, superMarket.checkout("KKKKK"));
		// will calculate regular price only as discount is wrong
		assertEquals(600, superMarket.checkout("EE"));
	}

	@Test
	public void testUpdateItemWithDiscount() throws Exception {
		superMarket.addItem("X", 80, 0, 0);
		superMarket.updateItem("X", 40, 4, 2);
		assertEquals(80, superMarket.checkout("XXXX"));
	}

	@Test
	public void testUpdateItemPriceOnly() throws Exception {
		superMarket.addItem("Y", 60, 0, 0);
		superMarket.updateItem("Y", 30);
		assertEquals(120, superMarket.checkout("YYYY"));
	}

	@Test
	public void testUpdateItemWhichDoesNotExist() throws Exception {
		exception.expect(ItemDoesNotExistException.class);
		exception.expectMessage("Item: ABC does not exist in the system");
		superMarket.updateItem("ABC", 100, 0, 0);
	}

}
