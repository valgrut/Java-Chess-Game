package ChessGame;

import java.util.Scanner;

public class TerminalUserInterface {
	private GameManager gameManager;
	
	public TerminalUserInterface()
	{
		this.gameManager = new GameManager();
	}
	
	public void printMainMenu()
	{
		System.out.println("----MENU----");
		System.out.println("0. goto Active Game");
		System.out.println("1. Load Game (notation file)");
		System.out.println("2. Load new Game");
		System.out.println("3. Vypsat Aktivni hry (taby)");
		System.out.println("4. Nastavit hru jako aktivni");
		System.out.println("5. Ulozit aktivni hru");
		System.out.println("6. Ulozit a ukoncit aktivni hru");
		System.out.println("7. Zavrit bez ulozeni aktivni hru");
		System.out.println("8. Ukoncit aplikaci");
	}
	
	public void printGameMenu()
	{
		System.out.println("----Game MENU----");
		System.out.println("0. back to Main Menu");
		System.out.println("1. nextMove");
		System.out.println("2. prevMove");
		System.out.println("3. ToStart"); 
		System.out.println("4. ToEnd"); 
		System.out.println("5. goto tah X");
		System.out.println("6. hraj ty: (normalne napis src pozici a dst pozici.)");
		//System.out.println("7. ((autoplay))");
		System.out.println("8. Undo");
		System.out.println("9. Redo");
	}
	
	public void GameLoop()
	{
		int userInput = 0;
		Scanner reader = new Scanner(System.in);  // Reading from System.in

		String menuType = "MainMenu";
		while(userInput != 7)
		{	
			System.out.println("///////////////////////////////////////////////////////////////////");

			if(menuType == "MainMenu")
			{
				this.printMainMenu();
				System.out.println("Enter a number: ");
				userInput = reader.nextInt();
				
				switch(userInput)
				{
					case 0:
						menuType = "playGame";
						break;
					
					case 1:
						System.out.println("Enter a notation file: ");
						String notationFile = reader.next();
						this.gameManager.loadGame(notationFile);
						break;
					
					case 2:
						this.gameManager.createNewGame();
						break;
						
					case 3:
						System.out.printf("Pocet rozehranych her: %d (Index 0 az count-1)\n", this.gameManager.getOpenedGameCount());
						break;
						
					case 4:
						System.out.printf("Pocet rozehranych her: %d (Index 0 az count-1)\n", this.gameManager.getOpenedGameCount());
						int activeGame = reader.nextInt();
						this.gameManager.setActiveGame(activeGame);
						break;
						
					case 5:
						this.gameManager.saveGame();
						break;
						
					case 6:
						this.gameManager.saveGame();
						this.gameManager.closeGame();
						break;
						
					case 7:
						this.gameManager.closeGame();
						break;
						
					case 8:
						//this.gameManager.closeGame();
						this.gameManager = null;
						break;
				}
			}
			else
			{				
				this.printGameMenu();
				System.out.println("Enter a number: ");
				userInput = reader.nextInt();
				
				switch(userInput)
				{
					case 0:
						menuType = "MainMenu";
						break;
					
					case 1:
						this.gameManager.getActiveGame().stepForward();
						break;

					case 2:
						this.gameManager.getActiveGame().stepBackward();
						break;
						
					case 3:
						this.gameManager.getActiveGame().toStart();
						break;
						
					case 4:
						this.gameManager.getActiveGame().toEnd();
						break;
						
					case 5:
						int moveNumber = reader.nextInt();
						this.gameManager.getActiveGame().gotoMove(moveNumber);
						break;
						
					case 6:
						System.out.println("Enter source position: ");
						String sourcePosition = reader.next();
						// TODO tady by se mohlo pro zvolenou figurku vykrizkovat policka, na ktera muze jit.

						System.out.println("Enter destination position: ");
						String destinationPosition = reader.next();
						
						this.gameManager.getActiveGame().playersMove(sourcePosition, destinationPosition);
						break;
						
					case 7:
						System.out.println("Autoplay <NotImplemented>");
						break;
						
					case 8:
						this.gameManager.getActiveGame().undo();
						break;
						
					case 9:
						this.gameManager.getActiveGame().redo();
						break;
				}
				
				this.gameManager.printGameBoard();
			}
			
		}
		
		System.out.println("Aplikace bude ukoncena (8).");
		reader.close();
	}
}
