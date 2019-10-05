package life.world;

class NeighbourState {
    private boolean[][] world;
    private boolean[] neighboursStates;


    public NeighbourState() {
        neighboursStates = new boolean[8];
    }

    private boolean NW(int i, int j) {
        int neighbourX = leftAndUpSideCoordinate(i);
        int neighbourY = leftAndUpSideCoordinate(j);
        return world[neighbourX][neighbourY];
    }

    private boolean N(int i, int j) {
        int neighbourX = leftAndUpSideCoordinate(i);
        return world[neighbourX][j];
    }

    private boolean NE(int i, int j) {
        int neighbourX = leftAndUpSideCoordinate(i);
        int neighbourY = rightAndDownSideCoordinate(j);
        return world[neighbourX][neighbourY];
    }

    private boolean W(int i, int j) {
        int neighbourY = leftAndUpSideCoordinate(j);
        return world[i][neighbourY];
    }

    private boolean E(int i, int j) {
        int neighbourY = rightAndDownSideCoordinate(j);
        return world[i][neighbourY];
    }

    private boolean SW(int i, int j) {
        int neighbourX = rightAndDownSideCoordinate(i);
        int neighbourY = leftAndUpSideCoordinate(j);
        return world[neighbourX][neighbourY];
    }

    private boolean S(int i, int j) {
        int neighbourX = rightAndDownSideCoordinate(i);
        return world[neighbourX][j];
    }

    private boolean SE(int i, int j) {
        int neighbourX = rightAndDownSideCoordinate(i);
        int neighbourY = rightAndDownSideCoordinate(j);
        return world[neighbourX][neighbourY];
    }

    private int leftAndUpSideCoordinate(int a) {
        return a - 1 >= 0 ? a - 1 : world.length - 1;
    }

    private int rightAndDownSideCoordinate(int a) {
        return a + 1 < world.length ? a + 1 : 0;
    }

    public boolean[] getNeighboursStates() {
        return neighboursStates;
    }

    public void setNeighboursStates(int i, int j) {
        neighboursStates[0] = NW(i, j);
        neighboursStates[1] = N(i, j);
        neighboursStates[2] = NE(i, j);
        neighboursStates[3] = W(i, j);
        neighboursStates[4] = E(i, j);
        neighboursStates[5] = SW(i, j);
        neighboursStates[6] = S(i, j);
        neighboursStates[7] = SE(i, j);
    }

    public void setWorld(boolean[][] world) {
        this.world = world;
    }
}
