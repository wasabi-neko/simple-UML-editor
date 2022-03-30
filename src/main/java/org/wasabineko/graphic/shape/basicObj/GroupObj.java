package org.wasabineko.graphic.shape.basicObj;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Objects;

public class GroupObj extends BasicObj {
    LinkedList<BasicObj> childList;

    public GroupObj() {
        super(0, 0, new JLabel(""));
        this.childList = new LinkedList<>();
    }

    public GroupObj(LinkedList<BasicObj> childList) {
        super(0, 0, new JLabel(""));
        this.group(childList);
    }

    /**
     * Place this groupObj to the position of minX and minY of all objets,
     * and then shift all objets to fit relevant coordinate to this groupObj
     * @param childList the objets to be grouped
     */
    void group(@NotNull LinkedList<BasicObj> childList) {
        Objects.requireNonNull(childList, "childList should not be null");
        this.childList = childList;

        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        for (BasicObj child : childList) {
            if (minX > child.getX()) {
                minX = child.getX();
            }
            if (minY > child.getY()) {
                minY = child.getY();
            }
        }

        this.setLocation(minX, minY);
//        TODO: maybe i need to setBounds?

        //* register parent and shift all child
        //* from back-est obj to front-est obj
        for (BasicObj child : childList) {
            child.setGroupParent(this);
        }
    }

    /**
     * return true if any one of its child contains the provided coordinate
     * @param x x coordinate
     * @param y y coordinate
     * @return is this obj contain provided coordinate
     */
    @Override
    public boolean isInShape(int x, int y) {
        for (BasicObj child : childList) {
           if (child.isInShape(x, y)) {
               return true;
           }
        }
        return false;
    }
}
