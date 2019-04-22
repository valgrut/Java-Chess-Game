package GameSaver;

import GameRecord.MoveData;

public interface INotationBuilder 
{	
	public String createNotationFromMove(MoveData move);
	public String createLineOfNotation(int line, MoveData whiteMove, MoveData blackMove);
}
