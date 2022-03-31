package org.wasabineko.editorBehavior.concreteBehavior;

import org.wasabineko.editorBehavior.EditorBehavior;
import org.wasabineko.graphic.UMLCanvas;
import org.wasabineko.graphic.shape.UMLObj;
import org.wasabineko.graphic.shape.basicObj.BasicObj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class SelectBehavior extends EditorBehavior {
    LinkedList<BasicObj> selectedList = new LinkedList<>();
    Point pressedCoordinate = new Point(0,0);
    Point objInternalShift = new Point(0, 0);
    boolean isDragObj;
    BasicObj registerObj;

    public SelectBehavior() {
        super(ModeTag.TAGS.SELECT_MODE.getValue());
    }

    private void cleanAllSelected() {
        for (BasicObj obj : selectedList) {
            obj.setSelected(false);
        }
        this.selectedList.clear();
    }

    private void selectArea(UMLCanvas canvas, MouseEvent event) {
        int upperX = event.getX();
        int upperY = event.getY();
        int lowerX = (int) pressedCoordinate.getX();
        int lowerY = (int) pressedCoordinate.getY();

        if (upperX > lowerX) {
            int tmp = upperX;
            upperX = lowerX;
            lowerX = tmp;
        }

        if (upperY > lowerY) {
            int tmp = upperY;
            upperY = lowerY;
            lowerY = tmp;
        }

        System.out.printf("(%d,%d), (%d,%d)%n", upperX, upperY, lowerX, lowerY);

        Component[] list = canvas.getComponents();
        for (Component component : list) {
            assert(component instanceof  BasicObj);
            BasicObj obj = (BasicObj) component;

            if (obj.getX() > upperX
                    && obj.getY() > upperY
                    && obj.getX() + obj.getWidth() < lowerX
                    && obj.getY() + obj.getHeight() < lowerY) {
                obj.setSelected(true);
                this.selectedList.add(obj);
                obj.getTopParent().getParent().setComponentZOrder(obj, 0);
            }
        }
    }

    private void dragObjToReleasePoint(BasicObj obj, Point objShift, MouseEvent event) {
        obj.setLocation((int) (event.getX() - objShift.getX()), (int) (event.getY() - objShift.getY()));
        obj.getTopParent().getParent().setComponentZOrder(obj, 0);
    }

    // Canvas Mouse Action

    @Override
    public void canvasClickAction(UMLCanvas canvas, MouseEvent event) {
        System.out.println("Select Canvas Click");
        cleanAllSelected();
    }

    @Override
    public void canvasPressAction(UMLCanvas canvas, MouseEvent event) {
        System.out.println("Canvas Press");
        cleanAllSelected();
        this.pressedCoordinate.setLocation(event.getX(), event.getY());
        this.isDragObj = false;
    }

    @Override
    public void canvasReleaseAction(UMLCanvas canvas, MouseEvent event) {
        System.out.println("Canvas release");
        if (this.isDragObj && this.registerObj != null) {
            dragObjToReleasePoint(this.registerObj, this.objInternalShift, event);
        } else {
            selectArea(canvas, event);
        }
    }

    // Obj Mouse Action

    @Override
    public void objClickAction(UMLObj obj, MouseEvent event) {
        System.out.println("Select obj Click");

        this.cleanAllSelected();
        if (obj instanceof BasicObj) {
            this.selectedList.add((BasicObj) obj);
            ((BasicObj) obj).setSelected(true);
            obj.getTopParent().getParent().setComponentZOrder(obj, 0);  // bring to front
        }
    }

    @Override
    public void objPressAction(UMLObj obj, MouseEvent event) {
        assert (obj instanceof BasicObj);
        this.objInternalShift.setLocation(event.getX(), event.getY());
        this.isDragObj = true;
        this.registerObj = (BasicObj) obj;
    }

    @Override
    public void objReleaseAction(UMLObj obj, MouseEvent event) {
        // convert local obj mouseEvent to canvas event (convert the coordinate)
        MouseEvent newEven = SwingUtilities.convertMouseEvent(obj, event, obj.getTopParent().getParent());
        if (this.isDragObj && this.registerObj != null) {
            dragObjToReleasePoint(this.registerObj, this.objInternalShift, newEven);
        } else {
            Container component = obj.getTopParent().getParent();
            assert (component instanceof UMLCanvas);
            selectArea((UMLCanvas) component, newEven);
        }

    }
}
