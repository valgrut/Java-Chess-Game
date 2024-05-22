package Loader;

/**
 * Interface for Readers of notation files. Classes that inherits from this interface must to implement all methods below.
 * @author xpeska05
 *
 */
public interface IReader 
{
	/**
	 * This method reads and returns next line from opened notation file.
	 * @return Next line from file.
	 */
	String getNextLine();
	
	/**
	 * Method prints whole file line by line to stdout.
	 */
	void printAll();
	
	/**
	 * Setter for input file name.
	 * @param inputFile Name of file to be set for procession.
	 */
	void setInput(String inputFile);
	
	/**
	 * Method opens file for reading.
	 */
	void openReader();
	
	/**
	 * Method closes opened file.
	 */
	void closeReader();
}
