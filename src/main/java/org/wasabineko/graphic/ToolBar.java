package org.wasabineko.graphic;

import org.wasabineko.GeneralConfig;
import org.wasabineko.editorBehavior.EditorBehavior;
import org.wasabineko.editorBehavior.EditorBehaviorAgent;
import org.wasabineko.editorBehavior.concreteBehavior.ConnectLineBehavior;
import org.wasabineko.editorBehavior.concreteBehavior.CreateObjBehavior;
import org.wasabineko.editorBehavior.concreteBehavior.SelectBehavior;
import org.wasabineko.graphic.shape.basicObj.ClassObj;
import org.wasabineko.graphic.shape.basicObj.UseCaseObj;
import org.wasabineko.graphic.shape.connectionLine.AssociateLine;
import org.wasabineko.graphic.shape.connectionLine.CompositionLine;
import org.wasabineko.graphic.shape.connectionLine.GeneralizationLine;
import org.wasabineko.graphic.toolButton.*;

import javax.swing.*;
import java.awt.*;

/**
 * Every toolButton in toolbar should change the behavior of the
 * target editorAgent.
 */
public class ToolBar extends JPanel {
    final EditorBehaviorAgent editorAgent;
    final ButtonGroup buttonGroup;

    public ToolBar(EditorBehaviorAgent editorAgent) {
        super();
        this.editorAgent = editorAgent;
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

    public EditorBehaviorAgent getEditorAgent() {
        return this.editorAgent;
    }

}
