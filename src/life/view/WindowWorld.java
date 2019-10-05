package life.view;


import life.utils.Constants;
import life.utils.Counter;
import life.world.World;

import javax.swing.*;
import java.awt.*;

public class WindowWorld extends JFrame {
    private World world;
    private int worldSize;
    private int preferredWindowXSize;
    private int preferredWindowYSize;
    private DescriptionLabel generationLabel;
    private DescriptionLabel aliveCountLabel;
    private GridCells grid;

    public WindowWorld(World world) {
        super("Game of Life");
        this.world = world;
        this.worldSize = world.getWorld().length;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSizes();
        setSize(preferredWindowXSize, preferredWindowYSize);
        this.setPreferredSize(new Dimension(preferredWindowXSize, preferredWindowYSize));
        grid = new GridCells(world.getWorld());
        setGenerationLabel();
        setAliveCountLabel();
        add(generationLabel);
        add(aliveCountLabel);
        add(grid);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setPreferredSizes() {
        this.preferredWindowXSize = worldSize * Constants.CELL_SIZE + 50;
        this.preferredWindowYSize = worldSize * Constants.CELL_SIZE + 100;
    }

    private void setGenerationLabel() {
        generationLabel = new DescriptionLabel();
        generationLabel.setBounds(0, 0, 150, 20);
        generationLabel.setText("Generation #0");
    }

    private void setAliveCountLabel() {
        aliveCountLabel = new DescriptionLabel();
        aliveCountLabel.setBounds(0, 15, 150, 20);
        aliveCountLabel.setText("Alive: " + Counter.countAlive(world.getWorld()));
    }


    public void setNumberForGenerationLabel(int number) {
        generationLabel.setText("Generation #" + number);
    }

    public void setNumberOfAliveCells(int alive) {
        aliveCountLabel.setText("Alive: " + alive);
    }

    public GridCells getGrid() {
        return grid;
    }
}
