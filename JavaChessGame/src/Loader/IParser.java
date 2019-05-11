package Loader;

import GameRecord.Pair;

/**
 * Interface for parsers of notation files. 
 * @author xpeska05
 *
 */
public interface IParser 
{
	/**
	 * Method will parse given line of notation file and created pair of strings representing 
	 * the white's move data and black's move data.
	 * @param line Line of notation from file.
	 * @return Pair of white's move data and black's move data.
	 * @throws InvalidNotationException 
	 */
	Pair parseLine(String line) throws InvalidNotationException;
}
