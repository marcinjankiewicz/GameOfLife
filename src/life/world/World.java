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

    private World() {
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

    public void printWorld() {
        windowWorld.manageWindow(numberOfCurrentGeneration);
    }

    public void lifeCycle() {
        evolutionController.setWorldGrid(world);
        this.world = evolutionController.birthControl();
        numberOfCurrentGeneration++;
    }

    public void createNewWorld(int worldSize, long seed, int numberOfGenerations) {
        setStartPoints(worldSize, seed, numberOfGenerations);
        generateLife(seed);
    }

    private void setStartPoints(int worldSize, long seed, int numberOfGenerations) {
        setStartPoints(worldSize, seed, numberOfGenerations, 0);
    }

    public void setStartPoints(int worldSize, long seed, int numberOfGenerations, int numberOfCurrentGeneration) {
        this.worldSize = worldSize;
        this.seed = seed;
        this.numberOfGenerations = numberOfGenerations;
        this.numberOfCurrentGeneration = numberOfCurrentGeneration;
        this.world = new boolean[worldSize][worldSize];
        setEvolutionController(new EvolutionController(this));
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

    public void setWorld(boolean[][] world) {
        this.world = world;
    }

    public int getWorldSize() {
        return worldSize;
    }
}
