package org.wasabineko.graphic.menu.editMenu;

import org.wasabineko.graphic.menu.BasicMenu;

public class EditMenu extends BasicMenu {

    public EditMenu() {
        super("Edit");

        this.add(new GroupItem());
        this.add(new UngroupItem());
        this.add(new RenameItem());
    }
}
