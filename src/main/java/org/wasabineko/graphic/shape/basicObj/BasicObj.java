package org.wasabineko.graphic.shape.basicObj;

import org.jetbrains.annotations.NotNull;
import org.wasabineko.graphic.shape.UMLObj;
import org.wasabineko.graphic.shape.basicObj.portObj.Port;
import org.wasabineko.graphic.shape.basicObj.portObj.portOverlay.PortOverlay;
import org.wasabineko.graphic.shape.connectionLine.ConnectionLine;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public abstract class BasicObj extends UMLObj {
    private GroupObj groupParent;
    final private boolean isConnectAble;
    protected boolean selected = false;
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

    public PortOverlay getPortOverlay() {
        return this.portOverlay;
    }

    public Port getClosetPort(int x, int y) {
        return this.portOverlay.getClosetPort(x, y);
    }

    //------------------------------------------------------------
    // Group relative Methods
    //------------------------------------------------------------
    public GroupObj getGroupParent() {
        return this.groupParent;
    }

    public void setGroupParent(GroupObj group) {
        this.groupParent = group;
    }

    @Override
    public UMLObj getTopParent() {
        if (groupParent == null) {
            return this;
        } else {
            return groupParent.getTopParent();
        }
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

        this.getParent().setComponentZOrder(this, 0);
        Container canvas = this.getTopParent().getParent();

        for (Port port : this.portOverlay.getPortList()) {
            if (port.getLineList() != null) {
                for (ConnectionLine line : port.getLineList()) {
                    canvas.setComponentZOrder(line, 0);
                }
                port.repaintConnectedLine();
            }
        }
    }

    public void repaintConnectedLine() {
        for (Port port : this.portOverlay.getPortList()) {
            port.repaintConnectedLine();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        this.paintSelf(g2d);
        paintChildren(g);
    }
}
