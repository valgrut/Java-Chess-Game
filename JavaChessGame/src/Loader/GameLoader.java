package Loader;

//import GameRecord.BasicGameRecord;
import GameRecord.GameRecord;
import GameRecord.MoveCommand;
//import GameRecord.MoveData;
import GameRecord.Pair;
import GameSaver.NotationType;

public class GameLoader {
	public GameLoader(GameRecord gameRecord, String notationFile)
	{
		IReader reader = new BufferedNotationReader(notationFile);
		//IReader reader = new BufferedNotationReader("/root/git/JavaChessGame/JavaChessGame/Notation_1"); 
		IParser parser = new LongNotationParser();
		
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
