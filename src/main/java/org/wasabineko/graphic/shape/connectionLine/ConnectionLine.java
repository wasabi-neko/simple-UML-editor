package org.wasabineko.graphic.shape.connectionLine;

import org.wasabineko.graphic.UMLCanvas;
import org.wasabineko.graphic.shape.UMLObj;
import org.wasabineko.graphic.shape.basicObj.portObj.Port;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

public abstract class ConnectionLine extends UMLObj {
    private final Port portFrom, portTo;

    public ConnectionLine(UMLCanvas canvas, Port portFrom, Port portTo) {
        this.portFrom = portFrom;
        this.portTo = portTo;
        portFrom.setConnectionLine(this, true);
        portTo.setConnectionLine(this, false);

        this.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        this.setOpaque(false);
    }

    abstract protected void paintHead(Graphics2D g2d);

    protected void paintBody(Graphics2D g2d) {
        Point realFrom = portFrom.getConvertedCoordinate(this);
        Point realTo = portTo.getConvertedCoordinate(this);

        System.out.println("test paint port: " + realFrom + realTo);

        Line2D line = new Line2D.Double(realFrom.x, realFrom.y, realTo.x, realTo.y);
        g2d.setPaint(Color.black);
        g2d.setStroke(new BasicStroke(4));
        g2d.draw(line);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        this.paintBody(g2d);
        this.paintHead(g2d);
        paintChildren(g);
    }

    //------------------------------------------------------------
    // ignore all group and select methods  //TODO: feels strange :thonk:
    //------------------------------------------------------------
    @Override
    public boolean isInShape(MouseEvent event) {
        return false;
    }

    @Override
    public UMLObj getTopParent() { return null; }

    @Override
    public boolean isSelectable() { return false; }

    @Override
    public void setSelected(boolean isSelected) { /* do nothing */ }
}
