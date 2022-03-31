package org.wasabineko.graphic.shape.basicObj;

import org.wasabineko.graphic.shape.basicObj.portObj.PortOverlay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class ClassObj extends BasicObj{
    protected Shape reactOutLine;
    protected Shape reactInside;


    public ClassObj(int posX, int posY) {
        super(posX, posY, new JLabel("class", SwingConstants.CENTER), new PortOverlay());

        int borderSize = 2;
        reactOutLine = new Rectangle2D.Double(0, 0, 100, 150);
        reactInside = new Rectangle2D.Double(borderSize, borderSize, 100 - 2*borderSize, 150 - 2*borderSize );

        setBounds(posX, posY, 100, 150);
        nameLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.add(nameLabel);
        this.add(portOverlay);
        portOverlay.setMaster(this);

        setLayout(null);
        setOpaque(false);
    }

    @Override
    public boolean isInShape(MouseEvent event) {
        return this.reactOutLine.contains(event.getX(), event.getY());
    }

    @Override
    public void paintSelf(Graphics2D g2d) {
        g2d.setPaint(Color.BLACK);
        g2d.fill(reactOutLine);
        g2d.setPaint(Color.LIGHT_GRAY);
        g2d.fill(reactInside);
    }
}
