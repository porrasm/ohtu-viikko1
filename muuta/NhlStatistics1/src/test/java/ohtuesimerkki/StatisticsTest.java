package ohtuesimerkki;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.util.stream.Collectors;

public class StatisticsTest {

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

    @Test
    public void searchTest() {

        for (Player p : readerStub.getPlayers()) {

            Player other = stats.search(p.getName());

            if (other == null) {
                fail("Player was null.");
            }

            assertEquals(p.getName(), other.getName());
        }

        Player player = stats.search("ri");

        if (player == null) {
            fail("Player was null.");
        }

        player = stats.search("abcdefghijk");

        assertEquals(null, player);
    }

    @Test
    public void teamPlayerSearchTest() {

        List<Player> players = stats.team("EDM");

        if (players == null) {
            fail("Player list was null.");
        }

        assertEquals(3, players.size());



        players = stats.team("PIT");
        assertEquals(1, players.size());
    }

    @Test
    public void topScoreTest() {

        List<Player> players = stats.topScorers(3);
        assertEquals(4, players.size());

        assertEquals("Gretzky", players.get(0).getName());

    }
}