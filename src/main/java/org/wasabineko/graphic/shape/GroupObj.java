package org.wasabineko.graphic.shape;

import org.jetbrains.annotations.NotNull;
import org.wasabineko.graphic.shape.UMLObj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.Objects;

public class GroupObj extends UMLObj {

    public GroupObj() {
        this.setLayout(null);
        this.setOpaque(false);
    }

    /**
     * Place this groupObj to the position of minX and minY of all objets,
     * and then shift all objets to fit relevant coordinate to this groupObj
     * @param childList the objets to be grouped
     */
    public void setObjsToGroup(@NotNull LinkedList<UMLObj> childList) {
        Objects.requireNonNull(childList, "childList should not be null");

        // find the top-left obj coordinate and bottom-right obj
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        int maxX = 0, maxY = 0;
        for (UMLObj child : childList) {
            if (minX > child.getX()) { minX = child.getX(); }
            if (minY > child.getY()) { minY = child.getY(); }
            if (maxX < child.getX() + child.getWidth()) { maxX = child.getX() + child.getWidth(); }
            if (maxY < child.getY() + child.getHeight()) { maxY = child.getY() + child.getHeight(); }
        }

        this.setBounds(minX, minY, maxX - minX, maxY - minY);

        //* register parent and shift all child
        //* from back-est obj to front-est obj
        for (UMLObj child : childList) {
            child.setGroupParent(this);
            child.getParent().remove(child);    // remove it from its previous parent
            this.add(child);
            child.setLocation(child.getX() - this.getX(), child.getY() - this.getY());
            child.bringToFront();
        }
    }

    /**
     * Release children to its parent
     */
    public void ungroup() {
        Container container = this.getParent();

        // remove the group obj from parent Container
        container.remove(this);

        // re-add back all the child obj to the canvas(the parent container of groupObj)
        Component[] children = this.getComponents();
        for (int i = children.length - 1; i >= 0; i--) {
            UMLObj child = (UMLObj) children[i];

            child.setGroupParent(null);
            child.setLocation(child.getX() + this.getX(), child.getY() + this.getY());
            this.remove(child);     // group.remove child
            container.add(child);
            child.bringToFront();
        }
        container.repaint();
    }


    // --------------------------------------------------
    // Override Methods
    // --------------------------------------------------
    @Override
    public void bringToFront() {
        super.bringToFront();
        Component[] children = this.getComponents();
        for (int i = children.length - 1; i >= 0; i--) {
            UMLObj obj = (UMLObj) children[i];
            obj.bringToFront();
        }
    }

    /**
     * return true if any one of its child contains the provided coordinate
     * @param event the mouse event (base on the obj itself)
     * @return is this obj contain provided coordinate
     */
    @Override
    public boolean isInShape(MouseEvent event) {
        Component[] children = this.getComponents();
        for (Component component: children) {
            assert (component instanceof UMLObj);
            MouseEvent objEvent = SwingUtilities.convertMouseEvent(this, event, component);
            if (((UMLObj) component).isInShape(objEvent)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isSelectable() {
        return true;
    }

    @Override
    public boolean isConnectAble() {
        return false;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        super.paintChildren(g);
        if (this.selected) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setPaint(Color.CYAN);
            g2d.setStroke(new BasicStroke(2));
            g2d.draw(new Rectangle2D.Double(0, 0, this.getWidth(), this.getHeight()));
        }
    }
}
