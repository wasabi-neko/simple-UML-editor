package org.wasabineko.graphic.shape.basicObj.portObj.portOverlay;

import org.wasabineko.graphic.shape.basicObj.BasicObj;
import org.wasabineko.graphic.shape.basicObj.portObj.Port;
import org.wasabineko.graphic.shape.connectionLine.ConnectionLine;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * I'm just too tired to make another PortLayout abstraction for its future concreted child
 */
public abstract class PortOverlay extends JPanel {
    protected final List<Port> portList = new ArrayList<>();

    public PortOverlay() {
        super();
    }

    abstract public void init(BasicObj master);

    public List<Port> getPortList() {
        return  this.portList;
    }

    public Port getClosetPort(int x, int y) {
        if (this.portList.size() <= 0)
            return null;

        Port minPort = this.portList.get(0);
        double minDistance = Double.MAX_VALUE;
        for (Port port : this.getPortList()) {
            double distance = Point2D.distance(port.getX(), port.getY(), x, y);
            if (distance < minDistance) {
                minDistance = distance;
                minPort = port;
            }
        }

        return minPort;
    }

    public void bringAllLineToFront() {
        for (Port port : this.portList) {
            for (ConnectionLine line : port.getLineList()) {
                line.bringToFront();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintChildren(g);
        for (Port port : this.portList) {
            for (ConnectionLine line : port.getLineList()) {
                line.repaint();
            }
        }
    }
}
