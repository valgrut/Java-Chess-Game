package GameRecord;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;

import Exceptions.EmptyMoveStackException;
import Exceptions.InvalidMoveException;
import GameSaver.NotationType;

/**
 * Class GameRecord holds all moves loaded from notation and created by user. It uses stacks for traversing operations like next move, previous move and for undo and redo operations.
 * This class can be used by various types of games to manage moves. Class provides mechanisms for traversing through the game backward and forward and for undoing and redoing players operations.
 * <p>
 * This mechanisms enables the creating multiple side-branches of played game and returning back to the older ones or reconstruct newly created branches of the game again.
 * 
 * <pre>
 * n - n - n - n - n - n - n
 *       \ 
 *         p - p - p - p - p - p
 *                   \ 
 *                     p - p - p - p - p
 * </pre>
 * 
 * @author xpeska05
 *
 */
public class GameRecord 
{
	/**
	 * Type of notation from which the game was loaded (LONG or SHORT).
	 */
	private NotationType notationType;
	
	/**
	 * Number of move that indicates current state of chess game (position in record).
	 */
	private int currentMoveNumber = 0;
	
	/**
	 * Number of lastly played move.
	 */
	private int lastMoveNumber = 0;
	
	/**
	 * Attribute indicating whether there was detected invalid notation record and game was stopped.
	 */
	private boolean invalidMove = false;
	
	/**
	 * Attribute that holds currently last move performed by player.
	 */
	MoveCommand lastPlayersMove = null;
	
	/**
	 * Array list of MoveCommands that were loaded from notation file.
	 */
	ArrayList<MoveCommand> moves;
	
	/**
	 * Stack that holds moves for undo operation.
	 */
	Stack<MoveCommand> undoMoveStack;
	
	/**
	 * Stack that holds moves for redo operation.
	 */
	Stack<MoveCommand> redoMoveStack;
	
	/**
	 * Stack that holds moves for nextStep operation.
	 */
	Stack<MoveCommand> nextMoveStack;
	
	/**
	 * Stack that holds moves for previousStep operation.
	 */
	Stack<MoveCommand> previousMoveStack;

	/**
	 * Initializes GameRecord manager, that holds all moves loaded from notation and played by player
	 * Also It can be used for any type of game that has some kind of moves (chess, checker).
	 */
	public GameRecord()
	{
		// default notation
		setNotationType(NotationType.LONG);
		
		moves = new ArrayList<MoveCommand>();
		undoMoveStack = new Stack<MoveCommand>();
		redoMoveStack = new Stack<MoveCommand>();
		nextMoveStack = new Stack<MoveCommand>();
		previousMoveStack = new Stack<MoveCommand>();
	}
	
	/**
	 * Returns vector of MoveData retrieved from stack structures in right order of moves from first to last move.
	 * @return Returns vector of MoveData.
	 */
	public Vector<MoveData> getCurrentRecord()
	{
		Vector<MoveData> record = new Vector<MoveData>();
		
		for(MoveCommand command : previousMoveStack)
		{
			record.add(command.getMoveData());
			
			if(command == lastPlayersMove)
				return record;
		}
		
		for(int commandindex = nextMoveStack.size()-1; commandindex >= 0; commandindex--)
		{
			MoveCommand command = nextMoveStack.elementAt(commandindex);
			record.add(command.getMoveData());
			
			if(command == lastPlayersMove) 
				break;
		}
		
		return record;
	}

	/**
	 * Initializes basic game loaded from notation and prepares stack for game traversing.
	 * @param newMove Move objects that contains necessary data about move.
	 */
	public void addMove(MoveCommand newMove)
	{
		moves.add(newMove);
		nextMoveStack.add(0, newMove);
		lastMoveNumber++;
	}
	
	/**
	 * Add players move to the stacks.
	 * @param newMove Players move object.
	 */
	public void addPlayersMove(MoveCommand newMove)
	{
		undoMoveStack.push(newMove);
		nextMoveStack.push(newMove);
		
		lastPlayersMove = newMove;
		
		if(currentMoveNumber == lastMoveNumber)
		{
			lastMoveNumber++;
		}
		else //set new last move player can replay game to.
		{
			lastMoveNumber = newMove.getMoveData().getMoveNumber();
		}
	}
	
	/**
	 * This method returns move located on the top of the NextMoveStack if this stack is not empty.
	 * @return Move object located on the top of the NextMoveStack if this stack is not empty.
	 * @throws Exception EmptyMoveStackException thrown when NextMoveStack is empty.
	 */
	public MoveCommand getNextMove() throws Exception
	{
		if(nextMoveStack.empty())
		{
			throw new EmptyMoveStackException("getNextMove: previousMoveStack is empty.");
		}
		
		if(isInvalidMove())
		{
			throw new InvalidMoveException("getNextMove: Byl nacten invalidni tah a proto nelze pokracovat dal.");
		}
		
		/* Players Move: krok pro prepsani partie od hracova tahu */
		if(lastPlayersMove != null)
		{
			if( ! previousMoveStack.empty() && lastPlayersMove == previousMoveStack.peek()) 
			{
				throw new EmptyMoveStackException("getNextMove: Hracuv tah je posledni tah, kam lze partii prehrat!");
			}
		}
		
		currentMoveNumber++;
		MoveCommand topMove = nextMoveStack.pop();
		previousMoveStack.push(topMove);
		return topMove;
	}
	
	/**
	 * This method returns move located on the top of the previousMoveStack if this stack is not empty.
	 * @return Move object located on the top of the previousMoveStack if this stack is not empty.
	 * @throws Exception EmptyMoveStackException thrown when previousStack is empty.
	 */
	public MoveCommand getPrevMove() throws Exception
	{
		if(previousMoveStack.empty())
		{
			throw new EmptyMoveStackException("getPrevMove: previousMoveStack is empty.");
		}
		
		if(isInvalidMove())
		{
			throw new InvalidMoveException("getPrevMove: Byl nacten invalidni tah a proto nelze pokracovat dal.");
		}
		
		currentMoveNumber--;

		MoveCommand topMove = previousMoveStack.pop();
		nextMoveStack.push(topMove);
		return topMove;
	}

	/**
	 * Method that modifies stacks so that last played players move will be undone.
	 * @throws EmptyMoveStackException Exception thrown when undoStack is empty.
	 */
	public void undoLastPlayersMove() throws EmptyMoveStackException
	{
		if(undoMoveStack.empty())
		{
			throw new EmptyMoveStackException("UndoMoveStack je prazdny, nelze provest Undo.");
		}
		
		nextMoveStack.removeElement(undoMoveStack.peek());
		redoMoveStack.push(undoMoveStack.pop());
		
		// navrat k puvodni notaci (k puvodni nactene hre)
		if(undoMoveStack.empty()) 
		{
			lastMoveNumber = moves.size();
			lastPlayersMove = null;
		}
		else
		{
			lastMoveNumber = undoMoveStack.peek().getMoveData().getMoveNumber();
			lastPlayersMove = undoMoveStack.peek();
		}
	}

	/**
	 * Method that modifies stacks so that lastly undone move will be redoed.
	 * @throws EmptyMoveStackException Exception thrown when redoStack is empty.
	 */
	public void redoLastPlayersMove() throws EmptyMoveStackException
	{
		if(redoMoveStack.empty())
		{
			throw new EmptyMoveStackException("RedoMoveStack je prazdny, nelze provest Redo.");
		}
		
		undoMoveStack.push(redoMoveStack.pop());
		nextMoveStack.push(undoMoveStack.peek());
		lastPlayersMove = undoMoveStack.peek();
		lastMoveNumber = lastPlayersMove.getMoveData().getMoveNumber();
	}
	
	/**
	 * Method returns number of lastly pushed move to redoMoveStack. Method is used when redo operation is performed and move that is 
	 * being redoed is further than current move number. In this case game has to be rewinded to the state before this move.
	 * @return Move Number of lastly pushed move to redoMoveStack.
	 */
	public int getLastRedoMoveNumber()
	{
		if(redoMoveStack.empty())
			return 0;
		
		return redoMoveStack.peek().getMoveData().getMoveNumber();
	}
	
	/**
	 * @return True if move is invalid.
	 */
	public boolean isInvalidMove() {
		return invalidMove;
	}

	/**
	 * Setter.
	 * @param invalidMove Value indicating whether invalid move was detected.
	 */
	public void setInvalidMove(boolean invalidMove) {
		this.invalidMove = invalidMove;
	}
	
	/**
	 * Setter.
	 * @param currentMove Number of current move.
	 */
	public void setCurrentMove(int currentMove) {
		this.currentMoveNumber = currentMove;
	}
	/**
	 * Getter.
	 * @return Current move number.
	 */
	public int getCurrentMoveNumber() {
		return this.currentMoveNumber;
	}
	/**
	 * Getter.
	 * @return Last move number.
	 */
	public int getLastMoveNumber() {
		return lastMoveNumber;
	}

	/**
	 * Setter.
	 * @param lastMoveNumber Number of last move.
	 */
	public void setLastMoveNumber(int lastMoveNumber) {
		this.lastMoveNumber = lastMoveNumber;
	}

	/**
	 * Getter.
	 * @return The notationType.
	 */
	public NotationType getNotationType() {
		return notationType;
	}

	/**
	 * Setter.
	 * @param notationType NotationType to be set.
	 */
	public void setNotationType(NotationType notationType) {
		this.notationType = notationType;
	}
	
	/**
	 * @return True if undoMoveStack is empty.
	 */
	public boolean isUndoStackEmpty()
	{
		return this.undoMoveStack.empty();
	}
	
	/**
	 * @return True if redoMoveStack is empty.
	 */
	public boolean isRedoStackEmpty()
	{
		return this.redoMoveStack.empty();
	}
}
