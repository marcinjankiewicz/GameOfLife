package life.view;


import life.utils.Constants;

import javax.swing.*;
import java.awt.*;

public class GridCells extends JPanel {
    private boolean[][] worldGrid;
    private int worldSize;
    private int preferredPanelSize;

    public GridCells(boolean[][] worldGrid) {
        this.worldGrid = worldGrid;
        this.worldSize = worldGrid.length;
        this.preferredPanelSize = worldSize * Constants.CELL_SIZE;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(preferredPanelSize, preferredPanelSize);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        Color lineColor = new Color(0x020202);
        Color cellColor = new Color(0x00340B);
        g.setColor(lineColor);
        int y = 2 * (Constants.FONT_SIZE + 3);
        for (int horz = 0; horz < worldSize; horz++) {
            int x = 0;
            for (int vert = 0; vert < worldSize; vert++) {
                g.drawRect(x, y, Constants.CELL_SIZE, Constants.CELL_SIZE);
                if (worldGrid[horz][vert]) {
                    g.setColor(cellColor);
                    g.fillRect(x+1, y+1, Constants.CELL_SIZE-1, Constants.CELL_SIZE-1);
                    g.setColor(lineColor);
                }
                x += Constants.CELL_SIZE;
            }
            y += Constants.CELL_SIZE;
        }
        g2d.dispose();
    }

    public void setWorldGrid(boolean[][] worldGrid) {
        this.worldGrid = worldGrid;
    }
}
