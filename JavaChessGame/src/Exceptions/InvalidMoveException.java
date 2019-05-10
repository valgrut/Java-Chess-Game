package Exceptions;

/**
 * @author root
 *
 */
public class InvalidMoveException extends Exception {

	private static final long serialVersionUID = -2961462139064943623L;

	/**
	 * @param errorMessage
	 */
	public InvalidMoveException(String errorMessage) {
        super(errorMessage);
    }
}
