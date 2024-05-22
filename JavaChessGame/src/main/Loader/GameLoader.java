package Loader;

import GameRecord.GameRecord;
import GameRecord.MoveCommand;
import GameRecord.Pair;
import GameSaver.NotationType;

/**
 * This class handles loading the game from notation file, parsing it and create Move objects.
 * 
 * @author xpeska05
 */
public class GameLoader 
{
	/**
	 * Constructor that initializes given gameRecord with move data created from given notationFile.
	 * <p>
	 * Method reads line by line using Reader instance and validates these lines, then parses them and creates MoveData.
	 * MoveData are inserted to game record instance.
	 * @param gameRecord Instance of game record that will be initialized with move data.
	 * @param notationFile File with notation records that will be readed and parsed.
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
			
			Pair fullMove;
			try 
			{
				fullMove = parser.parseLine(fullMoveLine);
	
				fullMove.getFirst().printThisMove();
				
				gameRecord.addMove(new MoveCommand(fullMove.getFirst())); //white
				if(fullMove.getSecond() != null)
				{	
					fullMove.getSecond().printThisMove();
					gameRecord.addMove(new MoveCommand(fullMove.getSecond())); //black
				}
			} 
			catch (InvalidNotationException e) 
			{
				System.out.println(e.getMessage());
				break;
			}
		}
	}
	
}
