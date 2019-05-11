package Loader;

/**
 * Exception thrown when Notation validator detects invalid move in notation file.
 * @author xpeska05
 */
public class InvalidNotationException extends Exception {

	private static final long serialVersionUID = 4162095269966841203L;

	/**
	 * @param errorMessage Message held by exception.
	 */
	public InvalidNotationException(String errorMessage) {
        super(errorMessage);
    }
}
