package org.wasabineko.editorBehavior.concreteBehavior;

import org.wasabineko.editorBehavior.EditorBehavior;
import org.wasabineko.graphic.UMLCanvas;
import org.wasabineko.graphic.shape.UMLObj;
import org.wasabineko.graphic.shape.basicObj.portObj.Port;
import org.wasabineko.graphic.shape.connectionLine.ConnectionLine;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ConnectLineBehavior extends EditorBehavior {

    public interface LineFactory {
        ConnectionLine createLine(Container canvas, Port from, Port to);
    }


    private final LineFactory lineFactory;
    private Port registerPort;

    public ConnectLineBehavior(LineFactory lineFactory) {
        super(ModeTag.TAGS.CONNECT_MODE.getValue());
        this.lineFactory = lineFactory;
    }

    //------------------------------------------------------------
    // Mouse event handlers
    //------------------------------------------------------------
    @Override
    public void canvasClickAction(UMLCanvas canvas, MouseEvent event) { /* do nothing*/ }
    @Override
    public void canvasPressAction(UMLCanvas canvas, MouseEvent event) { /* do nothing*/ }
    @Override
    public void canvasReleaseAction(UMLCanvas canvas, MouseEvent event) { /* do nothing*/ }
    @Override
    public void objClickAction(UMLObj obj, MouseEvent event) { /* do nothing*/ }


    @Override
    public void objPressAction(UMLObj obj, MouseEvent event) {
        this.registerPort = obj.getClosetPort(event.getX(), event.getY());
    }

    @Override
    public void objReleaseAction(UMLObj obj, MouseEvent event) {
        Port toPort = obj.getClosetPort(event.getX(), event.getY());

        // check if the formPort is null and if both fromPort and ToPort is in the same obj
        if (registerPort == null || toPort == null || obj == registerPort.getMaster()) {
            return;
        }

        Container canvas = obj.getTopParent().getParent();
        ConnectionLine line = this.lineFactory.createLine(canvas, registerPort, toPort);
        canvas.add(line);

        int toOrder = canvas.getComponentZOrder(toPort.getMaster());
        int fromOrder = canvas.getComponentZOrder(registerPort.getMaster());
        int zOrder = toOrder;
        if (zOrder > fromOrder) {zOrder = fromOrder;}

        line.bringToFront();
        line.repaint();

        this.registerPort = null;   // reset
    }
}
