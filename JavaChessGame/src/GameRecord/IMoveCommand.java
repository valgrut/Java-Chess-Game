package GameRecord;

import ChessGame.ChessBoard;

public interface IMoveCommand {

	boolean execute(ChessBoard board) throws Exception;

	MoveData getMove();

	void setMove(MoveData move);

	boolean revert(ChessBoard board);
}