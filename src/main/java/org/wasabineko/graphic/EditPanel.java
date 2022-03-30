package org.wasabineko.graphic;

import org.wasabineko.editorBehavior.EditorAgent;

import javax.swing.*;
import java.awt.*;

public class EditPanel extends JPanel {
    public EditPanel() {
        super();
        setLayout(new BorderLayout());

        EditorAgent editorAgent = new EditorAgent();
        ToolBar toolBar = new ToolBar(editorAgent);
//        TODO: toolBar init state to selectMode
        UMLCanvas canvas = new UMLCanvas(editorAgent);

        this.add(toolBar, BorderLayout.WEST);
        this.add(canvas, BorderLayout.CENTER);
    }
}
