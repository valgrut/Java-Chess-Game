package GameRecord;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;

import Figures.PieceColor;
import GameRecord.MoveComposite;

public class GameRecord {
	private int currentMove = -1;
	//Vector<MoveComposite> moves;
	//MoveComposite activeNode;
	/*
	 * Composite asi pujde zase do pryc a zustane zase jen MoveCommand
	 */
	
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
	
	public void addMove(MoveCommand newMove)
	{
		moves.add(newMove);
		nextMoveStack.add(newMove);
	}
	
	public MoveCommand getNextMove()
	{
		/*
		if(currentMove+1 < moves.size())
		{
			currentMove += 1;
			return moves.elementAt(currentMove).getMove();
		} 
		else 
		{
			return null;
		}
		*/
		
		MoveCommand topMove = nextMoveStack.pop();
		previousMoveStack.push(topMove);
		return topMove;
	}
	
	public MoveCommand getPrevMove()
	{
		/*
		if(currentMove-1 >= 0)
		{
			currentMove -= 1;
			return moves.elementAt(currentMove).getMove();
		} 
		else 
		{
			return null;
		}
		*/
		
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
