package GameSaver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
	    
		while(gameRecord.getCurrentMoveNumber() != gameRecord.getLastMoveNumber())
		{
			MoveData whiteMove = null;
			try 
			{
				whiteMove = gameRecord.getNextMove().getMove();
			} 
			catch (Exception e1) 
			{
				System.out.println("GameSaver: Bily tah neexistuje, takze nebude zapsan.");
				break;
			}
			
			MoveData blackMove = null;
			try 
			{
				blackMove = gameRecord.getNextMove().getMove();
			}
			catch(Exception e) 
			{
				System.out.println("GameSaver: cerny tah neexistuje, takze nebude zapsan.");
			}
			
			writer.append(builder.createLineOfNotation(notationLineNumber, whiteMove, blackMove));
			notationLineNumber++;
		}
		writer.close();
	}
}
