package org.wasabineko.graphic;

import org.wasabineko.GeneralConfig;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    public MenuBar() {
        super();
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");

        JMenuItem groupItem = new JMenuItem("group");
        JMenuItem ungroupItem = new JMenuItem("ungroup");
        JMenuItem renameItem = new JMenuItem("rename");

        editMenu.add(renameItem);
        editMenu.add(groupItem);
        editMenu.add(ungroupItem);

        this.add(fileMenu);
        this.add(editMenu);

        this.setPreferredSize(GeneralConfig.getInstance().getDefaultMenuSize());

    }
}
