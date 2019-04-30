package GUI;

import javafx.event.EventHandler;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class Tile extends StackPane 
{
	private boolean isHighlighted = false;
	private DropShadow pieceShadow;
	private ImageView view;
	
	public Tile(String identifier)
	{
		super();
		setId(identifier);
		
		setMinSize(60, 60);
		setWidth(60);
		setHeight(60);
		
		this.view = new ImageView();
		getChildren().add(view);
		
		
		pieceShadow = new DropShadow();
		view.setEffect(pieceShadow);
		
		setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) 
            {	
            	if(t.getButton() == MouseButton.PRIMARY) 
            	{
            		setStyle("-fx-background-color: blue;");
            		return;
            	}
            	else
            	{
            		setStyle("-fx-background-color: green;");
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
			//setEffect(glowEffect);
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
		//setEffect(glowEffect);
	}
	
	public void unsetHighlight()
	{
		isHighlighted = false;
		setEffect(null);
	}
}
