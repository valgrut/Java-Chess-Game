package Loader;

import GameRecord.GameRecord;
import GameRecord.MoveCommand;
import GameRecord.Pair;
import GameSaver.NotationType;

/**
 * @author xpeska05
 *
 */
public class GameLoader 
{
	/**
	 * @param gameRecord
	 * @param notationFile
	 */
	public GameLoader(GameRecord gameRecord, String notationFile)
	{
		IReader reader = new BufferedNotationReader(notationFile);
		IParser parser = new LongNotationParser();
		
		//TODO
//		if(notation is long)
			gameRecord.setNotationType(NotationType.LONG); //TODO - tohle se nastavi podle toho, jaka ta notace opravdu je.
//		else
//			gameRecord.setNotationType(NotationType.SHORT);
		
		String fullMoveLine;
		while((fullMoveLine = reader.getNextLine()) != null)
		{
			System.out.println(fullMoveLine);
			
			Pair fullMove = parser.parseLine(fullMoveLine);
			fullMove.getFirst().printThisMove();
			
			
			gameRecord.addMove(new MoveCommand(fullMove.getFirst())); //white
			if(fullMove.getSecond() != null)
			{	
				fullMove.getSecond().printThisMove();
				gameRecord.addMove(new MoveCommand(fullMove.getSecond())); //black
			}
		}
	}
	
}
