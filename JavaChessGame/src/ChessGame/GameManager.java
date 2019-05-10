package ChessGame;

import java.util.Vector;

/**
 * Game manager class is used for managing opened games (like tabs in GUI). Active game, to which control commands will be forwarded
 * is chosen by index to the vector of active games.
 * @author xpeska05
 *
 */
public class GameManager 
{
	/**
	 * Vector of CurrentGame holds all opened games.
	 */
	private Vector<CurrentGame> openedGames;
	
	/**
	 * Index to the vector of opened games. Index represents currently active game.
	 * When there is more than one opened game in same time, index points to active game, to which user commands will be forwarded.
	 */
	private int activeGameIndex;
	
	/**
	 * Constructor. Creates instances of Vector that holds opened games.
	 * Sets active game index to -1 value, because no game is opened in this state.
	 */
	public GameManager()
	{
		this.openedGames = new Vector<CurrentGame>();
		activeGameIndex = -1;
	}
	
	/**
	 * Creates empty game
	 */
	public void createNewGame()
	{
		CurrentGame newGame = new CurrentGame();
		
		this.openedGames.add(newGame);
		this.setActiveGame(this.getOpenedGameCount()-1);
	}
	
	/**
	 * Creates empty game and initializes it from given notation file
	 * @param notationFile
	 */
	public void loadGame(String notationFile)
	{
		this.createNewGame();
		this.getActiveGame().loadGame(notationFile);
	}
	
	/**
	 * Method return instance of currently active game. Active game is given by index to vector.
	 * This method is used for access to the currently chosen game by user. 
	 * @return 
	 */
	public CurrentGame getActiveGame()
	{
		return this.openedGames.elementAt(this.getActiveGameIndex()); 
	}
	
	/**
	 * This method saves move record of active game to the notation file.
	 * @param filename Name of notation file where game record of active game will be saved.
	 */
	public void saveGame(String filename)
	{
		this.getActiveGame().saveGame(filename);
	}
	
	/**
	 * Method removes active game from vector of currently opened games.
	 */
	public void closeGame()
	{
		this.openedGames.remove(this.getActiveGameIndex());
		//TODO emplace za null?
	}
	
	/**
	 * Method used for setting new active game.
	 * @param index Index of opened game that user want to set as active. Index has to be in range {0, ..., number of opened games - 1}.
	 */
	public void setActiveGame(int index)
	{
		if(index >= 0 && index < this.getOpenedGameCount() )
			this.activeGameIndex = index;
	}
	
	/**
	 * Method returns index of currently active game to which game commands will be forwarded.
	 * @return Index of currently active game.
	 */
	public int getActiveGameIndex()
	{
		return this.activeGameIndex;
	}
	
	/**
	 * Method returns number of opened games. Games can be opened clear or loaded from notation files.
	 * @return int Number of opened games.
	 */
	public int getOpenedGameCount()
	{
		return this.openedGames.size();
	}
	
	/**
	 * Prints game board of an active game.
	 */
	public void printGameBoard()
	{
		this.getActiveGame().printBoard();
	}
	
}
