package GameSaver;

/**
 * Factory that constructs Notation parser depending on given notation type.
 * @author xpeska05
 *
 */
public class NotationBuilderFactory 
{
	/**
	 * Factory method that constructs concrete notation parser according to given notation type.
	 * Notation type can be resolved during the validating and then this method can be used to get right instance of parser.
	 * <p>
	 * This method returns instance of long notation parser in default.
	 * @param notationType Type of notation in file that is read. 
	 * @return NotationBuilder instance according to given notation type parameter.
	 */
	public static INotationBuilder createNotationBuilder(NotationType notationType)
	{
		switch(notationType)
		{
		case SHORT:
			return new ShortNotationBuilder();
			
		case LONG:
			return new LongNotationBuilder();
		
		default: //return null;
			return new LongNotationBuilder();
		}
	}
}
