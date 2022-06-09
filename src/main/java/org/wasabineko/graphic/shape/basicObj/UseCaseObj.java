package org.wasabineko.graphic.shape.basicObj;

import org.wasabineko.graphic.shape.basicObj.portObj.portOverlay.BasicPortOverlay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class UseCaseObj extends BasicObj {
    protected final Ellipse2D ovalOutside;
    protected final Ellipse2D ovalInside;
    protected final Color color;

    public UseCaseObj(int posX, int posY) {
        super(posX, posY, true, new JLabel("Usecase", SwingConstants.CENTER), new BasicPortOverlay());

        ovalOutside = new Ellipse2D.Double(0, 0, 200, 100);  //TODO: general config
        int borderSize = 2;
        ovalInside = new Ellipse2D.Double(borderSize, borderSize, 200 - borderSize*2, 100 - borderSize*2);  //TODO: general config
        color = Color.LIGHT_GRAY;

        setBounds(posX, posY, 200, 100);
        nameLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.add(nameLabel);
        this.portOverlay.init(this);
        this.add(portOverlay);

        setLayout(null);
        setOpaque(false);
    }

    @Override
    public boolean isInShape(MouseEvent event) {
        return this.ovalOutside.contains(event.getX(), event.getY());
    }

    @Override
    public void paintSelf(Graphics2D g2d) {
        g2d.setPaint(Color.black);
        g2d.fill(this.ovalOutside);
        g2d.setPaint(this.color);
        g2d.fill(this.ovalInside);
    }
}
