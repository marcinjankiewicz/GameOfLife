package life.world;

import life.utils.Counter;
import life.utils.WorldPrinter;
import life.view.WindowWorld;

public class World {
    private int numberOfGenerations;
    private boolean[][] world;
    private WorldPrinter printer;
    private WindowWorld windowWorld;

    public World(int worldSize, long seed, int numberOfGenerations) {
        this.numberOfGenerations = numberOfGenerations;
        world = new boolean[worldSize][worldSize];
        LifeGenerator lifeGenerator = new LifeGenerator(seed);
        lifeGenerator.populateWorld(world);
        printer = new WorldPrinter();
        windowWorld = new WindowWorld(this);
    }



    public void lifeCycle() throws InterruptedException {
        EvolutionController evolutionController = new EvolutionController(this);
        for (int i = 0; i < numberOfGenerations + 1 ; i++) {
            printer.printWorld(world, i);
            manageWindow(i);
            letThemLive(evolutionController);
            Thread.sleep(2000);
        }
    }

    private void manageWindow(int numberOfGeneration){
        windowWorld.getGrid().setWorldGrid(world);
        windowWorld.setNumberForGenerationLabel(numberOfGeneration);
        windowWorld.setNumberOfAliveCells(Counter.countAlive(world));
        windowWorld.repaint();
    }

    private void letThemLive(EvolutionController ev){
        ev.setWorldGrid(world);
        this.world = ev.birthControl();
    }


    public boolean[][] getWorld() {
        return world;
    }


}
