package org.wasabineko.graphic.shape.basicObj;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class UseCaseObj extends BasicObj {
    protected Shape ovalOutside;
    protected Shape ovalInside;
    protected Color color;

    public UseCaseObj(int posX, int posY) {
        super(posX, posY, new JLabel("Usecase", SwingConstants.CENTER));

        ovalOutside = new Ellipse2D.Double(0, 0, 200, 100);  //TODO: general config
        int borderSize = 2;
        ovalInside = new Ellipse2D.Double(borderSize, borderSize, 200 - borderSize*2, 100 - borderSize*2);  //TODO: general config
        color = Color.LIGHT_GRAY;
        setBounds(posX, posY, 200, 100);
        nameLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.add(nameLabel);
        setLayout(null);
        setOpaque(false);
    }

    @Override
    public boolean isInShape(int x, int y) {
        return this.ovalOutside.contains(x, y);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(Color.black);
        g2d.fill(this.ovalOutside);
        g2d.setPaint(this.color);
        g2d.fill(this.ovalInside);
        paintChildren(g);
    }
}
