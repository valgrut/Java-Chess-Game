package Figures;

import java.util.Vector;

import ChessGame.BoardTile;

/**
 * @author xpeska05
 *
 */
public abstract class AbstractPiece implements IMovable 
{
	BoardTile position = null;
	String notation = null;
	PieceColor color = null;
	boolean isCaptured = false;
		
	/**
	 * @return
	 */
	public abstract Vector<BoardTile> getPossibleMoves();
	
	/**
	 * @param dst
	 * @return
	 */
	public boolean canMoveTo(BoardTile dst) 
	{
		Vector<BoardTile> possibleMoves = getPossibleMoves();
		
		if(possibleMoves.contains(dst)) 
			return true;
		
		return false;
	}
	
	/**
	 * Function swaps positions of source and destination figures and sets new tiles for them
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
	 * @param dst
	 * @return
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
	 * @return
	 */
	public BoardTile getPosition() {
		return position;
	}
	
	/**
	 * @param position
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
