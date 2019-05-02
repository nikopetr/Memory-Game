import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class StandardBoardTest {


    @Test
    public void flipCard() {
        System.out.println("flipCard");
        StandardBoard aBoard = new StandardBoard(4,6,2);
        assertTrue(aBoard.flipCard(2,3));
        assertFalse(aBoard.flipCard(2,3));
        assertTrue(aBoard.flipCard(3,3));

        aBoard = new StandardBoard(6,8,2);
        assertTrue(aBoard.flipCard(2,3));
        assertFalse(aBoard.flipCard(2,3));
        assertTrue(aBoard.flipCard(3,3));

        aBoard = new StandardBoard(6,6,3);
        assertTrue(aBoard.flipCard(2,3));
        assertFalse(aBoard.flipCard(2,3));
        assertTrue(aBoard.flipCard(3,3));

    }

    @Test
    public void doFlippedCardsMatch() {
        System.out.println("doFlippedCardsMatch");
        StandardBoard aBoard = new StandardBoard(4,6,2);
        assertFalse(aBoard.doFlippedCardsMatch());
        aBoard.flipCard(3,3);
        assertTrue(aBoard.doFlippedCardsMatch());
        aBoard.flipCard(0,3);
        boolean isMatched=false;
        if (aBoard.getCardContent(3,3)==aBoard.getCardContent(0,3)) {
            isMatched = true;
        }
        assertEquals(isMatched, aBoard.doFlippedCardsMatch());


        aBoard = new StandardBoard(6,8,2);
        assertFalse(aBoard.doFlippedCardsMatch());
        aBoard.flipCard(3,3);
        aBoard.flipCard(2,3);
        isMatched = aBoard.getCardContent(3, 3) == aBoard.getCardContent(2, 3);
        assertEquals(isMatched, aBoard.doFlippedCardsMatch());

        aBoard = new StandardBoard(6,6,3);
        assertFalse(aBoard.doFlippedCardsMatch());
        aBoard.flipCard(3,3);
        assertTrue(aBoard.doFlippedCardsMatch());
        aBoard.flipCard(0,3);
        aBoard.flipCard(2,3);
        isMatched=false;
        if (aBoard.getCardContent(3,3)==aBoard.getCardContent(0,3)) {
            if (aBoard.getCardContent(3,3)==aBoard.getCardContent(2,3)) {
                isMatched = true;
            }

        }
        assertEquals(isMatched, aBoard.doFlippedCardsMatch());
    }

    @Test
    public void getCardContent() {
        System.out.println("getCardContent");
        StandardBoard aBoard = new StandardBoard(4,6,2);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                if (aBoard.getCardContent(i,j) < 'A' || aBoard.getCardContent(i,j) > 'Z') {
                    fail("found values that were not expected");
                }
            }
        }
        HashMap<int[], Character> boardMap = new HashMap<>();
        int[] cardPos;
        for (int k = 0; k < aBoard.getRows(); k++)
            for (int m = 0; m < aBoard.getCol(); m++) {
                cardPos = new int[2];
                cardPos[0] = k;
                cardPos[1] = m;
                boardMap.put(cardPos, aBoard.getCardContent(k, m));
            }
        Character cardChar = ' ';
        for (HashMap.Entry<int[], Character> entry : boardMap.entrySet())
            if (entry.getValue() == aBoard.getCardContent(1, 1))
                cardChar = entry.getValue();


        Character cardChar2 = aBoard.getCardContent(1,1);
        assertEquals(cardChar,cardChar2);

        aBoard = new StandardBoard(6,8,2);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                if (aBoard.getCardContent(i,j) < 'A' || aBoard.getCardContent(i,j) > 'Z') {
                    fail("found values that were not expected");
                }
            }
        }
        boardMap = new HashMap<>();
        for (int k = 0; k < aBoard.getRows(); k++)
            for (int m = 0; m < aBoard.getCol(); m++) {
                cardPos = new int[2];
                cardPos[0] = k;
                cardPos[1] = m;
                boardMap.put(cardPos, aBoard.getCardContent(k, m));
            }

        for (HashMap.Entry<int[], Character> entry : boardMap.entrySet())
            if (entry.getValue() == aBoard.getCardContent(3, 2))
                cardChar = entry.getValue();


        cardChar2 = aBoard.getCardContent(3,2);
        assertEquals(cardChar,cardChar2);

        aBoard = new StandardBoard(6,6,3);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                if (aBoard.getCardContent(i,j) < 'A' || aBoard.getCardContent(i,j) > 'Z') {
                    fail("found values that were not expected");
                }
            }
        }
        boardMap = new HashMap<>();
        for (int k = 0; k < aBoard.getRows(); k++)
            for (int m = 0; m < aBoard.getCol(); m++) {
                cardPos = new int[2];
                cardPos[0] = k;
                cardPos[1] = m;
                boardMap.put(cardPos, aBoard.getCardContent(k, m));
            }


        for (HashMap.Entry<int[], Character> entry : boardMap.entrySet())
            if (entry.getValue() == aBoard.getCardContent(2, 2))
                cardChar = entry.getValue();


        cardChar2 = aBoard.getCardContent(2,2);
        assertEquals(cardChar,cardChar2);

    }

    @Test
    public void removeCardsFromBoard() {
        System.out.println("removeCardsFromBoard");
        StandardBoard aBoard = new StandardBoard(4, 6, 2);
        aBoard.flipCard(2, 2);
        assertEquals(aBoard.getCardContent(2, 2), aBoard.removeCardsFromBoard());
        aBoard.flipCard(3, 4);
        HashMap<int[], Character> boardMap = new HashMap<>();
        int[] cardPos = new int[2];
        for (int k = 0; k < aBoard.getRows(); k++)
            for (int m = 0; m < aBoard.getCol(); m++) {
                cardPos = new int[2];
                cardPos[0] = k;
                cardPos[1] = m;
                boardMap.put(cardPos, aBoard.getCardContent(k, m));
            }
        cardPos[0] = 3;
        cardPos[1] = 4;
        for (HashMap.Entry<int[], Character> entry : boardMap.entrySet())
            if ((entry.getValue() == aBoard.getCardContent(3, 4)) && (entry.getKey() != cardPos)) {

                cardPos[0] = entry.getKey()[0];
                cardPos[1] = entry.getKey()[1];
                break;
            }
        aBoard.flipCard(cardPos[0], cardPos[1]);
        assertTrue(aBoard.doFlippedCardsMatch());
        assertEquals(aBoard.getCardContent(3, 4), aBoard.removeCardsFromBoard());


        aBoard = new StandardBoard(6, 8, 2);
        aBoard.flipCard(2, 2);
        assertEquals(aBoard.getCardContent(2, 2), aBoard.removeCardsFromBoard());
        aBoard.flipCard(3, 4);
        boardMap = new HashMap<>();
        cardPos = new int[2];
        for (int k = 0; k < aBoard.getRows(); k++)
            for (int m = 0; m < aBoard.getCol(); m++) {
                cardPos = new int[2];
                cardPos[0] = k;
                cardPos[1] = m;
                boardMap.put(cardPos, aBoard.getCardContent(k, m));
            }
        cardPos[0] = 3;
        cardPos[1] = 4;
        for (HashMap.Entry<int[], Character> entry : boardMap.entrySet())
            if ((entry.getValue() == aBoard.getCardContent(3, 4)) && (entry.getKey() != cardPos)) {
                cardPos[0] = entry.getKey()[0];
                cardPos[1] = entry.getKey()[1];
                break;
            }
        aBoard.flipCard(cardPos[0], cardPos[1]);
        assertTrue(aBoard.doFlippedCardsMatch());
        assertEquals(aBoard.getCardContent(3, 4), aBoard.removeCardsFromBoard());

        aBoard = new StandardBoard(6, 6, 3);
        aBoard.flipCard(2, 2);
        assertEquals(aBoard.getCardContent(2, 2), aBoard.removeCardsFromBoard());

        aBoard = new StandardBoard(6, 6, 3);
        boardMap = new HashMap<>();
        cardPos = new int[2];
        for (int k = 0; k < aBoard.getRows(); k++){
            for (int m = 0; m < aBoard.getCol(); m++) {
                cardPos = new int[2];
                cardPos[0] = k;
                cardPos[1] = m;
                boardMap.put(cardPos, aBoard.getCardContent(k, m));
            }
        }
        aBoard.flipCard(3,3);
        cardPos[0] =3;
        cardPos[1] =3;
        int[] cardPos2 = new int[2];
        for (HashMap.Entry<int[], Character> entry : boardMap.entrySet())
            if ((entry.getValue() == aBoard.getCardContent(3, 3)) && (entry.getKey() != cardPos)) {

                cardPos2[0] = entry.getKey()[0];
                cardPos2[1] = entry.getKey()[1];
                break;
            }
        aBoard.flipCard(cardPos2[0],cardPos2[1]);
        for (HashMap.Entry<int[], Character> entry : boardMap.entrySet())
            if ((entry.getValue() == aBoard.getCardContent(3, 3)) && (entry.getKey() != cardPos)&&(entry.getKey() != cardPos2)) {
                cardPos[0] = entry.getKey()[0];
                cardPos[1] = entry.getKey()[1];
                break;
            }

        aBoard.flipCard(cardPos[0],cardPos[1]);
        assertTrue(aBoard.doFlippedCardsMatch());
        assertEquals(aBoard.getCardContent(3,3),aBoard.removeCardsFromBoard());

    }

    @Test
    public void getCardsFound() {
        System.out.println("getCardsFound");
        StandardBoard aBoard = new StandardBoard(4, 6, 2);
        assertEquals(0, aBoard.getCardsFound());

        aBoard.flipCard(2, 2);
        aBoard.getCardContent(2, 2);
        aBoard.removeCardsFromBoard();
        assertEquals(1, aBoard.getCardsFound());

        aBoard = new StandardBoard(4, 6, 2);

        HashMap<int[], Character> boardMap = new HashMap<>();
        int[] cardPos;
        for (int k = 0; k < aBoard.getRows(); k++){
            for (int m = 0; m < aBoard.getCol(); m++) {
                cardPos = new int[2];
                cardPos[0] = k;
                cardPos[1] = m;
                boardMap.put(cardPos, aBoard.getCardContent(k, m));
            }
        }


        cardPos=new int[2];
        cardPos[0]=1;
        cardPos[1]=2;


        assertTrue(aBoard.flipCard(1, 2));
        for (HashMap.Entry<int[], Character> entry : boardMap.entrySet()) {
            if ((entry.getValue() == aBoard.getCardContent(1, 2))&&(entry.getKey()[0] != cardPos[0]||(entry.getKey()[1]!=cardPos[1]))) {
                cardPos[0] = entry.getKey()[0];
                cardPos[1] = entry.getKey()[1];
                break;
            }
        }

        assertTrue(aBoard.flipCard(cardPos[0], cardPos[1]));
        assertTrue(aBoard.doFlippedCardsMatch());
        aBoard.removeCardsFromBoard();
        assertEquals(2,aBoard.getCardsFound());


        aBoard = new StandardBoard(6,8,2);
        assertEquals(0,aBoard.getCardsFound());
        aBoard.flipCard(2,2);
        aBoard.getCardContent(2,2);
        aBoard.removeCardsFromBoard();
        assertEquals(1,aBoard.getCardsFound());

        aBoard = new StandardBoard(6,8,2);
        boardMap = new HashMap<>();
        for (int k = 0; k < aBoard.getRows(); k++)
            for (int m = 0; m < aBoard.getCol(); m++) {
                cardPos = new int[2];
                cardPos[0] = k;
                cardPos[1] = m;
                boardMap.put(cardPos, aBoard.getCardContent(k, m));

            }

        cardPos=new int[2];
        cardPos[0] = 2;
        cardPos[1] = 2;
        assertTrue(aBoard.flipCard(2, 2));
        for (HashMap.Entry<int[], Character> entry : boardMap.entrySet()) {
            if ((entry.getValue() == aBoard.getCardContent(2, 2)) && (entry.getKey()[0] != cardPos[0]||(entry.getKey()[1]!=cardPos[1]))) {
                cardPos[0] = entry.getKey()[0];
                cardPos[1] = entry.getKey()[1];
                break;
            }
        }

        assertTrue(aBoard.flipCard(cardPos[0], cardPos[1]));
        assertTrue(aBoard.doFlippedCardsMatch());
        aBoard.removeCardsFromBoard();
        assertEquals(2,aBoard.getCardsFound());


        aBoard = new StandardBoard(6,6,3);
        assertEquals(0,aBoard.getCardsFound());
        aBoard.flipCard(2,2);
        aBoard.getCardContent(2,2);
        aBoard.removeCardsFromBoard();
        assertEquals(1,aBoard.getCardsFound());

        aBoard = new StandardBoard(6, 6, 3);
        boardMap = new HashMap<>();
        for (int k = 0; k < aBoard.getRows(); k++){
            for (int m = 0; m < aBoard.getCol(); m++) {
                cardPos = new int[2];
                cardPos[0] = k;
                cardPos[1] = m;
                boardMap.put(cardPos, aBoard.getCardContent(k, m));
            }
        }

        aBoard.flipCard(3,3);
        cardPos = new int[2];
        cardPos[0] =3;
        cardPos[1] =3;
        int[] cardPos2 = new int[2];
        for (HashMap.Entry<int[], Character> entry : boardMap.entrySet())
            if ((entry.getValue() == aBoard.getCardContent(3, 3)) && (entry.getKey()[0] != cardPos[0]||(entry.getKey()[1]!=cardPos[1]))) {

                cardPos2[0] = entry.getKey()[0];
                cardPos2[1] = entry.getKey()[1];
                boardMap.remove(entry.getKey());
                break;
            }

        assertTrue(aBoard.flipCard(cardPos2[0],cardPos2[1]));
        for (HashMap.Entry<int[], Character> entry : boardMap.entrySet())
            if ((entry.getValue() == aBoard.getCardContent(3, 3)) && (entry.getKey()[0] != cardPos[0]||(entry.getKey()[1]!=cardPos[1]))&& (entry.getKey()[0] != cardPos2[0]||(entry.getKey()[1]!=cardPos2[1]))) {
                cardPos[0] = entry.getKey()[0];
                cardPos[1] = entry.getKey()[1];
                break;
            }
        assertTrue(aBoard.flipCard(cardPos[0],cardPos[1]));
        assertTrue(aBoard.doFlippedCardsMatch());
        aBoard.removeCardsFromBoard();
        assertEquals(3,aBoard.getCardsFound());

    }

    @Test
    public void getRows() {
        System.out.println("getRows");
        StandardBoard aBoard = new StandardBoard(4,6,2);
        assertEquals(4, aBoard.getRows());

        aBoard = new StandardBoard(6,8,2);
        assertEquals(6, aBoard.getRows());

        aBoard = new StandardBoard(6,6,3);
        assertEquals(6, aBoard.getRows());
    }

    @Test
    public void getCol() {
        System.out.println("getCol");
        StandardBoard aBoard = new StandardBoard(4,6,2);
        assertEquals(6, aBoard.getCol());

        aBoard = new StandardBoard(6,8,2);
        assertEquals(8, aBoard.getCol());

        aBoard = new StandardBoard(6,6,3);
        assertEquals(6, aBoard.getCol());
    }
}