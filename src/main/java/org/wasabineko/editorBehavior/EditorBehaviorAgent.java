package org.wasabineko.editorBehavior;

import org.jetbrains.annotations.NotNull;
import org.wasabineko.editorBehavior.concreteBehavior.EmptyBehavior;
import org.wasabineko.graphic.UMLCanvas;
import org.wasabineko.graphic.shape.basicObj.BasicObj;

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

    //TODO: maybe this is not a dummy dum dum idea, but i have to :(
    public EditorBehavior getEditorBehavior() {
        return this.editorBehavior;
    }

    public EditorBehavior.ModeTag getEditorBehaviorModeTag() {
        return this.editorBehavior.modeTag;
    }

    // ------------------------------------------------------------
    // Just Pass every action to its core behavior object
    // ------------------------------------------------------------
    public void canvasMouseAction(UMLCanvas canvas, @NotNull MouseEvent event) {
        switch (event.getID()) {
            case MouseEvent.MOUSE_CLICKED -> editorBehavior.canvasClickAction(canvas, event);
            case MouseEvent.MOUSE_PRESSED -> editorBehavior.canvasPressAction(canvas, event);
            case MouseEvent.MOUSE_RELEASED -> editorBehavior.canvasReleaseAction(canvas, event);
            //TODO: raise exception: exception case
        }
    }

    public void objMouseAction(BasicObj obj, @NotNull MouseEvent event) {
        switch (event.getID()) {
            case MouseEvent.MOUSE_CLICKED -> editorBehavior.objClickAction(obj, event);
            case MouseEvent.MOUSE_PRESSED -> editorBehavior.objPressAction(obj, event);
            case MouseEvent.MOUSE_RELEASED -> editorBehavior.objReleaseAction(obj, event);
            //TODO: raise exception: exception case
        }
    }
}
