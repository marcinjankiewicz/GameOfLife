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

##Input data
User inputs three numbers N, S, M:
* N (N>0) is size of the universe N x N
* S is a seed used for a Random object
* M (M>=0) the number of generations the program should create

## Technologies
Project is created with:
* Java 11
* Swing

##NOTE! Project is still in progress...
To-do list:
* Adding buttons: pause/resume and start over
* Adding slider for changing speed mode
* Adding fields to change cells size
* Adding buttons and functionality save/load
