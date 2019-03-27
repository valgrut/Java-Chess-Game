package ChessGame;

import Figures.AbstractPiece;
import GameRecord.BasicGameRecord;
import GameRecord.MoveData;
import Loader.GameLoader;

public class CurrentGame {
	private ChessBoard board;
	private BasicGameRecord gameRecord;
	
	public CurrentGame()
	{
		board = new ChessBoard();
		gameRecord = new BasicGameRecord();
	}
	
	public void saveGame() 
	{}
	
	public void loadGame(String notationFile) 
	{
		System.out.println("Inicializuje se hra ze souboru...");
		GameLoader gameLoader = new GameLoader(this.gameRecord, notationFile);
		gameLoader = null;
		System.out.println("Hra byla nactena.");
	}
	
	public void printBoard() 
	{
		this.board.printBoard();
	}
	
	public void nextMove()
	{
		//MoveData nextMove = this.gameRecord.getNextMove();
		//BoardTile figure = this.board.getFigureOnPosition(nextMove.getSourcePosition());
		// if(figure.isValidMove(nextMove.getDestinationPosition())) {}
		//this.board.swap(nextMove.getSourcePosition(), nextMove.getDestinationPosition());
	}
	
	public void prevMove() 
	{
		
	}
	
	public void toEnd() {}
	public void toStart() {}
	public void gotoTah(int playersMove) {}
	public void playersMove(String sourcePosition, String destinationPosition) {}
	public void undo() {}
	public void redo() {}
}
