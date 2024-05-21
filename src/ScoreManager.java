import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.awt.Point;

public class ScoreManager
{
    // Calculates the total score based on correct guesses, the numbers list, and user guesses
    public int score(int correctGuesses, ArrayList<Integer> rayMarkerNumbers, ArrayList<Point> userGuessLocations )
    {
        int totalScore;
        int incorrectGuesses = userGuessLocations.size() - correctGuesses;
        Set<Integer> uniqueSet = new HashSet<>(rayMarkerNumbers); // To remove all dupicate ray markers
        if(userGuessLocations.isEmpty())
        {
            totalScore = uniqueSet.size();
        }
        else
        {
            totalScore = uniqueSet.size() + (5 * incorrectGuesses);
        } 
        return totalScore;
    }
    
}