package org.wasabineko.graphic.toolButton;

import org.wasabineko.editorBehavior.EditorBehavior;
import org.wasabineko.graphic.ToolBar;

import javax.swing.*;
import java.awt.*;

public class ToolButton extends JToggleButton {
    private ToolBar parentBar;

    public ToolButton(String name, EditorBehavior editorBehavior) {
        super(name);
        this.setPreferredSize(new Dimension(100, 100));
        this.addActionListener(actionEvent -> parentBar.getEditorAgent().setEditorBehavior(editorBehavior));
    }

    public void setParentBar(ToolBar parentBar) {
        this.parentBar = parentBar;
    }
}
