package org.wasabineko.editorBehavior.concreteBehavior;

import org.wasabineko.editorBehavior.EditorBehavior;
import org.wasabineko.graphic.UMLCanvas;
import org.wasabineko.graphic.shape.UMLObj;

import java.awt.event.MouseEvent;

public class CreateObjBehavior extends EditorBehavior {
    // ------------------------------------------------------------
    // Nested Class
    // ------------------------------------------------------------
    public interface ObjFactory {
        UMLObj createObj(int x, int y);
    }

    // ------------------------------------------------------------
    // Properties and Methods
    // ------------------------------------------------------------
    private final ObjFactory objFactory;

    public CreateObjBehavior(ObjFactory objFactory) {
        super(ModeTag.TAGS.CREATE_MODE.getValue());
        this.objFactory = objFactory;
    }

    @Override
    public void canvasClickAction(UMLCanvas canvas, MouseEvent event) {
        UMLObj obj = this.objFactory.createObj(event.getX(), event.getY());
        canvas.add(obj);
        obj.bringToFront();
        canvas.repaint();
    }

    @Override
    public void canvasPressAction(UMLCanvas canvas, MouseEvent event) { /* do nothing */ }

    @Override
    public void canvasReleaseAction(UMLCanvas canvas, MouseEvent event) { /* do nothing */ }

    @Override
    public void objClickAction(UMLObj obj, MouseEvent event) {
        EditorBehavior.objPassToCanvas(this, obj, event);
    }

    @Override
    public void objPressAction(UMLObj obj, MouseEvent event) {
        EditorBehavior.objPassToCanvas(this, obj, event);
    }

    @Override
    public void objReleaseAction(UMLObj obj, MouseEvent event) {
        EditorBehavior.objPassToCanvas(this, obj, event);
    }

}
