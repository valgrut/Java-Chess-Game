package Loader;

import GameRecord.BasicGameRecord;
import GameRecord.MoveData;
import GameRecord.Pair;

public class GameLoader {
	public GameLoader(BasicGameRecord gameRecord, String notationFile)
	{
		//IReader reader = new BufferedNotationReader(notationFile);
		IReader reader = new BufferedNotationReader("/root/git/JavaChessGame/JavaChessGame/Notation_1"); 
		IParser parser = new NotationParser();
		
		String fullMoveLine;
		while((fullMoveLine = reader.getNextLine()) != null)
		{
			System.out.println(fullMoveLine);
			
			Pair fullMove = parser.parseLine(fullMoveLine);
			fullMove.getFirst().printThisMove();
			fullMove.getSecond().printThisMove();
			
			gameRecord.addMove(fullMove.getFirst()); //white
			gameRecord.addMove(fullMove.getSecond()); //black
		}
	}
	
}
