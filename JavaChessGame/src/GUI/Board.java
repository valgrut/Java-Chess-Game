package GUI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import ChessGame.ChessBoard;
import Figures.AbstractPiece;
import GameRecord.PositionTranslator;
import javafx.beans.binding.SetBinding;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.effect.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

public class Board extends GridPane //TilePane
{
	private Tile[][] boardTiles;
	private ChessBoard board;
	private Map<String, Image> chessFiguresImageMap;
	
	public Board(ChessBoard board)
	{
		super();
	
		assert(board != null);
		this.board = board;
		
		boardTiles = new Tile[8][8];
		chessFiguresImageMap = new HashMap<String, Image>();
		loadPieceImages();
		
		setGridLinesVisible(true);
		//setEffect(new Glow());
		setWidth(400);
		setHeight(400);
		setMinSize(60*8, 60*8);
		
		setStyle("fx-border-color: brown; fx-border-width: 3px;");
		
		for (int row = 0; row < 8; row++) 
        {
            for (int col = 0; col < 8; col++)
            {
                //Tile square = new Tile(PositionTranslator.coordsToPosition(col, row));
            	Tile square = new Tile("A X");
            	boardTiles[row][col] = square;
                
                String color;
                if ((row + col) % 2 == 0) 
                {
                    color = "white";
                } 
                else 
                {
                    color = "gray";
                }
                square.setStyle("-fx-background-color: "+color+";");
                add(square, col, row);
            }
        }
		
		try 
		{
			update();
		} 
		catch (Exception e) 
		{
			System.out.println("Error while update(): " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void loadPieceImages()
	{
		String path = "/img/";

		chessFiguresImageMap.put("BLACKS", new Image(path + "Chess_bdt60.png"));
		chessFiguresImageMap.put("WHITES", new Image(path + "Chess_blt60.png"));

		chessFiguresImageMap.put("BLACKJ", new Image(path + "Chess_ndt60.png"));
		chessFiguresImageMap.put("WHITEJ", new Image(path + "Chess_nlt60.png"));

		chessFiguresImageMap.put("BLACKV", new Image(path + "Chess_rdt60.png"));
		chessFiguresImageMap.put("WHITEV", new Image(path + "Chess_rlt60.png"));

		chessFiguresImageMap.put("BLACKK", new Image(path + "Chess_kdt60.png"));
		chessFiguresImageMap.put("WHITEK", new Image(path + "Chess_klt60.png"));

		chessFiguresImageMap.put("BLACKD", new Image(path + "Chess_qdt60.png"));
		chessFiguresImageMap.put("WHITED", new Image(path + "Chess_qlt60.png"));

		chessFiguresImageMap.put("BLACKp", new Image(path + "Chess_pdt60.png"));
		chessFiguresImageMap.put("WHITEp", new Image(path + "Chess_plt60.png"));
	}

	public void setBoard(ChessBoard board)
	{
		this.board = board;
	}
	
	/**
	 * Nacte a aplikuje zmeny v zavislosti na stavu this.board
	 * @throws Exception 
	 */
	public void update() throws Exception 
	{
		if(this.board == null)
		{
			throw new Exception("Board neni inicializovan (null).");
		}
		
		for(int row = 1; row <= this.board.getSize(); row++)
		{
			for(int col = 1; col <= this.board.getSize(); col++)
			{
				AbstractPiece figure = this.board.getField(col, row).getFigure();
				if(figure.getNotation() != ".")
				{
					try 
					{
						String pieceColor = figure.getColor().toString();
						String pieceNotation = figure.getNotation();
						String pieceKey = pieceColor + pieceNotation;
						
						//System.out.println(pieceKey);
						Image currentPiece = this.chessFiguresImageMap.get(pieceKey);
	
						setImageOn(col-1, row-1, currentPiece);
					} 
					catch(java.lang.NullPointerException e)
					{
						//System.out.println("Vyjimka behem zjistovani figurky: ");
					}
				}
				else
				{
					setImageOn(col-1, row-1, null);
				}
			}	
		}
	}
	
	private void setImageOn(int col, int row, Image piece)
	{	
		boardTiles[col][row].setImage(null);
	    boardTiles[col][row].setImage(piece); 
	}
	
	
}
