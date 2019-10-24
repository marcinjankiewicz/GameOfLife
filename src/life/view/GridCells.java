package life.view;


import life.utils.Variables;

import javax.swing.*;
import java.awt.*;

public class GridCells extends JPanel {
    private boolean[][] worldGrid;
    private int worldSize;
    private int preferredPanelSize;

    public GridCells(boolean[][] worldGrid) {
        this.worldGrid = worldGrid;
        this.worldSize = worldGrid.length;
        this.preferredPanelSize = worldSize * Variables.cellSize + 25;
        this.setSize(preferredPanelSize, preferredPanelSize);
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
        int y = 20;
        for (int horz = 0; horz < worldSize; horz++) {
            int x = 0;
            for (int vert = 0; vert < worldSize; vert++) {
                g.drawRect(x, y, Variables.cellSize, Variables.cellSize);
                if (worldGrid[horz][vert]) {
                    g.setColor(cellColor);
                    g.fillRect(x + 1, y + 1, Variables.cellSize - 1, Variables.cellSize - 1);
                    g.setColor(lineColor);
                }
                x += Variables.cellSize;
            }
            y += Variables.cellSize;
        }
        g2d.dispose();
    }

    public void setWorldGrid(boolean[][] worldGrid) {
        this.worldGrid = worldGrid;
    }

    public void setWorldSize(int worldSize) {
        this.worldSize = worldSize;
    }

    public void setPreferredPanelSize(int preferredPanelSize) {
        this.preferredPanelSize = preferredPanelSize;
    }
}
