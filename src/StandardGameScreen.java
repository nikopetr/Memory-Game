import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/** Represents the StandardGameScreen that the standard game-mode is being played.
 * @author Nikolas Petrou
 * @version 2.0
 */
public class StandardGameScreen extends Screen
{
    /** Represents the Memory-Game
     */
    private MemoryGame game;

    /** Represents the game-type.
     */
    private int gameType;

    /** Represents the cards of the game visually.
     */
    private CardButton [][] gameCardButtons;

    /** Represents labels for the names and score of the Players
     */
    private JLabel [] playersScoreLabel;

    /** Represents label of the Player that plays for this turn.
     */
    private JLabel currentPlayerLabel;

    /** Creates a StandardGameScreen to start the Standard memory-game for the given gameType and gamePlayers.
     * Adds a start-Jbutton that Users have to click in order to begin the game.
     * @param gameType An integer representing the game-type of the Standard memory-game
     * @param gamePlayers An ArrayList of Players representing the standard memory-game players.
     */
    StandardGameScreen(int gameType, ArrayList<Player> gamePlayers)
    {
        super();
        getMainFrame().setLayout(new GridLayout(1, 2));

        this.gameType = gameType;
        game = new MemoryGameStandard(gameType, gamePlayers);

        JButton startGameButton = new JButton(Gui.getMessages().getString("startButtonMessage"));
        startGameButton.setFont(getMainFont());
        startGameButton.addActionListener(e -> {
            getMainFrame().remove(startGameButton);
            initializeCardPanel();
            initializeScorePanel(gamePlayers);
            getMainFrame().setVisible(true);
        });
        getMainFrame().add(startGameButton);
    }

    /** Begins the pick of a new pair of cards for the next Player, updates the game, the Players panel and the gameCardButtons.
     * When the game is finished sets the the current Screen to a GameEndingScreen.
     */
    private void nextTurn()
    {
        do {
            if (game.getCurrentPlayer() instanceof Bot)
                game.botTurn();

            updateCardPanel();

            if(game.update())
            {
                pickMessage();
                updatePlayersPanel();
                updateCardPanel();

                if (game.isGameFinished())
                {
                    Gui.setCurrentScreen(new GameEndingScreen(gameType, game.getGameWinner()));
                    getMainFrame().dispose();
                    return;
                }
            }
        }while(game.getCurrentPlayer() instanceof Bot);
    }

    /** Initializes panel for the gameCardButtons. Creates new gameCardButtons and adding new ActionListeners for them.
     *  Adds the gameCardButtons on cardPanel and then adding the cardPanel on the mainFrame.
     */
    private void initializeCardPanel()
    {
        JPanel cardPanel = new JPanel();
        cardPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(Gui.getMessages().getString("gameBoard")));
        cardPanel.setLayout(new GridLayout(game.getGameBoard().getRows(), game.getGameBoard().getCol()));

        gameCardButtons = new CardButton[game.getGameBoard().getRows()][game.getGameBoard().getCol()];

        for (int i = 0; i < game.getGameBoard().getRows(); i++)
            for (int j = 0; j < game.getGameBoard().getCol(); j++)
            {
                CardButton cardButton = new CardButton(i,j);
                cardButton.getCardButton().addActionListener(e -> {
                    {
                        if(game.flipCard(cardButton.getCardPos()))
                            nextTurn();
                    }
                });

                gameCardButtons[i][j] = cardButton;
                cardPanel.add(gameCardButtons[i][j].getCardButton());
            }
        updateCardPanel();
        getMainFrame().add(cardPanel);
    }

    /** Initializes panel for the score labels and the currentPlayerLabel. Creates new labels for the players' names and scores.
     *  Adds the scorePanel to the mainFrame.
     *  @param gamePlayers An ArrayList of Players, representing the Players of the game.
     */
    private void initializeScorePanel( ArrayList<Player> gamePlayers)
    {
        JPanel scorePanel = new JPanel(new GridLayout(gamePlayers.size() + 1, 1));

        currentPlayerLabel = new JLabel(Gui.getMessages().getString("currentPlayer") + " " + game.getCurrentPlayer().getName());
        currentPlayerLabel.setFont(getMainFont());
        scorePanel.add(currentPlayerLabel);

        JPanel [] playersScores = new JPanel[gamePlayers.size()];
        playersScoreLabel = new JLabel[gamePlayers.size()];

        for (int i = 0; i < playersScores.length; i++)
        {
            JLabel playerLabel = new JLabel(gamePlayers.get(i).getName());
            playerLabel.setFont(getMainFont());
            playersScoreLabel[i] = new JLabel(Gui.getMessages().getString("score")+ " " + 0);
            playersScoreLabel[i].setFont(getMainFont());
            playersScores[i] = new JPanel();
            playersScores[i].add(playerLabel);
            playersScores[i].add(playersScoreLabel[i]);
            scorePanel.add(playersScores[i]);
        }
        getMainFrame().add(scorePanel);
    }

    /** Updates the playersScoreLabels and the currentPlayerLabel to match with the new scores and current Player.
     */
    private void updatePlayersPanel()
    {
        playersScoreLabel[game.getCurrentPlayerIndex()].setText(Gui.getMessages().getString("score") + " " + game.getCurrentPlayer().getTotalPairsFound());
        currentPlayerLabel.setText(Gui.getMessages().getString("currentPlayer") + " " + game.getCurrentPlayer().getName());
    }

    /** Updates the gameCardButtons with the current card icons based on which of them are flipped or taken.
     */
    private void updateCardPanel()
    {
        for (int i = 0; i < game.getGameBoard().getRows(); i++)
            for (int j = 0; j < game.getGameBoard().getCol(); j++)
                gameCardButtons[i][j].getCardButton().setIcon((game.getGameBoard().getCardIconFromBoard(gameCardButtons[i][j].getCardPos()[0],gameCardButtons[i][j].getCardPos()[1])));
    }

    /**Pops up a MessageDialog that provides the users with information on who Player is playing next after a card pick is done.
     */
    private void pickMessage() {
        JOptionPane.showMessageDialog(getMainFrame(),  game.getCurrentPlayer().getName() + " " + Gui.getMessages().getString("playingNext"),
                Gui.getMessages().getString("pickDone"), JOptionPane.INFORMATION_MESSAGE);
    }
}