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


    public World() {
        setStartPoints();
        this.printer = new WorldPrinter();
        this.evolutionController = new EvolutionController(this);
        generateLife(seed);
    }

    public World(WindowWorld windowWorld) {
        this();
        this.windowWorld = windowWorld;
    }

    public void setStartPoints() {
        this.worldSize = sc.nextInt();
        this.seed = sc.nextLong();
        this.numberOfGenerations = sc.nextInt();
        this.numberOfCurrentGeneration = 0;
        this.world = new boolean[worldSize][worldSize];
    }

    public void generateLife(long seed) {
        LifeGenerator lifeGenerator = new LifeGenerator(seed);
        lifeGenerator.populateWorld(world);
    }

    public void start() {
        worldThread = new WorldThread(this);
        Thread thread = new Thread(worldThread);
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
}
