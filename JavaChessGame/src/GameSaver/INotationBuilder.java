package GameSaver;

import GameRecord.MoveData;

/**
 * @author root
 *
 */
public interface INotationBuilder 
{	
	/**
	 * 
	 * @param move
	 * @return
	 */
	public String createNotationFromMove(MoveData move);

	/**
	 * 
	 * @param line
	 * @param whiteMove
	 * @param blackMove
	 * @return
	 */
	public String createLineOfNotation(int line, MoveData whiteMove, MoveData blackMove);
}
