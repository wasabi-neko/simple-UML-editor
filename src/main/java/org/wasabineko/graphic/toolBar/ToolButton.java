package org.wasabineko.graphic.toolBar;

import org.wasabineko.editorBehavior.EditorBehavior;
import org.wasabineko.editorBehavior.EditorBehaviorAgent;

import javax.swing.*;
import java.awt.*;

public class ToolButton extends JToggleButton {

    public ToolButton(String name, EditorBehavior editorBehavior) {
        super(name);
        this.setPreferredSize(new Dimension(100, 100));
        this.addActionListener(actionEvent -> EditorBehaviorAgent.getInstance().setEditorBehavior(editorBehavior));
    }
}
