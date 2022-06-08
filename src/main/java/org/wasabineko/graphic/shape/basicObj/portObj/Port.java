package org.wasabineko.graphic.shape.basicObj.portObj;

import org.wasabineko.graphic.shape.basicObj.BasicObj;
import org.wasabineko.graphic.shape.connectionLine.ConnectionLine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Port extends JPanel {
    final BasicObj master;
    protected final ArrayList<ConnectionLine> lineList = new ArrayList<>();
    protected boolean isFromPort;

    public Port(BasicObj master, int posX, int posY, int size) {
        super();
        this.master = master;
        this.setBackground(Color.BLACK);
        this.setBounds(posX, posY, size, size);   //TODO: generalConfig
        this.setOpaque(true);
    }

    public void addConnectionLine(ConnectionLine connectionLine, boolean isConnectedFrom) {
        lineList.add(connectionLine);
        this.isFromPort = isConnectedFrom;
    }

    public boolean isFromPort() {
        return this.isFromPort;
    }

    public ArrayList<ConnectionLine> getLineList() {
        return lineList;
    }

    public void repaintConnectedLine() {
        for (ConnectionLine line : lineList) {
            line.repaint();
        }
    }

    public BasicObj getMaster() {
        return this.master;
    }

    public Point getConvertedCoordinate(Component destination) {
        return SwingUtilities.convertPoint(master, this.getX(), this.getY(), destination);
    }
}
