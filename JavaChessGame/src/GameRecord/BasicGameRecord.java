package GameRecord;

import java.util.Vector;

import Figures.PieceColor;

public class BasicGameRecord {
	private int currentMove = -1;
	
	class GameNode
	{
		private MoveData valueMove;
		private Vector<GameNode> innerMoves; // branches comming from this move
		
		public GameNode(MoveData newMove)
		{
			this.valueMove = newMove;
			innerMoves = new Vector<GameNode>();
		}
		
		public MoveData getMove()
		{
			return this.valueMove;
		}
		
		public void setMove(MoveData newMove)
		{
			this.valueMove = newMove;
		}
	} // end of InnerMove class
	
	Vector<GameNode> moves;
	GameNode activeNode;
	
	public BasicGameRecord()
	{
		moves = new Vector<GameNode>();
	}
	
	public void addMove(MoveData newMove)
	{
		this.moves.addElement(new GameNode(newMove));
	}
	
	public MoveData getNextMove()
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
	
	public MoveData getPrevMove()
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
	
	public MoveData getCurrentMove()
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
	
	public MoveData getMoveWithNumber(int moveNumber, PieceColor color)
	{
		if(! this.moves.isEmpty() && moveNumber < this.moves.size() && moveNumber >= 0)
		{
			return moves.elementAt(moveNumber).getMove();
		}
		else
		{
			return null;
		}
	}

	
}
