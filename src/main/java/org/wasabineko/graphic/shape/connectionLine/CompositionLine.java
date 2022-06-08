package org.wasabineko.graphic.shape.connectionLine;

import org.wasabineko.graphic.shape.basicObj.portObj.Port;
import org.wasabineko.utility.Vector2D;

import java.awt.*;

public class CompositionLine extends ConnectionLine {
    public CompositionLine(Container canvas, Port portFrom, Port portTo) {
        super(canvas, portFrom, portTo);
    }

    @Override
    protected void paintHead(Graphics2D g2d) {
        Vector2D bodyFrom = new Vector2D(this.getRealFromPoint());
        Vector2D bodyTo = new Vector2D(this.getRealToPoint());
        Vector2D bodyVec = bodyTo.minus(bodyFrom);

        Vector2D left = bodyVec.turnDegree((3.0/4.0) * 3.1415);
        Vector2D right = bodyVec.turnDegree((-3.0/4.0) * 3.1415);
        left = left.muliScalar(10 / left.getLength());
        right = right.muliScalar(10 / right.getLength());

        Point leftPoint = bodyTo.add(left).toPonint();
        Point rightPoint = bodyTo.add(right).toPonint();
        Point downPoint = bodyTo.add(left).add(right).toPonint();
        Polygon polygon = new Polygon();
        polygon.addPoint(bodyTo.toPonint().x, bodyTo.toPonint().y);
        polygon.addPoint(leftPoint.x, leftPoint.y);
        polygon.addPoint(downPoint.x, downPoint.y);
        polygon.addPoint(rightPoint.x, rightPoint.y);

        g2d.setPaint(Color.WHITE);
        g2d.fillPolygon(polygon);
        g2d.setPaint(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawPolygon(polygon);
    }
}
