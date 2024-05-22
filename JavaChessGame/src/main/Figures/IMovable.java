package Figures;

import ChessGame.BoardTile;

/**
 * Interface that is implemented by figures thats are able to move.
 * @author xpeska05
 *
 */
public interface IMovable {
	boolean isMovable = true;
	
	/**
	 * This method checks whether piece can be moved from its original position to position given by parameter according to its move rules.
	 * @param position Destination position piece will be moved to.
	 * @return True if concrete figure can move from its position to destination position given by parameter.
	 */
	boolean move(BoardTile position);
	
	/**
	 * Method returns true if piece can be moved.
	 * @return True if piece is movable.
	 */
	boolean isMovable();
}
