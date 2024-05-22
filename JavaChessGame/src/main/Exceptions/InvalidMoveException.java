package Exceptions;

/**
 * Exception used when there is detected invalid move in notation or after user move.
 * @author xpeska05
 */
public class InvalidMoveException extends Exception {

	private static final long serialVersionUID = -2961462139064943623L;

	/**
	 * @param errorMessage Message held by exception.
	 */
	public InvalidMoveException(String errorMessage) {
        super(errorMessage);
    }
}
