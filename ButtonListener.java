/*
Daniel Kamel and Kalil Bhojani
Grade 12 Computer Science
Ms. Kim, Mr. Knowles
Switches the button state on click
*/
package org.theButton.button;

// Imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class ButtonListener implements MouseListener {
    private org.theButton.button.Button button;
    private boolean isPressed = false;

    public ButtonListener(Button button) {
        this.button = button;
    }

    @Override
    public void mouseClicked(MouseEvent e) { // Mouse is pressed and released
        button.setLocation((int)(e.getLocationOnScreen().getX()-30), (int)e.getLocationOnScreen().getY()-50);

    }

    @Override
    public void mousePressed(MouseEvent e) { // Mouse is pressed
        if (!SwingUtilities.isLeftMouseButton(e))
            return;
        button.setColour(Color.BLUE);
        isPressed = true;
        button.setPressed(isPressed);
    }

    @Override
    public void mouseReleased(MouseEvent e) { // Mouse is released
        if (!SwingUtilities.isLeftMouseButton(e))
            return;
        button.setColour(Color.RED);
        isPressed = false;
        button.setPressed(isPressed);
    }

    @Override
    public void mouseEntered(MouseEvent e) { // Mouse enters the bounds of an object
    }

    @Override
    public void mouseExited(MouseEvent e) { // Mouse exits the bounds of an object
        if (button.isPressed())
            button.setLocation(e.getLocationOnScreen());
    }
}