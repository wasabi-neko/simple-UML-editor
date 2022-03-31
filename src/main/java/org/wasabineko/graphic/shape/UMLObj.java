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
    public abstract void setSelected(boolean isSelected);
}
