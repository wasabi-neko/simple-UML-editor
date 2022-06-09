package org.wasabineko.graphic.shape.basicObj;

import org.wasabineko.graphic.shape.GroupObj;
import org.wasabineko.graphic.shape.UMLObj;
import org.wasabineko.graphic.shape.basicObj.portObj.Port;
import org.wasabineko.graphic.shape.basicObj.portObj.portOverlay.PortOverlay;
import org.wasabineko.graphic.shape.connectionLine.ConnectionLine;

import javax.swing.*;
import java.awt.*;

public abstract class BasicObj extends UMLObj {
    final private boolean isConnectAble;
    protected final PortOverlay portOverlay;
    protected final JLabel nameLabel;

    public BasicObj(int posX, int posY, boolean isConnectAble, JLabel nameLabel, PortOverlay portOverlay) {
        super();
        this.isConnectAble = isConnectAble;
        this.nameLabel = nameLabel;
        this.portOverlay = portOverlay;
        this.setLocation(posX, posY);
    }

    //------------------------------------------------------------
    // Basic
    //------------------------------------------------------------
    @Override
    public boolean isConnectAble() {
        return this.isConnectAble;
    }

    public String getLabelName() {
        return this.nameLabel.getText();
    }

    public void setLabelName(String newName) {
        this.nameLabel.setText(newName);
    }

    public Port getClosetPort(int x, int y) {
        return this.portOverlay.getClosetPort(x, y);
    }


    //------------------------------------------------------------
    // Select relative Methods
    //------------------------------------------------------------
    @Override
    public boolean isSelectable() {
        return true;
    }

    @Override
    public void setSelected(boolean isSelected) {
        this.selected = isSelected;
        this.portOverlay.setVisible(isSelected);
        this.repaint();
    }

    //------------------------------------------------------------
    // Drawing relative Methods
    //------------------------------------------------------------
    abstract public void paintSelf(Graphics2D g2d);

    @Override
    public void bringToFront() {
        if (this.getTopParent().getParent() == null) {
            return;
        }

        this.getParent().setComponentZOrder(this, 0);  // bring basicObj to front
        this.portOverlay.bringAllLineToFront();     // bring its lines to front
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        this.paintSelf(g2d);
        paintChildren(g);
    }
}
