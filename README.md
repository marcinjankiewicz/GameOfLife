# Game of Life
Imitation of a primitive life form with two simple rules that creates amazing diversity of patterns.

## General info
The Game of Life was described by John Conway in 1970. It came from a mathematical problem, and since 1970 it has attracted the interest of scientists in various fields: physics, biology, economics, and so on.
The most amazing feature of this game is its simplicity, leading to many more complex patterns. The universe in this game looks like a grid in which each cell has one of two possible states: populated (alive) or not populated (dead).
                                                                                                Before the game starts, the player should define the state of each cell. To make the first state of the universe, we use randomness with seed that is being input.
                                                                                                The size of universe is set by user, but universe is considered to be periodic - each cell always has eight neighbours.
## Evolution
Evolution is controlled by two rules:
* An alive cell survives if has two or three alive neighbours otherwise it dies of bored or overpopulation
* A dead cell is reborn if it has exactly three alive neighbours

## Input data
User inputs three numbers N, S, M:
* N (N>0) is size of the universe N x N
* S is a seed used for a Random object
* M (M>=0) the number of generations the program should create

Startup input is:
* N = 50
* S = 10
* M = 100
* time to repaint = 2s

## Technologies
Project is created with:
* Java 11
* Swing

## Window description
There are three buttons on the top:
* Run (with arrow) - starts game if was stopped, changes timeToSleep to the current selection
* Pause - pauses the game
* Start Over - start new game with parameters set in fields below

## Saves and Loads
Functionality to save current world available.
The game state is saved under C:\Users\\%User%\GameOfLife\save.txt
After loading the game starts from the last state with timeToSleep = 2s

## NOTE! Project is still in progress...
To-do list:
* Adding fields to change cells size
