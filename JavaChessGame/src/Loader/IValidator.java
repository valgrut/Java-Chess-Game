package Loader;

/*
 * Validator zakladni regex dlouhe notace bez x,+,...
 *     \d.\s[D K S V J]?([a-h][1-8]){2}\s[D K S V J]?([a-h][1-8]){2}
 *     
 * Validator zakladni regex kratke notace bez x,+,...
 * 	  	\d.\s[D K S V J]?([a-h][1-8]){1,2}\s[D K S V J]?([a-h][1-8]){1,2}
 * 
 *  Validator jednoho sub-tahu !!!
 *  	^(([VJSDK]x?[a-h1-8]?[a-h][1-8])|([a-h1-8]?x?[a-h][1-8]))[VJSDK]?[+#]?$
 *  
 *  Validator radku notace !!!
 *     ^\d+.\s[a-zA-Z0-8+#]{2,8}\s[a-zA-Z0-8+#]{2,8}$
 */

/**
 * @author xpeska05
 *
 */
public interface IValidator 
{
	/**
	 * @param line
	 * @return
	 */
	boolean validateLine(String line);
	
	/**
	 * @param move
	 * @return
	 */
	boolean validateMove(String move);
}
