/*
Daniel Kamel and Kalil Bhojani
Grade 12 Computer Science
Ms. Kim, Mr. Knowles
The main object of the game. Allows the user to change it's state by clicking and dragging it
 */
package org.theButton.button;

// Imports
import org.theButton.SoundManager;
import org.theButton.Utilities;
import org.theButton.Game;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    private Color color = Color.RED;
    private final ButtonListener LISTENER;
    private final MovementManager MANAGER;
    private final Game GAME;
    private String label = "";
    private boolean pressed = false;
    private boolean immune = false;
    public Button(Game game) { // class constructor -> adds listeners, sets attributes
        super();
        super.setPreferredSize(new Dimension(50, 50));
        super.setBorderPainted(false);
        LISTENER = new ButtonListener(this);
        super.addMouseListener(LISTENER);
        MANAGER = new MovementManager(this);
        super.addMouseMotionListener(MANAGER);
        this.GAME = game;
    } // End of constructor
    protected void paintComponent(Graphics g) { // Makes a red circle that completely fills the component box
        g.setColor(color);
        g.fillOval(0, 0, this.getWidth(), this.getHeight());
        if (!this.label.isEmpty()) {
            g.setColor(Color.BLACK);
            int fontWidth;
            int x;
            if (this.label.length() == 1) {
                fontWidth = this.getWidth() / 2;
                x = (this.getWidth() / 2) - fontWidth / 2;
            } else {
                fontWidth = (this.getWidth() - this.label.length() * 10) / this.label.length();
                x = (this.getWidth() / 2) - (fontWidth / 2) * (this.label.length() + 1);
            } // End of if else
            g.setFont(new Font("Monospaced", Font.PLAIN, fontWidth * 2 - 11));
            g.drawString(this.label, x, (this.getHeight() + fontWidth) / 2);
        } // End of if else
    } // End of paintComponent
    public void setColour(Color colour) { // Changes the colour to display the button's state
        this.color = colour;
        super.repaint();
    } // End of setColour
    public void setLabel(String label) { // Changes the text displayed on the button
        this.label = label;
        this.repaint();
    } // End of setLabel
    public void kill() { // Removes listeners so button cannot be moved and plays a death animation
        this.removeMouseListener(LISTENER);
        this.removeMouseMotionListener(MANAGER);
        this.setLocation(this.GAME.getPanel().getWidth()/2, this.GAME.getPanel().getHeight()/2);
        this.color = Color.RED;
        setLabel("SCORE: " + this.label);
        int j = 1;
        for (int i = 1; i < 127; i += j) {
            this.setSize(this.getWidth() + i, this.getHeight() + i);
            this.setLocation(this.getX() - i/2, this.getY() - i/2);
            this.repaint();
//            this.color = new Color(255, 2 * i, 2 * i);
            j += 1;
            Utilities.sleep(25);
        } // End of for loop
    } // End of kill
    public boolean isPressed() { // Helper method
        return pressed;
    } // End of isPressed
    public void setPressed(boolean pressed) { // Changes the state of the button
        if (pressed) {
            this.GAME.getSoundManager().setMusic(SoundManager.Sound.ACTIVE);
        } else {
            this.GAME.getSoundManager().setMusic(SoundManager.Sound.PASSIVE);
        } // End of if else
        this.pressed = pressed;
    } // End of setPressed
    public void setImmune(boolean immune) { // Helper Method
        this.immune = immune;
    } // End of setImmune
    public boolean isImmune() { // Helper Method
        return immune;
    } // End of isImmune
}