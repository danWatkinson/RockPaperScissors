import player.ComputerPlayer;
import player.HumanPlayer;
import player.Player;
import selection.Selection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class RockPaperScissorsGame {
    private final PrintStream out;
    private Player player1;
    private Player player2;

    public static void main(String[] args) {
        Player player1 = new HumanPlayer(new BufferedReader(new InputStreamReader(System.in)), System.out);
        Player player2 = new ComputerPlayer();

        new RockPaperScissorsGame(player1, player2, System.out).start();
    }

    public RockPaperScissorsGame(final Player player1, final Player player2, final PrintStream out) {
        this.player1 = player1;
        this.player2 = player2;
        this.out = out;
    }

    public void start() {
        Selection player1Selection = player1.makeSelection();
        Selection player2Selection = player2.makeSelection();
        player1.informOfOpponentsSelection(player2Selection);
        boolean winner = "Rock".equals(player1Selection.toString());
        player1.informOfResult(winner);
    }

}
