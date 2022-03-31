package org.wasabineko.graphic.shape;

import javax.swing.*;

public abstract class UMLObj extends JPanel {
    public UMLObj() {
        super();
    }

    public abstract boolean isInShape(int x, int y);

    public abstract UMLObj getTopParent();
}
