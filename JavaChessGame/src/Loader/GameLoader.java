package Loader;

//import GameRecord.BasicGameRecord;
import GameRecord.GameRecord;
import GameRecord.MoveCommand;
//import GameRecord.MoveData;
import GameRecord.Pair;

public class GameLoader {
	public GameLoader(GameRecord gameRecord, String notationFile)
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
			
			gameRecord.addMove(new MoveCommand(fullMove.getFirst())); //white
			gameRecord.addMove(new MoveCommand(fullMove.getSecond())); //black // TODO zkontrolovat, ze partie obsahuje jako posledni tah cerny
		}
	}
	
}
