package GameRecord;

import java.util.Vector;

import Figures.PieceColor;
import GameRecord.MoveComposite;

public class GameRecord {
	private int currentMove = -1;
	Vector<MoveComposite> moves;
	MoveComposite activeNode;

	public GameRecord()
	{
		moves = new Vector<MoveComposite>();
	}
	
	public void addMove(MoveComposite newMove)
	{
		moves.addElement(newMove);
	}
	
	public MoveCommand getNextMove()
	{
		if(currentMove+1 < moves.size())
		{
			currentMove += 1;
			return moves.elementAt(currentMove).getMove();
		} 
		else 
		{
			return null;
		}
	}
	
	public MoveCommand getPrevMove()
	{
		if(currentMove-1 >= 0)
		{
			currentMove -= 1;
			return moves.elementAt(currentMove).getMove();
		} 
		else 
		{
			return null;
		}
	}
	
	public MoveCommand getCurrentMove()
	{
		if(this.moves.isEmpty() || this.currentMove == -1)
		{
			System.out.println("Index je mimo range." + this.currentMove);
			return null;
		}
		else
		{
			return moves.elementAt(currentMove).getMove();
		}
	}
	
	public MoveCommand getMoveWithNumber(int moveNumber, PieceColor color)
	{
		if(! this.moves.isEmpty() && moveNumber < this.moves.size() && moveNumber >= 0)
		{
			return moves.elementAt(moveNumber).getMove();
		}
		
		return null;
	}

	
}
