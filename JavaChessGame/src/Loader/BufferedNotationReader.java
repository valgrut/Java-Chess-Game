package Loader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * @author xpeska05
 *
 */
public class BufferedNotationReader implements IReader 
{
	private BufferedReader reader;
	private String inputFilePath;

	/**
	 * @author Jiri Peska
	 * @param input file path
	 * @see Constructor, creates instance of BufferedReader
	 */
	/**
	 * @param input
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
	 * @return
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
