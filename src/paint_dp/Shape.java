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
public interface Shape {
    void draw();
    void update(Point f, Point s);
    Point getUpperLeft();
    Point getLowerRight();
   
}
