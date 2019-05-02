import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class MemoryGameStandardTest {

    @Test
    public void getCurrentPlayerIndex() {
        System.out.println("getCurrentPlayerIndex");
        ArrayList<Player> gamePlayers;
        gamePlayers = new ArrayList<>();
        Player aPlayer = new Player();
        Player Player2 = new Player();
        Bot aBot = new Bot(1);
        gamePlayers.add(aPlayer);
        gamePlayers.add(Player2);
        gamePlayers.add(aBot);
        MemoryGameStandard aMemoryGame = new MemoryGameStandard(1,gamePlayers);
        assertEquals(0,aMemoryGame.getCurrentPlayerIndex());
        aMemoryGame.update();
        assertEquals(1,aMemoryGame.getCurrentPlayerIndex());
        aMemoryGame.update();
        assertEquals(2,aMemoryGame.getCurrentPlayerIndex());
        aMemoryGame = new MemoryGameStandard(2,gamePlayers);
        assertEquals(0,aMemoryGame.getCurrentPlayerIndex());
        aMemoryGame.update();
        assertEquals(1,aMemoryGame.getCurrentPlayerIndex());
        aMemoryGame.update();
        assertEquals(2,aMemoryGame.getCurrentPlayerIndex());
        aMemoryGame = new MemoryGameStandard(3,gamePlayers);
        assertEquals(0,aMemoryGame.getCurrentPlayerIndex());
        aMemoryGame.update();
        assertEquals(1,aMemoryGame.getCurrentPlayerIndex());
        aMemoryGame.update();
        assertEquals(2,aMemoryGame.getCurrentPlayerIndex());


    }

    @Test
    public void getGameBoard() {
        System.out.println("getGameBoard");
        ArrayList<Player> gamePlayers;
        gamePlayers = new ArrayList<>();
        Player aPlayer = new Player();
        Player player2 = new Player();
        gamePlayers.add(aPlayer);
        gamePlayers.add(player2);
        MemoryGameStandard aMemoryGame = new MemoryGameStandard(1,gamePlayers);
        StandardBoard aBoard = new StandardBoard(4,6,2);
        assertEquals(aBoard.getCol(),aMemoryGame.getGameBoard().getCol());
        assertEquals(aBoard.getRows(),aMemoryGame.getGameBoard().getRows());
        ArrayList<int []> availableCardPicks;
        availableCardPicks = aMemoryGame.getAvailableCardPicks();
        for (int[] a:availableCardPicks)
        {
            if (a[0] < 0 || a[0] > 4 && a[1] < 0 || a[1] > 6)
                fail("found values that were not expected");
        }

        aMemoryGame = new MemoryGameStandard(2,gamePlayers);
        aBoard = new StandardBoard(6,8,2);
        assertEquals(aBoard.getCol(),aMemoryGame.getGameBoard().getCol());
        assertEquals(aBoard.getRows(),aMemoryGame.getGameBoard().getRows());
        availableCardPicks.clear();
        availableCardPicks = aMemoryGame.getAvailableCardPicks();
        for (int[] a:availableCardPicks)
        {
            if (a[0] < 0 || a[0] > 6 && a[1] < 0 || a[1] > 8)
                fail("found values that were not expected");
        }

        aMemoryGame = new MemoryGameStandard(3,gamePlayers);
        aBoard = new StandardBoard(6,6,2);
        assertEquals(aBoard.getCol(),aMemoryGame.getGameBoard().getCol());
        assertEquals(aBoard.getRows(),aMemoryGame.getGameBoard().getRows());
        availableCardPicks.clear();
        availableCardPicks = aMemoryGame.getAvailableCardPicks();
        for (int[] a:availableCardPicks)
        {
            if (a[0] < 0 || a[0] > 6 && a[1] < 0 || a[1] > 6)
                fail("found values that were not expected");
        }


    }

    @Test
    public void getCurrentPlayer() {
        System.out.println("getCurrentPlayer");
        ArrayList<Player> gamePlayers;
        gamePlayers = new ArrayList<>();
        Player aPlayer = new Player();
        Player player2 = new Player();
        Bot aBot = new Bot(3);
        gamePlayers.add(aPlayer);
        gamePlayers.add(player2);
        gamePlayers.add(aBot);
        MemoryGameStandard aMemoryGame = new MemoryGameStandard(1,gamePlayers);
        assertEquals(aPlayer,aMemoryGame.getCurrentPlayer());
        aMemoryGame.update();
        assertEquals(player2,aMemoryGame.getCurrentPlayer());
        aMemoryGame.update();
        assertEquals(aBot,aMemoryGame.getCurrentPlayer());

        aMemoryGame = new MemoryGameStandard(2,gamePlayers);
        assertEquals(aPlayer,aMemoryGame.getCurrentPlayer());
        aMemoryGame.update();
        assertEquals(player2,aMemoryGame.getCurrentPlayer());
        aMemoryGame.update();
        assertEquals(aBot,aMemoryGame.getCurrentPlayer());

        aMemoryGame = new MemoryGameStandard(3,gamePlayers);
        assertEquals(aPlayer,aMemoryGame.getCurrentPlayer());
        aMemoryGame.update();
        assertEquals(player2,aMemoryGame.getCurrentPlayer());
        aMemoryGame.update();
        assertEquals(aBot,aMemoryGame.getCurrentPlayer());


    }

    @Test
    public void getAvailableCardPicks() {
        System.out.println("getAvailableCardPicks");
        ArrayList<Player> gamePlayers;
        gamePlayers = new ArrayList<>();
        Player aPlayer = new Player();
        Player player2 = new Player();
        gamePlayers.add(aPlayer);
        gamePlayers.add(player2);
        MemoryGameStandard aMemoryGame = new MemoryGameStandard(1,gamePlayers);
        assertEquals(24,aMemoryGame.getAvailableCardPicks().size());
        for (int[] a:aMemoryGame.getAvailableCardPicks())
        {
            if (a[0] < 0 || a[0] > 4 && a[1] < 0 || a[1] > 6)
                fail("found values that were not expected");
        }


        MemoryGameStandard MemoryGame2 = new MemoryGameStandard(2,gamePlayers);
        assertEquals(48,MemoryGame2.getAvailableCardPicks().size());
        for (int[] a:aMemoryGame.getAvailableCardPicks())
        {
            if (a[0] < 0 || a[0] > 6 && a[1] < 0 || a[1] > 8)
                fail("found values that were not expected");
        }


        MemoryGameStandard MemoryGame3 = new MemoryGameStandard(3,gamePlayers);
        assertEquals(36,MemoryGame3.getAvailableCardPicks().size());
        for (int[] a:aMemoryGame.getAvailableCardPicks())
        {
            if (a[0] < 0 || a[0] > 6 && a[1] < 0 || a[1] > 6)
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
        MemoryGameStandard aMemoryGame = new MemoryGameStandard(1,gamePlayers);
        assertFalse(aMemoryGame.isGameFinished());
        for (int i=0;i<4;i++) {
            for (int j = 0; j < 6; j++) {
                aMemoryGame.getGameBoard().flipCard(i, j);
                aMemoryGame.getGameBoard().getCardContent(i, j);
                aMemoryGame.getGameBoard().removeCardsFromBoard();
            }
        }
        assertTrue(aMemoryGame.isGameFinished());

        aMemoryGame = new MemoryGameStandard(2,gamePlayers);
        assertFalse(aMemoryGame.isGameFinished());
        for (int i=0;i<6;i++) {
            for (int j = 0; j < 8; j++) {
                aMemoryGame.getGameBoard().flipCard(i, j);
                aMemoryGame.getGameBoard().getCardContent(i, j);
                aMemoryGame.getGameBoard().removeCardsFromBoard();
            }
        }
        assertTrue(aMemoryGame.isGameFinished());

        aMemoryGame = new MemoryGameStandard(3,gamePlayers);
        assertFalse(aMemoryGame.isGameFinished());
        for (int i=0;i<6;i++) {
            for (int j = 0; j < 6; j++) {
                aMemoryGame.getGameBoard().flipCard(i, j);
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
        Player player2 = new Player();
        gamePlayers.add(aPlayer);
        gamePlayers.add(player2);
        MemoryGameStandard aMemoryGame = new MemoryGameStandard(1,gamePlayers);
        assertNull(aMemoryGame.getGameWinner());
        aPlayer.updatePairsFound();
        assertEquals(gamePlayers.get(0),aMemoryGame.getGameWinner());

        player2.updatePairsFound();
        player2.updatePairsFound();
        assertEquals(gamePlayers.get(1),aMemoryGame.getGameWinner());

        gamePlayers = new ArrayList<>();
        aPlayer = new Player();
        player2 = new Player();
        gamePlayers.add(aPlayer);
        gamePlayers.add(player2);
        aMemoryGame = new MemoryGameStandard(2,gamePlayers);
        assertNull(aMemoryGame.getGameWinner());
        aPlayer.updatePairsFound();
        assertEquals(gamePlayers.get(0),aMemoryGame.getGameWinner());

        player2.updatePairsFound();
        player2.updatePairsFound();
        assertEquals(gamePlayers.get(1),aMemoryGame.getGameWinner());

        gamePlayers = new ArrayList<>();
        aPlayer = new Player();
        player2 = new Player();
        gamePlayers.add(aPlayer);
        gamePlayers.add(player2);
        aMemoryGame = new MemoryGameStandard(3,gamePlayers);
        assertNull(aMemoryGame.getGameWinner());
        aPlayer.updatePairsFound();
        assertEquals(gamePlayers.get(0),aMemoryGame.getGameWinner());

        player2.updatePairsFound();
        player2.updatePairsFound();
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
        MemoryGameStandard aMemoryGame = new MemoryGameStandard(1,gamePlayers);
        int[] cardPos = new int[2];
        cardPos[0] = 1;
        cardPos[1] = 2;
        assertTrue(aMemoryGame.flipCard(cardPos));
        assertFalse(aMemoryGame.flipCard(cardPos));
        cardPos[0]=2;
        cardPos[1]=2;
        assertTrue(aMemoryGame.flipCard(cardPos));

        aMemoryGame = new MemoryGameStandard(1,gamePlayers);
        cardPos = new int[2];
        cardPos[0] = 1;
        cardPos[1] = 2;
        assertTrue(aMemoryGame.flipCard(cardPos));
        assertFalse(aMemoryGame.flipCard(cardPos));
        cardPos[0]=2;
        cardPos[1]=2;
        assertTrue(aMemoryGame.flipCard(cardPos));

        aMemoryGame = new MemoryGameStandard(1,gamePlayers);
        cardPos = new int[2];
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
        ArrayList<Player> gamePlayers;
        gamePlayers = new ArrayList<>();
        Bot aBot = new Bot(1);
        Player aPlayer = new Player();
        gamePlayers.add(aBot);
        gamePlayers.add(aPlayer);
        MemoryGameStandard aMemoryGame = new MemoryGameStandard(1,gamePlayers);
        int sizebeforeFlip = aBot.getFlippedCardsPos().size();

        aMemoryGame.botTurn();
        boolean isFlipped=false;
        int sizeafterFlip = aBot.getFlippedCardsPos().size();

        if (sizeafterFlip!=sizebeforeFlip)
            isFlipped=true;
        assertTrue(isFlipped);

        aBot = new Bot(2);
        gamePlayers.clear();
        gamePlayers.add(aBot);
        gamePlayers.add(aPlayer);
        aMemoryGame = new MemoryGameStandard(2,gamePlayers);
        sizebeforeFlip = aBot.getFlippedCardsPos().size();

        aMemoryGame.botTurn();
        isFlipped=false;
        sizeafterFlip = aBot.getFlippedCardsPos().size();

        if (sizeafterFlip!=sizebeforeFlip)
            isFlipped=true;
        assertTrue(isFlipped);

        aBot = new Bot(3);
        gamePlayers.clear();
        gamePlayers.add(aBot);
        gamePlayers.add(aPlayer);
        aMemoryGame = new MemoryGameStandard(3,gamePlayers);
        sizebeforeFlip = aBot.getFlippedCardsPos().size();

        aMemoryGame.botTurn();
        isFlipped=false;
        sizeafterFlip = aBot.getFlippedCardsPos().size();

        if (sizeafterFlip!=sizebeforeFlip)
            isFlipped=true;
        assertTrue(isFlipped);
    }


    @Test
    public void update() {
        System.out.println("update");
        ArrayList<Player> gamePlayers;
        gamePlayers = new ArrayList<>();
        Player aPlayer = new Player();
        Player Player2 = new Bot(1);
        gamePlayers.add(aPlayer);
        gamePlayers.add(Player2);
        Player player3 = new Player();
        gamePlayers.add(player3);
        MemoryGameStandard aMemoryGame = new MemoryGameStandard(1,gamePlayers);
        assertEquals(gamePlayers.get(0),aMemoryGame.getCurrentPlayer());
        int[] cardPos = {1,2};
        int[] cardPos2={1,4};
        aMemoryGame.flipCard(cardPos);
        aMemoryGame.flipCard(cardPos2);
        Player currentPlayer;
        if (aMemoryGame.getGameBoard().getCardContent(1,2)==aMemoryGame.getGameBoard().getCardContent(1,4))
            currentPlayer=gamePlayers.get(0);
        else
            currentPlayer=gamePlayers.get(1);

        aMemoryGame.update();
        assertEquals(currentPlayer,aMemoryGame.getCurrentPlayer());

        /*
        cardPos = new int[2];
        cardPos2 = new int[2];
        cardPos[0]=2;
        cardPos[1]=2;
        cardPos2[0]=3;
        cardPos2[1]=3;
        aMemoryGame.flipCard(cardPos);
        aMemoryGame.flipCard(cardPos2);
        if (aMemoryGame.getGameBoard().getCardContent(2,2)==aMemoryGame.getGameBoard().getCardContent(3,3))
        {
            currentPlayer=gamePlayers.get(1);
        } else
            currentPlayer=gamePlayers.get(2);
        System.out.println("current player"+currentPlayer);
        System.out.println(aMemoryGame.getGameBoard().getCardContent(2,2));
        System.out.println(aMemoryGame.getGameBoard().getCardContent(3,3));
        aMemoryGame.update();
        assertEquals(currentPlayer,aMemoryGame.getCurrentPlayer());
        */

        aMemoryGame = new MemoryGameStandard(2,gamePlayers);
        assertEquals(gamePlayers.get(0),aMemoryGame.getCurrentPlayer());
        cardPos[0] = 1;
        cardPos[1]= 2;
        cardPos2[0]=1;
        cardPos2[1]=4;
        aMemoryGame.flipCard(cardPos);
        aMemoryGame.flipCard(cardPos2);
        if (aMemoryGame.getGameBoard().getCardContent(1,2)==aMemoryGame.getGameBoard().getCardContent(1,4))
            currentPlayer=gamePlayers.get(0);
        else
            currentPlayer=gamePlayers.get(1);
        aMemoryGame.update();
        assertEquals(currentPlayer,aMemoryGame.getCurrentPlayer());

        /*
        cardPos = new int[2];
        cardPos[0]=2;
        cardPos[1]=2;
        cardPos2[0]=3;
        cardPos2[1]=3;
        aMemoryGame.flipCard(cardPos);
        aMemoryGame.flipCard(cardPos2);
        if (aMemoryGame.getGameBoard().getCardContent(2,2)==aMemoryGame.getGameBoard().getCardContent(3,3)) {
            currentPlayer = gamePlayers.get(1);
            System.out.println("empika");
        }
        else
            currentPlayer=gamePlayers.get(2);
        aMemoryGame.update();
        assertEquals(currentPlayer,aMemoryGame.getCurrentPlayer());
        */

        aMemoryGame = new MemoryGameStandard(3,gamePlayers);
        assertEquals(gamePlayers.get(0),aMemoryGame.getCurrentPlayer());
        cardPos[0] = 1;
        cardPos[1]= 2;
        cardPos2[0]=1;
        cardPos2[1]=4;
        int[] cardPos3 = {3,2};
        aMemoryGame.flipCard(cardPos);
        aMemoryGame.flipCard(cardPos2);
        if (aMemoryGame.getGameBoard().getCardContent(1,2)==aMemoryGame.getGameBoard().getCardContent(1,4))
        {
            aMemoryGame.update();
            aMemoryGame.flipCard(cardPos3);
            if (aMemoryGame.getGameBoard().getCardContent(1,2)==aMemoryGame.getGameBoard().getCardContent(3,2))
                currentPlayer = gamePlayers.get(0);
            else
                currentPlayer=gamePlayers.get(1);
        }
        else
            currentPlayer=gamePlayers.get(1);
        aMemoryGame.update();
        assertEquals(currentPlayer,aMemoryGame.getCurrentPlayer());

        /*
        cardPos[0] = 2;
        cardPos[1]= 2;
        cardPos2[0]=3;
        cardPos2[1]=3;
        cardPos3[0] = 1;
        cardPos3[1] = 1;
        aMemoryGame.flipCard(cardPos);
        aMemoryGame.flipCard(cardPos2);
        if (aMemoryGame.getGameBoard().getCardContent(2,2)==aMemoryGame.getGameBoard().getCardContent(3,3))
        {
            aMemoryGame.flipCard(cardPos3);
            if (aMemoryGame.getGameBoard().getCardContent(2,2)==aMemoryGame.getGameBoard().getCardContent(1,1))
                currentPlayer = gamePlayers.get(1);
            else
                currentPlayer=gamePlayers.get(2);
        }
        else
            currentPlayer=gamePlayers.get(2);
        aMemoryGame.update();
        assertEquals(currentPlayer,aMemoryGame.getCurrentPlayer());
        */

    }
}