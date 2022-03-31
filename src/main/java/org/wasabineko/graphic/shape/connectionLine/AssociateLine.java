package org.wasabineko.graphic.shape.connectionLine;

import org.wasabineko.graphic.UMLCanvas;
import org.wasabineko.graphic.shape.basicObj.portObj.Port;

import java.awt.*;

public class AssociateLine extends ConnectionLine {

    public AssociateLine(UMLCanvas canvas, Port portFrom, Port portTo) {
        super(canvas, portFrom, portTo);
    }

    @Override
    protected void paintHead(Graphics2D g2d) {

    }
}
