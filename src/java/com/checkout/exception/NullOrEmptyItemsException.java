package java.com.checkout.exception;

/**
 * Class for custom exception to be used when checkout items string is empty or
 * null
 * 
 * @author vinay
 * 
 */

@SuppressWarnings("serial")
public class NullOrEmptyItemsException extends Exception {

	private String message = null;

	public NullOrEmptyItemsException() {
		super();
	}

	public NullOrEmptyItemsException(String message) {
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
