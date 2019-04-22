package GameSaver;

public class NotationBuilderFactory 
{
	public static INotationBuilder createNotationBuilder(NotationType notationType)
	{
		switch(notationType)
		{
		case SHORT:
			return new ShortNotationBuilder();
			
		case LONG:
			return new LongNotationBuilder();
		
		default:
			return null;
		}
	}
}
