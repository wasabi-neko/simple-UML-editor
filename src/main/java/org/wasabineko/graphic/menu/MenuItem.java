package org.wasabineko.graphic.menu;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class MenuItem extends JMenuItem implements ActionListener {

    public MenuItem(String name) {
        super(name);
        this.addActionListener(this);
    }

    public abstract boolean isInWorkingCondition();
}
