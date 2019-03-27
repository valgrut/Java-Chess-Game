package GameRecord;

import ChessGame.ChessBoard;

public class MoveCommand implements IMoveCommand {
	private MoveData move;
	
	public MoveCommand(MoveData move)
	{
		this.move = move;
	}
	
	/* (non-Javadoc)
	 * @see GameRecord.IMoveCommand#execute()
	 */
	@Override
	public boolean execute(ChessBoard board) 
	{
		return false;
	}
	
	/* (non-Javadoc)
	 * @see GameRecord.IMoveCommand#getMove()
	 */
	@Override
	public MoveData getMove() {
		return move;
	}

	/* (non-Javadoc)
	 * @see GameRecord.IMoveCommand#setMove(GameRecord.Move)
	 */
	@Override
	public void setMove(MoveData move) {
		this.move = move;
	}

	/* (non-Javadoc)
	 * @see GameRecord.IMoveCommand#revert()
	 */
	@Override
	public boolean revert(ChessBoard board)
	{		
		return false;
	}
	
}
