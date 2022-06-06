package org.wasabineko.graphic.toolButton;

import org.wasabineko.editorBehavior.concreteBehavior.ConnectLineBehavior;
import org.wasabineko.graphic.shape.connectionLine.CompositionLine;

public class CompositionLineButton extends ToolButton {
    public CompositionLineButton() {
        super("composition line", new ConnectLineBehavior(CompositionLine::new));
    }
}
