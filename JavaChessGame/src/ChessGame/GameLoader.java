package ChessGame;

public class GameLoader {
	public GameLoader(GameRecord gameRecord, String notationFile)
	{
		//IReader reader = new BufferedNotationReader(notationFile);
		IReader reader = new BufferedNotationReader("/root/eclipse-workspace/FileReader/Notation_1"); 
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
