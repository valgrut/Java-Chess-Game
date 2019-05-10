package GUI;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * @author root
 *
 */
public class PlayersMoveEvent extends Event 
{
    public static final EventType<PlayersMoveEvent> PLAYERS_MOVE = new EventType<>(Event.ANY, "PLAYERS_MOVE");
    
    String srcMove;
	String dstMove;
	
	private static final long serialVersionUID = 1L;

	/**
	 * @param eventType
	 */
	public PlayersMoveEvent(EventType<? extends Event> eventType) 
	{
		super(PLAYERS_MOVE);
	}
	
	/**
	 * @param srcmove
	 */
	public void setSrcMove(String srcmove) {this.srcMove = srcmove;}
	/**
	 * @param dstmove
	 */
	public void setDstMove(String dstmove) {this.dstMove = dstmove;}
	
	/**
	 * @return
	 */
	public String getSrcMove() {return this.srcMove;}
	/**
	 * @return
	 */
	public String getDstMove() {return this.dstMove;}
	
	
	/**
	 * @param handler
	 */
	public void invokeHandler(PlayersMoveEventHandler handler) {
        handler.onEvent1(srcMove);
    }
    
}
