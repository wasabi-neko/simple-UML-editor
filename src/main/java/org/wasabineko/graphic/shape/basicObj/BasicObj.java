package org.wasabineko.graphic.shape.basicObj;

import org.wasabineko.graphic.shape.UMLObj;

import javax.swing.*;

public abstract class BasicObj extends UMLObj {
    protected JLabel nameLabel;

    public BasicObj(int posX, int posY, JLabel nameLabel) {
        super();
        this.nameLabel = nameLabel;
        this.setLocation(posX, posY);
    }
}
