package org.wasabineko.graphic.shape.basicObj.portObj;

import org.wasabineko.graphic.shape.basicObj.BasicObj;

import javax.swing.*;
import java.awt.*;

/**
 * I'm just too tired to make another PortLayout abstraction for its future concreted child
 */
public class PortOverlay extends JPanel {
    static public class Port extends JPanel {
        public Port(int posX, int posY, int size) {
            super();
            this.setBackground(Color.BLACK);
            this.setBounds(posX, posY, size, size);   //TODO: generalConfig
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
        int portSize = 10;

        this.setBounds(0,  0, master.getWidth(), master.getHeight());
        Port pNorth = new Port(getWidth()/2 - portSize/2, 0, portSize);
        Port pSouth = new Port(getWidth()/2 - portSize/2, getHeight() - portSize, portSize);
        Port pWest = new Port(0, getHeight()/2 - portSize/2, portSize);
        Port pEast = new Port(getWidth() - portSize, getHeight()/2 - portSize/2, portSize);
        this.add(pNorth);
        this.add(pSouth);
        this.add(pWest);
        this.add(pEast);
        this.setLayout(null);

        this.setOpaque(false);
        this.setVisible(false);
    }
}
