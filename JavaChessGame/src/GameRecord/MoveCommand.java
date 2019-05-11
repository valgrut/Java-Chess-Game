package GameRecord;

import java.util.Vector;

import ChessGame.BoardTile;
import ChessGame.ChessBoard;
import Exceptions.InvalidMoveException;
import Figures.AbstractPiece;
import Figures.PieceColor;
import Loader.FigureFactory;

/**
 * @author xpeska05
 * @see GameRecord.IMoveCommand
 */
public class MoveCommand implements IMoveCommand {
	/**
	 * Informations about this move. MoveData is needed for execute and revert operations.
	 */
	private MoveData moveData;
	
	/**
	 * Instance of Piece that has been taken in this concrete move.
	 */
	private AbstractPiece takenEnemy;

	/**
	 * Constructor. Initializes MoveData of this command, that will be used for execute and revert operations.
	 * @param move
	 */
	public MoveCommand(MoveData move)
	{
		this.moveData = move;
		takenEnemy = null;
	}
		
	/**
	 * @see GameRecord.IMoveCommand#execute(ChessGame.ChessBoard)
	 */
	@Override
	public boolean execute(ChessBoard board) throws Exception 
	{	
		BoardTile sourceTile = board.getBoardField(this.moveData.getSourcePosition());
		BoardTile destinationTile = board.getBoardField(this.moveData.getDestinationPosition());
		
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
			moveData.setTakenEnemy(enemy.getNotation());
			
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
	
	/**
	 * @see GameRecord.IMoveCommand#revert(ChessGame.ChessBoard)
	 */
	@Override
	public boolean revert(ChessBoard board)
	{	
		BoardTile destinationTile = board.getBoardField(this.moveData.getSourcePosition());
		BoardTile sourceTile = board.getBoardField(this.moveData.getDestinationPosition());

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
	/**
	 * Method tries to execute move according to MoveData and solves Kings checks and checkmates.
	 * @param board Board object that is used for check the situation.
	 * @throws InvalidMoveException
	 */
	public void tryToExecute(ChessBoard board) throws InvalidMoveException 
	{
		BoardTile sourceTile = board.getBoardField(this.moveData.getSourcePosition());
		BoardTile destinationTile = board.getBoardField(this.moveData.getDestinationPosition());
		
		if(sourceTile.getFigure().canMoveTo(destinationTile) == false)
		{
			throw new InvalidMoveException("Takhle nelze hrat. Tah nebyl ulozen.");
		}

		PieceColor currentMovePieceColor = this.moveData.getChessColor();

		// find this color king
		AbstractPiece king = null;
		for(AbstractPiece piece : board.getAllFigures())
		{
			if(piece.getColor() == currentMovePieceColor && piece.getNotation() == "K")
			{
				king = piece;
			}
		}
		
		// execute move
		try 
		{
			execute(board);
		} 
		catch (Exception e) {}
		
		// check, that king is safe after same color piece move
		BoardTile kingTile = board.getBoardField(king.getPosition().toString());
		for(AbstractPiece piece : board.getAllFigures())
		{
			if(piece.getColor() != currentMovePieceColor && ! piece.isCaptured())
			{
				BoardTile sourceEnemyTile = board.getBoardField(piece.getPosition().toString());
				
				if(sourceEnemyTile.getFigure().canMoveTo(kingTile))
				{
					revert(board);	
					throw new InvalidMoveException("Nelze! Tvuj Kral by byl ohrozen.");
				}
			}
		}
		
		// --- check whether enemys king is in check or checkmate ---
		// find other color king
		AbstractPiece enemyKing = null;
		for(AbstractPiece piece : board.getAllFigures())
		{
			if(piece.getColor() != currentMovePieceColor && piece.getNotation() == "K")
			{
				enemyKing = piece;
			}
		}
		
		// CHECK
		BoardTile enemyKingTile = board.getBoardField(enemyKing.getPosition().toString());
		for(AbstractPiece piece : board.getAllFigures())
		{
			if(piece.getColor() == currentMovePieceColor && ! piece.isCaptured())
			{
				BoardTile sourceEnemyTile = board.getBoardField(piece.getPosition().toString());
				
				if(sourceEnemyTile.getFigure().canMoveTo(enemyKingTile))
				{
					moveData.setSituation(MoveSituation.CHECK);
					break;
				}
			}
		}
		
		// CHECKMATE
		if(moveData.getSituation() == MoveSituation.CHECK)
		{
			Vector<BoardTile> possibleMovesTiles = enemyKing.getPossibleMoves();
			int possibleMoveCount = possibleMovesTiles.size();
			
			// create my move where dst is one of king.getPossibleMoves()
			MoveData kingPossibleMove = new MoveData();
			kingPossibleMove.setFigure(enemyKing.getNotation());
			kingPossibleMove.setChessColor(enemyKing.getColor());
			
			// budu muset pohnout s kralem na ta policka z getPossibleMoves();
			for(BoardTile possibleMoveTile : possibleMovesTiles)
			{
				// save Main move
				MoveData tmpMoveData = this.moveData;
				AbstractPiece tmpTakenEnemy = this.takenEnemy;
				
				kingPossibleMove.setDestinationPosition(possibleMoveTile.toString());
				kingPossibleMove.setSourcePosition(enemyKing.getPosition().toString());
				this.moveData = kingPossibleMove;
				this.takenEnemy = null;

				// execute this move
				try 
				{
					execute(board);
				} 
				catch (Exception e) {}
				
				// check if attacked in this current tmp Tile
				// TODO Poznamka: zde by sel aplikovat navrhovy vzor Filter !!! pro vyber specificke podmoziny dle podminky.
				for(AbstractPiece piece : board.getAllFigures())
				{
					if(piece.getColor() == currentMovePieceColor && ! piece.isCaptured())
					{
						BoardTile sourceEnemyTile = board.getBoardField(piece.getPosition().toString());
						
						if(sourceEnemyTile.getFigure().canMoveTo(possibleMoveTile))
						{
							--possibleMoveCount;
							break;
						}
					}
				}
				
				// revert move
				revert(board);
				
				// restore Main move
				this.moveData = tmpMoveData;
				this.takenEnemy = tmpTakenEnemy;
			}
			if(possibleMoveCount == 0)
			{
				moveData.setSituation(MoveSituation.CHECKMATE);
				return;
			}
		}
	}
	
	/**
	 * @see GameRecord.IMoveCommand#getMoveData()
	 */
	@Override
	public MoveData getMoveData() {
		return moveData;
	}

	/**
	 * @see GameRecord.IMoveCommand#setMoveData(GameRecord.MoveData)
	 */
	@Override
	public void setMoveData(MoveData move) {
		this.moveData = move;
	}
	
	/**
	 * @return
	 */
	public AbstractPiece getTakenEnemy() {
		return takenEnemy;
	}

	/**
	 * @param takenEnemy
	 */
	public void setTakenEnemy(AbstractPiece takenEnemy) {
		this.takenEnemy = takenEnemy;
	}
}
