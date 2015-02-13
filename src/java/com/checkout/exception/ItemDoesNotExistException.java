package java.com.checkout.exception;

/**
 * Class for custom exception to be used when item to update does not exist in
 * the system
 * 
 * @author vinay
 * 
 */

@SuppressWarnings("serial")
public class ItemDoesNotExistException extends Exception {

	private String message = null;

	public ItemDoesNotExistException() {
		super();
	}

	public ItemDoesNotExistException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
