package org.wasabineko.editorBehavior.concreteBehavior;

import org.wasabineko.editorBehavior.EditorBehavior;
import org.wasabineko.graphic.UMLCanvas;
import org.wasabineko.graphic.shape.UMLObj;
import org.wasabineko.graphic.shape.basicObj.BasicObj;
import org.wasabineko.graphic.shape.basicObj.GroupObj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class SelectBehavior extends EditorBehavior {
    final LinkedList<BasicObj> selectedList = new LinkedList<>();
    final Point pressedCoordinate = new Point(0,0);
    final Point objInternalShift = new Point(0, 0);
    boolean isDragObj;
    BasicObj registerObj;

    public SelectBehavior() {
        super(ModeTag.TAGS.SELECT_MODE.getValue());
    }

//    TODO: this structure feels dum dum, need to be refactor later
    public boolean isNowGroupAble() {
        return this.selectedList.size() > 1;
    }

    public boolean isNowUngroupAble() {
        return this.selectedList.size() == 1 && (this.selectedList.getFirst() instanceof GroupObj);
    }

    public boolean isNowRenameAble() {
        return this.selectedList.size() == 1;
    }

    public boolean groupSelectedObjs() {
        if (this.isNowGroupAble()) {
            Container container = selectedList.getFirst().getParent();
            GroupObj groupObj = new GroupObj();
            groupObj.setObjsToGroup(this.selectedList);
            container.add(groupObj);
            container.setComponentZOrder(groupObj, 0);

            // unselect all and select the groupObj
            this.cleanAllSelected();
            groupObj.setSelected(true);
            this.selectedList.add(groupObj);
            return true;
        } else {
            return false;
        }
    }

    public boolean ungroupSelectedObj() {
        if (this.isNowUngroupAble()) {
            GroupObj groupObj = (GroupObj) selectedList.getFirst();
            groupObj.ungroup();
            this.cleanAllSelected();
            return true;
        } else {
            return false;
        }
    }

    public boolean renameSelectedObj() {
        if (isNowRenameAble()) {
            BasicObj obj = this.selectedList.getFirst();
            String newName = JOptionPane.showInputDialog(obj.getLabelName());
            obj.setLabelName(newName);
            return true;
        } else {
            return false;
        }
    }

    private void cleanAllSelected() {
        for (BasicObj obj : selectedList) {
            obj.setSelected(false);
        }
        this.selectedList.clear();
    }

    //TODO: this method logic should move to UMLCanvas
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


        // add to front from back-est obj to front-est obj
        Component[] list = canvas.getComponents();
        for (int i = list.length-1; i >= 0; i--) {
            Component component = list[i];
            if (!(component instanceof  BasicObj)) {
                continue;
            }
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
