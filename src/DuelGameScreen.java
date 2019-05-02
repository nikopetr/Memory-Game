import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/** Represents the DuelGameScreen that the Duel game-mode is being played.
 * @author Nikolas Petrou
 * @version 2.0
 */
public class DuelGameScreen extends Screen
{
    private static final int DUEL_GAMEMODE_GAMETYPE = 4;

    /** Represents the Memory-Game.
     */
    private MemoryGame game;

    /** Represents labels for the names and score of the Players.
     */
    private JLabel [] playersScoreLabel;

    /** ArrayList of CardButton [][], with the two different gameCardButtonsBoards representing the two boards with the cards of the game visually.
     */
    private ArrayList<CardButton [][]> gameCardButtonsBoards;

    /** Creates a StandardGameScreen to start the Duel memory-game for the given gameType and gamePlayers.
     * Adds a start-Jbutton that Users have to click in order to begin the game.
     * @param gamePlayers An ArrayList of Players representing the Duel memory-game players.
     */
    DuelGameScreen(ArrayList<Player> gamePlayers)
    {
        super();
        getMainFrame().setLayout(new GridLayout(1, 2));

        game = new MemoryGameDuel(gamePlayers);
        gameCardButtonsBoards = new ArrayList<>();
        playersScoreLabel = new JLabel[2];

        JButton startGameButton = new JButton(Gui.getMessages().getString("startButtonMessage"));
        startGameButton.setFont(getMainFont());
        startGameButton.addActionListener(e -> {
            getMainFrame().remove(startGameButton);
            initializePanels(gamePlayers);
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

            updateCardPanels();

            game.update();
            updatePlayersPanel();
            pickMessage();
            updateCardPanels();

                if (game.isGameFinished())
                {
                    Gui.setCurrentScreen(new GameEndingScreen(DUEL_GAMEMODE_GAMETYPE, game.getGameWinner()));
                    getMainFrame().dispose();
                    return;
                }

        }while(game.getCurrentPlayer() instanceof Bot);
    }

    /** Initializes the panels for the two players.
     * Creates for each player: an information panel that contains the name and the score of the player and a panel containing a grid of the JButtons for the cards of the board that the Player is playing.
     * After that the two panels are joined together to another one and after that, both of those two panels (one for each player) is added to the mainFrame.
     * @param gamePlayers, An ArrayList of Players representing the players of the game.
     */
    private void initializePanels(ArrayList<Player> gamePlayers)
    {
        JPanel playerOnePanel = new JPanel();

        JPanel playerOneButtonsPanel = new JPanel();
        playerOneButtonsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(Gui.getMessages().getString("player1Board")));
        playerOneButtonsPanel.setLayout(new GridLayout(game.getGameBoard().getRows(), game.getGameBoard().getCol()));
        playerOneButtonsPanel.setPreferredSize(new Dimension(950, 970));

        CardButton [][] gameCardButtonsBoard1 = new CardButton[game.getGameBoard().getRows()][game.getGameBoard().getCol()];

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

                gameCardButtonsBoard1[i][j] = cardButton;
                playerOneButtonsPanel.add(gameCardButtonsBoard1[i][j].getCardButton());
            }
        gameCardButtonsBoards.add(gameCardButtonsBoard1);

        JLabel playerOneLabel = new JLabel(gamePlayers.get(0).getName());
        playerOneLabel.setFont(getMainFont());
        JLabel playerOneScoreLabel = new JLabel(Gui.getMessages().getString("score") + " " + 0);
        playerOneScoreLabel.setFont(getMainFont());

        playersScoreLabel[0] = playerOneScoreLabel;
        JPanel playerOneInfoPanel = new JPanel();
        playerOneInfoPanel.setLayout(new GridLayout(1, 2, 50, 10));
        playerOneInfoPanel.add(playerOneLabel);
        playerOneInfoPanel.add(playerOneScoreLabel);

        playerOnePanel.add(playerOneInfoPanel);
        playerOnePanel.add(playerOneButtonsPanel);

        getMainFrame().add(playerOnePanel);

        JPanel playerTwoPanel = new JPanel();

        JPanel playerTwoButtonsPanel = new JPanel();
        playerTwoButtonsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(Gui.getMessages().getString("player2Board")));
        playerTwoButtonsPanel.setLayout(new GridLayout(game.getGameBoard().getRows(), game.getGameBoard().getCol()));
        playerTwoButtonsPanel.setPreferredSize(new Dimension(950, 970));

        CardButton [][] gameCardButtonsBoard2 = new CardButton[game.getGameBoard().getRows()][game.getGameBoard().getCol()];

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

                gameCardButtonsBoard2[i][j] = cardButton;
                playerTwoButtonsPanel.add(gameCardButtonsBoard2[i][j].getCardButton());
            }
        gameCardButtonsBoards.add(gameCardButtonsBoard2);
        updateCardPanels();

        JLabel playerTwoLabel = new JLabel(gamePlayers.get(1).getName());
        playerTwoLabel.setFont(getMainFont());
        JLabel playerTwoScoreLabel = new JLabel(Gui.getMessages().getString("score")+ " " + 0);
        playerTwoScoreLabel.setFont(getMainFont());

        playersScoreLabel[1] = playerTwoScoreLabel;
        JPanel playerTwoInfoPanel = new JPanel();
        playerTwoInfoPanel.setLayout(new GridLayout(1, 2, 50, 10));
        playerTwoInfoPanel.add(playerTwoLabel);
        playerTwoInfoPanel.add(playerTwoScoreLabel);

        playerTwoPanel.add(playerTwoInfoPanel);
        playerTwoPanel.add(playerTwoButtonsPanel);

        getMainFrame().add(playerTwoPanel);
    }

    /** Updates the gameCardButtons with the current card icons based on which of them are flipped or taken.
     * Also enabling the JButtons of the current Player and disabling the JButtons of the other one.
     */
    private void updateCardPanels()
    {
        DuelingBoard gameBoard = (DuelingBoard) game.getGameBoard();

        for (int i = 0; i < game.getGameBoard().getRows(); i++)
            for (int j = 0; j < game.getGameBoard().getCol(); j++)
            {
                gameCardButtonsBoards.get(0)[i][j].getCardButton().setIcon((gameBoard.getCardIconFromBoard(gameCardButtonsBoards.get(0)[i][j].getCardPos()[0], // For player 1 cards
                        gameCardButtonsBoards.get(0)[i][j].getCardPos()[1])));

                gameCardButtonsBoards.get(1)[i][j].getCardButton().setIcon((gameBoard.getCardIconFromBoard2(gameCardButtonsBoards.get(1)[i][j].getCardPos()[0],// For player 2 cards
                        gameCardButtonsBoards.get(1)[i][j].getCardPos()[1])));

                gameCardButtonsBoards.get(game.getCurrentPlayerIndex())[i][j].getCardButton().setEnabled(true);
                gameCardButtonsBoards.get((game.getCurrentPlayerIndex() + 1) % 2)[i][j].getCardButton().setEnabled(false);

            }
    }

    /** Updates the playersScoreLabel to match with the new scores.
     */
    private void updatePlayersPanel()
    {
        playersScoreLabel[game.getCurrentPlayerIndex()].setText(Gui.getMessages().getString("score")+ " " + game.getCurrentPlayer().getTotalPairsFound());
    }

    /**Pops up a MessageDialog that provides the users with information on who Player is playing next after a card pick is done.
     */
    private void pickMessage() {
        JOptionPane.showMessageDialog(getMainFrame(),  game.getCurrentPlayer().getName() + " " + Gui.getMessages().getString("playingNext"),
                Gui.getMessages().getString("pickDone"), JOptionPane.INFORMATION_MESSAGE);
    }
}
