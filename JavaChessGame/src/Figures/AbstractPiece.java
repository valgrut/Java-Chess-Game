package Figures;

import java.util.Vector;

import ChessGame.BoardTile;

public abstract class AbstractPiece implements IMovable {
	BoardTile position = null;
	String notation = null;
	PieceColor color = null;
	boolean isCaptured = false;
		
	public abstract Vector<BoardTile> getPossibleMoves();
	
	public boolean canMoveTo(BoardTile dst) {
		Vector<BoardTile> possibleMoves = getPossibleMoves();
		
		if(possibleMoves.contains(dst)) 
			return true;
		
		return false;
	}
	
	@Override
	public boolean move(BoardTile dst) {
		if(this.canMoveTo(dst) == false)
		{
			return false;
		}
		
		// neni tam nepratelska figurka
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
		
	}
	
	
	public BoardTile getPosition() {
		return position;
	}
	public void setPosition(BoardTile position) {
		this.position = position;
	}
	public String getNotation() {
		return notation;
	}
	public void setNotation(String notation) {
		this.notation = notation;
	}
	public PieceColor getColor() {
		return color;
	}
	public void setColor(PieceColor color) {
		this.color = color;
	}
	public boolean isCaptured() {
		return isCaptured;
	}
	public void setCaptured(boolean isCaptured) {
		this.isCaptured = isCaptured;
	}
}
