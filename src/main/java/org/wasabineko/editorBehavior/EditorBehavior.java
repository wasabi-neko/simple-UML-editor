package org.wasabineko.editorBehavior;

import org.wasabineko.graphic.UMLCanvas;
import org.wasabineko.graphic.shape.UMLObj;

import java.awt.event.MouseEvent;

public abstract class EditorBehavior {
    public abstract void canvasClickAction(UMLCanvas canvas, MouseEvent event);
    public abstract void canvasPressAction(UMLCanvas canvas, MouseEvent event);
    public abstract void canvasReleaseAction(UMLCanvas canvas, MouseEvent event);

    public abstract void objClickAction(UMLObj obj, MouseEvent event);
    public abstract void objPressAction(UMLObj obj, MouseEvent event);
    public abstract void objReleaseAction(UMLObj obj, MouseEvent event);

    public static void objPassToCanvas(UMLObj obj, MouseEvent event) {
    }
}
