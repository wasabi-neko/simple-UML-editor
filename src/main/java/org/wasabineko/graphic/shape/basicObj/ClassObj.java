package org.wasabineko.graphic.shape.basicObj;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ClassObj extends BasicObj{
    protected Shape reactOutLine;
    protected Shape reactInside;


    public ClassObj(int posX, int posY) {
        super(posX, posY, new JLabel("class", SwingConstants.CENTER));

        int borderSize = 2;
        reactOutLine = new Rectangle2D.Double(0, 0, 100, 150);
        reactInside = new Rectangle2D.Double(borderSize, borderSize, 100 - 2*borderSize, 150 - 2*borderSize );

        setBounds(posX, posY, 100, 150);
        nameLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.add(nameLabel);
        setLayout(null);
        setOpaque(false);
    }

    @Override
    public boolean isInShape(int x, int y) {
        return this.reactOutLine.contains(x, y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.BLACK);
        g2d.fill(reactOutLine);
        g2d.setPaint(Color.LIGHT_GRAY);
        g2d.fill(reactInside);
        paintChildren(g);
    }
}
