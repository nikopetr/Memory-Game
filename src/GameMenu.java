import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/** Represents the GameMenu that sets up the players and the game type. Extends Screen.
 * Users select the game type that they want to play and the total Users or Bots that are going to be playing the game.
 * @author Nikolas Petrou
 * @version 2.0
 */
public abstract class GameMenu extends Screen
{
    private static final int STANDARD_MODE_PLAYER_LIMIT = 4;
    private static final int DUEL_MODE_PLAYER_LIMIT = 2;

    /** Represents the gameMode of the game.
     */
    private int gameMode;

    /** Represents the Panel that users select they total Users or Bots that are going to be playing the game.
     */
    private JPanel gamePlayersSelectionPanel;

    /** Represents the Panel providing users information about the game.
     */
    private JPanel gameInfoPanel;

    /** Shows the amount of players selected so far.
     */
    private JLabel totalPlayersLabel;

    /** ArrayList of Players that represents the Players of the game.
     */
    private ArrayList<Player> gamePlayers;

    /** JComboBox for the users to select the type of player they want to add to the game.
     */
    private JComboBox playerOptionsComboBox;

    /** The maximum players that can play in the specific mode.
     */
    private int playersLimit;

    /** Creates a GameMenu, and adding a User as a Player for the game
     * @param gameMode An integer representing the current game mode.
     */
    GameMenu(int gameMode)
    {
        super();
        this.gameMode = gameMode;
        if (gameMode == 1)
            playersLimit = STANDARD_MODE_PLAYER_LIMIT;
        else
            playersLimit = DUEL_MODE_PLAYER_LIMIT;

        gamePlayers = new ArrayList<>();
        User aNewUser = new User();
        aNewUser.setName("Player: " + (gamePlayers.size() + 1) + " (" + Gui.getMessages().getString("user") + ")");
        gamePlayers.add(aNewUser);

        GridLayout grid = new GridLayout(3, 0, 10, 20);
        getMainFrame().setLayout(grid);
    }

    /** Returns the the Players that are playing the game.
     * @return An ArrayList of Players representing the Players of the game.
     */
    ArrayList<Player> getGamePlayers()
    {
        return gamePlayers;
    }

    /** Initializes panel for player selection and for game information.
     * Adds the panels to the mainFrame.
     */
    void addRestGameMenuComponents()
    {
        initializeGamePlayersSelectionPanel();
        initializeGameInfoPanel();

        getMainFrame().add(gamePlayersSelectionPanel);
        getMainFrame().add(gameInfoPanel);
    }

    /**Pops up a MessageDialog that provides the users with information about the current game mode.
     */
    abstract void infoMessage();

    /** Initializes panel for player selection. Adds JComboBox to the panel with the available Player types, a JLabel with current players
     * and a reset JButton.
     */
    @SuppressWarnings("unchecked")
    private void initializeGamePlayersSelectionPanel()
    {
        gamePlayersSelectionPanel = new JPanel();
        gamePlayersSelectionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(Gui.getMessages().getString("addPlayers")));

        GridLayout grid = new GridLayout(2, 2, 10, 0);
        gamePlayersSelectionPanel.setLayout(grid);


        JButton resetPlayers = new JButton(Gui.getMessages().getString("resetPlayers"));
        resetPlayers.setFont(getMainFont());

        JPanel playerOptionsPanel = new JPanel();
        playerOptionsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder()));
        String[] playerOptionsStrings = {Gui.getMessages().getString("user"), Gui.getMessages().getString("goldfish"),
                Gui.getMessages().getString("kangaroo"), Gui.getMessages().getString("elephant")};
        playerOptionsComboBox = new JComboBox(playerOptionsStrings);

        playerOptionsComboBox.setFont(getMainFont());

        totalPlayersLabel = new JLabel(Gui.getMessages().getString("totalPlayers") + " " + gamePlayers.size());
        totalPlayersLabel.setFont(getMainFont());
        JLabel selectPlayerLabel = new JLabel(Gui.getMessages().getString("clickToAddPlayersMessage"));
        selectPlayerLabel.setFont(getMainFont());

        playerOptionsComboBox.addActionListener(e -> handlePlayerSelectionEvent(e));

        playerOptionsPanel.add(playerOptionsComboBox);

        resetPlayers.addActionListener(e -> handlePlayerSelectionEvent(e));

        gamePlayersSelectionPanel.add(selectPlayerLabel);
        gamePlayersSelectionPanel.add(totalPlayersLabel);
        gamePlayersSelectionPanel.add(playerOptionsComboBox);
        gamePlayersSelectionPanel.add(resetPlayers);
    }

    /** Handles the player selection panel JButtons and JComboBox ActionEvents.
     *  Adds the specified type of Player to the ArrayList gamePlayers.
     * @param e An ActionEvent, triggered by the JButtons and the JComboBox
     */
    private void handlePlayerSelectionEvent(ActionEvent e)
    {
        if (e.getActionCommand().equals(Gui.getMessages().getString("resetPlayers")))
        {
            gamePlayers.clear();
            User aNewUser = new User();
            aNewUser.setName("Player: " + (gamePlayers.size() + 1) + " (" + Gui.getMessages().getString("user") + ")");
            gamePlayers.add(aNewUser);
        }

        else if (gamePlayers.size() >= playersLimit )
        {
            JOptionPane.showMessageDialog(null, Gui.getMessages().getString("maxPlayerLimitMessage") + " " + playersLimit +".",
                    Gui.getMessages().getString("maxPlayersReachedMessage"), JOptionPane.ERROR_MESSAGE);
        }

        else
        {
            int playerType = playerOptionsComboBox.getSelectedIndex();
            if (playerType == 0)
            {
                User aNewUser = new User();
                aNewUser.setName("Player: " + (gamePlayers.size() + 1) + " (" + Gui.getMessages().getString("user") + ")");
                gamePlayers.add(aNewUser);
            }
            else
            {
                if (gameMode == 1)
                {
                    Bot aNewBot = new Bot(playerType);
                    aNewBot.setName("Player: " + (gamePlayers.size() + 1) + " (" + Gui.getMessages().getString("bot") + ")");
                    gamePlayers.add(aNewBot);
                }
                else
                {
                    DuelingBot aNewBot = new DuelingBot(playerType);
                    aNewBot.setName("Player: " + (gamePlayers.size() + 1) + " (" + Gui.getMessages().getString("bot") + ")");
                    gamePlayers.add(aNewBot);
                }
            }
        }

        totalPlayersLabel.setText(Gui.getMessages().getString("totalPlayers") +" "+ gamePlayers.size());
    }

    /** Initializes panel for the game information, adds a MessageDialog that provides the users with information about the game.
     */
    private void initializeGameInfoPanel() {
        gameInfoPanel = new JPanel();
        gameInfoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(Gui.getMessages().getString("gameInfo")));

        JButton gameInfoButton = new JButton(Gui.getMessages().getString("help"));
        gameInfoButton.setFont(getMainFont());
        gameInfoButton.setForeground(Color.BLUE);
        gameInfoButton.setPreferredSize(new Dimension(400, 150));
        gameInfoButton.addActionListener(e -> infoMessage());

        gameInfoPanel.add(gameInfoButton);
    }

}
