package org.wasabineko.graphic.shape.basicObj.portObj;

import org.wasabineko.graphic.shape.basicObj.BasicObj;

import javax.swing.*;
import java.awt.*;

/**
 * I'm just too tired to make another PortLayout abstraction for its future concreted child
 */
public class PortOverlay extends JPanel {
    static public class Port extends JPanel {
        public Port(int posX, int posY) {
            super();
            this.setBackground(Color.BLACK);
            this.setBounds(posX, posY, 5, 5);   //TODO: generalConfig
            this.setOpaque(true);
        }

//        public Dimension2D getRelevantCoord(JComponent component) {
//
//        }
    }

    BasicObj master;

    public PortOverlay() {
        super();
    }

    public void setMaster(BasicObj master) {
        this.master = master;
        this.setBounds(0,  0, master.getWidth(), master.getHeight());

        Port pNorth = new Port(getWidth() / 2, 0);
        Port pSouth = new Port(getWidth() / 2, getHeight());
        Port pWest = new Port(0, getHeight() / 2);
        Port pEast = new Port(0, getHeight() / 2);
        this.add(pNorth);
        this.add(pSouth);
        this.add(pWest);
        this.add(pEast);

        this.setBackground(Color.CYAN);
        this.setOpaque(true);
        this.setVisible(false);
    }
}
