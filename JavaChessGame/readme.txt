Java-Chess-Game

Authors:
	Jiri Peska - xpeska05
	Krystof Halmo - xhalmo00


Java Chess application that is able to replay games by notation record move by move. 
Game can be traversed back and forward and also player can anytime play own moves, which can be undone lately and redoed again.

User can load notation file, where is game record in following format:
    c2d4 Jb8c6
    Jg1f3 a7a6
    ...

User can create new game and export played game as new notation file in the format above.

Program Interface:
	1. Terminal interface Program fully supports terminal interface so user can interact with program just like from GUI.
    	2. Graphical User Interface Default - Graphical interface of the program offers options to load multiple games and replay loaded games from notation.

Functions:
    - Create new empty game
    - Load game from notation file 
    - Switch between currently opened games
    - One step forward - performs one move forward
    - One step backward - performs one move backward
    - Rewind game to the start
    - Rewind game to the end
    - Player can play his move according to chess rules, the rest of moves is erased
    - Undo last players move
    - Redo lastly undone players move
    - Save current game to the file

