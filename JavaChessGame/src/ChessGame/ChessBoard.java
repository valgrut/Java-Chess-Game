package ChessGame;
import java.util.Vector;

import Figures.AbstractPiece;
import Figures.PieceColor;
import GameRecord.PairInt;
import GameRecord.PositionTranslator;
import Loader.FigureFactory;

/**
 * @author xpeska05
 *
 */
public class ChessBoard 
{
	/**
	 * 2D array of BoardTiles used for Tile access using coordinates x and y or column and row.
	 */
	private BoardTile[][] board;
	
	/**
	 * Width of board.
	 */
	private int width = 8;
	
	/**
	 * Height of board.
	 */
	private int height = 8;
	
	/**
	 * Vector of all chess figure objects.
	 */
	private Vector<AbstractPiece> allFigures;
	
	/**
	 * 2D array of characters. Represents initial setup of chessboard pieces. It is used for setting the pieces to their original positions during initialization.
	 */
	private final Character initialSetup[][] = {
			{'V', 'J', 'S', 'D', 'K', 'S', 'J', 'V'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{'.', '.', '.', '.', '.', '.', '.', '.'},
			{'.', '.', '.', '.', '.', '.', '.', '.'},
			{'.', '.', '.', '.', '.', '.', '.', '.'},
			{'.', '.', '.', '.', '.', '.', '.', '.'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{'V', 'J', 'S', 'D', 'K', 'S', 'J', 'V'}
	};
	
	/**
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

	/**
	 * Get Tile by coords , ex.: [1,1], [3,4], [7,2], numbered like in Chess from down left.
	 * @param col Index of column to array. Index has to be from range {1, 2, ..., 8}
	 * @param row Index of row to array. Index has to be from range {1, 2, ..., 8}
	 * @return BoardTile Tile located by col, row coordinates. Numbered from BOTTOM left!
	 */
	public BoardTile getBoardField(int col, int row) {
		// assert
		if((col > 0 && col <= getSize()) && (row > 0 && row <= getSize()))
		{
			return this.board[9-row-1][col-1];
		}
		return null;
	}

	/**
	 * Get Tile according to chess position, ex.: c3, a8, g4, etc.
	 * Numbered from left down tile.
	 * @param tile String representation of position according to chess positioning (ex.: c3, b8, ...).
	 * @return BoardTile Tile at position given by parameter.
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
	
	/**
	 * Get Tile by coords , ex.: [1,1], [3,4], [7,2].
	 * @param col Index of column to array. Index has to be from range {1, 2, ..., 8}
	 * @param row Index of row to array. Index has to be from range {1, 2, ..., 8}
	 * @return BoardTile Tile located by col, row coordinates. Numbered from top left!
	 */
	public BoardTile getField(int col, int row) 
	{
		// assert
		if((col > 0 && col <= getSize()) && (row > 0 && row <= getSize()))
		{
			return this.board[col-1][row-1];
		}
		return null;
	}
	
	/**
	 * Method is used during initialization of Board Tiles. For internal use only.
	 * @param activeField Tile of which surrounding is initialized.
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
	
	/**
	 * Method used for printing the board layout with currently placed figures to stdout.
	 */
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

	/**
	 * This method returns size of board.
	 * @return int Size of Board.
	 */
	public int getSize() 
	{
		return this.width;
	}
	
	
	/**
	 * Returns vector of all figures of chess (from pawns to kings, both colors).
	 * @return Vector<AbstractPiece> Vector of figures.
	 */
	public Vector<AbstractPiece> getAllFigures() 
	{
		return allFigures;
	}

}
