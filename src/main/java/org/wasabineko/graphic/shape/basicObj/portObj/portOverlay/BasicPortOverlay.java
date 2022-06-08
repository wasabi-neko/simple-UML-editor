package org.wasabineko.graphic.shape.basicObj.portObj.portOverlay;

import org.wasabineko.graphic.shape.basicObj.BasicObj;
import org.wasabineko.graphic.shape.basicObj.portObj.Port;

public class BasicPortOverlay extends PortOverlay {
    public BasicPortOverlay() {}

    @Override
    public void init(BasicObj master) {
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
}
