package Loader;

import java.util.regex.Pattern;

/*
 * TODO: pridat regex pro nekompletni radek - kdyz ma jen bily tah ale ne cerny.
 */

/**
 * @author xpeska05
 *
 */
public class NotationValidator implements IValidator
{
	private Pattern linePattern;
	private Pattern movePattern;
	
	/**
	 * 
	 */
	public NotationValidator()
	{
		linePattern = Pattern.compile("^\\d+.\\s[a-zA-Z0-8+#]{2,8}\\s[a-zA-Z0-8+#]{2,8}$");
		movePattern = Pattern.compile("^(([VJSDK]x?[a-h1-8]?[a-h][1-8])|([a-h1-8]?x?[a-h][1-8]))[VJSDK]?[+#]?$");
	}
	
	/**
	 * @see Loader.IValidator#validateLine(java.lang.String)
	 */
	@Override
	public boolean validateLine(String currentLine) 
	{
		return linePattern.matcher(currentLine).matches();  
	}

	/**
	 * @see Loader.IValidator#validateMove(java.lang.String)
	 */
	@Override
	public boolean validateMove(String currentMove) 
	{
		return movePattern.matcher(currentMove).matches();  
	}
	
}
