import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void setName()
    {
        System.out.println("setName");
        Player aPlayer = new Player();
        aPlayer.setName("Kostis");
        assertEquals("Kostis",aPlayer.getName());
        aPlayer.setName("L0uKiL0uk");
        assertEquals("L0uKiL0uk",aPlayer.getName());

    }


    @Test
    public void getName() {
        System.out.println("getName");
        Player aPLayer = new Player();
        aPLayer.setName("Giannis");
        assertEquals("Giannis",aPLayer.getName());
        aPLayer.setName("Nick");
        assertEquals("Nick",aPLayer.getName());

    }

    @Test
    public void updateCardPicks() {
        System.out.println("updateCardPicks");
        Player aPLayer = new Player();
        assertEquals(0,aPLayer.getTotalCardPicks());
        aPLayer.updateCardPicks();
        assertEquals(1 , aPLayer.getTotalCardPicks());
        aPLayer.updateCardPicks();
        aPLayer.updateCardPicks();
        assertEquals(3 , aPLayer.getTotalCardPicks());
    }

    @Test
    public void updatePairsFound() {
        System.out.println("updatePairsFound");
        Player aPLayer = new Player();
        assertEquals(0,aPLayer.getTotalPairsFound());
        aPLayer.updatePairsFound();
        assertEquals(1 , aPLayer.getTotalPairsFound());
        aPLayer.updatePairsFound();
        aPLayer.updatePairsFound();
        assertEquals(3 , aPLayer.getTotalPairsFound());
    }

    @Test
    public void getTotalCardPics() {
        System.out.println("getTotalCardPicks");
        Player aPLayer = new Player();
        assertEquals(0,aPLayer.getTotalCardPicks());
        aPLayer.updateCardPicks();
        assertEquals(1 , aPLayer.getTotalCardPicks());
        aPLayer.updateCardPicks();
        aPLayer.updateCardPicks();
        assertEquals(3 , aPLayer.getTotalCardPicks());
    }

    @Test
    public void getTotalPairsFound() {
        System.out.println("getTotalPairsFound");
        Player aPLayer = new Player();
        assertEquals(0 , aPLayer.getTotalPairsFound());
        aPLayer.updatePairsFound();
        assertEquals(1,aPLayer.getTotalPairsFound());
        aPLayer.updatePairsFound();
        aPLayer.updatePairsFound();
        assertEquals(3,aPLayer.getTotalPairsFound());
    }
}