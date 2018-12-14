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
public interface ShapeGroup {
    void draw();
    void update(Point f, Point s);
    void addGroup(ShapeGroup s);
    void remove(ShapeGroup s);
    ArrayList<ShapeGroup> getShapeGroups();
    Point getUpperLeft();
    Point getLowerRight();
}
