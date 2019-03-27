package Figures;

import java.util.Vector;

import ChessGame.BoardTile;

public class EmptyPlace extends AbstractPiece implements IMovable {
	public String toString() {
		return this.getNotation();
	}
	
	public EmptyPlace() {
		this.setNotation(".");
	}

	@Override
	public boolean move(BoardTile Position) {
		return false;
	}

	@Override
	public
	boolean canMoveTo(BoardTile dst) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public
	Vector<BoardTile> getPossibleMoves() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean isMovable() {
		return false;
	}

}
