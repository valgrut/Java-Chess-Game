package Figures;

import java.util.Arrays;
import java.util.Vector;

import ChessGame.BoardTile;
import ChessGame.BoardTile.Direction;

public class Bishop extends AbstractPiece implements IMovable {
	
	private static Vector<Direction> directions = new Vector<Direction>(Arrays.asList(Direction.RU, Direction.RD, Direction.LU, Direction.LD));
	
	public String toString() {
		return this.getNotation();
	}
	
	public Bishop() {
		this.setNotation("S");
	}

	@Override
	public
	Vector<BoardTile> getPossibleMoves() {
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

	@Override
	public boolean isMovable() {
		return isMovable;
	}
}
