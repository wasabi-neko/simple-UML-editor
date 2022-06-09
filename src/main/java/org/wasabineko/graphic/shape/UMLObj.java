package org.wasabineko.graphic.shape;

import org.wasabineko.graphic.shape.basicObj.portObj.Port;

import javax.swing.*;
import java.awt.event.MouseEvent;

public abstract class UMLObj extends JPanel {
    protected boolean selected = false;
    protected UMLObj groupParent;

    public UMLObj() {
        super();
    }

    //------------------------------------------------------------
    // Basic Methods
    //------------------------------------------------------------
    public abstract boolean isInShape(MouseEvent event);
    public abstract boolean isSelectable();     // selectable means an obj can be selected and moved by mouse
    public abstract boolean isConnectAble();    // connectable means an obj can be connected by connection lines

    public void bringToFront() {
        if (this.getParent() != null) {
            this.getParent().setComponentZOrder(this, 0);
        }
    }

    public void setSelected(boolean isSelected) {
        this.selected = isSelected && isSelectable();
        this.repaint();
    }


    //------------------------------------------------------------
    // Group relative Methods
    //------------------------------------------------------------
    public void setGroupParent(GroupObj group) {
        this.groupParent = group;
    }

    public UMLObj getTopParent() {
        if (groupParent == null) {
            return this;
        } else {
            return groupParent.getTopParent();
        }
    }

    public void ungroup() {}

    //------------------------------------------------------------
    // percolating up the method from this class's child
    //------------------------------------------------------------
    public Port getClosetPort(int x, int y) {
        return null;
    }
    public void setLabelName(String newName) { /* do nothing */ }

    public Object getLabelName() { return null; }
}
