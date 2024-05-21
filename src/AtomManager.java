import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AtomManager {
    private Set<Point> atomPositions;
    private final int hexWidth;
    private final int hexHeight;
    private final int radius = 25;

    // Constructor
    public AtomManager(int hexWidth, int hexHeight) 
    {
        this.hexWidth = hexWidth;
        this.hexHeight = hexHeight;
        this.atomPositions = generateAtomPositions();
    }

    // Method to retreive atom positions
    public Set<Point> getAtomPositions()
    {
        return atomPositions;
    }

    // Generating 6 random atom positions 
    public Set<Point> generateAtomPositions() 
    {
        Set<Point> positions = new HashSet<>();
        ArrayList<int[]> gridPositions = new ArrayList<>();
        int rowCount = 9; 
        int[] arr = {5, 6, 7, 8, 9, 8, 7, 6, 5};

        for (int row = 0; row < rowCount; row++) 
        {
            for (int col = 0; col < arr[row]; col++) 
            {
                gridPositions.add(new int[] {row, col});
            }
        }

        // Shuffle the list to randomize position selection
        Collections.shuffle(gridPositions, new Random());

        // Pick the first 6 unique positions from the shuffled list
        for (int i = 0; i < 6; i++) 
        {
            int[] pos = gridPositions.get(i);
            int centerX = calculateCenterX(pos[0], pos[1]);
            int centerY = calculateCenterY(pos[0]);
            positions.add(new Point(centerX, centerY));
        }
        return positions;
    }

    // Method to draw hidden atoms
    public void drawAtoms(Graphics g) 
    {
        Color red = new Color(255, 43, 43); 
        for (Point position : atomPositions) 
        {
            g.setColor(Color.white);
            g.drawOval((position.x - radius)-50, (position.y - radius)-48, 150, 150);
        }
        for (Point position : atomPositions) 
        {
            g.setColor(red);
            g.fillOval(position.x - radius, position.y - radius, 2 * radius, 2 * radius);
            
        }
    }

    // Method to draw user guessed atoms
    public void drawUserGuessLocationsAtoms(Graphics g,ArrayList<Point> coordinates)
    {
        for (Point position : coordinates) 
        {
            g.setColor(Color.lightGray);
            g.fillOval(position.x - radius, position.y - radius, 2 * radius, 2 * radius);
        }
    }

    // Helper method to calculate x cordinate for atom
    private int calculateCenterX(int row, int col) 
    {
        if (row < 5) {
            return (col * hexWidth - ((row % 5) * hexWidth) / 2) + 500;
        } else {
            return (col * hexWidth + ((row % 5) * hexWidth) / 2) + 386;
        }
    }

    // Helper method to calculate y cordinate for atom
    private int calculateCenterY(int row) 
    {
        return (row * hexHeight) + 150;
    }

    // Method to predict if the neighbouring hexagons contain an atom/atoms
    public int atomPrediction(Point rayCoordinates, int angle)
    {
        Point predictMiddle = predictNextMiddleAtom(rayCoordinates,angle);
        Point predictLeft = predictNextLeftAtom(rayCoordinates,angle);
        Point predictRight = predictNextRightAtom(rayCoordinates,angle);
        ArrayList<Double> Middle = new ArrayList<>();
        ArrayList<Double> Left = new ArrayList<>();
        ArrayList<Double> Right = new ArrayList<>();

        Set<Point> coordinates = getAtomPositions();
        for(Point coordinate : coordinates)
        {          
           double distanceBetweenPointsM = Math.sqrt(Math.pow((coordinate.x - predictMiddle.x), 2) + Math.pow((coordinate.y - predictMiddle.y), 2)); 
           Middle.add(distanceBetweenPointsM);
           double distanceBetweenPointsL = Math.sqrt(Math.pow((coordinate.x - predictLeft.x), 2) + Math.pow((coordinate.y - predictLeft.y), 2));
           Left.add(distanceBetweenPointsL);
           double distanceBetweenPointsR = Math.sqrt(Math.pow((coordinate.x - predictRight.x), 2) + Math.pow((coordinate.y - predictRight.y), 2));               
           Right.add(distanceBetweenPointsR);    
        }

        boolean containsLessThan35M = Middle.stream()
                                            .anyMatch(number -> number < 35);
        boolean containsLessThan35L = Left.stream()
                                            .anyMatch(number -> number < 35);
        boolean containsLessThan35R = Right.stream()
                                            .anyMatch(number -> number < 35);

        if(containsLessThan35L && containsLessThan35R)
        {
            return -180; // U Turn
        }
        else if(containsLessThan35L && containsLessThan35M)
        {
            return -120; // Right Turn Down
        }
        else if(containsLessThan35R && containsLessThan35M)
        {
            return 120; // Left Turn Down
        }
        else if(containsLessThan35L)
        {
            return -60; // Right Turn Up
        }
        else if(containsLessThan35R)
        {
            return 60; // Right Turn Up
        }
        else if(containsLessThan35M)
        {
            HexagonPanel.rayTermination = true; // Stop and terminate
        }

        return 0; // Default
    }

    // Helper method 
    public Point predictNextMiddleAtom(Point rayCoordinates, int angle)
    {
        return determiningNextPoint(rayCoordinates, angle);
    }

    // Helper method 
    public Point predictNextLeftAtom(Point rayCoordinates, int angle)
    {
        angle+=60;
        return determiningNextPoint(rayCoordinates, angle);
    }

    // Helper method 
    public Point predictNextRightAtom(Point rayCoordinates, int angle)
    {
        angle-=60;
        return determiningNextPoint(rayCoordinates, angle); 
    }

    // Calculations for predicting atoms
    private Point determiningNextPoint(Point rayCoordinates, int angle)
    {
        double angleRadians = Math.toRadians(angle);
        int deltaX = (int)(Math.cos(angleRadians) * 70);
        int deltaY = (int)(Math.sin(angleRadians) * 70);
    
        int endX = rayCoordinates.x + deltaX;
        int endY = rayCoordinates.y - deltaY;
        return new Point(endX,endY);
     }
}
