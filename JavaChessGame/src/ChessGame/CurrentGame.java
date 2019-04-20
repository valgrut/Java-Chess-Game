package ChessGame;

import Exceptions.EmptyMoveStackException;
import Exceptions.InvalidMoveException;
//import GameRecord.BasicGameRecord;
import GameRecord.GameRecord;
import GameRecord.MoveCommand;
import GameRecord.MoveData;
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
	
	/*
	 * 
	 */
	public void loadGame(String notationFile) 
	{
		System.out.println("Inicializuje se hra ze souboru...");
		GameLoader gameLoader = new GameLoader(this.gameRecord, notationFile);
		gameLoader = null;
		System.out.println("Hra byla nactena.");
	}
	
	/*
	 * 
	 */
	public void printBoard() 
	{
		System.out.println("Aktualne provedeny tah: " + gameRecord.getCurrentMoveNumber() + "/" + gameRecord.getLastMoveNumber());
		this.board.printBoard();
	}
	
	/*
	 * 
	 */
	public void stepForward()
	{
		MoveCommand nextMove;
		try 
		{
			nextMove = this.gameRecord.getNextMove();
		}
		catch (EmptyMoveStackException e) {
			System.out.println("Nelze provest dalsi tah, jsi na konci partie!");
			System.out.println(e.getMessage());
			return;
		}
		catch (InvalidMoveException e) {
			System.out.println("Byl nacteny invalidni tah z notace!");
			e.printStackTrace();
			return;
		}
		catch (Exception e) {
			System.out.println("General Exception Catched.");
			e.printStackTrace();
			return;
		}
		
		try 
		{
			nextMove.execute(this.board);
		} 
		catch (InvalidMoveException e) {
			System.out.println("Invalidni tah byl nacten z notace a nelze pokracovat!");
			this.gameRecord.setInvalidMove(true);
		}
		catch (Exception e) {
			System.out.println("General Exception Catched.");
			e.printStackTrace();
			return;
		}
	}
	
	/*
	 * 
	 */
	public void stepBackward() 
	{
		MoveCommand nextMove;
		try 
		{
			nextMove = this.gameRecord.getPrevMove();
			nextMove.revert(this.board);
		} 
		catch (EmptyMoveStackException e) {
			System.out.println("Nelze provest dalsi tah, jsi na zacatku partie!");
			return;
		}
		catch (InvalidMoveException e) {
			System.out.println("Invalidni tah byl nacten z notace a nelze pokracovat!");
			return;
		}
		catch (Exception e) {
			System.out.println("General Exception Catched.");
			e.printStackTrace();
			return;
		}
	}
	
	/*
	 * 
	 */
	public void playersMove(String sourcePosition, String destinationPosition)
	{
		// konstrukce MoveData
		MoveData moveData = new MoveData();
		moveData.setDestinationPosition(destinationPosition);
		moveData.setSourcePosition(sourcePosition);
		moveData.setFigure(board.getBoardField(sourcePosition).getFigure().getNotation());
		moveData.setChessColor(board.getBoardField(sourcePosition).getFigure().getColor());
		moveData.setMoveNumber(gameRecord.getCurrentMoveNumber() + 1);
		
		// konstrukce MoveCommand
		MoveCommand newMove = new MoveCommand(moveData);
		
		gameRecord.addPlayersMove(newMove);
		stepForward();
	}
	
	/*
	 * 
	 */
	public void undo() 
	{
		try 
		{
			if(gameRecord.getCurrentMoveNumber() == gameRecord.getLastMoveNumber())
			{
				stepBackward();
			}
			gameRecord.undoLastPlayersMove();
		} 
		catch (EmptyMoveStackException e) 
		{
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * 
	 */
	public void redo() 
	{
		try {
			gameRecord.redoLastPlayersMove();
		} catch (EmptyMoveStackException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void toEnd() {} // mozna
	public void toStart() {} // mozna
	
	/*
	 * 
	 */
	public void gotoMove(int numberOfMove)
	{
		assert(numberOfMove >= 0);
		
		int currentMove = this.gameRecord.getCurrentMoveNumber();
		if(currentMove == numberOfMove)
		{
			System.out.println("Uz se nachazis na zadanem tahu.");
			return;
		}
		if(currentMove < numberOfMove)
		{
			for(int iMove = 0; iMove < Math.abs(currentMove-numberOfMove); iMove++)
			{
				stepForward();
			}
		}
		if(currentMove > numberOfMove)
		{
			for(int iMove = 0; iMove < Math.abs(currentMove-numberOfMove); iMove++)
			{
				stepBackward();
			}
		}
	}
}
