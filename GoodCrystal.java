package org.theButton.crystal;

import org.theButton.Game;

import java.awt.*;

public class GoodCrystal extends AbstractCrystal {
    private Game game;
    public GoodCrystal(Game game) {
        super(game);
        this.game = game;
    }
    protected void paintComponent(Graphics g) {
        g.setColor(new Color(49, 124, 0));
        g.fillOval(0, 0, 25, 25);
    }

    protected void touchAction() {
        if (!game.getButton().isPressed())
            return;
        game.addPoint();
        super.stop = true;
        this.setLocation(super.pickPoint());
    }
}