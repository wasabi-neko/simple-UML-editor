package org.wasabineko.graphic.toolBar;

import org.wasabineko.GeneralConfig;
import org.wasabineko.editorBehavior.EditorBehaviorAgent;

import javax.swing.*;
import java.awt.*;

/**
 * Every toolButton in toolbar should change the behavior of the
 * target editorAgent.
 */
public class ToolBar extends JPanel {
    final ButtonGroup buttonGroup;

    public ToolBar() {
        this.buttonGroup = new ButtonGroup();

        // appearance setting
        setPreferredSize(GeneralConfig.getInstance().getDefaultToolBarSize());
        setBackground(Color.CYAN);

        // buttons
        ToolButton selectButt = new SelectButton();
        ToolButton useCaseButt = new UseCaseButton();
        ToolButton classButt = new ClassButton();
        ToolButton associateLineButt = new AssociationLineButton();
        ToolButton compositionLineButt = new CompositionLineButton();
        ToolButton generalizationLineButt = new GeneralLineButton();

        this.add(selectButt);
        this.add(useCaseButt);
        this.add(classButt);
        this.add(associateLineButt);
        this.add(compositionLineButt);
        this.add(generalizationLineButt);
        selectButt.doClick();       // enter the select mode by default
    }

    public void add(ToolButton butt) {
        super.add(butt);
        this.buttonGroup.add(butt);
        butt.setParentBar(this);
    }
}
