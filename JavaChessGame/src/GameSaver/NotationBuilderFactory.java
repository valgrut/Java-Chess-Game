package GameSaver;

/**
 * @author xpeska05
 *
 */
public class NotationBuilderFactory 
{
	/**
	 * @param notationType
	 * @return NotationBuilder instance acording to given parameter
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
