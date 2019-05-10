package GUI;

import javafx.event.EventHandler;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * @author root
 *
 */
public class Tile extends StackPane 
{
	private boolean isHighlighted = false;
	private DropShadow pieceShadow;
	private ImageView view;
	private String css;
	
	/**
	 * @return
	 */
	public String getCss() {
		return css;
	}

	/**
	 * @param css
	 */
	public void setCss(String css) {
		this.css = css;
	}

	/**
	 * @param identifier
	 */
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
		
		/*
		addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() 
		{
	            public void handle(MouseEvent e) 
				{
	        		// TODO Auto-generated method stub
	    			//System.out.println(getId() + " - clicked on x: " + e.getSceneX() + " y:" + e.getSceneY());
				}
		});
		*/
		
		/*
		setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) 
            {	
            	if(t.getButton() == MouseButton.PRIMARY) 
            	{
            		//setStyle("-fx-background-color: blue;");
            		setHighlight();
            		return;
            	}
            	else
            	{
            		//setStyle("-fx-background-color: green;");
            		unsetHighlight();
            		return;
            	}
            }
        });
        */
        
	}
	
	/**
	 * @param piece
	 */
	public void setImage(Image piece)
	{
		view.setImage(piece);
	}
	
	/**
	 * 
	 */
	public void switchHighlight()
	{
		if(isHighlighted == false)
		{
			isHighlighted = true;
			setHighlight();
		}
		else
		{
			isHighlighted = false;
			unsetHighlight();
		}
	}
	
	/**
	 * 
	 */
	public void setHighlight()
	{
		isHighlighted = true;
		//setEffect(glowEffect);
		setStyle("-fx-background-color: green;");
	}
	
	/**
	 * 
	 */
	public void unsetHighlight()
	{
		isHighlighted = false;
		setStyle(this.css);
	}
	
	/**
	 * @return
	 */
	public boolean isEmpty()
	{
		if(this.view.getImage() == null)
			return true;
		
		return false;
	}
}
