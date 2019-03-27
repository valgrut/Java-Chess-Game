package ChessGame;

import java.util.HashMap;
import java.util.Map;

import Figures.AbstractPiece;
import Figures.EmptyPlace;
import GameRecord.PositionTranslator;

public class BoardTile {
	private AbstractPiece figure;
	private int arrayRowY;
	private int arrayColX;
	private int chessRowY;
	private int chessColX;
	private Map<Direction, BoardTile> surrounding;
	
	public String toString() {
		return PositionTranslator.coordsToPosition(getChessColX(), getChessRowY());
	}
	
	public static enum Direction implements java.io.Serializable, java.lang.Comparable<BoardTile.Direction>
	{
		D, L, LD, LU, R, RD, RU, U
	}
	
	public BoardTile(int colX, int rowY)
	{
		this.figure = null;
		this.arrayRowY = rowY;
		this.arrayColX = colX;
		
		this.chessRowY = 9-colX; 
		this.chessColX = rowY;
		this.surrounding = new HashMap<Direction, BoardTile>();
	}


	public BoardTile nextField(Direction ld) {
 		return this.surrounding.get(ld);
	}

	public void addNextField(Direction dirs, BoardTile field) {
		this.surrounding.put(dirs, field);
	}
	
	public AbstractPiece getFigure() {
		return figure;
	}

	public void setFigure(AbstractPiece figure) {
		this.figure = figure;
	}
	
	/*
	public boolean removeFigure()
	{
		if(isEmpty())
		{
			return false;
		} 
		else
		{
			this.figure = null;
			return true;
		}
	}
	*/
	
	public boolean isEmpty()
	{
		if(figure.isMovable()) {
			return false;
		}
		return true;
	}

	public int getArrayRowY() {
		return arrayRowY;
	}

	public int getArrayColX() {
		return arrayColX;
	}
	
	
	public int getChessRowY() {
		return chessRowY;
	}

	public void setChessRowY(int chessRowY) {
		this.chessRowY = chessRowY;
	}

	public int getChessColX() {
		return chessColX;
	}

	public void setChessColX(int chessColX) {
		this.chessColX = chessColX;
	}
}
