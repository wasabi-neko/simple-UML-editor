package org.wasabineko.graphic.toolBar;

import org.wasabineko.editorBehavior.concreteBehavior.ConnectLineBehavior;
import org.wasabineko.graphic.shape.connectionLine.GeneralizationLine;

public class GeneralLineButton extends ToolButton {
    public GeneralLineButton() {
        super("generalization line", new ConnectLineBehavior(GeneralizationLine::new));
    }
}
