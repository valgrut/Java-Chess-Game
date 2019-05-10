package Figures;

import ChessGame.BoardTile;

/**
 * @author xpeska05
 *
 */
public interface IMovable {
	boolean isMovable = true;
	
	/**
	 * @param position
	 * @return
	 */
	boolean move(BoardTile position); // ChessBoardPosition - treba e3
	
	/**
	 * @return
	 */
	boolean isMovable();
}
