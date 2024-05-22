package Loader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * Class that handles reading from notation file. Contains opened file and is able to read the input file line by line.
 * These lines can be provided for further processing. This concrete class uses Buffered reader for handling the files and reading.
 * @author xpeska05
 *
 */
public class BufferedNotationReader implements IReader 
{
	/**
	 * Instance of buffered reader.
	 */
	private BufferedReader reader;
	
	/**
	 * Name of the file that is handled by this class.
	 */
	private String inputFilePath;

	/**
	 * @param input Name of file that will be opened.
	 * @see Constructor, creates instance of BufferedReader.
	 */
	public BufferedNotationReader(String input)
	{
		this.setInput(input);
		this.openReader();
	}
	
	/**
	 * @see Loader.IReader#getNextLine()
	 */
	@Override
	public String getNextLine() {
		try 
		{	
			return this.reader.readLine();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		this.closeReader();
		return null;
	}

	/**
	 * @see Loader.IReader#setInput(java.lang.String)
	 */
	@Override
	public void setInput(String inputFile) {
		this.inputFilePath = inputFile;
	}
	
	/**
	 * Method returns input file path.
	 * @return Input file path.
	 */
	public String getInput()
	{
		return this.inputFilePath;
	}

	/**
	 * @see Loader.IReader#openReader()
	 */
	public void openReader()
	{
		if(this.reader == null)
		{
			try
			{
				this.reader = new BufferedReader(new FileReader(this.getInput()));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @see Loader.IReader#closeReader()
	 */
	public void closeReader()
	{
		if(this.reader != null)
		{
			try {
				this.reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see Loader.IReader#printAll()
	 */
	@Override
	public void printAll() {
		try 
		{			
			this.openReader();
			String line = this.reader.readLine();
			while(line != null)
			{
				System.out.println(line);
				line = this.reader.readLine();
			}
			this.closeReader();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
}
