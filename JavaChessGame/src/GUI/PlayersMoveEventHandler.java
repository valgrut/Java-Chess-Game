package GUI;

import javafx.event.EventHandler;

/**
 * @author root
 *
 */
public abstract class PlayersMoveEventHandler implements EventHandler<PlayersMoveEvent> {

    /**
     * @param srcMove
     */
    public void onEvent1(String srcMove) 
    {
    	
    }

    /**
     * @param dstMove
     */
    public void onEvent2(String dstMove)
    {
    	
    }
    
    /**
     * @see javafx.event.EventHandler#handle(javafx.event.Event)
     */
    @Override
    public void handle(PlayersMoveEvent event) {
        event.invokeHandler(this);
    }
}
