package GameRecord;

import java.util.ArrayList;
import java.util.Stack;

import Figures.PieceColor;

public class GameRecord {
	private int currentMove = -1;
	
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
	}
	
	public MoveCommand getNextMove() throws Exception
	{
		if(nextMoveStack.empty())
		{
			throw new Exception("previousMoveStack is empty.");
		}
		
		MoveCommand topMove = nextMoveStack.pop();
		previousMoveStack.push(topMove);
		return topMove;
	}
	
	public MoveCommand getPrevMove() throws Exception
	{
		if(previousMoveStack.empty())
		{
			throw new Exception("previousMoveStack is empty.");
		}
		
		MoveCommand topMove = previousMoveStack.pop();
		nextMoveStack.push(topMove);
		return topMove;
	}
	
	public MoveCommand getCurrentMove()
	{
		/*
		if(this.moves.isEmpty() || this.currentMove == -1)
		{
			System.out.println("Index je mimo range." + this.currentMove);
			return null;
		}
		else
		{
			return moves.elementAt(currentMove).getMove();
		}
		*/
		return null;

	}
	
	public MoveCommand getMoveWithNumber(int moveNumber, PieceColor color)
	{
		/*
		if(! this.moves.isEmpty() && moveNumber < this.moves.size() && moveNumber >= 0)
		{
			return moves.elementAt(moveNumber).getMove();
		}
		*/
		return null;
	}

	
}
