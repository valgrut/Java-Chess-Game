package GameRecord;

import java.util.Vector;

/*
 * Inner class that stores move Nodes in vector
 */
class MoveComposite
{
	private MoveCommand valueMove;
	private Vector<MoveComposite> innerMoves; // branches comming from this move
	
	public MoveComposite(MoveCommand newMove)
	{
		this.valueMove = newMove;
		innerMoves = new Vector<MoveComposite>();
	}
	
	public MoveCommand getMove()
	{
		return this.valueMove;
	}
	
	public void setMove(MoveCommand newMove)
	{
		this.valueMove = newMove;
	}
} 
// end of InnerMove class