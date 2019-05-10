package Figures;

import java.util.Vector;

import ChessGame.BoardTile;

/**
 * @author xpeska05
 *
 */
public class EmptyPlace extends AbstractPiece implements IMovable 
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
	public EmptyPlace() {
		this.setNotation(".");
	}

	/**
	 * @see Figures.AbstractPiece#move(ChessGame.BoardTile)
	 */
	@Override
	public boolean move(BoardTile Position) {
		return false;
	}

	/**
	 * @see Figures.AbstractPiece#canMoveTo(ChessGame.BoardTile)
	 */
	@Override
	public
	boolean canMoveTo(BoardTile dst) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see Figures.AbstractPiece#getPossibleMoves()
	 */
	@Override
	public
	Vector<BoardTile> getPossibleMoves() {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * @see Figures.IMovable#isMovable()
	 */
	@Override
	public boolean isMovable() {
		return false;
	}

}
