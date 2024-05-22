package Loader;
import Figures.AbstractPiece;
import Figures.Bishop;
import Figures.EmptyPlace;
import Figures.King;
import Figures.Knight;
import Figures.Pawn;
import Figures.Queen;
import Figures.Rook;

/**
 * Factory class that is used for creating the Piece instances by given figureNotation character.
 * @author Jiri Peska
 *
 */
public class FigureFactory 
{
	/**
	 * Factory method that is used for creating the Piece instances by given figureNotation character.
	 * @param figureNotation Character representing figure in notation.
	 * @return New instance of figure according to given notation. Returns EmptyPlace on default.
	 */
	public static AbstractPiece createFigureByNotation(Character figureNotation) //, Color color, ChessPosition position)
	{
		switch(figureNotation)
		{
		case 'V':
			return new Rook();
		
		case 'J':
			return new Knight();
			
		case 'S':
			return new Bishop();
		
		case 'K':
			return new King();
		
		case 'D':
			return new Queen();
		
		case 'p':
			return new Pawn();
			
		default:
			return new EmptyPlace();
		}
	}
}
