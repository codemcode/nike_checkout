package java.com.checkout.exception;

/**
 * Class for custom exception to be used when item to insert already exists in
 * the system
 * 
 * @author vinay
 * 
 */

@SuppressWarnings("serial")
public class ItemAlreadyExistsException extends Exception {

	private String message = null;

	public ItemAlreadyExistsException() {
		super();
	}

	public ItemAlreadyExistsException(String message) {
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
