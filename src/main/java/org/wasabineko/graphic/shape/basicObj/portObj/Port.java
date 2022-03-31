package org.wasabineko.graphic.shape.basicObj.portObj;

import org.wasabineko.graphic.shape.basicObj.BasicObj;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class Port extends JPanel {
    final BasicObj master;
    public Port(BasicObj master, int posX, int posY, int size) {
        super();
        this.master = master;
        this.setBackground(Color.BLACK);
        this.setBounds(posX, posY, size, size);   //TODO: generalConfig
        this.setOpaque(true);
    }

    public BasicObj getMaster() {
        return this.master;
    }

    public Point getConvertedCoordinate(Component destination) {
        return SwingUtilities.convertPoint(master, this.getX(), this.getY(), destination);
    }
}
