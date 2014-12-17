import player.ComputerPlayer;
import player.HumanPlayer;
import player.Player;
import selection.Selection;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RockPaperScissorsGame {
    private Player player1;
    private Player player2;
    private final Player observer;

    public static void main(String[] args) {
        Player player1;
        Player observer;
        if (args.length >0 && "watch".equals(args[0])) {
            player1 = new ComputerPlayer("Player 1");
            observer = new HumanPlayer("", new BufferedReader(new InputStreamReader(System.in)), System.out);
        } else {
            player1 = new HumanPlayer("Player 1", new BufferedReader(new InputStreamReader(System.in)), System.out);
            observer = new ComputerPlayer("");
        }
        Player player2 = new ComputerPlayer("Player 2");

        new RockPaperScissorsGame(player1, player2, observer).start();
    }

    public RockPaperScissorsGame(final Player player1, final Player player2) {
        this(player1, player2, new ComputerPlayer(""));
    }

    public RockPaperScissorsGame(final Player player1, final Player player2, final Player observer) {
        this.player1 = player1;
        this.player2 = player2;
        this.observer = observer;
    }

    public void start() {
        Player winner = null;
        while(winner == null) {
            Selection player1Selection = player1.makeSelection();
            Selection player2Selection = player2.makeSelection();
            player1.informOfOpponentsSelection(player2, player2Selection);
            observer.informOfOpponentsSelection(player1, player1Selection);
            observer.informOfOpponentsSelection(player2, player2Selection);
            winner = calculateWinner(player1, player1Selection, player2, player2Selection);

            player1.informOfResult(winner);
            observer.informOfResult(winner);
        }
    }

    private Player calculateWinner(final Player player1, final Selection player1Selection, final Player player2, final Selection player2Selection) {
        if (player1Selection == player2Selection) {
            return null;
        }

        if (player1Selection == Selection.Rock     && player2Selection == Selection.Paper
         || player1Selection == Selection.Paper    && player2Selection == Selection.Scissors
         || player1Selection == Selection.Scissors && player2Selection == Selection.Rock) {

            return player2;
        }
        return player1;
    }

}
