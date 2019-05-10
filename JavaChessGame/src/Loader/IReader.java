package Loader;

/**
 * @author xpeska05
 *
 */
public interface IReader 
{
	/**
	 * @return
	 */
	String getNextLine();
	
	/**
	 * 
	 */
	void printAll();
	
	/**
	 * @param inputFile
	 */
	void setInput(String inputFile);
	
	/**
	 * 
	 */
	void openReader();
	
	/**
	 * 
	 */
	void closeReader();
}
