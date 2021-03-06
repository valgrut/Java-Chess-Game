package Figures;

import java.util.Vector;

import ChessGame.BoardTile;
import ChessGame.BoardTile.Direction;

/**
 * @author xpeska05
 *
 */
public class King extends AbstractPiece implements IMovable 
{
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.getNotation();
	}
	
	/**
	 * 
	 */
	public King() {
		this.setNotation("K");
	}

	/**
	 * @see Figures.AbstractPiece#getPossibleMoves()
	 */
	@Override
	public Vector<BoardTile> getPossibleMoves() {
		BoardTile currentPosition = getPosition();
		
		Vector<BoardTile> candidates = new Vector<BoardTile>();
		try 
		{
			candidates.add(currentPosition.nextField(Direction.U));
			candidates.add(currentPosition.nextField(Direction.LU));
			candidates.add(currentPosition.nextField(Direction.L));
			candidates.add(currentPosition.nextField(Direction.LD));
			candidates.add(currentPosition.nextField(Direction.D));
			candidates.add(currentPosition.nextField(Direction.RD));
			candidates.add(currentPosition.nextField(Direction.R));
			candidates.add(currentPosition.nextField(Direction.RU));
		}
		catch(java.lang.NullPointerException e)
		{
			//none
		}

		Vector<BoardTile> possibleMoves = new Vector<BoardTile>();
		for(BoardTile tile : candidates)
		{
			if(tile == null)
			{
				continue;
			}
			
			if(tile.getFigure().getColor() == getColor())
			{
				continue;
			}
			
			possibleMoves.add(tile);
		}
		return possibleMoves;
	}

	/**
	 * @see Figures.IMovable#isMovable()
	 */
	@Override
	public boolean isMovable() {
		// TODO Auto-generated method stub
		return isMovable;
	}
}
