package life.view;


import life.utils.Counter;
import life.utils.Variables;
import life.world.World;

import javax.swing.*;
import java.awt.*;

public class WindowWorld extends JFrame {
    private World world;
    private MenuPanel menuPanel;
    private int worldSize;
    private GridCells grid;
    private int screenWidth;
    private int screenHeight;

    public WindowWorld() {
        super("Game of Life");
        this.world = new World(this);
        this.worldSize = world.getWorld().length;
        this.grid = new GridCells(world.getWorld());
        this.grid.setLocation(100, 0);
        this.menuPanel = new MenuPanel(world);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSizes();


        setLayout(null);
        add(grid);
        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((screenWidth - getWidth())/2, (screenHeight - getHeight())/2);
        setVisible(true);
        setResizable(true);
        world.start();
    }

    private void setPreferredSizes() {
        this.setPreferredSize(new Dimension(preferredWindowXSize(), preferredWindowYSize()));
        this.setSize(preferredWindowXSize(), preferredWindowYSize());
    }

    public int preferredWindowXSize(){
        return worldSize * Variables.cellSize + grid.getLocation().x + 25;
    }
    public int preferredWindowYSize(){
        return worldSize * Variables.cellSize + 100;
    }

    public void setPreferredSizes(int worldSize) {
        this.worldSize = worldSize;
        setPreferredSizes();
    }


    public void manageWindow(int numberOfGeneration) {
        grid.setWorldGrid(world.getWorld());
        menuPanel.setNumberForGenerationLabel(numberOfGeneration);
        menuPanel.setNumberOfAliveCells(Counter.countAlive(world.getWorld()));
        this.repaint();
    }

    public GridCells getGrid() {
        return grid;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}
