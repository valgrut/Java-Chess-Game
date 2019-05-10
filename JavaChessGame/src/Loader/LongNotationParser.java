package Loader;

import Figures.PieceColor;
import GameRecord.MoveData;
import GameRecord.MoveSituation;
import GameRecord.Pair;

/*
 * Long notation parser
 * 
 */
/**
 * @author xpeska05
 *
 */
public class LongNotationParser implements IParser 
{
	private IValidator notationValidator;
	
	/**
	 * 
	 */
	public LongNotationParser()
	{
		notationValidator = new NotationValidator();
	}
	
	/**
	 * @see Loader.IParser#parseLine(java.lang.String)
	 */
	@Override
	public Pair parseLine(String line)
	{
		if( ! notationValidator.validateLine(line))
		{
			System.out.println("Invalid notation found. Parsing terminated.");
			//TODO throw InvalidNotationException
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
	 * @param move
	 * @param moveObj - modified
	 */
	private void parseSubMove(String move, MoveData moveObj)
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
		
		try 
		{
			if(move.charAt(index+4) == '+' || move.charAt(index+5) == '+' || move.charAt(index+6) == '+')
			{
				moveObj.setSituation(MoveSituation.CHECK);
			}
			else if(move.charAt(index+4) == '#' || move.charAt(index+5) == '#' || move.charAt(index+6) == '#')
			{
				moveObj.setSituation(MoveSituation.CHECKMATE);
			}
		} catch(Exception e) {}
				
		moveObj.setFigure(figure);
		moveObj.setDestinationPosition(dstPosition);
		moveObj.setSourcePosition(srcPosition);
	}
	
	/**
	 * @param line
	 * @return
	 */
	private String[] splitString(String line)
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
	 * @param dotNumber
	 * @return
	 */
	private Integer parseMoveNumber(String dotNumber)
	{
		String moveOrder = dotNumber.substring(0, dotNumber.length() - 1);
		
		//TODO validate
		
		Integer moveOrderInt = Integer.parseInt(moveOrder);
				
		return moveOrderInt;
	}
}
