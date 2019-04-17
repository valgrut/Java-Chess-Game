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
		System.out.println("Aktualne provedeny tah: " + gameRecord.getCurrentMove());
		this.board.printBoard();
	}
	
	public void nextMove()
	{
		MoveCommand nextMove;
		try 
		{
			nextMove = this.gameRecord.getNextMove();
		}
		catch (Exception e) {
			System.out.println("Nelze provest dalsi tah, jsi na konci partie!");
			System.out.println("NEBO byl nacteny invalidni tah z notace!");
			return;
		}
		try 
		{
			nextMove.execute(this.board);
		} 
		catch (Exception e) {
			System.out.println("Invalidni tah byl nacten z notace a nelze pokracovat!");
			this.gameRecord.setInvalidMove(true);
		}
	}
	
	public void prevMove() 
	{
		MoveCommand nextMove;
		try 
		{
			nextMove = this.gameRecord.getPrevMove();
			nextMove.revert(this.board);
		} 
		catch (Exception e) {
			System.out.println("Nelze provest dalsi tah, jsi na zacatku partie!");
			System.out.println("NEBO Invalidni tah byl nacten z notace a nelze pokracovat!");
			return;
		}
	}
	
	public void toEnd() {} // mozna
	public void toStart() {} // mozna
	
	public void gotoTah(int numberOfMove)
	{
		assert(numberOfMove >= 0);
		
		int currentMove = this.gameRecord.getCurrentMove();
		if(currentMove == numberOfMove)
		{
			System.out.println("Uz se nachazis na zadanem tahu.");
			return;
		}
		if(currentMove < numberOfMove)
		{
			for(int iMove = 0; iMove < Math.abs(currentMove-numberOfMove); iMove++)
			{
				nextMove();
			}
		}
		if(currentMove > numberOfMove)
		{
			for(int iMove = 0; iMove < Math.abs(currentMove-numberOfMove); iMove++)
			{
				prevMove();
			}
		}
	}
	
	public void playersMove(String sourcePosition, String destinationPosition)
	{
		
	}
	public void undo() {}
	public void redo() {}
}
