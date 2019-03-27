package GameRecord;

public class PositionTranslator {
	
	public static PairInt positionToCoords(String position)
	{
		char charcol = position.charAt(0);
		
		PairInt p = new PairInt(charToColumn(charcol), Character.getNumericValue(position.charAt(1)));
		return p;
	}

	
	public static String coordsToPosition(int col, int row)
	{
		assert(col > 0 && col <= 8);
		assert(row > 0 && row <= 8);
		
		return columnToChar(col) + Integer.toString(row);
	}
	
	/*
	 * Transforms number column to Character column
	 * Input: integer in range from 1 to 8
	 * Output: character representing column
	 * Example: 8 -> H,   1 -> A,   3 -> C
	 */
	public static char columnToChar(int col)
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
	
	public static int charToColumn(char charcol)
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
