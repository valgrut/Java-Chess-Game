package GUI;

import javafx.event.EventHandler;

public abstract class MyCustomEventHandler implements EventHandler<PlayersMoveEvent> {

    public void onEvent1(String srcMove) 
    {
    	
    }

    public void onEvent2(String dstMove)
    {
    	
    }
    
    @Override
    public void handle(PlayersMoveEvent event) {
        event.invokeHandler(this);
    }
}
