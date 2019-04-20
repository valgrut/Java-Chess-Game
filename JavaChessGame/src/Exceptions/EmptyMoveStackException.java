package Exceptions;

public class EmptyMoveStackException extends Exception 
{ 
	private static final long serialVersionUID = 1345052908729478510L;

	public EmptyMoveStackException(String errorMessage) {
        super(errorMessage);
    }
}
