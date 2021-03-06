package Loader;

import Figures.PieceColor;
import GameRecord.MoveData;
import GameRecord.MoveSituation;
import GameRecord.Pair;

/**
 * Class implementing IParser is used for parsing Long Notation files.
 * @author xpeska05, xhalmo00
 *
 */
public class LongNotationParser implements IParser 
{
	private IValidator notationValidator;
	
	/**
	 * Constructor. Creates NotationValidator instance used for validating the long notation line.
	 */
	public LongNotationParser()
	{
		notationValidator = new NotationValidator();
	}
	
	/**
	 * @throws InvalidNotationException Exception thrown when invalid move detected in notation.
	 * @see Loader.IParser#parseLine(java.lang.String)
	 */
	@Override
	public Pair parseLine(String line) throws InvalidNotationException
	{
		if( ! notationValidator.longvalidateLine(line))
		{
			throw new InvalidNotationException("Invalid notation found. Parsing terminated.");
		}
		
		String partials[] = this.splitString(line);
		
		MoveData whiteMove = new MoveData();
		MoveData blackMove = new MoveData();
		
		Integer moveNumber = this.parseMoveNumber(partials[0]);
		whiteMove.setMoveNumber(moveNumber);
		whiteMove.setChessColor(PieceColor.WHITE);
		blackMove.setMoveNumber(moveNumber);
		blackMove.setChessColor(PieceColor.BLACK);
		
		this.parseSubMove(partials[1], whiteMove);
		try 
		{
			this.parseSubMove(partials[2], blackMove);
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("Nelze nacist tah cerneho, neexistuje.");
			blackMove = null;
		}
		
		Pair newMove = new Pair(whiteMove, blackMove);
		return newMove;
	}

	/**
	 * Parsing of one sub-move ("[J]h5[x]g7[D][+|#]") and assigning of values to moveObj parameter.
	 * @param move White's or black's notation move.
	 * @param moveObj MoveData object that will be initialized with values parsed from given string move notation.
	 */
	public void parseSubMove(String move, MoveData moveObj)
	{
		String figure = null;
		String dstPosition = null;
		String srcPosition = null;
		
		int index = 0;
			
		Character currentChar = move.charAt(index);
		if(Character.isUpperCase(currentChar))
		{
			figure = currentChar.toString();
			index++;
		}
		else
		{
			figure = "p";
		}
		
		srcPosition = Character.toString(move.charAt(index));
		srcPosition += move.charAt(index+1);
		
		// kontrola pokud v tahu byl nekdo vzat - Jd3xc5  (to pismeno 'x')
		if(move.charAt(index+2) == 'x')
		{
			moveObj.setTakenEnemy(""+ move.charAt(index+5));
			index++;
		}			
		
		dstPosition = Character.toString(move.charAt(index+2));
		dstPosition += move.charAt(index+3);
		
		if(move.charAt(move.length()-1) == '+')
		{
			moveObj.setSituation(MoveSituation.CHECK);
		}
		else if(move.charAt(move.length()-1) == '#')
		{
			moveObj.setSituation(MoveSituation.CHECKMATE);
		}
				
		moveObj.setFigure(figure);
		moveObj.setDestinationPosition(dstPosition);
		moveObj.setSourcePosition(srcPosition);
	}
	
	/**
	 * Method takes line of notation and split it by spaces.
	 * @param line Line of notation that will be split by spaces.
	 * @return Array of 3 strings, where first is move number, second is white's move and third is black's move.
	 */
	public String[] splitString(String line)
	{
		if(line.isEmpty())
		{
			return null;
		}
		else
		{
			return line.split(" ");
		}
	}
	
	/**
	 * Method reads move number from given notation record.
	 * @param dotNumber String with move number information.
	 * @return Parsed number of move.
	 */
	private Integer parseMoveNumber(String dotNumber)
	{
		String moveOrder = dotNumber.substring(0, dotNumber.length() - 1);
		
		//TODO validate
		
		Integer moveOrderInt = Integer.parseInt(moveOrder);
				
		return moveOrderInt;
	}
}
