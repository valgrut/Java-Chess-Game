package Figures;

import java.util.Arrays;
import java.util.Vector;

import ChessGame.BoardTile;
import ChessGame.BoardTile.Direction;

/**
 * @author root
 *
 */
public class Rook extends AbstractPiece implements IMovable {
	private static Vector<Direction> directions = new Vector<Direction>(Arrays.asList(Direction.U, Direction.R, Direction.D, Direction.L));
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.getNotation();
	}
	
	/**
	 * 
	 */
	public Rook()
	{
		this.setNotation("V");
	}

	/**
	 * @see Figures.AbstractPiece#getPossibleMoves()
	 */
	@Override
	public Vector<BoardTile> getPossibleMoves() 
	{
		BoardTile currentPosition = getPosition();
		
		Vector<BoardTile> candidates = new Vector<BoardTile>();
		
		for(Direction dir : this.directions)
		{
			try {
				BoardTile next = currentPosition;
				while(next != null)
				{
					next = next.nextField(dir);

					if(next.isEmpty())
					{
						candidates.add(next);
						continue;
					}
					
					if(next.getFigure().getColor() == getColor())
					{
						break;
					}
					
					if(next.getFigure().getColor() != getColor())
					{
						candidates.add(next);
						break;
					}
					
				}
			}
			catch(java.lang.NullPointerException e)
			{
				//none
			}
		}
		
		return candidates;
	}
	
	/**
	 * @see Figures.IMovable#isMovable()
	 */
	public boolean isMovable() {
		return isMovable;
	}
}
