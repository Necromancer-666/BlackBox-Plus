import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HexagonManager 
{
    private static final int HEX_SIZE = 45;
    private static final int[] arr = {5, 6, 7, 8, 9, 8, 7, 6, 5};
    private int hexWidth;
    private int hexHeight;
    private Map<Integer, Point> centres = new HashMap<>(); // Map to store centres of all hexagons

    // Constructor
    public HexagonManager(int hexSize) 
    {
        this.hexWidth = (int) (Math.sin(Math.PI / 3) * hexSize * 2);
        this.hexHeight = hexSize * 3 / 2;    
    }

    // Method to draw hexagons
    public void drawHexagons(Graphics g) 
    {
        g.setColor(Color.decode("#FFDF00"));          
        int i=1;
        for (int row = 0; row < 9; row++) {
            int GRID_WIDTH = arr[row];
            for (int col = 0; col < GRID_WIDTH; col++) {
                int centerX;
                if (row < 5) {
                    centerX = (col * hexWidth - ((row % 5) * hexWidth) / 2) + 500;
                } else {
                    centerX = (col * hexWidth + ((row % 5) * hexWidth) / 2) + 386;
                }
               
                int centerY = (row * hexHeight) + 150;
               
                centres.put(i,new Point(centerX,centerY));
                i++;
               
                Polygon hexagon = createHexagon(centerX, centerY, HEX_SIZE, row);

                g.drawPolygon(hexagon);
            }
        }       
    }

    // Creating a single hexagon
    public Polygon createHexagon(int centerX, int centerY, int size, int row)
    {
        Polygon hexagon = new Polygon();
        for (int i = 0; i < 6; i++) {
            int x = (int) (centerX + size * Math.sin(i * 2 * Math.PI / 6));
            int y = (int) (centerY + size * Math.cos(i * 2 * Math.PI / 6));
            hexagon.addPoint(x, y);
        }
        return hexagon;
    }

    // Retrieving the centres of all hexagons
    public Set<Point> getHexagonCenters()
    {
        Set <Point> coordinates = new HashSet<>();
        for(Point p:centres.values())
        {
            coordinates.add(p);
        }
        return coordinates;
    }
}
