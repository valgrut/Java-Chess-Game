package ChessGame;

import java.util.HashMap;
import java.util.Map;

import Figures.AbstractPiece;
import Figures.EmptyPlace;
import GameRecord.PositionTranslator;

/**
 * This class represents one concrete Tile in 2D array that represents chessboard.
 * Class contains reference to all surrounding tiles located in all 8 directions.
 * @author xpeska05
 *
 */
public class BoardTile 
{
	/**
	 * Attribute holds object of figure that is placed at this particular tile.
	 */
	private AbstractPiece figure;
	
	/**
	 * Row coordination in 2D array of tiles.
	 * This one is counted from left top position.
	 */
	private int arrayRowY;
	
	/**
	 * Column coordination in 2D array of tiles.
	 * This one is counted from left top position.
	 */
	private int arrayColX;
	
	/**
	 * Row coordination in 2D array of tiles but according to chess.
	 * This one is counted from left down position.
	 */
	private int chessRowY;
	
	/**
	 * Column coordination in 2D array of tiles but according to chess.
	 * This one is counted from left down position.
	 */
	private int chessColX;
	
	/**
	 * Map Structure that holds mapping of Direction and reference to Tile object in that direction.
	 * Represents surrounding of this tile object.
	 */
	private Map<Direction, BoardTile> surrounding;
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return PositionTranslator.coordsToPosition(getChessColX(), getChessRowY());
	}
	
	/**
	 * Enumerate representing directions in which surrounding tiles are located.
	 * @author xpeska05
	 */
	public static enum Direction implements java.io.Serializable, java.lang.Comparable<BoardTile.Direction>
	{
		D, L, LD, LU, R, RD, RU, U
	}
	
	/**
	 * Constructor of BoardTile located at column col and row row coordinates. Initializes
	 * basic attributes and instantiates HashMap representing surrounding tiles of this tile.
	 * @param colX Column position of this tile.
	 * @param rowY Row position of this tile.
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
	 * Method used for getting Tile in given direction.
	 * @param ld Direction in which Tile will be retrieved.
	 * @return BoardTile BoardTile object in direction 'ld'.
	 */
	public BoardTile nextField(Direction ld) {
 		return this.surrounding.get(ld);
	}

	/**
	 * Method used for initializing the surrounding of this tile.
	 * It maps given direction to tile that is located in direction dirs.
	 * @param dirs Direction in which given tile is located.
	 * @param field BoardTile which is located in direction from current tile.
	 */
	public void addNextField(Direction dirs, BoardTile field) {
		this.surrounding.put(dirs, field);
	}
	
	/**
	 * Method returns piece object that is in this tile.
	 * @return AbstractPiece Piece from this tile.
	 */
	public AbstractPiece getFigure() {
		return figure;
	}

	/**
	 * Sets figure object to this tile.
	 * @param figure Figure to be placed to this tile.
	 */
	public void setFigure(AbstractPiece figure) {
		this.figure = figure;
	}
	
	/**
	 * Method returns true when there is no figure placed to this tile.
	 * @return boolean True if there is no figure at this tile.
	 */
	public boolean isEmpty()
	{
		if(figure.isMovable()) {
			return false;
		}
		return true;
	}

	/**
	 * Getter for ArrayRowY attribute.
	 * @return int Returns ArrayRowY attribute.
	 */
	public int getArrayRowY() {
		return arrayRowY;
	}

	/**
	 * Getter for ArrayColX attribute.
	 * @return int Returns ArrayColX.
	 */
	public int getArrayColX() {
		return arrayColX;
	}
	
	
	/**
	 * Getter for ChessRowY attribute.
	 * @return int Returns ChessRowY.
	 */
	public int getChessRowY() {
		return chessRowY;
	}

	/**
	 * Setter for ChessRowY attribute.
	 * @param chessRowY New value to be assigned to attribute.
	 */
	public void setChessRowY(int chessRowY) {
		this.chessRowY = chessRowY;
	}

	/**
	 * Getter for ChessColX attribute.
	 * @return int Returns ChessColX.
	 */
	public int getChessColX() {
		return chessColX;
	}

	/**
	 * Setter for ChessColX attribute.
	 * @param chessColX New value to be assigned to attribute.
	 */
	public void setChessColX(int chessColX) {
		this.chessColX = chessColX;
	}
}
