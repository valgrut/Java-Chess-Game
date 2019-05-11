package GameRecord;

import ChessGame.ChessBoard;

/**
 * Interface for MoveCommand. Move Command contains MoveData informations, that are used for executing and reverting command.
 * 
 * @author xpeska05
 *
 */
public interface IMoveCommand {

	/**
	 * Executes move according to MoveData object and modifies given board according to that move.
	 * Before execution, method canExecute is used to check the move data validation.
	 * @param board Board object that will be modified according to move.
	 * @return True when execute succeeded.
	 * @throws Exception Thrown when error occures.
	 */
	boolean execute(ChessBoard board) throws Exception;

	/**
	 * Returns MoveData object that belongs to this command.
	 * @return MoveData from this object.
	 */
	MoveData getMoveData();

	/**
	 * Setter for MoveData.
	 * @param move MoveData that will be used for execute or revert operations.
	 */
	void setMoveData(MoveData move);

	/**
	 * Executes the move in switched source and destination position.
	 * @param board Board object that will be modified according to move.
	 * @return True when reverting succeeded.
	 */
	boolean revert(ChessBoard board);
}