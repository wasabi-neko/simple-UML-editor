package org.wasabineko.graphic.menu;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BasicMenu extends JMenu {
    final ArrayList<MenuItem> itemList = new ArrayList<>();

    public BasicMenu(String name) {
        super(name);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                for (MenuItem item : itemList) {
                    item.setEnabled(item.isInWorkingCondition());
                }
            }
        });
    }

    public MenuItem add(MenuItem menuItem) {
        super.add(menuItem);
        this.itemList.add(menuItem);
        return menuItem;
    }
}
