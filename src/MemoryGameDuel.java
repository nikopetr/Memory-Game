import java.util.ArrayList;
/** Implements a Memory Game Duel. This mode is strictly played by two players, one of which could be a bot.
 * A player starts by opening his own card, and the other player answers by opening his own card. If they are the same,
 * the second player wins. Then the player who plays first changes. Every time that a player turns over a card the board
 * of the game is updated.
 * @author Nikolas Petrou
 * @version 2.0
 */
public class MemoryGameDuel implements MemoryGame
{
    private static final int DUEL_GAMEMODE_COPIES = 2;
    private static final int DUEL_GAMEMODE_CARDS = 12;

    /** Represents the current player's picks for this turn.
     */
    private int currentTurnPicks;

    /** Represents the index of the Player who is currently playing.
     */
    private int currentPlayerIndex;

    /** Represents Board of the dueling board of the game.
     */
    private DuelingBoard gameBoard;

    /** An ArrayList of GameSet representing the game's players and their available card picks.
      */
    private ArrayList <GameSet> gameSet;

    /** Creates a Memory Game duel with the specified gamePlayers.
     * Calls gameBoard's constructor for the current game-duel and sets the copies for the cards.
     * Filling the availableCardPicks of each player with all card's coordinates of the duel board.
     * @param gamePlayers An ArrayList of Players with the Players for the game.
     */
    public MemoryGameDuel(ArrayList<Player> gamePlayers)
    {
        gameBoard = new DuelingBoard();
        ArrayList<int[]> availableCardPicks1 = new ArrayList<>();
        ArrayList<int[]> availableCardPicks2 = new ArrayList<>();

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 6; j++)
            {
                int[] aCardPos = {i, j};
                availableCardPicks1.add(aCardPos);
                availableCardPicks2.add(aCardPos);
            }

        Bot.setCopies(DUEL_GAMEMODE_COPIES);
        currentTurnPicks = 0;
        currentPlayerIndex = 0;

        if (gamePlayers.size() != 2)
            throw new ArithmeticException("Duel-mode needs to have two players to be played.");

        GameSet aGameSet1 = new GameSet(availableCardPicks1, gamePlayers.get(0));
        GameSet aGameSet2 = new GameSet(availableCardPicks2, gamePlayers.get(1));

        gameSet = new ArrayList<>();
        gameSet.add(aGameSet1);
        gameSet.add(aGameSet2);
    }

    /** Returns the game’s current player's index.
     * @return An integer representing the index of the Player who is currently playing.
     */
    public int getCurrentPlayerIndex()
    {
        return currentPlayerIndex;
    }

    /** Returns the game’s board.
     * @return A Board representing the game’s board.
     */
    public Board getGameBoard()
    {
        return gameBoard;
    }

    /** Returns the game’s current player.
     * @return A Player representing the Player who is currently playing.
     */
    public Player getCurrentPlayer(){ return gameSet.get(currentPlayerIndex).getGamePlayer();}

    /** Returns the coordinates of cards that are available for flip.
     * @return An ArrayList of int[] representing the x and y coordinates of the cards that are available for flip.
     */
    public ArrayList<int []> getAvailableCardPicks()
    {
        return gameSet.get(currentPlayerIndex).getAvailableCardPicks();
    }

    /** Returns if the game if finished.
     * @return A boolean representing if the game if finished.
     */
    public boolean isGameFinished()
    {
        return (gameBoard.getCardsFound() == DUEL_GAMEMODE_CARDS * DUEL_GAMEMODE_COPIES * 2);
    }


    /** Returns the game’s winner player.
     * If there are is more than one Player with the most cards found returns null.
     * @return A Player representing the Player who won at that game.
     */
    public Player getGameWinner()
    {
        int mostCardsFound = -1;
        Player winner = null;

        for (GameSet set : gameSet)
        {
            if (set.getGamePlayer().getTotalPairsFound() ==  mostCardsFound)
                return null;
            else if (set.getGamePlayer().getTotalPairsFound() > mostCardsFound)
            {
                mostCardsFound =set.getGamePlayer().getTotalPairsFound();
                winner = set.getGamePlayer();
            }
        }

        return winner;
    }

    /** Returns if the card at the given cardSelected coordinates of the current player is available for pick.
     * @param cardSelected An array representing the x and y coordinates of the selected card.
     * @return A boolean representing if the card at the given coordinates is available for pick.
     */
    private boolean isAvailable (int [] cardSelected)
    {
        for (int[] cardPos : gameSet.get(currentPlayerIndex).getAvailableCardPicks())
            if (cardPos[0] == cardSelected[0] && cardPos[1] == cardSelected[1])
                return true;

        return false;
    }

    /** Flips the given card of the current player that plays only if it's available and in board's range.
     * If the flip is done, updates all the bots' boardMap.
     * @param cardPos An array representing the x and y coordinates of the selected card.
     * @return A Boolean representing if the specified card is flipped.
     */
    public boolean flipCard(int [] cardPos)
    {
        if (!isAvailable(cardPos))
            return false;

        boolean flipDone = getGameBoard().flipCard(cardPos[0], cardPos[1]);
        if (flipDone)
        {
            if (getCurrentPlayer() instanceof Bot)
            {
                Bot aBot = (Bot) getCurrentPlayer();
                aBot.updateBoardMap(cardPos, getGameBoard().getCardContent(cardPos[0], cardPos[1]));
            }
        }
        return flipDone;
    }

    /** Current Bot player generates and flips a card.
     */
    public void botTurn()
    {
        DuelingBot aBot = (DuelingBot) getCurrentPlayer();
        flipCard(aBot.generateCardPick(getAvailableCardPicks()));
    }

    /** Changes the current player and the current board of the game.
     */
    private void nextPlayer()
    {
        currentPlayerIndex = (currentPlayerIndex + 1 ) % gameSet.size();
        gameBoard.changeBoard();
    }

    /** Updates the card picks of the player. Increases the number of current turn picks if the cards match and removes these cards of the board, else
     * reset the current turn picks. Returns true if the the number of current turn picks is equal to copies and removing the non-available cards of
     * the player's gameSet, or if the cards don't match. if the flipped
     * cards match changes the current player and the current board of the game. Returns false only when the next Player is playing.
     * @return A boolean representing if the game is updated.
     */
    public boolean update()
    {
        getCurrentPlayer().updateCardPicks();
        if (getGameBoard().doFlippedCardsMatch())
        {
            currentTurnPicks++;
            if (currentTurnPicks == DUEL_GAMEMODE_COPIES - 1 && gameSet.get((currentPlayerIndex + 1 ) % gameSet.size()).getGamePlayer() instanceof DuelingBot)//Sets the next pick of bot
            {
                DuelingBot aBot = (DuelingBot) gameSet.get((currentPlayerIndex + 1 ) % gameSet.size()).getGamePlayer();
                aBot.setGeneratedCardPick(gameBoard.getMostRecentCardContent());
            }

            if (currentTurnPicks == DUEL_GAMEMODE_COPIES)// If the cards match for the specific copies of the game
            {
                getCurrentPlayer().updatePairsFound();
                char cardContent = getGameBoard().removeCardsFromBoard();

                if (getCurrentPlayer() instanceof Bot) //Clears memory of bot for the removed cards
                {
                    Bot aBot = (Bot) getCurrentPlayer();
                    aBot.clearCardsFromMemory(cardContent);
                    aBot.removeFromFlipped();
                }

                   // Loop for removing the non-available cards for gameSet1
                    for (int i = 0 ; i < gameSet.get(0).getAvailableCardPicks().size(); i++)
                    {
                        int[] availableCardPicks =  gameSet.get(0).getAvailableCardPicks().get(i);
                        if (gameBoard.getCardContentBoard1(availableCardPicks[0],availableCardPicks[1]) == cardContent)
                        {
                            gameSet.get(0).getAvailableCardPicks().remove(i);
                            break;
                        }
                    }

                // Loop for removing the non-available cards for gameSet2
                for (int i = 0 ; i < gameSet.get(1).getAvailableCardPicks().size(); i++)
                {
                    int[] availableCardPicks =  gameSet.get(1).getAvailableCardPicks().get(i);
                    if (gameBoard.getCardContentBoard2(availableCardPicks[0],availableCardPicks[1]) == cardContent)
                    {
                        gameSet.get(1).getAvailableCardPicks().remove(i);
                        break;
                    }
                }
                currentTurnPicks = 0;
                return true;
            }
            nextPlayer();
            return false;
        }

        else
        {
            if (getCurrentPlayer() instanceof Bot)//Removing the flipped cards of bot
            {
                Bot aBot = (Bot) getCurrentPlayer();
                aBot.removeFromFlipped();
            }

            currentTurnPicks = 0;
            return true;
        }
    }
}
