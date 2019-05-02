import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MemoryGameDuelTest {

    @Test
    public void getCurrentPlayerIndex() {
        System.out.println("getCurrentPlayerIndex");
        ArrayList<Player> gamePlayers;
        gamePlayers = new ArrayList<>();
        Player aPlayer = new Player();
        Player Player2 = new Player();
        gamePlayers.add(aPlayer);
        gamePlayers.add(Player2);
        MemoryGameDuel aMemoryGame = new MemoryGameDuel(gamePlayers);
        assertEquals(0,aMemoryGame.getCurrentPlayerIndex());
        int[] cardPos = {1,2};
        aMemoryGame.flipCard(cardPos);
        aMemoryGame.update();
        assertEquals(1,aMemoryGame.getCurrentPlayerIndex());
        aMemoryGame.flipCard(cardPos);
        aMemoryGame.update();
        assertEquals(1,aMemoryGame.getCurrentPlayerIndex());
    }

    @Test
    public void getGameBoard() {
        System.out.println("getGameBoard");
        ArrayList<Player> gamePlayers;
        gamePlayers = new ArrayList<>();
        Player aPlayer = new Player();
        Player Player2 = new Player();
        gamePlayers.add(aPlayer);
        gamePlayers.add(Player2);
        MemoryGameDuel aMemoryGame = new MemoryGameDuel(gamePlayers);
        Board aBoard = new DuelingBoard();
        assertEquals(aBoard.getCol(),aMemoryGame.getGameBoard().getCol());
        assertEquals(aBoard.getRows(),aMemoryGame.getGameBoard().getRows());
        aBoard = aMemoryGame.getGameBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                if (aBoard.getCardContent(i,j) < 'A' || aBoard.getCardContent(i,j) > 'Z') {
                    fail("found values that were not expected");
                }
            }
        }
        for (int[] a:aMemoryGame.getAvailableCardPicks())
        {
            if (a[0] < 0 || a[0] > 6 && a[1] < 0 || a[1] > 6)
                fail("found values that were not expected");
        }
    }

    @Test
    public void getCurrentPlayer()
    {
        System.out.println("getCurrentPlayer");
        ArrayList<Player> gamePlayers;
        gamePlayers = new ArrayList<>();
        Player aPlayer = new Player();
        Player Player2 = new Player();
        gamePlayers.add(aPlayer);
        gamePlayers.add(Player2);
        MemoryGameDuel aMemoryGame = new MemoryGameDuel(gamePlayers);
        assertEquals(gamePlayers.get(0),aMemoryGame.getCurrentPlayer());
        int[] cardPos = {1,2};
        aMemoryGame.flipCard(cardPos);
        aMemoryGame.update();
        assertEquals(gamePlayers.get(1),aMemoryGame.getCurrentPlayer());
        int [] cardPos2 = {1,5};
        aMemoryGame.flipCard(cardPos2);
        aMemoryGame.update();
        assertEquals(gamePlayers.get(1),aMemoryGame.getCurrentPlayer());

    }

    @Test
    public void getAvailableCardPicks() {
        System.out.println("getAvailableCardPicks");
        ArrayList<Player> gamePlayers;
        gamePlayers = new ArrayList<>();
        Player aPlayer = new Player();
        Player Player2 = new Player();
        gamePlayers.add(aPlayer);
        gamePlayers.add(Player2);
        MemoryGameDuel aMemoryGame = new MemoryGameDuel(gamePlayers);
        assertEquals(24,aMemoryGame.getAvailableCardPicks().size());
        ArrayList<int []> availableCardPicks;
        availableCardPicks = aMemoryGame.getAvailableCardPicks();
        for (int[] a:availableCardPicks)
        {
                if (a[0] < 0 || a[0] > 4 && a[1] < 0 || a[1] > 6)
                    fail("found values that were not expected");

        }
    }

    @Test
    public void isGameFinished() {
        System.out.println("isGameFinished");
        ArrayList<Player> gamePlayers;
        gamePlayers = new ArrayList<>();
        Player aPlayer = new Player();
        Player Player2 = new Player();
        gamePlayers.add(aPlayer);
        gamePlayers.add(Player2);
        MemoryGameDuel aMemoryGame = new MemoryGameDuel(gamePlayers);
        assertFalse(aMemoryGame.isGameFinished());
        for (int i=0;i<4;i++) {
            for (int j = 0; j < 6; j++) {
                aMemoryGame.getGameBoard().flipCard(i, j);
                aMemoryGame.getGameBoard().getCardContent(i, j);
                aMemoryGame.getGameBoard().removeCardsFromBoard();
            }
        }


        DuelingBoard aDuelingBoard = (DuelingBoard)aMemoryGame.getGameBoard();
        aDuelingBoard.changeBoard();
        for (int i=0;i<4;i++) {
            for (int j = 0; j < 6; j++) {
                aMemoryGame.getGameBoard().flipCard(i,j);
                aMemoryGame.getGameBoard().getCardContent(i, j);
                aMemoryGame.getGameBoard().removeCardsFromBoard();
            }
        }
        assertTrue(aMemoryGame.isGameFinished());
    }

    @Test
    public void getGameWinner() {
        System.out.println("getGameWinner");
        ArrayList<Player> gamePlayers;
        gamePlayers = new ArrayList<>();
        Player aPlayer = new Player();
        Player Player2 = new Player();
        gamePlayers.add(aPlayer);
        gamePlayers.add(Player2);
        MemoryGameDuel aMemoryGame = new MemoryGameDuel(gamePlayers);
        assertNull(aMemoryGame.getGameWinner());
        aPlayer.updatePairsFound();
        assertEquals(gamePlayers.get(0),aMemoryGame.getGameWinner());
        Player2.updatePairsFound();
        Player2.updatePairsFound();
        assertEquals(gamePlayers.get(1),aMemoryGame.getGameWinner());

    }

    @Test
    public void flipCard() {
        System.out.println("flipCard");
        ArrayList<Player> gamePlayers;
        gamePlayers = new ArrayList<>();
        Player aPlayer = new Player();
        Player Player2 = new Player();
        gamePlayers.add(aPlayer);
        gamePlayers.add(Player2);
        MemoryGameDuel aMemoryGame = new MemoryGameDuel(gamePlayers);
        int[] cardPos = new int[2];
        cardPos[0] = 1;
        cardPos[1] = 2;
        assertTrue(aMemoryGame.flipCard(cardPos));
        assertFalse(aMemoryGame.flipCard(cardPos));
        cardPos[0]=2;
        cardPos[1]=2;
        assertTrue(aMemoryGame.flipCard(cardPos));

    }

    @Test
    public void botTurn() {
        System.out.println("botTurn");
        ArrayList<Player> gamePlayers = new ArrayList<>();
        DuelingBot aBot = new DuelingBot(1);
        Player aPlayer = new Player();
        gamePlayers.add(aBot);
        gamePlayers.add(aPlayer);
        boolean isTrue=false;
        MemoryGameDuel aMemoryGame = new MemoryGameDuel(gamePlayers);
        if (aMemoryGame.getCurrentPlayer()instanceof Bot){
            aMemoryGame.botTurn();
            isTrue=true;
        }

        assertTrue(isTrue);

        aMemoryGame.update();
        if (aMemoryGame.getCurrentPlayer()instanceof Bot){
            aMemoryGame.botTurn();
            isTrue=true;
        }
        else
            isTrue=false;
        assertFalse(isTrue);


    }

    @Test
    public void update() {
        System.out.println("update");
        ArrayList<Player> gamePlayers;
        gamePlayers = new ArrayList<>();
        Player aPlayer = new Player();
        Bot Player2 = new DuelingBot(3);
        gamePlayers.add(aPlayer);
        gamePlayers.add(Player2);
        MemoryGameDuel aMemoryGame = new MemoryGameDuel(gamePlayers);
        assertEquals(gamePlayers.get(0),aMemoryGame.getCurrentPlayer());
        int[] cardPos = {1,2};
        int[] cardPos2={1,4};
        aMemoryGame.flipCard(cardPos);
        aMemoryGame.update();
        DuelingBoard aDuelingBoard = (DuelingBoard)aMemoryGame.getGameBoard();
        aDuelingBoard.changeBoard();
        aMemoryGame.flipCard(cardPos2);
        Player currentPlayer;
        currentPlayer=gamePlayers.get(1);
        aMemoryGame.update();
        assertEquals(currentPlayer,aMemoryGame.getCurrentPlayer());

        aMemoryGame.flipCard(cardPos);
        aMemoryGame.update();
        aDuelingBoard.changeBoard();
        aMemoryGame.flipCard(cardPos2);
        currentPlayer=gamePlayers.get(0);
        aMemoryGame.update();
        assertEquals(currentPlayer,aMemoryGame.getCurrentPlayer());

    }
}