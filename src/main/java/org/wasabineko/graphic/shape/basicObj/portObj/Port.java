package org.wasabineko.graphic.shape.basicObj.portObj;

import org.wasabineko.graphic.shape.basicObj.BasicObj;
import org.wasabineko.graphic.shape.connectionLine.ConnectionLine;

import javax.swing.*;
import java.awt.*;

public class Port extends JPanel {
    final BasicObj master;
    protected ConnectionLine connectionLine;
    protected boolean isConnectedFrom;

    public Port(BasicObj master, int posX, int posY, int size) {
        super();
        this.master = master;
        this.setBackground(Color.BLACK);
        this.setBounds(posX, posY, size, size);   //TODO: generalConfig
        this.setOpaque(true);
    }

    public void setConnectionLine(ConnectionLine connectionLine, boolean isConnectedFrom) {
        this.connectionLine = connectionLine;
        this.isConnectedFrom = isConnectedFrom;
    }

    public boolean getIsConnectedFrom() {
        return this.isConnectedFrom;
    }

    public ConnectionLine getConnectionLine() {
        return this.connectionLine;
    }

    public void repaintConnectedLine() {
        if (this.connectionLine != null) {
            this.connectionLine.repaint();
        }
    }

    public BasicObj getMaster() {
        return this.master;
    }

    public Point getConvertedCoordinate(Component destination) {
        return SwingUtilities.convertPoint(master, this.getX(), this.getY(), destination);
    }
}
