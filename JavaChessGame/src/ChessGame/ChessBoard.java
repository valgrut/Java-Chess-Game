package ChessGame;
import java.util.Vector;

import Figures.AbstractPiece;
import Figures.PieceColor;
import GameRecord.PairInt;
import GameRecord.PositionTranslator;
import Loader.FigureFactory;

public class ChessBoard {
	private BoardTile[][] board;
	private int width = 8;
	private int height = 8;
	
	private Vector<AbstractPiece> allFigures;
	
	private final Character initialSetup[][] = {
			{'V', 'J', 'S', 'D', 'K', 'S', 'J', 'V'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{'.', '.', '.', '.', '.', '.', '.', '.'},
			{'.', '.', '.', '.', '.', '.', '.', '.'},
			{'.', '.', 'S', '.', '.', '.', '.', '.'},
			{'.', '.', '.', '.', '.', '.', '.', '.'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{'V', 'J', 'S', 'K', 'D', 'S', 'J', 'V'}
	};
	
	/*
	 * initializes board tiles, sets pieces to corresponding tiles and creates vector of pieces.
	 */
	public ChessBoard()
	{		
		this.board = new BoardTile[width][height];
		this.allFigures = new Vector<AbstractPiece>();
		
		for(int row = 0; row < 8; row++)
		{
			for(int col = 0; col < 8; col++)
			{
				AbstractPiece newFigure = FigureFactory.createFigureByNotation(this.initialSetup[row][col]);
				if(row > 5)
				{
					newFigure.setColor(PieceColor.WHITE);
				}
				if(row < 2)
				{
					newFigure.setColor(PieceColor.BLACK);
				}
				
				BoardTile newTile = new BoardTile(row+1, col+1); 
				newFigure.setPosition(newTile);
				newTile.setFigure(newFigure);
				
				this.board[row][col] = newTile;
				
				if(newFigure.isMovable() == true)
				{
					allFigures.add(newFigure);
				}
			}
		}
		
		/* Initializes surrounding of tiles */
		for(int row = 1; row <= getSize(); row++)
		{
			for(int col = 1; col <= getSize(); col++)
			{
				BoardTile activeField = getField(col, row);
				setSurroundingOfField(activeField);
			}
		}
	}
	
	/*
	 * returns BoardTile located on col and row, counted from bottom left
	 */
	public BoardTile getBoardField(int col, int row) {
		// assert
		if((col > 0 && col <= getSize()) && (row > 0 && row <= getSize()))
		{
			return this.board[9-row-1][col-1];
		}
		return null;
	}
	
	/*
	 * Get Tile by notation position, ex.: c3, a8, g4, ...
	 */
	public BoardTile getBoardField(String tile) {
		PairInt coords = PositionTranslator.positionToCoords(tile);
		int col = coords.getFirst();
		int row = coords.getSecond();
		
		if((col > 0 && col <= getSize()) && (row > 0 && row <= getSize()))
		{
			return this.board[9-row-1][col-1];
		}
		
		return null;
	}
	
	/*
	 * Get Tile by coords , ex.: [1,1], [3,4], [7,2], ...
	 */
	private BoardTile getField(int col, int row) {
		// assert
		if((col > 0 && col <= getSize()) && (row > 0 && row <= getSize()))
		{
			return this.board[col-1][row-1];
		}
		return null;
	}
	
	/*
	 * Method used during initialization of Board Tiles
	 */
	private void setSurroundingOfField(BoardTile activeField) 
	{	
		int row = activeField.getArrayRowY();
		int col = activeField.getArrayColX();
		
		activeField.addNextField(BoardTile.Direction.R,  getField(col, row+1));
		activeField.addNextField(BoardTile.Direction.RU, getField(col-1, row+1));
		activeField.addNextField(BoardTile.Direction.U,  getField(col-1, row));
		activeField.addNextField(BoardTile.Direction.LU, getField(col-1, row-1));
		activeField.addNextField(BoardTile.Direction.L,  getField(col, row-1));
		activeField.addNextField(BoardTile.Direction.LD, getField(col+1, row-1));
		activeField.addNextField(BoardTile.Direction.D,  getField(col+1, row));
		activeField.addNextField(BoardTile.Direction.RD, getField(col+1, row+1)); 
	}
	
	public void printBoard()
	{	
		/*
		System.out.print("  ");
		for(char col = 'a'; col <= 'h'; col++)
			System.out.print(col);
		System.out.print('\n');
		*/
		
		/*
		System.out.print("  ");
		for(int h = 0; h < 8; h++)
			System.out.print('|');
		System.out.print('\n');
		 */
			
		for(int w = 0; w < 8; w++)
		{
			System.out.print( 8-w + "|");
			for(int h = 0; h < 8; h++)
			{
				System.out.print(this.board[w][h].getFigure());
			}
			System.out.print('\n');
		}
		
		System.out.print("  ");
		for(char col = 'A'; col <= 'H'; col++)
			System.out.print(col);
		System.out.print('\n');
		System.out.print('\n');
	}

	public int getSize() {
		return this.width;
	}


}
