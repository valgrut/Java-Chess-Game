package GameSaver;

import GameRecord.MoveData;

public class LongNotationBuilder implements INotationBuilder 
{
	public LongNotationBuilder() 
	{
	}

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
		
		/*
		 * TODO add check of king as '+' to the end according to MoveSituation.
		if(move.isKingChecked() == true)
		{
			notationMove += "+";
		}
		 */
		
		return notationMove;
	}

	@Override
	public String createLineOfNotation(int line, MoveData whiteMove, MoveData blackMove) {
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
