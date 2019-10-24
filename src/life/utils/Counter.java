package life.utils;

public class Counter {

    public static int countAlive(boolean[][] world) {
        int a = 0;
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                if (world[i][j])
                    a++;
            }
        }
        return a;
    }
}
