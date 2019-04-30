package GUI;

import javafx.event.EventHandler;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TileRectangle extends Rectangle 
{
	private boolean isHighlighted = false;
	private Glow glowEffect;
	private ImageView view;
	
	public TileRectangle(double posX, double posY)
	{
		super();
		setWidth(50);
		setHeight(50);
		setX(posX);
		setY(posY);
		
		setFill(Color.BLUE);
		setStrokeWidth(2);
		setStroke(Color.GREEN);
		
		this.view = new ImageView();
		
		setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) 
            {	
            	if(t.getButton() == MouseButton.PRIMARY) 
            	{
            		setFill(Color.RED);
            		return;
            	}
            	else
            	{
            		setFill(Color.BLUE);
            		return;
            	}
            }
        });
		
	}
	
	public void setImage(Image piece)
	{
		view.setImage(piece);
	}
	
	public void switchHighlight()
	{
		if(isHighlighted == false)
		{
			isHighlighted = true;
			setEffect(glowEffect);
		}
		else
		{
			isHighlighted = false;
			setEffect(null);
		}
	}
	
	public void setHighlight()
	{
		isHighlighted = true;
		setEffect(glowEffect);
	}
	
	public void unsetHighlight()
	{
		isHighlighted = false;
		setEffect(null);
	}
}
