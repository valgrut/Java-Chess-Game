# Java-Chess-Game
Java Chess application that is able to replay games by notation record move by move.
Game can be traversed back and forward and also player can anytime play own moves, which can be undone lately and redoed again.

User can load notation file, where is game record in following format:
  1. c2d4 Jb8c6
  2. Jg1f3 a7a6
  3. ...

User can create new game and export played game as new notation file in the format above.

<b>Program Interface</b>
1. Terminal interface
  Program fully supports terminal interface so user can interact with program just like from GUI. 

2. Graphical User Interface
  Default - Graphical interface of the program offers options to load multiple games and replay loaded games from notation.

**Note**: Program logic and code design can be viewed [in this pdf](1380_001.pdf).

<b>Functions</b>
  1. Create new empty game
  2. Load game from notation file
  2.1 Switch between currently opened games
  3. One step forward - performs one move forward
  4. One step backward - performs one move backward
  5. Replay game to the start
  6. Replay game to the end
  7. Player can play his move according to chess rules, the rest of moves is erased
  8. Undo last players move
  9. Redo lastly undone players move
  10. Save current game to the file


<b>Preview</b>
[GUI and CLI showcase](code_gui_preview.png)

[GUI preview](gui_preview_new.png)

[GUI preview 2 (older)](gui_preview_old.png)
