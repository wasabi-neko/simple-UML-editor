package org.wasabineko.graphic;

import org.wasabineko.editorBehavior.EditorBehaviorAgent;
import org.wasabineko.graphic.toolBar.ToolBar;

import javax.swing.*;
import java.awt.*;

public class EditPanel extends JPanel {
    public EditPanel() {
        setLayout(new BorderLayout());

        ToolBar toolBar = new ToolBar();
        UMLCanvas canvas = new UMLCanvas();

        this.add(toolBar, BorderLayout.WEST);
        this.add(canvas, BorderLayout.CENTER);
    }
}
