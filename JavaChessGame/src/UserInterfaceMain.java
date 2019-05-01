
import ChessGame.GameManager;
import GUI.Board;
import GUI.MyButton;
import javafx.application.Application;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class UserInterfaceMain extends Application 
{
    GameManager gm = new GameManager();
    int tabCounter = 0;
	
    @Override
    public void start(Stage primaryStage) 
    {
    	VBox layout = new VBox();
    	
//    	gm.createNewGame();
    	gm.loadGame("notation");
    	Board guiBoard = new Board(gm.getActiveGame().getBoard());
    	
    	Button nextButton = new Button();
    	nextButton.setText("Next Step");
    	nextButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				gm.getActiveGame().stepForward();
				gm.printGameBoard();
				try 
				{
					guiBoard.update();
				} 
				catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    	);
    	
    	Button prevButton = new Button();
    	prevButton.setText("Step Back");
    	prevButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				gm.getActiveGame().stepBackward();
				gm.printGameBoard();
				try 
				{
					guiBoard.update();
				} 
				catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    	);
    	
    	/*
    	HBox newGameMenu = new HBox();
    	Button newGameButton = new Button("Vytvořit novou hru.");
    	Button loadGameButton = new Button("Načíst hru z notace.");
    	newGameMenu.getChildren().add(newGameButton);
    	newGameMenu.getChildren().add(loadGameButton);
    	
    	StackPane stackedBoards = new StackPane();
    	stackedBoards.getChildren().add(newGameMenu);
    	stackedBoards.getChildren().get(0).setVisible(true);
    	
    	VBox layout = new VBox();
    	
        TabPane tabpane = new TabPane();
        
        Tab newGameTab = new Tab("+");
        Label newGameLabel = new Label("This is Tab: +"); 
        newGameTab.setContent(newGameLabel);
        
        // action event 
        EventHandler<Event> event =  
        new EventHandler<Event>()
        { 
            public void handle(Event e)
            { 
                if (newGameTab.isSelected())  
                { 
                    // create Tab 
                    Tab tab = new Tab("Hra_" + (int)(tabCounter + 1)); 
      
                    // create a label 
                    Label label = new Label("This is Tab: " + (int)(tabCounter + 1)); 
      
                    tabCounter++; 
      
                    // add label to the tab 
                    tab.setContent(label); 
      
                    // add tab 
                    tabpane.getTabs().add(tabpane.getTabs().size() - 1, tab); 
      
                    // select the last tab 
                    tabpane.getSelectionModel().select(tabpane.getTabs().size() - 2); 
                } 
            } 
        }; 
        newGameTab.setOnSelectionChanged(event);
        tabpane.getTabs().add(newGameTab);
        */
        
        layout.getChildren().add(guiBoard);
        layout.getChildren().add(nextButton);
    	layout.getChildren().add(prevButton);
               
        primaryStage.setScene(new Scene(layout, 800, 600));
        primaryStage.show();
    }
    
    public static void main(String[] args) 
    {
        launch(args);
    }
}


