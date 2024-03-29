package life.utils;

import life.world.World;

public class WorldThread implements Runnable {
    private volatile boolean isStopped = false;
    private World world;

    public WorldThread(World world) {
        this.world = world;
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                while (world.getNumberOfCurrentGeneration() < world.getNumberOfGenerations() + 1 && !isStopped) {
                    world.printWorld();
                    Thread.sleep(Variables.timeToSleep);
                    if (isStopped) {
                        System.out.println("Going to wait()");
                        this.wait();
                    }
                    world.lifeCycle();
                }

            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }


    public void setStopped(boolean stopped) {
        isStopped = stopped;
    }

    public boolean isStopped() {
        return isStopped;
    }

}
