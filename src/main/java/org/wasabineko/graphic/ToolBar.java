package org.wasabineko.graphic;

import org.wasabineko.GeneralConfig;
import org.wasabineko.editorBehavior.EditorAgent;

import javax.swing.*;
import java.awt.*;

public class ToolBar extends JPanel {
    EditorAgent editorAgent;

    public ToolBar(EditorAgent editorAgent) {
        super();
        this.editorAgent = editorAgent;
        setPreferredSize(GeneralConfig.getInstance().getDefaultToolBarSize());
        setBackground(Color.CYAN);
    }
}
