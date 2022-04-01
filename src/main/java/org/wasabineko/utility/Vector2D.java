package org.wasabineko.utility;


import java.awt.*;

public class Vector2D {
    public double x = 0;
    public double y = 0;

    public Vector2D() {
        super();
    }

    public Vector2D(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    public Vector2D(Point from, Point to) {
        this.x = to.x - from.x;
        this.y = to.y - from.y;
    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D add(Vector2D vec) {
        return new Vector2D(this.x + vec.x, this.y + vec.y);
    }

    public Vector2D minus(Vector2D vec) {
        return new Vector2D(this.x - vec.x, this.y - vec.y);
    }

    public Vector2D muliScalar(double scalar) {
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    public double getLength() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public Vector2D turnDegree(double degree) {
        double sinVal = Math.sin(degree);
        double cosVal = Math.cos(degree);
        return new Vector2D(cosVal*x - sinVal*y, sinVal*x + cosVal*y);
    }

    public Point toPonint() {
        return new Point((int) this.x, (int) this.y);
    }
}
