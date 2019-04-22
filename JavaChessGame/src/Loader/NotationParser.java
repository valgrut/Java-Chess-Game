package Loader;

import Figures.PieceColor;
import GameRecord.MoveData;
import GameRecord.Pair;

public class NotationParser implements IParser 
{
	private IValidator notationValidator;
	
	public NotationParser()
	{
		notationValidator = new NotationValidator();
	}
	
	@Override
	public Pair parseLine(String line)
	{
		if( ! notationValidator.validateLine(line))
		{
			System.out.println("Invalid notation found. Parsing terminated.");
			//return null;
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
	 * Parsing of one sub-move ("Jh5+") and assigning of values to moveObj parameter.
	 * @param move
	 * @param moveObj - modified
	 */
	private void parseSubMove(String move, MoveData moveObj)
	{
		String figure = null;
		String dstPosition = null;
		String srcPosition = null;
		String [] others;
		
		int index = 0;
		//for(int index = 0; index < move.length(); index++)
		//{
			
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
			
			// urceni pozice src a dst - TODO: tohle je zatim natvrdo.
			srcPosition = Character.toString(move.charAt(index));
			srcPosition += move.charAt(index+1);
			dstPosition = Character.toString(move.charAt(index+2));
			dstPosition += move.charAt(index+3);
		//}
		
		moveObj.setFigure(figure);
		moveObj.setDestinationPosition(dstPosition);
		moveObj.setSourcePosition(srcPosition);
	}
	
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
	
	private Integer parseMoveNumber(String dotNumber)
	{
		String moveOrder = dotNumber.substring(0, dotNumber.length() - 1);
		
		//TODO validate
		
		Integer moveOrderInt = Integer.parseInt(moveOrder);
				
		return moveOrderInt;
	}
}
