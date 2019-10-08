package life;


import life.world.World;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int worldSize = sc.nextInt();
        long seed = sc.nextLong();
        int numberOfGenerations = sc.nextInt();

        try {
            World world = new World(worldSize, seed, numberOfGenerations);
            world.lifeCycle();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}

