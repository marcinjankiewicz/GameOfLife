package life.world;

import java.util.Random;

class LifeGenerator {
    private Random random;
    private long seed;

    public LifeGenerator(long seed) {
        this.seed = seed;
        random = new Random(seed);
    }

    public void populateWorld(boolean[][] world) {
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                world[i][j] = random.nextBoolean();
            }
        }
    }
}
