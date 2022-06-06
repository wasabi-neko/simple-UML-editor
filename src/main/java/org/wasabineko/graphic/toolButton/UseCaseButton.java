package org.wasabineko.graphic.toolButton;

import org.wasabineko.editorBehavior.concreteBehavior.CreateObjBehavior;
import org.wasabineko.graphic.shape.basicObj.UseCaseObj;

public class UseCaseButton extends ToolButton {
    public UseCaseButton() {
        super("useCase", new CreateObjBehavior(UseCaseObj::new));
    }
}
