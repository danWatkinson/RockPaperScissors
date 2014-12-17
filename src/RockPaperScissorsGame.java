import player.ComputerPlayer;
import player.HumanPlayer;
import player.Player;
import result.Result;
import selection.Selection;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RockPaperScissorsGame {
    private Player player1;
    private Player player2;

    public static void main(String[] args) {
        Player player1 = new HumanPlayer(new BufferedReader(new InputStreamReader(System.in)), System.out);
        Player player2 = new ComputerPlayer();

        new RockPaperScissorsGame(player1, player2).start();
    }

    public RockPaperScissorsGame(final Player player1, final Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void start() {
        Result result = Result.DRAW;
        while(result == Result.DRAW) {
            Selection player1Selection = player1.makeSelection();
            Selection player2Selection = player2.makeSelection();
            player1.informOfOpponentsSelection(player2Selection);

            result = player1Selection.resolveAgainst(player2Selection);

            player1.informOfResult(result);
        }
    }

}
