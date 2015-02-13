package java.com.checkout.dao;

import java.com.checkout.exception.ItemDoesNotExistException;
import java.com.checkout.schema.ItemDetails;
import java.util.List;

/**
 * This interface provides data access layer to perform operations on
 * ItemDetails table/object
 * 
 * @author vinay
 */
public interface ItemDetailsDao {

	/**
	 * This method will provide facility to service layer to search for all
	 * items in the system and will return a list containing all of them
	 * 
	 * @return List of items
	 */
	public List<ItemDetails> searchAllItems();

	/**
	 * This method will provide facility to service layer to search for an item
	 * based on item name provided as an input
	 * 
	 * @param itemName
	 *            Name of item we are looking for in the system
	 * @return ItemDetails object containing all information about the item
	 */
	public ItemDetails findItem(String itemName);

	/**
	 * This method provides facility to service layer to add a new item in the
	 * system and throws exception if item already exists
	 * 
	 * @param itemName
	 *            name of the item
	 * @param price
	 *            regular price of the item
	 * @param purchaseQuantity
	 *            quantity that can be purchased with discount
	 * @param atPriceQuantity
	 *            quantity which will be used to calculate discounted price
	 * @throws Exception
	 */
	public void addItem(String itemName, int price, int purchaseQuantity,
			int atPriceQuantity) throws Exception;

	/**
	 * This method provides facility to service layer to update only price of an
	 * existing item in the system and it will throw an exception if item to
	 * update does not exist
	 * 
	 * @param itemName
	 * @param price
	 * @throws ItemDoesNotExistException
	 */
	public void updateItem(String itemName, int price)
			throws ItemDoesNotExistException;

	/**
	 * This method provides facility to service layer to update price and
	 * discount quantity of an existing item in the system and it will throw an
	 * exception if item to update does not exist
	 * 
	 * @param itemName
	 * @param price
	 * @param purchaseQuantity
	 * @param atPriceQuantity
	 * @throws ItemDoesNotExistException
	 */
	public void updateItem(String itemName, int price, int purchaseQuantity,
			int atPriceQuantity) throws ItemDoesNotExistException;

}
