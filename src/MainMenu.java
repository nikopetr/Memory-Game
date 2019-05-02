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

        JButton standardGameButton = new JButton(Gui.getMessages().getString("standard"));
        JButton duelGameButton = new JButton(Gui.getMessages().getString("duel"));
        JButton gameInfoButton = new JButton(Gui.getMessages().getString("help"));

        standardGameButton.setFont(getMainFont());
        duelGameButton.setFont(getMainFont());
        gameInfoButton.setFont(getMainFont());

        gameInfoButton.setForeground(Color.BLUE);
        gameInfoButton.setPreferredSize(new Dimension(650, 400));

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

        gameSelectionPanel.setLayout(grid);

        gameSelectionPanel.add(standardGameButton);
        gameSelectionPanel.add(duelGameButton);
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
