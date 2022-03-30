package org.wasabineko.editorBehavior;

import org.jetbrains.annotations.NotNull;
import org.wasabineko.graphic.UMLCanvas;
import org.wasabineko.graphic.shape.UMLObj;

import javax.swing.*;
import java.awt.event.MouseEvent;

public abstract class EditorBehavior {
    protected final ModeTag modeTag;

    protected EditorBehavior(int modeTagValue) {
        this.modeTag = new ModeTag(modeTagValue);
    }

    public abstract void canvasClickAction(UMLCanvas canvas, MouseEvent event);
    public abstract void canvasPressAction(UMLCanvas canvas, MouseEvent event);
    public abstract void canvasReleaseAction(UMLCanvas canvas, MouseEvent event);

    public abstract void objClickAction(UMLObj obj, MouseEvent event);
    public abstract void objPressAction(UMLObj obj, MouseEvent event);
    public abstract void objReleaseAction(UMLObj obj, MouseEvent event);

    //------------------------------------------------------------
    // ModeEnum
    //------------------------------------------------------------

    /**
     * Using binary mask approach
     * Example: ModeTag tag = new ModeTag(ModeTag.TAGS.SELECT_MODE & ModeTag.TAGS.CREATE_MODE);
     * Example2: if (tag.isInclude(ModeTag.TAGS.SELECT_MODE) {...}
     */
    public static class ModeTag {
        public enum TAGS {
            EMPTY_MODE(0x0),
            SELECT_MODE(0x1),
            CREATE_MODE(0x1 << 1),
            CONNECT_MODE(0x1 << 2);

            public final int value;
            private TAGS(int label) {
                this.value = label;
            }
            public int getValue() {
                return this.value;
            }
        }

        private final int modeTag;

        public ModeTag(int modeTag) {
            this.modeTag = modeTag;
        }

        public boolean isInlucdeTag(@NotNull TAGS tag) {
            return (this.modeTag & tag.getValue()) != 0;
        }
    }

    //------------------------------------------------------------
    // EditorBehavior Action Template
    //------------------------------------------------------------
    public static void objPassToCanvas(EditorBehavior behavior, @NotNull UMLObj obj, @NotNull MouseEvent event) {
        JComponent container = (JComponent) obj.getTopParent().getParent();
        assert(container instanceof UMLCanvas);     // the parent of top-parent should be UMLCanvas

        UMLCanvas canvas = (UMLCanvas) container;
        switch (event.getID()) {
            case MouseEvent.MOUSE_CLICKED -> behavior.canvasClickAction(canvas, event);
            case MouseEvent.MOUSE_PRESSED -> behavior.canvasPressAction(canvas, event);
            case MouseEvent.MOUSE_RELEASED -> behavior.canvasReleaseAction(canvas, event);
        }
    }
}
