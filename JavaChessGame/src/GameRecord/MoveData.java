package GameRecord;

import Figures.PieceColor;

public class MoveData 
{
	private Integer moveNumber;
	private PieceColor color;
	private String figure;
	private String takenFigure;
	private String sourcePosition;
	private String destinationPosition;
	private MoveSituation situation = null;
	
	/*
	 * pridat sem atribut kterej bude drzet vzatou figurku, pokud v tomto tahu doslo k vzati figurky.
	 */
	
	public MoveData() {}
	public MoveData(int number, PieceColor currentColor) 
	{
		this.moveNumber = number;
		this.color = currentColor;
	}
	
	public Integer getMoveNumber() {
		return moveNumber;
	}
	public void setMoveNumber(Integer moveNumber) {
		this.moveNumber = moveNumber;
	}

	public String getFigure() {
		return figure;
	}
	
	public void setFigure(String figure) {
		this.figure = figure;
	}
	
	public String getSourcePosition() {
		return sourcePosition;
	}
	
	public void setSourcePosition(String sourcePosition) {
		this.sourcePosition = sourcePosition;
	}
	
	public String getDestinationPosition() {
		return destinationPosition;
	}
	
	public void setDestinationPosition(String destinationPosition) {
		this.destinationPosition = destinationPosition;
	}
	
	public void setChessColor(PieceColor color)
	{
		this.color = color;
	}
	
	public PieceColor getChessColor()
	{
		return this.color;
	}
	
	public void printThisMove()
	{
		System.out.println("Tah: " + this.moveNumber + ", hrac: " + this.color + ", " + this.figure + this.sourcePosition + this.destinationPosition);
	}
	public String getTakenEnemy() {
		return this.takenFigure;
	}
	public void setTakenEnemy(String enemy) {
		this.takenFigure = enemy;
	}
	/**
	 * @return the situation
	 */
	public MoveSituation getSituation() {
		return situation;
	}
	/**
	 * @param situation the situation to set
	 */
	public void setSituation(MoveSituation situation) {
		this.situation = situation;
	}
}
