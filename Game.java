package org.theButton;
// Imports
import org.theButton.button.Button;
import org.theButton.crystal.CrystalManager;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Game extends JFrame {
    private final JPanel panel = new JPanel();
    private final Button THE_BUTTON = new Button(this);
    private int points = 0;
    private final CrystalManager crystalManager;
    private final SoundManager soundManager;
    public Game() {
        super("The Button");
        panel.add(THE_BUTTON);

        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(new Dimension(500, 500));
        super.setExtendedState(MAXIMIZED_BOTH);
        super.setContentPane(panel);
        super.repaint();
        super.setVisible(true);
        crystalManager = new CrystalManager(this);
        Utilities.sleep(500);
        this.repaint();
        this.setSize(1920 - points * 5, 1080 - points * 5);

        soundManager = new SoundManager();
        soundManager.setMusic(SoundManager.Sound.PASSIVE);
//        for (int i = 0; i < 50; i++)
//            crystalManager.createEvilCrystal();
        Random r = new Random();
        while (true) {
            panel.setBackground(new Color(r.nextInt(255), 0, r.nextInt(255)));
            Utilities.sleep(1000);
        }
    }
    public SoundManager getSoundManager() {
        return this.soundManager;
    }
    public JPanel getPanel() {
        return this.panel;
    }
    public Button getButton() {
        return this.THE_BUTTON;
    }

    public void end() {
        this.soundManager.playSound(SoundManager.Sound.DEATH);
        this.getButton().kill();
        Utilities.sleep(1);
        this.removeAll();
        Utilities.sleep(2500);
        System.exit(0);
    }

    public void addPoint() {
        this.soundManager.playSound(SoundManager.Sound.COLLECT);
        points += 1;
        THE_BUTTON.setLabel(String.valueOf(points));
        crystalManager.createEvilCrystal();
        this.repaint();
        if (points % 5 == 0)
            crystalManager.createGoodCrystal();
    }

    public int getPoints() {
        return this.points;
    }

    public CrystalManager getCrystalManager() {
        return this.crystalManager;
    }
}