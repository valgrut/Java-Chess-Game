package Figures;

import java.util.Vector;

import ChessGame.BoardTile;
import ChessGame.BoardTile.Direction;

/**
 * @author root
 *
 */
public class Pawn extends AbstractPiece implements IMovable 
{
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return this.getNotation();
	}
	
	/**
	 * 
	 */
	public Pawn() 
	{
		this.setNotation("p");
	}

	/**
	 * @see Figures.AbstractPiece#getPossibleMoves()
	 */
	@Override
	public Vector<BoardTile> getPossibleMoves() 
	{
		BoardTile currentPosition = getPosition();
		
		Vector<BoardTile> candidates = new Vector<BoardTile>();
		
		if(getColor() == PieceColor.BLACK)
		{
			try 
			{
				BoardTile blackD = currentPosition.nextField(Direction.D);
				BoardTile blackDD = blackD.nextField(Direction.D);
				
				if(blackD.isEmpty())
				{
					candidates.add(blackD);
				}
				if(currentPosition.getChessRowY() == 7 && blackD.isEmpty() && blackDD.isEmpty())
				{
					candidates.add(blackDD);
				}
			} 
			catch(java.lang.NullPointerException e) {};
			
			try 
			{
				BoardTile blackLD = currentPosition.nextField(Direction.LD);
				if( ! blackLD.isEmpty() && blackLD.getFigure().getColor() != getColor())
				{
					candidates.add(blackLD);
				}
			} 
			catch(java.lang.NullPointerException e) {};
			
			try 
			{
				BoardTile blackRD = currentPosition.nextField(Direction.RD);
				if( ! blackRD.isEmpty() && blackRD.getFigure().getColor() != getColor()) 
				{
					candidates.add(blackRD);
				}
			} 
			catch(java.lang.NullPointerException e) {};
		}
		
		if(getColor() == PieceColor.WHITE)
		{
			try 
			{
				BoardTile blackU = currentPosition.nextField(Direction.U);
				BoardTile blackUU = blackU.nextField(Direction.U);
				if(blackU.isEmpty())
				{
					candidates.add(blackU);
				}
				if(currentPosition.getChessRowY() == 2 && blackU.isEmpty() && blackUU.isEmpty())
				{
					candidates.add(blackUU);
				}
			} 
			catch(java.lang.NullPointerException e) {};
			
			try 
			{
				BoardTile blackLU = currentPosition.nextField(Direction.LU);
				if( ! blackLU.isEmpty() && blackLU.getFigure().getColor() != getColor())
				{
					candidates.add(blackLU);
				}
			} 
			catch(java.lang.NullPointerException e) {};
			
			try 
			{
				BoardTile blackRU = currentPosition.nextField(Direction.RU);
				if( ! blackRU.isEmpty() && blackRU.getFigure().getColor() != getColor()) 
				{
					candidates.add(blackRU);
				}
			} 
			catch(java.lang.NullPointerException e) {};
		}
	
		return candidates;
	}
	
	/**
	 * @see Figures.IMovable#isMovable()
	 */
	public boolean isMovable() 
	{
		return isMovable;
	}

}
