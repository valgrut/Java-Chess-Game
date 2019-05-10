package Exceptions;

/**
 * @author xpeska05
 *
 */
public class EmptyMoveStackException extends Exception 
{ 
	private static final long serialVersionUID = 1345052908729478510L;

	/**
	 * @param errorMessage
	 */
	public EmptyMoveStackException(String errorMessage) {
        super(errorMessage);
    }
}
