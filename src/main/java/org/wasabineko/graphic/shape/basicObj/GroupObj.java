package org.wasabineko.graphic.shape.basicObj;

import org.jetbrains.annotations.NotNull;
import org.wasabineko.graphic.shape.basicObj.portObj.PortOverlay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.Objects;

public class GroupObj extends BasicObj {

    public GroupObj() {
        super(0, 0, false, new JLabel(""), new PortOverlay());     // TODO: groupObj should not have port
        this.setLayout(null);
        this.setOpaque(false);
    }

    /**
     * Place this groupObj to the position of minX and minY of all objets,
     * and then shift all objets to fit relevant coordinate to this groupObj
     * @param childList the objets to be grouped
     */
    public void setObjsToGroup(@NotNull LinkedList<BasicObj> childList) {
        Objects.requireNonNull(childList, "childList should not be null");

        // find the top-left obj coordinate and bottom-right obj
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        int maxX = 0, maxY = 0;
        for (BasicObj child : childList) {
            if (minX > child.getX()) { minX = child.getX(); }
            if (minY > child.getY()) { minY = child.getY(); }
            if (maxX < child.getX() + child.getWidth()) { maxX = child.getX() + child.getWidth(); }
            if (maxY < child.getY() + child.getHeight()) { maxY = child.getY() + child.getHeight(); }
        }

        this.setBounds(minX, minY, maxX - minX, maxY - minY);
//        TODO: maybe i need to setBounds?

        //* register parent and shift all child
        //* from back-est obj to front-est obj
        for (BasicObj child : childList) {
            child.setGroupParent(this);
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
            BasicObj obj = (BasicObj) children[i];
            obj.ungroupFromGroup(this, container);
        }
        container.repaint();
    }

    @Override
    public void bringToFront() {
        super.bringToFront();
        Component[] children = this.getComponents();
        for (int i = children.length - 1; i >= 0; i--) {
            BasicObj obj = (BasicObj) children[i];
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
            assert (component instanceof BasicObj);
            MouseEvent objEvent = SwingUtilities.convertMouseEvent(this, event, component);
            if (((BasicObj) component).isInShape(objEvent)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setSelected(boolean isSelected) {
        super.setSelected(isSelected);
        this.portOverlay.setVisible(false);
    }

    @Override
    public void paintSelf(Graphics2D g2d) {
        if (this.selected) {
            g2d.setPaint(Color.CYAN);
            g2d.setStroke(new BasicStroke(2));
            g2d.draw(new Rectangle2D.Double(0, 0, this.getWidth(), this.getHeight()));
        }
    }
}
