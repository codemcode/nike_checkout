package java.com.checkout.services;

import java.com.checkout.dao.ItemDetailsDao;
import java.com.checkout.dao.ItemDetailsDaoImpl;
import java.com.checkout.exception.ItemDoesNotExistException;
import java.com.checkout.exception.NullOrEmptyItemsException;
import java.com.checkout.schema.ItemDetails;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to implement functionalities like checkout, add item, update item
 * 
 * @author vinay
 * 
 */
public class SuperMarketImpl implements SuperMarket {

	ItemDetailsDao itemDetailsDao = new ItemDetailsDaoImpl();

	/**
	 * This method will provide facility to perform checkout function on items
	 * which will return total price of all items based on discount available on
	 * those items. It will throw an exception if items scanned are invalid
	 * (null, empty, not exists).
	 * 
	 * @param items
	 *            string of items for which we want to calculate total price
	 * @return
	 * @throws NullOrEmptyItemsException
	 * @throws ItemDoesNotExistException
	 */
	@Override
	public int checkout(String items) throws NullOrEmptyItemsException,
			ItemDoesNotExistException {

		if (items == null || items.equals("")) {
			throw new NullOrEmptyItemsException(
					"Items string you entered is empty or null");
		}

		Map<String, Integer> itemsCount = getItemsCount(items);

		int totalPrice = 0;
		for (String itemName : itemsCount.keySet()) {
			int itemQuantity = itemsCount.get(itemName);
			totalPrice = totalPrice
					+ getTotalPriceForItem(itemName, itemQuantity);
		}
		return totalPrice;
	}

	/**
	 * This method will provide facility to add just itemName and prize without
	 * offer details. It will throw an exception if the new item to be added is
	 * already available in the system.
	 * 
	 * @param itemName
	 *            name of item
	 * @param price
	 *            item price
	 * @throws Exception
	 */
	@Override
	public void addItem(String itemName, int price) throws Exception {
		addItem(itemName, price, 0, 0);
	}

	/**
	 * This method will provide facility to add a new item with regular price
	 * and offer. It will throw an exception if the new item to be added is
	 * already available in the system.
	 * 
	 * @param itemName
	 *            item name
	 * @param price
	 *            item price
	 * @param purchaseQuantity
	 *            quantity to purchase at discount rate
	 * @param atPriceQuantity
	 *            quantity to consider to calculate discounted price
	 * @throws Exception
	 */
	@Override
	public void addItem(String itemName, int price, int purchaseQuantity,
			int atPriceQuantity) throws Exception {
		itemDetailsDao.addItem(itemName, price, purchaseQuantity,
				atPriceQuantity);

	}

	/**
	 * This method will provide facility to update an existing item without
	 * affecting previous offer details. It will throw an exception if the item
	 * to be updated is not available in the system.
	 * 
	 * @param itemName
	 *            name of item
	 * @param price
	 *            item price
	 * @throws ItemDoesNotExistException
	 * 
	 */
	@Override
	public void updateItem(String itemName, int price)
			throws ItemDoesNotExistException {
		itemDetailsDao.updateItem(itemName, price);
	}

	/**
	 * This method will provide facility to update price and offer of the
	 * existing item. It will throw an exception if the item to be updated is
	 * not available in the system.
	 * 
	 * @param itemName
	 *            item name
	 * @param price
	 *            item price
	 * @param purchaseQuantity
	 *            quantity to purchase at discount rate
	 * @param atPriceQuantity
	 *            quantity to consider to calculate discounted price
	 * @throws ItemDoesNotExistException
	 */
	@Override
	public void updateItem(String itemName, int price, int purchaseQuantity,
			int atPriceQuantity) throws ItemDoesNotExistException {
		itemDetailsDao.updateItem(itemName, price, purchaseQuantity,
				atPriceQuantity);
	}

	/**
	 * Helper function to count quantity of each item
	 * 
	 * @param items
	 * @return Map with item as key and count of that item as value
	 */
	private Map<String, Integer> getItemsCount(String items) {

		Map<String, Integer> itemCount = new HashMap<String, Integer>();

		String[] itemArray = items.split("(?!^)");

		for (String item : itemArray) {
			if (itemCount.containsKey(item)) {
				int count = itemCount.get(item);
				count++;
				itemCount.put(item, count);
			} else {
				itemCount.put(item, 1);
			}
		}
		return itemCount;
	}

	/**
	 * Helper function to count regular/discounted total price for each type of
	 * item
	 * 
	 * @param itemName
	 * @param itemQuantity
	 * @return total price for each type of item
	 * @throws ItemDoesNotExistException
	 */
	private int getTotalPriceForItem(String itemName, int itemQuantity)
			throws ItemDoesNotExistException {
		int itemTotal = 0;
		ItemDetails item = itemDetailsDao.findItem(itemName);
		if (item == null) {
			throw new ItemDoesNotExistException("Item: " + itemName
					+ " does not exist in the system");
		}
		if (item.getPurchaseQuantity() > 0 && item.getAtPriceQuantity() > 0
				&& (item.getPurchaseQuantity() >= item.getAtPriceQuantity())) {
			int discountPrice = (itemQuantity / item.getPurchaseQuantity())
					* (item.getAtPriceQuantity() * item.getPrice());
			int fullPrice = (itemQuantity % item.getPurchaseQuantity())
					* item.getPrice();
			itemTotal = discountPrice + fullPrice;
		} else {
			itemTotal = itemQuantity * item.getPrice();
		}
		return itemTotal;
	}

}
