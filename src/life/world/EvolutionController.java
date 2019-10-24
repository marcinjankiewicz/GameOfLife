package life.world;

public class EvolutionController {
    private boolean[][] worldGrid;
    private NeighbourState neighbourState;

    public EvolutionController(World world) {
        this.worldGrid = world.getWorld();
        this.neighbourState = new NeighbourState();
    }

    public boolean[][] birthControl() {
        neighbourState.setWorld(worldGrid);
        boolean[][] newWorld = new boolean[worldGrid.length][worldGrid.length];
        for (int i = 0; i < worldGrid.length; i++) {
            for (int j = 0; j < worldGrid[i].length; j++) {
                checkNeighboursAndStateIfNeeded(i, j, newWorld);
            }
        }
        return newWorld;
    }

    private int countNeighbours(int i, int j) {
        int numberOfAliveNeighbours = 0;
        neighbourState.setNeighboursStates(i, j);
        for (boolean nState : neighbourState.getNeighboursStates()) {
            if (nState) {
                numberOfAliveNeighbours++;
            }
            if (numberOfAliveNeighbours > 3)
                break;
        }
        return numberOfAliveNeighbours;
    }

    private void checkNeighboursAndStateIfNeeded(int i, int j, boolean[][] newWorld) {
        int numberOfAliveNeighbours = countNeighbours(i, j);
        if (worldGrid[i][j] && (numberOfAliveNeighbours < 2 || numberOfAliveNeighbours > 3)) {
            newWorld[i][j] = false;
        } else if (!worldGrid[i][j] && numberOfAliveNeighbours == 3) {
            newWorld[i][j] = true;
        } else {
            newWorld[i][j] = worldGrid[i][j];
        }
    }

    public void setWorldGrid(boolean[][] worldGrid) {
        this.worldGrid = worldGrid;
    }
}
