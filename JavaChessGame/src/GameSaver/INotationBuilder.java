package GameSaver;

import GameRecord.MoveData;

/**
 * Interface for notation builders that provides required methods for building the notation files from MoveData informations.
 * @author xpeska05
 *
 */
public interface INotationBuilder 
{	
	/**
	 * Method creates notation from one move.
	 * @param move Move data from which will be created notation.
	 * @return Notation of one move.
	 */
	public String createNotationFromMove(MoveData move);

	/**
	 * This method takes line number, both white's and black's move data and assembles one line of notation.
	 * <p>
	 * When black's move is missing, line is constructed without it. 
	 * @param line Number of line of notation (1. whitemove blackmove). 
	 * @param whiteMove Move informations of white's move.
	 * @param blackMove Move informations of black's move.
	 * @return Constructed line of notation that can be written to notation file.
	 */
	public String createLineOfNotation(int line, MoveData whiteMove, MoveData blackMove);
}
