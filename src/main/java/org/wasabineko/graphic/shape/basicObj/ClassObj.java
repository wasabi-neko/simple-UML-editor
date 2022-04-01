package org.wasabineko.graphic.shape.basicObj;

import org.wasabineko.graphic.shape.basicObj.portObj.PortOverlay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class ClassObj extends BasicObj{
    protected final Shape reactOutLine;
    protected final Shape reactInside;


    public ClassObj(int posX, int posY) {
        super(posX, posY, true, new JLabel("class", SwingConstants.CENTER), new PortOverlay());

        int borderSize = 2;
        reactOutLine = new Rectangle2D.Double(0, 0, 100, 150);
        reactInside = new Rectangle2D.Double(borderSize, borderSize, 100 - 2*borderSize, 150 - 2*borderSize );

        setBounds(posX, posY, 100, 150);
        nameLabel.setBounds(0, 0, this.getWidth(), this.getHeight() / 3);
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

        Line2D line1 = new Line2D.Double(0, this.getHeight()/3.0, this.getWidth(), this.getHeight()/3.0);
        Line2D line2 = new Line2D.Double(0, this.getHeight()*2/3.0, this.getWidth(), this.getHeight()*2/3.0);
        g2d.setPaint(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.draw(line1);
        g2d.draw(line2);
    }
}
