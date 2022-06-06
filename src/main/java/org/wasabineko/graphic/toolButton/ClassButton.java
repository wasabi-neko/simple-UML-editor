package org.wasabineko.graphic.toolButton;

import org.wasabineko.editorBehavior.concreteBehavior.CreateObjBehavior;
import org.wasabineko.graphic.shape.basicObj.ClassObj;

public class ClassButton extends ToolButton {
    public ClassButton() {
        super("class", new CreateObjBehavior(ClassObj::new));
    }
}
