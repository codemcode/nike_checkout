package java.com.checkout.schema;

/**
 * This class behaves as a database table to store item details
 * 
 * @author vinay
 * 
 */
public class ItemDetails {

	private String itemName;
	private int price;
	private int purchaseQuantity = 0;
	private int atPriceQuantity = 0;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPurchaseQuantity() {
		return purchaseQuantity;
	}

	public void setPurchaseQuantity(int purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}

	public int getAtPriceQuantity() {
		return atPriceQuantity;
	}

	public void setAtPriceQuantity(int atPriceQuantity) {
		this.atPriceQuantity = atPriceQuantity;
	}

}
