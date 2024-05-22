package ChessGame;

import java.io.IOException;
import java.util.Vector;

import Exceptions.EmptyMoveStackException;
import Exceptions.InvalidMoveException;
import GameRecord.GameRecord;
import GameRecord.MoveCommand;
import GameRecord.MoveData;
import GameSaver.GameSaver;
import GameSaver.INotationBuilder;
import GameSaver.NotationBuilderFactory;
import Loader.GameLoader;

/**
 * This class represents currently played game. Class holds instance of board and instance of game record and 
 * contains all operations for interacting with currently played game.
 * 
 * @author xpeska05
 */
public class CurrentGame 
{
	/**
	 * Instance of ChessBoard class representing chess board.
	 */
	private ChessBoard board;
	
	/**
	 * Instance of GameRecord class that holds all played moves and is used for managing and traversing through played game.
	 */
	private GameRecord gameRecord;
	
	/**
	 * Constructor for concrete game that is being played. Creates instance of GameRecord and of ChessBoard.
	 */
	public CurrentGame()
	{
		board = new ChessBoard();
		gameRecord = new GameRecord();
	}
	
	/**
	 * Takes the newest moves(moves player can step through) and saves notation to file.
	 * @param filename Name of file where game record will be saved.
	 */
	public void saveGame(String filename) 
	{
		GameSaver gameSaver = new GameSaver(gameRecord);
		try 
		{
			gameSaver.saveGame(filename);
		} 
		catch (IOException e) 
		{
			System.out.println("Nepovedlo se ulozit hru pomoci GameSaver.");
			e.printStackTrace();
		}
		gameSaver = null;
	}
	
	/**
	 * Loads and initializes gameRecord with notation loaded from given notation file.
	 * @param notationFile Name of file where is located notation.
	 */
	public void loadGame(String notationFile) 
	{
		System.out.println("Inicializuje se hra ze souboru...");
		GameLoader gameLoader = new GameLoader(this.gameRecord, notationFile);
		gameLoader = null;
		System.out.println("Hra byla nactena.");
	}
	
	/**
	 * Prints board setup of this game.
	 */
	public void printBoard() 
	{
		System.out.println("Aktualne provedeny tah: " + gameRecord.getCurrentMoveNumber() + "/" + gameRecord.getLastMoveNumber());
		this.board.printBoard();
	}
	
	/**
	 * Returns instance of ChessBoard of current game
	 * @return Instance of board of current game.
	 */
	public ChessBoard getBoard()
	{
		return this.board;
	}
	
	/**
	 * Step one move forward in currently played game.
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
			//this.gameRecord.setInvalidMove(true);
		}
		catch (Exception e) {
			System.out.println("General Exception Catched.");
			e.printStackTrace();
			return;
		}
	}
	
	/**
	 * Step one move backward in currently played game.
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
	
	/**
	 * Player can play own move by providing source and destination position.
	 * New Move is created and added to gameResource manager.
	 * If the move is illegal, it will not be saved to game manager.
	 * @param sourcePosition
	 * @param destinationPosition
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
		
		// kontrola, jestli je newMove proveditelny - jestli neni invalidni.
		try 
		{
			newMove.tryToExecute(this.board);
		} 
		catch (InvalidMoveException e) 
		{
			System.out.println(e.getMessage());
			return;
		}
		
		gameRecord.addPlayersMove(newMove);
		stepForward();
	}
	
	/**
	 * Undo last players move. If current move is move that will be undone, step backward is performed.
	 */
	public void undo() 
	{
		if(! gameRecord.isUndoStackEmpty())
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
	}
	
	/**
	 * Restores last undone players move.
	 */
	public void redo() 
	{
		if(! gameRecord.isRedoStackEmpty())
		{
			try 
			{
				gotoMove(gameRecord.getLastRedoMoveNumber()-1);
				gameRecord.redoLastPlayersMove();
			}
			catch (EmptyMoveStackException e)
			{
				System.out.println(e.getMessage());
				//e.printStackTrace();
			}
		}
	}

	/**
	 * Rewind game to the end.
	 */
	public void toEnd() 
	{
		gotoMove(gameRecord.getLastMoveNumber());
	} 
	
	/**
	 * Rewind game to start (to initial setup).
	 */
	public void toStart() 
	{
		gotoMove(0);
	}
	
	/**
	 * Rewind current game to the stage given by number of move.
	 * @param numberOfMove Number of move to which game will be executed.
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
	
	/**
	 * This method returns vector of strings that represents currently newest moves (player can traverse them with next, prev operations).
	 * Notation string moves are constructed by notationBuilder from MoveData objects.
	 * @return Vector<String> Vector of string representations of moves of currently played game.
	 */
	public Vector<String> getCurrentGameRecord()
	{
		INotationBuilder builder = NotationBuilderFactory.createNotationBuilder(gameRecord.getNotationType());
		
		Vector<MoveData> moves = gameRecord.getCurrentRecord();
		Vector<String> record = new Vector<String>();
		
		for(MoveData move : moves)
		{
			record.add(builder.createNotationFromMove(move));
		}
		
		return record;
	}
	
	/**
	 * Method returns players last move number.
	 * @return int Number of lastly played move.
	 */
	public int getPlayersLastMoveNumber()
	{
		return this.gameRecord.getLastMoveNumber();
	} 
	
	/**
	 * Method returns current move number.
	 * @return int Number of current move.
	 */
	public int getPlayersCurrentMoveNumber()
	{
		return this.gameRecord.getCurrentMoveNumber();
	}

}
