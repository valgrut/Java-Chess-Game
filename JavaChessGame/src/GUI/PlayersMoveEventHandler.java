package GUI;

import javafx.event.EventHandler;

/**
 * Class represents custom event handler.
 * @author xpeska05
 */
public abstract class PlayersMoveEventHandler implements EventHandler<PlayersMoveEvent> {

    /**
     * @param srcMove Source position of move.
     */
    public void onEvent1(String srcMove) 
    {
    	
    }

    /**
     * @param dstMove Destination position of move.
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
