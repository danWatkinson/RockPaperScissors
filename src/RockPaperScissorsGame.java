import player.HumanPlayer;
import player.Player;
import selection.Selection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class RockPaperScissorsGame {
    private final BufferedReader in;
    private final PrintStream out;
    private Player player1;
    private Player player2;

    public static void main(String[] args) {
        new RockPaperScissorsGame(new BufferedReader(new InputStreamReader(System.in)), System.out)
                .start();
    }

    public RockPaperScissorsGame(final BufferedReader in, final PrintStream out) {
        this.in = in;
        this.out = out;
        player1 = new HumanPlayer(in, out);
    }

    public void start() {
        Selection playersSelection = player1.makeSelection();
        out.println("You selected " + playersSelection);
        if (player2 != null) {
            player2.makeSelection();
        }
    }

    public void setPlayer2(final Player player2) {
        this.player2 = player2;
    }
}
