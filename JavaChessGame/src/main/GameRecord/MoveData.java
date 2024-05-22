package GameRecord;

import Figures.PieceColor;

/**
 * Class that stores complete informations about move.
 * 
 * @author xpeska05
 */
public class MoveData 
{
	private Integer moveNumber;
	private PieceColor color;
	private String figure;
	private String takenFigure;
	private String sourcePosition;
	private String destinationPosition;
	private MoveSituation situation = null;

	/**
	 * Constructor.
	 */
	public MoveData() {}
	
	/**
	 * @param number
	 * @param currentColor
	 */
	public MoveData(int number, PieceColor currentColor) 
	{
		this.moveNumber = number;
		this.color = currentColor;
	}
	
	/**
	 * @return
	 */
	public Integer getMoveNumber() {
		return moveNumber;
	}
	/**
	 * @param moveNumber
	 */
	public void setMoveNumber(Integer moveNumber) {
		this.moveNumber = moveNumber;
	}

	/**
	 * @return
	 */
	public String getFigure() {
		return figure;
	}
	
	/**
	 * @param figure
	 */
	public void setFigure(String figure) {
		this.figure = figure;
	}
	
	/**
	 * @return
	 */
	public String getSourcePosition() {
		return sourcePosition;
	}
	
	/**
	 * @param sourcePosition
	 */
	public void setSourcePosition(String sourcePosition) {
		this.sourcePosition = sourcePosition;
	}
	
	/**
	 * @return
	 */
	public String getDestinationPosition() {
		return destinationPosition;
	}
	
	/**
	 * @param destinationPosition
	 */
	public void setDestinationPosition(String destinationPosition) {
		this.destinationPosition = destinationPosition;
	}
	
	/**
	 * @param color
	 */
	public void setChessColor(PieceColor color)
	{
		this.color = color;
	}
	
	/**
	 * @return
	 */
	public PieceColor getChessColor()
	{
		return this.color;
	}
	
	/**
	 * 
	 */
	public void printThisMove()
	{
		System.out.println("Tah: " + this.moveNumber + ", hrac: " + this.color + ", " + this.figure + this.sourcePosition + this.destinationPosition);
	}
	
	/**
	 * @return
	 */
	public String getTakenEnemy() {
		return this.takenFigure;
	}
	
	/**
	 * @param enemy
	 */
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
