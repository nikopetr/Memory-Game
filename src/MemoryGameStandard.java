import java.util.ArrayList;

/** Implements a Memory Game Standard. The game is played by turning over two or three cards a time, until all the cards have been matched.
 * There are 3 modes of the game. Every time that a player turns over a card the board of the game is updated.
 * @author Nikolas Petrou
 * @version 2.0
 */

public class MemoryGameStandard implements MemoryGame
{
    private static final int BASIC_GAMEMODE_COPIES = 2;
    private static final int BASIC_GAMEMODE_CARDS = 12;
    private static final int DOUBLE_GAMEMODE_COPIES = 2;
    private static final int DOUBLE_GAMEMODE_CARDS = 24;
    private static final int TRIPLE_GAMEMODE_COPIES = 3;
    private static final int TRIPLE_GAMEMODE_CARDS = 12;

    /** Represents the number of the different cards of the game.
     */
    private int differentCards;

    /** Represents the copies of each card.
     */
    private int copies;

    /** Represents the current player's picks for this turn.
     */
    private int currentTurnPicks ;

    /** ArrayList of int[] that represents the x and y coordinates of the cards that are available for flip.
     */
    private ArrayList<int []> availableCardPicks;

    /** ArrayList of Players that represents the Players of the game.
     */
    private ArrayList<Player> gamePlayers;

    /** Represents the index of the Player who is currently playing.
     */
    private int currentPlayerIndex;

    /** Represents Board of the board of the game.
     */
    private Board gameBoard;

    /** Creates a MemoryGame with the specified gameType, and Players.
     * Sets the copies for the cards and calls gameBoard's constructor for the current gameType.
     * Filling the availableCardPicks with each card's coordinates.
     * @param gameType The MemoryGame's type.
     * @param gamePlayers An ArrayList of Players with the Players for the game.
     */
    public MemoryGameStandard(int gameType, ArrayList<Player> gamePlayers)
    {
        switch (gameType)
        {
            case 1:  // Basic-GameMode
            {
                copies = BASIC_GAMEMODE_COPIES;
                differentCards = BASIC_GAMEMODE_CARDS;
                gameBoard = new StandardBoard(4,6, copies);
                break;
            }

            case 2:// Double-GameMode
            {
                copies = DOUBLE_GAMEMODE_COPIES;
                differentCards = DOUBLE_GAMEMODE_CARDS;
                gameBoard = new StandardBoard(6,8, copies);
                break;
            }

            case 3://Triple game-mode
            {
                copies = TRIPLE_GAMEMODE_COPIES;
                differentCards = TRIPLE_GAMEMODE_CARDS;
                gameBoard = new StandardBoard(6,6, copies);
                break;
            }
            default:
                return;
        }

        availableCardPicks = new ArrayList<>();
        for (int i = 0; i < gameBoard.getRows(); i++)
            for (int j = 0; j < gameBoard.getCol(); j++)
            {
                int[] aCardPos = {i, j};
                availableCardPicks.add(aCardPos);
            }

        Bot.setCopies(copies);
        currentTurnPicks = 0;
        currentPlayerIndex = 0;

        if (gamePlayers.size() > 4)
            throw new ArithmeticException("Standard mode can't have more than 4 players");

        this.gamePlayers = gamePlayers;
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
    public Player getCurrentPlayer(){ return gamePlayers.get(currentPlayerIndex);}

    /** Returns the game’s current player's index.
     * @return An integer representing the index of the Player who is currently playing.
     */
    public int getCurrentPlayerIndex() { return currentPlayerIndex; }

    /** Returns the cards that are available for flip.
     * @return An ArrayList of int[] representing the x and y coordinates of the cards that are available for flip.
     */
    public ArrayList<int []> getAvailableCardPicks()
    {
        return availableCardPicks;
    }

    /** Returns if the game if finished.
     * @return A boolean representing if the game if finished.
     */
    public boolean isGameFinished()
    {
        return (gameBoard.getCardsFound() == differentCards * copies);
    }

    /** Returns the game’s winning player.
     * If there are is more than one Player with the most cards found returns null.
     * @return A Player representing the Player who won at the game.
     */
    public Player getGameWinner()
    {
        int mostCardsFound = -1;
        Player winner = null;

        for (Player gamePlayer : gamePlayers)
        {
            if (gamePlayer.getTotalPairsFound() ==  mostCardsFound)
                winner = null;
            else if (gamePlayer.getTotalPairsFound() > mostCardsFound)
            {
                mostCardsFound = gamePlayer.getTotalPairsFound();
                winner = gamePlayer;
            }
        }

        return winner;
    }

    /** Returns if the card at the given cardSelected coordinates is available for pick.
     * @param cardSelected An array representing the x and y coordinates of the selected card.
     * @return A boolean representing if the card at the given coordinates is available for pick.
     */
    private boolean isAvailable (int [] cardSelected)
    {
        for (int[] cardPos : availableCardPicks)
            if (cardPos[0] == cardSelected[0] && cardPos[1] == cardSelected[1])
                return true;

        return false;
    }

    /** Flips the given card if it's available and in board's range.
     * If the flip is done, updates all the bots' boardMap.
     * @param cardPos An array representing the x and y coordinates of the selected card.
     * @return A Boolean representing if the specified card is now flipped.
     */
    public boolean flipCard(int [] cardPos)
    {
        if (!isAvailable(cardPos))
            return false;

        boolean flipDone = gameBoard.flipCard(cardPos[0], cardPos[1]);
        if (flipDone)
        {
            for (Player gamePlayer : gamePlayers)
                if (gamePlayer instanceof Bot)
                {
                    Bot aBot = (Bot) gamePlayer;
                    aBot.updateBoardMap(cardPos, gameBoard.getCardContent(cardPos[0], cardPos[1]));
                }
        }
        return flipDone;
    }

    /** Current Bot player generates and flips a card.
     */
    public void botTurn()
    {
        Bot aBot = (Bot) getCurrentPlayer();
        flipCard(aBot.generateCardPick(getAvailableCardPicks()));
    }

    /** Updates the card picks of the Players, updates Bots' memory and the AvailableCardPicks. Increases the current Player's number of current turn picks.
     * if the flipped cards match it removes these cards from the board, else resets the current turn picks.
     * Returns true if the the number of current turn picks is equal to copies or if the cards don't match.
     * @return A boolean representing if the board is updated.
     */
    public boolean update()
    {
        gamePlayers.get(currentPlayerIndex).updateCardPicks();
        if (gameBoard.doFlippedCardsMatch())
        {
            currentTurnPicks++;

            if (currentTurnPicks == copies)// If the cards match for the specific copies of the game
            {
                gamePlayers.get(currentPlayerIndex).updatePairsFound();
                char cardContent = gameBoard.removeCardsFromBoard();//Removing cards from Board

                for (Player aGamePlayer : gamePlayers)//Clears memory of bot for the removed cards
                    if (aGamePlayer instanceof Bot)
                    {
                        Bot aBot = (Bot) aGamePlayer;
                        aBot.clearCardsFromMemory(cardContent);
                    }

                int i = 0;
                while (i < availableCardPicks.size()) // Removing cards from availableCardPicks
                {
                    if (gameBoard.getCardContent(availableCardPicks.get(i)[0], availableCardPicks.get(i)[1]) == cardContent)
                        availableCardPicks.remove(i);

                    else
                        i++;
                }

                if (getCurrentPlayer() instanceof Bot)
                {
                    Bot aBot = (Bot) getCurrentPlayer();
                    aBot.removeFromFlipped();
                }

                currentTurnPicks = 0;
                return true;
            }
        }
        else  //Cards don't match
        {
            if (getCurrentPlayer() instanceof Bot)
            {
                Bot aBot = (Bot) getCurrentPlayer();
                aBot.removeFromFlipped();
            }

            currentPlayerIndex = (currentPlayerIndex + 1 ) % gamePlayers.size();//Next player
            currentTurnPicks = 0;
            return true;
        }
        return false;
    }
}