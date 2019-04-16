package ChessGame;

//import GameRecord.BasicGameRecord;
import GameRecord.GameRecord;
import GameRecord.MoveCommand;
//import GameRecord.MoveData;
import Loader.GameLoader;

public class CurrentGame {
	private ChessBoard board;
	private GameRecord gameRecord;
	
	public CurrentGame()
	{
		board = new ChessBoard();
		gameRecord = new GameRecord();
	}
	
	public void saveGame() 
	{
		// take GameRecord object and save current game, using GameSaver class.
	}
	
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
		MoveCommand nextMove;
		try {
			nextMove = this.gameRecord.getNextMove();
			nextMove.execute(this.board);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Nelze provest dalsi tah, jsi na konci partie!");
		}
	}
	
	public void prevMove() 
	{
		MoveCommand nextMove;
		try {
			nextMove = this.gameRecord.getPrevMove();
			nextMove.revert(this.board);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Nelze provest dalsi tah, jsi na zacatku partie!");
		}
	}
	
	public void toEnd() {} // mozna
	public void toStart() {} // mozna
	public void gotoTah(int numberOfMove) {}
	public void playersMove(String sourcePosition, String destinationPosition) {}
	public void undo() {}
	public void redo() {}
}
