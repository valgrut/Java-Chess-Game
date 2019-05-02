package ChessGame;

import java.util.Vector;

public class GameManager {
	private Vector<CurrentGame> openedGames;
	private int activeGameIndex;
	
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
	
	/*
	 * Creates empty game and initializes it from given notation file
	 */
	public void loadGame(String notationFile)
	{
		this.createNewGame();
		this.getActiveGame().loadGame(notationFile);
	}
	
	/**
	 * Pristupova metoda, vraci instanci aktualne zvolene hry
	 * Pouziva se pro pristup do AKTIVNI hry.
	 * @return aktualne aktivni hru
	 */
	public CurrentGame getActiveGame()
	{
		return this.openedGames.elementAt(this.getActiveGameIndex()); 
	}
	
	public void saveGame(String filename)
	{
		this.getActiveGame().saveGame(filename);
	}
	
	public void closeGame()
	{
		this.openedGames.remove(this.getActiveGameIndex());
		//TODO emplace za null?
	}
	
	public void setActiveGame(int index)
	{
		if(index >= 0 && index < this.getOpenedGameCount() )
			this.activeGameIndex = index;
	}
	
	public int getActiveGameIndex()
	{
		return this.activeGameIndex;
	}
	
	public int getOpenedGameCount()
	{
		return this.openedGames.size();
	}
	
	public void printGameBoard()
	{
		this.getActiveGame().printBoard();
	}
	
}
