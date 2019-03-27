package Figures;

import ChessGame.BoardTile;

public interface IMovable {
	boolean isMovable = true;
	boolean move(BoardTile position); // ChessBoardPosition - treba e3
	boolean isMovable();
}
