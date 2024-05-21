import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.*;
import java.util.ArrayList;
import java.util.Set;

public  class HexagonPanel extends JPanel {

    private static final int HEX_SIZE = 45;
    private final int hexWidth = (int) (Math.sin(Math.PI / 3) * HEX_SIZE * 2);
    private final int hexHeight = HEX_SIZE * 3 / 2;

    private AtomManager atomManager;
    private NumberManager numberManager;
    private RayManager rayManager;
    private HexagonManager hexagonManager;
    private ScoreManager scoreManager;
    public static boolean rayTermination = false;
    public ArrayList<Point> rayEndPoints = new ArrayList<>();
    private ArrayList<Point> userGuessLocations = new ArrayList<>();
    private int correctGuesses = 0;
    private boolean showRays = false;
    private boolean endGame = false;
    private int score;   

    // Constructor
    public HexagonPanel() 
    {
        this.hexagonManager = new HexagonManager(HEX_SIZE);
        this.atomManager = new AtomManager(hexWidth, hexHeight);
        this.numberManager = new NumberManager(hexWidth, hexHeight);
        this.rayManager = new RayManager();
        this.scoreManager = new ScoreManager();       
    }
    
    // Handling mouse clicks for guessing atoms
    private void handleMouseClick(MouseEvent e) {
        int clickX = e.getX();
        int clickY = e.getY();
        double minDistance = Double.MAX_VALUE;
        Point closestCenter = null;
        int maxGuess = 6;

        if(userGuessLocations.size() < maxGuess)
            for (Point center : hexagonManager.getHexagonCenters()) { // Assuming you have a method to get centers
                double distance = Math.sqrt(Math.pow(center.x - clickX, 2) + Math.pow(center.y - clickY, 2));
                if (distance < HEX_SIZE && distance < minDistance) {
                    minDistance = distance;
                    closestCenter = center;
                }
            }

            if (closestCenter != null && !userGuessLocations.contains(closestCenter)) {
                userGuessLocations.add(closestCenter); // Storing centers where circles will be drawn
                repaint();
            }
    }


    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        hexagonManager.drawHexagons(g);
        numberManager.drawNumbers(g); // Method to draw the numbers


        if(endGame){
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    handleMouseClick(e);
                }
            });
            if(userGuessLocations.size() == 6 ){
                rayManager.drawRays(g);
                atomManager.drawAtoms(g);
            }

        }
        // Drawing ray from number to the nearest hexagon
        Point newCoordinates= new Point(0,0);
        int angle =0;
        if(!endGame){
            if (!HexLayout.rayMarkerNumbers.isEmpty()) 
            {
                for(int i=0;i<HexLayout.rayMarkerNumbers.size();i++)
                {
                    int lastEnteredNumber = HexLayout.rayMarkerNumbers.get(i);
                    angle = numberManager.numberToAngle(lastEnteredNumber);
                    if (angle != -1) 
                    {
                        rayTermination = false;
                        Point startingCoordinates = calculatingStartingPoint(lastEnteredNumber);
                        angle += atomManager.atomPrediction(startingCoordinates, angle);
                        newCoordinates = rayManager.drawRayPath(startingCoordinates, angle, 66);
                        
                    }
                }

                // Loop to complete the ray path
                while(rayManager.rayOutOfBounds(newCoordinates) == 1 && rayTermination == false)
                {
                    if(atomManager.atomPrediction(newCoordinates, angle) == -180){
                        newCoordinates.x += 4;
                        newCoordinates = rayManager.drawRayPath(newCoordinates, angle+90, 4);
                    }
                    angle += atomManager.atomPrediction(newCoordinates, angle);
                
                    newCoordinates = rayManager.drawRayPath(newCoordinates, angle, 77);        
                }
                
                if(rayTermination == false)
                {
                    rayEndPoints.add(new Point(newCoordinates.x,newCoordinates.y));
                }  
            } 
        }
        for(Point p:rayEndPoints)
        {
            g.setColor(Color.WHITE);
            g.fillOval(p.x-6, p.y-6, 15, 15);
        } 
        atomManager.drawUserGuessLocationsAtoms(g, userGuessLocations);
        coloringAtoms(g);
        updateScore(score);
    }


    // Method to update the score 
    public void updateScore(int score){
        score = scoreManager.score(correctGuesses,HexLayout.rayMarkerNumbers,userGuessLocations);
        HexLayout.updateScore(score);
    }

    // Method to color atoms green if guesses correctly
    public void coloringAtoms(Graphics g){
        correctGuesses = 0;
        Set<Point> atomsCoordinates = atomManager.getAtomPositions();
        for(Point p:userGuessLocations){
            if(atomsCoordinates.contains(p)){
                correctGuesses++;
                g.setColor(Color.decode("#046307"));
                g.fillOval(p.x - 25, p.y - 25, 2 * 25, 2 * 25);
            }
        }
    }

    // Method to toggle endGame from false to true
    public void toggleEndGame() 
    {
        endGame = !endGame; // Toggle the visibility state
        repaint();            
    }

    // Method to calculate ray starting coordinates
    public Point calculatingStartingPoint(Integer number) 
    {
        Point start = numberManager.retrieveCoordinates(number);
        return start;
    }
    
    public void addNumberAndCalculateAngle(Integer number) 
    {
        Point start = numberManager.retrieveCoordinates(number);
        int angle = numberManager.numberToAngle(number);
        rayManager.drawRayPath(start, angle, 66); 
        repaint();
    }
       
}


