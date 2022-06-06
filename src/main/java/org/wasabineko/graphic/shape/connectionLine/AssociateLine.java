package org.wasabineko.graphic.shape.connectionLine;

import org.wasabineko.graphic.UMLCanvas;
import org.wasabineko.graphic.shape.basicObj.portObj.Port;
import org.wasabineko.utility.Vector2D;

import java.awt.*;
import java.awt.geom.Line2D;

public class AssociateLine extends ConnectionLine {

    public AssociateLine(Container canvas, Port portFrom, Port portTo) {
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
        Line2D lineL = new Line2D.Double(bodyTo.toPonint(), leftPoint);
        Line2D lineR = new Line2D.Double(bodyTo.toPonint(), rightPoint);

        g2d.setPaint(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.draw(lineL);
        g2d.draw(lineR);
    }

}
