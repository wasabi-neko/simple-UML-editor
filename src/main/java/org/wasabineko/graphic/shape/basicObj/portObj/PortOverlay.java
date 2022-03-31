package org.wasabineko.graphic.shape.basicObj.portObj;

import org.wasabineko.graphic.shape.basicObj.BasicObj;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * I'm just too tired to make another PortLayout abstraction for its future concreted child
 */
public class PortOverlay extends JPanel {
    private BasicObj master;
    private List<Port> portList = new ArrayList<>();

    public PortOverlay() {
        super();
    }

    public void setMaster(BasicObj master) {
        this.master = master;
        int portSize = 10;

        this.setBounds(0,  0, master.getWidth(), master.getHeight());
        //TODO: simplify this large code block
        Port pNorth = new Port(master, getWidth()/2 - portSize/2, 0, portSize);
        Port pSouth = new Port(master, getWidth()/2 - portSize/2, getHeight() - portSize, portSize);
        Port pWest = new Port(master, 0, getHeight()/2 - portSize/2, portSize);
        Port pEast = new Port(master, getWidth() - portSize, getHeight()/2 - portSize/2, portSize);
        this.add(pNorth);
        this.add(pSouth);
        this.add(pWest);
        this.add(pEast);
        portList.add(pNorth);
        portList.add(pSouth);
        portList.add(pWest);
        portList.add(pEast);
        this.setLayout(null);

        this.setOpaque(false);
        this.setVisible(false);
    }

    public List<Port> getPortList() {
        return  this.portList;
    }

    public Port getClosetPort(int x, int y) {
        Port minPort = this.portList.get(1);
        double minDistance = Double.MAX_VALUE;
        for (Port port : this.portList) {
            double distane = Point2D.distance(port.getX(), port.getY(), x, y);
            if (distane < minDistance) {
                minDistance = distane;
                minPort = port;
            }
        }

        return minPort;
    }
}
