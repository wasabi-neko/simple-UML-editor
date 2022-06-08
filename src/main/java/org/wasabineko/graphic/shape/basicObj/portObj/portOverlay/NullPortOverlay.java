package org.wasabineko.graphic.shape.basicObj.portObj.portOverlay;

import org.wasabineko.graphic.shape.basicObj.BasicObj;
import org.wasabineko.graphic.shape.basicObj.portObj.Port;

public class NullPortOverlay extends PortOverlay{

    public NullPortOverlay() {
        super();
    }

    @Override
    public void init(BasicObj master) {
        /*do nothing*/
    }

    @Override
    public Port getClosetPort(int x, int y) {
        return null;
    }
}
