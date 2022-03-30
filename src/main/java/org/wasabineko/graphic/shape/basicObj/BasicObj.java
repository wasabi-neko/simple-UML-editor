package org.wasabineko.graphic.shape.basicObj;

import org.jetbrains.annotations.NotNull;
import org.wasabineko.graphic.shape.UMLObj;

import javax.swing.*;
import java.util.Objects;

public abstract class BasicObj extends UMLObj {
    private GroupObj groupParent;
    protected JLabel nameLabel;

    public BasicObj(int posX, int posY, JLabel nameLabel) {
        super();
        this.nameLabel = nameLabel;
        this.setLocation(posX, posY);
    }

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
        setLocation(this.getX() - group.getX(), this.getY() - group.getY());
        group.setComponentZOrder(this, 0);
    }

    /**
     * Remove this object from group, and add it back to the groupObject's parent.
     * The coordinate of the object will be shifted back to the relevant coordinate
     * with the upper parent
     * @param group the group object which to be removed from
     */
    public void unGroup(@NotNull GroupObj group) {
        Objects.requireNonNull(group, "groupObj should not be null");
        this.groupParent = null;
        setLocation(this.getX() + group.getX(), this.getY() + group.getY());
        group.remove(this);
        group.getParent().add(this);
    }

    @Override
    public UMLObj getTopParent() {
        if (groupParent == null) {
            return this;
        } else {
            return groupParent.getTopParent();
        }
    }
}
