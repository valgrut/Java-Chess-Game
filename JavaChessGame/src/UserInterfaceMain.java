
import java.io.File;
import java.util.Vector;

import ChessGame.ChessBoard;
import ChessGame.GameManager;
import GUI.Board;
import GUI.MyButton;
import GUI.PlayersMoveEvent;
import GUI.PlayersMoveEventHandler;
import GameRecord.MoveData;
import GameRecord.PairInt;
import GameRecord.PositionTranslator;
import Loader.IParser;
import Loader.LongNotationParser;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
 * TODO check that opened games are saved, otherwise ask for save or throw when clicked on x.
 * TODO pridat k Board cislovani radku a sloupcu a pismenka 1-8 A-H
 * TODO zvyrazneni moznych tahu vybrane figurky a zvyrazneni vybrane figurky.
 * 
 * ......
 * TODO nachystat notation file pro check, checkmate, se spatnou notaci od zacatku, od pulky a 
 * s validni notaci ale s neproveditelnymi tahy.
 * TODO stridani cerne a bile !!!
 * TODO oznaceni vybrane figurky
 * TODO klikani pouze na figurky
 * TODO rychlost prehravani - pridat nejake omezeni
 * ......
 * 
 * TODO >>>>> FOUND BUGS <<<<<<
 *  - pokud je v notaci validni tah, ale je neproveditelny figurkou, tak lze notaci porad prehrava.
 *  Mela by se nastavit jako invalidMoveDetected a nemoct ji provadet dal.
 *
 *	- notation validator detekoval nejaky move ktery by mel byt ok jako invalidni, proc?
 *		NotCheckBytWhy file.
 */

/**
 * Class represents Graphical User Interface and is used as an entry point of application.
 * This class implements all control mechanisms for interacting with the chess game.
 * 
 * @author xpeska05
 */
public class UserInterfaceMain extends Application 
{
    GameManager gm = new GameManager();
    LongNotationParser parser = new LongNotationParser();
    int tabCounter = 0;
	
    /**
     * Method contains full layout of GUI, all buttons, event handling,
     * tabs, new game creation etc.
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage primaryStage) 
    {
    	VBox layout = new VBox();
    	HBox currentGameLayout = new HBox();	
    	
    	Board guiBoard = new Board();
    	
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
            	
            	if(gm.getOpenedGameCount() != 0)
            	{
	            	gm.getActiveGame().gotoMove(numberOfMove);
	        
	            	gm.printGameBoard();
					try 
					{
						guiBoard.update(getActiveGameBoard());
						updateRecordList(moveRecord, gm.getActiveGame().getCurrentGameRecord());
						updateHighlightCurrentMove(moveRecord);
						updateHighlightOfLastMove(moveRecord, guiBoard);
					} 
					catch (Exception e1)
					{
						e1.printStackTrace();
					}
            	}
			}
		});
    	
    	ObservableList<Label> items = FXCollections.observableArrayList();
    	moveRecord.setItems(items);
    	
    	if(gm.getOpenedGameCount() != 0)
    	{	
    		System.out.println("----> Number of Opened game: zero");
	    	updateRecordList(moveRecord, gm.getActiveGame().getCurrentGameRecord());
	    	moveRecord.setFocusTraversable(false);
    	}
    	
    	/*
    	 * Creating of Tabs
    	 */
    	TabPane tabpane = new TabPane();
	        
        Tab newGameTab = new Tab("+");
        //newGameTab.setContent(new Label("This is Tab: +"));
        newGameTab.setOnSelectionChanged(new EventHandler<Event>()
        {
            public void handle(Event e)
            {
            	// if clicked on '+' tab
                if (newGameTab.isSelected())
                { 
                	// choose notation file
                    final FileChooser fileChooserLoad = new FileChooser();
            		File filename = fileChooserLoad.showOpenDialog(primaryStage);
                    if (filename != null) 
                    {
                    	System.out.println("-------> Loading and Creating game from notation");
                    	try
                    	{
                    		gm.loadGame(filename.getAbsolutePath());
                    	}
                    	catch(SecurityException ex)
                    	{
                        	System.out.println("Nebyl vybran soubor takze bude vytvorena prazdna hra.");
                    		gm.createNewGame();
                    	}
                    }
                    else
                    {
                    	System.out.println("Nebyl vybran soubor takze bude vytvorena prazdna hra.");
                    	gm.createNewGame();
                    }
                    
                    // create Tab 
                    String notationName = null;
                    if(filename == null)
                    {
                    	notationName = "Hra_" + (int)(tabCounter + 1);
                    }
                    else
                    {
                    	notationName = filename.getName();
                    }
                    
                    Tab newTab = new Tab(notationName); 

                    // create a label       
                    //newTab.setContent(new Label("This is Tab: " + (int)(tabCounter + 1)));
                    newTab.setId(String.valueOf(tabCounter + 1));

                    tabCounter++; 
            
                    // add tab 
                    tabpane.getTabs().add(tabpane.getTabs().size() - 1, newTab); 
      
                    // select the last tab 
                    tabpane.getSelectionModel().select(tabpane.getTabs().size() - 2); 
                    
                    try 
                	{
						guiBoard.update(getActiveGameBoard());
						updateRecordList(moveRecord, gm.getActiveGame().getCurrentGameRecord());
						updateHighlightCurrentMove(moveRecord);
						updateHighlightOfLastMove(moveRecord, guiBoard);
					} 
                	catch (Exception e1) {}
                    
                    newTab.setOnSelectionChanged(new EventHandler<Event>()
                    { 
                        public void handle(Event e)
                        {
                        	// if clicked on '+' tab
                            if (newTab.isSelected())  
                            {			                	
			                	gm.setActiveGame(Integer.parseInt(newTab.getId())-1);
			                	
			                	try 
			                	{
									guiBoard.update(getActiveGameBoard());
									updateRecordList(moveRecord, gm.getActiveGame().getCurrentGameRecord());
									updateHighlightCurrentMove(moveRecord);
									updateHighlightOfLastMove(moveRecord, guiBoard);
								}
			                	catch (Exception e1) {}
								
                            }
                        } 
                    });
//                    newTab.setOnClosed(value);
//                    		//tabCounter++; // + kontrola ze by nemel jit zavrit jestli je posledni.
//                    		//			   zeptat se jestli chce uzivatel hru ulozit pred zavrenim.

                }
            } 
        }); 
        
        tabpane.getTabs().add(newGameTab);
    	
    	/*
    	 * Players Move
    	 * 
    	 * Create custom event and catch it here, get SRC DST pozition through parameter
    	 * and then execute players_move() and update();
    	 */
    	guiBoard.addEventFilter(PlayersMoveEvent.PLAYERS_MOVE, new PlayersMoveEventHandler() 
		{
			public void handle(PlayersMoveEvent playersEvent) 
			{ 
				System.out.println("GUI: Players Move Complete: " + playersEvent.getSrcMove() + " " + playersEvent.getDstMove());
				gm.getActiveGame().playersMove(playersEvent.getSrcMove(), playersEvent.getDstMove());
				gm.printGameBoard();
				try 
				{
					guiBoard.update(getActiveGameBoard());
					updateRecordList(moveRecord, gm.getActiveGame().getCurrentGameRecord());
					updateHighlightCurrentMove(moveRecord);	
					updateHighlightOfLastMove(moveRecord, guiBoard);
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
					guiBoard.update(getActiveGameBoard());
					updateHighlightCurrentMove(moveRecord);
					updateHighlightOfLastMove(moveRecord, guiBoard);
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
    	 * Auto Play forward
    	 */
    	TextField speedAnimationField = new TextField();
    	speedAnimationField.setPrefWidth(50);
    	speedAnimationField.setAccessibleText("0.5");
    	speedAnimationField.setText("0.5");

    	double playSpeed = Double.valueOf(speedAnimationField.getAccessibleText());
    	Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(playSpeed), e -> {
    				nextButton.fire();
    				//guiBoard.setRotate(guiBoard.getRotate() + 10);
    		    })
    	);
    	timeline.setCycleCount(Timeline.INDEFINITE);
    	timeline.setAutoReverse(true);

    	speedAnimationField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            	speedAnimationField.setText(newValue);
            	timeline.setRate(Double.valueOf(speedAnimationField.getText()));
        });
    	
    	
    	Button playAutomaticGameButton = new Button("Play");
		playAutomaticGameButton.setBackground(new Background(new BackgroundFill(Color.CORAL, null, null)));
		Runnable autoPlayRunnable = ()-> playAutomaticGameButton.fire();
		playAutomaticGameButton.setOnAction(e -> {
			if(timeline.getStatus() == Status.RUNNING)
			{
				timeline.stop();
				playAutomaticGameButton.setText("Play");
				return;
			}
			else if(timeline.getStatus() == Status.STOPPED)
			{
				timeline.play();
				playAutomaticGameButton.setText("Stop");
				return;
			}
		});
    	
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
					guiBoard.update(getActiveGameBoard());
					updateHighlightCurrentMove(moveRecord);
					updateHighlightOfLastMove(moveRecord, guiBoard);
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
					guiBoard.update(getActiveGameBoard());
					updateHighlightCurrentMove(moveRecord);
					updateHighlightOfLastMove(moveRecord, guiBoard);
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
					guiBoard.update(getActiveGameBoard());
					updateHighlightCurrentMove(moveRecord);
					updateHighlightOfLastMove(moveRecord, guiBoard);
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
					guiBoard.update(getActiveGameBoard());
					updateRecordList(moveRecord, gm.getActiveGame().getCurrentGameRecord());
					updateHighlightCurrentMove(moveRecord);
					updateHighlightOfLastMove(moveRecord, guiBoard);
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
					guiBoard.update(getActiveGameBoard());
					updateRecordList(moveRecord, gm.getActiveGame().getCurrentGameRecord());
					updateHighlightCurrentMove(moveRecord);
					updateHighlightOfLastMove(moveRecord, guiBoard);
				} 
				catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    	);
    	
        // add tabPane to main layout
        layout.getChildren().add(tabpane);
        
    	currentGameLayout.getChildren().add(guiBoard);
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
        menu.getChildren().add(playAutomaticGameButton);
        menu.getChildren().add(speedAnimationField);
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
        // keyboard shortcuts
        mainScene.getAccelerators().put(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN), saveGameRunnable);
        mainScene.getAccelerators().put(new KeyCodeCombination(KeyCode.UP), endRunnable);
        mainScene.getAccelerators().put(new KeyCodeCombination(KeyCode.DOWN), startRunnable);
        mainScene.getAccelerators().put(new KeyCodeCombination(KeyCode.LEFT), prevRunnable);
        mainScene.getAccelerators().put(new KeyCodeCombination(KeyCode.RIGHT), nextRunnable);
        mainScene.getAccelerators().put(new KeyCodeCombination(KeyCode.P), autoPlayRunnable);
        mainScene.getAccelerators().put(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN), redoRunnable);
        mainScene.getAccelerators().put(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN), undoRunnable);
        //mainScene.getAccelerators().put(new KeyCodeCombination(KeyCode.TAB), nextTabRunnable);
        
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Chess game reviewer");
        primaryStage.show();
    }
    
    /**
     * Method clears ListView representing current game record and initializes ListView 
     * with records of moves from vector of strings given as the second parameter.
     * @param destination ListView that is being initialized.
     * @param sourceRecord Vector of strings from which destination is initialized.
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
    
    /**
     * This method sets css style (background) for each record in the ListView
     * to none and sets yellow background only for current move record.
     * 
     * @param record ListView containing records, where currentMove background will be set to yellow.
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
    		System.out.println("No move selected, nothing to highlight.");
    	}
    	catch(java.lang.IndexOutOfBoundsException e)
    	{
    		//System.out.println("updateHighlightCurrentMove(): Out of bound: -1 index");
    	}    	
    }
    
    /**
     * This method sets css style (background) for each record in the ListView
     * to none and sets yellow background only for current move record.
     * 
     * @param record ListView containing records, where currentMove background will be set to yellow.
     */
    public void updateHighlightOfLastMove(ListView<Label> record, Board guiboard)
    {
    	try
    	{        	
    		String lastMoveNotation = record.getItems().get(gm.getActiveGame().getPlayersCurrentMoveNumber()-1).getText();
    		MoveData lastMoveData = new MoveData();
    		parser.parseSubMove(parser.splitString(lastMoveNotation)[1], lastMoveData);
    		
    		// highlight destination tile of move.
    		PairInt destinationCoords = PositionTranslator.positionToCoords(lastMoveData.getDestinationPosition());
    		guiboard.getTileOn(destinationCoords.getFirst(), destinationCoords.getSecond()).setHighlight();
    		
    		// highlight source tile of move.
    		PairInt sourceCoords = PositionTranslator.positionToCoords(lastMoveData.getSourcePosition());
    		guiboard.getTileOn(sourceCoords.getFirst(), sourceCoords.getSecond()).setHighlight();
    	}
    	catch(java.lang.ArrayIndexOutOfBoundsException e)
    	{
    		System.out.println("updateHighlightCurrentMove(): Out of bound: -1 index");
    	}
    	catch(java.lang.IndexOutOfBoundsException e)
    	{
    		System.out.println("updateHighlightCurrentMove(): Out of bound: -1 index");
    	}    	
    }
    
    /**
     * Returns This method returns ChessBoard instance of currently active game in game manager.
     * @return ChessBoard Instance of ChessBoard of currently Active game.
     */
    public ChessBoard getActiveGameBoard()
    {
    	return this.gm.getActiveGame().getBoard();
    }
    
    /**
     * Main method.
     * @param args Arguments from terminal.
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
}


