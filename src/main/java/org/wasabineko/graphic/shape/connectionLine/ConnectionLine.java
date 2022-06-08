package org.wasabineko.graphic.shape.connectionLine;

import org.wasabineko.graphic.shape.UMLObj;
import org.wasabineko.graphic.shape.basicObj.portObj.Port;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

public abstract class ConnectionLine extends UMLObj {
    private final Port portFrom, portTo;

    public ConnectionLine(Container canvas, Port portFrom, Port portTo) {
        this.portFrom = portFrom;
        this.portTo = portTo;
        portFrom.addConnectionLine(this, true);
        portTo.addConnectionLine(this, false);

        this.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        this.setOpaque(false);
    }

    protected Point getRealFromPoint() {
        return portFrom.getConvertedCoordinate(this);
    }

    protected Point getRealToPoint() {
        return portTo.getConvertedCoordinate(this);
    }

    abstract protected void paintHead(Graphics2D g2d);

    protected void paintBody(Graphics2D g2d) {
        Point realFrom = this.getRealFromPoint();
        Point realTo = this.getRealToPoint();

        Line2D line = new Line2D.Double(realFrom.x, realFrom.y, realTo.x, realTo.y);
        g2d.setPaint(Color.black);
        g2d.setStroke(new BasicStroke(4));
        g2d.draw(line);
        new Point(realTo.x - realFrom.x, realTo.y - realFrom.y);
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
    public boolean isConnectAble() {
        return false;
    }

    @Override
    public boolean isSelectable() {
        return  false;
    }

    @Override
    public boolean isInShape(MouseEvent event) {
        return false;
    }

    @Override
    public UMLObj getTopParent() { return null; }
}
