package org.wasabineko.graphic.shape.basicObj;

import org.wasabineko.graphic.shape.UMLObj;

import javax.swing.*;

public abstract class BasicObj extends UMLObj {
    private GroupObj parent;
    protected JLabel nameLabel;

    public BasicObj(int posX, int posY, JLabel nameLabel) {
        super();
        this.nameLabel = nameLabel;
        this.setLocation(posX, posY);
    }

    public GroupObj getGroupParent() {
        return this.parent;
    }

    public void setGroupParent(GroupObj group) {
        this.parent = group;
        setLocation(this.getX() - group.getX(), this.getY() - group.getY());
        group.setComponentZOrder(this, 0);
    }

    public void unGroup(GroupObj group) {
        this.parent = null;
        setLocation(this.getX() + group.getX(), this.getY() + group.getY());
        group.remove(this);
    }


    @Override
    public UMLObj getTopParent() {
        if (parent == null) {
            return this;
        } else {
            return parent.getTopParent();
        }
    }
}
