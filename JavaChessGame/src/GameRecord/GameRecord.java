package GameRecord;

import java.util.ArrayList;
import java.util.Stack;

import Exceptions.EmptyMoveStackException;
import Exceptions.InvalidMoveException;
import Figures.PieceColor;

public class GameRecord {
	private int currentMoveNumber = 0;
	private int lastMoveNumber = 0; // Stack?
	
	private boolean invalidMove = false;

	MoveCommand lastPlayersMove = null;
	
	ArrayList<MoveCommand> moves;
	Stack<MoveCommand> undoMoveStack;
	Stack<MoveCommand> redoMoveStack;
	Stack<MoveCommand> nextMoveStack;
	Stack<MoveCommand> previousMoveStack;

	public GameRecord()
	{
		moves = new ArrayList<MoveCommand>();
		undoMoveStack = new Stack<MoveCommand>();
		redoMoveStack = new Stack<MoveCommand>();
		nextMoveStack = new Stack<MoveCommand>();
		previousMoveStack = new Stack<MoveCommand>();
	}
	
	/*
	 * Initializes basic game loaded from notation and prepares stack for game stepping. 
	 */
	public void addMove(MoveCommand newMove)
	{
		moves.add(newMove);
		nextMoveStack.add(0, newMove);
		lastMoveNumber++;
	}
	
	/*
	 * Add players move
	 */
	public void addPlayersMove(MoveCommand newMove)
	{
		if(currentMoveNumber == lastMoveNumber)
		{
			undoMoveStack.push(newMove);
			//previousMoveStack.push(newMove);
			nextMoveStack.push(newMove);
			
			lastPlayersMove = newMove;
			lastMoveNumber++; // pokud vkladame za posledni tah, tak se zvysuje posledni mozny.
		}
		
	}
	
	public MoveCommand getNextMove() throws Exception
	{
		if(nextMoveStack.empty())
		{
			throw new EmptyMoveStackException("previousMoveStack is empty.");
		}
		
		if(isInvalidMove())
		{
			throw new InvalidMoveException("Byl nacten invalidni tah a proto nelze pokracovat dal.");
		}
		
		/* Players Move: krok pro prepsani partie od hracova tahu */
		if(lastPlayersMove != null)
		{
			
			if( ! previousMoveStack.empty() && lastPlayersMove == previousMoveStack.peek())
			//System.out.println(">>>>>Hracuv tah je posledni tah, kam lze partii prehrat!");
			throw new EmptyMoveStackException("Hracuv tah je posledni tah, kam lze partii prehrat!");
			//return null;
		}
		
		
		currentMoveNumber++;
		MoveCommand topMove = nextMoveStack.pop();
		previousMoveStack.push(topMove);
		return topMove;
	}
	
	public MoveCommand getPrevMove() throws Exception
	{
		if(previousMoveStack.empty())
		{
			throw new EmptyMoveStackException("previousMoveStack is empty.");
		}
		
		if(isInvalidMove())
		{
			throw new InvalidMoveException("Byl nacten invalidni tah a proto nelze pokracovat dal.");
		}
		
		currentMoveNumber--;

		MoveCommand topMove = previousMoveStack.pop();
		nextMoveStack.push(topMove);
		return topMove;
	}

	public boolean isInvalidMove() {
		return invalidMove;
	}

	public void setInvalidMove(boolean invalidMove) {
		this.invalidMove = invalidMove;
	}
	public void setCurrentMove(int currentMove) {
		this.currentMoveNumber = currentMove;
	}
	public int getCurrentMoveNumber() {
		return this.currentMoveNumber;
	}
	public int getLastMoveNumber() {
		return lastMoveNumber;
	}

	public void setLastMoveNumber(int lastMoveNumber) {
		this.lastMoveNumber = lastMoveNumber;
	}
}
