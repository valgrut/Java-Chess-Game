package GUI;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;

public class MyButton extends Button 
{
	public MyButton()
	{
		super();
		setText("Destroy the Universe");
		
		DropShadow shadow = new DropShadow();
    	shadow.setOffsetX(5);
    	shadow.setOffsetY(5);
    	
    	setEffect(shadow);
	}
}
