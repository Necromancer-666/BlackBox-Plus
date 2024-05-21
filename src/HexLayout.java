import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class HexLayout extends JFrame 
{
    private HexagonPanel hexagonPanel;
    private JTextField textField;
    private static JLabel scoreLabel;
    public static ArrayList<Integer> rayMarkerNumbers;

    public HexLayout() 
    {
        super("Hexagon Drawer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
        setLayout(null);
        setSize(1500, 850);
        getContentPane().setBackground(Color.decode("#383838")); 

        // Initialize HexagonPanel and set its background color to grey
        hexagonPanel = new HexagonPanel();
        hexagonPanel.setBackground(Color.decode("#383838"));
        hexagonPanel.setBounds(0, 0, 1200, 800);
        add(hexagonPanel);

        // Initialize rayMarkerNumbers
        rayMarkerNumbers = new ArrayList<>();

        // Setup UI components
        setupUIComponents();
        
        setVisible(true);
    }

    // Additional components of the game
    private void setupUIComponents() 
    {
        // Panel for score display
        JPanel scorePanel = new JPanel();
        scorePanel.setBounds(1200, 150, 270, 45); 
        scorePanel.setBackground(Color.decode("#383838")); 

        // Score label initialization
        scoreLabel = new JLabel("Score : 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 40));
        scoreLabel.setForeground(Color.WHITE);
        scorePanel.add(scoreLabel);
        add(scorePanel);

        // Panel for title and text field
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#383838"));
        panel.setBounds(1200, 270, 270, 30); 
        JLabel titleLabel = new JLabel("Enter a number between 1-54");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        panel.add(titleLabel);
        add(panel);
    
        // Text field for user input
        textField = new JTextField("");
        textField.setBounds(1210, 300, 250, 40);  
        textField.setMargin(new Insets(10, 10, 10, 10));
        textField.setBackground(Color.decode("#2C2C2C"));
        textField.setForeground(Color.decode("#FFFFFF"));
        textField.setFont(new Font("Arial", Font.BOLD, 18));
        add(textField);
    
        // Button to place guess atoms
        JButton endGameButton = new JButton("Place Atoms");
        endGameButton.setBackground(Color.decode("#007BFF"));
        endGameButton.setForeground(Color.decode("#FFFFFF"));
        endGameButton.setFont(new Font("Arial", Font.BOLD, 18));
        endGameButton.setBounds(1210, 400, 250, 40);  
        add(endGameButton);

        // Button to go back to start page
        JButton mainMenuButton = new JButton("Go to Main Menu");
        mainMenuButton.setBackground(Color.decode("#007BFF"));
        mainMenuButton.setForeground(Color.decode("#FFFFFF"));
        mainMenuButton.setFont(new Font("Arial", Font.BOLD, 18));
        mainMenuButton.setBounds(1210, 500, 250, 40);  
        add(mainMenuButton);
        
        // Action listeners
        textField.addActionListener(e -> {
            String inputText = textField.getText();
            try {
                int number = Integer.parseInt(inputText);
                if (number <= 0 || number > 54) {
                    throw new NumberFormatException();
                }
                rayMarkerNumbers.add(number);
                textField.setText("");
                hexagonPanel.addNumberAndCalculateAngle(number);
                printRayMarkerNumbers();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });
   
        endGameButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                hexagonPanel.toggleEndGame(); // Toggling endGame 
                hexagonPanel.repaint(); 
            });
        });

        mainMenuButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                StartPage startPage = new StartPage(); 
                startPage.setVisible(true); 
            });
            dispose(); 
        });       
    }   

    // Method to update the score
    public static void updateScore(int currentScore) 
    {
        scoreLabel.setText("Score : " + currentScore);
    }
    
    // Method to print ray markers
    private static void printRayMarkerNumbers() 
    {
        System.out.println("Order in which rays are Entered : "+rayMarkerNumbers);
    }

    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> new HexLayout().setVisible(true));
    }
}
