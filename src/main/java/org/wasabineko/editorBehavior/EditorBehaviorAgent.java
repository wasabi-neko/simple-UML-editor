package org.wasabineko.editorBehavior;

import org.wasabineko.editorBehavior.concreteBehavior.EmptyBehavior;
import org.wasabineko.graphic.UMLCanvas;
import org.wasabineko.graphic.shape.UMLObj;

import java.awt.event.MouseEvent;
import java.util.Objects;

public class EditorBehaviorAgent {
    private EditorBehavior editorBehavior;

    public EditorBehaviorAgent() {
        this.editorBehavior = new EmptyBehavior();
    }

    public void setEditorBehavior(EditorBehavior editorBehavior) {
        Objects.requireNonNull(editorBehavior, "editorBehavior should not be null");
        this.editorBehavior = editorBehavior;
    }

    public EditorBehavior.ModeTag getEditorBehaviorModeTag() {
        return this.editorBehavior.modeTag;
    }

    // ------------------------------------------------------------
    // Just Pass every action to its core behavior object
    // ------------------------------------------------------------
    public void canvasClickAction(UMLCanvas canvas, MouseEvent event) {
        editorBehavior.canvasClickAction(canvas, event);
    }

    public void canvasPressAction(UMLCanvas canvas, MouseEvent event) {
        editorBehavior.canvasPressAction(canvas, event);
    }

    public void canvasReleaseAction(UMLCanvas canvas, MouseEvent event) {
        editorBehavior.canvasReleaseAction(canvas, event);
    }

    public void objClickAction(UMLObj obj, MouseEvent event) {
        editorBehavior.objClickAction(obj, event);
    }

    public void objPressAction(UMLObj obj, MouseEvent event) {
        editorBehavior.objPressAction(obj, event);
    }

    public void objReleaseAction(UMLObj obj, MouseEvent event) {
        editorBehavior.objReleaseAction(obj, event);
    }

}
