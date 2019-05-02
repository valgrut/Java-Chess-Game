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

/*
 * 
 */
public class CurrentGame 
{
	private ChessBoard board;
	private GameRecord gameRecord;
	
	/*
	 * Constructor for concrete game that is being played.
	 */
	public CurrentGame()
	{
		board = new ChessBoard();
		gameRecord = new GameRecord();
	}
	
	/*
	 * Takes the newest moves(moves player can step through) and saves notation to file.
	 */
	public void saveGame(String filename) 
	{
		GameSaver gameSaver = new GameSaver(gameRecord);
		try 
		{
			//gameSaver.saveGame("/root/git/JavaChessGame/JavaChessGame/moje_notace.out");
			gameSaver.saveGame(filename);
		} 
		catch (IOException e) 
		{
			System.out.println("Nepovedlo se ulozit hru pomoci GameSaver.");
			e.printStackTrace();
		}
		gameSaver = null;
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
	 * TODO: There should be parameter of object, that serves as source where to draw board??
	 * OR board will be given as parameter to graphic entity, which will update based on board?
	 */
	public void printBoard() 
	{
		System.out.println("Aktualne provedeny tah: " + gameRecord.getCurrentMoveNumber() + "/" + gameRecord.getLastMoveNumber());
		this.board.printBoard();
	}
	
	/*
	 * Returns instance of ChessBoard of current game
	 */
	public ChessBoard getBoard()
	{
		return this.board;
	}
	
	/*
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
	
	/*
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
	
	/*
	 * Player can play own move by providing source and destination position.
	 * New Move is created and added to gameResource manager.
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
	
	/*
	 * Undo last players move. If current move is move, that will be undone, step backward is performed.
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
	
	/*
	 * Restores last undone players move
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
	
	/*
	 * Rewind game to the end
	 */
	public void toEnd() 
	{
		gotoMove(gameRecord.getLastMoveNumber());
	} 
	
	/*
	 * Rewind game to start
	 */
	public void toStart() 
	{
		gotoMove(0);
	}
	
	/*
	 * Rewind game to the stage given by number of move
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
	
	public int getPlayersLastMoveNumber()
	{
		return this.gameRecord.getLastMoveNumber();
	} 
	
	public int getPlayersCurrentMoveNumber()
	{
		return this.gameRecord.getCurrentMoveNumber();
	}

}
