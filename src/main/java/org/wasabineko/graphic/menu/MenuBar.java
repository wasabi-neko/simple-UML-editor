package org.wasabineko.graphic.menu;

import org.wasabineko.GeneralConfig;
import org.wasabineko.graphic.menu.editMenu.EditMenu;

import javax.swing.*;

public class MenuBar extends JMenuBar {

    public MenuBar() {
        JMenu fileMenu = new JMenu("File");
        EditMenu editMenu = new EditMenu();

        this.add(fileMenu);
        this.add(editMenu);

        this.setPreferredSize(GeneralConfig.getInstance().getDefaultMenuSize());
    }
}
