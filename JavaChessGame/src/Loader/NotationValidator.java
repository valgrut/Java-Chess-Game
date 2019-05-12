package Loader;

import java.util.regex.Pattern;

/*
 * TODO: pridat regex pro nekompletni radek - kdyz ma jen bily tah ale ne cerny.
 */

/**
 * Notation validator class is used for validating given notation lines or moves. For validation are used regular expressions for 
 * long moves, short moves and for lines.
 * 
 * @author xpeska05
 *
 */
public class NotationValidator implements IValidator
{
	/**
	 * Regex pattern for line validation.
	 */
	private Pattern linePattern;
	
	/**
	 * Regex for short move validation.
	 */
	private Pattern movePattern;
	
	/**
	 * Regex pattern for long line validation
	 */
	private Pattern lineLongPattern;
	/**
	 * Regex for long move validation
	 */
	private Pattern longMovePattern;
	
	/**
	 * Constructor of validator. Regex patterns are created here.
	 */
	public NotationValidator()
	{
		lineLongPattern = Pattern.compile("^\\d+.\\s[a-hVDKJSpx0-8+#]{4,8}\\s{0,1}([a-hVDKJSpx0-8+#]{4,8}){0,1}$");
		linePattern = Pattern.compile("^\\d+.\\s[a-hVDKJSpx0-8+#]{2,6}\\s{0,1}([a-hVDKJSpx0-8+#]{2,6}){0,1}$");
		movePattern = Pattern.compile("^(([VJSDK]x?[a-h1-8]?[a-h][1-8])|([a-h1-8]?x?[a-h][1-8]))[VJSDK]?[+#]?$");
		longMovePattern = Pattern.compile("^[VDKJSp]{0,1}[a-h1-8]{1,2}x[a-h1-8]{1,2}[VDKJSp]{0,1}[+#]{0,1}$");
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
	/**
	 * This function tests a long  move and return true if valid 
	 * @see Loader.IValidator#longvalidateMove(java.lang.String)
	 */
	public boolean longvalidateMove(String currentMove) 
	{
		return longMovePattern.matcher(currentMove).matches();  
	}
	/**
	 * This function tests a long  line and return true if valid 
	 * @see Loader.IValidator#longvalidateLine(java.lang.String)
	 */
	public boolean longvalidateLine(String currentLine) 
	{
		return lineLongPattern.matcher(currentLine).matches();  
	}
	
}
