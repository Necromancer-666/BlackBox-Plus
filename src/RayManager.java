import java.awt.*;
import java.util.ArrayList;

public class RayManager 
{
    // A list of lines representing the rays that have been drawn
    private ArrayList<Line> rays = new ArrayList<>();
   
    /*
     * Nested class to represent a line with a start and end point.
     * This helps in managing the geometry of rays more effectively.
    */
    public static class Line 
    {
        Point start;
        Point end;

        public Line(Point start, Point end) 
        {
            this.start = start;
            this.end = end;
        }
    }

    // Method to add rays to rays Arraylist
    public void addRay(Point start, Point end) {
        rays.add(new Line(start, end));
    }

    // Method to draw rays using the Graphics object
    public void drawRays(Graphics g) 
    {
        for (Line ray : rays) {
            g.setColor(Color.decode("#00FFFF"));
            g.drawLine(ray.start.x, ray.start.y, ray.end.x, ray.end.y);
        } 
    }

    // Method to get rays 
    public ArrayList<Line> getRays() 
    {
        return rays;
    }

    // Method to draw the rays
    public Point drawRayPath(Point coordinates, double angleDegrees, double distance)
    {
        double angleRadians = Math.toRadians(angleDegrees);
    
        int deltaX = (int)(Math.cos(angleRadians) * distance);
        int deltaY = (int)(Math.sin(angleRadians) * distance);
    
        int endX = coordinates.x + deltaX;
        int endY = coordinates.y - deltaY; 
        
        rays.add(new Line(new Point(coordinates.x, coordinates.y), new Point(endX, endY)));
      
        Point end = new Point(endX,endY);

        return end;
    }

    // Method to check if ray has exited the hexagon grid
    public int rayOutOfBounds(Point coordinate) 
    {
        // Center of the grid
        int centerX = 654;
        int centerY = 418;

        // Max distances allowed from the center
        int maxDistanceX = 324;
        int maxDistanceY = 323;

        // Calculate distances from the center
        int distanceX = Math.abs(coordinate.x - centerX); 
        int distanceY = Math.abs(coordinate.y - centerY); 

        // Check if the point is outside the circular boundary
        if (distanceX > maxDistanceX || distanceY > maxDistanceY) {
            return 0; 
        }

        return 1;
    }
}