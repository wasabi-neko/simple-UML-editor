package org.wasabineko.graphic.shape;

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
    public abstract void setSelected(boolean isSelected);

    public void bringToFront() {
        if (this.getParent() != null) {
            this.getParent().setComponentZOrder(this, 0);
        }
    }
}
