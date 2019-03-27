package GameRecord;

import ChessGame.ChessBoard;

public interface IMoveCommand {

	boolean execute(ChessBoard board);

	MoveData getMove();

	void setMove(MoveData move);

	boolean revert(ChessBoard board);
}