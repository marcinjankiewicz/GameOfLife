package life.utils;

import life.world.World;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Saver {
    private World world;


    private Saver() {
        File file = new File(Constants.FILE_DIRECTORY);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public Saver(World world) {
        this();
        this.world = world;
    }

    public void saveWorld() throws IOException {
        File file = new File(Constants.FILE_PATH);
        FileWriter writer = new FileWriter(file);
        PrintWriter pw = new PrintWriter(writer);
        printWorld(pw);
        writer.close();
        pw.close();
    }

    private void printWorld(PrintWriter pw) {
        pw.println(world.getNumberOfCurrentGeneration() + " " + world.getNumberOfGenerations());
        pw.println(world.getWorldSize());
        pw.println(world.getSeed());
        for (int i = 0; i < world.getWorld().length; i++) {
            for (int j = 0; j < world.getWorld()[i].length; j++) {
                pw.print(world.getWorld()[i][j] ? "O" : " ");
            }
            pw.println();
        }
    }


}
