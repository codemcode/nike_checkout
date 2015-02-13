package javatests.com.checkout.dao;

import java.com.checkout.dao.ItemDetailsDao;
import java.com.checkout.dao.ItemDetailsDaoImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Test class for ItemDetailsDaoImpl
 * 
 * @author vinay
 * 
 */
@RunWith(JUnit4.class)
public class ItemDetailsDaoImplTest {

	ItemDetailsDao itemDetailsDao;

	@Before
	public void setUp() throws Exception {
		itemDetailsDao = new ItemDetailsDaoImpl();
	}

	@Test
	public final void testSearchAllItems() {
		Assert.assertEquals(3, itemDetailsDao.searchAllItems().size());
	}

	@Test
	public final void testFindItem() {
		Assert.assertEquals(20, itemDetailsDao.findItem("A").getPrice());
		Assert.assertEquals(0, itemDetailsDao.findItem("A")
				.getPurchaseQuantity());
		Assert.assertEquals(0, itemDetailsDao.findItem("A")
				.getAtPriceQuantity());
		Assert.assertEquals(50, itemDetailsDao.findItem("B").getPrice());
		Assert.assertEquals(5, itemDetailsDao.findItem("B")
				.getPurchaseQuantity());
		Assert.assertEquals(3, itemDetailsDao.findItem("B")
				.getAtPriceQuantity());
	}
}
