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
 * @author Jiri Peska
 *
 */
public class FigureFactory {
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
