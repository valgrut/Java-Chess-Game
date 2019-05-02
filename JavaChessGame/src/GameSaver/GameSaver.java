package GameSaver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import GameRecord.GameRecord;
import GameRecord.MoveData;

/*
 * 
 */
public class GameSaver 
{
	private GameRecord gameRecord;
	
	/*
	 * 
	 */
	public GameSaver(GameRecord gameRecord)
	{
		this.gameRecord = gameRecord;
	}
	
	/*
	 * 
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
