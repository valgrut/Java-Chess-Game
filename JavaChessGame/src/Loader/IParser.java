package Loader;

import GameRecord.Pair;

/**
 * @author xpeska05
 *
 */
public interface IParser 
{
	/**
	 * @param line
	 * @return
	 */
	Pair parseLine(String line);
}
