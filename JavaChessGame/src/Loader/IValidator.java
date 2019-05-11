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
 * Interface for validators of notation files.
 * @author xpeska05
 *
 */
public interface IValidator 
{
	/**
	 * This method takes one line of notation and validates it.
	 * <p>
	 * <b>Example:</b>
	 * <pre>
	 * 	1. Jc3d5 h2xg3V+
	 * 	2. a2a4+ Vc2c6
	 * </pre>
	 * @param line Line of notation to be validated.
	 * @return Result of validation.
	 */
	boolean validateLine(String line);
	
	/**
	 * This method takes notation move and validates it.
	 * <p>
	 * <b>Example:</b>
	 * <pre>
	 * 	Jc3d5
	 * 	h2xg3V+
	 * 	a2a4+ 
	 * 	Vc2c6
	 * </pre>
	 * @param move Move notation to be validated.
	 * @return Result of validation.
	 */
	boolean validateMove(String move);
}
