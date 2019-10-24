package life.utils;

import java.io.IOException;

//This class is unused, left for purpose
public class WorldPrinter {
    public void printWorld(boolean[][] world, int generation) {
        System.out.println("Generation #" + generation);
        System.out.println("Alive: " + Counter.countAlive(world));
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                System.out.print(world[i][j] ? "O" : " ");
            }
            System.out.println();
        }
        System.out.println();

        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


}
