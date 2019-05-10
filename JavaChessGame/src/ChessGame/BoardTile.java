package ChessGame;

import java.util.HashMap;
import java.util.Map;

import Figures.AbstractPiece;
import Figures.EmptyPlace;
import GameRecord.PositionTranslator;

/**
 * @author xpeska05
 *
 */
public class BoardTile {
	private AbstractPiece figure;
	private int arrayRowY;
	private int arrayColX;
	private int chessRowY;
	private int chessColX;
	private Map<Direction, BoardTile> surrounding;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return PositionTranslator.coordsToPosition(getChessColX(), getChessRowY());
	}
	
	/**
	 * @author root
	 *
	 */
	public static enum Direction implements java.io.Serializable, java.lang.Comparable<BoardTile.Direction>
	{
		D, L, LD, LU, R, RD, RU, U
	}
	
	/**
	 * @param colX
	 * @param rowY
	 */
	public BoardTile(int colX, int rowY)
	{
		this.figure = null;
		this.arrayRowY = rowY;
		this.arrayColX = colX;
		
		this.chessRowY = 9-colX; 
		this.chessColX = rowY;
		this.surrounding = new HashMap<Direction, BoardTile>();
	}


	/**
	 * @param ld
	 * @return
	 */
	public BoardTile nextField(Direction ld) {
 		return this.surrounding.get(ld);
	}

	/**
	 * @param dirs
	 * @param field
	 */
	public void addNextField(Direction dirs, BoardTile field) {
		this.surrounding.put(dirs, field);
	}
	
	/**
	 * @return
	 */
	public AbstractPiece getFigure() {
		return figure;
	}

	/**
	 * @param figure
	 */
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
	
	/**
	 * @return
	 */
	public boolean isEmpty()
	{
		if(figure.isMovable()) {
			return false;
		}
		return true;
	}

	/**
	 * @return
	 */
	public int getArrayRowY() {
		return arrayRowY;
	}

	/**
	 * @return
	 */
	public int getArrayColX() {
		return arrayColX;
	}
	
	
	/**
	 * @return
	 */
	public int getChessRowY() {
		return chessRowY;
	}

	/**
	 * @param chessRowY
	 */
	public void setChessRowY(int chessRowY) {
		this.chessRowY = chessRowY;
	}

	/**
	 * @return
	 */
	public int getChessColX() {
		return chessColX;
	}

	/**
	 * @param chessColX
	 */
	public void setChessColX(int chessColX) {
		this.chessColX = chessColX;
	}
}
