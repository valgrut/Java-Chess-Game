package GameRecord;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;

import Exceptions.EmptyMoveStackException;
import Exceptions.InvalidMoveException;
import GameSaver.NotationType;

/**
 * @author xpeska05
 *
 */
public class GameRecord {
	private NotationType notationType;
	private int currentMoveNumber = 0;
	private int lastMoveNumber = 0;
	
	private boolean invalidMove = false;
	MoveCommand lastPlayersMove = null;
	
	ArrayList<MoveCommand> moves;
	Stack<MoveCommand> undoMoveStack;
	Stack<MoveCommand> redoMoveStack;
	
	Stack<MoveCommand> nextMoveStack;
	Stack<MoveCommand> previousMoveStack;

	/**
	 * Initializes GameRecord manager, that holds all moves loaded from notation and played by player
	 * Also It can be used for any type of game that has some kind of moves (chess, checker).
	 */
	public GameRecord()
	{
		moves = new ArrayList<MoveCommand>();
		undoMoveStack = new Stack<MoveCommand>();
		redoMoveStack = new Stack<MoveCommand>();
		nextMoveStack = new Stack<MoveCommand>();
		previousMoveStack = new Stack<MoveCommand>();
	}
	
	/**
	 * @return Returns vector of MoveData retrieved from stacks in right order.
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
		
		for(int commandindex = nextMoveStack.size()-1; commandindex >=0; commandindex--)
		{
			MoveCommand command = nextMoveStack.elementAt(commandindex);
			record.add(command.getMoveData());
			
			if(command == lastPlayersMove) 
				break;
		}
		
		return record;
	}

	/**
	 * Initializes basic game loaded from notation and prepares stack for game stepping.
	 * @param newMove
	 */
	public void addMove(MoveCommand newMove)
	{
		moves.add(newMove);
		nextMoveStack.add(0, newMove);
		lastMoveNumber++;
	}
	
	/**
	 * Add players move
	 * @param newMove
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
	 * @return
	 * @throws Exception
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
	 * @return
	 * @throws Exception
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
	 * @throws EmptyMoveStackException
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
	 * @throws EmptyMoveStackException
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
	 * @return Move Number of lastly pushed move to redoMovetack
	 */
	public int getLastRedoMoveNumber()
	{
		if(redoMoveStack.empty())
			return 0;
		
		return redoMoveStack.peek().getMoveData().getMoveNumber();
	}
	
	/**
	 * @return True if move is invalid
	 */
	public boolean isInvalidMove() {
		return invalidMove;
	}

	/**
	 * @param invalidMove
	 */
	public void setInvalidMove(boolean invalidMove) {
		this.invalidMove = invalidMove;
	}
	
	/**
	 * @param currentMove
	 */
	public void setCurrentMove(int currentMove) {
		this.currentMoveNumber = currentMove;
	}
	/**
	 * @return
	 */
	public int getCurrentMoveNumber() {
		return this.currentMoveNumber;
	}
	/**
	 * @return
	 */
	public int getLastMoveNumber() {
		return lastMoveNumber;
	}

	/**
	 * @param lastMoveNumber
	 */
	public void setLastMoveNumber(int lastMoveNumber) {
		this.lastMoveNumber = lastMoveNumber;
	}

	/**
	 * @return the notationType
	 */
	public NotationType getNotationType() {
		return notationType;
	}

	/**
	 * @param notationType the notationType to set
	 */
	public void setNotationType(NotationType notationType) {
		this.notationType = notationType;
	}
	
	/**
	 * @return True if undoStack is empty
	 */
	public boolean isUndoStackEmpty()
	{
		return this.undoMoveStack.empty();
	}
	
	/**
	 * @return
	 */
	public boolean isRedoStackEmpty()
	{
		return this.redoMoveStack.empty();
	}
}
