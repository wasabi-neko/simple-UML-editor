package org.wasabineko.editorBehavior.concreteBehavior;

import org.wasabineko.editorBehavior.EditorBehavior;
import org.wasabineko.graphic.UMLCanvas;
import org.wasabineko.graphic.shape.UMLObj;
import org.wasabineko.graphic.shape.basicObj.BasicObj;

import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class SelectBehavior extends EditorBehavior {
    LinkedList<BasicObj> selectedList = new LinkedList<>();

    public SelectBehavior() {
        super(ModeTag.TAGS.SELECT_MODE.getValue());
    }

    private void cleanAllSelected() {
        for (BasicObj obj : selectedList) {
            obj.setSelected(false);
        }
        this.selectedList.clear();
    }

    // Canvas Mouse Action

    @Override
    public void canvasClickAction(UMLCanvas canvas, MouseEvent event) {
        System.out.println("Select Canvas Click");
        cleanAllSelected();
    }

    @Override
    public void canvasPressAction(UMLCanvas canvas, MouseEvent event) { /* do nothing */ }

    @Override
    public void canvasReleaseAction(UMLCanvas canvas, MouseEvent event) { /* do nothing */ }

    // Obj Mouse Action

    @Override
    public void objClickAction(UMLObj obj, MouseEvent event) {
        System.out.println("Select obj Click");

        this.cleanAllSelected();
        if (obj instanceof BasicObj) {
            this.selectedList.add((BasicObj) obj);
            ((BasicObj) obj).setSelected(true);
        }
    }

    @Override
    public void objPressAction(UMLObj obj, MouseEvent event) {

    }

    @Override
    public void objReleaseAction(UMLObj obj, MouseEvent event) {

    }
}
