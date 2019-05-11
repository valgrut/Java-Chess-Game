package GameRecord;

/**
 * Class translates chess tile positions to coordinates and vice versa.
 * <p>
 * <b>Example:</b>
 * <pre>
 * 		c3 -> [3, 3]
 * 		b8 -> [2, 8]
 * 
 * 		[4, 2] -> d2
 * 		[1, 1] -> a1
 * </pre>
 * 
 * @author xpeska05
 */
public class PositionTranslator 
{	
	/**
	 * Method translates chess position to x,y coordinates.
	 * <p>
	 * <b>Example:</b>
	 * <pre>
	 * 		c3 -> [3, 3]
	 * 		b8 -> [2, 8]
	 * </pre>
	 * @param position Position in chess form (a3, h7, d1, ...).
	 * @return Pair of x,y coordinates.
	 */
	public static PairInt positionToCoords(String position)
	{
		char charcol = position.charAt(0);
		
		PairInt p = new PairInt(charToColumn(charcol), Character.getNumericValue(position.charAt(1)));
		return p;
	}

	/**
	 * Method translates column and row coordinates to chess position.
	 * <p>
	 * <b>Example:</b>
	 * <pre>
	 * 		[4, 2] -> d2
	 * 		[1, 1] -> a1
	 * </pre>
	 * @param col Column coordinate.
	 * @param row Row coordinate.
	 * @return Chess position (c1, f4, ...).
	 */
	public static String coordsToPosition(int col, int row)
	{
		assert(col > 0 && col <= 8);
		assert(row > 0 && row <= 8);
		
		return columnToChar(col) + Integer.toString(row);
	}
	
	/**
	 * Transforms number column to Character column.
	 * <p>
	 * <b>Example:</b>
	 * <pre>
	 * 		8 -> H
	 * 		1 -> A
	 * 	    3 -> C
	 * </pre>
	 * @param col Column that will be translated to character representation of column. Col is integer in range 1 to 8.
	 * @return Char representing the given column.
	 */
	private static char columnToChar(int col)
	{
		assert(col > 0 && col <= 8);
		String src1 = Integer.toString(col);
		src1 = src1.replace("1", "a");
		src1 = src1.replace("2", "b");
		src1 = src1.replace("3", "c");
		src1 = src1.replace("4", "d");
		src1 = src1.replace("5", "e");
		src1 = src1.replace("6", "f");
		src1 = src1.replace("7", "g");
		src1 = src1.replace("8", "h");

		return src1.charAt(0);
	}
	
	/**
	 * Transforms Character column to number column.
	 * <p>
	 * <b>Example:</b>
	 * <pre>
	 * 		H -> 8
	 * 		A -> 1
	 * 	    C -> 3
	 * </pre>
	 * @param charcol Char representing column.
	 * @return Column that corresponds with given character.
	 */
	private static int charToColumn(char charcol)
	{
		String src1 = Character.toString(charcol);
		src1 = src1.replace("a", "1");
		src1 = src1.replace("b", "2");
		src1 = src1.replace("c", "3");
		src1 = src1.replace("d", "4");
		src1 = src1.replace("e", "5");
		src1 = src1.replace("f", "6");
		src1 = src1.replace("g", "7");
		src1 = src1.replace("h", "8");
		int x = Integer.parseInt(src1);
		
		return x;
	}
}
