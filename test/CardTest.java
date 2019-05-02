import org.junit.Test;


import static org.junit.Assert.*;

public class CardTest {


    @Test
    public void getContent() {
        System.out.println("getContent");
        Card aCard = new Card('A');
        assertEquals('A',aCard.getContent());
    }

    @Test
    public void setContent() {
        System.out.println("setContent");
        Card aCard = new Card('A');
        aCard.setContent('B');
        Card bCard = new Card('B');
        assertEquals(bCard.getContent(),aCard.getContent());
    }

    @Test
    public void isTaken() {
        System.out.println("isTaken");
        Card aCard = new Card('A');
        assertFalse(aCard.isTaken());
        aCard.setTaken(true);
        assertTrue(aCard.isTaken());
    }

    @Test
    public void setTaken() {
        System.out.println("setTaken");
        Card aCard = new Card('A');
        assertFalse(aCard.isTaken());
        aCard.setTaken(true);
        assertTrue(aCard.isTaken());
        aCard.setTaken(false);
        assertFalse(aCard.isTaken());

    }
}