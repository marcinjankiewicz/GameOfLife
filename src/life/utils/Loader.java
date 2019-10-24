package life.utils;

import life.world.World;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Loader {
    private World world;

    public Loader(World world) {
        this.world = world;
    }

    public void loadWorld() throws FileNotFoundException {
        File file = new File(Constants.FILE_PATH);
        Scanner scanner = new Scanner(file);
        int numberOfCurrentGeneration = scanner.nextInt();
        int numberOfGenerations = scanner.nextInt();
        int worldSize = scanner.nextInt();
        long seed = scanner.nextLong();
        boolean[][] worldGrid = aliveOrganismRecognizer(scanner, worldSize);
        world.setStartPoints(worldSize, seed, numberOfGenerations, numberOfCurrentGeneration);
        world.setWorld(worldGrid);
        Variables.timeToSleep = 2000;
        scanner.close();
    }

    private boolean[][] aliveOrganismRecognizer(Scanner scanner, int worldSize) {
        boolean[][] world = new boolean[worldSize][worldSize];
        scanner.nextLine();
        for (int i = 0; i < worldSize; i++) {
            char[] lineArray = scanner.nextLine().toCharArray();
            for (int j = 0; j < lineArray.length; j++) {
                if (lineArray[j] == 'O') {
                    world[i][j] = true;
                }
            }
        }
        return world;
    }
}
