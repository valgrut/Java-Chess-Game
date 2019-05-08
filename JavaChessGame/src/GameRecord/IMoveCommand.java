package GameRecord;

import ChessGame.ChessBoard;

public interface IMoveCommand {

	boolean execute(ChessBoard board) throws Exception;

	MoveData getMoveData();

	void setMoveData(MoveData move);

	boolean revert(ChessBoard board);
}