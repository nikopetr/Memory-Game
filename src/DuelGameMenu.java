import javax.swing.*;

/** Represents the DuelGameMenu that sets up the players and the game type for the Duel Mode. Extends GameMenu.
 * @author Nikolas Petrou
 * @version 2.0
 */
public class DuelGameMenu extends GameMenu
{
    /** Creates a DuelGameMenu by initializing the gameModeSelectionPanel, adding a start-game JButton and the rest components of the GameMenu after that.
     */
    DuelGameMenu()
    {
        super(2);

        JButton startGameButton = new JButton(Gui.getMessages().getString("startGame"));
        startGameButton.setFont( getMainFont());
        startGameButton.addActionListener(e -> {
            if (getGamePlayers().size() != 2)
                JOptionPane.showMessageDialog(null, Gui.getMessages().getString("duelMinPlayerLimitMessage"),
                        Gui.getMessages().getString("playerNotFoundMessage"), JOptionPane.ERROR_MESSAGE);
            else
                {
                    Gui.setCurrentScreen(new DuelGameScreen(getGamePlayers()));
                    getMainFrame().dispose();
                }
        });
        getMainFrame().add(startGameButton);
        addRestGameMenuComponents();

        getMainFrame().setVisible(true);
    }

    /**Pops up a MessageDialog that provides the users with the information and the rules of the Duel-game mode.
     */
    public void infoMessage() {
        JOptionPane.showMessageDialog(getMainFrame(), Gui.getMessages().getString("duelMenuInfo"),
                Gui.getMessages().getString("help"), JOptionPane.INFORMATION_MESSAGE);
    }
}
