package GameSaver;

import GameRecord.MoveData;
import GameRecord.MoveSituation;

/**
 * @author xpeska05
 *
 */
public class LongNotationBuilder implements INotationBuilder 
{
	/**
	 * 
	 */
	public LongNotationBuilder() 
	{
	}

	/**
	 * @see GameSaver.INotationBuilder#createNotationFromMove(GameRecord.MoveData)
	 */
	@Override
	public String createNotationFromMove(MoveData move) 
	{
		if(move == null) return "";
		
		String notationMove = "";
		
		if(move.getFigure() != "p")
		{
			notationMove += move.getFigure();
		}
		
		notationMove += move.getSourcePosition();
		
		if(move.getTakenEnemy() != null)
		{
			notationMove += "x";
		}
		
		notationMove += move.getDestinationPosition();
		
		// TODO v pripade Pawn, appendnout ho nebo pridat && not Pawn then
		if(move.getTakenEnemy() != null)
		{
			notationMove += move.getTakenEnemy();
		}
		
		if(move.getSituation() == MoveSituation.CHECK)
		{
			notationMove += "+";
		}
		else if(move.getSituation() == MoveSituation.CHECKMATE)
		{
			notationMove += "#";
		}
		else
		{
			// ...
		}
		
		return notationMove;
	}

	/**
	 * @see GameSaver.INotationBuilder#createLineOfNotation(int, GameRecord.MoveData, GameRecord.MoveData)
	 */
	@Override
	public String createLineOfNotation(int line, MoveData whiteMove, MoveData blackMove) 
	{
		String fullLine = "";
		
		fullLine += line;
		fullLine += ". ";
		fullLine += createNotationFromMove(whiteMove);
		fullLine += " ";
		fullLine += createNotationFromMove(blackMove);
		fullLine += '\n';
		return fullLine;
	}
}
