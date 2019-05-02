import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/** Represents the StandardGameMenu that sets up the players and the game type for the Standard Mode. Extends GameMenu.
 * @author Nikolas Petrou
 * @version 2.0
 */

public class StandardGameMenu extends GameMenu
{
    /** Represents the Panel for the game type selection.
     */
    private JPanel gameModeSelectionPanel;

    /** Creates a StandardGameMenu by initializing the gameModeSelectionPanel and adding the rest components of the GameMenu after that.
     */
    StandardGameMenu()
    {
        super(1);

        initializeGameModeSelectionPanel();
        getMainFrame().add(gameModeSelectionPanel);
        addRestGameMenuComponents();

        getMainFrame().setVisible(true);
    }

    /**Pops up a MessageDialog that provides the users with the information and the rules of the Standard game mode and the types of games.
     */
    public void infoMessage() {
        JOptionPane.showMessageDialog(getMainFrame(), Gui.getMessages().getString("availableGameTypesMessage"),
                Gui.getMessages().getString("help"), JOptionPane.INFORMATION_MESSAGE);
    }

    /** Initializes panel for the game type selection. Adds JButtons and their Action Listeners for the available game types.
     */
    private void initializeGameModeSelectionPanel()
    {
        gameModeSelectionPanel = new JPanel();
        gameModeSelectionPanel.setBorder(BorderFactory.createTitledBorder(Gui.getMessages().getString("modeSelectionMessage")));

        gameModeSelectionPanel.setLayout(new GridLayout(3, 0, 10, 20));

        JButton basicModeButton = new JButton(Gui.getMessages().getString("basicMode"));
        JButton doubleModeButton = new JButton(Gui.getMessages().getString("doubleMode"));
        JButton tripleModeButton = new JButton(Gui.getMessages().getString("tripleMode"));

        Font myFont = new Font(("Arial"), Font.PLAIN, 40);

        basicModeButton.setFont(myFont);
        doubleModeButton.setFont(myFont);
        tripleModeButton.setFont(myFont);

        basicModeButton.addActionListener(e -> handleGameModeSelectionEvent(e));

        doubleModeButton.addActionListener(e -> handleGameModeSelectionEvent(e));

        tripleModeButton.addActionListener(e -> handleGameModeSelectionEvent(e));

        gameModeSelectionPanel.add(basicModeButton);
        gameModeSelectionPanel.add(doubleModeButton);
        gameModeSelectionPanel.add(tripleModeButton);
    }

    /** Handles the gameMode selection panel JButtons ActionEvents. Creates the new game Screen based ont he Action event occurred.
     * @param e An ActionEvent, triggered by the JButtons.
     */
    private void handleGameModeSelectionEvent(ActionEvent e)
    {
        if (e.getActionCommand().equals(Gui.getMessages().getString("basicMode")))
        {
            Gui.setCurrentScreen(new StandardGameScreen(1, getGamePlayers()));
            getMainFrame().dispose();
        }
        else if (e.getActionCommand().equals(Gui.getMessages().getString("doubleMode")))
        {
            Gui.setCurrentScreen(new StandardGameScreen(2, getGamePlayers()));
            getMainFrame().dispose();
        }
        else if (e.getActionCommand().equals(Gui.getMessages().getString("tripleMode")))
        {
            Gui.setCurrentScreen(new StandardGameScreen(3, getGamePlayers()));
            getMainFrame().dispose();
        }
    }
}
