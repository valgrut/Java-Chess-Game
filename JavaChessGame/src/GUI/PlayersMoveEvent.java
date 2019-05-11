package GUI;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * Event representing players own move that holds source tile and destination tile of move.
 * 
 * @author xpeska05
 */
public class PlayersMoveEvent extends Event 
{	
	private static final long serialVersionUID = 1L;

    /**
     * Event type specification.
     */
    public static final EventType<PlayersMoveEvent> PLAYERS_MOVE = new EventType<>(Event.ANY, "PLAYERS_MOVE");
    
    /**
     * Source position of held move in notation form (a3,).
     */
    String srcMove;
	
    /**
	 * Destination position of held move in notation form (a3,).
	 */
	String dstMove;
	
	/**
	 * Constructor of event.
	 * @param eventType Type of event.
	 */
	public PlayersMoveEvent(EventType<? extends Event> eventType) 
	{
		super(PLAYERS_MOVE);
	}
	
	/**
	 * Setter for srcMove attribute.
	 * @param srcmove Source position of move.
	 */
	public void setSrcMove(String srcmove) {this.srcMove = srcmove;}
	
	/**
	 * Setter for dstMove attribute.
	 * @param dstmove Destination position of move.
	 */
	public void setDstMove(String dstmove) {this.dstMove = dstmove;}
	
	/**
	 * Getter for srcMove attribute.
	 * @return Source position of move.
	 */
	public String getSrcMove() {return this.srcMove;}
	
	/**
	 * Getter for dstMove attribute.
	 * @return Destination position of move.
	 */
	public String getDstMove() {return this.dstMove;}
	
	/**
	 * Sets event method that is called after event invocation
	 * @param handler EventHandler
	 */
	public void invokeHandler(PlayersMoveEventHandler handler) {
        handler.onEvent1(srcMove);
    }
}
