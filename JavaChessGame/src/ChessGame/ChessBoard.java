package ChessGame;
import Figures.IBoardEntity;

public class ChessBoard {
	private IBoardEntity[][] board;
	private int width = 8;
	private int height = 8;
	
	private final Character initialSetup[][] = {
			{'V', 'J', 'S', 'D', 'K', 'S', 'J', 'V'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{'.', '.', '.', '.', '.', '.', '.', '.'},
			{'.', '.', '.', '.', '.', '.', '.', '.'},
			{'.', '.', '.', '.', '.', '.', '.', '.'},
			{'.', '.', '.', '.', '.', '.', '.', '.'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{'V', 'J', 'S', 'K', 'D', 'S', 'J', 'V'}
	};
	
	public ChessBoard()
	{
		FigureFactory figureBuilder = new FigureFactory();
		
		this.board = new IBoardEntity[width][height];
		
		for(int w = 0; w < width; w++)
		{
			for(int h = 0; h < height; h++)
			{
				this.board[w][h] = figureBuilder.createFigureByNotation(this.initialSetup[w][h]);
			}
		}
	}
	
	public void take(String srcPosition, String dstPosition)
	{
		// ???
	}
	
	public void swap(String srcPosition, String dstPosition)
	{
		// parse src position
		String src1 = Character.toString(srcPosition.charAt(0));
		src1 = src1.replace("a", "1");
		src1 = src1.replace("b", "2");
		src1 = src1.replace("c", "3");
		src1 = src1.replace("d", "4");
		src1 = src1.replace("e", "5");
		src1 = src1.replace("f", "6");
		src1 = src1.replace("g", "7");
		src1 = src1.replace("h", "8");	
		int src_x = Integer.parseInt(src1);

		String src2= Character.toString(srcPosition.charAt(1));		
		int src_y = Integer.parseInt(src2);
		
		// parse dst position
		String dst1 = Character.toString(dstPosition.charAt(0));
		dst1 = dst1.replace("a", "1");
		dst1 = dst1.replace("b", "2");
		dst1 = dst1.replace("c", "3");
		dst1 = dst1.replace("d", "4");
		dst1 = dst1.replace("e", "5");
		dst1 = dst1.replace("f", "6");
		dst1 = dst1.replace("g", "7");
		dst1 = dst1.replace("h", "8");	
		int dst_x = Integer.parseInt(dst1);

		String dst2= Character.toString(dstPosition.charAt(1));		
		int dst_y = Integer.parseInt(dst2);
		
		// swap
		IBoardEntity tmp = this.board[dst_y-1][dst_x-1];
		this.board[dst_y-1][dst_x-1] = this.board[src_y-1][src_x-1];
		this.board[src_y-1][src_x-1] = tmp;
	}
	
	public IBoardEntity getFigureOnPosition(String position)
	{		
		String src1 = Character.toString(position.charAt(0));
		src1 = src1.replace("a", "1");
		src1 = src1.replace("b", "2");
		src1 = src1.replace("c", "3");
		src1 = src1.replace("d", "4");
		src1 = src1.replace("e", "5");
		src1 = src1.replace("f", "6");
		src1 = src1.replace("g", "7");
		src1 = src1.replace("h", "8");
		int x = Integer.parseInt(src1);

		String src2= Character.toString(position.charAt(1));		
		int y = Integer.parseInt(src2);
				
		return this.board[y-1][x-1];
	}
	
	public void printBoard()
	{	
		for(int w = 0; w < 8; w++)
		{
			for(int h = 0; h < 8; h++)
			{
				System.out.print(this.board[w][h]);
			}
			System.out.print('\n');
		}
	}
}
