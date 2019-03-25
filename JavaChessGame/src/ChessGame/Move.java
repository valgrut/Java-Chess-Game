package ChessGame;
public class Move 
{
	private Integer moveNumber;
	private ChessColor color;
	private String figure;
	private String sourcePosition;
	private String destinationPosition;
	private String additional = "+#KDJVSp";
	
	/*
	 * TODO udelat enum tridu, ktera bude pouzivana k pristupu do nize zmineneho pole, ve kterem budou options.
	 * TODO tohle bude trida ktera bude obsahovat "check, checkmate, casting, promotion, change".
	 */
	
	/*
	 * pridat sem atribut kterej bude drzet vzatou figurku, pokud v tomto tahu doslo k vzati figurky.
	 */
	
	public Move() {}
	public Move(int number, ChessColor currentColor) 
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
	
	public void setChessColor(ChessColor color)
	{
		this.color = color;
	}
	
	public ChessColor getChessColor()
	{
		return this.color;
	}
	
	public void printThisMove()
	{
		System.out.println("Tah: " + this.moveNumber + ", hrac: " + this.color + ", " + this.figure + this.sourcePosition + this.destinationPosition);
	}
}
