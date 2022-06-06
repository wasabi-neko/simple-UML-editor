package org.wasabineko.graphic.toolBar;

import org.wasabineko.editorBehavior.concreteBehavior.SelectBehavior;

public class SelectButton extends ToolButton {
    public SelectButton() {
        super("select", new SelectBehavior());
    }
}
