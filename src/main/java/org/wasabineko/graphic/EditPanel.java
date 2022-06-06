package org.wasabineko.graphic;

import org.wasabineko.editorBehavior.EditorBehaviorAgent;
import org.wasabineko.graphic.toolBar.ToolBar;

import javax.swing.*;
import java.awt.*;

public class EditPanel extends JPanel {
    public EditPanel(EditorBehaviorAgent editorAgent) {
        super();
        setLayout(new BorderLayout());

        ToolBar toolBar = new ToolBar(editorAgent);
        UMLCanvas canvas = new UMLCanvas(editorAgent);

        this.add(toolBar, BorderLayout.WEST);
        this.add(canvas, BorderLayout.CENTER);
    }
}
