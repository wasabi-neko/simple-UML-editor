package org.wasabineko.graphic.toolButton;

import org.wasabineko.editorBehavior.concreteBehavior.ConnectLineBehavior;
import org.wasabineko.graphic.shape.connectionLine.AssociateLine;

public class AssociationLineButton extends ToolButton {
    public AssociationLineButton() {
        super("associate line", new ConnectLineBehavior(AssociateLine::new));
    }
}
