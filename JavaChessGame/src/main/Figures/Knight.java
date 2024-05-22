package Figures;

import java.util.Vector;

import ChessGame.BoardTile;
import ChessGame.BoardTile.Direction;

/**
 * @author root
 *
 */
public class Knight extends AbstractPiece implements IMovable {

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.getNotation();
	}
	
	/**
	 * 
	 */
	public Knight() 
	{
		this.setNotation("J");
	}

	/**
	 * @see Figures.AbstractPiece#getPossibleMoves()
	 */
	@Override
	public Vector<BoardTile> getPossibleMoves() 
	{
		BoardTile currentPosition = getPosition();
		
		Vector<BoardTile> candidates = new Vector<BoardTile>();
		try {candidates.add(currentPosition.nextField(Direction.U).nextField(Direction.U).nextField(Direction.L));} catch(java.lang.NullPointerException e) {}
		try {candidates.add(currentPosition.nextField(Direction.U).nextField(Direction.U).nextField(Direction.R));} catch(java.lang.NullPointerException e) {}
		try {candidates.add(currentPosition.nextField(Direction.U).nextField(Direction.L).nextField(Direction.L));} catch(java.lang.NullPointerException e) {}
		try {candidates.add(currentPosition.nextField(Direction.U).nextField(Direction.R).nextField(Direction.R));} catch(java.lang.NullPointerException e) {}
		
		try {candidates.add(currentPosition.nextField(Direction.D).nextField(Direction.D).nextField(Direction.L));} catch(java.lang.NullPointerException e) {}
		try {candidates.add(currentPosition.nextField(Direction.D).nextField(Direction.D).nextField(Direction.R));} catch(java.lang.NullPointerException e) {}
		try {candidates.add(currentPosition.nextField(Direction.D).nextField(Direction.L).nextField(Direction.L));} catch(java.lang.NullPointerException e) {}
		try {candidates.add(currentPosition.nextField(Direction.D).nextField(Direction.R).nextField(Direction.R));} catch(java.lang.NullPointerException e) {}

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
	public boolean isMovable() {
		return isMovable;
	}
}
