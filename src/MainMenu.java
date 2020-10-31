import javax.management.StandardEmitterMBean;
import javax.swing.*;
import java.awt.*;

/** Represents the Main Menu of the game for the users.
 * Users select the game mode that they want to play or select to get help for extra information.
 * @author Nikolas Petrou
 * @version 2.0
 */
public class MainMenu extends Screen
{
    /** Creates a MainMenu, with the JButtons of the two game modes and a help JButton.
     */
    MainMenu() {
        super();
        
        JPanel gameSelectionPanel = new JPanel();
        JPanel gameInfoPanel = new JPanel();

        gameSelectionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(Gui.getMessages().getString("gameSelection")));
        gameInfoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(Gui.getMessages().getString("gameInfo")));

        //Construct new Components
        JLabel title = new JLabel("Memory Game");
        JButton standardGameButton = new JButton("Standard");
        JButton duelGameButton = new JButton("Duel");
        JButton gameInfoButton = new JButton("Help");
        //End

        //Set properties of new Components
        standardGameButton.setBackground(new Color(59, 89, 182));
        standardGameButton.setForeground(Color.WHITE);
        standardGameButton.setFocusPainted(false);
        standardGameButton.setFont(new Font("Tahoma", Font.BOLD, 6));

        duelGameButton.setBackground(new Color(59, 89, 182));
        duelGameButton.setForeground(Color.WHITE);
        duelGameButton.setFocusPainted(false);
        duelGameButton.setFont(new Font("Tahoma", Font.BOLD, 6));

        gameInfoButton.setBackground(new Color(59, 89, 182));
        gameInfoButton.setForeground(Color.WHITE);
        gameInfoButton.setFocusPainted(false);
        gameInfoButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        //End

        
        title.setText("<html><font color=black size=45><b>Memory Game</b></html>");

        //Add Bounds
        title.setBounds (575, 45, 500, 70);
        standardGameButton.setBounds (385, 200, 255, 60);
        standardGameButton.setPreferredSize(new Dimension(255, 60));
        duelGameButton.setBounds (770, 200, 255, 60);
        duelGameButton.setPreferredSize(new Dimension(255, 60));
        gameInfoButton.setBounds(560, 900, 255, 60);

        standardGameButton.setFont(getMainFont());
        duelGameButton.setFont(getMainFont());
        gameInfoButton.setFont(getMainFont());

        gameInfoButton.setForeground(Color.WHITE);
        gameInfoButton.setPreferredSize(new Dimension(255, 60));

        gameInfoButton.addActionListener(e -> infoMessage());

        standardGameButton.addActionListener(e -> {
            getMainFrame().dispose();
            Gui.setCurrentScreen(new StandardGameMenu());
        });

        duelGameButton.addActionListener(e -> {
            getMainFrame().dispose();
            Gui.setCurrentScreen(new DuelGameMenu());
        });

        GridLayout grid = new GridLayout(2, 0, 10, 20);

        //Added Absolute Positioning
        gameSelectionPanel.setLayout(null);

        gameSelectionPanel.add(standardGameButton);
        gameSelectionPanel.add(duelGameButton);
        gameSelectionPanel.add(title);
        gameInfoPanel.add(gameInfoButton);

        getMainFrame().setLayout(grid);
        getMainFrame().add(gameSelectionPanel);
        getMainFrame().add(gameInfoPanel);
        getMainFrame().setVisible(true);
    }

    /** Pops up a MessageDialog that provides the users with information about the game.
     */
    private void infoMessage() {
        JOptionPane.showMessageDialog(getMainFrame(), Gui.getMessages().getString("mainMenuInfo"),
                Gui.getMessages().getString("help"), JOptionPane.INFORMATION_MESSAGE);
    }
}
