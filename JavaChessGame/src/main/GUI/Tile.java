package GUI;

import javafx.event.EventHandler;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * Class represents one particular tile located in GUI chess board.
 * This class holds piece located in this tile and manages graphical view of this tile.
 * 
 * @author xpeska05
 */
public class Tile extends StackPane 
{
	/**
	 * Indication whether this tile is highlighted or not.
	 */
	private boolean isHighlighted = false;
	
	/**
	 * Instance of shadow effect.
	 */
	private DropShadow pieceShadow;
	
	/**
	 * Instance of view that holds figure image that is placed to this tile.
	 */
	private ImageView view;
	
	/**
	 * Css style of this tile.
	 */
	private String css;
	
	/**
	 * Getter of css style of this tile.
	 * @return String representing css style of this tile.
	 */
	public String getCss() {
		return css;
	}

	/**
	 * Sets css styles of this tile.
	 * @param css Css styles.
	 */
	public void setCss(String css) {
		this.css = css;
	}

	/**
	 * Constructor of this tile. Sets size of tile, image view, shadow and effects.
	 * @param identifier Id of this tile.
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
	 * Sets image of piece to this tile. This results in drawing of given image inside of this tile.
	 * @param piece
	 */
	public void setImage(Image piece)
	{
		view.setImage(piece);
	}
	
	/**
	 * Switches the Highlight effect.
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
	 * Toggles on the Highlight effect.
	 */
	public void setHighlight()
	{
		isHighlighted = true;
		//setEffect(new Bloom());
		setStyle("-fx-background-color: orange;");
	}
	
	/**
	 * Toggles off the Highlight effect.
	 */
	public void unsetHighlight()
	{
		isHighlighted = false;
		//setEffect(null);
		setStyle(this.css);
	}
	
	/**
	 * Method returns true when no image of piece is set to this tile or false when image is set.
	 * @return True if piece is not set in this tile.
	 */
	public boolean isEmpty()
	{
		if(this.view.getImage() == null)
			return true;
		
		return false;
	}
}
