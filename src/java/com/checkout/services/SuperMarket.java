package java.com.checkout.services;

import java.com.checkout.exception.ItemDoesNotExistException;
import java.com.checkout.exception.NullOrEmptyItemsException;

/**
 * Interface to provide functionalities like checkout, add item, update item
 * 
 * @author vinay
 * 
 */
public interface SuperMarket {

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
	public int checkout(String items) throws NullOrEmptyItemsException,
			ItemDoesNotExistException;

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
	public void addItem(String itemName, int price, int purchaseQuantity,
			int atPriceQuantity) throws Exception;

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
	public void addItem(String itemName, int price) throws Exception;

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
	public void updateItem(String itemName, int price)
			throws ItemDoesNotExistException;

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
	public void updateItem(String itemName, int price, int purchaseQuantity,
			int atPriceQuantity) throws ItemDoesNotExistException;

}
