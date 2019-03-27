package Figures;

import java.util.Vector;

import ChessGame.BoardTile;
import ChessGame.BoardTile.Direction;

public class Knight extends AbstractPiece implements IMovable {

	public String toString() {
		return this.getNotation();
	}
	
	public Knight() {
		this.setNotation("J");
	}

	@Override
	public Vector<BoardTile> getPossibleMoves() {
		BoardTile currentPosition = getPosition();
		
		Vector<BoardTile> candidates = new Vector<BoardTile>();
		try {
			candidates.add(currentPosition.nextField(Direction.U).nextField(Direction.U).nextField(Direction.L));
			candidates.add(currentPosition.nextField(Direction.U).nextField(Direction.U).nextField(Direction.R));
			candidates.add(currentPosition.nextField(Direction.U).nextField(Direction.L).nextField(Direction.L));
			candidates.add(currentPosition.nextField(Direction.U).nextField(Direction.R).nextField(Direction.R));
			
			candidates.add(currentPosition.nextField(Direction.D).nextField(Direction.D).nextField(Direction.L));
			candidates.add(currentPosition.nextField(Direction.D).nextField(Direction.D).nextField(Direction.R));
			candidates.add(currentPosition.nextField(Direction.D).nextField(Direction.L).nextField(Direction.L));
			candidates.add(currentPosition.nextField(Direction.D).nextField(Direction.R).nextField(Direction.R));
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
	
	public boolean isMovable() {
		return isMovable;
	}

	// nastavit zde null nebo EmptyPlace ???? TODO
	// nemely by mit move pretizeni s moznosti argumentu String ?? (c5, ...)
	

}
