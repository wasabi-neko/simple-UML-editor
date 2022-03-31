package org.wasabineko.graphic.shape.basicObj;

import org.jetbrains.annotations.NotNull;
import org.wasabineko.graphic.shape.UMLObj;
import org.wasabineko.graphic.shape.basicObj.portObj.PortOverlay;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public abstract class BasicObj extends UMLObj {
    private GroupObj groupParent;
    protected boolean selected = false;
    protected PortOverlay portOverlay;
    protected JLabel nameLabel;

    public BasicObj(int posX, int posY, JLabel nameLabel, PortOverlay portOverlay) {
        super();
        this.nameLabel = nameLabel;
        this.portOverlay = portOverlay;
        this.setLocation(posX, posY);
    }

    //------------------------------------------------------------
    // Basic
    //------------------------------------------------------------
    public String getLabelName() {
        return this.nameLabel.getText();
    }

    public void setLabelName(String newName) {
        this.nameLabel.setText(newName);
    }


    //------------------------------------------------------------
    // Group relative Methods
    //------------------------------------------------------------
    public GroupObj getGroupParent() {
        return this.groupParent;
    }

    /**
     * Remove the objet itself from canvas, and add it to provided groupObject
     * The coordinate of the object will be shifted to the relevant coordinate
     * with the GroupObjet
     * @param group the group object which to add this object in
     */
    public void setGroupParent(@NotNull GroupObj group) {
        Objects.requireNonNull(group, "groupObj should not be null");

        this.groupParent = group;
        this.getParent().remove(this);
        group.add(this);
        setLocation(this.getX() - group.getX(), this.getY() - group.getY());
        group.setComponentZOrder(this, 0);
    }

    /**
     * Remove this object from group, and add it back to the groupObject's parent.
     * The coordinate of the object will be shifted back to the relevant coordinate
     * with the upper parent
     * @param group the group object which to be removed from
     */
    public void ungroupFromGroup(@NotNull GroupObj group, Container groupParent) {
        Objects.requireNonNull(group, "groupObj should not be null");
        this.groupParent = null;
        setLocation(this.getX() + group.getX(), this.getY() + group.getY());
        group.remove(this);
        groupParent.add(this);
        groupParent.setComponentZOrder(this, 0);
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
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        this.paintSelf(g2d);
        paintChildren(g);
    }
}
