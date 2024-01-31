package org.theButton.crystal;

import org.theButton.Game;
import org.theButton.Utilities;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.ArrayList;

public abstract class AbstractCrystal extends JComponent implements Runnable {

    private final Game GAME;
    private final Thread THREAD;
    private double speed = 1;
    protected boolean stop = false;

    public AbstractCrystal(Game game) {
        super();
        this.GAME = game;
        super.setPreferredSize(new Dimension(50, 50));
        this.THREAD = new Thread(this);
        this.THREAD.start();
    }

    public void run() {
        this.GAME.repaint();
        while(true)
            this.goTo(this.pickPoint());
    }
    private ArrayList<Point> generatePath(Point end) {
        ArrayList<Point> path = new ArrayList<>();
        path.add(this.getLocation());

        float xA = this.getX(); // x coordinate of starting point
        float yA = this.getY(); // y coordinate of starting point
        float xB = (float) end.getX(); // x coordinate of end point
        float yB = (float) end.getY(); // y coordinate of end point

        float l = xB-xA; // length of the right triangle formed by the points
        float h = yB-yA; // height of the right triangle formed by the points

        float pathLen = (float) Math.sqrt(Math.pow(l, 2) + Math.pow(h, 2));

        float numOfDivs = (float)  (pathLen / (5 * speed));
        float xDivLen = l / numOfDivs;
        float yDivLen = h / numOfDivs;

        for (int i = 0; i < numOfDivs; i++)
            path.add(new Point((int) (path.get(i).x + xDivLen), (int) (path.get(i).y + yDivLen)));
        return path;
    }
    protected void goTo(Point point) {
        ArrayList<Point> path = this.generatePath(point);
        for (Point i : path) {
            for (int x = 0; x < 5; x++) {
                if (isTouchingButton()) {
                    touchAction();
                }
                if (this.stop) {
                    this.stop = false;
                    return;
                }
                Utilities.sleep(2);
            }
            this.setLocation(i);
            this.GAME.repaint();
            this.repaint();
            this.GAME.getPanel().repaint();
        }
    }

    protected abstract void touchAction();

    public Point pickPoint() {
        Random r = new Random();
        int x = r.nextInt(0, this.GAME.getPanel().getWidth());
        int y = r.nextInt(0, this.GAME.getPanel().getHeight());
        return new Point(x, y);
    }

    public boolean isTouchingButton() {
        return this.GAME.getButton().getLocation().distance(this.getLocation()) <= 25;
    }

    public Thread getThread() {
        return THREAD;
    }
    public int getID() {
        return this.GAME.getCrystalManager().getCrystals().indexOf(this);
    }

    protected void changeSpeed(double speed) {
        this.speed = speed;
    }

    protected double getSpeed() {
        return speed;
    }
}