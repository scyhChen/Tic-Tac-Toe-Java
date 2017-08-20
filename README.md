# Tic-Tac-Toe-Java
Five-in-a-row game implemented in Java. Has GUI that players can interact with. Has different AIs and Human player options to choose from.

Only the code in /src/student is original. Other code is from CS 2110: OO Programming and Data Structures, Professors Mike George and David Gries, Fall 2016, Cornell University.

## Instruction on GUI main
1. Run GUI main
2. a window will be prompted where the user selects the two player's types respectively. Player1 uses the marker X, player2 uses the marker O. Click "Go" after the choice is made.
3. a new window will be prompted, showing the board on which the two players play the game.
4. player1 and Player2 takes turn to place their markers on the board. Meanwhile, the prompt on the right will update the progress of the game: who is the next to play, if the game ends, who is the winner, if the game is a draw.

## Known Bugs
1. When game is running, every time game changes, GUI is correctly updated to indicate the player of next turn. But when the game ends, GUI does not update to show that the game is ended and who the winner is or if the game ended in a draw.
2. When playing human vs human, the winning step will not update on GUI. For example, if X has 5 in a row, only 4 will be shown on the board and the last step, his winning step does not update on the board before the game ends. This bug also happens sometimes when either player is an AI.
3. When playing human vs human, sometimes the GUI does not update when a new move is made. It may update 2 steps together when another move is made. Also in some rare occasions, player X or O cannot take step on some certain locations. There is no pattern on what what triggers this bug.

## Additional Design
1. a restart option can be added
2. GUI components can be better aligned
