import org.junit.Test;
import player.Player;
import selection.Selection;

import java.io.IOException;

import static glue.PlayerHelper.aPlayer;
import static org.junit.Assert.assertEquals;

public class RulesEngineTests {


    @Test
    public void rockBeatsScissors() throws IOException {
        Player player1 = aPlayer();
        Player player2 = aPlayer();

        RulesEngine rulesEngine = new RulesEngine();

        assertEquals(player1, rulesEngine.determineWinner(player1, Selection.Rock, player2, Selection.Scissors));
    }

    @Test
    public void scissorsBeatPaper() throws IOException {
        Player player1 = aPlayer();
        Player player2 = aPlayer();

        RulesEngine rulesEngine = new RulesEngine();

        assertEquals(player1, rulesEngine.determineWinner(player1, Selection.Scissors, player2, Selection.Paper));
    }

    @Test
    public void paperBeatsRock() throws IOException {
        Player player1 = aPlayer();
        Player player2 = aPlayer();

        RulesEngine rulesEngine = new RulesEngine();

        assertEquals(player2, rulesEngine.determineWinner(player1, Selection.Rock, player2, Selection.Paper));
    }

    @Test
    public void drawsReturnNull() {
        Player player1 = aPlayer();
        Player player2 = aPlayer();

        RulesEngine rulesEngine = new RulesEngine();

        assertEquals(null, rulesEngine.determineWinner(player1, Selection.Rock, player2, Selection.Rock));
    }

}
