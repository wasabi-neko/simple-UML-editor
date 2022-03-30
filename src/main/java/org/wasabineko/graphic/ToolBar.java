package org.wasabineko.graphic;

import org.wasabineko.GeneralConfig;
import org.wasabineko.editorBehavior.EditorBehaviorAgent;

import javax.swing.*;
import java.awt.*;

/**
 * Every toolButton in toolbar should change the behavior of the
 * target editorAgent.
 */
public class ToolBar extends JPanel {
    EditorBehaviorAgent editorAgent;

    public ToolBar(EditorBehaviorAgent editorAgent) {
        super();
        this.editorAgent = editorAgent;
        setPreferredSize(GeneralConfig.getInstance().getDefaultToolBarSize());
        setBackground(Color.CYAN);
    }
}
