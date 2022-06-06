package org.wasabineko.graphic.Menu;

import org.wasabineko.GeneralConfig;
import org.wasabineko.editorBehavior.EditorBehavior;
import org.wasabineko.editorBehavior.EditorBehaviorAgent;
import org.wasabineko.editorBehavior.concreteBehavior.SelectBehavior;
import org.wasabineko.graphic.Menu.editMenu.EditMenu;

import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList;

public class MenuBar extends JMenuBar {
    final EditorBehaviorAgent editorAgent;

    public MenuBar(EditorBehaviorAgent editorAgent) {
        super();
        this.editorAgent = editorAgent;

        JMenu fileMenu = new JMenu("File");
        EditMenu editMenu = new EditMenu();

        this.add(fileMenu);
        this.add(editMenu);

        this.setPreferredSize(GeneralConfig.getInstance().getDefaultMenuSize());
    }
}
