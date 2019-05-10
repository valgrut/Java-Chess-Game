package GUI;


import java.util.HashMap;
import java.util.Map;

import ChessGame.ChessBoard;
import Figures.AbstractPiece;
import GameRecord.PositionTranslator;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.effect.*;
import javafx.scene.image.*;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * This class represents part of the GUI, the ChessBoard.
 * Class is used for drawing pieces according to given board instance 
 * located in (retrieved from) backend.  
 * @author xpeska05
 */
public class Board extends GridPane
{
	/**
	 * 2D array representing chess board.
	 */
	private Tile[][] boardTiles;
	
	/**
	 * Map structure that holds 
	 * pairs of string,image, where key is string (ex.: BLACKV) 
	 * and value is png image path name.
	 */
	private Map<String, Image> chessFiguresImageMap;
	
	/**
	 * Attribute holds first (source) position of move.
	 */
	private String playersMove1 = "";
	
	/**
	 * Attribute holds second (destination) position of move.
	 */
	private String playersMove2 = "";
	
	/**
	 * Constructor of Board. Initializes 2D array of BoardTiles, sets background color
	 * of tiles according to chess board and sets event handling for
	 * catch mouse events.
	 */
	public Board()
	{
		super();
		
		boardTiles = new Tile[8][8];
		chessFiguresImageMap = new HashMap<String, Image>();
		loadPieceImages();
		
		setGridLinesVisible(true);
		//setEffect(new Glow());
		setWidth(400);
		setHeight(400);
		setMinSize(60*8, 60*8);
		
		setStyle("-fx-padding: 15 15 15 15");
		//setStyle("fx-border-color: brown; fx-border-width: 3px;");
		
		// Define an event filter event handler
		EventHandler<MouseEvent> filter = new EventHandler<MouseEvent>() 
		{
			public void handle(MouseEvent e) 
			{
				String clickPosition = e.getSource().toString().substring(8, 10);
				//System.out.println("Event catched: " + clickPosition);
				
				if(playersMove1.isEmpty())
				{
					//PairInt coords = PositionTranslator.positionToCoords(clickPosition);
					//if( ! boardTiles[coords.getSecond()-1][coords.getFirst()-1].isEmpty())
					//{
						playersMove1 = clickPosition;
						// TODO nastavit policka, kam figurka muze, na jinou barvu.
						
						//System.out.println(">>> Nelze presunout prazdne policko!!!!");
					//}
					//else
					//{
					//	System.out.println(">>> Nelze presunout prazdne policko!!!!");
					//}
				}
				else
				{
					// hrac chce odkliknout zvolenou figurku
					if(playersMove1 == clickPosition)
					{
						playersMove1 = "";
						// TODO ODNASTAVIT policka, kam figurka muze, na puvodni barvu.
						// policko musim ziskat prekladem z A3 na souradnice col, row a 
						// pristoupit do this.board[][]. Stejne tak i dale.
					}
					else
					{
						// Provedeni tahu
						playersMove2 = clickPosition;
						// TODO ODNASTAVIT policka, kam figurka muze, na puvodni barvu.
						
						
						// TODO provedeni tahu
						PlayersMoveEvent playersMoveEvent = new PlayersMoveEvent(Event.ANY);
						playersMoveEvent.setSrcMove(playersMove1);
						playersMoveEvent.setDstMove(playersMove2);
						fireEvent(playersMoveEvent);
						
						//deinicializace players moves
						playersMove1 = "";
						playersMove2 = "";
					}
					
				}
			};              
		};
		
		for (int row = 0; row < 8; row++) 
        {
            for (int col = 0; col < 8; col++)
            {
            	// col = {a..h}, row={1..8}
                Tile square = new Tile(PositionTranslator.coordsToPosition(col+1, 7-row+1));
                
                // register filter for this new Tile.
                square.addEventFilter(MouseEvent.MOUSE_PRESSED, filter);
 
            	boardTiles[row][col] = square;
                
                String color;
                if ((row + col) % 2 == 0) 
                {
                    color = "white";
                } 
                else 
                {
                    color = "yellowgreen";
                }
                square.setStyle("-fx-background-color: "+color+";");
                square.setCss("-fx-background-color: "+color+";");
                add(square, col, row);
            }
        }
		
		/*
		try 
		{
			update(board);
		} 
		catch (Exception e)
		{
			System.out.println("Error while update(): " + e.getMessage());
			e.printStackTrace();
		}
		*/
		
		/*
		 * TODO Zde asi bude potreba pridat nejaky filter nebo neco, a podle ID of Tile budu vedet, ktere bylo
		 * zmacknuto a z toho vezmu z ChessBoard intance figurku a moznosti, kam muze, a ty vyznacim do
		 * sachovnice.
		 */
		
		// Register an event filter for a single node and a specific event type
		/*
		addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() 
		{
			public void handle(MouseEvent e) { 
				System.out.println("Event catched: " + e.getSource());
			};              
		});
		*/
	}
	
	/**
	 * Initializes Map structure that maps keys to values, where 
	 * key is String and value is corresponding '.png' image name.
	 */
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
	
	/**
	 * Nacte a aplikuje zmeny v zavislosti na stavu this.board
	 * @param board ChessBoard instance from backend used as model for placing figures.
	 * @throws Exception Thrown when error occurs during update.
	 */
	public void update(ChessBoard board) throws Exception 
	{
		if(board == null)
		{
			throw new Exception("Board neni inicializovan (null).");
		}
		
		for(int row = 1; row <= board.getSize(); row++)
		{
			for(int col = 1; col <= board.getSize(); col++)
			{
				AbstractPiece figure = board.getField(col, row).getFigure();
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
	
	/**
	 * Method used for setting the correct image of piece inside Tile located on given col and row.
	 * @param col Column of Tile in 2D array of BoardTiles.
	 * @param row Row of Tile in 2D array of BoardTiles.
	 * @param piece Image of piece that will be placed to given position.
	 */
	private void setImageOn(int col, int row, Image piece)
	{	
	    boardTiles[col][row].setImage(piece); 
	}
	
}
