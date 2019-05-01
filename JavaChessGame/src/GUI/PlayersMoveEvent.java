package GUI;

import javafx.event.Event;
import javafx.event.EventType;

public class PlayersMoveEvent extends Event 
{
    public static final EventType<PlayersMoveEvent> PLAYERS_MOVE = new EventType<>(Event.ANY, "PLAYERS_MOVE");
    
    String srcMove;
	String dstMove;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlayersMoveEvent(EventType<? extends Event> eventType) 
	{
		super(PLAYERS_MOVE);
	}
	
	public void setSrcMove(String srcmove) {this.srcMove = srcmove;}
	public void setDstMove(String dstmove) {this.dstMove = dstmove;}
	
	public String getSrcMove() {return this.srcMove;}
	public String getDstMove() {return this.dstMove;}
	
	
	public void invokeHandler(PlayersMoveEventHandler handler) {
        handler.onEvent1(srcMove);
    }
    
}
