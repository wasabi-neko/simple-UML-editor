package org.wasabineko.graphic.shape;

import org.wasabineko.graphic.shape.basicObj.portObj.Port;

import javax.swing.*;
import java.awt.event.MouseEvent;

public abstract class UMLObj extends JPanel {
    public UMLObj() {
        super();
    }

    public abstract boolean isInShape(MouseEvent event);

    public abstract UMLObj getTopParent();
    public abstract boolean isSelectable();
    public abstract boolean isConnectAble();

    public void bringToFront() {
        if (this.getParent() != null) {
            this.getParent().setComponentZOrder(this, 0);
        }
    }

    /* percolating up the method from this class's child */
    public void setSelected(boolean isSelected) { /*do nothing*/ }
    public Port getClosetPort(int x, int y) {
        return null;
    }
}
