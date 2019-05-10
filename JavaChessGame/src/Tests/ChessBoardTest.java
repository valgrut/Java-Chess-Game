package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ChessGame.BoardTile;
import ChessGame.ChessBoard;
import ChessGame.BoardTile.Direction;
import Figures.PieceColor;


/*
ChessBoard board = new ChessBoard();
board.printBoard();

BoardTile g11 = board.getBoardField(5, 1); // a2 (col, row)
System.out.println("Color: " + g11.getFigure().getColor());
System.out.println("Notation: " + g11.getFigure().getNotation());
System.out.println("ColX " + g11.getChessColX() + "    RowY " + g11.getChessRowY());
//System.out.println("Down: " + g11.nextField(Direction.D).getFigure().getNotation());
//System.out.println("Up: " + g11.nextField(Direction.U).getFigure().getNotation());
//System.out.println("Left: " + g11.nextField(Direction.L).getFigure().getNotation());
//System.out.println("Right: " + g11.nextField(Direction.R).getFigure().getNotation());
//System.out.println("Up: " + g11.nextField(Direction.D).getColX() + " " + g11.nextField(Direction.D).getRowY());
//System.out.println("Right: " + g11.nextField(Direction.L).getFigure().getNotation());

System.out.println("");

EmptyPlace em = new EmptyPlace();
em.setNotation("x");

g11.setFigure(em);
board.printBoard();
*/

/* Test moznosti pohybu figurek, dle jejich barvy */
/*
BoardTile knightTile = board.getBoardField("c4");
System.out.println(knightTile.getFigure().getNotation());
knightTile.getFigure().setColor(PieceColor.WHITE);

Vector<BoardTile> candidates = knightTile.getFigure().getPossibleMoves();
for (BoardTile tile : candidates)
{
	System.out.println(tile);
	tile.getFigure().setNotation("x");
}
board.printBoard();
*/

/**
 * @author xpeska05
 *
 */
class ChessBoardTest {
	ChessBoard board = new ChessBoard();

	@Test
	void GetFieldBasicCheckPieceTest() {
		//fail("Not yet implemented");
		assertEquals("V", board.getBoardField(1, 1).getFigure().getNotation(), "Empty line");
		assertEquals(PieceColor.WHITE, board.getBoardField(1, 1).getFigure().getColor(), "Empty line");
		assertEquals(PieceColor.WHITE, board.getBoardField(8, 1).getFigure().getColor(), "Empty line");
		
		assertEquals(PieceColor.BLACK, board.getBoardField(1, 8).getFigure().getColor(), "Empty line");
		
		assertEquals("D", board.getBoardField(4, 1).getFigure().getNotation(), "Empty line");
		assertEquals("K", board.getBoardField(5, 1).getFigure().getNotation(), "Empty line");
	}
	
	@Test
	void GetFieldOutOfRangeNegative() {
		assertEquals(null, board.getBoardField(-1, 3), "-1 Out Of Range");
	}
	
	@Test
	void GetFieldOutOfRangeZero() {
		assertEquals(null, board.getBoardField(0, 0), "0 Out Of Range");
	}
	
	@Test
	void GetFieldOutOfRangeNine() {
		assertEquals(null, board.getBoardField(9, 0), "9 Out Of Range");
	}

	@Test
	void GetFieldInDirection() {
		BoardTile tilePawn = board.getBoardField(2, 2);
		BoardTile tilePawnDown = tilePawn.nextField(Direction.D);
		
		assertEquals("p",  tilePawn.getFigure().getNotation(), "Piece in this position");
		assertEquals("J", tilePawnDown.getFigure().getNotation(), "Piece one step Down.");
	}
	
	@Test
	void GetFieldInDirectionCoords() {
		BoardTile tilePawn = board.getBoardField(2, 2);
		assertEquals(2,  tilePawn.getChessColX(), "Piece in this position");
		assertEquals(2,  tilePawn.getChessRowY(), "Piece in this position");
		
		BoardTile tilePawnDown = tilePawn.nextField(Direction.D);
		assertEquals(2,  tilePawnDown.getChessColX(), "Piece in this position");
		assertEquals(1,  tilePawnDown.getChessRowY(), "Piece in this position");
	}
	
}
