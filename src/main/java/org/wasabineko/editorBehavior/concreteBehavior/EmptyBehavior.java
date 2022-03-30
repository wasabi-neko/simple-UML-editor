package org.wasabineko.editorBehavior.concreteBehavior;

import org.wasabineko.editorBehavior.EditorBehavior;
import org.wasabineko.graphic.UMLCanvas;
import org.wasabineko.graphic.shape.UMLObj;

import java.awt.event.MouseEvent;

public class EmptyBehavior extends EditorBehavior {

    public EmptyBehavior() {
        super(ModeTag.TAGS.EMPTY_MODE.getValue());
    }

    @Override
    public void canvasClickAction(UMLCanvas canvas, MouseEvent event) { /*do nothing*/ }

    @Override
    public void canvasPressAction(UMLCanvas canvas, MouseEvent event) {/*do nothing*/}

    @Override
    public void canvasReleaseAction(UMLCanvas canvas, MouseEvent event) {/*do nothing*/}

    @Override
    public void objClickAction(UMLObj obj, MouseEvent event) { /*do nothing*/ }

    @Override
    public void objPressAction(UMLObj obj, MouseEvent event) {/*do nothing*/}

    @Override
    public void objReleaseAction(UMLObj obj, MouseEvent event) {/*do nothing*/}
}
