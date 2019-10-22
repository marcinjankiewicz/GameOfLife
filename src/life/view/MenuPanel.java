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
    private JSpinner sizeSpinner;
    private JSpinner seedSpinner;
    private JSpinner numOfGenerationsSpinner;
    private JComboBox<Double> speedCombo;

    public MenuPanel(World world) {
        this.world = world;
        this.container = world.getWindowWorld().getContentPane();
        this.sizeSpinner = createWordSizeJSpinner();
        this.seedSpinner = createSeedSpinner();
        this.numOfGenerationsSpinner = createNumOfGenerationSpinner();
        this.speedCombo = createSpeedComboBox();
        buildButtons();
        buildDescriptionsPanel();
        container.add(sizeSpinner);
        container.add(seedSpinner);
        container.add(numOfGenerationsSpinner);
        container.add(speedCombo);
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
        pause.setLocation(40, 20);
        pause.addActionListener(click -> {
            world.getWorldThread().setStopped(true);
        });
        return pause;
    }

    private JButton buildStartOverButton() {
        JButton startOver = new ActionButton(IconPath.REFRESH);
        startOver.setLocation(80, 20);
        startOver.addActionListener(click -> {
            world.getWorldThread().setStopped(true);
            setStartPoints();
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
                Thread thread = new Thread(world.getWorldThread());
                thread.setDaemon(true);
                thread.start();
                world.getWorldThread().notify();
            }
        });
        return startOver;
    }

    private void setStartPoints() {
        world.setWorldSize((int) sizeSpinner.getValue());
        world.setSeed(Long.parseLong(Integer.toString((int) seedSpinner.getValue())));
        world.setNumberOfGenerations((int) numOfGenerationsSpinner.getValue());
        world.setNumberOfCurrentGeneration(0);
        world.setWorld(new boolean[world.getWorldSize()][world.getWorldSize()]);
        Variables.timeToSleep = (int) ((double) speedCombo.getSelectedItem() * 1000);
    }

    private void buildDescriptionsPanel() {
        setGenerationLabel();
        setAliveCountLabel();
        container.add(generationLabel);
        container.add(aliveCountLabel);
    }

    private JSpinner createWordSizeJSpinner() {
        SpinnerNumberModel model = new SpinnerNumberModel(50, 4, 150, 1);
        DescriptionLabel sizeLabel = new DescriptionLabel();
        sizeLabel.setText("Size: ");
        sizeLabel.setLocation(0, 100);
        container.add(sizeLabel);
        JSpinner jSpinner = new JSpinner(model);
        jSpinner.setLocation(70, 100);
        jSpinner.setSize(50, 20);
        jSpinner.setMaximumSize(new Dimension(100, 20));
        jSpinner.setToolTipText("Size");
        return jSpinner;
    }

    private JSpinner createSeedSpinner() {
        DescriptionLabel seedLabel = new DescriptionLabel();
        seedLabel.setText("Seed: ");
        seedLabel.setLocation(0, 120);
        container.add(seedLabel);
        JSpinner jSpinner = new JSpinner();
        jSpinner.setLocation(70, 120);
        jSpinner.setSize(50, 20);
        jSpinner.setMaximumSize(new Dimension(100, 20));
        jSpinner.setToolTipText("Seed");
        return jSpinner;
    }

    private JSpinner createNumOfGenerationSpinner() {
        SpinnerNumberModel model = new SpinnerNumberModel(50, 1, 200, 1);
        DescriptionLabel generationsLabel = new DescriptionLabel();
        generationsLabel.setText("Amount: ");
        generationsLabel.setLocation(0, 140);
        container.add(generationsLabel);
        JSpinner jSpinner = new JSpinner(model);
        jSpinner.setLocation(70, 140);
        jSpinner.setSize(50, 20);
        jSpinner.setMaximumSize(new Dimension(100, 20));
        jSpinner.setToolTipText("Number of generations");
        return jSpinner;
    }

    private JComboBox<Double> createSpeedComboBox() {
        DescriptionLabel speedLabel = new DescriptionLabel();
        speedLabel.setText("Speed [s]: ");
        speedLabel.setLocation(0, 160);
        container.add(speedLabel);
        JComboBox<Double> comboBox = new JComboBox<>();
        comboBox.addItem(0.25);
        comboBox.addItem(0.50);
        comboBox.addItem(1.0);
        comboBox.addItem(1.5);
        comboBox.addItem(2.0);
        comboBox.addItem(4.0);
        comboBox.addItem(10.0);
        comboBox.setLocation(70, 160);
        comboBox.setSize(50, 20);
        comboBox.setToolTipText("Speed");
        return comboBox;
    }
}
