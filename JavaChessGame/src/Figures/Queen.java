package Figures;

import java.util.Arrays;
import java.util.Vector;

import ChessGame.BoardTile;
import ChessGame.BoardTile.Direction;

/**
 * @author root
 *
 */
public class Queen extends AbstractPiece implements IMovable 
{
	
	private static Vector<Direction> directions = new Vector<Direction>(Arrays.asList(Direction.RU, Direction.RD, Direction.LU, Direction.LD, Direction.U, Direction.R, Direction.D, Direction.L));
	
	/**
	 * 
	 */
	public Queen()
	{
		// TODO vlozit pozici figurky, vytvorit atribut
		this.setNotation("D");
	}
	/**
	 * @param position
	 */
	public Queen(String position)
	{
		// TODO vlozit pozici figurky, vytvorit atribut
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.getNotation();
	}

	/**
	 * @see Figures.AbstractPiece#getPossibleMoves()
	 */
	@Override
	public Vector<BoardTile> getPossibleMoves() {
		BoardTile currentPosition = getPosition();
		
		Vector<BoardTile> candidates = new Vector<BoardTile>();
		
		for(Direction dir : this.directions)
		{
			try {
				BoardTile next = currentPosition;
				while(next != null)
				{
					next = next.nextField(dir);
					// if notEmpty
					// 		if Next.color neni moje barva, tak ADD && break
					// 		if next.color je moje barva, break
					// Add  - mozna pridame rovnou do candidates !!!!!
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
		// TODO Auto-generated method stub
		return isMovable;
	}

}
