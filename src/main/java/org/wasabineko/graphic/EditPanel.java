package org.wasabineko.graphic;

import org.wasabineko.editorBehavior.EditorBehaviorAgent;

import javax.swing.*;
import java.awt.*;

public class EditPanel extends JPanel {
    public EditPanel() {
        super();
        setLayout(new BorderLayout());

        EditorBehaviorAgent editorAgent = new EditorBehaviorAgent();
        ToolBar toolBar = new ToolBar(editorAgent);
//        TODO: toolBar init state to selectMode
        UMLCanvas canvas = new UMLCanvas(editorAgent);

        this.add(toolBar, BorderLayout.WEST);
        this.add(canvas, BorderLayout.CENTER);
    }
}
