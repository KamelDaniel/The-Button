package org.theButton.crystal;

import org.theButton.Game;
import org.theButton.Utilities;
import org.theButton.button.Button;

import java.awt.*;
import java.util.Random;
public class EvilCrystal extends AbstractCrystal {
    private Game game;
    Random r = new Random();
    private Mode mode;
    private Color color;
    private final Button BUTTON;
    private final Color DEFAULT_COLOR = this.color = new Color(r.nextInt(255), 0, r.nextInt(255));
    private boolean isAlive = true;

    public EvilCrystal(Game game) {
        super(game);
        this.game = game;
        this.BUTTON = this.game.getButton();
    }
    @Override
    public void run() {
        this.game.repaint();
        while(true) {
            pickMode();
            switch (this.mode) {
                case NORMAL:
                    this.changeSpeed(1);
                    this.color = this.DEFAULT_COLOR;
                    this.goTo(this.pickPoint());
                    break;
                case TRACKER:
                    Color flashColor = new Color(this.DEFAULT_COLOR.getRed(), 200, this.DEFAULT_COLOR.getBlue());
                    this.changeSpeed(5);
                    for (int i = 0; i < 5; i++) {
                        this.game.repaint();
                        this.color = this.DEFAULT_COLOR;
                        Utilities.sleep(125);
                        this.game.repaint();
                        this.color = flashColor;
                        Utilities.sleep(125);
                    }
                    this.color = this.DEFAULT_COLOR;
                    double x = BUTTON.getX() + (BUTTON.getSize().getWidth() / 2);
                    double y = BUTTON.getY() + (BUTTON.getSize().getHeight() / 2);
                    this.goTo(new Point((int) x, (int) y));
                    this.changeSpeed(1);
                    break;
                case FAST:
                    for (int i = 1; i <= 5; i++) {
                        this.goTo(this.pickPoint());
                        this.changeSpeed(this.getSpeed() + i);
                    }
            }
        }
    }

    protected void paintComponent(Graphics g) {
        g.setColor(this.color);
        g.fillOval(0, 0, 25, 25);
    }

    protected void touchAction() {
        if (!game.getButton().isPressed() || game.getButton().isImmune())
            return;
        game.end();
    }

    public Mode getMode() {
        return mode;
    }

    private enum Mode {
        NORMAL,
        TRACKER,
        FAST
    }

    private void pickMode() {
        int num = r.nextInt(100);
        if (num < 85) {
            mode = Mode.NORMAL;
        } else if (num < 95) {
            mode = Mode.TRACKER;
        } else {
            mode = Mode.FAST;
        }
    }
}