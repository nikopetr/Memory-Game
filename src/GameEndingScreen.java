import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.ListIterator;

/** Represents the GameEndingScreen that appears after a game is finished.
 * Displays the results of the game and the high score of the specific type of game.
 * @author Nikolas Petrou
 * @version 2.0
 */
public class GameEndingScreen extends Screen
{
    /** Represents the JPanel with the high-scores.
     */
    private JPanel highScorePanel;

    /** Represents a JButton for saving the game's winner name by his score.
     */
    private JButton saveScoreButton;

    /** For loading the high-scores and for saving the results.
     */
    private StatsFileSave gameStatsFiles;

    /** Creates the GameEndingScreen
     * @param gameType An integer representing the type of the game.
     * @param gameWinner A Player representing the winner of the game.
     */
    GameEndingScreen(int gameType, Player gameWinner)
    {
        super();
        getMainFrame().setLayout(new GridLayout(3,1));
        gameStatsFiles = new StatsFileSave(gameType);
        initializeWinningPlayerLabel(gameWinner);
        initializeHighScoresPanel(gameWinner);
        getMainFrame().setVisible(true);
    }

    /** Initializes the winningPlayerLabel based on the given gameWinner.
     * If gameWinner is null then then label gets "No winner" message otherwise it gets the winning Player's name and its totalCardPicks
     * @param gameWinner A Player representing the winner of the game.
     */
    private void initializeWinningPlayerLabel(Player gameWinner)
    {
        JLabel winningPlayerLabel = new JLabel();
        winningPlayerLabel.setFont(getMainFont());
        winningPlayerLabel.setForeground(Color.BLUE);

        if (gameWinner == null)
            winningPlayerLabel.setText(Gui.getMessages().getString("noWinnerMessage"));

        else
            winningPlayerLabel.setText(Gui.getMessages().getString("winner")+ ": "+ gameWinner.getName() + " " + Gui.getMessages().getString("totalCardPicksMessage") + ": " + gameWinner.getTotalCardPicks() + "!!!");

        getMainFrame().add(winningPlayerLabel);
    }

    /** Initializes the highScorePanel. Adds highScorePanel to the mainFrame.
     * Creates saveScoreButton JButton for saving the game's winner score and wins.
     * If there is a User winner and the JButton is pressed then the Name given by the user will be saved in the high-scores.
     * Otherwise the saveScoreButton  gets disabled.
     * @param gameWinner A Player representing the winner of the game.
     */
    private void initializeHighScoresPanel(Player gameWinner)
    {
        highScorePanel = new JPanel();
        highScorePanel.setBorder(BorderFactory.createTitledBorder(Gui.getMessages().getString("highScoresMessage")));
        highScorePanel.setLayout(new GridLayout(10, 2));

        updateHighScoresPanel();

        saveScoreButton = new JButton(Gui.getMessages().getString("saveScore"));
        saveScoreButton.setFont(getMainFont());

        if (!(gameWinner instanceof User))
            disableSaveScoreButton();

        saveScoreButton.addActionListener(e -> {
            String winnerName;
            do {
                winnerName = JOptionPane.showInputDialog(Gui.getMessages().getString("giveNameMessage"));
            }while (winnerName.equals("")); // User can't give empty string for name

           if (gameWinner instanceof User)
           {
               gameWinner.setName(winnerName);
               gameStatsFiles.saveUserStats((User)gameWinner);
           }

            updateHighScoresPanel();
            highScorePanel.updateUI();

            disableSaveScoreButton();
        });

        JButton playAgainButton = new JButton(Gui.getMessages().getString("playAgain"));
        playAgainButton.setFont(getMainFont());
        playAgainButton.addActionListener(e -> {
            Gui.setCurrentScreen(new MainMenu());
            getMainFrame().dispose();
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(2,1));
        buttonsPanel.add(saveScoreButton);
        buttonsPanel.add(playAgainButton);

        getMainFrame().add(highScorePanel);
        getMainFrame().add(buttonsPanel);
    }

    /** Updates highScorePanel by removing everything from it and then adding every Player found from gameStatsFiles.getTopUsers().
     * If there are no Players in gameStatsFiles.getTopUsers() then highScorePanel gets a JLabel with "no data found" message.
     */
    private void updateHighScoresPanel()
    {
        highScorePanel.removeAll();

        ArrayList<User> topUsers = gameStatsFiles.getTopUsers();

        if(topUsers.size() != 0)
        {
            // ListIterator to traverse the list
            ListIterator iterator = topUsers.listIterator();
            while (iterator.hasNext())
            {
                highScorePanel.add(new JLabel((iterator.nextIndex() + 1) + ") " + iterator.next().toString()));
            }
        }
        else
            highScorePanel.add(new JLabel(Gui.getMessages().getString("noDataFound")));
    }

    /** Disables the saveScoreButton and sets its text as thanks.
     */
    private void disableSaveScoreButton()
    {
        saveScoreButton.setText(Gui.getMessages().getString("thanks"));
        saveScoreButton.setEnabled(false);
    }
}
