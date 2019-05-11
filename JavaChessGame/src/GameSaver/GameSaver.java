package GameSaver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import GameRecord.GameRecord;
import GameRecord.MoveData;


/**
 * Class that is used when current game record is required to be saved to file. GameSaver uses for 
 * creating the notation lines from MoveDatas appropriate NotationBuilder according to notation type.
 * @author xpeska05
 *
 */
public class GameSaver 
{
	/**
	 * Instance of gameRecord that contains moves, that will be transformed to notation and saved to file.
	 */
	private GameRecord gameRecord;
	
	/**
	 * Constructor. 
	 * @param gameRecord Instance of GameRecord that will be saved to file.
	 */
	public GameSaver(GameRecord gameRecord)
	{
		this.gameRecord = gameRecord;
	}
	
	/**
	 * Method constructs appropriate notation builder and writer. Then retrieves newest moves from gameRecord and for each move builds 
	 * notation record that writes to the output file.
	 * @param fileName Name of output notation file to which new notation will be saved.
	 * @throws IOException Throws exception when error occurs during writing.
	 */
	public void saveGame(String fileName) throws IOException
	{
		/*
		 * TODO: melo by se to zeptat, jestli uz soubor neexistuje - ne- ok, ano - zadej jiny nazev.
		 */
		int notationLineNumber = 1;
		INotationBuilder builder = NotationBuilderFactory.createNotationBuilder(gameRecord.getNotationType());
		
	    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
	    Vector<MoveData> record = gameRecord.getCurrentRecord();
	    
	    MoveData whiteMove = null;
	    MoveData blackMove = null;
	    
	    for(MoveData movedata : record)
	    {
	    	if(whiteMove == null)
	    	{
	    		whiteMove = movedata;
	    		continue;
	    	}
	    	if(blackMove == null)
	    	{
	    		blackMove = movedata;
	    	}
	    	if(whiteMove != null && blackMove != null)
	    	{
	    		writer.append(builder.createLineOfNotation(notationLineNumber, whiteMove, blackMove));
	    		notationLineNumber++;
	    		
	    		whiteMove = null;
	    		blackMove = null;
	    	}
	    }
	    
	    if(whiteMove != null && blackMove == null)
    	{
    		writer.append(builder.createLineOfNotation(notationLineNumber, whiteMove, blackMove));
    	}
	    
		writer.close();
	}
}
