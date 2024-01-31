package org.theButton.crystal;

import org.theButton.Game;
import org.theButton.Utilities;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CrystalManager {
    private int points;
    private int lastPoint;
    private final Game GAME;
    private final ArrayList<EvilCrystal> crystals = new ArrayList<>();
    private final GoodCrystal goodCrystal;

    public CrystalManager(Game game) {
        this.GAME = game;
//        createEvilCrystal();
//        createEvilCrystal();
        goodCrystal = createGoodCrystal();
    }
    public ArrayList<EvilCrystal> getCrystals() {
        return this.crystals;
    }

    public void createEvilCrystal() {
        EvilCrystal crystal = new EvilCrystal(this.GAME);
        this.crystals.add(crystal);
        this.GAME.getButton().setImmune(true);

        this.GAME.getPanel().add(crystal);
        this.GAME.repaint();
        int x = this.GAME.getButton().getX();
        int y = this.GAME.getButton().getY();
        Dimension retSize = this.GAME.getSize();
        Point retLoc = this.GAME.getButton().getLocation();
        this.GAME.setSize(new Dimension(10000, 10000));
        this.GAME.setSize(retSize);
        this.GAME.getButton().setLocation(new Point(x, y));
       // this.GAME.getButton().setLocation(retLoc);
        this.GAME.getButton().doClick();
    }

    public GoodCrystal createGoodCrystal() {
        GoodCrystal crystal = new GoodCrystal(this.GAME);
        this.GAME.getPanel().add(crystal);
        this.GAME.repaint();

        return crystal;
    }
}