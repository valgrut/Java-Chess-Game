package ChessGame;

public class SubMove {
	private String figure;
	private String sourcePosition;
	private String destinationPosition;
	private String colorOfFigure;
	private String [] addition; 
	
	/*
	 * TODO udelat enum tridu, ktera bude pouzivana k pristupu do nize zmineneho pole, ve kterem budou options.
	 * TODO tohle bude trida ktera bude obsahovat "check, checkmate, casting, promotion, change".
	 */
	
	public String getColorOfFigure() {
		return colorOfFigure;
	}

	public void setColorOfFigure(String colorOfFigure) {
		this.colorOfFigure = colorOfFigure;
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
}
