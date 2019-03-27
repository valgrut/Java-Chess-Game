package Loader;

import java.util.regex.Pattern;

public class NotationValidator implements IValidator{
	private Pattern linePattern;
	private Pattern movePattern;
	
	public NotationValidator()
	{
		linePattern = Pattern.compile("^\\d+.\\s[a-zA-Z0-8+#]{2,8}\\s[a-zA-Z0-8+#]{2,8}$");
		movePattern = Pattern.compile("^(([VJSDK]x?[a-h1-8]?[a-h][1-8])|([a-h1-8]?x?[a-h][1-8]))[VJSDK]?[+#]?$");
	}
	
	@Override
	public boolean validateLine(String currentLine) {
		return linePattern.matcher(currentLine).matches();  
	}

	@Override
	public boolean validateMove(String currentMove) {
		return movePattern.matcher(currentMove).matches();  
	}
	
}
