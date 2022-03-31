package org.wasabineko.graphic;

import org.wasabineko.GeneralConfig;
import org.wasabineko.editorBehavior.EditorBehavior;
import org.wasabineko.editorBehavior.EditorBehaviorAgent;
import org.wasabineko.editorBehavior.concreteBehavior.CreateObjBehavior;
import org.wasabineko.graphic.shape.basicObj.UseCaseObj;

import javax.swing.*;
import java.awt.*;

/**
 * Every toolButton in toolbar should change the behavior of the
 * target editorAgent.
 */
public class ToolBar extends JPanel {
    EditorBehaviorAgent editorAgent;
    ButtonGroup buttonGroup;

    public ToolBar(EditorBehaviorAgent editorAgent) {
        super();
        this.editorAgent = editorAgent;
        this.buttonGroup = new ButtonGroup();

        // appearance setting
        setPreferredSize(GeneralConfig.getInstance().getDefaultToolBarSize());
        setBackground(Color.CYAN);

        // buttons
        ToolButton useCaseButt = new ToolButton("useCase", new CreateObjBehavior(UseCaseObj::new));
        ToolButton useCaseButt2 = new ToolButton("useCase", new CreateObjBehavior(UseCaseObj::new));

        this.add(useCaseButt);
        this.add(useCaseButt2);
    }

    public void add(ToolButton butt) {
        super.add(butt);
        this.buttonGroup.add(butt);
        butt.parentBar = this;
    }

    private static class ToolButton extends JToggleButton {
        private ToolBar parentBar;

        public ToolButton(String name, EditorBehavior editorBehavior) {
            super(name);
            this.addActionListener(actionEvent -> parentBar.editorAgent.setEditorBehavior(editorBehavior));
        }
    }
}
