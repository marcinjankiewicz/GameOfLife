package life.world;

import life.utils.WorldPrinter;
import life.utils.WorldThread;
import life.view.WindowWorld;

import java.util.Scanner;


public class World {
    private int worldSize;
    private long seed;
    private int numberOfGenerations;
    private int numberOfCurrentGeneration;
    private boolean[][] world;
    private WorldPrinter printer;
    private EvolutionController evolutionController;
    private WorldThread worldThread;
    private WindowWorld windowWorld;
    private Scanner sc = new Scanner(System.in);
    private Thread thread;


    public World() {
        this.worldSize = 50;
        this.seed = 10;
        this.numberOfGenerations = 100;
        this.numberOfCurrentGeneration = 0;
        this.world = new boolean[worldSize][worldSize];
        this.printer = new WorldPrinter();
        this.evolutionController = new EvolutionController(this);
        generateLife(seed);
    }

    public World(WindowWorld windowWorld) {
        this();
        this.windowWorld = windowWorld;
    }



    public void generateLife(long seed) {
        LifeGenerator lifeGenerator = new LifeGenerator(seed);
        lifeGenerator.populateWorld(world);
    }

    public void start() {
        worldThread = new WorldThread(this);
        thread = new Thread(worldThread);
        thread.setDaemon(true);
        worldThread.setStopped(false);
        thread.start();
    }



    public void lifeCycle() {
        printer.printWorld(world, numberOfCurrentGeneration);
        windowWorld.manageWindow(numberOfCurrentGeneration);
        letThemLive();
        numberOfCurrentGeneration++;
    }


    private void letThemLive() {
        evolutionController.setWorldGrid(world);
        this.world = evolutionController.birthControl();
    }


    public boolean[][] getWorld() {
        return world;
    }

    public int getNumberOfCurrentGeneration() {
        return numberOfCurrentGeneration;
    }


    public int getNumberOfGenerations() {
        return numberOfGenerations;
    }

    public WorldThread getWorldThread() {
        return worldThread;
    }

    public WindowWorld getWindowWorld() {
        return windowWorld;
    }

    public void setEvolutionController(EvolutionController evolutionController) {
        this.evolutionController = evolutionController;
    }

    public long getSeed() {
        return seed;
    }

    public Thread getThread() {
        return thread;
    }

    public void setWorldSize(int worldSize) {
        this.worldSize = worldSize;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public void setNumberOfGenerations(int numberOfGenerations) {
        this.numberOfGenerations = numberOfGenerations;
    }

    public void setNumberOfCurrentGeneration(int numberOfCurrentGeneration) {
        this.numberOfCurrentGeneration = numberOfCurrentGeneration;
    }

    public void setWorld(boolean[][] world) {
        this.world = world;
    }

    public int getWorldSize() {
        return worldSize;
    }
}
