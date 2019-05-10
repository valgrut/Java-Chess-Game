import ChessGame.TerminalUserInterface;

/**
 * Terminal user interface and entry point to application when Terminal user interface 
 * is required or wanted. 
 * User can control the game from terminal through this class or from GUI.
 * @author xpeska05
 * @see UserInterfaceMain
 */
public class Main 
{
	/**
	 * @param args Arguments given from terminal.
	 */
	public static void main(String[] args) 
	{
		TerminalUserInterface UI = new TerminalUserInterface();
		UI.GameLoop();		
	}

}
