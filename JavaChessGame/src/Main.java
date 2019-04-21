import java.util.Vector;

import ChessGame.BoardTile;
import ChessGame.ChessBoard;
import ChessGame.TerminalUserInterface;
import ChessGame.BoardTile.Direction;
import Figures.EmptyPlace;
import Figures.PieceColor;
import GameRecord.BasicGameRecord;
import GameRecord.GameRecord;
import GameRecord.MoveData;
import GameRecord.PositionTranslator;
import Loader.GameLoader;
import GameRecord.MoveCommand;

public class Main {

	/*
	 * pozn... pri cteni notace se naparsovana notace bude vkladat do vectoru a postupne cist.
	 * pri ucivatelskem tahu se vytvori alternativni pole zacinajici na teto pozici.
	 * 
	 * 
	 * TODO : udelat unit testy pro parser a validator
	 * TODO : refactor, jeste promyslet ty tahy atd...
	 * TODO : nekam dat dve metody, ktere prevadi chessBoardPozici na x,y a naopak.   e3-> 5,3 a_naopak.
	 * TODO : pridat do figurek konstruktory s pozici, pridat atribut zdedeny Pozice
	 * TODO : opravit Figure Builder
	 * TODO : rozhranni IBoard a implementovat ChessBoard, pripadne moznost pouzit abstraktni tridu jeste, to samo u figurek
	 * 
	 * 
	 * TODO: Cil: jednoduche vkladani figurek do board
	 * - zmena pozice figurky - BoardManager nebo GameManager co bude cist notaci a hybat figurkama.
	 * --- zmena pozice figurky na zaklade jeji pozice a jeji moznosti pohybu.
	 * ----- zmena pozice figurky na zaklade notace!!!
	 * 
	 * - jednoduche vytvoreni IBoard = new ChessBoard  // Chess board bude mit natvrdo v konstuktoru velikost 8x8.
	 * 
	 * 
	 * figure.move(dest_position) throw IllegalMove;
	 * 
	 * TODO: Nejaka trida obsahuje posloupnost Tahuu a obsahuje moznosti nextMove() atd..
	 * 
	 * 
	 * Validator zakladni regex dlouhe notace bez x,+,...
	 *     \d.\s[D K S V J]?([a-h][1-8]){2}\s[D K S V J]?([a-h][1-8]){2}
	 *     
	 * Validator zakladni regex kratke notace bez x,+,...
	 * 	  	\d.\s[D K S V J]?([a-h][1-8]){1,2}\s[D K S V J]?([a-h][1-8]){1,2}
	 * 
	 *  Validator jednoho sub-tahu !!!
	 *  	^(([VJSDK]x?[a-h1-8]?[a-h][1-8])|([a-h1-8]?x?[a-h][1-8]))[VJSDK]?[+#]?$
	 *  
	 *  Validator radku notace !!!
	 *     ^\d+.\s[a-zA-Z0-8+#]{2,8}\s[a-zA-Z0-8+#]{2,8}$
	 */
	
	public static void main(String[] args) 
	{
		
		TerminalUserInterface UI = new TerminalUserInterface();
		UI.GameLoop();
		
		/*
		ChessBoard board = new ChessBoard();
		board.printBoard();
		*/
		
		/*
		BoardTile g11 = board.getBoardField(5, 1); // a2 (col, row)
		System.out.println("Color: " + g11.getFigure().getColor());
		System.out.println("Notation: " + g11.getFigure().getNotation());
		System.out.println("ColX " + g11.getChessColX() + "    RowY " + g11.getChessRowY());
		//System.out.println("Down: " + g11.nextField(Direction.D).getFigure().getNotation());
		//System.out.println("Up: " + g11.nextField(Direction.U).getFigure().getNotation());
		//System.out.println("Left: " + g11.nextField(Direction.L).getFigure().getNotation());
		//System.out.println("Right: " + g11.nextField(Direction.R).getFigure().getNotation());
		//System.out.println("Up: " + g11.nextField(Direction.D).getColX() + " " + g11.nextField(Direction.D).getRowY());
		//System.out.println("Right: " + g11.nextField(Direction.L).getFigure().getNotation());
		
		System.out.println("");
		
		EmptyPlace em = new EmptyPlace();
		em.setNotation("x");
		
		g11.setFigure(em);
		board.printBoard();
		*/
		
		/* Test moznosti pohybu figurek, dle jejich barvy */
		/*
		BoardTile knightTile = board.getBoardField("c4");
		System.out.println(knightTile.getFigure().getNotation());
		knightTile.getFigure().setColor(PieceColor.WHITE);
		
		Vector<BoardTile> candidates = knightTile.getFigure().getPossibleMoves();
		for (BoardTile tile : candidates)
		{
			System.out.println(tile);
			tile.getFigure().setNotation("x");
		}
		board.printBoard();
		*/
		
		/*
		BoardTile testTile = board.getBoardField("e3");
		System.out.println(knightTile.getFigure().canMoveTo(testTile));
		System.out.println(knightTile.getFigure().move(testTile));
		board.printBoard();
		
		
		BasicGameRecord record = new BasicGameRecord();
		GameLoader loader = new GameLoader(record, "Notation_1");
		loader = null;
		System.out.println(record.getNextMove().getSourcePosition());
		*/
		
		/*
		for(int moveNumber = 1; moveNumber < 10; moveNumber++)
		{
			Move newMove = new Move();
			newMove.setMoveNumber(moveNumber);
			MoveCommand command = new MoveCommand(newMove);
			record.addMove(command);
				
		}
		*/
				
		//System.out.println("Fuck yeah");
	}

}
