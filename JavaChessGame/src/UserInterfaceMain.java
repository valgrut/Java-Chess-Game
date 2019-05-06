
import java.io.File;
import java.util.Vector;

import javax.swing.GroupLayout.Alignment;

import ChessGame.GameManager;
import GUI.Board;
import GUI.MyButton;
import GUI.PlayersMoveEvent;
import GUI.PlayersMoveEventHandler;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
 * TODO check that opened games are saved, otherwise ask for save or throw when clicked on x.
 * 
 */

public class UserInterfaceMain extends Application 
{
    GameManager gm = new GameManager();
    int tabCounter = 0;
	
    @Override
    public void start(Stage primaryStage) 
    {
    	VBox layout = new VBox();
    	HBox currentGameLayout = new HBox();
    	
    	/*
    	 * Loading the game from notation
    	 */
        final FileChooser fileChooserLoad = new FileChooser();
		File filename = fileChooserLoad.showOpenDialog(primaryStage);
        if (filename != null) 
        {
            //gm.getActiveGame().saveGame(filename.getAbsolutePath());
        	try 
        	{
        		gm.loadGame(filename.getAbsolutePath());
        	}
        	catch(SecurityException e)
        	{
            	System.out.println("Nebyl vybran nazev souboru takze hra z notace nebyla nactena.");
        		gm.createNewGame();
        	}
        }
        else
        {
        	System.out.println("Nebyl vybran nazev souboru takze hra z notace nebyla nactena.");
        	gm.createNewGame();
        }
    	
    	//gm.loadGame(filename.getAbsolutePath());
    	//gm.createNewGame();
    	
    	Board guiBoard = new Board(gm.getActiveGame().getBoard());
    	
    	/*
    	 * List View initialisation - record of game in notation
    	 */
    	ListView<Label> moveRecord = new ListView<Label>();
    	moveRecord.setPrefWidth(200);
    	moveRecord.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() 
		{
            public void handle(MouseEvent e) 
			{
            	int numberOfMove = moveRecord.getSelectionModel().getSelectedIndex() + 1;
            	
            	//System.out.println(moveRecord.getSelectionModel().getSelectedItem().toString());
            	//System.out.println(moveRecord.getSelectionModel().getSelectedIndex());
            	
            	gm.getActiveGame().gotoMove(numberOfMove);
        
            	gm.printGameBoard();
				try 
				{
					guiBoard.update();
					updateRecordList(moveRecord, gm.getActiveGame().getCurrentGameRecord());
					updateHighlightCurrentMove(moveRecord);				
				} 
				catch (Exception e1) 
				{
					e1.printStackTrace();
				}
			}
		});
    	ObservableList<Label> items = FXCollections.observableArrayList();
    	moveRecord.setItems(items);
    	
    	updateRecordList(moveRecord, gm.getActiveGame().getCurrentGameRecord());
    	moveRecord.setFocusTraversable(false);
    	
    	/*
    	 * Create custom event and catch it here, get SRC DST pozition through parameter
    	 * and then execute players_move() and update();
    	 */
    	guiBoard.addEventFilter(PlayersMoveEvent.PLAYERS_MOVE, new PlayersMoveEventHandler() 
		{
			public void handle(PlayersMoveEvent playersEvent) 
			{ 
				System.out.println("Players Move Complete: " + playersEvent.getSrcMove() + " " + playersEvent.getDstMove());
				gm.getActiveGame().playersMove(playersEvent.getSrcMove(), playersEvent.getDstMove());
				gm.printGameBoard();
				try 
				{
					guiBoard.update();
					updateRecordList(moveRecord, gm.getActiveGame().getCurrentGameRecord());
					updateHighlightCurrentMove(moveRecord);				
				} 
				catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};              
		});

    	//guiBoard.addEventFilter(PlayersMoveEvent.PLAYERS_MOVE, handle -> System.out.println("hello"));
    	/*
    	guiBoard.addEventFilter(PlayersMoveEvent.CUSTOM_EVENT_TYPE, new MyCustomEventHandler() 
		{
			public void handle(PlayersMoveEvent e) { 
				System.out.println("Event catched: " + e.getSource());
			};              
		});
		*/
    	
    	/*
    	 * Save game
    	 */
        final FileChooser fileChooser = new FileChooser();
    	Button saveGameButton = new Button();
    	saveGameButton.setText("Save Game");
    	Runnable saveGameRunnable = ()-> saveGameButton.fire();
    	saveGameButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				File filename = fileChooser.showOpenDialog(primaryStage);
                if (filename != null) 
                {
                    gm.getActiveGame().saveGame(filename.getAbsolutePath());
                }
                else
                {
                	System.out.println("Nebyl vybran nazev souboru takze hra nebyla ulozena.");
                }
			}
		}
    	);
    	
    	/*
    	 * Step Forward
    	 */
    	Button nextButton = new Button();
    	nextButton.setText("Next Step");
    	Runnable nextRunnable = ()-> nextButton.fire();
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
					updateHighlightCurrentMove(moveRecord);
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
    	 * Step Backward
    	 */
    	Button prevButton = new Button();
    	prevButton.setText("Step Back");
    	Runnable prevRunnable = ()-> prevButton.fire();
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
					updateHighlightCurrentMove(moveRecord);
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
    	 * To Start
    	 */
    	Button startButton = new Button();
    	startButton.setText("To Start");
    	Runnable startRunnable = ()-> startButton.fire();
    	startButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				gm.getActiveGame().toStart();
				gm.printGameBoard();
				try 
				{
					guiBoard.update();
					updateHighlightCurrentMove(moveRecord);
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
    	 * To End
    	 */
    	Button endButton = new Button();
    	endButton.setText("To End");
    	Runnable endRunnable = ()-> endButton.fire();
    	endButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				gm.getActiveGame().toEnd();
				gm.printGameBoard();
				try 
				{
					guiBoard.update();
					updateHighlightCurrentMove(moveRecord);
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
    	 * Undo move
    	 */
    	Button undoButton = new Button();
    	undoButton.setText("Undo");
    	Runnable undoRunnable = ()-> undoButton.fire();
    	undoButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				gm.getActiveGame().undo();
				gm.printGameBoard();
				try 
				{
					guiBoard.update();
					updateRecordList(moveRecord, gm.getActiveGame().getCurrentGameRecord());
					updateHighlightCurrentMove(moveRecord);
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
    	 * Redo
    	 */
    	Button redoButton = new Button();
    	redoButton.setText("Redo");
    	Runnable redoRunnable = ()-> redoButton.fire();
    	redoButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				gm.getActiveGame().redo();
				gm.printGameBoard();
				try 
				{
					guiBoard.update();
					updateRecordList(moveRecord, gm.getActiveGame().getCurrentGameRecord());
					updateHighlightCurrentMove(moveRecord);
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
        
    	currentGameLayout.getChildren().add(guiBoard);
    	//currentGameLayout.getChildren().add(new Separator());
    	currentGameLayout.getChildren().add(moveRecord);
        layout.getChildren().add(currentGameLayout);
        

        HBox menu = new HBox();
        menu.setMinWidth(currentGameLayout.getWidth());
        menu.setPrefWidth(currentGameLayout.getWidth());
        menu.setFillHeight(true);
        //menu.setStyle("-fx-background-color: yellow;");
        menu.getChildren().add(startButton);
        menu.getChildren().add(prevButton);
        menu.getChildren().add(new Separator());
        //menu.getChildren().add(new Text(gm.getActiveGame().getPlayersCurrentMoveNumber()+"/"+gm.getActiveGame().getPlayersLastMoveNumber()));
        menu.getChildren().add(nextButton);
        menu.getChildren().add(endButton);
        menu.getChildren().add(new Separator());
        menu.getChildren().add(undoButton);
        menu.getChildren().add(redoButton);
        menu.getChildren().add(new Separator());
        menu.getChildren().add(saveGameButton);
        
        layout.getChildren().add(menu);
        Scene mainScene = new Scene(layout, 800,600);
        mainScene.getAccelerators().put(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN), saveGameRunnable);
        mainScene.getAccelerators().put(new KeyCodeCombination(KeyCode.UP), endRunnable);
        mainScene.getAccelerators().put(new KeyCodeCombination(KeyCode.DOWN), startRunnable);
        mainScene.getAccelerators().put(new KeyCodeCombination(KeyCode.LEFT), prevRunnable);
        mainScene.getAccelerators().put(new KeyCodeCombination(KeyCode.RIGHT), nextRunnable);
        mainScene.getAccelerators().put(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN), redoRunnable);
        mainScene.getAccelerators().put(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN), undoRunnable);

        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Chess game reviewer");
        primaryStage.show();
    }
    
    /*
     * 
     */
    public void updateRecordList(ListView<Label> destination, Vector<String> sourceRecord)
    {
    	int moveCount = 1;
    	destination.getItems().clear();
    	
    	for(String move : sourceRecord)
    	{
    		Label moveRecordNotation = new Label(moveCount + ". " + move);
    		destination.getItems().add(moveRecordNotation);
    		
    		moveCount++;
    	}
    }
    
    /*
     * 
     */
    public void updateHighlightCurrentMove(ListView<Label> record)
    {
    	for(Label movelabel : record.getItems())
    	{
    		movelabel.setStyle("-fx-background-color: none;");
    	}
    	
    	try
    	{
    		record.getItems().get(gm.getActiveGame().getPlayersCurrentMoveNumber()-1).setStyle("-fx-background-color: yellow;");
    	}
    	catch(java.lang.ArrayIndexOutOfBoundsException e)
    	{
    		System.out.println("updateHighlightCurrentMove(): Out of bound: -1 index");
    	}
    }
    
    public static void main(String[] args) 
    {
        launch(args);
    }
}


