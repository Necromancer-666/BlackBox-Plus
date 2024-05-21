import java.awt.*;
import java.util.HashMap;
import java.util.Map;


// Adding the numbers around the grid
public class NumberManager {
    private static Map<Integer, Point> coordinates = new HashMap<>();
    private int hexWidth;
    private int hexHeight;
    private static final int[] arr = {5, 6, 7, 8, 9, 8, 7, 6, 5}; // Array with row configuration

    // Constructor
    public NumberManager(int hexWidth, int hexHeight) {
        this.hexWidth = hexWidth;
        this.hexHeight = hexHeight;        
    }

    // Method to draw and store coordinates of numbers around the grid
    public void drawNumbers(Graphics g) 
    {           
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.setColor(Color.decode("#FFDF00"));
        int number =1;
        int xCoordinate,yCoordinate;
        for (int row = 0; row < 9; row++) 
        {
                if (row < 5) 
                {
                    xCoordinate = ( hexWidth - ((row % 5) * hexWidth) / 2) + 395;
                    yCoordinate = (row * hexHeight) + 105;
                    coordinates.put(number, new Point(xCoordinate-3, yCoordinate-11));
                    
                } 
                else 
                {
                    xCoordinate = ( hexWidth + ((row % 5) * hexWidth) / 2) + 230;
                    yCoordinate = (row * hexHeight) + 137;
                    coordinates.put(number, new Point(xCoordinate+4, yCoordinate+2));
                }
                
                g.drawString(Integer.toString(number), xCoordinate, yCoordinate);
                number++;

                if (row < 5) 
                {
                    xCoordinate = ( hexWidth - ((row % 5) * hexWidth) / 2) + 357;
                    yCoordinate = (row * hexHeight) + 150;
                    coordinates.put(number, new Point(xCoordinate, yCoordinate));
                } 
                else 
                {
                    xCoordinate = ( hexWidth + ((row % 5) * hexWidth) / 2) + 238;
                    yCoordinate = (row * hexHeight) + 160;
                    coordinates.put(number, new Point(xCoordinate+3, yCoordinate-10));
                }
                
                g.drawString(Integer.toString(number), xCoordinate, yCoordinate);
                number++;     
        }
        
        yCoordinate = 742;
        xCoordinate = 463;
        g.drawString(Integer.toString(number), xCoordinate, yCoordinate);
        coordinates.put(number, new Point(xCoordinate+5, yCoordinate));

        xCoordinate+=50;
        number++;
        while(number<28)
        {
            g.drawString(Integer.toString(number), xCoordinate, yCoordinate);
            coordinates.put(number, new Point(xCoordinate+18, yCoordinate-2));
            xCoordinate+=40;
            number++;
            g.drawString(Integer.toString(number), xCoordinate, yCoordinate);
            coordinates.put(number, new Point(xCoordinate-6, yCoordinate));
            xCoordinate+=37;
            number++;
        }

        for (int row = 8; row >=0; row--) 
        {            
            if(row < 4)
            {
                xCoordinate = (arr[row] * hexWidth - ((row % 5) * hexWidth) / 2) + 483;
                yCoordinate =  (row * hexHeight) + 175;
                g.drawString(Integer.toString(number), xCoordinate, yCoordinate);
                coordinates.put(number, new Point(xCoordinate+12, yCoordinate-13));     
            }
            else if (row == 4 ) 
            {
                xCoordinate = (arr[row] * hexWidth - ((row % 5) * hexWidth) / 2) + 445;
                yCoordinate = (row * hexHeight) + 200;
                g.drawString(Integer.toString(number), xCoordinate, yCoordinate);
                coordinates.put(number, new Point(xCoordinate+10, yCoordinate+6));
            } 
            else 
            {
                xCoordinate = (arr[row] * hexWidth + ((row % 5) * hexWidth) / 2) + 330;
                yCoordinate = (row * hexHeight) + 200;
                g.drawString(Integer.toString(number), xCoordinate, yCoordinate);
                coordinates.put(number, new Point(xCoordinate+10, yCoordinate+6));
            }

            number++;

            yCoordinate = (row * hexHeight) + 150;
            if (row < 4) 
            {
                xCoordinate = (arr[row] * hexWidth - ((row % 5) * hexWidth) / 2) + 467;
                g.drawString(Integer.toString(number), xCoordinate, yCoordinate);
                coordinates.put(number, new Point(xCoordinate+22, yCoordinate));  
            } 
            else if(row ==4 )
            {
                xCoordinate = (arr[row] * hexWidth - ((row % 5) * hexWidth) / 2) + 465;
                g.drawString(Integer.toString(number), xCoordinate, yCoordinate);
                coordinates.put(number, new Point(xCoordinate+24, yCoordinate));    
            }
            else 
            {
                xCoordinate = (arr[row] * hexWidth + ((row % 5) * hexWidth) / 2) + 356;
                yCoordinate = (row * hexHeight) + 160;
                g.drawString(Integer.toString(number), xCoordinate, yCoordinate);
                coordinates.put(number, new Point(xCoordinate+18, yCoordinate-8));
            }

            number++;
        }

        yCoordinate = 105;
        xCoordinate = 827;
        g.drawString(Integer.toString(number), xCoordinate, yCoordinate);
        coordinates.put(number, new Point(xCoordinate+13, yCoordinate-10));
        xCoordinate-=50;
        number++;
        while(number<55)
        {
            g.drawString(Integer.toString(number), xCoordinate, yCoordinate);
            coordinates.put(number, new Point(xCoordinate, yCoordinate-13));
            xCoordinate-=35;
            number++;
            g.drawString(Integer.toString(number), xCoordinate, yCoordinate);
            coordinates.put(number, new Point(xCoordinate+20, yCoordinate-10));
            xCoordinate-=42;
            number++;
        }        
        //coordinates.entrySet().forEach(System.out::println);
    }


    // Calculating the angle for ray based on the user input number
    public int numberToAngle(int hexNumber)
    {         
        if(hexNumber<20)
        {
            if(hexNumber % 2 == 0)
            {
                return 0;
            }
            else if(hexNumber<10)
            {
                return 300;
            }
            else
            {
                return 60;
            }
        }
        else if(hexNumber<28)
        {
            if(hexNumber %2 == 0)
            {
                return 120;
            }
            else{
                return 60;
            }
        }
        else if(hexNumber<47)
        {
            if(hexNumber % 2 != 0)
            {
                return 180;
            }
            else if(hexNumber<37)
            {
                return 120;
            }
            else
            {
                return 240;
            }
        }
        else
        {
            if(hexNumber % 2 != 0)
            {
                return 300;
            }
            else
            {
                return 240;
            }
        }
    }

    // Retrieving coordinates for a given number
    public Point retrieveCoordinates(int key)
    {
        return coordinates.get(key);
    }
}

