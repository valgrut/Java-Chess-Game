package Figures;

import java.util.Vector;

import ChessGame.BoardTile;

/**
 * Class represents abstract piece from which concrete pieces will inherit methods and properties. Each concrete piece will implement their 
 * own move rules.
 * @author xpeska05
 *
 */
public abstract class AbstractPiece implements IMovable 
{
	/**
	 * Position of this piece. Tile where this piece is placed.
	 */
	BoardTile position = null;
	
	/**
	 * Notation character of this figure (V, D, K, p, ).
	 */
	String notation = null;
	
	/**
	 * Color of this piece.
	 */
	PieceColor color = null;
	
	/**
	 * Flag indicates whether this piece has been taken.
	 */
	boolean isCaptured = false;
		
	/**
	 * Method returns vector of tiles where piece can move to according to its moving rules.
	 * @return Vector of BoardTile objects.
	 */
	public abstract Vector<BoardTile> getPossibleMoves();
	
	/**
	 * Method checks if piece can be moved to given destination tile according to its move rules.
	 * @param dst Destination tile we want to move piece to.
	 * @return True if this piece can move to destination tile dst.
	 */
	public boolean canMoveTo(BoardTile dst) 
	{
		Vector<BoardTile> possibleMoves = getPossibleMoves();
		
		if(possibleMoves.contains(dst)) 
			return true;
		
		return false;
	}
	
	/**
	 * Function swaps positions of source and destination figures and sets new tiles for them.
	 * @see Figures.IMovable#move(ChessGame.BoardTile)
	 */
	@Override
	public boolean move(BoardTile dst) 
	{
		/*
		if(this.canMoveTo(dst) == false)
		{
			return false;
		}
		
		// neni tam nepratelska figurka
		if(dst.isEmpty())
		{	
			AbstractPiece tmpNullFigure = dst.getFigure();
			BoardTile sourceTile = this.getPosition();
			
			dst.setFigure(this);
			this.setPosition(dst);

			sourceTile.setFigure(tmpNullFigure);
			tmpNullFigure.setPosition(sourceTile);
		}
		else
		{	
			AbstractPiece dstFigure = dst.getFigure();
			dstFigure.setCaptured(true);
			
			EmptyPlace nullPiece = new EmptyPlace();
			nullPiece.setPosition(this.getPosition());
			
			BoardTile tmp = this.getPosition();
			
			this.setPosition(dst);
			dst.setFigure(this);
			
			tmp.setFigure(nullPiece);
			//dstFigure.setPosition(getPosition());
			
		}
		
		return true;
		*/
		return false;
	}
	
	/**
	 * This method moves current position to destination tile without checking the move rules of moved figure.
	 * @param dst Destination tile where this piece will be moved to.
	 * @return True if move is successfull.
	 */
	public boolean moveHard(BoardTile dst) 
	{
		// neni tam nepratelska figurka
		/*
		if(dst.isEmpty())
		{
			AbstractPiece dstFigure = dst.getFigure();
			dst.setFigure(this);
			getPosition().setFigure(dstFigure);
			this.setPosition(dst);
			dstFigure.setPosition(getPosition());
		}
		else
		{	
			AbstractPiece dstFigure = dst.getFigure();
			//dstFigure.setCaptured(true);
			
			EmptyPlace nullPiece = new EmptyPlace();
			nullPiece.setPosition(this.getPosition());
			
			BoardTile tmp = this.getPosition();
			
			this.setPosition(dst);
			dst.setFigure(this);
			
			tmp.setFigure(nullPiece);
			//dstFigure.setPosition(getPosition());
		}
		
		return true;
		*/
		
		return false;
	}
	
	/**
	 * Method returns tile position of this figure.
	 * @return Tile where this figure is placed.
	 */
	public BoardTile getPosition() {
		return position;
	}
	
	/**
	 * Method sets tile position of this figure.
	 * @param position Tile will be set for this figure.
	 */
	public void setPosition(BoardTile position) {
		this.position = position;
	}
	
	/**
	 * @return
	 */
	public String getNotation() {
		return notation;
	}
	
	/**
	 * @param notation
	 */
	public void setNotation(String notation) {
		this.notation = notation;
	}
	
	/**
	 * @return
	 */
	public PieceColor getColor() {
		return color;
	}
	
	/**
	 * @param color
	 */
	public void setColor(PieceColor color) {
		this.color = color;
	}
	
	/**
	 * @return
	 */
	public boolean isCaptured() {
		return isCaptured;
	}
	
	/**
	 * @param isCaptured
	 */
	public void setCaptured(boolean isCaptured) {
		this.isCaptured = isCaptured;
	}
}
