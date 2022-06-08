package org.wasabineko.graphic.Menu.editMenu;

import org.wasabineko.graphic.Menu.BasicMenu;

public class EditMenu extends BasicMenu {

    public EditMenu() {
        super("Edit");

        this.add(new GroupItem());
        this.add(new UngroupItem());
        this.add(new RenameItem());
    }
}
