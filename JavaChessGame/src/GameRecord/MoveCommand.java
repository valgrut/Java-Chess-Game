package GameRecord;

import ChessGame.BoardTile;
import ChessGame.ChessBoard;
import Exceptions.InvalidMoveException;
import Figures.AbstractPiece;
import Loader.FigureFactory;

public class MoveCommand implements IMoveCommand {
	private MoveData move;
	private AbstractPiece takenEnemy;

	public MoveCommand(MoveData move)
	{
		this.move = move;
		takenEnemy = null;
	}
		
	/* (non-Javadoc)
	 * @see GameRecord.IMoveCommand#execute()
	 */
	@Override
	public boolean execute(ChessBoard board) throws Exception 
	{
		BoardTile sourceTile = board.getBoardField(this.move.getSourcePosition());
		BoardTile destinationTile = board.getBoardField(this.move.getDestinationPosition());
		
		if(sourceTile.getFigure().canMoveTo(destinationTile) == false)
		{
			throw new InvalidMoveException("Byl nacten invalidni tah v notaci. Nelze hrat dal!");
		}
		// TODO // zde asi budou nejake cachry v pripade kratke notace, a jen v pripade, ze to nezaridim uz pri inicializaci.
		// cachry ve smyslu: najdi figurku, ktera muze na tohle pole (protoze nemam src tile)

		// nepritel je zde
		AbstractPiece enemy = null;
		AbstractPiece sourceFigure = sourceTile.getFigure();
		if(destinationTile.getFigure().isMovable())
		{
			enemy = destinationTile.getFigure();
			this.takenEnemy = enemy;
			move.setTakenEnemy(enemy.getNotation());
			
			AbstractPiece nullFig = FigureFactory.createFigureByNotation('.');
			nullFig.setPosition(sourceTile);
			sourceTile.setFigure(nullFig);
			
			destinationTile.setFigure(sourceFigure);
			sourceFigure.setPosition(destinationTile);
		}
		else // cilove pole je prazdne.
		{
			// proste se ta pole prohodi.
			AbstractPiece tmpNullFigure = destinationTile.getFigure();
			
			destinationTile.setFigure(sourceFigure);
			sourceFigure.setPosition(destinationTile);
			
			sourceTile.setFigure(tmpNullFigure);
			tmpNullFigure.setPosition(sourceTile);
		}
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see GameRecord.IMoveCommand#revert()
	 */
	@Override
	public boolean revert(ChessBoard board)
	{	
		BoardTile destinationTile = board.getBoardField(this.move.getSourcePosition());
		BoardTile sourceTile = board.getBoardField(this.move.getDestinationPosition());

		AbstractPiece sourceFigure = sourceTile.getFigure();
		
		// pokud byl v tomto tahu vzat nepritel, vratime ho zpet.
		if(takenEnemy != null)
		{
			destinationTile.setFigure(sourceFigure);
			sourceFigure.setPosition(destinationTile);
			
			sourceTile.setFigure(takenEnemy);
			takenEnemy.setPosition(sourceTile);
		}
		else // nikdo vzat nebyl
		{
			AbstractPiece tmpNullFigure = destinationTile.getFigure();
			
			destinationTile.setFigure(sourceFigure);
			sourceFigure.setPosition(destinationTile);
			
			sourceTile.setFigure(tmpNullFigure);
			tmpNullFigure.setPosition(sourceTile);
		}
		
		return true;
	}
	
	/*
	 * Funkce slouzi pro otestovani prave zahraneho tahu hracem - je-li tah mozny.
	 */
	public void tryToExecute(ChessBoard board) throws InvalidMoveException 
	{
		BoardTile sourceTile = board.getBoardField(this.move.getSourcePosition());
		BoardTile destinationTile = board.getBoardField(this.move.getDestinationPosition());
		
		if(sourceTile.getFigure().canMoveTo(destinationTile) == false)
		{
			throw new InvalidMoveException("Takhle nelze hrat. Tah nebyl ulozen.");
		}
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
	
	public AbstractPiece getTakenEnemy() {
		return takenEnemy;
	}

	public void setTakenEnemy(AbstractPiece takenEnemy) {
		this.takenEnemy = takenEnemy;
	}
}
