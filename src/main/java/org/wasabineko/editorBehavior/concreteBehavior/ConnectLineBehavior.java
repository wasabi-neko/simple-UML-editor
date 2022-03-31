package org.wasabineko.editorBehavior.concreteBehavior;

import org.wasabineko.editorBehavior.EditorBehavior;
import org.wasabineko.graphic.UMLCanvas;
import org.wasabineko.graphic.shape.UMLObj;
import org.wasabineko.graphic.shape.basicObj.BasicObj;
import org.wasabineko.graphic.shape.basicObj.portObj.Port;
import org.wasabineko.graphic.shape.connectionLine.ConnectionLine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ConnectLineBehavior extends EditorBehavior {

    public interface LineFactory {
        ConnectionLine createLine(UMLCanvas canvas, Port from, Port to);
    }


    private LineFactory lineFactory;
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
        if (obj instanceof BasicObj) {
            this.registerPort = ((BasicObj)obj).getPortOverlay().getClosetPort(event.getX(), event.getY());
        }
    }

    @Override
    public void objReleaseAction(UMLObj obj, MouseEvent event) {
        if (!(obj instanceof BasicObj)) {   //TODO: maybe change it to raise exception. need a better way to prevent down cast the UMLObj
            return;
        }
        Port toPort = ((BasicObj)obj).getPortOverlay().getClosetPort(event.getX(), event.getY());
        UMLCanvas canvas = (UMLCanvas) obj.getTopParent().getParent();
        ConnectionLine line = this.lineFactory.createLine(canvas, registerPort, toPort);
        canvas.add(line);
        int zOrder = canvas.getComponentZOrder(toPort.getMaster());
        canvas.setComponentZOrder(line, zOrder);
        canvas.repaint();
    }
}
