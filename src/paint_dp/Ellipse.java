/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint_dp;

import java.awt.Point;

/**
 *
 * @author Tsjerk
 */
public class Ellipse extends java.awt.geom.Ellipse2D.Double implements Shape {
    private Point upperLeft;
    private Point lowerRight;
    
    public Ellipse(Point first, Point second) {
        this.upperLeft = new Point(Math.min(first.x, second.x), Math.min(first.y, second.y));
        this.lowerRight = new Point(Math.max(first.x, second.x), Math.max(first.y, second.y));
    }
    @Override
    public void draw() {
        super.setFrameFromDiagonal(upperLeft, lowerRight);
    }
    
    @Override
    public void update(Point first, Point second) {
        this.upperLeft = new Point(Math.min(first.x, second.x), Math.min(first.y, second.y));
        this.lowerRight = new Point(Math.max(first.x, second.x), Math.max(first.y, second.y));
        this.draw();
    }

    @Override
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    @Override
    public Point getLowerRight() {
        return this.lowerRight;
    }
}
