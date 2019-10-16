package life.view;

import life.utils.Counter;
import life.utils.IconPath;
import life.utils.Variables;
import life.world.EvolutionController;
import life.world.World;

import javax.swing.*;
import java.awt.*;

public class MenuPanel {
    private DescriptionLabel generationLabel;
    private DescriptionLabel aliveCountLabel;
    private World world;
    private Container container;

    public MenuPanel(World world) {
        this.world = world;
        this.container = world.getWindowWorld().getContentPane();

        buildButtons();
        buildDescriptionsPanel();
    }

    private void setGenerationLabel() {
        generationLabel = new DescriptionLabel();
        generationLabel.setText("Generation #0");
        generationLabel.setLocation(0, 60);
    }

    private void setAliveCountLabel() {
        aliveCountLabel = new DescriptionLabel();
        aliveCountLabel.setText("Alive: " + Counter.countAlive(world.getWorld()));
        aliveCountLabel.setLocation(0, 80);
    }


    public void setNumberForGenerationLabel(int number) {
        generationLabel.setText("Generation #" + number);
    }

    public void setNumberOfAliveCells(int alive) {
        aliveCountLabel.setText("Alive: " + alive);
    }

    private void buildButtons() {
        container.add(buildRunButton());
        container.add(buildPauseButton());
        container.add(buildStartOverButton());
    }

    private JButton buildRunButton() {
        JButton run = new ActionButton(IconPath.RUN);
        run.setLocation(0, 20);
        run.addActionListener(click -> {
            if (world.getWorldThread().isStopped()) {
                synchronized (world.getWorldThread()) {
                    world.getWorldThread().setStopped(false);
                    world.getWorldThread().notify();
                }
            }
        });
        return run;
    }

    private JButton buildPauseButton() {
        JButton pause = new ActionButton(IconPath.PAUSE);
        pause.setLocation(30, 20);
        pause.addActionListener(click -> {
            world.getWorldThread().setStopped(true);
        });
        return pause;
    }

    private JButton buildStartOverButton() {
        JButton startOver = new ActionButton(IconPath.REFRESH);
        startOver.setLocation(60, 20);
        startOver.addActionListener(click -> {
            world.getWorldThread().setStopped(true);
            world.setStartPoints();
            world.generateLife(world.getSeed());
            world.setEvolutionController(new EvolutionController(world));
            WindowWorld window = world.getWindowWorld();
            window.getGrid().setWorldSize(world.getWorld().length);
            window.getGrid().setWorldGrid(world.getWorld());
            int preferredPanelSize = world.getWorld().length * Variables.cellSize + 25;
            window.getGrid().setPreferredPanelSize(preferredPanelSize);
            window.getGrid().setSize(preferredPanelSize, preferredPanelSize);
            window.setPreferredSizes(world.getWorld().length);
            window.setLocation((window.getScreenWidth() - window.getWidth()) / 2, (window.getScreenHeight() - window.getHeight()) / 2);
            synchronized (world.getWorldThread()) {
                world.getWorldThread().setStopped(false);
                world.getWorldThread().notify();
            }
        });
        return startOver;
    }

    private void buildDescriptionsPanel() {
        setGenerationLabel();
        setAliveCountLabel();
        container.add(generationLabel);
        container.add(aliveCountLabel);
    }
}
