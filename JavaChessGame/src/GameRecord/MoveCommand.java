package GameRecord;

import java.util.Vector;

import ChessGame.BoardTile;
import ChessGame.ChessBoard;
import Exceptions.InvalidMoveException;
import Figures.AbstractPiece;
import Figures.PieceColor;
import Loader.FigureFactory;

public class MoveCommand implements IMoveCommand {
	private MoveData moveData;
	private AbstractPiece takenEnemy;

	public MoveCommand(MoveData move)
	{
		this.moveData = move;
		takenEnemy = null;
	}
		
	/* (non-Javadoc)
	 * @see GameRecord.IMoveCommand#execute()
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
		
		//// kontrola, jestli po provedeni tahu nebude nepratelsky kral v sachu/ sachmat
		// ano-> nastavit v moveData polozku CHECK nebo CHECKMATE

		return true;
	}
	
	/* (non-Javadoc)
	 * @see GameRecord.IMoveCommand#revert()
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
	public void tryToExecute(ChessBoard board) throws InvalidMoveException 
	{
		BoardTile sourceTile = board.getBoardField(this.moveData.getSourcePosition());
		BoardTile destinationTile = board.getBoardField(this.moveData.getDestinationPosition());
		
		if(sourceTile.getFigure().canMoveTo(destinationTile) == false)
		{
			throw new InvalidMoveException("Takhle nelze hrat. Tah nebyl ulozen.");
		}
				
		// kontrola, jestli po provedeni tahu by nebyl kral teto barvy v ohrozeni.
		// pokud ANO - > throw InvalidMoveException("Kral by byl v sachu.").
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
		
		// check whether he is on check or checkmate and set it in moveData
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
		
		// TODO CHECKMATE
		// zkontrolovat, jestli to neni dokonce checkMATE
		// pri kazdy mozny tah krale zjistit, jestli tam nebude v ohrozeni
		// king.getPossibleMoves()   
		// for (possible_move : enemyking.getPossibleMoves())
		// {
		// 		zjistit, jestli na possible_move jako dst se muze pohnout nejaka z figurek opacne barvy nez enemy kral
		//		jestli na zadne z nich, kral je v bezpeci a je pouze v sachu
		//		jestli na vsechny z nich muze nepritel, tak je kral v sachmat.
		//		jestli je move.getSituation nastavene CHECKMATE, tak canMove vrati vyjimku a nelze se pohnout.
		// }
		
		if(moveData.getSituation() == MoveSituation.CHECK)
		{
			Vector<BoardTile> possibleMovesTiles = enemyKing.getPossibleMoves();
			int possibleMoveCount = possibleMovesTiles.size();
			
			/*
			for(BoardTile possibleMoveTile : possibleMovesTiles)
			{
				// TODO Poznamka: zde by sel aplikovat navrhovy vzor Filter !!! pro vyber specificke podmoziny dle podminky.
				for(AbstractPiece piece : board.getAllFigures())
				{
					if(piece.getColor() == currentMovePieceColor && ! piece.isCaptured())
					{
						System.out.println("Piece: " + piece.getColor().toString() + " " + piece.getNotation());
						BoardTile sourceEnemyTile = board.getBoardField(piece.getPosition().toString());
						
						if(sourceEnemyTile.getFigure().canMoveTo(possibleMoveTile))
						{
							System.out.println("possible Move --");
							--possibleMoveCount;
							break;
						}
					}
				}
			}
			if(possibleMoveCount == 0)
			{
				moveData.setSituation(MoveSituation.CHECKMATE);
				return;
			}
			*/
			
			/// verze 2
			
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
	
	
	/* (non-Javadoc)
	 * @see GameRecord.IMoveCommand#getMove()
	 */
	@Override
	public MoveData getMoveData() {
		return moveData;
	}

	/* (non-Javadoc)
	 * @see GameRecord.IMoveCommand#setMove(GameRecord.Move)
	 */
	@Override
	public void setMoveData(MoveData move) {
		this.moveData = move;
	}
	
	public AbstractPiece getTakenEnemy() {
		return takenEnemy;
	}

	public void setTakenEnemy(AbstractPiece takenEnemy) {
		this.takenEnemy = takenEnemy;
	}
}
