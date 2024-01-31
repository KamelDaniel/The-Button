package org.theButton.button;

import org.theButton.button.Button;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MovementManager implements MouseMotionListener {
    private Button button;
    public MovementManager(Button button) {
        this.button = button;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!SwingUtilities.isLeftMouseButton(e))
            return;
        if (button.getParent().getBounds().contains(e.getLocationOnScreen()))
            button.setLocation((int)(e.getLocationOnScreen().getX()-button.getWidth()/2), (int)e.getLocationOnScreen().getY()-button.getHeight());
       button.setImmune(false);
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
}