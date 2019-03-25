package ChessGame;

import java.util.Vector;

public class GameRecord {
	private int currentMove = -1;
	
	class GameNode
	{
		private Move valueMove;
		private Vector<GameNode> innerMoves; // branches comming from this move
		
		public GameNode(Move newMove)
		{
			this.valueMove = newMove;
			innerMoves = new Vector<GameNode>();
		}
		
		public Move getMove()
		{
			return this.valueMove;
		}
		
		public void setMove(Move newMove)
		{
			this.valueMove = newMove;
		}
	} // end of InnerMove class
	
	Vector<GameNode> moves;
	GameNode activeNode;
	
	public GameRecord()
	{
		moves = new Vector<GameNode>();
	}
	
	public void addMove(Move newMove)
	{
		this.moves.addElement(new GameNode(newMove));
	}
	
	public Move getNextMove()
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
	
	public Move getPrevMove()
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
	
	public Move getCurrentMove()
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
	
	public Move getMoveWithNumber(int moveNumber, ChessColor color)
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
