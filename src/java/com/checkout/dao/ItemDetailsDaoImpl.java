package java.com.checkout.dao;

import java.com.checkout.exception.ItemAlreadyExistsException;
import java.com.checkout.exception.ItemDoesNotExistException;
import java.com.checkout.schema.ItemDetails;
import java.util.ArrayList;
import java.util.List;

/**
 * This class will implement all methods in ItemDetailsDao to provide read/write
 * data from/to ItemDetails
 * 
 * @author vinay
 */
public class ItemDetailsDaoImpl implements ItemDetailsDao {

	// List is working as a database
	private List<ItemDetails> itemList;

	/**
	 * Constructor to set all items initially
	 */
	public ItemDetailsDaoImpl() {
		itemList = new ArrayList<ItemDetails>();

		ItemDetails item1 = new ItemDetails();
		item1.setItemName("A");
		item1.setPrice(20);
		itemList.add(item1);

		ItemDetails item2 = new ItemDetails();
		item2.setItemName("B");
		item2.setPrice(50);
		item2.setPurchaseQuantity(5);
		item2.setAtPriceQuantity(3);
		itemList.add(item2);

		ItemDetails item3 = new ItemDetails();
		item3.setItemName("C");
		item3.setPrice(30);
		itemList.add(item3);

	}

	/**
	 * This method provides facility to service layer to search for all items in
	 * the system and will return a list containing all of them
	 * 
	 * @return List of items
	 */
	@Override
	public List<ItemDetails> searchAllItems() {
		return itemList;
	}

	/**
	 * This method provides facility to service layer to search for an item
	 * based on item name provided as an input
	 * 
	 * @param itemName
	 *            Name of item we are looking for in the system
	 * @return ItemDetails object containing all information about the item
	 */
	@Override
	public ItemDetails findItem(String itemName) {
		for (ItemDetails item : itemList) {
			if (item.getItemName().equals(itemName)) {
				return item;
			}
		}
		return null;
	}

	/**
	 * This method provides facility to service layer to to add a new item in
	 * the system and throws exception if item already exists
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
	@Override
	public void addItem(String itemName, int price, int purchaseQuantity,
			int atPriceQuantity) throws Exception {
		if (findItem(itemName) != null) {
			throw new ItemAlreadyExistsException("Item: " + itemName
					+ " already exists in the system");
		}

		ItemDetails item = new ItemDetails();
		item.setItemName(itemName);
		item.setPrice(price);
		item.setPurchaseQuantity(purchaseQuantity);
		item.setAtPriceQuantity(atPriceQuantity);
		itemList.add(item);
	}

	/**
	 * This method provides facility to service layer to update only price of an
	 * existing item in the system and it will throw an exception if item to
	 * update does not exist
	 * 
	 * @param itemName
	 * @param price
	 * @throws ItemDoesNotExistException
	 */
	@Override
	public void updateItem(String itemName, int price)
			throws ItemDoesNotExistException {
		ItemDetails item = findItem(itemName);
		if (item == null) {
			throw new ItemDoesNotExistException("Item: " + itemName
					+ " does not exist in the system");
		} else {
			item.setItemName(itemName);
			item.setPrice(price);
		}

	}

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
	@Override
	public void updateItem(String itemName, int price, int purchaseQuantity,
			int atPriceQuantity) throws ItemDoesNotExistException {
		ItemDetails item = findItem(itemName);
		if (item == null) {
			throw new ItemDoesNotExistException("Item: " + itemName
					+ " does not exist in the system");
		} else {
			item.setItemName(itemName);
			item.setPrice(price);
			item.setPurchaseQuantity(purchaseQuantity);
			item.setAtPriceQuantity(atPriceQuantity);
		}

	}

}
