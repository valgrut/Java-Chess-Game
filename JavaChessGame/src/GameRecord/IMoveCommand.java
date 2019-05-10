package GameRecord;

import ChessGame.ChessBoard;

/**
 * @author xpeska05
 *
 */
public interface IMoveCommand {

	/**
	 * @param board
	 * @return
	 * @throws Exception
	 */
	boolean execute(ChessBoard board) throws Exception;

	/**
	 * @return
	 */
	MoveData getMoveData();

	/**
	 * @param move
	 */
	void setMoveData(MoveData move);

	/**
	 * @param board
	 * @return
	 */
	boolean revert(ChessBoard board);
}