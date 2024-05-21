import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartPage extends JFrame 
{
    public StartPage() 
    {
        super("BlackBox Plus");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 800);

        // Adding the start page main Panel 
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.decode("#383838"));

        // Adding the title of the game
        JLabel titleLabel = new JLabel("BlackBox Plus");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 90));
        titleLabel.setForeground(Color.decode("#C0C0C0"));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(15)); 
        
        // Load and display the game preview image
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\agamp\\OneDrive\\Desktop\\BlackboxPlus\\1.png"); 
        Image image = imageIcon.getImage(); 
        Image newimg = image.getScaledInstance(230, 200,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);  
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(imageLabel);
        panel.add(Box.createVerticalStrut(20)); 

        // Adding Start Button
        JButton newGameButton = new JButton("Start Game");
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGameButton.setFont(new Font("Arial", Font.PLAIN, 40)); 
        newGameButton.setBackground(Color.decode("#007FFF"));  
        newGameButton.setForeground(Color.decode("#FFFFFF"));
        newGameButton.setPreferredSize(new Dimension(500, 100)); 
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new HexLayout(); 
                    }
                });
                dispose();
            }
        });
        panel.add(newGameButton);
        panel.add(Box.createVerticalStrut(30));

        // Adding Instruction Button
        JButton instructionsButton = new JButton("Instructions");
        instructionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionsButton.setBackground(Color.decode("#007FFF"));  
        instructionsButton.setForeground(Color.decode("#FFFFFF"));
        instructionsButton.setFont(new Font("Arial", Font.PLAIN, 40));
        instructionsButton.setPreferredSize(new Dimension(400, 100)); 
        instructionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(StartPage.this,
                    "Game instructions:\n\n" +
                    "Objective: Deduce the positions of 6 hidden atoms on a hexagonal grid with least score.\n\n" +
                    "1. Enter a number between 1-54 in the text field to start the rays at that number.\n" +
                    "2. Observe where rays exit the grid; no marker indicates a direct hit.\n" +
                    "3. Click 'Place Atoms' to guess the atom positions after several observations.\n" +
                    "4. Click on any suspected hexagons to place your guesses; you have 6 total guesses.\n" +
                    "5. Scoring: Each ray used adds +1 and each wrong guess +5.\n" +
                    "6. The game ends when you've placed all guesses, displaying final positions and scores.\n\n" +
                    "Strategy Tips:\n" +
                    "- Pay close attention to where rays exit the grid to infer possible atom positions.\n" +
                    "- Use a minimal number of rays to maximize your score. Each unused ray can save points and make your score higher.\n\n" +
                    "Enjoy and sharpen your deductive skills!", 
                    "Instructions", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        panel.add(instructionsButton);
        panel.add(Box.createVerticalStrut(30));

        // Adding Exit Button
        JButton exitButton = new JButton("Exit");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setBackground(Color.decode("#007FFF"));  
        exitButton.setForeground(Color.decode("#FFFFFF"));
        exitButton.setFont(new Font("Arial", Font.PLAIN, 40)); 
        exitButton.setPreferredSize(new Dimension(400, 100)); 
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(StartPage.this, "Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        panel.add(exitButton);

        add(panel);
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StartPage();
                
            }
        });
    }
}
