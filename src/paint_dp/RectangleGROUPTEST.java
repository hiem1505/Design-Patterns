/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint_dp;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Tsjerk
 */
public class RectangleGROUPTEST extends java.awt.Rectangle implements ShapeGroup {
    private Point upperLeft;
    private Point lowerRight;
    private ArrayList<ShapeGroup> shapes;
    
    public RectangleGROUPTEST(Point first, Point second) {
        this.shapes = new ArrayList<>();
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
        if(!shapes.isEmpty()) {
            for (ShapeGroup shape: shapes) {
                shape.update(first, second);
            }
        }
        
    }
            
    @Override
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    @Override
    public Point getLowerRight() {
        return this.lowerRight;
    }

    @Override
    public void addGroup(ShapeGroup s) {
        shapes.add(s);
    }

    @Override
    public void remove(ShapeGroup s) {
        shapes.remove(s);
    }

    @Override
    public ArrayList<ShapeGroup> getShapeGroups() {
        return shapes;
    }
}
