package org.wasabineko.editorBehavior;

import org.jetbrains.annotations.NotNull;
import org.wasabineko.editorBehavior.concreteBehavior.EmptyBehavior;
import org.wasabineko.graphic.UMLCanvas;
import org.wasabineko.graphic.shape.UMLObj;

import java.awt.event.MouseEvent;
import java.util.Objects;

public class EditorBehaviorAgent {
    // --------------------------------------------------
    // Singleton
    // --------------------------------------------------
    private static EditorBehaviorAgent instance;

    public static EditorBehaviorAgent getInstance() {
        if (EditorBehaviorAgent.instance == null) {
            EditorBehaviorAgent.instance = new EditorBehaviorAgent();
        }
        return EditorBehaviorAgent.instance;
    }

    // --------------------------------------------------
    // Main
    // --------------------------------------------------
    private EditorBehavior editorBehavior;

    private EditorBehaviorAgent() {
        this.editorBehavior = new EmptyBehavior();
    }

    public void setEditorBehavior(EditorBehavior editorBehavior) {
        Objects.requireNonNull(editorBehavior, "editorBehavior should not be null");
        this.editorBehavior = editorBehavior;
    }

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
        }
    }

    public void objMouseAction(UMLObj obj, @NotNull MouseEvent event) {
        switch (event.getID()) {
            case MouseEvent.MOUSE_CLICKED -> editorBehavior.objClickAction(obj, event);
            case MouseEvent.MOUSE_PRESSED -> editorBehavior.objPressAction(obj, event);
            case MouseEvent.MOUSE_RELEASED -> editorBehavior.objReleaseAction(obj, event);
        }
    }
}
