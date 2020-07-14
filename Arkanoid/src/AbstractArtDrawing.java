import java.awt.Color;
import java.util.Random;

import biuoop.DrawSurface;
import biuoop.GUI;
import geometry.Line;
import geometry.Point;

/**
 *
 * @author hadar
 *
 */
public class AbstractArtDrawing {

   /**
    *
    * @param point a given point
    * @param color a given color
    * @param drawSurface a given GUI frame to draw on
    */
   public void drawPoint(Point point, Color color, DrawSurface drawSurface) {
          int r = 3;
          int x1 = (int) point.getX();
          int y1 = (int) point.getY();
          drawSurface.setColor(color);
          drawSurface.fillCircle(x1, y1, r);
   }

   /**
    *
    * @return random line
    */
   public Line generateRandomLine() {
       Random rand = new Random();
       int x1 = rand.nextInt(400) + 1;
       int y1 = rand.nextInt(300) + 1;
       int x2 = rand.nextInt(400) + 1;
       int y2 = rand.nextInt(300) + 1;
       return new Line(x1, y1, x2, y2);
   }

   /**
    *
    * @param line a given line
    * @param drawSurface a given GUI frame to draw on
    */
   public void drawLine(Line line, DrawSurface drawSurface) {
       int x1 = (int) line.start().getX();
       int y1 = (int) line.start().getY();
       int x2 = (int) line.end().getX();
       int y2 = (int) line.end().getY();
       drawSurface.setColor(Color.BLACK);
       drawSurface.drawLine(x1, y1, x2, y2);
   }

   /**
    * the program creates 10 lines on the screen
    * and shows their middle and intersection points.
    *
    * @param args ignored.
    */
   public static void main(String[] args) {
       AbstractArtDrawing abstractArtDrawing  = new AbstractArtDrawing();
       Line[] lines = new Line[10];
       GUI gui = new GUI("Random Lines", 400, 300);
       DrawSurface drawSurface = gui.getDrawSurface();
       for (int i = 0; i < 10; i++) {
           Line line = abstractArtDrawing.generateRandomLine();
           abstractArtDrawing.drawLine(line, drawSurface);
           abstractArtDrawing.drawPoint(line.middle(), Color.BLUE, drawSurface);
           lines[i] = line;
       }
       for (int i = 0; i < lines.length; i++) {
           for (int j = 1; j < lines.length; j++) {
               Point intersectPoint = lines[i].intersectionWith(lines[j]);
               if (intersectPoint != null) {
                   abstractArtDrawing.drawPoint(intersectPoint, Color.RED, drawSurface);
               }
           }
       }
       gui.show(drawSurface);
   }
 }