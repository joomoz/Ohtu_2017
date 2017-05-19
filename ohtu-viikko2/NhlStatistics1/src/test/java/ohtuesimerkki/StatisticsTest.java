/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jns
 */
public class StatisticsTest {
    
    public StatisticsTest() {

    }
    
    
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    } 

    /**
     * Test of search method, of class Statistics.
     */
    @Test
    public void testSearchReturnsNameOfTheFinnishLegend() {
        Player kurri = stats.search("Kurri");
        assertEquals("Kurri",kurri.getName());
    }
    
    @Test
    public void testSearchDoesntReturnAnythingIfNameIsInvalid() {
        Player koivu = stats.search("Koivu");
        assertEquals(null, koivu);
    }

    /**
     * Test of team method, of class Statistics.
     */
    @Test
    public void testTeam() {
        List<Player> pit = stats.team("PIT");
        assertEquals(1, pit.size());
    }

    /**
     * Test of topScorers method, of class Statistics.
     */
    @Test
    public void testTopScorers() {
        List<Player> top = stats.topScorers(2);
        assertEquals(top.size(), 2);
    }
    
    @Test
    public void testTopScorerIsCorrect() {
        List<Player> top = stats.topScorers(3);
        assertEquals(top.get(0).getName(),"Gretzky");
    }
    
}
