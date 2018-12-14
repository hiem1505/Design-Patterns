/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint_dp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
/**
 *
 * @author Tsjerk
 */
public class Paint_DP {
    public static String mode;

    /**
     * @param args the command line arguments
     */
    public static void draw(JFrame paint) {
        paint.add( new JComponent() {
        private List<Shape> shapes = new ArrayList<> ();
        private List<ShapeGroup> shapeGroups = new ArrayList<> ();
        private Shape currentShape = null;
        private int selectedShapePos = -1;
        private Point beginPoint = null;
        private List<Integer> moveShapes = new ArrayList<> ();

        {
            MouseAdapter mouseAdapter = new MouseAdapter ()
            {
                @Override
                public void mousePressed ( MouseEvent e )
                {
                    beginPoint = e.getPoint();
                    if(mode.equals("R")) {
                        Rectangle rect = new Rectangle(beginPoint, beginPoint);
                        rect.draw();
                        currentShape = rect;
                        shapes.add ( currentShape );
                    }
                    else if(mode.equals("E")) {
                        Ellipse elli = new Ellipse(beginPoint, beginPoint);
                        elli.draw();
                        currentShape = elli;
                        shapes.add ( currentShape );
                        //currentShape = (Shape) new Ellipse2D.Double(beginPoint.x, beginPoint.y, 1,1);
                    }
                    else if (mode.equals("T")) {
                        ShapeGroup r1 = new RectangleGROUPTEST(new Point(100,100), new Point(200,200));
                        r1.draw();
                        ShapeGroup r2 = new RectangleGROUPTEST(new Point(110,110), new Point(190,190));
                        r2.draw();
                        shapeGroups.add(r1);
                        shapeGroups.add(r2);
                        r1.addGroup(r2);
                        
                    }
                    else {
                        int i = 0;
                        for(Shape shape: shapes) {
                            if(beginPoint.x <= shape.getLowerRight().x && beginPoint.x >= shape.getUpperLeft().x && beginPoint.y >= shape.getUpperLeft().y && beginPoint.y <= shape.getLowerRight().y) {
                                currentShape = shape;
                                selectedShapePos = i;
                                System.out.println("Shape " + (i + 1) + "\n");
                                //Console log coördinates
                                break;
                            }
                            i++;
                        }
                    }
                    repaint ();
                }

                @Override
                public void mouseDragged ( MouseEvent e )
                {
                    if(mode.equals("R") || mode.equals("E")) {
                        currentShape.update(beginPoint, e.getPoint());
                    }
                    else if(mode.equals("+") || mode.equals("-") || mode.equals("T")) {
                        //Nothing
                    }
                    else {
                        currentShape.update(new Point(currentShape.getUpperLeft().x + (e.getPoint().x - beginPoint.x), currentShape.getUpperLeft().y + (e.getPoint().y - beginPoint.y)),
                                    new Point(currentShape.getLowerRight().x + (e.getPoint().x - beginPoint.x), currentShape.getLowerRight().y + (e.getPoint().y - beginPoint.y)));
                            //tempShape.setFirst(new Point(tempShape.getFirst().x + e.getPoint().x - beginPoint.x, tempShape.getFirst().y + e.getPoint().y - beginPoint.y));
                            //tempShape.setSecond(new Point(tempShape.getFirst().x + e.getPoint().x - beginPoint.x, tempShape.getFirst().y + e.getPoint().y - beginPoint.y));
                            beginPoint = e.getPoint();
                    }
//                    if(mode.equals("R")) {
//                        Rectangle rect = ( Rectangle ) currentShape;
//                        rect.setFrameFromDiagonal ( beginPoint, e.getPoint () );
//                    }
//                    else {
//                        Ellipse2D elli = ( Ellipse2D ) currentShape;
//                        elli.setFrameFromCenter(beginPoint, e.getPoint());
//                    }
                    repaint ();
                }

                @Override
                public void mouseReleased ( MouseEvent e )
                {
                    if(mode.equals("+")) {
                        currentShape.update(new Point(currentShape.getUpperLeft().x - 10, currentShape.getUpperLeft().y - 10), new Point(currentShape.getLowerRight().x + 10, currentShape.getLowerRight().y + 10));
                    }
                    if(mode.equals("-")) {
                        currentShape.update(new Point(currentShape.getUpperLeft().x + 10, currentShape.getUpperLeft().y + 10), new Point(currentShape.getLowerRight().x - 10, currentShape.getLowerRight().y - 10));
                    }
                    if(mode.equals("S")) {
                        shapes.set(selectedShapePos, currentShape);
                        selectedShapePos = -1;
                    }
                    moveShapes.clear();
                    currentShape = null;
                    beginPoint = null;
                    repaint ();
                }
            };
            addMouseListener ( mouseAdapter );
            addMouseMotionListener ( mouseAdapter );
        }

        @Override
        protected void paintComponent ( Graphics g )
        {
        Graphics2D g2d = ( Graphics2D ) g;
        g2d.setPaint ( Color.BLACK );
        shapes.forEach((shape) -> {
            g2d.draw ((java.awt.Shape) shape);
                });
        shapeGroups.forEach((shape) -> {
            g2d.draw ((java.awt.Shape) shape);
                });
        }
    } );
    }
    public static void main(String[] args) 
    {
        mode = "R";   
        
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem testItem1, menuItem1, menuItem2, menuItem3, menuItem4, menuItem5;
        
        menuBar = new JMenuBar();
        menu = new JMenu("Shape");
        menuBar.add(menu);
        
        testItem1 = new JMenuItem("Test");
        menu.add(testItem1);
        
        testItem1.addActionListener((ActionEvent e) -> {
            mode = "T";
        });
        
        menuItem1 = new JMenuItem("Rectangle");
        menu.add(menuItem1);
        
        menuItem1.addActionListener((ActionEvent e) -> {
            mode = "R";
        });
        
        menuItem2 = new JMenuItem("Ellipse");
        menu.add(menuItem2);
        
        menuItem2.addActionListener((ActionEvent e) -> {
            mode = "E";
        });
        
        menuItem3 = new JMenuItem("Select");
        menu.add(menuItem3);
        
        menuItem3.addActionListener((ActionEvent e) -> {
            mode = "S";
        });
        
        menuItem4 = new JMenuItem("Make shape bigger");
        menu.add(menuItem4);
        
        menuItem4.addActionListener((ActionEvent e) -> {
            mode = "+";
        });
        
        menuItem5 = new JMenuItem("Make shape smaller");
        menu.add(menuItem5);
        
        menuItem5.addActionListener((ActionEvent e) -> {
            mode = "-";
        });
        
        JFrame paint = new JFrame();
        paint.setJMenuBar(menuBar);
        
        draw(paint);

        paint.setSize ( 500, 500 );
        paint.setLocationRelativeTo ( null );
        paint.setVisible ( true );
       }
}