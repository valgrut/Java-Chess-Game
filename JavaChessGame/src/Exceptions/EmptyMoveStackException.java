package Exceptions;

/**
 * Exception used when some stack of moves is empty so the game can not be stepped further.
 * @author xpeska05
 */
public class EmptyMoveStackException extends Exception 
{ 
	private static final long serialVersionUID = 1345052908729478510L;

	/**
	 * @param errorMessage Message held by exception.
	 */
	public EmptyMoveStackException(String errorMessage) {
        super(errorMessage);
    }
}
