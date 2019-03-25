import ChessGame.TerminalUserInterface;

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
		//ChessBoard board = new ChessBoard();
		//board.printBoard();
		
		TerminalUserInterface UI = new TerminalUserInterface();
		UI.GameLoop();
		
	}

}
